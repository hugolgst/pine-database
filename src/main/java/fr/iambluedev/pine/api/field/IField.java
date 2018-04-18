package fr.iambluedev.pine.api.field;

/**
 * @author <a href="https://twitter.com/iambluedev">iambluedev</a>
 */
public interface IField {

	/**
     * Retrieves the parsed name with the simple quotes.
     *
     * @return The parsed name with the simple quotes.
     */
	String getParsedName();
	
	/**
     * Retrieves the parsed value with the simple quotes.
     *
     * @return The parsed value with the simple quotes.
     */
 	String getParsedValue();
	
 	/**
     * Retrieves the parsed name and values with
     * simple quotes and equal.
     *
     * @return The parsed name and values with simple quotes and equal.
     */
 	String getParsedNameAndValue();
}
