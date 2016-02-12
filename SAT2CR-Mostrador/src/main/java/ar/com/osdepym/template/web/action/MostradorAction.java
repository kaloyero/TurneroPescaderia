package ar.com.osdepym.template.web.action;
 
import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import org.apache.commons.io.FileUtils;
import org.apache.struts2.ServletActionContext;

import ar.com.osdepym.template.entity.Configuracion;
import ar.com.osdepym.template.entity.Turno;
import ar.com.osdepym.template.service.MostradorServicio;

import org.apache.commons.lang.StringUtils;
import com.opensymphony.xwork2.ActionSupport;


 
public class MostradorAction extends ActionSupport{
	  private MostradorServicio mostradorServicio = new MostradorServicio();
	  private ArrayList<Turno> listaTurnos ;
	  private String urlFeed;
		private Map<String, String> maps = new HashMap<String, String>();
		private List<Map> lists = new ArrayList<Map>();


	  /**
	   * Llena la lista de los ultimos turnos que se mostraran en pantalla.
	   * @return String
	   */
	  public String ultimosLlamados() {
	
		 //System.out.println("Done");
		  listaTurnos=mostradorServicio.getUltimosTurnos();
			return SUCCESS;
		}
	  /**
	   * Recupera la URL del Feed a buscar
	   * @return String
	   */
	  public String urlFeed() {
		
		  urlFeed=mostradorServicio.getUrlFeed();
			return SUCCESS;
		}

	  public String galeria() {
	
			HttpServletRequest request = ServletActionContext.getRequest();
			 
		  //request.getServletContext().getRealPath("/WebContent");
			Configuracion configuracion=mostradorServicio.getSegundosConfiguracion();
			
			String archivosFolder = "/archivos";
			String serverFolder = ServletActionContext.getServletContext().getRealPath(archivosFolder);
			
			String urlPublicidad = mostradorServicio.getUrlPublicidadConfiguracion();
			File dest = new File(serverFolder);
			
			//Si la url de publicidad esta Vacía no actualiza los archivos de sistema
			if (StringUtils.isNotEmpty(urlPublicidad)  ){
				File source = new File(urlPublicidad);

				//File dest = new File("C:\\wsKaizen\\SAT2CR-Mostrador\\WebContent\\archivos");
				//File dest = new File(request.getServletContext().getRealPath("/WebContent/archivos").toString());
				try {
					FileUtils.cleanDirectory(dest);
				    FileUtils.copyDirectory(source, dest);
				} catch (IOException e) {
				    e.printStackTrace();
				}
			}
			  File[] directoryListing = dest.listFiles();
			  if (directoryListing != null) {
			    for (File child : directoryListing) {
					  Map<String, String> mapa = new HashMap<String, String>();
					  Integer tiempo;
					  String extension = child.getName().substring(child.getName().lastIndexOf(".") + 1, child.getName().length());
					  if (extension.equals("jpg") || extension.equals("png")){
						   tiempo=(configuracion.getTiempoPngJpg() * 1000);
							mapa.put("duration",tiempo.toString());

					  }
					  if (extension.equals("gif")){
						   tiempo=(configuracion.getTiempogif() * 1000);
							mapa.put("duration",tiempo.toString());

					  }			  
			    	mapa.put("thumbnail", "/SAT2CR-Mostrador/archivos/"+child.getName());
					mapa.put("file", "/SAT2CR-Mostrador/archivos/"+child.getName());
//			    	mapa.put("thumbnail", "http://localhost:8080/SAT2CR-Mostrador/archivos/"+child.getName());
//					mapa.put("file", "http://localhost:8080/SAT2CR-Mostrador/archivos/"+child.getName());

						mapa.put("title", "Rugge Mar");
						mapa.put("link", "link");
						mapa.put("key5", "http://html5box.com");
						lists.add(mapa);
			    }
			  } else {
			    // Handle the case where dir is not really a directory.
			    // Checking dir.isDirectory() above would not be sufficient
			    // to avoid race conditions with another process that deletes
			    // directories.
			  }


				return SUCCESS;
			}
	  

	public List<Map> getLists() {
		return lists;
	}

	public void setLists(List<Map> lists) {
		this.lists = lists;
	}

	public ArrayList<Turno> getListaTurnos() {
		return listaTurnos;
	}

	public void setListaTurnos(ArrayList<Turno> listaTurnos) {
		this.listaTurnos = listaTurnos;
	}

	public Map<String, String> getMaps() {
		return maps;
	}

	public void setMaps(Map<String, String> maps) {
		this.maps = maps;
	}
	public String getUrlFeed() {
		return urlFeed;
	}
	public void setUrlFeed(String urlFeed) {
		this.urlFeed = urlFeed;
	}
	  
}