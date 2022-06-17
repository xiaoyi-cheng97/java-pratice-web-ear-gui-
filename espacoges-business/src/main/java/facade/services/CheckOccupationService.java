package facade.services;

import java.util.LinkedList;
import java.util.List;

import javax.ejb.EJB;
import javax.ejb.Stateless;

import business.handlers.CheckOcupationHandler;
import business.sports.Sport;
import facade.Interface.ICheckOccupationServiceRemote;
import facade.dto.SpaceDTO;
import facade.dto.SportDTO;
import facade.exceptions.ApplicationException;
import facade.wrappers.Record;


/**
 * A service that offers operations to check the occupation of a space.
 * The purpose of this class is to provide access to the business layer, hiding its
 * implementation from the clients
 *
 */
@Stateless
public class CheckOccupationService implements ICheckOccupationServiceRemote {

	/**
	 * The business object facade that handles the use case check occupation
	 */
	@EJB
	private CheckOcupationHandler checkOccupationHandler;
	
	
	
	/**
	 * Given a space name, checks if space exists, and returns list of wrappers for
	 * its occupation records, for the period between startDate and endDate
	 * @param spaceName The name of the space
	 * @param startDate String representing the starting date of the period to check
	 * @param endDate String representing the end date of the period to check
	 * @return A list of wrappers for the space occupation records
	 * @throws Exception 
	 */
	public Iterable<Record> checkUseOfSpace(String spaceName, String startDate, String endDate) throws Exception {
		return checkOccupationHandler.checkUseOfSpace(spaceName, startDate, endDate);
	}

	public Iterable<SpaceDTO> getSpaces(){
		List<SpaceDTO> list = new LinkedList<>();
		
		checkOccupationHandler.getSpaces().forEach(s -> list.add(new SpaceDTO(s.getId(), s.getName(), s.getMaxCapacity(), s.getPrice(), s.getStartTimeWeekdays(), s.getEndTimeWeekdays(), s.getStartTimeWeekends(), s.getEndTimeWeekends(), s.getAllowedSport(), s.getRecord())));
		return list;
	}
}
