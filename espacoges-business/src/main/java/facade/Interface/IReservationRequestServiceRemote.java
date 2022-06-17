package facade.Interface;

import javax.ejb.Remote;

import business.sports.exceptions.SportNotFoundException;
import facade.dto.SportDTO;
import facade.exceptions.ApplicationException;

@Remote
public interface IReservationRequestServiceRemote {

	
	public Iterable<SportDTO> makeReservation() throws ApplicationException;
	
	public void giveId(int id) throws Exception;
	
	public void chooseSport(String sportName) throws ApplicationException, SportNotFoundException;
	
	public void createReservation(String description, String startDate, String endDate, 
			int nParticipants) throws Exception;
}
