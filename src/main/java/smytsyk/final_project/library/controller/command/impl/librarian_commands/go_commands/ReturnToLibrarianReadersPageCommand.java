package smytsyk.final_project.library.controller.command.impl.librarian_commands.go_commands;

import smytsyk.final_project.library.controller.command.Command;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

/**
 * Command that redirects librarian to page with readers
 */
public class ReturnToLibrarianReadersPageCommand implements Command {
    @Override
    public void execute(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        req.getRequestDispatcher("/librarian_readers.jsp").forward(req, resp);
    }
}
