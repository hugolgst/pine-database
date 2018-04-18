package xyz.hugol.database.objects;

import lombok.AccessLevel;
import lombok.NonNull;
import lombok.RequiredArgsConstructor;
import xyz.hugol.database.utils.Method;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 * @author <a href="mailto:contact@hugol.xyz">hugol</a>
 */
@RequiredArgsConstructor(access = AccessLevel.PROTECTED)
public class RequestHandler {

    private Logger logger = Logger.getLogger(getClass().getName());

    @NonNull
    private Database database;

    /**
     * Execute an update with a MySQL query.
     *
     * @param query The MySQL query.
     */
    protected void executeUpdate(String query) {
        database.connect();
        try {
            PreparedStatement sts = database.getConnection().prepareStatement(query);
            sts.executeUpdate();
            sts.close();
        } catch (SQLException e) {
            logger.log(Level.SEVERE, e.getMessage(), e);
        }
        database.close();
    }

    /**
     * Select an object in the database with a MySQL query.
     *
     * @param query The MySQL query.
     * @param get   The object to get.
     * @return The find object.
     */
    protected Object select(String query, String get) {
        database.connect();
        Object request = null;

        try {
            PreparedStatement sts = database.getConnection().prepareStatement(query);
            ResultSet result = sts.executeQuery();
            while (result.next())
                request = result.getObject(get);
            sts.close();
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
     * @param get   The field to read.
     * @return Returns found List.
     */
    protected List<Object> selectList(String query, String get) {
        database.connect();
        List<Object> request = new ArrayList();

        try {
            PreparedStatement sts = database.getConnection().prepareStatement(query);
            ResultSet result = sts.executeQuery();
            while (result.next())
                request.add(result.getObject(get));

            sts.close();
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
    protected String getListOfWhereValues(List<Field> where) {
        StringBuilder whereFields = new StringBuilder();
        where.forEach(whereField ->
                whereFields.append(whereField.getParsedNameAndValue() + Method.AND));

        return whereFields.toString().substring(0, whereFields.length() - 4);
    }

}
