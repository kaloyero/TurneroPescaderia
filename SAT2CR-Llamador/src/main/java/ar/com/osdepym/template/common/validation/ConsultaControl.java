package ar.com.osdepym.template.common.validation;

// Llamador

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import org.apache.log4j.Logger;

import ar.com.osdepym.common.utils.ConnectionMysql;
import ar.com.osdepym.common.utils.LoggerVariables;
import ar.com.osdepym.template.entity.Control;

public class ConsultaControl {

	private static Logger LOGGER = Logger.getLogger(LoggerVariables.OPERADOR
			+ "-" + ConsultaControl.class);
	
	public synchronized Control getControlByBoton( int boton) {
		Control ctrl = new Control();
		Connection connection = new ConnectionMysql().createConnection();

		try {

			String query = "SELECT id,siguiente,anterior, codigo FROM turnero.controlremoto cr "
					+ "WHERE cr.anterior = '"+ boton +"' OR cr.siguiente = '"+ boton +"' ";

			PreparedStatement preparedStmt = connection.prepareStatement(query);

			ResultSet rs = preparedStmt.executeQuery();

			while (rs.next()) {
				ctrl.setIdControl(rs.getInt("id"));;
				if (rs.getInt("anterior") == boton){
					ctrl.setAnterior(true);
				} else if (rs.getInt("siguiente") == boton){
					ctrl.setSiguiente(true);
				}
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
		return ctrl;
	}
	
}