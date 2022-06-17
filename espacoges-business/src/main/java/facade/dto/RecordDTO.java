package facade.dto;

import java.io.Serializable;

public class RecordDTO implements Serializable{

	/**
	 * 
	 */
	private static final long serialVersionUID = 6046752688026334412L;

	
	private String clientName;
	private String clubStatus;
	private String sport;
	private String description;
	private String period;
	private String date;
	private String startTime;
	private String endTime;
	public RecordDTO(String clientName, String clubStatus, String sport, String description, String period, String date,
			String startTime, String endTime) {
		super();
		this.clientName = clientName;
		this.clubStatus = clubStatus;
		this.sport = sport;
		this.description = description;
		this.period = period;
		this.date = date;
		this.startTime = startTime;
		this.endTime = endTime;
	}
	public String getClientName() {
		return clientName;
	}
	public String getClubStatus() {
		return clubStatus;
	}
	public String getSport() {
		return sport;
	}
	public String getDescription() {
		return description;
	}
	public String getPeriod() {
		return period;
	}
	public String getDate() {
		return date;
	}
	public String getStartTime() {
		return startTime;
	}
	public String getEndTime() {
		return endTime;
	}
	
}
