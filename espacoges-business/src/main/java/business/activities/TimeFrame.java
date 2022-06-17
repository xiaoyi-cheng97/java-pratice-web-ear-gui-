package business.activities;

import java.time.LocalDate;
import java.time.LocalTime;
import java.util.Date;

import javax.persistence.Embeddable;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

import business.utils.DateUtils;
import javax.persistence.Column;

/**
 * A class representing a period of time, including a start date, 
 * start time, end date and end time
 */
@Embeddable
public class TimeFrame {
	
	/**
	 * The start date with time
	 */
	@Temporal(TemporalType.TIMESTAMP) @Column(nullable = false)
	private Date start;
	
	
	/**
	 * The end date with time
	 */
	@Temporal(TemporalType.TIMESTAMP) @Column(nullable = false)
	private Date end;
	
	/**
	 * Constructor needed by JPA.
	 */
	TimeFrame() {
	}

	/**
	 * Creates a new time frame given its a start and end dates (with times)
	 * @param start The start date with time
	 * @param end The end date with time
	 */
	public TimeFrame(Date start, Date end) {
		this.start = start;
		this.end = end;
	}
	
	/**
	 * Returns the start date of the activity
	 * @return the start date of the activity
	 */
	public LocalDate getStartDate() {
		return DateUtils.toLocalDate(start);
	}
	
	/**
	 * Returns the end date of the activity
	 * @return the end date of the activity
	 */
	public LocalDate getEndDate() {
		return DateUtils.toLocalDate(end);
	}
	
	/**
	 * Returns the start time of the activity
	 * @return the start time of the activity
	 */
	public LocalTime getStartTime() {
		return DateUtils.toLocalTime(start);
	}
	
	/**
	 * Returns the end time of the activity
	 * @return the end time of the activity
	 */
	public LocalTime getEndTime() {
		return DateUtils.toLocalTime(end);
	}
	
	@Override
	public String toString() {
		return getStartDate().toString() + " " + getStartTime().toString() 
									     + " - " + getEndDate().toString() 
									     + " " + getEndTime().toString();
	}
	
}
