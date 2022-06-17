package facade.wrappers;

/**
 * A class that "wraps" information about an OccupationRecord
 *
 */
public class Record {
	
	private String clientName;
	private String clubStatus;
	private String sport;
	private String description;
	private String period;
	private String date;
	private String startTime;
	private String endTime;
	
	/**
	 * Creates a new record wrapper given strings representing the data from an occupation record
	 * @param clientName The name of the client
	 * @param clubStatus The status of the club (if it's an individual then should be an empty string)
	 * @param sport The sport associated with the record's activity
	 * @param description The activity's description
	 * @param period The time frame in which the activity occurs
	 */
	public Record(String clientName, String clubStatus, String sport, String description, 
			String period, String date, String startTime, String endTime) {
		this.clientName = clientName;
		this.clubStatus = clubStatus;
		this.sport = sport;
		this.description = description;
		this.period = period;
		this.date = date;
		this.startTime = startTime;
		this.endTime = endTime;
	}

	/**
	 * Returns the name of the client
	 * @return The name of the client
	 */
	public String getClientName() {
		return clientName;
	}

	/**
	 * Returns the status of the client if it's a club, an empty string if it's an individual
	 * @return The status of the client if it's a club, an empty string if it's an individual
	 */
	public String getClubStatus() {
		return clubStatus;
	}

	/**
	 * Returns the name of the sport 
	 * @return The name of the sport
	 */
	public String getSport() {
		return sport;
	}

	/**
	 * Returns the activity's description
	 * @return The activity's description
	 */
	public String getDescription() {
		return description;
	}

	/**
	 * Returns the time frame in which the activity occurs
	 * @return The time frame in which the activity occurs
	 */
	public String getPeriod() {
		return period;
	}

	/**
	 * @return the endTime
	 */
	public String getEndTime() {
		return endTime;
	}

	/**
	 * @param date the date to set
	 */
	public String getDate() {
		return this.date;
	}

	/**
	 * @param startTime the startTime to set
	 */
	public String getStartTime() {
		return this.startTime;
	}

}
