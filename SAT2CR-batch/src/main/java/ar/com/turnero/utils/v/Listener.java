package ar.com.turnero.utils.v;

import java.util.List;

import javax.servlet.ServletContext;
import javax.servlet.ServletContextEvent;

import giovynet.nativelink.SerialPort;
import giovynet.serial.Baud;
import giovynet.serial.Com;
import giovynet.serial.Parameters;

public class Listener implements javax.servlet.ServletContextListener {

		   public void contextInitialized(ServletContext context) {
			   System.out.println("hola1");
		   
		   }

		@Override
		public void contextInitialized(ServletContextEvent sce) {
//			LeerControl c = new LeerControl();
			
		}

	    private synchronized void escuchar(int  dataReceived){
	    	
	    	
	    }
	    
	    private synchronized void pulsar(int  dataReceived){
	    	System.out.println("hola--- " +dataReceived);
	    	if (dataReceived == 1){
	    		System.out.println("dataReceived--- " +dataReceived);
//            	System.out.println("izquierda remoto 1");
            } else if (dataReceived == 2){
            	System.out.println("dataReceived--- " +dataReceived);
//            	System.out.println("derecha remoto 1");
            } if (dataReceived == 3){
            	System.out.println("dataReceived--- " +dataReceived);
//            	System.out.println("izquierda remoto 2");
            } else if (dataReceived == 4){
            	System.out.println("dataReceived--- " +dataReceived);
//            	System.out.println("derecha remoto 2");
            }
	    	
	    }
		
		@Override
		public void contextDestroyed(ServletContextEvent sce) {
			   System.out.println("hola3");
		}
}
