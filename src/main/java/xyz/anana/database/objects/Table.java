package xyz.hugol.database.objects;

import lombok.Getter;
import xyz.hugol.database.utils.Method;

import java.util.List;
import java.util.Map;

/**
 * @author <a href="mailto:contact@hugol.xyz">hugol</a>
 */
public class Table {

    private RequestHandler requestHandler;
    @Getter
    private Database database;
    @Getter
    private String name;
    private String quotesName;

    protected Table(Database database, String name) {
        this.database = database;
        this.name = name;
        this.quotesName = " `" + name + "` ";
        this.requestHandler = new RequestHandler(database);
    }

    /**
     * Retrieves a value find in the table from a {@link Field}.
     *
     * @param field The field to get.
     * @param where The field to find the row.
     * @return The find value.
     */
    public Object select(String field, Field where) {
        return requestHandler.select(Method.SELECT.getName() + " `" + field + "` " + Method.FROM.getName() +
                quotesName + Method.WHERE.getName() + where.getParsedNameAndValue(), field);
    }

    /**
     * Retrieves a value find in the table from
     * a {@link Field} with a list of where values.
     *
     * @param field The field to get.
     * @param where The list of fields to find the row.
     * @return The find value.
     */
    public Object select(String field, List<Field> where) {
        return requestHandler.select(Method.SELECT.getName() + " `" + field + "` " + Method.FROM.getName() +
                quotesName + Method.WHERE.getName() + requestHandler.getListOfWhereValues(where), field);
    }

    /**
     * Retrieves a list of values find in the table.
     *
     * @param field The field to get.
     * @return The list of values.
     */
    public List<Object> selectList(String field) {
        return requestHandler.selectList(Method.SELECT.getName() + " `" + field + "` " + Method.FROM.getName() + quotesName, field);
    }

    /**
     * Retrieves a list of values find in the table with a
     * specific value in row.
     *
     * @param field The field to get.
     * @param where The field to get the row.
     * @return The list of values find with specific value.
     */
    public List<Object> selectList(String field, Field where) {
        return requestHandler.selectList(Method.SELECT.getName() + " `" + field + "` " + Method.FROM.getName() +
                quotesName + Method.WHERE.getName() + where.getParsedNameAndValue(), field);
    }

    /**
     * Retrieves a list of values find in the table with a
     * list of specific values in row.
     *
     * @param field The field to get.
     * @param where The list of fields to get the row.
     * @return The list of values find with specific value.
     */
    public List<Object> selectList(String field, List<Field> where) {
        return requestHandler.selectList(Method.SELECT.getName() + " `" + field + "` " + Method.FROM.getName() +
                quotesName + Method.WHERE.getName() + requestHandler.getListOfWhereValues(where), field);
    }

    /**
     * Insert a list of field into the table.
     *
     * @param fields The list of fields.
     */
    public void insert(Map<String, Object> fields) {
        //Setup insert fields
        StringBuilder setter = new StringBuilder();
        StringBuilder field = new StringBuilder();
        for (Map.Entry<String, Object> entry : fields.entrySet()) {
            field.append("`" + entry.getKey() + "`, ");
            setter.append("'" + entry.getValue() + "', ");
        }

        //Update query
        requestHandler.executeUpdate(Method.INSERT_INTO.getName() + quotesName + "(" +
                field.substring(0, field.length() - 2) + ") " + Method.VALUES.getName() +
                "(" + setter.substring(0, setter.length() - 2) + ")");
    }

    /**
     * Delete a row find by a field.
     *
     * @param where The field to find the row.
     */
    public void delete(Field where) {
        requestHandler.executeUpdate(Method.DELETE_FROM.getName() + quotesName + Method.WHERE.getName() + where.getParsedNameAndValue());
    }

    /**
     * Update a row in the table with the field to set
     * and the field to get the row.
     *
     * @param toSet The field to set.
     * @param where The field to get the row.
     */
    public void update(Field toSet, Field where) {
        requestHandler.executeUpdate(Method.UPDATE.getName() + quotesName + Method.SET.getName() +
                toSet.getParsedNameAndValue() + Method.WHERE.getName() + where.getParsedNameAndValue());
    }

    /**
     * Update a row in the table with a list of fields to set
     * and the field to get the row.
     *
     * @param toSet The list of fields to set.
     * @param where The field to get the row.
     */
    public void update(Map<String, Object> toSet, Field where) {
        //Setup updated fields
        StringBuilder setters = new StringBuilder();
        for (Map.Entry<String, Object> entry : toSet.entrySet())
            setters.append("`" + entry.getKey() + "` = '" + entry.getValue() + "', ");

        //Update query
        requestHandler.executeUpdate(Method.UPDATE.getName() + quotesName + Method.SET.getName() +
                setters.substring(0, setters.length() - 2) + Method.WHERE.getName() + where.getParsedNameAndValue());
    }

}
