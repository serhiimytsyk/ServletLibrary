package smytsyk.finalProject.library.controller.command.impl.librarianCommands.ordersCommands;

import smytsyk.finalProject.library.controller.command.Command;
import smytsyk.finalProject.library.entitiy.User;
import smytsyk.finalProject.library.service.I18N;
import smytsyk.finalProject.library.service.OrderService;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

/**
 * Command that closes order
 */
public class CloseOrderCommand implements Command {
    @Override
    public void execute(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        int id = Integer.parseInt(req.getParameter("order_id"));
        User user = (User) req.getSession().getAttribute("reader");
        if (!new OrderService().closeOrder(id)) {
            req.getSession().setAttribute("error", I18N.translate("error_close_order", req.getSession()));
        }
        resp.sendRedirect("/Controller?command=go_to_librarian_reader_orders_page&user_id=" + user.getId());
    }
}
