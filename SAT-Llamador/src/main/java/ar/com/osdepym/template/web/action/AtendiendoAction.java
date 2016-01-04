package ar.com.osdepym.template.web.action;

import java.util.HashMap;
import java.util.Map;

// Llamador


import org.apache.log4j.Logger;

import com.opensymphony.xwork2.config.entities.Parameterizable;

import ar.com.osdepym.common.utils.LoggerVariables;
import ar.com.osdepym.template.common.validation.LlamarTurnoSiguiente;

public class AtendiendoAction extends AbstractLoginAction {
	
	 private String codigoControl;

	private static Logger LOGGER = Logger.getLogger(LoggerVariables.OPERADOR
			+ "-" + AtendiendoAction.class);

	/**
	 * Llama al siguiente turno
	 */
	public String execute() {
		try {

				LlamarTurnoSiguiente lt = new LlamarTurnoSiguiente();
				lt.execute(Integer.valueOf(codigoControl));

		} catch (Exception ex) {
			this.setErrorMessage("Ha ocurrido un error inesperado.");
			LOGGER.error(LoggerVariables.ERROR + "-" + ex.getMessage());

			return ERROR + "null";
		}
		return "";
	}

	public String getCodigoControl() {
		return codigoControl;
	}

	public void setCodigoControl(String codigoControl) {
		this.codigoControl = codigoControl;
	}


}