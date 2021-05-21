package smytsyk.finalProject.library.controller.command.impl.adminCommands.bookCommands;

import smytsyk.finalProject.library.controller.command.Command;
import smytsyk.finalProject.library.service.BookService;
import smytsyk.finalProject.library.service.I18N;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;

/**
 * Command that updates information about book
 */
public class UpdateBookCommand implements Command {
    @Override
    public void execute(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        int id = Integer.parseInt(req.getParameter("book_id"));
        String name = req.getParameter("name");
        String author = req.getParameter("author");
        String publisher = req.getParameter("publisher");
        String date = req.getParameter("publication_date");
        if (!new BookService().updateBook(id, name, author, publisher, date)) {
            HttpSession session = req.getSession();
            if (session != null) session.setAttribute("error", I18N.translate("error_book_add", session));
        }
        resp.sendRedirect("/Controller?command=go_to_admin_books_page");
    }
}
