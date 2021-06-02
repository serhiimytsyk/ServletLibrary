package smytsyk.final_project.library.controller.command.impl.librarian_commands.go_commands;

import smytsyk.final_project.library.controller.command.Command;
import smytsyk.final_project.library.controller.command.impl.PaginationUtil;
import smytsyk.final_project.library.entitiy.Order;
import smytsyk.final_project.library.service.OrderService;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.time.format.DateTimeFormatter;
import java.util.List;

/**
 * Command that redirects librarian to page with order requests
 */
public class GoToLibrarianOrdersPageCommand implements Command {
    private static final int ORDERS_PER_PAGE = 20;

    @Override
    public void execute(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        List<Order> orders = new OrderService().getUnconfirmedOrders();
        orders = PaginationUtil.paginateList(orders, ORDERS_PER_PAGE, req);
        req.getSession().setAttribute("orders", orders);
        req.getSession().setAttribute("formatter", DateTimeFormatter.ofPattern("dd.MM.yyyy"));
        req.getRequestDispatcher("/librarian_orders.jsp").forward(req, resp);
    }
}
