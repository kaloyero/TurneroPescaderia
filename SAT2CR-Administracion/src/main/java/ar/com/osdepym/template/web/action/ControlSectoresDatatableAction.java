package ar.com.osdepym.template.web.action;
 
import java.util.ArrayList;
import java.util.Date;
import java.util.Map;

import org.apache.struts2.interceptor.ParameterAware;

import ar.com.osdepym.template.entity.ControlSector;
import ar.com.osdepym.template.service.ControlRemotoServicio;

import com.opensymphony.xwork2.ActionSupport;
 
public class ControlSectoresDatatableAction extends ActionSupport implements ParameterAware{
	  Map parameters;
	  private ArrayList<ControlSector> aaData = new ArrayList();
	  private ControlRemotoServicio controlRemotoServicio = new ControlRemotoServicio();
	  private ControlSector row ;
	
	  /**
	   * Lista usuarios sectores
	   * @return
	   */
	public String listaControlSectores() {
		this.setAaData(this.getControlRemotoServicio().listaControSector());
		return SUCCESS;
	}
	/**
	 * Usuarios Sectores operaciones
	 * @return
	 */
	public String controlSectoresOperaciones() {
		LoginAction.getUsuariosLogueadosTest().remove(0);
		LoginAction.getUsuariosLogueadosTest().add(new Date());
		String[] accion= (String[]) parameters.get("action");
		if (accion[0].equalsIgnoreCase("edit") ){
			ControlSector controlSectorEditar= new ControlSector();
			
			controlSectorEditar.setSectorId(Integer.parseInt(((String[]) parameters.get("data[sectorId]"))[0]));
			controlSectorEditar.setControlId(Integer.parseInt(((String[]) parameters.get("data[controlId]"))[0]));

			controlSectorEditar.setDT_RowId(Integer.parseInt(((String[]) parameters.get("id"))[0]));
		

			this.setRow(this.getControlRemotoServicio().editarControlSector(controlSectorEditar));
		}
		if (accion[0].equalsIgnoreCase("create")){
			ControlSector controlSectorIngresar= new ControlSector();
			
			controlSectorIngresar.setSectorId(Integer.parseInt(((String[]) parameters.get("data[sectorId]"))[0]));
			controlSectorIngresar.setControlId(Integer.parseInt(((String[]) parameters.get("data[controlId]"))[0]));
			
			this.setRow(this.getControlRemotoServicio().insertarControlSector(controlSectorIngresar));
		}
		if (accion[0].equalsIgnoreCase("remove")){
			this.getControlRemotoServicio().eliminarControlSector(Integer.parseInt(((String[]) parameters.get("id[]"))[0]));
		}
		
		return SUCCESS;
	}

	public ArrayList<ControlSector> getAaData() {
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
	public ControlSector getRow() {
		return row;
	}
	public void setRow(ControlSector row) {
		this.row = row;
	}
	public void setAaData(ArrayList<ControlSector> aaData) {
		this.aaData = aaData;
	}
	
 

}