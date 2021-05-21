package smytsyk.finalProject.library.controller.command.impl.goCommands;

import smytsyk.finalProject.library.controller.command.Command;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

/**
 * Command that redirects user to main page
 */
public class GoToMainPageCommand implements Command {
    @Override
    public void execute(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        req.getSession().removeAttribute("user");
        req.getRequestDispatcher("/index.jsp").forward(req, resp);
    }
}
