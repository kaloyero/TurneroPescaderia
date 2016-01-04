package ar.com.osdepym.template.web.action;
 
import java.util.ArrayList;
import java.util.Date;
import java.util.Map;

import org.apache.log4j.Logger;
import org.apache.struts2.interceptor.ParameterAware;

import ar.com.osdepym.common.utils.LoggerVariables;
import ar.com.osdepym.template.entity.ControlRemoto;
import ar.com.osdepym.template.entity.Sector;
import ar.com.osdepym.template.service.ControlRemotoServicio;
import ar.com.osdepym.template.service.SectorServicio;

import com.opensymphony.xwork2.ActionSupport;
 
public class ControlRemotoDatatableAction extends ActionSupport implements ParameterAware{
	  Map parameters;
	  private ArrayList<ControlRemoto> aaData = new ArrayList();
	  private ControlRemotoServicio controlRemotoServicio = new ControlRemotoServicio();
	  private ControlRemoto row ;
	  private String error;
		private static Logger LOGGER = Logger.getLogger(LoggerVariables.ADMINISTRADOR + "-" + ControlRemotoDatatableAction.class);

		/**
		 * Lista sectores
		 * @return
		 */
	public String listaControlesRemoto() {
		LOGGER.debug("Recuperando ControlesRemoto");

		this.setAaData(this.getControlRemotoServicio().listaControles());
		return SUCCESS;
	}
	
	/**
	 * Sectores operaciones
	 * @return
	 */
	public String controlesRemotoOperaciones() {
		LoginAction.getUsuariosLogueadosTest().remove(0);
		LoginAction.getUsuariosLogueadosTest().add(new Date());
		String[] accion= (String[]) parameters.get("action");
		if (accion[0].equalsIgnoreCase("edit") ){
			LOGGER.debug("Editando Control");

			ControlRemoto controlRemotoEditar= new ControlRemoto();
			controlRemotoEditar.setCodigo(((String[]) parameters.get("data[codigo]"))[0].toUpperCase());
			controlRemotoEditar.setDT_RowId(Integer.parseInt(((String[]) parameters.get("id"))[0]));			
			this.setRow(this.getControlRemotoServicio().editarControlRemoto((controlRemotoEditar)));
			this.setError(this.getRow().getError());
			//this.setAaData(this.getSectorServicio().listaSectores());
		}
		if (accion[0].equalsIgnoreCase("create")){
			LOGGER.debug("Creando Control Remoto");

			ControlRemoto controlRemotoInsertar= new ControlRemoto();
			controlRemotoInsertar.setCodigo(((String[]) parameters.get("data[codigo]"))[0].toUpperCase());
			
			//sectorInsertar.setSector(Integer.parseInt(((String[]) parameters.get("data[sector]"))[0]));
			this.setRow(this.getControlRemotoServicio().insertarControl(controlRemotoInsertar));
			this.setError(this.getRow().getError());
		}
		if (accion[0].equalsIgnoreCase("remove")){
			LOGGER.debug("Eliminando Control Remoto");

			this.getControlRemotoServicio().eliminarControlRemoto(Integer.parseInt(((String[]) parameters.get("id[]"))[0]));
		}
		
		return SUCCESS;
	}

	
	public ArrayList<ControlRemoto> getAaData() {
		return aaData;
	}

	public Map getParameters() {
	    return parameters;
	  }

	  public void setParameters(Map parameters) {
	    this.parameters = parameters;
	  }

	public ControlRemotoServicio getControlRemotoServicio() {
		return controlRemotoServicio;
	}

	public void setControlRemotoServicio(ControlRemotoServicio controlRemotoServicio) {
		this.controlRemotoServicio = controlRemotoServicio;
	}

	public void setAaData(ArrayList<ControlRemoto> aaData) {
		this.aaData = aaData;
	}


	public ControlRemoto getRow() {
		return row;
	}

	public void setRow(ControlRemoto row) {
		this.row = row;
	}

	public String getError() {
		return error;
	}
	public void setError(String error) {
		this.error = error;
	}
 

}