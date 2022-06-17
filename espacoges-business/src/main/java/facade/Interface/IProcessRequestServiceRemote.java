package facade.Interface;

import javax.ejb.Remote;

import business.requests.exceptions.InvalidRequestException;
import business.sports.Sport;
import business.sports.exceptions.SportNotFoundException;
import facade.exceptions.ApplicationException;
import facade.wrappers.Request;

@Remote
public interface IProcessRequestServiceRemote {
	
	public Iterable<Sport> startProcessing() throws ApplicationException;
	
	public Iterable<Request> filterRequests(String sport, String type) throws ApplicationException, SportNotFoundException, InvalidRequestException;
	
	public void chooseRequest(int number, String spaceName) throws Exception;

}
