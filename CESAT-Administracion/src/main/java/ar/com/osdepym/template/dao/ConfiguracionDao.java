package ar.com.osdepym.template.dao;

import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

import org.apache.log4j.Logger;

import ar.com.osdepym.common.utils.ConnectionMysql;
import ar.com.osdepym.common.utils.LoggerVariables;
import ar.com.osdepym.template.entity.Configuracion;


public class ConfiguracionDao {
	private static Logger LOGGER = Logger
			.getLogger(LoggerVariables.ADMINISTRADOR + "-" + ConfiguracionDao.class);
	
	/**
	 * Metodo listar usuarios
	 * @return ArrayList<Usuario>
	 */
	public ArrayList<Configuracion> listaConfiguracion() {
		Connection connection = new ConnectionMysql().createConnection();
		ArrayList<Configuracion> configuraciones = new ArrayList<Configuracion>();
		LOGGER.debug("Listando Configuraciones");

		String query = "SELECT * FROM sat.configuracion";

		PreparedStatement preparedStmt;
		try {
			preparedStmt = connection.prepareStatement(query);
			ResultSet rs = preparedStmt.executeQuery();

			while (rs.next()) {
				Configuracion configuracion = new Configuracion();
				configuracion.setDT_RowId(rs.getInt("id"));
				configuracion.setTiempopngjpg(rs.getInt("tiempopngjpg"));
				configuracion.setTiempogif(rs.getInt("tiempogif"));
				configuracion.setDirectorio(rs.getString("directorio"));
				configuracion.setFeed(rs.getString("feed"));



				configuraciones.add(configuracion);
			}

			preparedStmt.close();
		} catch (SQLException e) {
			LOGGER.error(LoggerVariables.ERROR + "-" + e.getMessage());
			e.printStackTrace();
		} finally {
			try {
				if (connection != null) {
					connection.close();
					LOGGER.info(LoggerVariables.CONEXION_CERRADA);
				}
			} catch (SQLException e) {
				e.getMessage();
				e.printStackTrace();
				LOGGER.error(LoggerVariables.ERROR + "-" + e.getMessage());
			}
		}

		return configuraciones;
	}
	/**
	 * Editar Configuracion
	 * @param configuracionEditar
	 * @return Configuracion
	 */
	public Configuracion editarConfiguracion(Configuracion configuracionEditar) {
		Connection connection = new ConnectionMysql().createConnection();
		String query = "update sat.configuracion set tiempopngjpg = ?,tiempogif = ?,directorio=?,feed=? where id = ? ";
		LOGGER.debug(LoggerVariables.PREPARANDO_EDIT);

		PreparedStatement preparedStmt;
		try {
			preparedStmt = connection.prepareStatement(query);
			preparedStmt.setInt(1, configuracionEditar.getTiempopngjpg());
			preparedStmt.setInt(2, configuracionEditar.getTiempogif());
			preparedStmt.setString(3, configuracionEditar.getDirectorio());
			preparedStmt.setString(4, configuracionEditar.getFeed());

			
			preparedStmt.setInt(5, configuracionEditar.getDT_RowId());
			

			preparedStmt.executeUpdate();

			preparedStmt.close();
	
			return configuracionEditar;
			// Recupero la fila cambiada
		} catch (SQLException e) {
			String error = e.getMessage();
			LOGGER.error(LoggerVariables.ERROR + "-" + e.getMessage());
			return configuracionEditar;

		} finally {
			try {
				if (connection != null) {
					connection.close();
					LOGGER.info(LoggerVariables.CONEXION_CERRADA);
				}
			} catch (SQLException e) {
				e.getMessage();
				e.printStackTrace();
				LOGGER.error(LoggerVariables.ERROR + "-" + e.getMessage());
			}
		}
	}
}
