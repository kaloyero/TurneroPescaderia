package ar.com.osdepym.template.web.action;

// Llamador


import org.apache.log4j.Logger;

import ar.com.osdepym.common.utils.LoggerVariables;
import ar.com.osdepym.template.common.validation.LlamarTurnoAnterior;


public class AtendiendoAnteriorAction extends AbstractLoginAction {
	
	 private String codigoControl;

	
	private static Logger LOGGER = Logger.getLogger(LoggerVariables.OPERADOR
			+ "-" + AtendiendoAnteriorAction.class);

	/**
	 * Llama al siguiente turno
	 * @return 
	 */
	public String execute() {
		try {

				LlamarTurnoAnterior lt = new LlamarTurnoAnterior();
				lt.execute(Integer.valueOf(codigoControl));


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