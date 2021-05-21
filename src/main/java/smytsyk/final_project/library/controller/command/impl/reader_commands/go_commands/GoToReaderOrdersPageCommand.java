package smytsyk.final_project.library.controller.command.impl.reader_commands.go_commands;

import smytsyk.final_project.library.controller.command.Command;
import smytsyk.final_project.library.entitiy.Order;
import smytsyk.final_project.library.entitiy.User;
import smytsyk.final_project.library.service.BookService;
import smytsyk.final_project.library.service.OrderService;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

/**
 * Command that redirects user to page with his orders
 */
public class GoToReaderOrdersPageCommand implements Command {
    private static final int ORDERS_PER_PAGE = 20;

    @Override
    public void execute(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String pageStr = req.getParameter("page");
        if (pageStr == null || pageStr.isEmpty()) pageStr = "0";
        int page = Integer.parseInt(pageStr);

        User user = (User) req.getSession().getAttribute("user");
        List<Order> orders = new OrderService().getConfirmedOrdersByUser(user.getId()).
                stream().skip(page * ORDERS_PER_PAGE).limit(ORDERS_PER_PAGE).collect(Collectors.toList());
        Map<Integer, String> orderIdToBookName = new HashMap<>();
        Map<Integer, String> orderIdToFine = new HashMap<>();
        orders.forEach(o -> {
            orderIdToBookName.put(o.getId(), new BookService().getBook(o.getBookId()).getName());
            orderIdToFine.put(o.getId(), (LocalDate.now().isAfter(o.getReturnDate()) ?
                    OrderService.countFine(o.getReturnDate(), LocalDate.now()) : "-"));
        });
        req.getSession().setAttribute("pageNum", page);
        req.getSession().setAttribute("orders", orders);
        req.getSession().setAttribute("orderIdToBookName", orderIdToBookName);
        req.getSession().setAttribute("orderIdToFine", orderIdToFine);
        req.getSession().setAttribute("formatter", DateTimeFormatter.ofPattern("dd.MM.yyyy"));
        req.getRequestDispatcher("/reader_orders.jsp").forward(req, resp);
    }
}
