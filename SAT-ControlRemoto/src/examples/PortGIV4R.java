/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package examples;

import giovynet.devices.GIV4R;
import giovynet.devices.GIV4R.Connect;
import giovynet.devices.ScanDevices;
import giovynet.permissions.Info;
import java.util.List;

/**
 *
 * @author Giovynet.com
 */
public class PortGIV4R {

    private static String  ACTIVATION_KEY_FOR_THE_PORT_GIV4R ="93564-01";
    private static GIV4R giv4r;
    /**
     * Main method.
     */
    public static void main(String[] args) throws Exception {
        showsInfoAboutGiovynetDriver(); // Shows Information about Giovynet Driver
        ScanDevices scanDevices = new ScanDevices();
        List<GIV4R> lstGIV4R= scanDevices.findGIV4R();//Returns all connected GIV4R
        if(lstGIV4R.size()>0){
            System.out.println("Number of GIV4R instantiated: "+ Info.getNumberOfInstancesGIV4R());
            giv4r =lstGIV4R.get(0);//Gets the first GIV4R
            if(!giv4r.isActivated()){//Check the activation of the first GIV4R                
                if(ACTIVATION_KEY_FOR_THE_PORT_GIV4R.equals("")) System.err.println("For this example, you must to assign the activation key in the variable:\nACTIVATION_KEY_FOR_THE_PORT_GIV4R");                                   
                giv4r.activate(ACTIVATION_KEY_FOR_THE_PORT_GIV4R);//activates the first GIV4R found
            }
            
            for(int i=0; i<4; i++){//Sends 8 commands to relay 1
                System.out.println("...Relay 1 is connected to terminal A...");
                giv4r.command(GIV4R.Relay._1, Connect.to_A);
                Thread.sleep(1700);//Wait 1.7 seconds
                System.out.println("...Relay 1 is connected to terminal B...");
                giv4r.command(GIV4R.Relay._1, Connect.to_B);
                Thread.sleep(1700);//Wait 1.7 seconds
            }
        }else{
            System.out.println("No ports GIV4R connected.");        
        }
    
    }
    
    public static void showsInfoAboutGiovynetDriver(){
        System.out.println("------------------------------------------------------------------------------->");
        System.out.println("Giovynet Driver version "+Info.getVersion());
        System.out.println("Type of distribution: "+Info.getTypeOfDistribution());
        System.out.println("Number of devices allowed: "+Info.getNumDevicesAllowed());
        System.out.println("Distribution permissions: "+Info.getDistributionLicense());
        System.out.println("<-------------------------------------------------------------------------------");
    }
   
    
}
