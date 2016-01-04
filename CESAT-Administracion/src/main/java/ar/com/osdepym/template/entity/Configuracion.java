package ar.com.osdepym.template.entity;

import java.util.ArrayList;
import java.util.List;

												// Login

public class Configuracion {
	
	private int DT_RowId= 0;
	private int tiempopngjpg ;
	private int tiempogif ;
	private String directorio ;
	private String feed ;


	private String error;
	public int getDT_RowId() {
		return DT_RowId;
	}
	public void setDT_RowId(int dT_RowId) {
		DT_RowId = dT_RowId;
	}
	
	
	public int getTiempopngjpg() {
		return tiempopngjpg;
	}
	public void setTiempopngjpg(int tiempopngjpg) {
		this.tiempopngjpg = tiempopngjpg;
	}
	public int getTiempogif() {
		return tiempogif;
	}
	public void setTiempogif(int tiempogif) {
		this.tiempogif = tiempogif;
	}
	public String getDirectorio() {
		return directorio;
	}
	public void setDirectorio(String directorio) {
		this.directorio = directorio;
	}
	public String getError() {
		return error;
	}
	public void setError(String error) {
		this.error = error;
	}
	public String getFeed() {
		return feed;
	}
	public void setFeed(String feed) {
		this.feed = feed;
	}
	
	
}
