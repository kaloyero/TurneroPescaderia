package ar.com.osdepym.template.entity;

import java.util.ArrayList;


public class ControlSector {

	private int DT_RowId= 0;
	private int controlId ;
	private int sectorId ;
	private String sectorNombre ;
	private String codigoSector ;
	private String codigoControl ;
	public int getDT_RowId() {
		return DT_RowId;
	}
	public void setDT_RowId(int dT_RowId) {
		DT_RowId = dT_RowId;
	}
	public int getControlId() {
		return controlId;
	}
	public void setControlId(int controlId) {
		this.controlId = controlId;
	}
	public int getSectorId() {
		return sectorId;
	}
	public String getCodigoSector() {
		return codigoSector;
	}
	public void setCodigoSector(String codigoSector) {
		this.codigoSector = codigoSector;
	}
	public void setSectorId(int sectorId) {
		this.sectorId = sectorId;
	}
	public String getSectorNombre() {
		return sectorNombre;
	}
	public void setSectorNombre(String sectorNombre) {
		this.sectorNombre = sectorNombre;
	}

	public String getCodigoControl() {
		return codigoControl;
	}
	public void setCodigoControl(String codigoControl) {
		this.codigoControl = codigoControl;
	}
	
	
}
