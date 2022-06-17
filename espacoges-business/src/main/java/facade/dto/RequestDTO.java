package facade.dto;

import static javax.persistence.CascadeType.ALL;

import java.io.Serializable;
import java.util.Date;

import javax.persistence.Column;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.OneToOne;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

import business.activities.Activity;
import business.requests.RequestStatus;

public class RequestDTO implements Serializable{

	/**
	 * 
	 */
	private static final long serialVersionUID = 9163570083825152011L;

	private int requestId;
	private Date timestamp;
	private String period;
	private String description;
	
	public RequestDTO(int requestId, Date timestamp, String period, String description) {
	
		this.requestId = requestId;
		this.timestamp = timestamp;
		this.period = period;
		this.description = description;
	}

	public int getRequestId() {
		return requestId;
	}

	public Date getTimestamp() {
		return timestamp;
	}

	public String getPeriod() {
		return period;
	}

	public String getDescription() {
		return description;
	}
	
	
	
	
}
