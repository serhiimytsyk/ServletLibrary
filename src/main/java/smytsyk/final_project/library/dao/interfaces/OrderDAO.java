package smytsyk.final_project.library.dao.interfaces;

import smytsyk.final_project.library.entitiy.Order;

import java.util.List;


/**
 * DAO of Orders
 */
public interface OrderDAO extends AbstractDao<Order> {
    boolean confirmOrder(int id);
    boolean closeOrder(int id);
    List<Order> getUnconfirmedOrders();
    List<Order> getConfirmedOrdersByUserId(int id);
}
