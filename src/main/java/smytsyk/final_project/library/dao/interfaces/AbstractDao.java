package smytsyk.final_project.library.dao.interfaces;

import smytsyk.final_project.library.dao.DBManager;
import smytsyk.final_project.library.entitiy.Entity;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

/**
 * Generic interface for DAOs of all project entities
 * @param <T>
 */
public interface AbstractDao<T extends Entity> {
    /**
     * Inserts entity to DB(with autoincrement)
     */
    default boolean insert(T entity) {
        String query = "INSERT INTO " + getTable() + " VALUES(" + getUnknownValues() + ");";
        boolean success = false;
        Connection connection = null;
        try  {
            connection = DBManager.getInstance().getConnection();
            connection.setAutoCommit(false);
            int nexId = getNewId(connection);
            entity.setId(nexId);
            try (PreparedStatement preparedStatement = connection.prepareStatement(query)) {
                setRowByEntity(preparedStatement, entity);
                if (preparedStatement.executeUpdate() > 0) {
                    commit(connection);
                    success = true;
                } else {
                    rollback(connection);
                }
            }
        } catch (SQLException e) {
            rollback(connection);
            logError("Cannot insert entity to table ", e);
        } finally {
            close(connection);
            return success;
        }
    }

    /**
     * Gets entity by id
     */
    default T get(int id) {
        String query = "SELECT * FROM " + getTable() + " WHERE id = ?;";
        try (Connection connection = DBManager.getInstance().getConnection()) {
            try (PreparedStatement preparedStatement = connection.prepareStatement(query)) {
                preparedStatement.setInt(1, id);
                try (ResultSet resultSet = preparedStatement.executeQuery()) {
                    if (resultSet.next()) {
                        return getEntityFromRow(resultSet);
                    }
                }
            }
        } catch (SQLException e) {
            logError("Cannot get entity from table ", e);
        }
        return null;
    }

    /**
     * Gets all entities from DB
     */
    default List<T> getAll() {
        List<T> list = new ArrayList<>();
        String query = "SELECT * FROM " + getTable() + ";";
        try (Connection connection = DBManager.getInstance().getConnection()) {
            try (Statement statement = connection.createStatement()) {
                try (ResultSet resultSet = statement.executeQuery(query)) {
                    while (resultSet.next()) {
                        T entity = getEntityFromRow(resultSet);
                        list.add(entity);
                    }
                }
            }
        } catch (SQLException e) {
            logError("Cannot get entities from table ", e);
        }
        return list;
    }

    /**
     * Updates information about entity in the DB
     */
    default boolean update(T entity) {
        String query = "UPDATE " + getTable() + " SET " + getUnknownPairsFieldValue() + " WHERE id = ?;";
        try (Connection connection = DBManager.getInstance().getConnection()) {
            try (PreparedStatement preparedStatement = connection.prepareStatement(query)) {
                setRowByEntity(preparedStatement, entity);
                preparedStatement.setInt(getNumberOfFields() + 1, entity.getId());
                return preparedStatement.executeUpdate() > 0;
            }
        } catch (SQLException e) {
            logError("Cannot update entity in table ", e);
        }
        return false;
    }

    /**
     * Deletes entity by ID
     */
    default boolean delete(int id) {
        String query = "DELETE FROM " + getTable() + " WHERE id = ?;";
        try (Connection connection = DBManager.getInstance().getConnection()) {
            try (PreparedStatement preparedStatement = connection.prepareStatement(query)) {
                preparedStatement.setInt(1, id);
                return preparedStatement.executeUpdate() > 0;
            }
        } catch (SQLException e) {
            logError("Cannot delete entity from table ", e);
        }
        return false;
    }

    /**
     * Return new id to insert in table
     */
    private int getNewId(Connection connection) throws SQLException {
        int id = 0;
        String query = "SELECT MAX(id) FROM " + getTable() + ";";
        try (Statement statement = connection.createStatement()) {
            try (ResultSet resultSet = statement.executeQuery(query)) {
                if (resultSet.next()) {
                    id = Integer.max(id, resultSet.getInt(1));
                }
            }
        }
        return id + 1;
    }

    /**
     * Gets entity from current row of ResultSet
     */
    T getEntityFromRow(ResultSet rs);

    /**
     * Inserts entity fields of PreparedStatement
     */
    void setRowByEntity(PreparedStatement preparedStatement, T entity);

    /**
     * Returns name of table
     */
    String getTable();

    /**
     * Returns array of field names
     */
    String[] getFields();

    /**
     * Writes to log about error
     */
    void logError(String text, Throwable e);

    private int getNumberOfFields() {
        return getFields().length;
    }

    /**
     * It's a part of SQL query
     */
    private String getUnknownValues() {
        return "?" + ", ?".repeat(Math.max(0, getNumberOfFields() - 1));
    }

    /**
     * It's a part of SQL query
     */
    private String getUnknownPairsFieldValue() {
        StringBuilder sb = new StringBuilder("id = ?");
        for (int i = 1; i < getNumberOfFields(); i++) sb.append(", ").append(getFields()[i]).append(" = ?");
        return sb.toString();
    }

    private void commit(Connection connection) {
        try {
            connection.commit();
            connection.setAutoCommit(true);
        } catch (Exception e) {
            logError("Cannot commit ", e);
        }
    }

    private void rollback(Connection connection) {
        try {
            connection.rollback();
            connection.setAutoCommit(true);
        } catch (Exception e) {
            logError("Cannot rollback ", e);
        }
    }

    private void close(Connection connection) {
        try {
            connection.close();
        } catch (Exception e) {
            logError("Cannot close ", e);
        }
    }
}
