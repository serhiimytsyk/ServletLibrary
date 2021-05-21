package smytsyk.finalProject.library.service;

import smytsyk.finalProject.library.dao.DAOFactory;
import smytsyk.finalProject.library.entitiy.Book;
import smytsyk.finalProject.library.entitiy.Order;
import smytsyk.finalProject.library.entitiy.User;

import java.time.LocalDate;
import static java.time.temporal.ChronoUnit.DAYS;
import java.time.format.DateTimeFormatter;
import java.util.List;
import java.util.stream.Collectors;

/**
 * Class with all needed services with orders
 */
public class OrderService {
    /**
     * Deletes order by id
     */
    public void deleteOrder(int id) {
        DAOFactory.getOrderDAO().delete(id);
    }

    /**
     * Returns all unconfirmed orders
     */
    public List<Order> getUnconfirmedOrders() {
        return DAOFactory.getOrderDAO().getAll().stream().filter(o -> o.getOrderStatusId() == 0).collect(Collectors.toList());
    }

    /**
     * Returns all confirmed orders
     */
    public List<Order> getConfirmedOrders() {
        return DAOFactory.getOrderDAO().getAll().stream().filter(o -> o.getOrderStatusId() == 1).collect(Collectors.toList());
    }

    /**
     * Returns all confirmed orders of this user
     */
    public List<Order> getConfirmedOrdersByUser(int user_id) {
        return DAOFactory.getOrderDAO().getAll().stream().filter(o -> o.getOrderStatusId() == 1).
                filter(o -> o.getReaderId() == user_id).collect(Collectors.toList());
    }

    /**
     * Send orders request by userId, bookId, and returnDate. Return if it's successful
     */
    public boolean sendsOrderRequest(int userId, int bookId, String date) {
        if (!(isValidBookAccept(bookId) && isValidUser(userId) && isValidDate(date))) return false;
        Order order = new Order();
        order.setBookId(bookId);
        order.setReaderId(userId);
        order.setOrderStatusId(0);
        order.setReturnDate(parseDate(date));
        return DAOFactory.getOrderDAO().insert(order);
    }

    /**
     * Confirms order by id. Returns if it's successful
     */
    public boolean confirmOrder(int id) {
        Order order = DAOFactory.getOrderDAO().get(id);
        if (order == null || order.getOrderStatusId() != 0 || order.getReturnDate().isBefore(LocalDate.now())) return false;
        if (!(isValidUser(order.getReaderId()) && isValidBookAccept(order.getBookId()))) return false;
        order.setOrderStatusId(1);
        DAOFactory.getOrderDAO().update(order);
        return true;
    }

    /**
     * Closes order. Returns if it's successful
     */
    public boolean closeOrder(int id) {
        Order order = DAOFactory.getOrderDAO().get(id);
        if (order == null || order.getOrderStatusId() != 1) return false;
        if (!(isValidUser(order.getReaderId()) && isValidBookClose(order.getBookId()))) return false;
        order.setOrderStatusId(2);
        DAOFactory.getOrderDAO().update(order);
        return true;
    }

    /**
     * Checks if user is reader
     */
    public boolean isValidUser(int id) {
        User user = DAOFactory.getUserDAO().get(id);
        return user != null && user.getRoleId() == 1;
    }

    /**
     * Checks if book is in library
     */
    public boolean isValidBookAccept(int id) {
        Book book = DAOFactory.getBookDAO().get(id);
        BookService service = new BookService();
        return book != null && service.
                isListWithNumber(service.getFreeBooks().stream().map(Book::getId).collect(Collectors.toList()), id);
    }

    /**
     * Checks if book can be returned to library
     */
    public boolean isValidBookClose(int id) {
        Book book = DAOFactory.getBookDAO().get(id);
        BookService service = new BookService();
        return book != null
                && service.isListWithNumber(service.getAllBooks().stream().map(Book::getId).collect(Collectors.toList()), id)
                && !service.isListWithNumber(service.getFreeBooks().stream().map(Book::getId).collect(Collectors.toList()), id);
    }

    /**
     * Checks if date is valid
     */
    public boolean isValidDate(String date) {
        try {
            DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd.MM.yyyy");
            LocalDate localDate = LocalDate.parse(date, formatter);
            if (localDate.isBefore(LocalDate.now())) return false;
        } catch (Exception e) {
            return false;
        }
        return true;
    }

    /**
     * Parses date from the String
     */
    public LocalDate parseDate(String date) {
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd.MM.yyyy");
        return LocalDate.parse(date, formatter);
    }

    /**
     * Counts fine from returnDate to nowDate
     */
    public static String countFine(LocalDate returnDate, LocalDate now) {
        return String.valueOf(10*DAYS.between(returnDate, now));
    }
}
