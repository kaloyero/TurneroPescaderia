package ar.com.osdepym.template.web.action;
 

import java.util.ArrayList;
import java.util.Map;

import org.apache.struts2.interceptor.ParameterAware;

import ar.com.osdepym.template.entity.Configuracion;
import ar.com.osdepym.template.service.ConfiguracionServicio;

import com.opensymphony.xwork2.ActionSupport;
 
public class ConfiguracionDatatableAction extends ActionSupport implements ParameterAware{
	  Map parameters;
	  private ArrayList<Configuracion> aaData = new ArrayList();
	  private ConfiguracionServicio configuracionServicio = new ConfiguracionServicio();
	  private Configuracion row ;
	  private String error;
	
	/**
	 * Lista Sucursales
	 * @return
	 */
	public String listaConfiguraciones() {
		this.setAaData(this.getConfiguracionServicio().listaConfiguracion());
		return SUCCESS;
	}
	
	/**
	 * Configuraciones Operaiones
	 * @return
	 */
	public String configuracionesOperaciones() {
		//LoginAction.getUsuariosLogueadosTest().remove(0);
		//LoginAction.getUsuariosLogueadosTest().add(new Date());
		String[] accion= (String[]) parameters.get("action");
	

		if (accion[0].equalsIgnoreCase("edit") ){
			Configuracion configuracionlEditar= new Configuracion();


			configuracionlEditar.setDT_RowId(Integer.parseInt(((String[]) parameters.get("id"))[0]));

				configuracionlEditar.setTiempopngjpg(Integer.parseInt(((String[]) parameters.get("data[tiempopngjpg]"))[0]));
				configuracionlEditar.setTiempogif(Integer.parseInt(((String[]) parameters.get("data[tiempogif]"))[0]));
				configuracionlEditar.setDirectorio(((String[]) parameters.get("data[directorio]"))[0]);
				configuracionlEditar.setFeed(((String[]) parameters.get("data[feed]"))[0]);


				this.setRow(this.getConfiguracionServicio().editarConfiguracion(configuracionlEditar));
				this.setError(this.getRow().getError());
				


		}
	
		
		return SUCCESS;
	}


	public ArrayList<Configuracion> getAaData() {
		return aaData;
	}
	public void setAaData(ArrayList<Configuracion> aaData) {
		this.aaData = aaData;
	}
	public ConfiguracionServicio getConfiguracionServicio() {
		return configuracionServicio;
	}
	public void setConfiguracionServicio(ConfiguracionServicio configuracionServicio) {
		this.configuracionServicio = configuracionServicio;
	}
	public Map getParameters() {
	    return parameters;
	  }

	  public void setParameters(Map parameters) {
	    this.parameters = parameters;
	  }


	public Configuracion getRow() {
		return row;
	}
	public void setRow(Configuracion row) {
		this.row = row;
	}
	public String getError() {
		return error;
	}
	public void setError(String error) {
		this.error = error;
	}

 

}