package ar.com.osdepym.template.entity;

import java.util.ArrayList;
import java.util.List;

												// Login

public class ControlRemoto {
	
	private int DT_RowId= 0;
	private String codigo ;
	
	private String error ;
	public int getDT_RowId() {
		return DT_RowId;
	}
	public String getCodigo() {
		return codigo;
	}
	public void setCodigo(String codigo) {
		this.codigo = codigo;
	}
	public String getError() {
		return error;
	}
	public void setError(String error) {
		this.error = error;
	}
	public void setDT_RowId(int dT_RowId) {
		DT_RowId = dT_RowId;
	}
	
	
	
}
