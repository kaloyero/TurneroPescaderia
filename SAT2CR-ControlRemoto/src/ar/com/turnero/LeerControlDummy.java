package ar.com.turnero;

import java.util.Scanner;

public class LeerControlDummy {

	public static void main(String[] args)  {
		
			escuchar();
	}

	private synchronized static void escuchar()  {
		Scanner leer=new Scanner(System.in); 
		String letra = "";
		while ( ! letra.equals("s") ) {
			System.out.println("Presione un boton del control remoto:  Ejem Siguiente(1) o Anterior(2) \nSALIR= 's'1"); 
			letra=leer.nextLine(); 
			if ( ! letra.equals("s")){
				int data = Integer.parseInt(letra);
				pulsar(data);
			}
		}

	}

	private static synchronized void pulsar(int dataReceived) {
		System.out.println("PRESIONA BOTON--- " + dataReceived);
		AccederUrl.llamarTurno(dataReceived);

	}
}
