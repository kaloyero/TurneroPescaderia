package ar.com.turnero;

import java.io.File;
import java.io.FileInputStream;
import java.io.InputStream;
import java.util.Properties;

public class PropertiesRemoto {

	public static final String KEY_PUERTO = "PUERTO";
	public static final String KEY_IP_LLAMADOR = "IP_LLAMADOR";
	
	private static PropertiesRemoto propertiesDB;
	private static Properties prop;

	protected static PropertiesRemoto getPropertyFile() {

		if (propertiesDB == null) {
			propertiesDB = new PropertiesRemoto();
		}
		return propertiesDB;
	}

	public static String getPropertyValue(String key) {
		return getPropertyFile().getProp().getProperty(key);
	}

	private PropertiesRemoto() {
		try {

			String propertyFolder = new File (".").getCanonicalPath();
//			String serverFolder = ServletActionContext.getServletContext().getRealPath(propertyFolder);
			InputStream is = new FileInputStream(propertyFolder + "/remoto.properties");
			prop = new Properties();
			prop.load(is);

		} catch (Exception e1) {
			e1.printStackTrace();
		}
	}

	private Properties getProp() {
		return prop;
	}
	
	public static String getPuerto() {
		return getPropertyValue(KEY_PUERTO);
	}
	public static String getIpLlamador() {
		return getPropertyValue(KEY_IP_LLAMADOR);
	}	


}
