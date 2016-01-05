package ar.com.turnero;

import java.util.Scanner;

public class LeerControlDummy {

	private static final int CTRL_1_BOTON_1 = 1;
	private static final int CTRL_1_BOTON_2 = 2;
	private static final int CTRL_2_BOTON_3 = 3;
	private static final int CTRL_2_BOTON_4 = 4;

	public static void main(String[] args)  {
		
			escuchar();
	}

	private synchronized static void escuchar()  {
		Scanner leer=new Scanner(System.in); 
		String letra = "";
		while ( ! letra.equals("s") ) {
			System.out.println("CONTROL 1:  Siguiente(1) o Anterior(2) \nCONTROL 2: Siguiente(1) o Anterior(2)  \nSALIR= 's'1"); 
			letra=leer.nextLine(); 
			if ( ! letra.equals("s")){
				int data = Integer.parseInt(letra);
				pulsar(data);
			}
		}

	}

	private static synchronized void pulsar(int dataReceived) {
		if (dataReceived == CTRL_1_BOTON_1) {
			AccederUrl.botonSiguienteSectorUno();
			System.out.println("Siguiente remoto 1--- " + dataReceived);
		} else if (dataReceived == CTRL_1_BOTON_2) {
			AccederUrl.botonAnteriorSectorUno();
			System.out.println("Anterior remoto 1--- " + dataReceived);
		}
		if (dataReceived == CTRL_2_BOTON_3) {
			AccederUrl.botonSiguienteSectorDos();
			System.out.println("Siguiente remoto 2--- " + dataReceived);
		} else if (dataReceived == CTRL_2_BOTON_4) {
			AccederUrl.botonAnteriorSectorDos();
			System.out.println("Siguiente remoto 2--- " + dataReceived);
		}

	}
}
