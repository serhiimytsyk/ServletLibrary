package smytsyk.finalProject.library.dao;

import smytsyk.finalProject.library.dao.implementation.BookDAOImpl;
import smytsyk.finalProject.library.dao.implementation.OrderDAOImpl;
import smytsyk.finalProject.library.dao.implementation.UserDAOImpl;
import smytsyk.finalProject.library.dao.interfaces.BookDAO;
import smytsyk.finalProject.library.dao.interfaces.OrderDAO;
import smytsyk.finalProject.library.dao.interfaces.UserDAO;

/**
 * Factory of DAO implementations of project entities
 */
public class DAOFactory {
    /**
     * Returns DAO of Books
     */
    public static BookDAO getBookDAO() {
        return new BookDAOImpl();
    }

    /**
     * Returns DAO of Orders
     */
    public static OrderDAO getOrderDAO() {
        return new OrderDAOImpl();
    }

    /**
     * Returns DAO of Users
     */
    public static UserDAO getUserDAO() { return new UserDAOImpl(); }
}
