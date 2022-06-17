package business.handlers;


import java.util.Date;

import javax.ejb.EJB;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;

import business.activities.TimeFrame;
import business.activities.exceptions.InsufficientParticipantsException;
import business.activities.exceptions.InsufficientTimeException;
import business.activities.exceptions.InvalidTimeFrameException;
import business.clients.Client;
import business.clients.ClientCatalog;
import business.clients.Club;
import business.clients.Individual;
import business.clients.exceptions.InvalidClientException;
import business.requests.RequestCatalog;
import business.sports.Sport;
import business.sports.SportCatalog;
import business.sports.exceptions.SportNotFoundException;
import business.utils.DateUtils;
import facade.exceptions.ApplicationException;

/**
 * Handles the make reservation request use case.
 *
 */
@Stateless
public class ReservationRequestHandler {
	
	
	@EJB
	SportCatalog sportCatalog;
	
	@EJB
	ClientCatalog clientCatalog;
	
	@EJB
	RequestCatalog requestCatalog;
	
	/**
	 * Entity manager factory for accessing the persistence service 
	 */
	
	
	private Client client;
	private Sport sport;

	/**
	 * Creates a handler for the make reservation request use case given
	 * the application's entity manager factory
	 * 
	 * @param emf The entity manager factory of the application
	 */
	public ReservationRequestHandler() {
		
	}

	/**
	 * Starts reservation process and returns a list with the names of all existing sports
	 * @return A list with the names of all existing sports
	 * @throws ApplicationException When there is an error retrieving the sports from the database
	 */
	public Iterable<Sport> makeReservation() throws ApplicationException {

		
		
			
			return sportCatalog.getSports();
			
		
		
	}
	
	/** 
	 * Receives a client id and checks the following:
	 * 		- the client exists
	 * 		- the client's type is valid
	 *      - the client (if an individual client) is an adult
	 * @param number The client's id
	 * @throws ApplicationException When the client is invalid or when there's an error from the database
	 * @throws InvalidClientException 
	 */
	public void giveId(int id) throws ApplicationException, Exception {
	
			// checks that client is registered
			client = clientCatalog.getClient(id);
			// checks that client type is valid
			if (!(client instanceof Individual) && !(client instanceof Club)) {
				throw new InvalidClientException("Client type is invalid.");
			}
			// checks that client (if an individual client) is an adult
			if (client instanceof Individual && !((Individual)client).isAdult()) {
				throw new InvalidClientException("Client is underage.");
			}
		
	}

	/**
	 * Receives a sport name and checks if it is valid
	 * @param sportName The name of the sport
	 * @throws ApplicationException When the sport is invalid or where there's an error from the database
	 * @throws SportNotFoundException 
	 */
	public void chooseSport(String sportName) throws ApplicationException, SportNotFoundException {
		
			sport = sportCatalog.getSportByName(sportName);

	
		
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
	 * @throws ApplicationException When the data is invalid or when there is an error from the database
	 * @throws Exception
	 */
	public void createReservation(String description, String startDate, String endDate, 
			int nParticipants) throws Exception {
		
			sport = sportCatalog.refresh(sport);
			
			// checks that number of participants is valid
			if (nParticipants < sport.getMinParticipants()) {
				throw new InsufficientParticipantsException("Number of participants is insufficient for chosen sport");
			}
			
			Date start = DateUtils.parse(startDate);
			Date end = DateUtils.parse(endDate);
			
			// check that start date is earlier than end date
			if (!start.before(end)) {
				throw new InvalidTimeFrameException("Start date must be before end date.");
			}
			
			// check if minutes comply with sport minimum
			if (DateUtils.minutesBetween(start, end) < sport.getMinMinutes()) {
				throw new InsufficientTimeException("Time interval must be superior to " + sport.getMinMinutes());
			}
			
			// check that interval is appropriate for client type
			if ((client instanceof Individual && !DateUtils.isSameDay(start, end)) || (client instanceof Club && DateUtils.hoursBetween(start, end) <= 24)) {
				throw new InvalidTimeFrameException("Time frame is invalid for user type " + client.getType());
			}
			
			// create reservation request and add it to catalog
			TimeFrame period = new TimeFrame(start, end);
			requestCatalog.addRequest(DateUtils.hoursBetween(start, end), description, nParticipants, sport, period, client);
			
			
		
	}
}
