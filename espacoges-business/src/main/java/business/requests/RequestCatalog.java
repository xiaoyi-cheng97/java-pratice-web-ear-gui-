package business.requests;

import java.util.ArrayList;
import java.util.List;

import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.TypedQuery;
import javax.transaction.Transactional;

import business.activities.TimeFrame;
import business.clients.Client;
import business.clients.Club;
import business.clients.Individual;
import business.requests.exceptions.RequestNotFoundException;
import business.sports.Sport;
import facade.wrappers.Request;

/**
 * A catalog of ReservationRequests
 */
@Stateless
public class RequestCatalog {
	
	/**
	 * Entity manager for accessing the persistence service 
	 */
	@PersistenceContext
	private EntityManager em;
	
	/**
	 * Constructs request catalog given an entity manager
	 * @param em The entity manager
	 */
	public RequestCatalog() {
		
	}

	/**
	 * Finds the reservation request given its id
	 * @param requestId The reservation request id
	 * @return The reservation request with given id
	 * @throws RequestNotFoundException When the reservation request does not exist 
	 */
	public ReservationRequest getRequest(int requestId) throws RequestNotFoundException {
		try {
			TypedQuery<ReservationRequest> query = em.createNamedQuery(ReservationRequest.FIND_BY_ID, ReservationRequest.class);
			query.setParameter(ReservationRequest.REQUEST_ID, requestId);
			return query.getSingleResult();
		} catch (Exception e) {
			throw new RequestNotFoundException("Request with id " + requestId + " does not exist.", e);
		}
	}
	
	/**
	 * Finds all requests with the sport and type given ordered by their time stamp, and returns them
	 * in an iterable of a wrapper object called Request
	 * @param type The reservation request type
	 * @param sport The name of the sport
	 * @return An iterable of all requests with given sport and type, ordered by their time stamp
	 * @requires type.equals("Individual") || type.equals("Club")
	 */
	public Iterable<Request> getAllRequestsBy(String type, String sport) {
		
		List<Request> result = new ArrayList<>();
		try {
			TypedQuery<ReservationRequest> query = em.createNamedQuery(ReservationRequest.FIND_BY_SPORT_AND_TYPE, ReservationRequest.class);
			if (type.equals("Individual")) {
				query.setParameter(ReservationRequest.CLIENT_TYPE, Individual.class);
			} 
			else {
				query.setParameter(ReservationRequest.CLIENT_TYPE, Club.class);
			}
			query.setParameter(ReservationRequest.SPORT_NAME, sport);
			query.setParameter(ReservationRequest.REQUEST_STATUS, RequestStatus.OPEN);
		    List<ReservationRequest> requests = query.getResultList();
			
			for (ReservationRequest req : requests) {
				result.add(new Request(req.getId(), req.getTimestamp(), req.getPeriod().toString(), req.getDescription()));
			}
			return result;
		} catch (Exception e) {
			return result;
		}
	}
	
	/**
	 * Adds a new reservation request
	 * @param reservedHours The amount of reserved hours
	 * @param description The description of the activity
	 * @param nParticipants The number of participants
	 * @param sport The sport that will be played 
	 * @param period The time frame in which the activity will take place
	 * @param client The client that requested this activity
	 */
	@Transactional
	public void addRequest(long reservedHours, String description, int nParticipants, Sport sport, TimeFrame period, Client client) {
		ReservationRequest request = new ReservationRequest(reservedHours);
		request.createActivity(description, nParticipants, sport, period, client);
		em.persist(request);
	}
	
	/**
	 * Given a string, checks if its a valid reservation request type
	 * @param type The string with the client type
	 * @return true When the client type is valid
	 */
	public boolean isValidType(String type) {
		return type.equals("Individual") || type.equals("Club");
		//return getAllTypes().contains(type);
	}
	
	/**
	 * Returns all client types currently existing in the database
	 * Note that if a client type is valid but has no instance in the database it will not be
	 * returned.
	 * @return A list with all client types currently existing in the database
	 */
//	private List<String> getAllTypes() {
//		try {
//			TypedQuery<String> query = em.createNamedQuery(ReservationRequest.GET_ALL_TYPES, String.class);
//		    return query.getResultList();
//		} catch (Exception e) {
//			return new ArrayList<>();
//		}
//	}

}
