package ar.com.osdepym.template.web.action;

// Llamador
import org.apache.log4j.Logger;

import ar.com.osdepym.common.utils.LoggerVariables;
import ar.com.osdepym.template.common.validation.ConsultaControl;
import ar.com.osdepym.template.common.validation.LlamarTurnoAnterior;
import ar.com.osdepym.template.common.validation.LlamarTurnoSiguiente;
import ar.com.osdepym.template.entity.Control;

public class AtencionAction extends AbstractLoginAction {
	
	 private String codigoControl;

	private static Logger LOGGER = Logger.getLogger(LoggerVariables.OPERADOR
			+ "-" + AtencionAction.class);

	/**
	 * Llama al siguiente turno
	 */
	public String execute() {
		try {
				/* Consulto en la BD el codigo de Control del botn que se presiono */
			    ConsultaControl consulta =  new ConsultaControl();	
			    Integer boton = Integer.valueOf(codigoControl);
			    Control control = consulta.getControlByBoton(boton);
			    System.out.println("Se obtiene el idControl: " + control.getIdControl() + " para el boton " + boton);
			    LOGGER.debug("Se obtiene el idControl: " + control.getIdControl() + " para el boton " + boton);
			    if (control.isAnterior()){
					LlamarTurnoAnterior lt = new LlamarTurnoAnterior();
					lt.execute(control.getIdControl());
			    } else if  (control.isSiguiente()){
			    	LlamarTurnoSiguiente lt = new LlamarTurnoSiguiente();
					lt.execute(control.getIdControl());
			    }
			    

		} catch (Exception ex) {
			this.setErrorMessage("Ha ocurrido un error inesperado.");
			LOGGER.error(LoggerVariables.ERROR + "-" + ex.getMessage());

			return ERROR + "null";
		}
		return "success";
	}

	public String getCodigoControl() {
		return codigoControl;
	}

	public void setCodigoControl(String codigoControl) {
		this.codigoControl = codigoControl;
	}


}