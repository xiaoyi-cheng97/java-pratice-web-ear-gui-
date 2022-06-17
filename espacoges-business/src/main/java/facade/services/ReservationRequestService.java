package facade.services;

import java.util.LinkedList;
import java.util.List;

import javax.ejb.EJB;
import javax.ejb.Stateless;

import business.handlers.ReservationRequestHandler;
import business.sports.exceptions.SportNotFoundException;
import facade.Interface.IReservationRequestServiceRemote;
import facade.dto.SportDTO;
import facade.exceptions.ApplicationException;

/**
 * A service that offers operations to make a reservation request.
 * The purpose of this class is to provide access to the business layer, hiding its
 * implementation from the clients.
 *
 */

@Stateless
public class ReservationRequestService implements IReservationRequestServiceRemote {
	
	/**
	 * The business object facade that handles the use case reservation request
	 */
	@EJB
	private ReservationRequestHandler reservationRequestHandler;
	
	
	
	/**
	 * Starts reservation process and returns a list with the names of all existing sports
	 * @return A list with the names of all existing sports
	 * @throws ApplicationException When there is an error retrieving the sports from the database.
	 */
	public Iterable<SportDTO> makeReservation() throws ApplicationException {
		List<SportDTO> list = new LinkedList<>();
				
		reservationRequestHandler.makeReservation().forEach(s -> list.add(new SportDTO(s.getId(), s.getName(), s.getMinParticipants(), s.getMinMinutes(), s.getSportType())));
		return list;
	}
	
	/** 
	 * Receives a client id and checks the following:
	 * 		- the client exists
	 * 		- the client's type is valid
	 *      - the client (if an individual client) is an adult
	 * @param number The client's id
	 * @throws Exception 
	 */
	public void giveId(int id) throws Exception {
		reservationRequestHandler.giveId(id);
	}
	
	/**
	 * Receives a sport name and checks if it is valid
	 * @param sportName The name of the sport
	 * @throws ApplicationException When the sport is invalid or where there's an error from the database
	 * @throws SportNotFoundException 
	 */
	public void chooseSport(String sportName) throws ApplicationException, SportNotFoundException {
		reservationRequestHandler.chooseSport(sportName);
	}
	
	/**
	 * Receives data about the reservation request and checks that:
	 * 	   -  the number of participants comply with the sport's minimum
	 *     -  the dates are valid
	 *     -  the amount of minutes comply with the sport's minimum
	 *     -  the time interval is appropriate for the client type
	 * and creates a new reservation request, persisting it.
	 * @param description The description of the activity
	 * @param startDate A string representing the start date of the activity
	 * @param endDate A string representing the end date of the activity
	 * @param nParticipants The number of participants
	 * @throws Exception 
	 */
	public void createReservation(String description, String startDate, String endDate, 
			int nParticipants) throws Exception {
		reservationRequestHandler.createReservation(description, startDate, endDate, nParticipants);
	}
}