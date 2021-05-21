package smytsyk.final_project.library.dao;

import smytsyk.final_project.library.dao.implementation.BookDAOImpl;
import smytsyk.final_project.library.dao.implementation.OrderDAOImpl;
import smytsyk.final_project.library.dao.implementation.UserDAOImpl;
import smytsyk.final_project.library.dao.interfaces.BookDAO;
import smytsyk.final_project.library.dao.interfaces.OrderDAO;
import smytsyk.final_project.library.dao.interfaces.UserDAO;

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
