package business.reservations;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.OneToOne;

import business.requests.ReservationRequest;
import business.spaces.Space;

/**
 * A class representing the reservation, created after its request is processed.
 */
@Entity

@NamedQueries({
	@NamedQuery(name=Reservation.FIND_BY_ID, query="SELECT r FROM Reservation r WHERE r.id = :" + Reservation.RESERVATION_ID),
})
public class Reservation {
	
	public static final String FIND_BY_ID = "Reservation.findById";
	public static final String RESERVATION_ID = "id";
	
	/**
	 * Reservation primary key. Needed by JPA.
	 */
	@Id @GeneratedValue(strategy = GenerationType.AUTO)
	private int id;
	
	/**
	 * The approved reservation request
	 */
	@OneToOne
	private ReservationRequest request;
	
	/**
	 * The reserved space
	 */
	@ManyToOne
	private Space space;
	
	/**
	 * Constructor needed by JPA.
	 */
	Reservation() {
	}
	
	/**
	 * Creates a new reservation given the approved reservation request and reserved space
	 * @param request The approved reservation request
	 * @param space The reserved space
	 */
	public Reservation(ReservationRequest request, Space space) {
		this.request = request;
		this.space = space;
	}

	/**
	 * Returns the approved reservation request
	 * @return The approved reservation request
	 */
	public ReservationRequest getRequest() {
		return request;
	}

	/**
	 * Returns the reserved space
	 * @return The reserved space
	 */
	public Space getSpace() {
		return space;
	}	

}
