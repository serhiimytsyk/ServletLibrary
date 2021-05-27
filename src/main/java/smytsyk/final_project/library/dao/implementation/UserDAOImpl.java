package smytsyk.final_project.library.dao.implementation;

import org.apache.log4j.Logger;
import smytsyk.final_project.library.dao.interfaces.UserDAO;
import smytsyk.final_project.library.entitiy.User;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

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
