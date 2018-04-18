package xyz.anana.database.utils.copy;

/**
 * @author <a href="mailto:contact@anana.xyz">Anana</a>
 */
public enum Method {

    SELECT, WHERE, FROM, INSERT_INTO, VALUES, UPDATE, DELETE_FROM, SET, AND;

    /**
     * Retrieves the name of the method.
     *
     * @return The name of the method.
     */
    public String getName() {
        return name().replace("_", " ");
    }

}
