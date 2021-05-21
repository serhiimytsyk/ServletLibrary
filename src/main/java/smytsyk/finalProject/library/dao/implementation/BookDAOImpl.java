package smytsyk.finalProject.library.dao.implementation;

import org.apache.log4j.Logger;
import smytsyk.finalProject.library.dao.interfaces.BookDAO;
import smytsyk.finalProject.library.entitiy.Book;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.time.LocalDate;
import java.sql.Date;

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
        Book book = new Book();
        try {
            book.setId(rs.getInt(1));
            book.setName(rs.getString(2));
            book.setAuthor(rs.getString(3));
            book.setPublisher(rs.getString(4));
            Date publicationDate = rs.getDate(5);
            LocalDate date = publicationDate == null ? null :
                    publicationDate.toLocalDate();
            book.setPublicationDate(date);
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
