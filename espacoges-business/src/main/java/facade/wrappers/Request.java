package facade.wrappers;

import java.util.Date;

/**
 * A class that "wraps" information about a ReservationRequest.
 *
 */
public class Request {
	
	private int requestId;
	private Date timestamp;
	private String period;
	private String description;
	
	/**
	 * Creates a new request wrapper given information about a reservation request
	 * @param requestId The reservation request's id
	 * @param timestamp The reservation request's time stamp
	 * @param period A string representing the requested time frame for the activity
	 * @param description The activity's description
	 */
	public Request(int requestId, Date timestamp, String period, String description) {
		this.requestId = requestId;
		this.timestamp = timestamp;
		this.period = period;
		this.description = description;
	}
	/**
	 * Returns the request's id
	 * @return The request's id
	 */
	public int getRequestId() {
		return requestId;
	}
	
	/**
	 * Returns the request's time stamp
	 * @return The request's time stamp
	 */
	public Date getTimestamp() {
		return timestamp;
	}
	
	/**
	 * Returns a string representing the requested time frame for the activity
	 * @return A string representing the requested time frame for the activity
	 */
	public String getPeriod() {
		return period;
	}
	
	/**
	 * Returns the activity's description
	 * @return The activity's description
	 */
	public String getDescription() {
		return description;
	}
	
}
