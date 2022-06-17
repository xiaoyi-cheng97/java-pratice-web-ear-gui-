package business.spaces;

import java.time.LocalTime;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.TemporalType;
import javax.persistence.TypedQuery;
import javax.transaction.Transactional;

import business.spaces.exceptions.SpaceNotFoundException;
import business.sports.Sport;
import business.utils.DateUtils;
import facade.wrappers.Record;

/**
 * A catalog of spaces
 */
@Stateless
public class SpaceCatalog {
	
	/**
	 * Entity manager for accessing the persistence service 
	 */
	private EntityManager em;
	
	/**
	 * Constructs a space catalog given an entity manager
	 */
	public SpaceCatalog() {
	}
	
	/**
	 * Finds a space given its id
	 * @param id The space's id
	 * @return The space with given id
	 * @throws SpaceNotFoundException When the space does not exists.
	 */
	public Space getSpace(int spaceId) throws SpaceNotFoundException {
		try {
			TypedQuery<Space> query = em.createNamedQuery(Space.FIND_BY_ID, Space.class);
			query.setParameter(Space.SPACE_ID, spaceId);
			return query.getSingleResult();
		} catch (Exception e) {
			throw new SpaceNotFoundException("Space with id " + spaceId + " does not exist");
		}
	}
	
	
	public Iterable<Space> getSpaces() {
		try {
			TypedQuery<Space> query = em.createNamedQuery(Space.GET_ALL_NAMES, Space.class);
			return query.getResultList();
		} catch (Exception e) {
			return new ArrayList<>(); 
		}	
	}
	
	/**
	 * Finds a space given its name
	 * @param space The name of the space
	 * @return The space with given name
	 * @throws SpaceNotFoundException When the space does not exists.
	 */
	public Space getSpaceByName(String spaceName) throws SpaceNotFoundException {
		try {
			TypedQuery<Space> query = em.createNamedQuery(Space.FIND_BY_NAME, Space.class);
			query.setParameter(Space.SPACE_NAME, spaceName);
			return query.getSingleResult();
		} catch (Exception e) {
			throw new SpaceNotFoundException("Space with name " + spaceName + " does not exist", e);
		}
	}
	
	/**
	 * Adds a new space
	 * @param name The name of the space
	 * @param maxCapacity The maximum capacity of the space
	 * @param price The price per hour of using the space
	 * @param startTimeWeekdays The opening time on weekdays
	 * @param endTimeWeekdays The closing time on weekdays
	 * @param startTimeWeekends The opening time on weekends
	 * @param endTimeWeekends The closing time on weekends
	 */
	@Transactional
	public void addSpace(String name, int maxCapacity, double price, Date startTimeWeekdays, Date endTimeWeekdays,
			Date startTimeWeekends, Date endTimeWeekends) {
		Space space = new Space(name, maxCapacity, price, startTimeWeekdays, endTimeWeekdays, startTimeWeekends, endTimeWeekends);
		em.persist(space);
	}

	/**
	 * Finds all occupation records in given space between given dates ordered by date, and returns them
	 * in an iterable of a wrapper object called Record
	 * @param space The space to search for the records
	 * @param start The start date of the search period
	 * @param end The end date of the search period
	 * @return An iterable of all records between given dates ordered by date
	 */
	public Iterable<Record> getRecordsIn(Space space, Date start, Date end) {
		
		List<Record> result = new ArrayList<>();
		try {
			TypedQuery<OccupationRecord> query = em.createNamedQuery(OccupationRecord.FIND_ALL_BETWEEN_DATES, OccupationRecord.class);
			query.setParameter(OccupationRecord.START_DATE, start, TemporalType.DATE);
			query.setParameter(OccupationRecord.END_DATE, end, TemporalType.DATE);
			query.setParameter(OccupationRecord.SPACE_NAME, space);
			//query.setParameter(OccupationRecord.START_TIME, startTime, TemporalType.TIME);
			//query.setParameter(OccupationRecord.END_TIME, endTime, TemporalType.TIME);
			Iterable<OccupationRecord> records = query.getResultList();
			LocalTime startTime = DateUtils.toLocalTime(start);
			LocalTime endTime = DateUtils.toLocalTime(start);
			
			for (OccupationRecord rec : records) {
				if(rec.isBetween(startTime, endTime))
					result.add(rec.getRecord());
     		}
			
			return result;
			
		} catch (Exception e) {
			return result;
		}
	}
	
}
