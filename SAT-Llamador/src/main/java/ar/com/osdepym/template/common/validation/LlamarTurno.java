package ar.com.osdepym.template.common.validation;

// Llamador

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Date;

import org.apache.log4j.Logger;

import ar.com.osdepym.common.utils.ConnectionMysql;
import ar.com.osdepym.common.utils.LoggerVariables;

public abstract class LlamarTurno {

	protected synchronized int getSectorByControl( int codigoControl) {
		int sector  = 0;
		Connection connection = new ConnectionMysql().createConnection();

		try {

			String query = "SELECT idControl, idSector, codigo FROM turnero.controlremoto_sector crs "
					+ "inner join  turnero.controlremoto  cr "
					+ "on (crs.idControl = cr.id AND cr.codigo like '"+ codigoControl +"') ";

			PreparedStatement preparedStmt = connection.prepareStatement(query);

			ResultSet rs = preparedStmt.executeQuery();

			while (rs.next()) {
				sector = rs.getInt("idSector");
				break;
			}
			

			preparedStmt.close();


		} catch (SQLException e) {
			e.getMessage();
			e.printStackTrace();
			System.out.println("Error de conexion 0" + e.getMessage());
			int xdebug = 999;
		} finally {
			try {
				if (connection != null) {
					connection.close();
				}

			} catch (SQLException e) {
				e.getMessage();
				e.printStackTrace();
				System.out.println("Error de conexion 1" + e.getMessage());
			}

		}
		return sector;
	}
	
	protected synchronized int getIdControlByCodigoControl( int codigoControl) {
		int idControl  = 0;
		Connection connection = new ConnectionMysql().createConnection();

		try {

			String query = "SELECT idControl, idSector, codigo FROM turnero.controlremoto_sector crs "
					+ "inner join  turnero.controlremoto  cr "
					+ "on (crs.idControl = cr.id AND cr.codigo like '"+ codigoControl +"') ";

			PreparedStatement preparedStmt = connection.prepareStatement(query);


			ResultSet rs = preparedStmt.executeQuery();

			while (rs.next()) {
				idControl = rs.getInt("idControl");
				break;
			}
			

			preparedStmt.close();


		} catch (SQLException e) {
			e.getMessage();
			e.printStackTrace();
			System.out.println("Error de conexion 0" + e.getMessage());
			int xdebug = 999;
		} finally {
			try {
				if (connection != null) {
					connection.close();
				}

			} catch (SQLException e) {
				e.getMessage();
				e.printStackTrace();
				System.out.println("Error de conexion 1" + e.getMessage());
			}

		}
		return idControl;
	}
	
}