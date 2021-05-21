package smytsyk.finalProject.library.controller.command.impl.adminCommands.bookCommands;

import smytsyk.finalProject.library.controller.command.Command;
import smytsyk.finalProject.library.service.BookService;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

/**
 * Command that deletes book from the library
 */
public class DeleteBookCommand implements Command {
    @Override
    public void execute(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        int book_id = Integer.parseInt(req.getParameter("book_id"));
        new BookService().deleteBook(book_id);
        resp.sendRedirect("/Controller?command=go_to_admin_books_page");
    }
}
