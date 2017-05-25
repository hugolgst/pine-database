package xyz.anana.database.objects;

import lombok.Getter;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 * @author <a href="mailto:contact@anana.xyz">Anana</a>
 */
public class Database {

    private Logger logger = Logger.getLogger(getClass().getName());

    @Getter
    private Connection connection;
    private String host;
    private String user;
    private String password;

    @Getter
    private boolean connected;

    public Database(final String host, final String database, final String user, final String password) {
        this.host = "jdbc:mysql://" + host + ":3306/" + database;
        this.user = user;
        this.password = password;
    }

    /**
     * Retrieves a table into this database with his name.
     *
     * @param name The table name.
     * @return The table object.
     */
    public Table getTable(String name) {
        return new Table(this, name);
    }

    /**
     * Etablish a connection to the MySQL Database.
     */
    public void connect() {
        if (connected)
            return;

        try {
            Class.forName("com.mysql.jdbc.Driver");

            this.connection = DriverManager.getConnection(host, user, password);
            this.connected = true;
        } catch (SQLException e) {
            logger.log(Level.SEVERE, "Can't connect to the database", e);
        } catch (ClassNotFoundException e) {
            logger.log(Level.SEVERE, "Can't find JDBC Driver", e);
        }
    }

    /**
     * Close the etablished connection.
     */
    public void close() {
        if (!connected)
            return;

        try {
            connection.close();
        } catch (SQLException e) {
            logger.log(Level.SEVERE, e.getMessage(), e);
        }
    }

}
