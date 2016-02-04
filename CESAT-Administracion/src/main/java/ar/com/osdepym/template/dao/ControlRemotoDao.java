package ar.com.osdepym.template.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

import org.apache.log4j.Logger;

import ar.com.osdepym.common.utils.ConnectionMysql;
import ar.com.osdepym.common.utils.LoggerVariables;
import ar.com.osdepym.template.entity.ControlRemoto;
import ar.com.osdepym.template.entity.ControlSector;
import ar.com.osdepym.template.entity.Sector;

import com.mysql.jdbc.Statement;

public class ControlRemotoDao {

	private static Logger LOGGER = Logger
			.getLogger(LoggerVariables.ADMINISTRADOR + "-" + ControlRemotoDao.class);
	/**
	 * Metodo listar Controles Remoto
	 * @return ArrayList<Control Remoto>
	 */
	public ArrayList<ControlRemoto> listaControlesRemoto() {
		Connection connection = new ConnectionMysql().createConnection();
		ArrayList<ControlRemoto> controlesRemotos = new ArrayList<ControlRemoto>();
		LOGGER.debug(LoggerVariables.PREPARANDO_BUSCAR);

		String query = "SELECT * from turnero.controlremoto";
		PreparedStatement preparedStmt;
		try {
			preparedStmt = connection.prepareStatement(query);
			ResultSet rs = preparedStmt.executeQuery();

			while (rs.next()) {
				ControlRemoto control = new ControlRemoto();
				control.setDT_RowId(rs.getInt("id"));
				
				control.setCodigo(rs.getString("codigo"));
				control.setAnterior(rs.getInt("anterior"));
				control.setSiguiente(rs.getInt("siguiente"));
				controlesRemotos.add(control);
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

		return controlesRemotos;
	}
	/**
	 * Modificar Control
	 * @param controlEditar
	 * @return ControlRemoto
	 */
	public ControlRemoto editarControlRemoto(ControlRemoto controlEditar) {
		Connection connection = new ConnectionMysql().createConnection();
		String query = "update turnero.controlremoto set codigo = ? , anterior = ? , siguiente = ? where id = ? ";
		PreparedStatement preparedStmt;
		LOGGER.debug(LoggerVariables.PREPARANDO_EDIT);

		try {
			preparedStmt = connection.prepareStatement(query);
			// preparedStmt.setInt(1, sectorEditar.getSector());
			preparedStmt.setString(1, controlEditar.getCodigo());
			preparedStmt.setInt(2, controlEditar.getAnterior());
			preparedStmt.setInt(3, controlEditar.getSiguiente());
			preparedStmt.setInt(4, controlEditar.getDT_RowId());
			preparedStmt.executeUpdate();
			return controlEditar;
			// Recupero la fila cambiada
		} catch (SQLException e) {
			String error = e.getMessage();
			if (error.contains("'UK_cod_sector'")) {
				controlEditar
						.setError("No se permiten Codigo de Sector Controles duplicados");
				LOGGER.error(LoggerVariables.ERROR + "-"
						+ controlEditar.getError());

				return controlEditar;
			}

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
				LOGGER.error(LoggerVariables.ERROR + "-"
						+ controlEditar.getError());
			}
		}

		return null;
	}

	/**
	 * Crear Control Remoto
	 * @param controlRemotoInsertar
	 * @return ControlRemoto
	 */
	public ControlRemoto insertarControlRemoto(ControlRemoto controlInsertar) {
		Connection connection = new ConnectionMysql().createConnection();
		String query = "insert into turnero.controlremoto (codigo,anterior,siguiente) values(?,?,?) ";
		LOGGER.debug(LoggerVariables.PREPARANDO_INSERT);

		PreparedStatement preparedStmt;
		try {
			preparedStmt = connection.prepareStatement(query,
					Statement.RETURN_GENERATED_KEYS);
			preparedStmt.setString(1, controlInsertar.getCodigo());
			preparedStmt.setInt(2, controlInsertar.getAnterior());
			preparedStmt.setInt(3, controlInsertar.getSiguiente());

			preparedStmt.execute();
			ResultSet generatedKeys = preparedStmt.getGeneratedKeys();

			while (generatedKeys.next()) {
				controlInsertar.setDT_RowId(generatedKeys.getInt(1));
			}
			return controlInsertar;
			// Recupero la fila cambiada
		} catch (SQLException e) {
			String error = e.getMessage();
			if (error.contains("'UK_cod_sector'")) {
				controlInsertar
						.setError("No se permiten Codigo de Control Remoto duplicados");
				LOGGER.error(LoggerVariables.ERROR + "-"
						+ controlInsertar.getError());

				return controlInsertar;
			}

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

		return null;
	}
	/**
	 * Eliminar Control
	 * @param id
	 */
	public void eliminarControlRemoto(int id) {
		Connection connection = new ConnectionMysql().createConnection();
		LOGGER.debug(LoggerVariables.PREPARANDO_DELETE);

		String query = "delete from turnero.controlremoto where id = ? ";
		PreparedStatement preparedStmt;
		try {
			preparedStmt = connection.prepareStatement(query);
			preparedStmt.setInt(1, id);
			preparedStmt.executeUpdate();
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
	}
	/**
	 * Eliminar Sector/Control
	 * @param id
	 */
	public void eliminarSectorControlRemoto(int id) {
		Connection connection = new ConnectionMysql().createConnection();
		LOGGER.debug(LoggerVariables.PREPARANDO_DELETE);

		String query = "delete from turnero.controlremoto_sector where id = ? ";
		PreparedStatement preparedStmt;
		try {
			preparedStmt = connection.prepareStatement(query);
			preparedStmt.setInt(1, id);
			preparedStmt.executeUpdate();
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
	}
	
	public ArrayList<Sector> borrarSector() {
		return null;
	}
	 /**
	  * Metodo para obtener conexion a DB
	  * @return
	  */
	public Connection obtenerConexion() {
		return new ConnectionMysql().createConnection();
	}
	
	/**
	 * Obtener lista de controlSectores
	 * @return ArrayList<ControlSector>
	 */
	public ArrayList<ControlSector> listaControlSectores() {
		Connection connection = new ConnectionMysql().createConnection();
		ArrayList<ControlSector> controlesSector = new ArrayList<ControlSector>();
		LOGGER.debug(LoggerVariables.PREPARANDO_BUSCAR);

		String query = "SELECT * FROM turnero.controlremoto_sector INNER JOIN turnero.controlremoto ON turnero.controlremoto.id = turnero.controlremoto_sector.idControl INNER JOIN turnero.sector ON turnero.controlremoto_sector.idSector = turnero.sector.id_sector";

		PreparedStatement preparedStmt;
		try {
			preparedStmt = connection.prepareStatement(query);
			ResultSet rs = preparedStmt.executeQuery();

			while (rs.next()) {
				ControlSector controlsector = new ControlSector();
				controlsector.setDT_RowId(rs.getInt("id"));
				controlsector.setCodigoControl(rs.getString("codigo"));
				controlsector.setControlId(rs.getInt("idControl"));
				controlsector.setSectorId(rs.getInt("id_sector"));
				controlsector.setSectorNombre(rs.getString("nom_sector"));
				controlsector.setCodigoSector(rs.getString("cod_sector"));

				controlesSector.add(controlsector);
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

		return controlesSector;
	}
	/**
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	/**
	 * Metodo para asignar un sector a un Con trol. Crea un nuevo registro en ControlSectores
	 * @param controlSectorInsertar
	 * @return ControlSector
	 */
	public ControlSector insertarControlSector(
			ControlSector controlSectorInsertar) {
		Connection connection = new ConnectionMysql().createConnection();
		LOGGER.debug(LoggerVariables.PREPARANDO_INSERT);

		String query = "insert into turnero.controlremoto_sector (idControl,idSector) values(?,?) ";
		String querySector = "Select * from turnero.sector where id_sector ="
				+ controlSectorInsertar.getSectorId();
		String queryControl = "Select * from turnero.controlremoto where id ="
				+ controlSectorInsertar.getControlId();
		PreparedStatement preparedStmt;
		PreparedStatement preparedStmtSector;
		PreparedStatement preparedStmtControl;


		try {
			preparedStmt = connection.prepareStatement(query,
					Statement.RETURN_GENERATED_KEYS);

			preparedStmt.setInt(1, controlSectorInsertar.getControlId());
			preparedStmt.setInt(2, controlSectorInsertar.getSectorId());

			preparedStmt.execute();
			preparedStmtSector = connection.prepareStatement(querySector);
			preparedStmtControl = connection.prepareStatement(queryControl);

			ResultSet rsSector = preparedStmtSector.executeQuery();
			ResultSet rsControl = preparedStmtControl.executeQuery();

			while (rsSector.next()) {

				controlSectorInsertar.setCodigoSector(rsSector
						.getString("cod_sector"));
				controlSectorInsertar.setSectorNombre(rsSector
						.getString("nom_sector"));
			}
			while (rsControl.next()) {
				controlSectorInsertar.setCodigoControl(rsControl
						.getString("codigo"));
			}
			ResultSet generatedKeys = preparedStmt.getGeneratedKeys();

			while (generatedKeys.next()) {
				controlSectorInsertar.setDT_RowId(generatedKeys.getInt(1));
			}

			preparedStmt.close();
			preparedStmtSector.close();
			preparedStmtControl.close();

			return controlSectorInsertar;
			// Recupero la fila cambiada
		} catch (SQLException e) {
			LOGGER.error(LoggerVariables.ERROR+"-"+e.getMessage());
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
				LOGGER.error(LoggerVariables.ERROR+"-"+e.getMessage());
			}
		}

		return null;
	}
	public ControlSector editarControlSector(ControlSector controlSectorEditar) {
		Connection connection = new ConnectionMysql().createConnection();
		LOGGER.debug(LoggerVariables.PREPARANDO_EDIT);

		String query = "update turnero.controlremoto_sector set idSector = ?,   idControl = ?  where id = ? ";
		String querySector = "Select * from turnero.sector where id_sector ="
				+ controlSectorEditar.getSectorId();
		String queryControl = "Select * from turnero.controlremoto where id ="
				+ controlSectorEditar.getControlId();
		PreparedStatement preparedStmt;
		PreparedStatement preparedStmtSector;
		PreparedStatement preparedStmtControl;

		try {
			preparedStmt = connection.prepareStatement(query);

			preparedStmt.setInt(1, controlSectorEditar.getSectorId());
			preparedStmt.setInt(2, controlSectorEditar.getControlId());
			preparedStmt.setInt(3, controlSectorEditar.getDT_RowId());

			preparedStmt.executeUpdate();

			preparedStmtSector = connection.prepareStatement(querySector);
			preparedStmtControl = connection.prepareStatement(queryControl);

			ResultSet rsSector = preparedStmtSector.executeQuery();
			ResultSet rsControl = preparedStmtControl.executeQuery();

			while (rsSector.next()) {
				controlSectorEditar.setCodigoSector(rsSector
						.getString("cod_sector"));
				controlSectorEditar.setSectorNombre(rsSector
						.getString("nom_sector"));
			}
			while (rsControl.next()) {
				controlSectorEditar.setCodigoControl(rsControl
						.getString("codigo"));
			}

			preparedStmt.close();
			preparedStmtSector.close();
			preparedStmtControl.close();

			return controlSectorEditar;
			// Recupero la fila cambiada
		} catch (SQLException e) {
			LOGGER.error(LoggerVariables.ERROR+"-"+e.getMessage());
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
				LOGGER.error(LoggerVariables.ERROR+"-"+e.getMessage());
			}
		}

		return null;
	

		
	}

}
