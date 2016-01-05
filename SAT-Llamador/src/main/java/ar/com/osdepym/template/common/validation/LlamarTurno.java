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

	private static Logger LOGGER = Logger.getLogger(LoggerVariables.OPERADOR
			+ "-" + LlamarTurno.class);
	
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
	
	protected synchronized void callTurnoSiguiente( int idControl, int id_turno) throws SQLException {
		Connection connection = new ConnectionMysql().createConnection();

		//FEcha actual
		Date fechaDate = new Date();
		java.text.SimpleDateFormat sdf = new java.text.SimpleDateFormat(
				"yyyy-MM-dd HH:mm:ss");
		String fecha = sdf.format(fechaDate);
		
		// actualiza el llamado
		String query = "update turnero.turno set fecha_atencion = ?, idControl = ?,  llamado = ?  where id_turno = ? ";

		PreparedStatement preparedStmt2 = connection
				.prepareStatement(query);
		preparedStmt2.setString(1, fecha);
		preparedStmt2.setInt(2, idControl);
		preparedStmt2.setString(3, "SI"); //
		preparedStmt2.setInt(4, id_turno);
		preparedStmt2.executeUpdate();

		preparedStmt2.close();	
		LOGGER.debug("Se ha actualizado la tabla de Turnos");
		
	}
	
	protected synchronized void callTurnoAnterior( int id_turno) throws SQLException {
		Connection connection = new ConnectionMysql().createConnection();
		/* El ultimo que llamó lo pongo como que no fuè llamado */
		String query = "update turnero.turno set idControl = ?,  llamado = ?  where id_turno = ? ";
		PreparedStatement preparedStmt2 = connection
				.prepareStatement(query);
//		preparedStmt2.setString(1, null);
		preparedStmt2.setInt(1, 0);
		preparedStmt2.setString(2, "NO"); //
		preparedStmt2.setInt(3, id_turno);
		preparedStmt2.executeUpdate();
		preparedStmt2.close();
		
	}
	
}