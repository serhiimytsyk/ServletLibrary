package smytsyk.final_project.library.service;

import smytsyk.final_project.library.dao.DAOFactory;
import smytsyk.final_project.library.entitiy.Order;

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
        if (!(isValidDateForSending(date))) return false;
        Order order = Order.builder().
                bookId(bookId).
                readerId(userId).
                orderStatusId(0).
                returnDate(parseDate(date)).build();
        return DAOFactory.getOrderDAO().insert(order);
    }

    /**
     * Confirms order by id. Returns if it's successful
     */
    public boolean confirmOrder(int id) {
        return DAOFactory.getOrderDAO().confirmOrder(id);
    }

    /**
     * Closes order. Returns if it's successful
     */
    public boolean closeOrder(int id) {
        return DAOFactory.getOrderDAO().closeOrder(id);
    }

    /**
     * Checks if date is valid
     */
    public boolean isValidDateForSending(String date) {
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
