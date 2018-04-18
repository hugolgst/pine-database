package xyz.hugol.pine.core.request;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.ResultSetMetaData;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;

import fr.iambluedev.pine.api.field.IField;
import fr.iambluedev.pine.api.field.IFields;
import fr.iambluedev.pine.api.request.IRequest;
import lombok.Getter;
import lombok.NonNull;
import lombok.RequiredArgsConstructor;
import xyz.hugol.pine.core.database.Database;
import xyz.hugol.pine.core.field.Field;
import xyz.hugol.pine.core.field.Fields;
import xyz.hugol.pine.core.utils.Method;

/**
 * @author <a href="mailto:contact@hugol.xyz">hugol</a>
 */
/**
 * @author Guillaume
 *
 */
@RequiredArgsConstructor
public class RequestHandler implements IRequest {

    private Logger logger = Logger.getLogger(getClass().getName());

    @NonNull
    private Database database;

    @Getter
	private String lastExecutedQuery;
    
    /**
     * Execute an update with a MySQL query.
     *
     * @param query The MySQL query.
     */
    @Override
    public boolean executeUpdate(String query) {
        database.connect();
        try {
            PreparedStatement sts = database.getConnection().prepareStatement(query);
            sts.executeUpdate();
            sts.close();
            this.lastExecutedQuery = query;
        } catch (SQLException e) {
            logger.log(Level.SEVERE, e.getMessage(), e);
            return false;
        }
        database.close();
        return true;
    }

    /**
     * Select an object in the database with a MySQL query.
     *
     * @param query The MySQL query.
     * @param get   The object to get.
     * @return The find object.
     */
    @Override
    public Object select(String query, String get) {
        database.connect();
        Object request = null;

        try {
            PreparedStatement sts = database.getConnection().prepareStatement(query);
            ResultSet result = sts.executeQuery();
            while (result.next())
                request = result.getObject(get);
            sts.close();
            this.lastExecutedQuery = query;
        } catch (SQLException e) {
            logger.log(Level.SEVERE, e.getMessage(), e);
        }

        database.close();
        return request;
    }

    /**
     * Select an object in the database with a MySQL query.
     *
     * @param query The MySQL query.
     * @param get   The list of Fields
     * @return The find Fields Object.
     */
    @Override
	public IFields select(String query, List<String> fields) {
		database.connect();
		List<IField> datas = new ArrayList<IField>();
        try {
            PreparedStatement sts = database.getConnection().prepareStatement(query);
            ResultSet result = sts.executeQuery();
            ResultSetMetaData rsmd = sts.getMetaData();
            int columnCount = rsmd.getColumnCount();
            while (result.next()) {
            	for (int i = 1; i <= columnCount; i++ ) {
            		String name = rsmd.getColumnName(i);
            		if(fields.contains(name)){
            			Field tmp = new Field(name, result.getObject(i));
            			datas.add(tmp);
            		}
             	}
            }
            sts.close();
            this.lastExecutedQuery = query;
        } catch (SQLException e) {
            logger.log(Level.SEVERE, e.getMessage(), e);
        }
        database.close();
        return new Fields(datas);
	}
	
	/**
     * Select an object in the database with a MySQL query.
     *
     * @param query The MySQL query.
     * @param get   The list of Fields
     * @return A list of the founded Fields.
     */
	@Override
	public List<IFields> select(String query) {
		database.connect();
		List<IFields> datas = new ArrayList<IFields>();
        try {
            PreparedStatement sts = database.getConnection().prepareStatement(query);
            ResultSet result = sts.executeQuery();
            ResultSetMetaData rsmd = sts.getMetaData();
            int columnCount = rsmd.getColumnCount();
            while (result.next()) {
            	List<IField> list = new ArrayList<IField>();
            	for (int i = 1; i <= columnCount; i++ ) {
            		String name = rsmd.getColumnName(i);
                 	Field tmp = new Field(name, result.getObject(i));
                 	list.add(tmp);
             	}
            	datas.add(new Fields(list));
            }
            sts.close();
            this.lastExecutedQuery = query;
        } catch (SQLException e) {
        	logger.log(Level.SEVERE, e.getMessage(), e);
        }
        database.close();
        return datas;
	}
    
    /**
     * Read a list of values with MySQL query.
     *
     * @param query The MySQL written query.
     * @param get   The field to read.
     * @return Returns found List.
     */
	@Override
    public List<Object> selectList(String query, String get) {
        database.connect();
        List<Object> request = new ArrayList<Object>();

        try {
            PreparedStatement sts = database.getConnection().prepareStatement(query);
            ResultSet result = sts.executeQuery();
            while (result.next())
                request.add(result.getObject(get));

            sts.close();
            this.lastExecutedQuery = query;
        } catch (SQLException e) {
            logger.log(Level.SEVERE, e.getMessage(), e);
        }

        database.close();
        return request;
    }

	/**
     * Read a list of values with MySQL query.
     *
     * @param query The MySQL written query.
     * @return Returns found List.
     */
    @Override
	public List<IFields> selectList(String query) {
		database.connect();
        List<IFields> request = new ArrayList<IFields>();
        try {
        	PreparedStatement sts = database.getConnection().prepareStatement(query);
            ResultSet result = sts.executeQuery();
            ResultSetMetaData rsmd = sts.getMetaData();
            int columnCount = rsmd.getColumnCount();
            while (result.next()) {
            	List<IField> list = new ArrayList<IField>();
            	for (int i = 1; i <= columnCount; i++ ) {
            		String name = rsmd.getColumnName(i);
                 	Field tmp = new Field(name, result.getObject(i));
                 	list.add(tmp);
             	}
            	request.add(new Fields(list));
            }
            sts.close();
            this.lastExecutedQuery = query;
        } catch (SQLException e) {
            logger.log(Level.SEVERE, e.getMessage(), e);
        }

        database.close();
        return request;
	}
    
    /**
     * Retrieves the string parsed from the list of where values.
     *
     * @param where The list of where values.
     * @return The parsed string.
     */
    @Override
    public String getListOfWhereValues(List<IField> where) {
        StringBuilder whereFields = new StringBuilder();
        where.forEach(whereField ->
                whereFields.append(whereField.getParsedNameAndValue() + Method.AND));

        return whereFields.toString().substring(0, whereFields.length() - 5);
    }
    
    
	/**
	 * Check if an item exist or not in the database
	 * 
	 * @param query
     * @return exist or not
	 */
    @Override
	public boolean exist(String query) {
		database.connect();
        boolean exist = false;
        try {
            PreparedStatement sts = database.getConnection().prepareStatement(query);
            ResultSet result = sts.executeQuery();
            
            sts.close();
            this.lastExecutedQuery = query;
            return result.next();
        } catch (SQLException e) {
            logger.log(Level.SEVERE, e.getMessage(), e);
        }

        database.close();
        return exist;
	}

}
