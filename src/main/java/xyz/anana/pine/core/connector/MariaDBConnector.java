package xyz.anana.pine.core.connector;

import fr.iambluedev.pine.api.connector.IConnector;

public class MariaDBConnector implements IConnector {

	/**
	 * Get the name of the connector
	 * 
	 * @return connector's name
	 */
	@Override
	public String getName() {
		return "mariadb";
	}

	/**
	 * Get the driver path of the connector
	 * 
	 * @return connector's driver path
	 */
	@Override
	public String getDriverPath() {
		return "org.mariadb.jdbc.Driver";
	}

}
