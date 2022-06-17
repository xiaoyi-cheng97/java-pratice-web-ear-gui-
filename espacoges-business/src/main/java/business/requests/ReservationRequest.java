package business.requests;

import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.OneToOne;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

import business.activities.Activity;
import business.activities.TimeFrame;
import business.clients.Client;
import business.sports.Sport;
import static javax.persistence.CascadeType.ALL;

/**
 * A class representing a reservation request
 *
 */
@Entity
@NamedQueries({
	@NamedQuery(name=ReservationRequest.FIND_BY_ID, 
			    query="SELECT r FROM ReservationRequest r WHERE r.id = :" + ReservationRequest.REQUEST_ID),
	@NamedQuery(name=ReservationRequest.FIND_BY_SPORT_AND_TYPE, 
	            query="SELECT r FROM ReservationRequest r WHERE r.activity.sport.name = :" + ReservationRequest.SPORT_NAME + " and r.status = :" + ReservationRequest.REQUEST_STATUS +  
					  " ORDER BY r.timestamp"),
	@NamedQuery(name=ReservationRequest.GET_ALL_TYPES,
				query="SELECT DISTINCT TYPE(r.activity.client) FROM ReservationRequest r"),
})
public class ReservationRequest {
	
	// Named query name constants
	
	public static final String FIND_BY_ID = "ReservationRequest.findById";
	public static final String REQUEST_ID = "id";
	
	public static final String FIND_BY_SPORT_AND_TYPE = "ReservationRequest.findBySportAndType";
	public static final String CLIENT_TYPE = "type";
	public static final String SPORT_NAME = "sport";
	public static final String REQUEST_STATUS = "status";
	
	public static final String GET_ALL_TYPES = "ReservationRequest.getAllTypes";
	
	/**
	 * Sale primary key. Needed by JPA.
	 */
	@Id @GeneratedValue(strategy = GenerationType.AUTO)
	private int id;
	
	@Enumerated(EnumType.STRING)
	private RequestStatus status;
	
	@Temporal(TemporalType.TIMESTAMP)
	private Date timestamp;
	
	@Column(nullable = false)
	private long reservedHours;
	
	@OneToOne(cascade = ALL)
	private Activity activity;
	

	/**
	 * Constructor needed by JPA.
	 */
	ReservationRequest() {
	}
	
	/**
	 * Creates a reservation request given its data
	 * @param reservedHours The amount of hours requested to be reserved
	 */
	public ReservationRequest(long reservedHours) {
		this.status = RequestStatus.OPEN;
		this.timestamp = new Date();
		this.reservedHours = reservedHours;
	}
	
	/**
	 * Creates an activity given its data
	 * @param description The description of the activity
	 * @param nParticipants The number of participants
	 * @param sport The sport that will be played
	 * @param period The time frame in which the activity will take place
	 * @param client The client that requested the activity
	 */
	public void createActivity(String description, int nParticipants, Sport sport, TimeFrame period, Client client) {
		activity = new Activity(description, nParticipants, sport, period, client);
	}
	
	/**
	 * Returns the request's id
	 * @return The request's id
	 */
	public int getId() {
		return id;
	}

	/** 
	 * Returns the reservation request's type
	 * @return The reservation request's type
	 */
	public String getType() {
		return activity.getClient().getType();
	}
	
	/**
	 * Returns the reservation request's sport name
	 * @return The reservation request's sport name
	 */
	public String getSport() {
		return activity.getSport();
	}
	
	/**
	 * Returns the reservation request's time stamp
	 * @return The reservation request's time stamp
	 */
	public Date getTimestamp() {
		return timestamp;
	}
	
	/**
	 * Returns the reservation request's amount of reserved hours
	 * @return The reservation request's amount of reserved hours
	 */
	public long getReservedHours() {
		return reservedHours;
	}
	
	/** 
	 * Returns true if the reservation request is pending
	 * @return True if the reservation request is pending
	 */
	public boolean isPending() {
		return status == RequestStatus.OPEN;
	}
	
	/**
	 * Returns the reservation request's activity description
	 * @return The reservation request's activity description
	 */
	public String getDescription() {
		return activity.getDescription();
	}
	
	/**
	 * Returns the reservation request's activity
	 * @return The reservation request's activity
	 */
	public Activity getActivity() {
		return activity;
	}
	
	/**
	 * Returns the reservation request's activity's time frame
	 * @return The reservation request's activity's time frame
	 */
	public TimeFrame getPeriod() {
		return activity.getPeriod();
	}
	
	/**
	 * Closes the reservation request, by changing its status to CLOSED
	 */
	public void close() {
		status = RequestStatus.CLOSED;
	}

}
