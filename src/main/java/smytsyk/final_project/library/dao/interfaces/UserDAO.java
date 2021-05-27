package smytsyk.final_project.library.dao.interfaces;

import smytsyk.final_project.library.dao.DBManager;
import smytsyk.final_project.library.entitiy.User;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

/**
 * DAO of Users
 */
public interface UserDAO extends AbstractDao<User> {

    /**
     * Gets user by login
     */
    default User get(String login) {
        User user = null;
        String query = "SELECT * FROM " + getTable() + " WHERE login = ?;";
        try (Connection connection = DBManager.getInstance().getConnection()) {
            try (PreparedStatement preparedStatement = connection.prepareStatement(query)) {
                preparedStatement.setString(1, login);
                try (ResultSet resultSet = preparedStatement.executeQuery()) {
                    if (resultSet.next()) {
                        user = getEntityFromRow(resultSet);
                    }
                }
            }
        } catch (SQLException e) {
            logError("Cannot get User from table ", e);
            user = null;
        }
        return user;
    }

    default boolean changeRole(int id, int role) {
        String query = "UPDATE " + getTable() + " SET role_id = ? WHERE id = ?;";
        try (Connection connection = DBManager.getInstance().getConnection()) {
            try (PreparedStatement preparedStatement = connection.prepareStatement(query)) {
                preparedStatement.setInt(1, role);
                preparedStatement.setInt(2, id);
                return preparedStatement.executeUpdate() > 0;
            }
        } catch (SQLException e) {
            logError("Cannot change user's role ", e);
        }
        return false;
    }
}
