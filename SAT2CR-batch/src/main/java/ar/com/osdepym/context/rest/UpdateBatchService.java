package ar.com.osdepym.context.rest;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import ar.com.osdepym.bean.PeriodBatch;

@RestController
public class UpdateBatchService {
	
	@RequestMapping("/updatePeriodBatch")
	public String getEstadisticasTurnero(@RequestParam(value = "min",required = false,
	                                                    defaultValue = "60") Integer minutos) {
		
		PeriodBatch.setPeriodTime(minutos);
		return "OK";
	}
}