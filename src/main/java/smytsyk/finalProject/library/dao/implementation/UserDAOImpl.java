package smytsyk.finalProject.library.dao.implementation;

import org.apache.log4j.Logger;
import smytsyk.finalProject.library.dao.interfaces.UserDAO;
import smytsyk.finalProject.library.entitiy.User;

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
        User user = new User();
        try {
            user.setId(rs.getInt(1));
            user.setLogin(rs.getString(2));
            user.setPassword(rs.getString(3));
            user.setFirstName(rs.getString(4));
            user.setLastName(rs.getString(5));
            user.setEmail(rs.getString(6));
            user.setRoleId(rs.getInt(7));
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
