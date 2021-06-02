package smytsyk.final_project.library.controller.command.impl.admin_commands.go_commands;

import smytsyk.final_project.library.controller.command.Command;
import smytsyk.final_project.library.controller.command.impl.PaginationUtil;
import smytsyk.final_project.library.entitiy.Book;
import smytsyk.final_project.library.service.BookService;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.time.format.DateTimeFormatter;
import java.util.List;

/**
 * Command that redirects admin to the page with books
 */
public class GoToAdminBooksPageCommand implements Command {
    private static final int BOOKS_PER_PAGE = 5;

    @Override
    public void execute(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        List<Book> books = new BookService().getAllBooks();
        books = PaginationUtil.paginateList(books, BOOKS_PER_PAGE, req);
        req.getSession().setAttribute("books", books);
        req.getSession().setAttribute("formatter", DateTimeFormatter.ofPattern("dd.MM.yyyy"));
        req.getRequestDispatcher("/admin_books.jsp").forward(req, resp);
    }
}
