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

public class LlamarTurnoSiguiente extends LlamarTurno {

	private static Logger LOGGER = Logger.getLogger(LoggerVariables.OPERADOR
			+ "-" + LlamarTurnoSiguiente.class);

	

	// Proximo turno

	/**
	 * Metodo para llamar al siguiente turno
	 * 
	 * @param sector
	 * @param control
	 */
	public synchronized String execute(	int codigoControl) {

		LOGGER.debug(LoggerVariables.PREPARANDO_LLAMAR);

		//obtengo el sector
		int sector = getSectorByControl(codigoControl); 
		
		Connection connection = new ConnectionMysql().createConnection();

		String error = "";
		try {
	
			
			String query = "select * from turnero.turno where llamado like ? and id_sector = ? and DATE(fecha_ticket)=DATE(NOW()) order by numero_turno and fecha_ticket ";

			PreparedStatement preparedStmt = connection.prepareStatement(query);
			preparedStmt.setString(1, "NO");
			preparedStmt.setInt(2, sector);

			ResultSet rs = preparedStmt.executeQuery();

			int id_turno = 0;
			while (rs.next()) {
				id_turno = rs.getInt("id_turno");
				break;
			}
			
			int idControl = getIdControlByCodigoControl(codigoControl);
			
			/* LLAMA AL SIGUIENTE TURNO*/
			callTurnoSiguiente(idControl, id_turno );

			preparedStmt.close();

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