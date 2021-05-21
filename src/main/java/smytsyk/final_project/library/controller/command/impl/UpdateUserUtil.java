package smytsyk.final_project.library.controller.command.impl;

import org.apache.log4j.Logger;
import smytsyk.final_project.library.service.I18N;
import smytsyk.final_project.library.service.UserService;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import java.io.IOException;

/**
 * Class with method that helps in updating user information
 */
public class UpdateUserUtil {
    private static final Logger log = Logger.getLogger(UpdateUserUtil.class);

    /**
     * Method that updates information about user in the DB
     */
    public static void update(HttpServletRequest req) throws ServletException, IOException {
        int id = Integer.parseInt(req.getParameter("user_id"));
        String password = req.getParameter("password");
        String firstName = req.getParameter("first_name");
        String lastName = req.getParameter("last_name");
        String email = req.getParameter("email");
        log.debug("Trying to update information about person with id = " + id + " first name = " +
                firstName + " last name = " + lastName + " email = " + email);
        if (!new UserService().updateUser(id, password, firstName, lastName, email)) {
            log.debug("Update is successful");
            HttpSession session = req.getSession();
            if (session != null) session.setAttribute("error", I18N.translate("error_upd", session));
        } else {
            log.debug("Update is not successful");
            req.getSession().setAttribute("user", new UserService().getUser(id));
        }
    }
}
