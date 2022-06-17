package facade.Interface;

import javax.ejb.Remote;

import facade.dto.SpaceDTO;
import facade.wrappers.Record;

@Remote
public interface ICheckOccupationServiceRemote {
	
	public Iterable<Record> checkUseOfSpace(String spaceName, String startDate, String endDate) throws Exception;
	
	public Iterable<SpaceDTO> getSpaces();

}
