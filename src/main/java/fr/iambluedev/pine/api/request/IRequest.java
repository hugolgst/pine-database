package fr.iambluedev.pine.api.request;

import java.util.List;

import fr.iambluedev.pine.api.field.IField;
import fr.iambluedev.pine.api.field.IFields;

/**
 * @author <a href="https://twitter.com/iambluedev">iambluedev</a>
 */

public interface IRequest {

	/**
	 * Execute an update with a MySQL query.
	 *
	 * @param query The MySQL query.
	 */
	boolean executeUpdate(String query);

	/**
	 * Select an object in the database with a MySQL query.
	 *
	 * @param query The MySQL query.
	 * @param get The object to get.
	 * @return The find object.
	 */
	Object select(String query, String get);

	/**
     * Select an object in the database with a MySQL query.
     *
     * @param query The MySQL query.
     * @param get   The list of Fields
     * @return The find Fields Object.
     */
	IFields select(String query, List<String> fields);
	
	/**
     * Select an object in the database with a MySQL query.
     *
     * @param query The MySQL query.
     * @param get   The list of Fields
     * @return A list of the founded Fields.
     */
	List<IFields> select(String query);

	/**
     * Read a list of values with MySQL query.
     *
     * @param query The MySQL written query.
     * @param get   The field to read.
     * @return Returns found List.
     */
	List<Object> selectList(String query, String get);

	/**
     * Read a list of values with MySQL query.
     *
     * @param query The MySQL written query.
     * @return Returns found List.
     */
	List<IFields> selectList(String query);
	
	/**
     * Retrieves the string parsed from the list of where values.
     *
     * @param where The list of where values.
     * @return The parsed string.
     */
	String getListOfWhereValues(List<IField> where);

	/**
	 * Check if an item exist or not in the database
	 * 
	 * @param query
     * @return exist or not
	 */
	boolean exist(String query);
}
