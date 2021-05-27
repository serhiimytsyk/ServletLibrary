package smytsyk.final_project.library.controller.command.impl;

import org.apache.log4j.Logger;
import smytsyk.final_project.library.controller.command.Command;
import smytsyk.final_project.library.service.I18N;
import smytsyk.final_project.library.service.UserService;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;

/**
 * Command with registration
 */
public class RegisterCommand implements Command {
    private static final Logger log = Logger.getLogger(RegisterCommand.class);

    @Override
    public void execute(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String login = req.getParameter("login");
        String password = req.getParameter("password");
        String firstName = req.getParameter("first_name");
        String lastName = req.getParameter("last_name");
        String email = req.getParameter("email");
        log.debug("Trying to register person with login = " + login + " first name = " +
                firstName + " last name = " + lastName + " email = " + email);
        if (!(UserUtil.isValidLogin(login) && UserUtil.isValidPassword(password) && UserUtil.isValidEmail(email) && UserUtil.isValidName(firstName) && UserUtil.isValidName(lastName))) {
            log.debug("Registration is not successful");
            HttpSession session = req.getSession();
            if (session != null) session.setAttribute("error", I18N.translate("error_reg", session));
            resp.sendRedirect("/Controller?command=go_to_register_page");
        } else {
            if (new UserService().insertUser(login, password, firstName, lastName, email)) {
                log.debug("Registration is successful, login = " + login);
                resp.sendRedirect("/Controller?command=go_to_main_page");
            } else {
                log.debug("Registration is not successful");
                HttpSession session = req.getSession();
                if (session != null) session.setAttribute("error", I18N.translate("error_reg", session));
                resp.sendRedirect("/Controller?command=go_to_register_page");
            }
        }
    }
}
