
package ar.com.turnero;

import java.io.IOException;
import java.net.MalformedURLException;
import java.net.URL;
import java.net.URLConnection;

public class AccederUrl {
	private static final String PROYECTO_LLAMADOR = "SAT2CR-Llamador";
	private static final String URL_LLAMAR_TURNO = "http://"+PropertiesRemoto.getIpLlamador()+"/"+PROYECTO_LLAMADOR+"/llamarTurno.action?codigoControl=";
	
	public static void llamarTurno(int boton) {
		//concateno el boton a la url a llamar
		llamarUrl(URL_LLAMAR_TURNO + boton);
	}

	private static void llamarUrl(String urlPath) {
		try {

		  URL url = new URL(urlPath);
	      URLConnection con = url.openConnection();
	      con.connect();
	      con.getInputStream();

		} catch (MalformedURLException e) {
			
		} catch (IOException e) {
			
		}
	}
}
