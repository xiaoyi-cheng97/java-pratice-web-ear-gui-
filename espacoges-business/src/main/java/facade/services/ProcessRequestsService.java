package facade.services;

import javax.ejb.EJB;
import javax.ejb.Stateless;

import business.handlers.ProcessRequestsHandler;
import business.requests.exceptions.InvalidRequestException;
import business.sports.Sport;
import business.sports.exceptions.SportNotFoundException;
import facade.Interface.IProcessRequestServiceRemote;
import facade.dto.SportDTO;
import facade.exceptions.ApplicationException;
import facade.wrappers.Request;

/**
 * A service that offers operations that enable the processing of reservation requests
 * The purpose of this class is to provide access to the business layer, hiding its
 * implementation from the clients
 *
 */
@Stateless
public class ProcessRequestsService implements IProcessRequestServiceRemote{

	/**
	 * The business object facade that handles the use case process request
	 */
	@EJB
	private ProcessRequestsHandler processRequestsHandler;

	
	
	/**
	 * Starts processing requests and returns a list with the names of all existing sports
	 * @return A list with the names of all existing sports
	 * @throws ApplicationException When there is an error retrieving the sports from the database
	 */
	public Iterable<Sport> startProcessing() throws ApplicationException {
		
		
		return processRequestsHandler.startProcessing();
	}
	
	/**
	 * Receives a sport and a request type. Checks if sport and client type are valid
	 * Returns list of all requests with that sport and type
	 * @param sport The name of the sport
	 * @param type The client type
	 * @return A list of all requests with the given sport and client type
	 * @throws ApplicationException When the given sport or client type are invalid or when there's an error from the database
	 * @throws InvalidRequestException 
	 * @throws SportNotFoundException 
	 */
	public Iterable<Request> filterRequests(String sport, String type) throws ApplicationException, SportNotFoundException, InvalidRequestException {
		return processRequestsHandler.filterRequests(sport, type);
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
	 * @throws Exception 
	 */
	public void chooseRequest(int number, String spaceName) throws Exception {
		processRequestsHandler.chooseRequest(number, spaceName);
	}
}
