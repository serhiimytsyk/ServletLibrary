package smytsyk.final_project.library.controller.command.impl.admin_commands.go_commands;

import smytsyk.final_project.library.controller.command.Command;
import smytsyk.final_project.library.entitiy.Book;
import smytsyk.final_project.library.service.BookService;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.time.format.DateTimeFormatter;
import java.util.List;
import java.util.stream.Collectors;

/**
 * Command that redirects admin to the page with books
 */
public class GoToAdminBooksPageCommand implements Command {
    private static final int BOOKS_PER_PAGE = 5;

    @Override
    public void execute(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String pageStr = req.getParameter("page");
        if (pageStr == null || pageStr.isEmpty()) pageStr = "0";
        int page = Integer.parseInt(pageStr);

        List<Book> books = new BookService().getAllBooks().
                stream().skip(page * BOOKS_PER_PAGE).limit(BOOKS_PER_PAGE).collect(Collectors.toList());

        req.getSession().setAttribute("pageNum", page);
        req.getSession().setAttribute("books", books);
        req.getSession().setAttribute("formatter", DateTimeFormatter.ofPattern("dd.MM.yyyy"));
        req.getRequestDispatcher("/admin_books.jsp").forward(req, resp);
    }
}
