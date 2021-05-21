package smytsyk.finalProject.library.controller.command.impl;

import org.apache.log4j.Logger;
import smytsyk.finalProject.library.controller.command.Command;
import smytsyk.finalProject.library.entitiy.User;
import smytsyk.finalProject.library.service.I18N;
import smytsyk.finalProject.library.service.UserService;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;

/**
 * Command with user authentication
 */
public class LoginCommand implements Command {
    private static final Logger log = Logger.getLogger(LoginCommand.class);

    @Override
    public void execute(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String login = req.getParameter("login");
        String password = req.getParameter("password");
        log.debug("Person with login = " + login + " is trying to log in");
        User user = new UserService().getUserByLoginAndPassword(login, password);
        HttpSession session = req.getSession();
        if (user == null) {
            log.debug("Log in is not successful, login = " + login);
            session.setAttribute("error", I18N.translate("error_login", session));
            resp.sendRedirect("/Controller?command=go_to_login_page");
        } else {
            log.debug("Log in is successful, login = " + login);
            session.setAttribute("user", user);
            redirect(req, resp, user);
        }
    }

    /**
     * Method that redirect user to his main page
     */
    public static void redirect(HttpServletRequest req, HttpServletResponse resp, User user) throws ServletException, IOException {
        switch (user.getRoleId()) {
            case 0: req.getRequestDispatcher("/banned.jsp").forward(req, resp); break;
            case 1: req.getRequestDispatcher("/reader.jsp").forward(req, resp); break;
            case 2: req.getRequestDispatcher("/librarian.jsp").forward(req, resp); break;
            case 3: req.getRequestDispatcher("/admin.jsp").forward(req, resp); break;
            default: req.getRequestDispatcher("/error_page.jsp").forward(req, resp); break;
        }
    }
}
