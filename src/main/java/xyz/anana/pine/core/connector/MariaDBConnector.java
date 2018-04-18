package xyz.hugol.pine.core.connector;

import fr.iambluedev.pine.api.connector.IConnector;

public class MariaDBConnector implements IConnector {

	@Override
	public String getName() {
		return "mariadb";
	}

	@Override
	public String getDriverPath() {
		return "org.mariadb.jdbc.Driver";
	}

}
