package business.reservations.exceptions;


/**
 * An exception that indicates that a reservation does not exist in the system.
 * 
 */
public class ReservationNotFoundException extends Exception {
	
	/**
	 * The serial version id (generated automatically by Eclipse)
	 */
	private static final long serialVersionUID = 261260000862336500L;

	/**
	 * Creates an exception given an error message
	 * @param message The error message
	 */
	public ReservationNotFoundException(String message) {
		super(message);
	}
	
	/**
	 * Creates an exception wrapping a lower level exception
	 * @param message The error message
	 * @param e		  The wrapped exception
	 */
	public ReservationNotFoundException(String message, Exception e) {
		super(message, e);
	}

}
