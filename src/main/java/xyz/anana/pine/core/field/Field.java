package xyz.hugol.pine.core.field;

import fr.iambluedev.pine.api.field.IField;
import lombok.Getter;

/**
 * @author <a href="mailto:contact@hugol.xyz">hugol</a>
 */
@Getter
public class Field implements IField {

	private String name;
	private Object value;
	private boolean like = false;

	public Field(String name, Object value) {
		this.name = name;
		this.value = value;
	}
	
	public Field(String name, Object value, boolean like) {
		this.name = name;
		this.value = value;
		this.like = like;
	}
	
    /**
     * Retrieves the parsed name with the simple quotes.
     *
     * @return The parsed name with the simple quotes.
     */
    public String getParsedName() {
        return " `" + name + "` ";
    }

    /**
     * Retrieves the parsed value with the simple quotes.
     *
     * @return The parsed value with the simple quotes.
     */
    public String getParsedValue() {
    	String formatted = String.valueOf(value);
		formatted = formatted.replaceAll("'", "\\\\'").replaceAll("\\P{Print}", "");
		if(like) {
			return "'%" + formatted + "%'";
		}
		return "'" + formatted + "'";
    }

    /**
     * Retrieves the parsed name and values with
     * simple quotes and equal.
     *
     * @return The parsed name and values with simple quotes and equal.
     */
    public String getParsedNameAndValue() {
        return getParsedName() + ((this.like) ? " LIKE " : "=") + getParsedValue();
    }

}
