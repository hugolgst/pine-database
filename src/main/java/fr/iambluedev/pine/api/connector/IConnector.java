package fr.iambluedev.pine.api.connector;

/**
 * @author <a href="https://twitter.com/iambluedev">iambluedev</a>
 */
public interface IConnector {

	/**
	 * Get the name of the connector
	 * 
	 * @return connector's name
	 */
	String getName();
	
	/**
	 * Get the driver path of the connector
	 * 
	 * @return connector's driver path
	 */
	String getDriverPath();
	
}
