package presentation.web.model;


import facade.Interface.ICheckOccupationServiceRemote;
import facade.dto.SpaceDTO;


public class NewCheckOcupationModel extends Model {
	
	private String space;
	private String startDate;
	private String startTime;
	private String endDate;
	private String endTime;
	private ICheckOccupationServiceRemote ocupationHandler;
	
	public NewCheckOcupationModel() {
	}

	public void setOcupationHandler(ICheckOccupationServiceRemote ocupationHandler) {
		this.ocupationHandler = ocupationHandler;
	}
	
	public void setSpace(String space) {
		this.space = space;
	}
	
	public String getSpace () {
		return this.space;
	}
	
	public void setStartDate (String startDate) {
		this.startDate = startDate;
	}
	
	public String getStartDate () {
		return this.startDate;
	}
	
	public void setEndDate (String endDate) {
		this.endDate = endDate;
	}
	
	public String getEndDate () {
		return this.endDate;
	}
	
	public void setStartTime (String startTime) {
		this.startTime = startTime;
	}
	
	public String getStartTime () {
		return this.startTime;
	}
	
	public void setEndTime (String endTime) {
		this.endTime = endTime;
	}
	
	public String getEndTime () {
		return this.endTime;
	}
	
	public Iterable<SpaceDTO> getSpaces(){
	
		return this.ocupationHandler.getSpaces();
	}
	
	public void clearFields() {
		space = startDate = startTime = endDate = endTime = "";
	}

}
