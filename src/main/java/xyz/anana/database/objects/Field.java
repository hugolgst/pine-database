package xyz.hugol.database.objects;

import lombok.AllArgsConstructor;
import lombok.Getter;

/**
 * @author <a href="mailto:contact@hugol.xyz">hugol</a>
 */
@AllArgsConstructor
@Getter
public class Field {

    private String name;
    private Object value;

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
        return " '" + value + "' ";
    }

    /**
     * Retrieves the parsed name and values with
     * simple quotes and equal.
     *
     * @return The parsed name and values with simple quotes and equal.
     */
    public String getParsedNameAndValue() {
        return getParsedName() + "=" + getParsedValue();
    }

}
