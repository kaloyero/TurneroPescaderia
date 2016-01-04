/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package examples;

import giovynet.devices.GIV8DAQ;
import giovynet.devices.ScanDevices;
import giovynet.permissions.Info;
import java.util.List;

/**
 *
 * @author Giovynet.com
 */
public class PortGIV8DAQ {
    private static String  ACTIVATION_KEY_FOR_THE_PORT_GIV8DAQ ="93564-01";
    private static GIV8DAQ giv8daq;
    /**
     * Main method.
     */
    public static void main(String[] args) throws Exception {
        showsInfoAboutGiovynetDriver(); // Shows Information about Giovynet Driver
        ScanDevices scanDevices = new ScanDevices();
        List<GIV8DAQ> lstGIV8DAQ= scanDevices.findGIV8DAQ();//Returns all connected GIV8DAQ
        if(lstGIV8DAQ.size()>0){
            System.out.println("Number of GIV8DAQ instantiated: "+ Info.getNumberOfInstancesGIV8DAQ());
            giv8daq =lstGIV8DAQ.get(0);//Gets the first GIV8DAQ
            if(!giv8daq.isActivated()){//Check the activation of the first GIV8DAQ                
                if(ACTIVATION_KEY_FOR_THE_PORT_GIV8DAQ.equals("")) System.err.println("For this example, you must to assign the activation key in the variable:\nACTIVATION_KEY_FOR_THE_PORT_GIV8DAQ");                                   
                giv8daq.activate(ACTIVATION_KEY_FOR_THE_PORT_GIV8DAQ);//activates the first GIV8DAQ found
            }
            
            
            for (int i = 0; i < 4; i++) {//Sends 8 commands to channel 1
                giv8daq.command(GIV8DAQ.Channel.ch1, GIV8DAQ.DigitalOutput.set_High);
                System.out.println(">>> Sets the voltage for channel 1 to 5VDC.");
                Thread.sleep(1700);//Wait 1.7 seconds

                giv8daq.command(GIV8DAQ.Channel.ch1, GIV8DAQ.DigitalOutput.set_Low);
                System.out.println(">>> Sets the voltage for channel 1 to 0VDC.");
                Thread.sleep(1700);//Wait 1.7 seconds
            }
            
            for(int i = 0; i < 4; i++){//Sends 4 commands to channel 1
                String voltageValue = giv8daq.command(GIV8DAQ.Channel.ch1, GIV8DAQ.Analog.get_Voltage_Measurement );
                System.out.println(">>> Voltage value in channel 1 is: "+ voltageValue);
                Thread.sleep(1700);//Wait 1.7 seconds            
            }

            /************************************************************************
             * TO TEST TEMPERATURE READING YOU MUST CONNECT IN THE CHANNEL 1, 
             * THE SENSOR GIV18B20 (sold separately).
             ************************************************************************/
            giv8daq.command(GIV8DAQ.Channel.ch1, GIV8DAQ.Analog.get_Temperature_Measurement);//First temperature reading is discarded, for details see the Handbook.
            for(int i = 0; i < 4; i++){//Sends 4 commands to channel 1                
                String tempValue = giv8daq.command(GIV8DAQ.Channel.ch1, GIV8DAQ.Analog.get_Temperature_Measurement);
                System.out.println(">>> Temperatute value in channel 1 is: "+ tempValue);
                Thread.sleep(1700);//Wait 1.7 seconds
            }
            
            
        }else{
            System.out.println("No ports GIV8DAQ connected.");        
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
