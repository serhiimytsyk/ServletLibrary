package smytsyk.final_project.library.controller.command.impl.commands_without_control;

import org.apache.log4j.Logger;
import smytsyk.final_project.library.controller.command.Command;
import smytsyk.final_project.library.entitiy.User;
import smytsyk.final_project.library.service.I18N;
import smytsyk.final_project.library.service.UserService;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.util.Set;

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
            Set<Integer> logged_user_ids = (Set<Integer>) req.getServletContext().getAttribute("logged_user_ids");
            if (!logged_user_ids.contains(user.getId())) {
                log.debug("Log in is successful, login = " + login);
                logged_user_ids.add(user.getId());
                req.getServletContext().setAttribute("logged_user_ids", logged_user_ids);
                session.setAttribute("user", user);
                redirect(req, resp, user);
            } else {
                log.debug("User is already logged id, login = " + login);
                session.setAttribute("error", I18N.translate("already", session));
                resp.sendRedirect("/Controller?command=go_to_login_page");
            }
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
