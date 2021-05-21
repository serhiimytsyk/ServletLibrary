package smytsyk.final_project.library.service;

import smytsyk.final_project.library.dao.DAOFactory;
import smytsyk.final_project.library.entitiy.User;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.List;
import java.util.stream.Collectors;

/**
 * Class with all needed services with users
 */
public class UserService {
    /**
     * Gets user by login and password. If user doesn't exist, returns null
     */
    public User getUserByLoginAndPassword(String login, String password) {
        if (login == null || password == null) return null;
        User user = DAOFactory.getUserDAO().get(login);
        if (user == null) return null;
        return password.equals(user.getPassword()) ? user : null;
    }

    /**
     * Gets user by login. If user doesn't exist, returns null
     */
    public User getUserByLogin(String login) {
        if (login == null) return null;
        return DAOFactory.getUserDAO().get(login);
    }

    /**
     * Gets user by id. If user doesn't exist, returns null
     */
    public User getUser(int id) {
        return DAOFactory.getUserDAO().get(id);
    }

    /**
     * Tries to insert user with given parameters. Returns if it was successful
     */
    public boolean insertUser(String login, String password, String firstName, String lastName, String email) {
        if (getUserByLogin(login) == null) {
            User user = new User();
            if (!isValidLogin(login)) return false;
            user.setLogin(login);
            if (!isValidPassword(password)) return false;
            user.setPassword(password);
            if (!isValidName(firstName)) return false;
            user.setFirstName(firstName);
            if (!isValidName(lastName)) return false;
            user.setLastName(lastName);
            if (!isValidEmail(email)) return false;
            user.setEmail(email);
            user.setRoleId(1);
            return DAOFactory.getUserDAO().insert(user);
        }
        return false;
    }

    /**
     * Tries to update user with given parameters. Returns if it was successful
     */
    public boolean updateUser(int id, String password, String firstName, String lastName, String email) {
        if (isValidName(firstName) && isValidName(lastName) && isValidEmail(email) && isValidPassword(password)) {
            User user = DAOFactory.getUserDAO().get(id);
            if (user != null) {
                user.setPassword(password);
                user.setFirstName(firstName);
                user.setLastName(lastName);
                user.setEmail(email);
                DAOFactory.getUserDAO().update(user);
                return true;
            }
        }
        return false;
    }

    /**
     * Checks if login is valid
     */
    public boolean isValidLogin(String name) {
        if (name == null || name.isEmpty() || name.length() > 30) return false;
        return name.matches("[-_.\\p{IsAlphabetic}0-9]+");
    }

    /**
     * Checks if name is valid
     */
    public boolean isValidName(String name) {
        if (name == null || name.isEmpty() || name.length() > 30) return false;
        return name.matches("[-\\p{IsAlphabetic}\\s]+");
    }

    /**
     * Checks if password is valid
     */
    public boolean isValidPassword(String name) {
        return name != null && !name.isEmpty() && name.length() <= 30;
    }

    /**
     * Checks if email is valid
     */
    public boolean isValidEmail(String name) {
        if (name == null || name.isEmpty() || name.length() > 30) return false;
        String letters = "[-_.0-9\\p{IsAlphabetic}]+";
        String letters1 = "[-_0-9\\p{IsAlphabetic}]+";
        String regex = letters + "@" + letters1 + "\\." + letters1;
        return name.matches(regex);
    }

    /**
     * Returns list of all users
     */
    public List<User> getAllUsers() {
        return DAOFactory.getUserDAO().getAll().stream().filter(u -> u.getRoleId() != 3).collect(Collectors.toList());
    }

    /**
     * Returns all readers
     */
    public List<User> getAllReaders() {
        return getAllUsers().stream().filter(u -> u.getRoleId() == 1).collect(Collectors.toList());
    }

    /**
     * Changes role of person by id(from request) and redirects to another page
     */
    public void changeRole(HttpServletRequest req, HttpServletResponse resp, int role) throws IOException {
        int user_id = Integer.parseInt(req.getParameter("user_id"));
        User user = DAOFactory.getUserDAO().get(user_id);
        if (user != null) {
            user.setRoleId(role);
            DAOFactory.getUserDAO().update(user);
            resp.sendRedirect("/Controller?command=go_to_admin_users_page");
        } else {
            resp.sendRedirect("/Controller?command=go_to_error_page");
        }
    }
}
