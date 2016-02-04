package ar.com.osdepym.template.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

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

//		String query = "select * from turnero.turno  inner join turnero.sector on turnero.turno.id_sector =turnero.sector.id_sector where llamado ='SI' and  DATE(fecha_atencion) = DATE( NOW()) order by fecha_atencion desc limit 5";
/*		String query = "select  numero_turno,nom_sector,cod_sector from turnero.turno  " +
		" inner join turnero.sector on turnero.turno.id_sector =turnero.sector.id_sector " +
		" where llamado ='SI' and  DATE(fecha_atencion) = DATE( NOW()) group by nom_sector,cod_sector order by fecha_atencion desc limit 100";*/
		String query = "select  numero_turno,nom_sector,cod_sector from turnero.turno  " +
		" inner join turnero.sector on turnero.turno.id_sector =turnero.sector.id_sector " +
		" where llamado ='SI' and  DATE(fecha_atencion) = DATE( NOW()) order by cod_sector,fecha_atencion desc limit 100";

		PreparedStatement preparedStmt;
		try {
			preparedStmt = connection.prepareStatement(query);
			ResultSet rs = preparedStmt.executeQuery();

			Map<String,String> sectores = new HashMap<String,String>();
			
			while (rs.next()) {
				String sector =rs.getString("cod_sector"); 
				if ( ! sectores.containsKey(sector)){
					sectores.put(sector, sector);
					turno = new Turno();
					turno.setNumeroTurno(sector
							+ rs.getInt("numero_turno"));
					turno.setNombreSector(rs.getString("nom_sector"));
					turnos.add(turno);
				}
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

		String query = "SELECT feed FROM turnero.configuracion";
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

		String query = "SELECT tiempogif,tiempopngjpg FROM turnero.configuracion";
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

		String query = "SELECT directorio FROM turnero.configuracion";
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
