package smytsyk.final_project.library.dao.interfaces;

import smytsyk.final_project.library.entitiy.User;

import java.util.List;

/**
 * DAO of Users
 */
public interface UserDAO extends AbstractDao<User> {

    /**
     * Gets user by login
     */
    User get(String login);
    boolean changeRole(int id, int role);
    List<User> getNotAdminUsers();
    List<User> getAllReaders();
}
