package ar.com.osdepym.template.entity;


public class Turno {
	private String numeroTurno ;
	private String sectorAtencion ;
	private String nombreSector ;
	private String error;
	
	public String getError() {
		return error;
	}
	public void setError(String error) {
		this.error = error;
	}
	public String getNumeroTurno() {
		return numeroTurno;
	}
	public void setNumeroTurno(String numeroTurno) {
		this.numeroTurno = numeroTurno;
	}
	public String getSectorAtencion() {
		return sectorAtencion;
	}
	public void setSectorAtencion(String sectorAtencion) {
		this.sectorAtencion = sectorAtencion;
	}
	public String getNombreSector() {
		return nombreSector;
	}
	public void setNombreSector(String nombreSector) {
		this.nombreSector = nombreSector;
	}
	
	
	
}
