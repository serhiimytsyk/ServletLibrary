package smytsyk.finalProject.library.controller.command.impl.librarianCommands.ordersCommands;

import smytsyk.finalProject.library.controller.command.Command;
import smytsyk.finalProject.library.service.OrderService;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

/**
 * Command that rejects order request
 */
public class RejectOrderCommand implements Command {
    @Override
    public void execute(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        int id = Integer.parseInt(req.getParameter("order_id"));
        new OrderService().deleteOrder(id);
        resp.sendRedirect("/Controller?command=go_to_librarian_orders_page");
    }
}
