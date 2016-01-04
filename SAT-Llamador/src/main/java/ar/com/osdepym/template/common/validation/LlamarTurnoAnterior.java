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

public class LlamarTurnoAnterior extends LlamarTurno{

	private static Logger LOGGER = Logger.getLogger(LoggerVariables.OPERADOR
			+ "-" + LlamarTurnoAnterior.class);

	

	// Proximo turno

	/**
	 * Metodo para llamar al siguiente turno
	 * 
	 * @param sector
	 * @param control
	 */
	public synchronized String execute( int codigoControl) {

		LOGGER.debug(LoggerVariables.PREPARANDO_LLAMAR);

		//obtengo el sector
		int sector = getSectorByControl(codigoControl); 
		
		Connection connection = new ConnectionMysql().createConnection();

		Date fechaDate = new Date();
		java.text.SimpleDateFormat sdf = new java.text.SimpleDateFormat(
				"yyyy-MM-dd HH:mm:ss");

		String fecha = sdf.format(fechaDate);
		System.out.println("Fecha " + fecha);

		String error = "";
		try {

			String query = "select * from SAT.turno where llamado like ? and id_sector = ? and DATE(fecha_ticket)=DATE(NOW()) order by numero_turno desc,fecha_ticket desc ";

			PreparedStatement preparedStmt = connection.prepareStatement(query);
			preparedStmt.setString(1, "SI");
			preparedStmt.setInt(2, sector);

			ResultSet rs = preparedStmt.executeQuery();

			int id_turno = 0;
			while (rs.next()) {
				id_turno = rs.getInt("id_turno");
				break;
			}
			
			// actualiza el llamado
//			query = "update SAT.turno set fecha_atencion = ?, idControl = ?,  llamado = ?  where id_turno = ? ";
			query = "update SAT.turno set idControl = ?,  llamado = ?  where id_turno = ? ";
			PreparedStatement preparedStmt2 = connection
					.prepareStatement(query);
//			preparedStmt2.setString(1, null);
			preparedStmt2.setInt(1, 0);
			preparedStmt2.setString(2, "NO"); //
			preparedStmt2.setInt(3, id_turno);
			preparedStmt2.executeUpdate();

			preparedStmt.close();
			preparedStmt2.close();

			LOGGER.debug("Se ha actualizado la tabla de Turnos");

			// actualiza la tabla de turnos
			System.out.println("Actualizo la tabla de turnos id " + id_turno);

		} catch (SQLException e) {
			LOGGER.error(LoggerVariables.ERROR + "-" + e.getMessage());
			e.getMessage();
			e.printStackTrace();
			error = e.getMessage();
			System.out.println("Error de conexion 0" + e.getMessage());
			int xdebug = 999;
			return "Error";
		} finally {
			try {
				if (connection != null) {

					LOGGER.info(LoggerVariables.CONEXION_CERRADA);
					connection.close();
				}

			} catch (SQLException e) {
				LOGGER.error(LoggerVariables.ERROR + "-" + e.getMessage());
				e.getMessage();
				e.printStackTrace();
				error = e.getMessage();
				System.out.println("Error de conexion 1" + e.getMessage());
				return "Error";

			}

		}
		return "OK";
	}

}