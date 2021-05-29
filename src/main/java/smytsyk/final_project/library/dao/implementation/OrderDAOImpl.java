package smytsyk.final_project.library.dao.implementation;

import org.apache.log4j.Logger;
import smytsyk.final_project.library.dao.DAOFactory;
import smytsyk.final_project.library.dao.DBManager;
import smytsyk.final_project.library.dao.interfaces.OrderDAO;
import smytsyk.final_project.library.entitiy.Order;

import java.sql.*;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

/**
 * Implementation of OrderDAO using POSTGRES
 */
public class OrderDAOImpl implements OrderDAO {
    private static final String TABLE = "orders";
    private static final String[] FIELDS =
            new String[] {"id", "reader_id", "book_id", "order_status_id", "return_date"};
    private static final Logger log = Logger.getLogger(OrderDAOImpl.class);

    @Override
    public Order getEntityFromRow(ResultSet rs) {
        Order order;
        try {
            Date returnDate = rs.getDate(5);
            LocalDate date = returnDate == null ? null :
                    returnDate.toLocalDate();
            order = Order.builder().id(rs.getInt(1)).
                    readerId(rs.getInt(2)).
                    bookId(rs.getInt(3)).
                    orderStatusId(rs.getInt(4)).
                    returnDate(date).build();
        } catch (SQLException e) {
            log.error("Cannot get Order from table row ", e);
            order = null;
        }
        return order;
    }

    @Override
    public void setRowByEntity(PreparedStatement preparedStatement, Order entity) {
        try {
            preparedStatement.setInt(1, entity.getId());
            preparedStatement.setInt(2, entity.getReaderId());
            preparedStatement.setInt(3, entity.getBookId());
            preparedStatement.setInt(4, entity.getOrderStatusId());
            preparedStatement.setDate(5, java.sql.Date.valueOf(entity.getReturnDate()));
        } catch (SQLException e) {
            log.error("Cannot insert Order to table row ", e);
        }
    }

    @Override
    public boolean confirmOrder(int id) {
        String orders_table = DAOFactory.getOrderDAO().getTable();
        String books_table = DAOFactory.getBookDAO().getTable();
        String subquery = "SELECT id FROM " + books_table + " " +
                "WHERE id NOT IN " +
                "(SELECT book_id FROM " + orders_table + " WHERE order_status_id = 1)";
        String query = "UPDATE " + getTable() + " SET order_status_id = 1 " +
                "WHERE id = ? " +
                "AND book_id IN (" + subquery + ") " +
                "AND order_status_id = 0 " +
                "AND return_date >= now()::date;";
        try (Connection connection = DBManager.getInstance().getConnection()){
            try (PreparedStatement preparedStatement = connection.prepareStatement(query)) {
                preparedStatement.setInt(1, id);
                return preparedStatement.executeUpdate() > 0;
            }
        } catch (SQLException e) {
            logError("Cannot confirm order ", e);
        }
        return false;
    }

    @Override
    public boolean closeOrder(int id) {
        String query = "UPDATE " + getTable() + " SET order_status_id = 2 " +
                "WHERE id = ? " +
                "AND order_status_id = 1;";
        try (Connection connection = DBManager.getInstance().getConnection()){
            try (PreparedStatement preparedStatement = connection.prepareStatement(query)) {
                preparedStatement.setInt(1, id);
                return preparedStatement.executeUpdate() > 0;
            }
        } catch (SQLException e) {
            logError("Cannot close order ", e);
        }
        return false;
    }

    @Override
    public List<Order> getUnconfirmedOrders() {
        List<Order> orders = new ArrayList<>();
        String query = "SELECT * FROM " + getTable() + " WHERE order_status_id = 0;";
        try (Connection connection = DBManager.getInstance().getConnection()) {
            try (Statement statement = connection.createStatement()) {
                try (ResultSet resultSet = statement.executeQuery(query)) {
                    while (resultSet.next()) {
                        Order order = getEntityFromRow(resultSet);
                        orders.add(order);
                    }
                }
            }
        } catch (SQLException e) {
            logError("Cannot get entities from table ", e);
        }
        return orders;
    }

    @Override
    public List<Order> getConfirmedOrdersByUserId(int id) {
        List<Order> orders = new ArrayList<>();
        String query = "SELECT * FROM " + getTable() + " WHERE order_status_id = 1 AND reader_id = ?;";
        try (Connection connection = DBManager.getInstance().getConnection()) {
            try (PreparedStatement preparedStatement = connection.prepareStatement(query)) {
                preparedStatement.setInt(1, id);
                try (ResultSet resultSet = preparedStatement.executeQuery()) {
                    while (resultSet.next()) {
                        Order order = getEntityFromRow(resultSet);
                        orders.add(order);
                    }
                }
            }
        } catch (SQLException e) {
            logError("Cannot get entities from table ", e);
        }
        return orders;
    }

    @Override
    public String getTable() {
        return TABLE;
    }

    @Override
    public String[] getFields() {
        return FIELDS;
    }

    @Override
    public void logError(String text, Throwable e) {
        log.error(text, e);
    }
}
