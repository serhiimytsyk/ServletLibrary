package smytsyk.final_project.library.dao.implementation;

import org.apache.log4j.Logger;
import smytsyk.final_project.library.dao.DBManager;
import smytsyk.final_project.library.dao.interfaces.UserDAO;
import smytsyk.final_project.library.entitiy.User;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

/**
 * Implementation of UserDAO using POSTGRES
 */
public class UserDAOImpl implements UserDAO {
    private static final String TABLE = "users";
    private static final String[] FIELDS =
            new String[] {"id", "login", "password", "first_name", "last_name", "email", "role_id"};
    private static final Logger log = Logger.getLogger(UserDAOImpl.class);

    @Override
    public User getEntityFromRow(ResultSet rs) {
        User user;
        try {
            user = User.builder().
                    id(rs.getInt(1)).
                    login(rs.getString(2)).
                    password(rs.getString(3)).
                    firstName(rs.getString(4)).
                    lastName(rs.getString(5)).
                    email(rs.getString(6)).
                    roleId(rs.getInt(7)).build();
        } catch (SQLException e) {
            log.error("Cannot get User from table row ", e);
            user = null;
        }
        return user;
    }

    @Override
    public void setRowByEntity(PreparedStatement preparedStatement, User entity) {
        try {
            preparedStatement.setInt(1, entity.getId());
            preparedStatement.setString(2, entity.getLogin());
            preparedStatement.setString(3, entity.getPassword());
            preparedStatement.setString(4, entity.getFirstName());
            preparedStatement.setString(5, entity.getLastName());
            preparedStatement.setString(6, entity.getEmail());
            preparedStatement.setInt(7, entity.getRoleId());
        } catch (SQLException e) {
            log.error("Cannot insert User to table row ", e);
        }
    }

    @Override
    public boolean changeRole(int id, int role) {
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

    @Override
    public User get(String login) {
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

    @Override
    public List<User> getNotAdminUsers() {
        List<User> users = new ArrayList<>();
        String query = "SELECT * FROM " + getTable() + " WHERE role_id < 3;";
        try (Connection connection = DBManager.getInstance().getConnection()) {
            try (Statement statement = connection.createStatement()) {
                try (ResultSet resultSet = statement.executeQuery(query)) {
                    while (resultSet.next()) {
                        User user = getEntityFromRow(resultSet);
                        users.add(user);
                    }
                }
            }
        } catch (SQLException e) {
            logError("Cannot get not-admin users from table ", e);
        }
        return users;
    }

    @Override
    public List<User> getAllReaders() {
        List<User> users = new ArrayList<>();
        String query = "SELECT * FROM " + getTable() + " WHERE role_id = 1;";
        try (Connection connection = DBManager.getInstance().getConnection()) {
            try (Statement statement = connection.createStatement()) {
                try (ResultSet resultSet = statement.executeQuery(query)) {
                    while (resultSet.next()) {
                        User user = getEntityFromRow(resultSet);
                        users.add(user);
                    }
                }
            }
        } catch (SQLException e) {
            logError("Cannot get readers from table ", e);
        }
        return users;
    }

    @Override
    public String getTable() {
        return TABLE;
    }

    @Override
    public String[] getFields() {
        return FIELDS;
    }

    @Override
    public void logError(String text, Throwable e) {
        log.error(text, e);
    }
}
