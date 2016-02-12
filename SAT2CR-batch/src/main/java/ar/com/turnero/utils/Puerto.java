//package ar.com.turnero.utils;
//
//public class Puerto {
//
//	public void searchForPorts() {
//		System.out.println("Puertos Disponibles:");
//		ports = CommPortIdentifier.getPortIdentifiers();
//		while (ports.hasMoreElements()) {
//			CommPortIdentifier curPort = (CommPortIdentifier) ports.nextElement();
//
//			if (curPort.getPortType() == CommPortIdentifier.PORT_SERIAL) {
//				System.out.println(curPort.getName());
//				portMap.put(curPort.getName(), curPort);
//			}
//		}
//		System.out.println("----------------------");
//	}
//
//	public void connect() {
//		String puerto = "COM4";
//		selectedPortIdentifier = (CommPortIdentifier) portMap.get(puerto);
//
//		CommPort commPort = null;
//
//		try {
//			commPort = selectedPortIdentifier.open("Send Sms Java", TIMEOUT);
//			serialPort = (SerialPort) commPort;
//			setSerialPortParameters();
////			connected = true;
//
//			System.out.println("conectado exitosamente a puerto " + puerto);
//		} catch (PortInUseException e) {
//			System.out.println("Puerto en uso.");
//		} catch (Exception e) {
//			System.out.println("Error al abrir puerto.");
//		}
//	}
//
//}
