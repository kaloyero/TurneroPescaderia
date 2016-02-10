package ar.com.osdepym.template.common.validation;

// Llamador

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
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
	public synchronized String execute( int idControl) {

		LOGGER.debug(LoggerVariables.PREPARANDO_LLAMAR);

		//obtengo el sector
		int sector = getSectorByControl(idControl); 
		
		Connection connection = new ConnectionMysql().createConnection();

		Date fechaDate = new Date();
		java.text.SimpleDateFormat sdf = new java.text.SimpleDateFormat(
				"yyyy-MM-dd HH:mm:ss");

		String fecha = sdf.format(fechaDate);
		System.out.println("Fecha " + fecha);

		String error = "";
		try {

			String query = "select * from turnero.turno where llamado like ? and id_sector = ? and DATE(fecha_ticket)=DATE(NOW()) order by numero_turno desc,fecha_ticket desc ";

			PreparedStatement preparedStmt = connection.prepareStatement(query);
			preparedStmt.setString(1, "SI");
			preparedStmt.setInt(2, sector);

			ResultSet rs = preparedStmt.executeQuery();
			
			//Verifico que exista turno siguiente
			boolean existeProximoTurno = verificarProximoTurno(rs);
			if (existeProximoTurno){
				//Obtengo el turno a retroceder
				int id_turno_retroceder = getTurnoId(rs);
	
				/* TURNO ANTERIOR: Retrocedo el id de turno. lo pongo como que no fue llamado*/
				callTurnoAnterior(id_turno_retroceder);
	
				/* LLAMA AL SIGUIENTE TURNO*/
				//Obtengo el turno anteriormente llamado por ese Sector
				int id_turno_proximo = getTurnoId(rs);
				if (id_turno_proximo > 0){
					callTurnoSiguiente(idControl, id_turno_proximo );
				}
	
				// actualiza la tabla de turnos
				System.out.println("ANTERIOR: Turno id que se retrocede " + id_turno_retroceder);
				System.out.println("ANTERIOR: Turno id proximo a llamar " + id_turno_proximo);
			}
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
	
	private boolean verificarProximoTurno(ResultSet rs) throws SQLException{
		boolean proximoTurno = false;
		if (getSizeRs(rs) > 1){
			proximoTurno=true;
		}
		
		return proximoTurno;
	}
	
	private int getTurnoId(ResultSet rs) throws SQLException{
		int id_turno = 0;
		while (rs.next()) {
			id_turno = rs.getInt("id_turno");
			break;
		}
		return id_turno;
	}

}