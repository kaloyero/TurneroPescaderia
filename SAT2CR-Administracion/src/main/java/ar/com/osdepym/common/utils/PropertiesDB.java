package ar.com.osdepym.common.utils;

import java.io.FileInputStream;
import java.io.InputStream;
import java.util.Properties;

import org.apache.struts2.ServletActionContext;

public class PropertiesDB {

	public static final String KEY_SERVER_NAME = "SERVER_NAME";
	public static final String KEY_PORT_NUMBER = "PORT_NUMBER";
	public static final String KEY_USER_NAME = "USER_NAME";
	public static final String KEY_PASSWORD = "PASSWORD";
	public static final String KEY_DB_NAME = "DB_NAME";

	
	private static PropertiesDB propertiesDB;
	private static Properties prop;

	protected static PropertiesDB getPropertyFile() {

		if (propertiesDB == null) {
			propertiesDB = new PropertiesDB();
		}
		return propertiesDB;
	}

	public static String getPropertyValue(String key) {
		return getPropertyFile().getProp().getProperty(key);
	}

	private PropertiesDB() {
		try {
			String propertyFolder = "/properties";
			String serverFolder = ServletActionContext.getServletContext().getRealPath(propertyFolder);
			InputStream is = new FileInputStream(serverFolder+"/dbProperties.properties");
			prop = new Properties();
			prop.load(is);

		} catch (Exception e1) {
			e1.printStackTrace();
		}
	}

	private Properties getProp() {
		return prop;
	}
	
	public static String getServerName() {
		return getPropertyValue(KEY_SERVER_NAME);
	}
	
	public static String getPortNumber() {
		return getPropertyValue(KEY_PORT_NUMBER);
	}
	public static String getUserName() {
		return getPropertyValue(KEY_USER_NAME);
	}
	public static String getPassword() {
		return getPropertyValue(KEY_PASSWORD);
	}
	public static String getDbName() {
		return getPropertyValue(KEY_DB_NAME);
	}

}
