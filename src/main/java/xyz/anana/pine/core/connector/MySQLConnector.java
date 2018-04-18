package xyz.anana.pine.core.connector;

import fr.iambluedev.pine.api.connector.IConnector;

public class MySQLConnector implements IConnector {

	@Override
	public String getName() {
		return "mysql";
	}

	@Override
	public String getDriverPath() {
		return  "com.mysql.jdbc.Driver";
	}

}
