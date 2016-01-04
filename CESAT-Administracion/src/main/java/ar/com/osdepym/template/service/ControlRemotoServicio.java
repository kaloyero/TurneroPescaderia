package ar.com.osdepym.template.service;

import java.util.ArrayList;
import java.util.List;

import ar.com.osdepym.template.dao.ControlRemotoDao;
import ar.com.osdepym.template.entity.ControlRemoto;
import ar.com.osdepym.template.entity.ControlSector;


public class ControlRemotoServicio {
	private ControlRemotoDao controlDao= new ControlRemotoDao();
		
	
	/**
	 * Listar Controles Remoto
	 * @return ArrayList<ControlRemoto>
	 */
	public ArrayList<ControlRemoto> listaControles() {
		return controlDao.listaControlesRemoto();
	}
	/**
	 * Listar Controles Remoto/Sector
	 * @return ArrayList<ControlSector>
	 */
	public ArrayList<ControlSector> listaControSector() {
		return controlDao.listaControlSectores();
	}
	/**
	 * Crear Control
	 * @param controlInsertar
	 * @return Control
	 */
	public ControlRemoto insertarControl(ControlRemoto controlInsertar) {
		return this.controlDao.insertarControlRemoto(controlInsertar);
	}
	/**
	 * Modificar ControlRemoto
	 * @param ControlRemotoEditar
	 * @return ControlRemoto
	 */
	public ControlRemoto editarControlRemoto(ControlRemoto controlRemotoEditar) {
		
		return this.controlDao.editarControlRemoto(controlRemotoEditar);
	}
	/**
	 * Eliminar Control Remoto
	 * @param id
	 */
	public void eliminarControlRemoto(int id) {
		 this.controlDao.eliminarControlRemoto(id);
		
	}
	public ControlSector editarControlSector(ControlSector controlSectorEditar) {
		// TODO Auto-generated method stub
		return this.controlDao.editarControlSector(controlSectorEditar);
	}
	public ControlSector insertarControlSector(ControlSector controlSectorIngresar) {
		// TODO Auto-generated method stub
		return this.controlDao.insertarControlSector(controlSectorIngresar);
	
	
	}
	public void eliminarControlSector(int id) {
		 this.controlDao.eliminarSectorControlRemoto(id);
		
	}
}
