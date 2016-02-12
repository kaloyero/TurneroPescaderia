package ar.com.ruggemar.scheduler;

import org.quartz.Job;
import org.quartz.JobExecutionContext;
import org.quartz.JobExecutionException;
import org.springframework.http.ResponseEntity;
import org.springframework.web.client.RestTemplate;

import ar.com.turnero.utils.v.LeerControl2;

public class SchedulerJob implements Job {

	// Tarea que consulta el control remoto
	@Override
	public void execute(JobExecutionContext context) throws JobExecutionException {
		try {

			System.out.println("Consulto Control remoto");

			consultaControlRemoto();

			boolean read = false;
			while (true) {
				String p = LeerControl2.getPuerto().receiveSingleString();
				if ("p".equals(p)) {
					read = true;

				}
				if (read) {
					int dataReceived = LeerControl2.getPuerto().receiveSingleCharAsInteger();
					if (dataReceived != 0) {
						pulsar(dataReceived);
					}
				}
				if ("z".equals(p)) {
					read = false;

				}
			}
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
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

	private void consultaControlRemoto() {

		/*
		 * logica de control remoto
		 * 
		 * Reglas: 1 .Analizo si se presiono botton "122" 1.1Si se envió señal:
		 * 1.1.0 Identifico id de control remoto (logica dura) 1.1.1 analiso si
		 * es impar es boton siguiente 1.1.2 si es par botón anterior
		 * 
		 */

	}

	private void botonSiguiente(int idControl) throws Exception {
		System.out.println("Boton siguiente");
		// llamo a la url que invoca el turno siguiente
		RestTemplate restTemplate = new RestTemplate();
		ResponseEntity<String> response = restTemplate
				.getForEntity("http://localhost:8080/SAT2CR-Llamador/llamarTurno.action", String.class);
		// restTemplate.getForObject(PROTOCOLO+suc.getIP()+PROYECTO+SERVICIO+"?id="+idUltimoTurnoSuc,
		// TurnoEstadistica[].class);

	}

	private void botonAnterior(int idControl) throws Exception {
		System.out.println("Boton Anterior");
		// llamo a la url que invoca el turno anterior
		RestTemplate restTemplate = new RestTemplate();
		ResponseEntity<String> response = restTemplate
				.getForEntity("http://localhost:8080/SAT2CR-Llamador/llamarTurno.action", String.class);
	}

	/*
	 * APAGAR BATCH
	 * 
	 * @SuppressWarnings("unchecked") private void validarRepeticionTrabajo
	 * (JobExecutionContext context){ // Obtengo el triguer viejo Trigger
	 * oldTrigger = context.getTrigger();
	 * 
	 * // Obtengo como fue configurado TriggerBuilder tb =
	 * oldTrigger.getTriggerBuilder();
	 * 
	 * // Actualizo el periodo int periodTime = PeriodBatch.getPeriodValue();
	 * Trigger newTrigger =
	 * tb.withSchedule(SimpleScheduleBuilder.simpleSchedule()
	 * .withIntervalInSeconds(periodTime ).repeatForever()).build();
	 * 
	 * Scheduler scheduler = context.getScheduler();
	 * 
	 * 
	 * try {
	 * 
	 * //reconfiguro el trabajo con el nuevo periodo
	 * scheduler.rescheduleJob(oldTrigger.getKey(), newTrigger); } catch
	 * (SchedulerException e) { //Si no se actualiza imprime una excepciòn
	 * System.out.println("ERROR AL ACTUALIZAR EL PEriodo: " + e.getMessage());
	 * }
	 * 
	 * //lo inyecto en el contexto context.put("scheduler", scheduler); }
	 */

	/*
	 * PRENDER BATCH
	 * 
	 * @SuppressWarnings("unchecked") private void validarRepeticionTrabajo
	 * (JobExecutionContext context){ // Obtengo el triguer viejo Trigger
	 * oldTrigger = context.getTrigger();
	 * 
	 * // Obtengo como fue configurado TriggerBuilder tb =
	 * oldTrigger.getTriggerBuilder();
	 * 
	 * // Actualizo el periodo int periodTime = PeriodBatch.getPeriodValue();
	 * Trigger newTrigger =
	 * tb.withSchedule(SimpleScheduleBuilder.simpleSchedule()
	 * .withIntervalInSeconds(periodTime ).repeatForever()).build();
	 * 
	 * Scheduler scheduler = context.getScheduler();
	 * 
	 * 
	 * try {
	 * 
	 * //reconfiguro el trabajo con el nuevo periodo
	 * scheduler.rescheduleJob(oldTrigger.getKey(), newTrigger); } catch
	 * (SchedulerException e) { //Si no se actualiza imprime una excepciòn
	 * System.out.println("ERROR AL ACTUALIZAR EL PEriodo: " + e.getMessage());
	 * }
	 * 
	 * //lo inyecto en el contexto context.put("scheduler", scheduler); }
	 */
}
