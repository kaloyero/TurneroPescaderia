package ar.com.osdepym.template.entity;

import java.util.ArrayList;
import java.util.List;

												// Login

public class ControlRemoto {
	
	private int DT_RowId= 0;
	private String codigo ;
	private int anterior ;
	private int siguiente ;
	
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
	public int getAnterior() {
		return anterior;
	}
	public void setAnterior(int anterior) {
		this.anterior = anterior;
	}
	public int getSiguiente() {
		return siguiente;
	}
	public void setSiguiente(int siguiente) {
		this.siguiente = siguiente;
	}
	
	
	
}
