package smytsyk.final_project.library.controller.command.impl.reader_commands;

import smytsyk.final_project.library.controller.command.Command;
import smytsyk.final_project.library.service.I18N;
import smytsyk.final_project.library.service.OrderService;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

/**
 * Command that sends order request
 */
public class SendOrderCommand implements Command {
    @Override
    public void execute(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        int book_id = Integer.parseInt(req.getParameter("book_id"));
        int user_id = Integer.parseInt(req.getParameter("user_id"));
        String date = req.getParameter("date");
        if (!new OrderService().sendsOrderRequest(user_id, book_id, date)) {
            req.getSession().setAttribute("error", I18N.translate("error_accept_order", req.getSession()));
        } else {
            req.getSession().setAttribute("error", I18N.translate("success", req.getSession()));
        }
        resp.sendRedirect("/Controller?command=go_to_catalog_page");
    }
}
