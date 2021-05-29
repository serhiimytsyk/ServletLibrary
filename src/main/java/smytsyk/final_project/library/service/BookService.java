package smytsyk.final_project.library.service;

import smytsyk.final_project.library.dao.DAOFactory;
import smytsyk.final_project.library.dao.interfaces.BookDAO;
import smytsyk.final_project.library.entitiy.Book;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.List;

/**
 * Class with all needed services with books
 */
public class BookService {
    private final BookDAO bookDAO = DAOFactory.getBookDAO();
    /**
     * Returns all books
     */
    public List<Book> getAllBooks() {
        return bookDAO.getAll();
    }

    /**
     * Returns all free books
     */
    public List<Book> getFreeBooks() {
        return bookDAO.getFreeBooks();
    }

    /**
     * Deletes book by id
     */
    public void deleteBook(int id) {
        bookDAO.delete(id);
    }

    /**
     * Gets book by id. If book doesn't exist return null
     */
    public Book getBook(int id) {
        return bookDAO.get(id);
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
        return bookDAO.insert(book);
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
        return bookDAO.update(book);
    }

    /**
     * Parses date from String
     */
    public LocalDate parseDate(String date) {
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd.MM.yyyy");
        return LocalDate.parse(date, formatter);
    }
}
