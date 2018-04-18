package fr.iambluedev.pine.api.database;

import fr.iambluedev.pine.api.table.ITable;

/**
 * @author <a href="https://twitter.com/iambluedev">iambluedev</a>
 */

public interface IDatabase {

	/**
     * Retrieves a table into this database with his name.
     *
     * @param name The table name.
     * @return The table object.
     */
	ITable getTable(String name);
	
	/**
	 * Drop Table
	 * 
	 * @param name
	 */
	boolean dropTable(String name);
	
	/**
	 * Truncate Table
	 * 
	 * @param name
	 */
	boolean truncateTable(String name);
	
	 /**
     * Etablish a connection to the MySQL Database.
     */
	boolean connect();
	
	/**
     * Close the etablished connection.
     */
	boolean close();
	
	 /**
     * Get Database Status
     */
	boolean status();
}
