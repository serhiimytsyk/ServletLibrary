package smytsyk.final_project.library.service;

import smytsyk.final_project.library.dao.DAOFactory;
import smytsyk.final_project.library.entitiy.Book;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.List;

/**
 * Class with all needed services with books
 */
public class BookService {
    /**
     * Returns all books
     */
    public List<Book> getAllBooks() {
        return DAOFactory.getBookDAO().getAll();
    }

    /**
     * Returns all free books
     */
    public List<Book> getFreeBooks() {
        return DAOFactory.getBookDAO().getFreeBooks();
    }

    /**
     * Deletes book by id
     */
    public void deleteBook(int id) {
        DAOFactory.getBookDAO().delete(id);
    }

    /**
     * Gets book by id. If book doesn't exist return null
     */
    public Book getBook(int id) {
        return DAOFactory.getBookDAO().get(id);
    }

    /**
     * Inserts book to DB. Returns if it's successful
     */
    public boolean insertBook(String name, String author, String publisher, String date) {
        Book book = Book.builder().
                name(name).
                author(author).
                publisher(publisher).
                publicationDate(parseDate(date)).build();
        return DAOFactory.getBookDAO().insert(book);
    }

    /**
     * Updates information about book. Returns if it's successful
     */
    public boolean updateBook(int id, String name, String author, String publisher, String date) {
        Book book = Book.builder().
                id(id).
                name(name).
                author(author).
                publisher(publisher).
                publicationDate(parseDate(date)).build();
        DAOFactory.getBookDAO().update(book);
        return true;
    }

    /**
     * Parses date from String
     */
    public LocalDate parseDate(String date) {
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd.MM.yyyy");
        return LocalDate.parse(date, formatter);
    }

}
