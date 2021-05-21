package smytsyk.finalProject.library.controller.command.impl.librarianCommands.GoCommands;

import smytsyk.finalProject.library.controller.command.Command;
import smytsyk.finalProject.library.entitiy.Order;
import smytsyk.finalProject.library.entitiy.User;
import smytsyk.finalProject.library.service.OrderService;
import smytsyk.finalProject.library.service.UserService;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.time.format.DateTimeFormatter;
import java.util.List;
import java.util.stream.Collectors;

/**
 * Command that redirects librarian to page with orders of some user
 */
public class GoToLibrarianReaderOrdersPageCommand implements Command {
    private static final int ORDERS_PER_PAGE = 20;

    @Override
    public void execute(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String pageStr = req.getParameter("page");
        if (pageStr == null || pageStr.isEmpty()) pageStr = "0";
        int page = Integer.parseInt(pageStr);

        int id = Integer.parseInt(req.getParameter("user_id"));
        User user = new UserService().getUser(id);
        List<Order> orders = new OrderService().getConfirmedOrdersByUser(id).
                stream().skip(page * ORDERS_PER_PAGE).limit(ORDERS_PER_PAGE).collect(Collectors.toList());

        req.getSession().setAttribute("pageNum", page);
        req.getSession().setAttribute("reader", user);
        req.getSession().setAttribute("orders", orders);
        req.getSession().setAttribute("formatter", DateTimeFormatter.ofPattern("dd.MM.yyyy"));
        req.getRequestDispatcher("/librarian_reader_orders.jsp").forward(req, resp);
    }
}
