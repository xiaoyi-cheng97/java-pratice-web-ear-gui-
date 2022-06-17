package business.reservations;

import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.TypedQuery;
import javax.transaction.Transactional;

import business.requests.ReservationRequest;
import business.reservations.exceptions.ReservationNotFoundException;
import business.spaces.Space;

/**
 * A catalog of reservations
 *
 */
@Stateless
public class ReservationCatalog {
	
	/**
	 * Entity manager for accessing the persistence service 
	 */
	@PersistenceContext
	private EntityManager em;
	
	/**
	 * Constructs a sport catalog given an entity manager
	 * @param em The entity manager
	 */
	public ReservationCatalog() {

	}
	
	/**
	 * Finds a reservation given its id
	 * @param resId The id of the reservation
	 * @return The reservation with given id
	 * @throws ReservationNotFoundException When the reservation doesn't exist.
	 */
	public Reservation getReservation(int resId) throws ReservationNotFoundException {
		try {
			TypedQuery<Reservation> query = em.createNamedQuery(Reservation.FIND_BY_ID, Reservation.class);
			query.setParameter(Reservation.RESERVATION_ID, resId);
			return query.getSingleResult();
		} catch (Exception e) {
			throw new ReservationNotFoundException("Reservation with id " + resId + " does not exist.", e);
		}
		
	}
	
	/**
	 * Adds a new reservation
	 * @param request The approved reservation request
	 * @param space The reserved space
	 */
	@Transactional
	public void addReservation(ReservationRequest request, Space space) {
		Reservation res = new Reservation(request, space);
		em.persist(res);
	}

}
