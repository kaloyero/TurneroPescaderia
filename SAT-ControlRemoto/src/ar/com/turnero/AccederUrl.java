
package ar.com.turnero;

import java.io.IOException;
import java.net.MalformedURLException;
import java.net.URL;
import java.net.URLConnection;

public class AccederUrl {

	private static final String URL_SIGUIENTE_CONTROL_UNO = "http://"+PropertiesRemoto.getIpLlamador()+"/SAT-Llamador/llamarTurno.action?codigoControl=12";
	private static final String URL_ANTERIOR_CONTROL_UNO = "http://"+PropertiesRemoto.getIpLlamador()+"/SAT-Llamador/llamarTurnoAnterior.action?codigoControl=12";
	private static final String URL_SIGUIENTE_CONTROL_DOS = "http://"+PropertiesRemoto.getIpLlamador()+"/SAT-Llamador/llamarTurno.action?codigoControl=34";
	private static final String URL_ANTERIOR_CONTROL_DOS = "http://"+PropertiesRemoto.getIpLlamador()+"/SAT-Llamador/llamarTurnoAnterior.action?codigoControl=34";
	
	public static void botonSiguienteSectorUno() {
		llamarUrl(URL_SIGUIENTE_CONTROL_UNO);
	}
	public static void botonAnteriorSectorUno() {
		llamarUrl(URL_ANTERIOR_CONTROL_UNO);
	}
	public static void botonSiguienteSectorDos() {
		llamarUrl(URL_SIGUIENTE_CONTROL_DOS);
	}
	public static void botonAnteriorSectorDos() {
		llamarUrl(URL_ANTERIOR_CONTROL_DOS);
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
