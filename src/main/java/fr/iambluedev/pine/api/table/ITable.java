package fr.iambluedev.pine.api.table;

import java.util.List;

import fr.iambluedev.pine.api.field.IField;
import fr.iambluedev.pine.api.field.IFields;

/**
 * @author <a href="https://twitter.com/iambluedev">iambluedev</a>
 */

public interface ITable {

	/**
     * Retrieves a value find in the table from a field.
     *
     * @param field The field to get.
     * @return The find value.
     */
	Object select(String field);
	
	 /**
     * Retrieves all entries from the table
     *
     * @return The list of values
     */
	List<IFields> selectAll();
	
	/**
     * Retrieves a value find in the table from a Field.
     *
     * @param field The field to get.
     * @param where The field to find the row.
     * @return The find value.
     */
	Object select(String field, IField where);
	
	/**
     * Retrieves a value find in the table from
     * a Field with a list of where values.
     *
     * @param field The field to get.
     * @param where The list of fields to find the row.
     * @return The find value.
     */
	Object select(String field, List<IField> where);
	
	/**
     * Retrieves a set of values find in the table from
     * a list of fields with a where field.
     *
     * @param field The list of fields to get.
     * @param where The fields to find the row.
     * @return The find values.
     */
	IFields select(List<String> fields, IField where);
	
	/**
     * Retrieves a set of values find in the table from
     * a list of fields with a list of where fields.
     *
     * @param field The list of fields to get.
     * @param where The list of fields to find the row.
     * @return The find values.
     */
	IFields select(List<String> fields, List<IField> where);
	
	/**
     * Retrieves a list of values find in the table.
     *
     * @param field The field to get.
     * @return The list of values.
     */
	List<Object> selectList(String field);
	
	/**
     * Retrieves a list of values find in the table with a
     * specific value in row.
     *
     * @param field The field to get.
     * @param where The field to get the row.
     * @return The list of values find with specific value.
     */
	List<Object> selectList(String field, IField where);
	
	/**
     * Retrieves a list of values find in the table with a
     * list of specific values in row.
     *
     * @param field The field to get.
     * @param where The list of fields to get the row.
     * @return The list of values find with specific value.
     */
	List<Object> selectList(String field, List<IField> where);
	
	/**
     * Retrieves all values find in the table with a
     * list of specific values in row.
     *
     * @param where The list of fields to get the row.
     * @return The list of values find with specific values.
     */
	List<IFields> selectList(List<IField> where);
	
	/**
     * Insert a list of field into the table.
     *
     * @param fields The list of fields.
     * @return boolean
     */
	boolean insert(List<IField> fields);
	
	/**
     * Delete a row find by a field.
     *
     * @param where The field to find the row.
     * @return boolean
     */
	boolean delete(IField where);
	
	/**
     * Update a row in the table with the field to set
     * and the field to get the row.
     *
     * @param toSet The field to set.
     * @param where The field to get the row.
     * @return boolean
     */
	boolean update(IField toSet, IField where);
	
	/**
     * Update a row in the table with a list of fields to set
     * and the field to get the row.
     *
     * @param toSet The list of fields to set.
     * @param where The field to get the row.
     * @return boolean
     */
	boolean update(List<IField> toSet, IField where);
	
	/**
     * Search in the table if the entry exist
     * 
     * @param field The Field to get the row
     * @return boolean
     */
	boolean exist(IField field);

	/**
     * Search in the table if the entry exist
     * 
     * @param field Specify a column
     * @param where The list of fields to get the row.
     * @return boolean
     */
	boolean exist(String field, List<IField> where);
	
	/**
	 * Dump function, print all entries of the table
	 */
	void dump();
}
