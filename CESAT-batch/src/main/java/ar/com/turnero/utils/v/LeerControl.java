package ar.com.turnero.utils.v;

import java.util.List;

import giovynet.nativelink.SerialPort;
import giovynet.serial.Baud;
import giovynet.serial.Com;
import giovynet.serial.Parameters;

public class LeerControl {

	public LeerControl() {
		System.out.println("hol");
		escucharControl();
	}

	public static void main(String[] args)throws Exception{
		    	escucharControl();
		    }

	public static void escucharControl() {
		try {
			// looks for free serial ports
			SerialPort free = new SerialPort();
			List<String> portList;

			portList = free.getFreeSerialPort();

			for (String string : portList) {
				System.out.println(string);
			}
			// Settings parameters COM1.
			Parameters settings = new Parameters();
			// settings.setPort("COM1");
			// settings.setBaudRate("9600");
			// Instance COM1.
			// Com com1 = new Com(settings);
			// Settings parameters COM·.
			settings.setPort("COM3");
			settings.setBaudRate(Baud._9600);
			// settings.setByteSize("8");
			// settings.setParity("None");
			// Instance COM3.
			Com com3 = new Com(settings);
			// Write COM1.
			// for (int i = 0; i < 4; i++) {
			// System.out.println(">>Send G");
			// com1.sendSingleData('G');
			// }
			// Read COM3
			boolean read = false;
			while (true) {
				// for (int i = 0; i < 124; i++) {

				Thread.sleep(500);
				String p = com3.receiveSingleString();
				if ("p".equals(p)) {
					read = true;

				}
				if (read) {
					// System.out.println("<<Receive
					// "+com3.receiveSingleString());
					// System.out.println("<<Receive
					// "+com3.readDataInt(com3.receiveSingleString()));
					int dataReceived = com3.receiveSingleCharAsInteger();
					if (dataReceived != 0) {
						pulsar(dataReceived);
					}
					// switch (dataReceived) {
					// case 1:
					// System.out.println("izquierda remoto 1");
					// break;
					// case 2:
					// System.out.println("derecha remoto 1");
					// break;
					// case 3:
					// System.out.println("izquierda remoto 2");
					// break;
					// case 4:
					// System.out.println("derecha remoto 2");
					// break;
					// default:
					// break;
					// }

					// System.out.println("<<Receive "+dataReceived);
				}
				if ("z".equals(p)) {
					read = false;

				}
			}
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

	}

	private synchronized void escuchar(int dataReceived) {

	}

	private static synchronized void pulsar(int dataReceived) {
		System.out.println("hola--- " + dataReceived);
		if (dataReceived == 1) {
			System.out.println("dataReceived--- " + dataReceived);
			// System.out.println("izquierda remoto 1");
		} else if (dataReceived == 2) {
			System.out.println("dataReceived--- " + dataReceived);
			// System.out.println("derecha remoto 1");
		}
		if (dataReceived == 3) {
			System.out.println("dataReceived--- " + dataReceived);
			// System.out.println("izquierda remoto 2");
		} else if (dataReceived == 4) {
			System.out.println("dataReceived--- " + dataReceived);
			// System.out.println("derecha remoto 2");
		}

	}
}
