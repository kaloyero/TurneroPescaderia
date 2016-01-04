package ar.com.osdepym.template.web.action;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import org.apache.log4j.Logger;
import org.apache.struts2.interceptor.SessionAware;

import ar.com.osdepym.common.utils.LoggerVariables;
import ar.com.osdepym.template.entity.Sector;
import ar.com.osdepym.template.service.SectorDao;
import ar.com.osdepym.template.service.TurnoDto;

import com.opensymphony.xwork2.ActionContext;
import com.opensymphony.xwork2.ActionSupport;

public abstract class AbstractLoginAction extends ActionSupport implements
		SessionAware {
	
	private static Logger LOGGER = Logger.getLogger(LoggerVariables.OPERADOR
			+ "-" + AbstractLoginAction.class);

	private String nro_sector;
	private String contraCambiada;
	private String exportado;
	public static final String NOM_EXPO = "nombreExpo";
	public static final String TURNOS = "turnos";
	private String rutaArchivo;
	private String espera;
	private String nomSector;

	private String letra_sector;
	private List<TurnoDto> turnos = new ArrayList<TurnoDto>();
	private Sector sector;
	private String id_user;
	private String numero;
	private String perfil;
	private String contrasenaAnterior;
	private String contrasenaNueva;
	private String contrasenaRepetida;
	private String llamado;
	private String puesto;
	private String errorMessage;
	private String id_turno;

	public static final String PUESTO = "puesto";
	private int nrosEspera;
	private String nomUsuario;
	private String nroLlamado;
	private SectorDao sectorDAO = new SectorDao();
	public static final String USER = "nomUser";
	public static final String SECTOR = "sector";
	public static final String TURNO = "turno";
	private List<Sector> sectores = new ArrayList<Sector>();

	private String ipv4;
	public static final String PERFIL = "perfil";
	public static final String DIR_IP = "ipv4";
	public static final String ID_PUESTO = "idPuesto";
	Map<String, Object> session = ActionContext.getContext().getSession();
	

	
	public String getNro_sector() {
		return nro_sector;
	}

	public void setNro_sector(String nro_sector) {
		this.nro_sector = nro_sector;
	}

	public String getNomSector() {
		return nomSector;
	}

	public void setNomSector(String nomSector) {
		this.nomSector = nomSector;
	}

	public String getLetra_sector() {
		return letra_sector;
	}

	public void setLetra_sector(String letra_sector) {
		this.letra_sector = letra_sector;
	}

	public String getNumero() {
		return numero;
	}

	public void setNumero(String numero) {
		this.numero = numero;
	}

	public String getPerfil() {
		return perfil;
	}

	public void setPerfil(String perfil) {
		this.perfil = perfil;
	}

	public String getPuesto() {
		return puesto;
	}

	public void setPuesto(String puesto) {
		this.puesto = puesto;
	}

	public String getErrorMessage() {
		return errorMessage;
	}

	public void setErrorMessage(String errorMessage) {
		this.errorMessage = errorMessage;
	}

	public int getNrosEspera() {
		return nrosEspera;
	}

	public void setNrosEspera(int nrosEspera) {
		this.nrosEspera = nrosEspera;
	}

	public String getNomUsuario() {
		return nomUsuario;
	}

	public void setNomUsuario(String nomUsuario) {
		this.nomUsuario = nomUsuario;
	}


	public String getNroLlamado() {
		return nroLlamado;
	}

	public void setNroLlamado(String nroLlamado) {
		this.nroLlamado = nroLlamado;
	}

	public SectorDao getSectorDAO() {
		return sectorDAO;
	}

	public void setSectorDAO(SectorDao sectorDAO) {
		this.sectorDAO = sectorDAO;
	}

	public List<Sector> getSectores() {
		return sectores;
	}

	public void setSectores(List<Sector> sectores) {
		this.sectores = sectores;
	}



	public String getIpv4() {
		return ipv4;
	}

	public void setIpv4(String ipv4) {
		this.ipv4 = ipv4;
	}


	public Map<String, Object> getSession() {
		return session;
	}

	public void setSession(Map<String, Object> session) {
		this.session = session;
	}

	public String getId_user() {
		return id_user;
	}

	public void setId_user(String id_user) {
		this.id_user = id_user;
	}

	public String getId_turno() {
		return id_turno;
	}

	public void setId_turno(String id_turno) {
		this.id_turno = id_turno;
	}

	public String getContraCambiada() {
		return contraCambiada;
	}

	public void setContraCambiada(String contraCambiada) {
		this.contraCambiada = contraCambiada;
	}

	public String getLlamado() {
		return llamado;
	}

	public void setLlamado(String llamado) {
		this.llamado = llamado;
	}

	public String getContrasenaAnterior() {
		return contrasenaAnterior;
	}

	public void setContrasenaAnterior(String contrasenaAnterior) {
		this.contrasenaAnterior = contrasenaAnterior;
	}

	public String getContrasenaNueva() {
		return contrasenaNueva;
	}

	public void setContrasenaNueva(String contrasenaNueva) {
		this.contrasenaNueva = contrasenaNueva;
	}

	public String getContrasenaRepetida() {
		return contrasenaRepetida;
	}

	public void setContrasenaRepetida(String contrasenaRepetida) {
		this.contrasenaRepetida = contrasenaRepetida;
	}

	public Sector getSector() {
		return sector;
	}

	public void setSector(Sector sector) {
		this.sector = sector;
	}



	public String getExportado() {
		return exportado;
	}

	public void setExportado(String exportado) {
		this.exportado = exportado;
	}

	public String getRutaArchivo() {
		return rutaArchivo;
	}

	public void setRutaArchivo(String rutaArchivo) {
		this.rutaArchivo = rutaArchivo;
	}

	public String getEspera() {
		return espera;
	}

	public void setEspera(String espera) {
		this.espera = espera;
	}

	public List<TurnoDto> getTurnos() {
		return turnos;
	}

	public void setTurnos(List<TurnoDto> turnos) {
		this.turnos = turnos;
	}



}
