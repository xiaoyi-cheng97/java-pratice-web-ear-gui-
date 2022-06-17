package business.sports;

import java.util.ArrayList;

import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.TypedQuery;
import javax.transaction.Transactional;

import business.sports.exceptions.SportNotFoundException;

/**
 * A catalog of sports 
 *
 */
@Stateless
public class SportCatalog {
	
	/**
	 * Entity manager for accessing the persistence service 
	 */
	@PersistenceContext
	private EntityManager em;
	
	/**
	 * Constructs a sport catalog given an entity manager
	 * @param em The entity manager
	 */
	public SportCatalog() {
	}
	
	/**
	 * Finds a sport given its id
	 * @param sportId The id of the sport
	 * @return The sport with given id
	 * @throws SportNotFoundException When the sport doesn't exist.
	 */
	public Sport getSport(int sportId) throws SportNotFoundException {
		try {
			TypedQuery<Sport> query = em.createNamedQuery(Sport.FIND_BY_ID, Sport.class);
			query.setParameter(Sport.SPORT_ID, sportId);
			return query.getSingleResult();
		} catch (Exception e) {
			throw new SportNotFoundException("Sport with id " + sportId + " does not exist.", e);
		}
		
	}
	
	/**
	 * Finds a sport given its name
	 * @param sportName The name of the sport
	 * @return The sport with given name
	 * @throws SportNotFoundException When the sport doesn't exist.
	 */
	public Sport getSportByName(String sportName) throws SportNotFoundException {
		try {
			TypedQuery<Sport> query = em.createNamedQuery(Sport.FIND_BY_NAME, Sport.class);
			query.setParameter(Sport.SPORT_NAME, sportName);
			return query.getSingleResult();
		} catch (Exception e) {
			throw new SportNotFoundException("Sport with name " + sportName + " does not exist.", e);
		}
		
	}
	
	/**
	 * Returns an iterable with all existing sport names. If no sports are found an empty list
	 * is returned.
	 * @return An iterable with all existing sport names.
	 */
	public Iterable<Sport> getSports() {
		try {
			TypedQuery<Sport> query = em.createNamedQuery(Sport.GET_ALL_NAMES, Sport.class);
			return query.getResultList();
		} catch (Exception e) {
			return new ArrayList<>(); 
		}	
	}
	
	
	/**
	 * Adds a new sport
	 * @param name The name of the sport
	 * @param minParticipants The sport's minimum participants
	 * @param minMinutes The sport's minimum minutes 
	 * @param sportType The type of the sport
	 */
	@Transactional
	public void addSport(String name, int minParticipants, int minMinutes, SportType sportType) {
		Sport sport = new Sport(name, minParticipants, minMinutes, sportType);
		em.persist(sport);
	}
	
	/**
	 * Updates the sport object (in case it was modified in the database)
	 * @param sport The sport to refresh
	 * @return The sport object obtained with a find
	 */
	
	public Sport refresh(Sport sport) {
		return em.find(Sport.class, sport.getId());
	}

}
