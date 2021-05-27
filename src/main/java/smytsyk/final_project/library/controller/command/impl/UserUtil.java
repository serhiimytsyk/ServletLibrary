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
public class UserUtil {
    private static final Logger log = Logger.getLogger(UserUtil.class);

    /**
     * Method that updates information about user in the DB
     */
    public static void update(HttpServletRequest req, int role) throws ServletException, IOException {
        int id = Integer.parseInt(req.getParameter("user_id"));
        String login = req.getParameter("user_login");
        String password = req.getParameter("password");
        String firstName = req.getParameter("first_name");
        String lastName = req.getParameter("last_name");
        String email = req.getParameter("email");
        log.debug("Trying to update information about person with id = " + id + " first name = " +
                firstName + " last name = " + lastName + " email = " + email);
        if (!(isValidLogin(login) && isValidPassword(password) && isValidName(firstName) && isValidName(lastName) && isValidEmail(email))) {
            HttpSession session = req.getSession();
            if (session != null) session.setAttribute("error", I18N.translate("error_upd", session));
        } else {
            if (!new UserService().updateUser(id, login, password, firstName, lastName, email, role)) {
                log.debug("Update is not successful");
                HttpSession session = req.getSession();
                if (session != null) session.setAttribute("error", I18N.translate("error_upd", session));
            } else {
                log.debug("Update is successful");
                req.getSession().setAttribute("user", new UserService().getUser(id));
            }
        }
    }

    /**
     * Checks if login is valid
     */
    public static boolean isValidLogin(String name) {
        if (name == null || name.isEmpty() || name.length() > 30) return false;
        return name.matches("[-_.\\p{IsAlphabetic}0-9]+");
    }

    /**
     * Checks if name is valid
     */
    public static boolean isValidName(String name) {
        if (name == null || name.isEmpty() || name.length() > 30) return false;
        return name.matches("[-\\p{IsAlphabetic}\\s]+");
    }

    /**
     * Checks if password is valid
     */
    public static boolean isValidPassword(String name) {
        return name != null && !name.isEmpty() && name.length() <= 30;
    }

    /**
     * Checks if email is valid
     */
    public static boolean isValidEmail(String name) {
        if (name == null || name.isEmpty() || name.length() > 30) return false;
        String letters = "[-_.0-9\\p{IsAlphabetic}]+";
        String letters1 = "[-_0-9\\p{IsAlphabetic}]+";
        String regex = letters + "@" + letters1 + "\\." + letters1;
        return name.matches(regex);
    }
}
