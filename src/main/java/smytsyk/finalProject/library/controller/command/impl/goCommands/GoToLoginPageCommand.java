package smytsyk.finalProject.library.controller.command.impl.goCommands;

import smytsyk.finalProject.library.controller.command.Command;
import smytsyk.finalProject.library.controller.command.impl.LoginCommand;
import smytsyk.finalProject.library.entitiy.User;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;

/**
 * Command that redirects user to login page
 */
public class GoToLoginPageCommand implements Command {
    @Override
    public void execute(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        HttpSession session = req.getSession();
        User user = (User) session.getAttribute("user");
        if (user == null) {
            req.getRequestDispatcher("/login.jsp").forward(req, resp);
        } else {
            LoginCommand.redirect(req, resp, user);
        }
    }
}
