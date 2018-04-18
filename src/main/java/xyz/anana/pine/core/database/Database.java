package xyz.hugol.pine.core.database;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.logging.Level;
import java.util.logging.Logger;

import fr.iambluedev.pine.api.connector.IConnector;
import fr.iambluedev.pine.api.database.IDatabase;
import fr.iambluedev.pine.api.table.ITable;
import lombok.Getter;
import xyz.hugol.pine.core.connector.MySQLConnector;
import xyz.hugol.pine.core.request.RequestHandler;
import xyz.hugol.pine.core.table.Table;

/**
 * @author <a href="mailto:contact@hugol.xyz">hugol</a>
 */
@Getter
public class Database implements IDatabase {

    private Logger logger = Logger.getLogger(getClass().getName());

    @Getter
	private static Database instance;
    
    private Connection connection;
	private RequestHandler requestHandler;
	private String host;
    private String user;
	private String password;
	private String database;
	private Integer port;
	private boolean connected;
	private IConnector connector;
    
    private Database(String host, String database, String user, String password) {
		this.database = database;
        this.user = user;
        this.password = password;
        this.port = 3306;
        this.requestHandler = new RequestHandler(this);
        this.connector = new MySQLConnector();
        this.host = this.formatHost(host);
        instance = this;
	}
	
	public Database(String host, String database, String user, String password, Integer port) {
        this.database = database;
        this.user = user;
        this.password = password;
        this.port = port;
        this.requestHandler = new RequestHandler(this);
        this.connector = new MySQLConnector();
        this.host = this.formatHost(host);
        instance = this;
	}
	
	public Database(String host, String database, String user, String password, IConnector connector) {
		this.database = database;
        this.user = user;
        this.password = password;
        this.port = 3306;
        this.requestHandler = new RequestHandler(this);
        this.connector = connector;
        this.host = this.formatHost(host);
        instance = this;
	}
	
	public Database(String host, String database, String user, String password, Integer port, IConnector connector) {
        this.database = database;
        this.user = user;
        this.password = password;
        this.port = port;
        this.requestHandler = new RequestHandler(this);
        this.connector = connector;
        this.host = this.formatHost(host);
        instance = this;
	}

    /**
     * Retrieves a table into this database with his name.
     *
     * @param name The table name.
     * @return The table object.
     */
	@Override
    public ITable getTable(String name) {
        return new Table(this, name);
    }

	/**
	 * Drop Table
	 * 
	 * @param name
	 */
	@Override
	public boolean dropTable(String name) {
		return this.requestHandler.executeUpdate("DROP TABLE `" + name + "`");
	}

	/**
	 * Truncate Table
	 * 
	 * @param name
	 */
	@Override
	public boolean truncateTable(String name) {
		return this.requestHandler.executeUpdate("TRUNCATE TABLE `" + name + "`");
	}
	
    /**
     * Etablish a connection to the MySQL Database.
     */
	@Override
    public boolean connect() {
        if (connected)
            return false;

        try {
        	Class.forName(this.connector.getDriverPath());

            this.connection = DriverManager.getConnection(host, user, password);
            this.connected = true;
            return true;
        } catch (SQLException e) {
            logger.log(Level.SEVERE, "Can't connect to the database", e);
        } catch (ClassNotFoundException e) {
            logger.log(Level.SEVERE, "Can't find JDBC Driver", e);
        }
        return false;
    }

    /**
     * Close the etablished connection.
     */
    @Override
    public boolean close() {
        if (!connected)
            return false;

        try {
            connection.close();
            this.connected = false;
            return true;
        } catch (SQLException e) {
            logger.log(Level.SEVERE, e.getMessage(), e);
        }
        return false;
    }
    
    
    /**
     * Get Database Status
     */
    @Override
	public boolean status() {
		return this.connected;
	}
	
    /**
     * Tool in order to get the right formatted database url, following the specified connector
     * 
     * @param host
     * @return Formatted Database Url
     */
	private String formatHost(String host){
		return "jdbc:" + this.connector.getName() + "://" + host + ":" + this.port + "/" + this.database;
	}

}
