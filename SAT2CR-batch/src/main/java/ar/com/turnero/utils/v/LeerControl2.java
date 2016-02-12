package ar.com.turnero.utils.v;

import giovynet.serial.Baud;
import giovynet.serial.Com;
import giovynet.serial.Parameters;

public class LeerControl2 {
	private static LeerControl2 control;
	Com com3 ;

	public static LeerControl2 getConfigurador() {

		if (control == null) {
			control = new LeerControl2();
		}
		return control;
	}

	public static Com getPuerto() {

		return getConfigurador().com3;
	}
	
	private LeerControl2() {
		try {
			Parameters settings = new Parameters();
			settings.setPort("COM3");
			settings.setBaudRate(Baud._9600);
			Com com3 = new Com(settings);

		} catch (Exception e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}
	}

}