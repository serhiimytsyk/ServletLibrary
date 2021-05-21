package smytsyk.finalProject.library.controller.command.impl.adminCommands.GoCommands;

import smytsyk.finalProject.library.controller.command.Command;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

/**
 * Command that redirects admin to his main page
 */
public class ReturnToAdminPageCommand implements Command {
    @Override
    public void execute(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        req.getRequestDispatcher("/admin.jsp").forward(req, resp);
    }
}
