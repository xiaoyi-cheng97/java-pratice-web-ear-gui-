package business.spaces;

import java.time.LocalDate;
import java.time.LocalTime;
import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

import business.activities.Activity;
import business.clients.Client;
import business.clients.Club;
import business.utils.DateUtils;
import facade.wrappers.Record;

/**
 * A class representing an occupation record
 *
 */
@Entity
@NamedQueries({
	@NamedQuery(name=OccupationRecord.FIND_ALL_BETWEEN_DATES, 
			    query="SELECT o FROM OccupationRecord o WHERE o.date BETWEEN :" + OccupationRecord.START_DATE 
			    	+ " AND :" + OccupationRecord.END_DATE + " and o.space = :" + OccupationRecord.SPACE_NAME + " ORDER BY o.date"),
})
//@NamedQueries({
//	@NamedQuery(name=OccupationRecord.FIND_BY_DATE, query="SELECT o FROM OccupationRecord o WHERE o.date = :" 
//             + OccupationRecord.RECORD_DATE + " and o.startTime > :" + OccupationRecord.RECORD_START_TIME
//})
public class OccupationRecord {
	
	// Named query name constants

	public static final String FIND_ALL_BETWEEN_DATES = "Space.findAllBetweenDates";
	
	public static final String START_DATE = "startDate";
	public static final String END_DATE = "endDate";
	
	public static final String SPACE_NAME = "spaceName"; 
	
//	public static final String START_TIME = "startTime";
//	public static final String END_TIME = "endTime";

		
//	public static final String FIND_BY_DATE = "Space.findByDate";
//	public static final String RECORD_DATE = "date";
//	public static final String RECORD_START_TIME = "startTime";
//	public static final String RECORD_END_TIME = "endTime";
	
	/**
	 * OccupationRecord primary key. Needed by JPA.
	 */
	@Id @GeneratedValue(strategy = GenerationType.AUTO)
	private int id;
	
	/**
	 * The date of the occupation record
	 */
	@Temporal(TemporalType.DATE) @Column(nullable = false)
	private Date date;
	
	/**
	 * The start time of the occupation record
	 */
	@Temporal(TemporalType.TIME) @Column(nullable = false)
	private Date startTime;
	
	/**
	 * The end time of the occupation record
	 */
	@Temporal(TemporalType.TIME) @Column(nullable = false)
	private Date endTime;
	
	@ManyToOne
	private Space space;
	
	/**
	 * The activity occupying the space in this record
	 */
	@ManyToOne
	private Activity activity;
	
	/**
	 * Constructor needed by JPA.
	 */
	OccupationRecord() {
	}	
	
	/**
	 * Creates an occupation record given its data
	 * @param date The date of the occupation record
	 * @param startTime The start time of the occupation record
	 * @param endTime The end time of the occupation record
	 * @param activity The activity occupying the space in this record
	 */
	public OccupationRecord(LocalDate date, LocalTime startTime, LocalTime endTime, Activity activity, Space space) {
		this.date = DateUtils.toDate(date);
		this.startTime = DateUtils.toDate(startTime);
		this.endTime = DateUtils.toDate(endTime);
		this.activity = activity;
		this.space = space;
	}
	
	/**
	 * Returns the date of the occupation record
	 * @return The date of the occupation record
	 */
	public LocalDate getDate() {
		return DateUtils.toLocalDate(date);
	}
	
	/**
	 * Returns the start time at which the space was occupied 
	 * @return The start time at which the space was occupied 
	 */
	public LocalTime getStartTime() {
		return DateUtils.toLocalTime(startTime);
	}
	
	/**
	 * The end time of the occupation record
	 * @return The ending time of the record
	 */
	public LocalTime getEndTime() {
		return DateUtils.toLocalTime(endTime);
	}
	
	/**
	 * Checks if given date is the same as this record's date
	 * @param other The other date
	 * @return True if they have the same date, false otherwise
	 */
	public boolean isSameDay(LocalDate other) {
		return getDate().equals(other);
		
	}
	
	/**
	 * Checks if this record's time interval does not violate availability of the space in given time interval
	 * @param start Starting time for the interval
	 * @param end Ending time for the interval
	 * @return True if this record's time interval does not violate availability of the space in given time interval, false otherwise
	 */
	public boolean isFree(LocalTime start, LocalTime end) {
		return getEndTime().isBefore(start) || getStartTime().isAfter(end);
	}

//	/**
//	 * Checks if this record's time interval overlaps the given time interval
//	 * @param period The period representing the time interval
//	 * @return True if this record's time interval overlaps the given time interval, false otherwise
//	 */
//	public boolean isBetween(TimeFrame period) {
//		if (getDate().isAfter(period.getStartDate()) && getDate().isBefore(period.getEndDate())) {
//			return getEndTime().isAfter(period.getStartTime()) || getStartTime().isBefore(period.getEndTime());
//		}
//		return false;
//	}
	
	/**
	 * Checks if this record's time interval overlaps the given time interval
	 * @param period The period representing the time interval
	 * @return True if this record's time interval overlaps the given time interval, false otherwise
	 */
	public boolean isBetween(LocalTime start, LocalTime end) {
		return getEndTime().isAfter(start) || getStartTime().isBefore(end);
	}
	
	/**
	 * Returns a wrapper object representing this record
	 * @return A wrapper object representing this record
	 */
	public Record getRecord() {
		Client c = activity.getClient();
		if (c instanceof Club) {
			Club club = (Club) c;
			return new Record(c.getName(), club.getStatus(), activity.getSport(), activity.getDescription(), activity.getPeriod().toString(),
					getDate().toString(), getStartTime().toString(), getEndTime().toString() );
		}
		else {
			return new Record(c.getName(), "", activity.getSport(), activity.getDescription(), activity.getPeriod().toString(),
					getDate().toString(), getStartTime().toString(), getEndTime().toString() );
		}
	}
	
}
