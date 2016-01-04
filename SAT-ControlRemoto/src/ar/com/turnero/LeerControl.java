package ar.com.turnero;

import java.util.List;

import giovynet.nativelink.SerialPort;
import giovynet.serial.Baud;
import giovynet.serial.Com;
import giovynet.serial.Parameters;

public class LeerControl {

	private static final String OPEN_MESSAGE = "p";
	private static final String CLOSE_MESSAGE = "z";
	private final static String LIBSERIALPORT = "libSerialPort";

	public static void main(String[] args) {
		System.out.println("Ip Llamador: " + PropertiesRemoto.getIpLlamador());
		System.out.println("Puerto configurado: " + PropertiesRemoto.getPuerto());
		
		try {
			System.loadLibrary(LIBSERIALPORT);
			System.out.println("DLL is loaded from memory");
		} catch (UnsatisfiedLinkError e) {
			System.out.println("no leyo la librería");
		}

		System.out.println("EMPIEZA");
		SerialPort free = new SerialPort();
		List<String> portList;
		try {
			portList = free.getFreeSerialPort();

			for (String string : portList) {
				System.out.println("Esta en uso: " + string);
			}
		} catch (Exception e) {
			System.out.println("Ningún puerto en uso");
		}
		Parameters settings;
		try {
			settings = new Parameters();

			settings.setPort(PropertiesRemoto.getPuerto());
			settings.setBaudRate(Baud._9600);

			Com com3 = new Com(settings);
			
			escuchar(com3, 500);

		} catch (Exception e) {
			System.out.println("No se detecto "+PropertiesRemoto.getPuerto());
		}

	}

	private synchronized static void escuchar(Com com3, int tiempoConsulta) throws Exception {
		boolean read = false;
		while (true) {
			Thread.sleep(tiempoConsulta);
			String p = com3.receiveSingleString();
			if (OPEN_MESSAGE.equals(p)) {
				read = true;

			}
			if (read) {
				int dataReceived = com3.receiveSingleCharAsInteger();
				if (dataReceived != 0) {
					pulsar(dataReceived);
				}
			}
			if (CLOSE_MESSAGE.equals(p)) {
				read = false;
			}
		}

	}

	private static synchronized void pulsar(int dataReceived) {
		if (dataReceived == 1) {
			AccederUrl.botonSiguienteSectorUno();
			System.out.println("derecha remoto 1--- " + dataReceived);
		} else if (dataReceived == 2) {
			AccederUrl.botonAnteriorSectorUno();
			System.out.println("izquierda remoto 1--- " + dataReceived);
		}
		if (dataReceived == 3) {
			AccederUrl.botonSiguienteSectorDos();
			System.out.println("derecha remoto 2--- " + dataReceived);
		} else if (dataReceived == 4) {
			AccederUrl.botonAnteriorSectorDos();
			System.out.println("izquierda remoto 2--- " + dataReceived);
		}

	}
}
