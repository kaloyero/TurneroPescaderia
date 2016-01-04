package ar.com.osdepym.model;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

public class BaseDao {
    private static final String MYSQL_DRIVERS = "com.mysql.jdbc.Driver";
    private static final String DB_URL = "jdbc:mysql://localhost:3306/CESAT";
    private static final String USER = "adminsat";
    private static final String PASSWORD = "Osdepym2014";

    private Connection connect = null;
    private Statement statement = null;
    private PreparedStatement preparedStatement = null;
    private ResultSet resultSet = null;

	protected Statement getConn() throws ClassNotFoundException, SQLException{
	
        // Cargo los drivers de MySQL
        Class.forName(MYSQL_DRIVERS);
        // Configuracion
        connect = DriverManager.getConnection(DB_URL, USER, PASSWORD);

        // statements allow to issue SQL queries to the database
        return connect.createStatement();
	}
}
