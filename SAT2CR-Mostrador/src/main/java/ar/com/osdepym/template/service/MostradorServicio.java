package ar.com.osdepym.template.service;

import java.util.ArrayList;

import ar.com.osdepym.template.dao.MostradorDao;
import ar.com.osdepym.template.entity.Configuracion;
import ar.com.osdepym.template.entity.Turno;



public class MostradorServicio {
	private MostradorDao mostradorDao= new MostradorDao();
	
	/**
	 * Servicio para obtener los ultimos turnos
	 * @return ArrayList<Turno>
	 */
	public ArrayList<Turno> getUltimosTurnos() {
		return this.mostradorDao.getUltimosTurnos();
	}

	public String getUrlFeed() {
		// TODO Auto-generated method stub
		return this.mostradorDao.getUrlFeed();

	}

	public Configuracion getSegundosConfiguracion() {
		// TODO Auto-generated method stub
		return this.mostradorDao.getSegundosConfiguracion();
	}
	
	public String getUrlPublicidadConfiguracion() {
		return this.mostradorDao.getUrlPublicidadConfiguracion();
	}	
	
}
