package smytsyk.final_project.library.service;

import smytsyk.final_project.library.dao.DAOFactory;
import smytsyk.final_project.library.dao.interfaces.UserDAO;
import smytsyk.final_project.library.entitiy.User;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.List;

/**
 * Class with all needed services with users
 */
public class UserService {
    private final UserDAO userDAO = DAOFactory.getUserDAO();

    /**
     * Gets user by login and password. If user doesn't exist, returns null
     */
    public User getUserByLoginAndPassword(String login, String password) {
        if (login == null || password == null) return null;
        User user = userDAO.get(login);
        if (user == null) return null;
        return password.equals(user.getPassword()) ? user : null;
    }

    /**
     * Gets user by id. If user doesn't exist, returns null
     */
    public User getUser(int id) {
        return userDAO.get(id);
    }

    /**
     * Tries to insert user with given parameters. Returns if it was successful
     */
    public boolean insertUser(String login, String password, String firstName, String lastName, String email) {
        User user = User.builder().
                login(login).
                password(password).
                firstName(firstName).
                lastName(lastName).
                email(email).
                roleId(1).build();
        return userDAO.insert(user);
    }

    /**
     * Tries to update user with given parameters. Returns if it was successful
     */
    public boolean updateUser(int id, String login, String password, String firstName, String lastName, String email, int role) {
        User user = User.builder().
                id(id).
                login(login).
                password(password).
                firstName(firstName).
                lastName(lastName).
                email(email).
                roleId(role).build();
        return userDAO.update(user);
    }

    /**
     * Returns list of all users
     */
    public List<User> getAllUsers() {
        return userDAO.getNotAdminUsers();
    }

    /**
     * Returns all readers
     */
    public List<User> getAllReaders() {
        return userDAO.getAllReaders();
    }

    /**
     * Changes role of person by id(from request) and redirects to another page
     */
    public void changeRole(HttpServletRequest req, HttpServletResponse resp, int role) throws IOException {
        int user_id = Integer.parseInt(req.getParameter("user_id"));
        if (userDAO.changeRole(user_id, role)) {
            resp.sendRedirect("/Controller?command=go_to_admin_users_page");
        } else {
            resp.sendRedirect("/Controller?command=go_to_error_page");
        }
    }
}
