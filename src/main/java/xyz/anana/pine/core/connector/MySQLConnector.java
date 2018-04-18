package xyz.hugol.pine.core.connector;

import fr.iambluedev.pine.api.connector.IConnector;

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
