package smytsyk.finalProject.library.service;

import smytsyk.finalProject.library.dao.DAOFactory;
import smytsyk.finalProject.library.entitiy.Book;
import smytsyk.finalProject.library.entitiy.Order;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.List;
import java.util.stream.Collectors;

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
        List<Integer> notFreeBooksIds = new OrderService().getConfirmedOrders().
                stream().map(Order::getBookId).collect(Collectors.toList());
        return DAOFactory.getBookDAO().getAll().stream().
                filter(b -> !isListWithNumber(notFreeBooksIds, b.getId())).collect(Collectors.toList());
    }

    /**
     * Checks if number is in list
     */
    public boolean isListWithNumber(List<Integer> list, int i) {
        for (int j : list) if (i == j) return true;
        return false;
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
        if (!(isValidName(name) && isValidAuthor(author) && isValidPublisher(publisher) && isValidDate(date))) return false;
        Book book = new Book();
        book.setName(name);
        book.setAuthor(author);
        book.setPublisher(publisher);
        book.setPublicationDate(parseDate(date));
        return DAOFactory.getBookDAO().insert(book);
    }

    /**
     * Updates information about book. Returns if it's successful
     */
    public boolean updateBook(int id, String name, String author, String publisher, String date) {
        if (!(isValidName(name) && isValidAuthor(author) && isValidPublisher(publisher) && isValidDate(date))) return false;
        Book book = getBook(id);
        if (book == null) return false;
        book.setName(name);
        book.setAuthor(author);
        book.setPublisher(publisher);
        book.setPublicationDate(parseDate(date));
        DAOFactory.getBookDAO().update(book);
        return true;
    }

    /**
     * Checks if name is valid
     */
    public boolean isValidName(String name) {
        if (name == null || name.isEmpty() || name.length() > 30) return false;
        return name.matches("[-,.:;!?\\p{IsAlphabetic}\\s0-9]+");
    }

    /**
     * Checks if author is valid
     */
    public boolean isValidAuthor(String name) {
        if (name == null || name.isEmpty() || name.length() > 60) return false;
        return name.matches("[-\\p{IsAlphabetic}\\s]+");
    }

    /**
     * Checks if publisher is valid
     */
    public boolean isValidPublisher(String name) {
        if (name == null || name.isEmpty() || name.length() > 30) return false;
        return name.matches("[-,.:;!?\\p{IsAlphabetic}\\s0-9]+");
    }

    /**
     * Checks if date is valid
     */
    public boolean isValidDate(String date) {
        try {
            DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd.MM.yyyy");
            LocalDate localDate = LocalDate.parse(date, formatter);
            if (localDate.isAfter(LocalDate.now())) return false;
        } catch (Exception e) {
            return false;
        }
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
