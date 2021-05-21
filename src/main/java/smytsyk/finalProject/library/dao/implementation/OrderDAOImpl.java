package smytsyk.finalProject.library.dao.implementation;

import org.apache.log4j.Logger;
import smytsyk.finalProject.library.dao.interfaces.OrderDAO;
import smytsyk.finalProject.library.entitiy.Order;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.time.LocalDate;
import java.sql.Date;

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
        Order order = new Order();
        try {
            order.setId(rs.getInt(1));
            order.setReaderId(rs.getInt(2));
            order.setBookId(rs.getInt(3));
            order.setOrderStatusId(rs.getInt(4));
            Date returnDate = rs.getDate(5);
            LocalDate date = returnDate == null ? null :
                    returnDate.toLocalDate();
            order.setReturnDate(date);
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
