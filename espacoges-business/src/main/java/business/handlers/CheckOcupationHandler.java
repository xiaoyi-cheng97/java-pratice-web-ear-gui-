package business.handlers;

import java.util.Date;

import javax.ejb.EJB;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;

import business.activities.exceptions.InvalidTimeFrameException;
import business.spaces.Space;
import business.spaces.SpaceCatalog;
import business.spaces.exceptions.SpaceNotFoundException;
import business.utils.DateUtils;
import business.utils.InvalidDateFormatException;
import facade.exceptions.ApplicationException;
import facade.wrappers.Record;

/**
 * Handles the check occupation use case
 *
 */
@Stateless
public class CheckOcupationHandler {
	
	
	@EJB
	SpaceCatalog spaceCatalog;

	/**
	 * Creates a handler for the check occupation use case given
	 * the application's entity manager factory
	 * @param emf The entity manager factory of the application
	 */
	public CheckOcupationHandler() {
	
	}
	
	public Iterable<Space> getSpaces(){
		return spaceCatalog.getSpaces();
					
			
	}
	
	/**
	 * Given a space name checks that:
	 * 			- the space exists
	 * 			- the dates are valid
	 * and returns an iterable of all 'wrapped' records existing for given space in the period defined
	 * between given dates
	 * @param spaceName The name of the space
	 * @param startDate A string representing the beginning of the period
	 * @param endDate A string representing the end of the period
	 * @return An iterable of all 'wrapped' records existing for given space in the period defined 
	 * between given dates 
	 * @throws ApplicationException If there was an error checking the space's availability in the 
	 * given interval
	 * @throws Exception 
	 */
	public Iterable<Record> checkUseOfSpace(String spaceName, String startDate, String endDate) 
			throws ApplicationException, Exception {
		
		
			
			// validate space name
			Space space = spaceCatalog.getSpaceByName(spaceName);
			
			// validate dates
			Date start = DateUtils.parse(startDate);
			Date end = DateUtils.parse(endDate);
			
			if (!start.before(end)) {
				throw new InvalidTimeFrameException("Start date must be before end date.");
			}
			
			// get records
			Iterable<Record> records = spaceCatalog.getRecordsIn(space, start, end);
			
			// end transaction
			
			return records;
			
		
	}

}
