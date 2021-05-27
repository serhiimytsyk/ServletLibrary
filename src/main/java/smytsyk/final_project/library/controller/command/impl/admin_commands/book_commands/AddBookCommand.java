package smytsyk.final_project.library.controller.command.impl.admin_commands.book_commands;

import smytsyk.final_project.library.controller.command.Command;
import smytsyk.final_project.library.controller.command.impl.BookUtil;
import smytsyk.final_project.library.service.BookService;
import smytsyk.final_project.library.service.I18N;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;


/**
 * Command that inserts new book to the library
 */
public class AddBookCommand implements Command {
    @Override
    public void execute(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String name = req.getParameter("name");
        String author = req.getParameter("author");
        String publisher = req.getParameter("publisher");
        String date = req.getParameter("publication_date");
        if (!(BookUtil.isValidName(name) && BookUtil.isValidAuthor(author) && BookUtil.isValidPublisher(publisher) && BookUtil.isValidDate(date))) {
            HttpSession session = req.getSession();
            if (session != null) session.setAttribute("error", I18N.translate("error_book_add", session));
        } else {
            if (!new BookService().insertBook(name, author, publisher, date)) {
                HttpSession session = req.getSession();
                if (session != null) session.setAttribute("error", I18N.translate("error_book_add", session));
            }
        }
        resp.sendRedirect("/Controller?command=go_to_admin_books_page");
    }
}
