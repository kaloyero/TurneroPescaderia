package ar.com.osdepym.model.query;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

import ar.com.osdepym.model.BaseDao;

public class ConfigurationQuery extends BaseDao{

    private Statement statement = null;
    private PreparedStatement preparedStatement = null;
    private ResultSet resultSet = null;
    
    public int getBatchPeriod() {
    	int batchPeriod = 60;
    	try {
        	
        	statement = getConn();
            // resultSet gets the result of the SQL query
            resultSet = statement.executeQuery("select * from configuracion where clave like 'periodtime' ;");

            while (resultSet.next()) {
                String  value = resultSet.getString("value");
                batchPeriod = Integer.parseInt(value); 
            }

        } catch (Exception e) {
System.out.println(e);
        } finally {

            if(resultSet != null) {
                try {
                    resultSet.close();
                } catch (SQLException e) {
                    e.printStackTrace();
                }
            }

            if (statement != null) {
                try {
                    statement.close();
                } catch (SQLException e) {
                    e.printStackTrace();
                }

                statement = null;
            }
        }
    	return batchPeriod;
    }

	
}
