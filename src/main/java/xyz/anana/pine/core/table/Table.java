package xyz.anana.pine.core.table;

import java.util.List;

import fr.iambluedev.pine.api.field.IField;
import fr.iambluedev.pine.api.field.IFields;
import fr.iambluedev.pine.api.table.ITable;
import lombok.Getter;
import xyz.anana.pine.core.database.Database;
import xyz.anana.pine.core.field.Field;
import xyz.anana.pine.core.request.RequestHandler;
import xyz.anana.pine.core.utils.Method;

/**
 * @author <a href="mailto:contact@anana.xyz">Anana</a>
 */
@Getter
public class Table implements ITable {

    private RequestHandler requestHandler;
    private Database database;
    private String name;
    private String quotesName;

    public Table(Database database, String name) {
        this.database = database;
        this.name = name;
        this.quotesName = " `" + name + "` ";
        this.requestHandler = new RequestHandler(database);
    }

    public Table(String name) {
        this.database = Database.getInstance();
        this.name = name;
        this.quotesName = " `" + name + "` ";
        this.requestHandler = new RequestHandler(database);
	}
    
    /**
     * Retrieves a value find in the table from a field.
     *
     * @param field The field to get.
     * @return The find value.
     */
    @Override
	public Object select(String field) {
		return requestHandler.select(Method.SELECT.getName() + " `" + field + "` " + Method.FROM.getName() + 
				quotesName, field);
	}
    
    /**
     * Retrieves all entries from the table
     *
     * @return The list of values
     */
    @Override
	public List<IFields> selectAll() {
		return requestHandler.select(Method.SELECT.getName() + " * " + Method.FROM.getName() + 
				quotesName);
	}
    
    /**
     * Retrieves a value find in the table from a Field.
     *
     * @param field The field to get.
     * @param where The field to find the row.
     * @return The find value.
     */
    @Override
    public Object select(String field, IField where) {
        return requestHandler.select(Method.SELECT.getName() + " `" + field + "` " + Method.FROM.getName() +
                quotesName + Method.WHERE.getName() + where.getParsedNameAndValue(), field);
    }

    /**
     * Retrieves a value find in the table from
     * a Field with a list of where values.
     *
     * @param field The field to get.
     * @param where The list of fields to find the row.
     * @return The find value.
     */
    @Override
    public Object select(String field, List<IField> where) {
        return requestHandler.select(Method.SELECT.getName() + " `" + field + "` " + Method.FROM.getName() +
                quotesName + Method.WHERE.getName() + requestHandler.getListOfWhereValues(where), field);
    }

    /**
     * Retrieves a set of values find in the table from
     * a list of fields with a where field.
     *
     * @param field The list of fields to get.
     * @param where The fields to find the row.
     * @return The find values.
     */
    @Override
	public IFields select(List<String> fields, IField where) {
		return requestHandler.select(Method.SELECT.getName() + " * " + Method.FROM.getName() + 
				quotesName + Method.WHERE.getName() + where.getParsedNameAndValue(), fields);
	}
	
    /**
     * Retrieves a set of values find in the table from
     * a list of fields with a list of where fields.
     *
     * @param field The list of fields to get.
     * @param where The list of fields to find the row.
     * @return The find values.
     */
	@Override
	public IFields select(List<String> fields, List<IField> where) {
		return requestHandler.select(Method.SELECT.getName() + " * " + Method.FROM.getName() +
				quotesName + Method.WHERE.getName() + " " + requestHandler.getListOfWhereValues(where), fields);
	}
    
    /**
     * Retrieves a list of values find in the table.
     *
     * @param field The field to get.
     * @return The list of values.
     */
    @Override
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
    @Override
    public List<Object> selectList(String field, IField where) {
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
    @Override
    public List<Object> selectList(String field, List<IField> where) {
        return requestHandler.selectList(Method.SELECT.getName() + " `" + field + "` " + Method.FROM.getName() +
                quotesName + Method.WHERE.getName() + requestHandler.getListOfWhereValues(where), field);
    }
    
    /**
     * Retrieves all values find in the table with a
     * list of specific values in row.
     *
     * @param where The list of fields to get the row.
     * @return The list of values find with specific values.
     */
    @Override
	public List<IFields> selectList(List<IField> where) {
		return requestHandler.selectList(Method.SELECT.getName() + " * " + Method.FROM.getName() + 
				quotesName + Method.WHERE.getName() + requestHandler.getListOfWhereValues(where));
	}
    
    /**
     * Insert a list of field into the table.
     *
     * @param fields The list of fields.
     * @return boolean
     */
    @Override
    public boolean insert(List<IField> fields) {
        //Setup insert fields
        StringBuilder setter = new StringBuilder();
        StringBuilder field = new StringBuilder();
        for (IField entry : fields) {
        	field.append(entry.getParsedName() + ", ");
            setter.append(entry.getParsedValue() + ", ");
        }

        //Update query
        return requestHandler.executeUpdate(Method.INSERT_INTO.getName() + quotesName + "(" +
                field.substring(0, field.length() - 2) + ") " + Method.VALUES.getName() +
                "(" + setter.substring(0, setter.length() - 2) + ")");
    }

    /**
     * Delete a row find by a field.
     *
     * @param where The field to find the row.
     * @return boolean
     */
    @Override
    public boolean delete(IField where) {
        return requestHandler.executeUpdate(Method.DELETE_FROM.getName() + quotesName + Method.WHERE.getName() + where.getParsedNameAndValue());
    }

    /**
     * Update a row in the table with the field to set
     * and the field to get the row.
     *
     * @param toSet The field to set.
     * @param where The field to get the row.
     * @return boolean
     */
    @Override
    public boolean update(IField toSet, IField where) {
        return requestHandler.executeUpdate(Method.UPDATE.getName() + quotesName + Method.SET.getName() +
                toSet.getParsedNameAndValue() + Method.WHERE.getName() + where.getParsedNameAndValue());
    }

    /**
     * Update a row in the table with a list of fields to set
     * and the field to get the row.
     *
     * @param toSet The list of fields to set.
     * @param where The field to get the row.
     * @return boolean
     */
    @Override
	public boolean update(List<IField> toSet, IField where) {
		//Setup updated fields
        StringBuilder setters = new StringBuilder();
        for (IField entry : toSet)
            setters.append(entry.getParsedNameAndValue() + ", ");

        //Update query
        return requestHandler.executeUpdate(Method.UPDATE.getName() + quotesName + Method.SET.getName() +
        		setters.substring(0, setters.length() - 2) + Method.WHERE.getName() + where.getParsedNameAndValue());
	}
    
    /**
     * Search in the table if the entry exist
     * 
     * @param field The Field to get the row
     * @return boolean
     */
    @Override
	public boolean exist(IField field) {
		return requestHandler.exist(Method.SELECT.getName() + " " + field.getParsedName() + " " + Method.FROM.getName() +
				quotesName + Method.WHERE.getName() + field.getParsedNameAndValue());
	}
	
    /**
     * Search in the table if the entry exist
     * 
     * @param field Specify a column
     * @param where The list of fields to get the row.
     * @return boolean
     */
	@Override
	public boolean exist(String field, List<IField> where) {
		return requestHandler.exist(Method.SELECT.getName() + " " + field + " " + Method.FROM.getName() +
				quotesName + Method.WHERE.getName() + requestHandler.getListOfWhereValues(where));
	}
    
	/**
	 * Dump function, print all entries of the table
	 */
    @Override
	public void dump() {
		int line = 1;
		for(IFields fields : this.selectAll()){
			System.out.print("Entry n" + line + ": ");
			for(IField field : fields.getFields()) {
				Field formattedField = (Field) field;
				System.out.print(formattedField.getName() + "=" + formattedField.getParsedValue() + ", ");
			}
			System.out.println("");
			line++;
		}
	}
    
}
