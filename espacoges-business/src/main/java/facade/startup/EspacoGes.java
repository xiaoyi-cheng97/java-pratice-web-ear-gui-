package facade.startup;

import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;

import business.handlers.CheckOcupationHandler;
import business.handlers.ProcessRequestsHandler;
import business.handlers.ReservationRequestHandler;
import facade.exceptions.ApplicationException;
import facade.services.CheckOccupationService;
import facade.services.ProcessRequestsService;
import facade.services.ReservationRequestService;

public class EspacoGes {
	
	private ReservationRequestService reservationRequestService;
	private ProcessRequestsService processRequestsService;
	private CheckOccupationService checkOccupationService;
	
	private EntityManagerFactory emf;
	
	public void run() throws ApplicationException {
		// Connects to the database
		try {
			
			reservationRequestService = new ReservationRequestService(new ReservationRequestHandler());
			processRequestsService = new ProcessRequestsService(new ProcessRequestsHandler());
			checkOccupationService = new CheckOccupationService(new CheckOcupationHandler());
			// TODO - initialise other services
			
			// exceptions thrown by JPA are not checked
		} catch (Exception e) {
			throw new ApplicationException("Error connecting database", e);
		}
	}
	
	public void stopRun() {
		// Closes the database connection
		emf.close();
	}
	
	/**
	 * Returns the reservation request service
	 * @return The reservation request service
	 */
	public ReservationRequestService getReservationRequestService() {
		return reservationRequestService;
	}
	
	/**
	 * Returns the process requests service
	 * @return The process requests service
	 */
	public ProcessRequestsService getProcessRequestsService() {
		return processRequestsService;
	}
	
	/**
	 * Returns the check occupation service
	 * @return The check occupation service
	 */
	public CheckOccupationService getCheckOccupationService() {
		return checkOccupationService;
	}
	
}
