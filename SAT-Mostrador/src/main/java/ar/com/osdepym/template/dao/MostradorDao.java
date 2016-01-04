package ar.com.osdepym.template.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

import org.apache.log4j.Logger;

import ar.com.osdepym.common.utils.ConnectionMysql;
import ar.com.osdepym.common.utils.LoggerVariables;
import ar.com.osdepym.template.entity.Configuracion;
import ar.com.osdepym.template.entity.Turno;

public class MostradorDao {

	private static Logger LOGGER = Logger.getLogger(LoggerVariables.MOSTRADOR
			+ "-" + MostradorDao.class);
	/**
	 * Metodo para obtener los últimos cinco números llamados.
	 * 
	 * @return ArrayList<Turno>
	 */
	public ArrayList<Turno> getUltimosTurnos() {
		LOGGER.debug(LoggerVariables.PREPARANDO_BUSCAR);
		Turno turno = new Turno();
		Connection connection = new ConnectionMysql().createConnection();
		ArrayList<Turno> turnos = new ArrayList<Turno>();

		String query = "select * from SAt.turno  inner join SAT.sector on SAt.turno.id_sector =SAt.sector.id_sector where llamado ='SI' and  DATE(fecha_atencion) = DATE( NOW()) order by fecha_atencion desc limit 5";
		PreparedStatement preparedStmt;
		try {
			preparedStmt = connection.prepareStatement(query);
			ResultSet rs = preparedStmt.executeQuery();

			while (rs.next()) {
				turno = new Turno();
				turno.setNumeroTurno(rs.getString("cod_sector")
						+ rs.getInt("numero_turno"));
				turno.setNombreSector(rs.getString("nom_sector"));
				turnos.add(turno);
			}

		} catch (SQLException e) {
			LOGGER.error(LoggerVariables.ERROR + "-" + e.getMessage());
			e.printStackTrace();

		} finally {
			try {
				if (connection != null) {
					LOGGER.info(LoggerVariables.CONEXION_CERRADA);
					connection.close();
				}
			} catch (SQLException e) {
				e.getMessage();
				e.printStackTrace();
				LOGGER.error(LoggerVariables.ERROR + "-" + e.getMessage());
			}
		}

		return turnos;

	}
	public String getUrlFeed() {
		LOGGER.debug(LoggerVariables.PREPARANDO_BUSCAR);
		Connection connection = new ConnectionMysql().createConnection();
		String urlFeed = "";

		String query = "SELECT feed FROM SAT.configuracion";
		PreparedStatement preparedStmt;
		try {
			preparedStmt = connection.prepareStatement(query);
			ResultSet rs = preparedStmt.executeQuery();

			while (rs.next()) {
				urlFeed=rs.getString("feed");
			}

		} catch (SQLException e) {
			LOGGER.error(LoggerVariables.ERROR + "-" + e.getMessage());
			e.printStackTrace();

		} finally {
			try {
				if (connection != null) {
					LOGGER.info(LoggerVariables.CONEXION_CERRADA);
					connection.close();
				}
			} catch (SQLException e) {
				e.getMessage();
				e.printStackTrace();
				LOGGER.error(LoggerVariables.ERROR + "-" + e.getMessage());
			}
		}

		return urlFeed;
	}
	public Configuracion getSegundosConfiguracion() {
		LOGGER.debug(LoggerVariables.PREPARANDO_BUSCAR);
		Connection connection = new ConnectionMysql().createConnection();
		Configuracion configuracion = new Configuracion();

		String query = "SELECT tiempogif,tiempopngjpg FROM SAT.configuracion";
		PreparedStatement preparedStmt;
		try {
			preparedStmt = connection.prepareStatement(query);
			ResultSet rs = preparedStmt.executeQuery();

			while (rs.next()) {
				configuracion.setTiempogif(rs.getInt("tiempogif"));
				configuracion.setTiempoPngJpg(rs.getInt("tiempopngjpg"));

			}

		} catch (SQLException e) {
			LOGGER.error(LoggerVariables.ERROR + "-" + e.getMessage());
			e.printStackTrace();

		} finally {
			try {
				if (connection != null) {
					LOGGER.info(LoggerVariables.CONEXION_CERRADA);
					connection.close();
				}
			} catch (SQLException e) {
				e.getMessage();
				e.printStackTrace();
				LOGGER.error(LoggerVariables.ERROR + "-" + e.getMessage());
			}
		}

		return configuracion;
	}
	
	public String getUrlPublicidadConfiguracion() {
		LOGGER.debug(LoggerVariables.PREPARANDO_BUSCAR);
		Connection connection = new ConnectionMysql().createConnection();
		String urlPublicidad = "";

		String query = "SELECT directorio FROM SAT.configuracion";
		PreparedStatement preparedStmt;
		try {
			preparedStmt = connection.prepareStatement(query);
			ResultSet rs = preparedStmt.executeQuery();

			while (rs.next()) {
				urlPublicidad=rs.getString("directorio");
			}

		} catch (SQLException e) {
			LOGGER.error(LoggerVariables.ERROR + "-" + e.getMessage());
			e.printStackTrace();

		} finally {
			try {
				if (connection != null) {
					LOGGER.info(LoggerVariables.CONEXION_CERRADA);
					connection.close();
				}
			} catch (SQLException e) {
				e.getMessage();
				e.printStackTrace();
				LOGGER.error(LoggerVariables.ERROR + "-" + e.getMessage());
			}
		}

		return urlPublicidad;
	}
	

}
