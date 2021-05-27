package smytsyk.final_project.library.dao.implementation;

import org.apache.log4j.Logger;
import smytsyk.final_project.library.dao.DAOFactory;
import smytsyk.final_project.library.dao.DBManager;
import smytsyk.final_project.library.dao.interfaces.BookDAO;
import smytsyk.final_project.library.entitiy.Book;

import java.sql.*;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

/**
 * Implementation of BookDAO using POSTGRES
 */
public class BookDAOImpl implements BookDAO {
    private static final String TABLE = "books";
    private static final String[] FIELDS =
            new String[] {"id", "name", "author", "publisher", "publication_date"};
    private static final Logger log = Logger.getLogger(BookDAOImpl.class);

    @Override
    public Book getEntityFromRow(ResultSet rs) {
        Book book;
        try {
            Date publicationDate = rs.getDate(5);
            LocalDate date = publicationDate == null ? null :
                    publicationDate.toLocalDate();
            book = Book.builder().id(rs.getInt(1)).
                    name(rs.getString(2)).
                    author(rs.getString(3)).
                    publisher(rs.getString(4)).
                    publicationDate(date).build();
        } catch (SQLException e) {
            log.error("Cannot get Book from table row ", e);
            book = null;
        }
        return book;
    }

    @Override
    public void setRowByEntity(PreparedStatement preparedStatement, Book entity) {
        try {
            preparedStatement.setInt(1, entity.getId());
            preparedStatement.setString(2, entity.getName());
            preparedStatement.setString(3, entity.getAuthor());
            preparedStatement.setString(4, entity.getPublisher());
            preparedStatement.setDate(5, java.sql.Date.valueOf(entity.getPublicationDate()));
        } catch (SQLException e) {
            log.error("Cannot insert Book to table row ", e);
        }
    }

    @Override
    public List<Book> getFreeBooks() {
        List<Book> list = new ArrayList<>();
        String orders_table = DAOFactory.getOrderDAO().getTable();
        String query = "SELECT * FROM " + getTable() + " WHERE id NOT IN " +
                "(SELECT book_id FROM " + orders_table + " WHERE order_status_id = 1);";
        try (
                Connection connection = DBManager.getInstance().getConnection()) {
            try (Statement statement = connection.createStatement()) {
                try (ResultSet resultSet = statement.executeQuery(query)) {
                    while (resultSet.next()) {
                        Book entity = getEntityFromRow(resultSet);
                        list.add(entity);
                    }
                }
            }
        } catch (SQLException e) {
            logError("Cannot get entities from table ", e);
        }
        return list;
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
