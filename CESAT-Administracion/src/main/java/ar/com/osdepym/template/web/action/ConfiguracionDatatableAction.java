package ar.com.osdepym.template.web.action;
 

import java.awt.Image;
import java.awt.image.BufferedImage;
import java.io.BufferedInputStream;
import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.ArrayList;
import java.util.Map;

import javax.imageio.ImageIO;
import javax.swing.ImageIcon;

import org.apache.commons.io.FileUtils;
import org.apache.struts2.ServletActionContext;
import org.apache.struts2.interceptor.ParameterAware;

import ar.com.osdepym.template.entity.Configuracion;
import ar.com.osdepym.template.service.ConfiguracionServicio;

import com.opensymphony.xwork2.ActionSupport;
 
public class ConfiguracionDatatableAction extends ActionSupport implements ParameterAware{
	  Map parameters;
	  private ArrayList<Configuracion> aaData = new ArrayList();
	  private ConfiguracionServicio configuracionServicio = new ConfiguracionServicio();
	  private Configuracion row ;
	  private String error;
	
	/**
	 * Lista Sucursales
	 * @return
	 */
	public String listaConfiguraciones() {
		this.setAaData(this.getConfiguracionServicio().listaConfiguracion());
		return SUCCESS;
	}
	
	/**
	 * Configuraciones Operaiones
	 * @return
	 */
	public static void saveImage(String imageUrl, String destinationFile) throws IOException {
 
	}
	public String configuracionesOperaciones() throws IOException {
		//LoginAction.getUsuariosLogueadosTest().remove(0);
		//LoginAction.getUsuariosLogueadosTest().add(new Date());
		String[] accion= (String[]) parameters.get("action");
		

		if (accion[0].equalsIgnoreCase("edit") ){
			Configuracion configuracionlEditar= new Configuracion();


			configuracionlEditar.setDT_RowId(Integer.parseInt(((String[]) parameters.get("id"))[0]));

				configuracionlEditar.setTiempopngjpg(Integer.parseInt(((String[]) parameters.get("data[tiempopngjpg]"))[0]));
				configuracionlEditar.setTiempogif(Integer.parseInt(((String[]) parameters.get("data[tiempogif]"))[0]));
				configuracionlEditar.setDirectorio(((String[]) parameters.get("data[directorio]"))[0]);
				configuracionlEditar.setFeed(((String[]) parameters.get("data[feed]"))[0]);


				this.setRow(this.getConfiguracionServicio().editarConfiguracion(configuracionlEditar));
				this.setError(this.getRow().getError());
				String resultMostrador = "";
				String resultTurneroFondo= "";
				String resultTurneroLogo= "";
				String errorImagenFinal="";
				if (((String[]) parameters.get("data[image]"))[0]!=""){
					resultMostrador=uploadPicture(((String[]) parameters.get("data[image]"))[0],"fondoMostrador.jpg");
					if (resultMostrador=="error"){
						errorImagenFinal="* Imagen Fondo Mostrador";
					}

				}
				if (((String[]) parameters.get("data[imageFondoTurnero]"))[0]!=""){
					resultTurneroFondo=uploadPicture(((String[]) parameters.get("data[imageFondoTurnero]"))[0],"fondoTurnero.jpg");
					if (resultTurneroFondo=="error"){
						errorImagenFinal="*Imagen Fondo Turnero ";
					}

				}
				if (((String[]) parameters.get("data[imageLogoTurnero]"))[0]!=""){
					resultTurneroLogo=uploadPicture(((String[]) parameters.get("data[imageLogoTurnero]"))[0],"logoTurnero.jpg");
					if (resultTurneroLogo=="error"){
						errorImagenFinal="* Imagen Logo Turnero";
					}

				}


				if (errorImagenFinal!=""){
					if(this.getRow().getError()==null){
						this.setError("Datos guardados.Existen errores para subir imagenes con la/las siguientes URL "+errorImagenFinal);

					}else{
						//this.setError("Existen errores para subir imagenes con la/las siguientes URL "+errorImagenFinal);

					}
				}


		}
	
		
		return SUCCESS;
	}
	
	public String uploadPicture(String imageUrl,String fileName){
		
		
		 //String imageUrl = "http://www.avajava.com/images/avajavalogo.jpg";
		// String imageUrl = "C:\\ProyectosAle\\nues.jpg";
		
		 URL url;
		try {
			//Se sube si es URL
			url = new URL(imageUrl);
			 InputStream in = null;
			 
			try {
				in = new BufferedInputStream(url.openStream());
			} catch (IOException e) {
				// TODO Auto-generated catch block
				return "error";
			}
            BufferedImage image = ImageIO.read(new URL(imageUrl));  
            if(image == null){  
                return "error";
            } 
			 ByteArrayOutputStream out = new ByteArrayOutputStream();
			 byte[] buf = new byte[1024];
			 int n = 0;
			 while (-1!=(n=in.read(buf)))
			 {
			    out.write(buf, 0, n);
			 }
			 out.close();
			 in.close();
			 byte[] response = out.toByteArray();
			 
				String archivosFolder = "/archivos/"+fileName;
				String serverFolder = ServletActionContext.getServletContext().getRealPath(archivosFolder);
			 FileOutputStream fos = new FileOutputStream(serverFolder);
			 fos.write(response);
			 fos.close();
		} catch (MalformedURLException e) {
			//Si esta mal formada,se prueba ver si es una direccion local
			String archivosFolder = "/archivos/"+fileName;
			String serverFolder = ServletActionContext.getServletContext().getRealPath(archivosFolder);
			File source = new File(imageUrl);
			File dest = new File(serverFolder);
		    try {
				FileUtils.copyFile(source, dest);
			} catch (IOException e1) {
				// TODO Auto-generated catch block
				e1.printStackTrace();
				return "error";
			}

			// TODO Auto-generated catch block
			//e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		return "ok";
		
		
		
		
		
	}
	public ArrayList<Configuracion> getAaData() {
		return aaData;
	}
	public void setAaData(ArrayList<Configuracion> aaData) {
		this.aaData = aaData;
	}
	public ConfiguracionServicio getConfiguracionServicio() {
		return configuracionServicio;
	}
	public void setConfiguracionServicio(ConfiguracionServicio configuracionServicio) {
		this.configuracionServicio = configuracionServicio;
	}
	public Map getParameters() {
	    return parameters;
	  }

	  public void setParameters(Map parameters) {
	    this.parameters = parameters;
	  }


	public Configuracion getRow() {
		return row;
	}
	public void setRow(Configuracion row) {
		this.row = row;
	}
	public String getError() {
		return error;
	}
	public void setError(String error) {
		this.error = error;
	}

 

}