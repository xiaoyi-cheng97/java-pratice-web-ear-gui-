package presentation.web.model;


import facade.Interface.IReservationRequestServiceRemote;
import facade.dto.SportDTO;
import facade.exceptions.ApplicationException;

public class NewRequestModel extends Model{

	private String description;
	private String id;
	private String sport;
	private String startDate;
	private String startTime;
	private String endTime;
	private String endDate;
	private String nParticipants;
	private IReservationRequestServiceRemote addRequest;
	
	
	public void setRequestHandler(IReservationRequestServiceRemote addRequest) {
		this.addRequest= addRequest;
	}

	public String getDescription() {
		return description;
	}


	public void setDescription(String description) {
		this.description = description;
	}

	public String getId() {
		return id;
	}

	public void setStartTime(String startTime) {
		this.startTime = startTime;
	}

	public String getStartTime() {
		return startTime;
	}

	public void setEndTime(String endTime) {
		this.endTime = endTime;
	}

	public String getEndTime() {
		return endTime;
	}
	
	public void setId(String id) {
		this.id = id;
	}
	
	public String getSports() {
		return sport;
	}
	public void setSports(String sport) {
		this.sport = sport;
	}

	public String getStartDate() {
		return startDate;
	}


	public void setStartDate(String startDate) {
		this.startDate = startDate;
	}


	public String getEndDate() {
		return endDate;
	}


	public void setEndDate(String endDate) {
		this.endDate = endDate;
	}


	public String getnPartecipante() {
		return nParticipants;
	}


	public void setnPartecipante(String nParticipants) {
		this.nParticipants = nParticipants;
	}

	public void clearFields() {
		description = id = startDate = startTime = endTime = endDate = nParticipants = sport = "";
	}
	
	public Iterable<SportDTO> getListSport() throws ApplicationException{
			
			return addRequest.makeReservation();
	
	}

}
