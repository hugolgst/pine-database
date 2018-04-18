package xyz.anana.pine.core.connector;

import fr.iambluedev.pine.api.connector.IConnector;

/**
 * @author <a href="https://twitter.com/iambluedev">iambluedev</a>
 */
public class MySQLConnector implements IConnector {

	/**
	 * Get the name of the connector
	 * 
	 * @return connector's name
	 */
	@Override
	public String getName() {
		return "mysql";
	}

	/**
	 * Get the driver path of the connector
	 * 
	 * @return connector's driver path
	 */
	@Override
	public String getDriverPath() {
		return  "com.mysql.jdbc.Driver";
	}

}
