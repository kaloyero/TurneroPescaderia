package ar.com.osdepym.bean;

import ar.com.osdepym.model.query.ConfigurationQuery;

/**
 * Created by Alejandro on 8/15/14.
 */
public class PeriodBatch {
    private static PeriodBatch period = null;
    private int value;

    private PeriodBatch() {
    	//Obtengo de la base de datos la configuracion
    	getQryPeriodBatch();
    }

    public static PeriodBatch getPeriodBatch() {
        if(period == null) {
        	period = new PeriodBatch();
        }

        return period;
    }

    public void setValue(int periodTime) {
        this.value = periodTime;
    }

    public static int getPeriodValue() {
    	//TODO remover esto
    	getPeriodBatch().getQryPeriodBatch();
        return getPeriodBatch().value;
    }
    
    public static void setPeriodTime(int periodTime) {
        getPeriodBatch().setValue(periodTime);
    }    
    
    private void getQryPeriodBatch(){
    	ConfigurationQuery qry = new ConfigurationQuery();
    	this.value  =qry.getBatchPeriod();
    }
    
}
