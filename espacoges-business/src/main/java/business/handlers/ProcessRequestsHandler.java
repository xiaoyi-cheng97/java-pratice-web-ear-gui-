package business.handlers;

import javax.ejb.EJB;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;

import business.activities.Activity;
import business.clients.Client;
import business.clients.Club;
import business.requests.RequestCatalog;
import business.requests.ReservationRequest;
import business.requests.exceptions.InvalidRequestException;
import business.requests.exceptions.RequestNotFoundException;
import business.reservations.ReservationCatalog;
import business.spaces.Space;
import business.spaces.SpaceCatalog;
import business.spaces.exceptions.UnavailableSpaceException;
import business.sports.Sport;
import business.sports.SportCatalog;
import business.sports.exceptions.InvalidSportException;
import business.sports.exceptions.SportNotFoundException;
import facade.exceptions.ApplicationException;
import facade.wrappers.Request;

/**
 * Handles the process requests use case
 *
 */
@Stateless
public class ProcessRequestsHandler {
	
	/**
	 * Entity manager factory for accessing the persistence service 
	 */
	@EJB
	SportCatalog sportCatalog;
	
	@EJB
	RequestCatalog requestCatalog;
	
	@EJB
	ReservationCatalog resCatalog;
	
	@EJB
	SpaceCatalog spaceCatalog;
	/**
	 * Creates a handler for the process requests use case given
	 * the application's entity manager factory
	 * @param emf The entity manager factory of the application
	 */
	public ProcessRequestsHandler() {
		
	}
	
	/**
	 * Starts processing requests and returns a list with the names of all existing sports
	 * @return A list with the names of all existing sports
	 * @throws ApplicationException When there is an error retrieving the sports from the database
	 */
	public Iterable<Sport> startProcessing() throws ApplicationException {
		
	
			return sportCatalog.getSports();
		
			
		
	}
	
	/**
	 * Receives a sport and a request type and checks that:
	 * 			- the sport is valid
	 * 			- the client type is valid
	 * and returns an iterable of all 'wrapped' requests with given sport and type.
	 * @param sport The name of the sport
	 * @param type The client type
	 * @return A list of all requests with the given sport and client type
	 * @throws ApplicationException When the given sport or client type are invalid or when there's 
	 * an error from the database
	 * @throws SportNotFoundException 
	 * @throws InvalidRequestException 
	 */
	public Iterable<Request> filterRequests(String sport, String type) throws ApplicationException, SportNotFoundException, InvalidRequestException {
	
	
			sportCatalog.getSportByName(sport);
			if (!requestCatalog.isValidType(type)) {
				
					throw new InvalidRequestException("Type " + type + " is not a valid request type.");
				
			}
			Iterable<Request> requests = requestCatalog.getAllRequestsBy(type, sport);
		
			return requests;
		
	}
	
	/**
	 * Receives a request id and the name of a space and checks that:
	 * 			- the request id represents an existing pending request
	 * 			- the space with the given name exists
	 * 			- the space allows for the practice of the requested sport
	 * 			- the space is free in the requested period
	 * Also does the following:
	 * 			- creates the occupation records for the requested activity
	 *          - updates the user's status if it is a club
	 *          - creates a reservation for the given space and activity
	 *          - persists the reservation & the occupation records
	 *         
	 * @param requestId The request's id
	 * @param spaceName The name of the space
	 * @throws ApplicationException //if there was an error selecting a request with the given number and space
	 * @throws Exception 
	 */
	public void chooseRequest(int requestId, String spaceName) throws ApplicationException, Exception {
		
		
		
		

			
			// validate request id
			ReservationRequest request = requestCatalog.getRequest(requestId);
			
			// check that request is pending
			if (!request.isPending()) {
				throw new InvalidRequestException("Request " + requestId + " is already closed.");
			}
			
			// validate space name
			Space space = spaceCatalog.getSpaceByName(spaceName);
			
			// check if sport is allowed
			String sportName = request.getSport();
			if (!space.allowsSport(sportName)) {
				throw new InvalidSportException("Sport " + sportName + " not allowed in this space.");
			}
			
			// check if space is free in period
			if (!space.isFree(request.getPeriod(), request.getType())) {
				throw new UnavailableSpaceException("Space " + spaceName + " is unavailable in the requested period.");
			}
			
			// create records & persist them
			Activity activity = request.getActivity();
			space.createRecords(activity, request.getType());
			
			// update club status
			Client client = activity.getClient();
			if (client instanceof Club) {
				Club club = (Club) client;
				club.addHours(request.getReservedHours());
				club.updateStatus();
			
			}
		
			// close request
			request.close();
			
			
			// create reservation & persist it
			resCatalog.addReservation(request, space);
			
			// end transaction
			
	}

}
