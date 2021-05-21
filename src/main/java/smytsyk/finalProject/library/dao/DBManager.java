package smytsyk.finalProject.library.dao;

import org.apache.log4j.Logger;

import javax.naming.Context;
import javax.naming.InitialContext;
import javax.naming.NamingException;
import javax.sql.DataSource;
import java.sql.Connection;
import java.sql.SQLException;

/**
 * Singleton class that works with connection pool and returns conntections to DB
 */
public class DBManager {
    private static final DBManager INSTANCE = new DBManager();
    private static final Logger log = Logger.getLogger(DBManager.class);

    private DBManager() {}

    public static DBManager getInstance() {
        return INSTANCE;
    }

    /**
     * Returns connection to DB if possible, else returns null
     */
    public Connection getConnection() {
        Connection connection = null;
        try {
            Context context = new InitialContext();
            DataSource ds = (DataSource) context.lookup("java:comp/env/jdbc/LibraryServletsDB");
            connection = ds.getConnection();
            log.info("Connection is successfully got");
        } catch (NamingException | SQLException e) {
            log.error("Cannot get connection from the connection pool", e);
        }
        return connection;
    }
}
