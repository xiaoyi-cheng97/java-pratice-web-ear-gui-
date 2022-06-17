package business.requests.exceptions;

/**
 * An exception that indicates that a reservation request does not exist in the system.
 *
 */
public class RequestNotFoundException extends Exception {
	
	/**
	 * The serial version id (generated automatically by Eclipse)
	 */
	private static final long serialVersionUID = -7611931558646194836L;

	/**
	 * Creates an exception given an error message
	 * @param message The error message
	 */
	public RequestNotFoundException(String message) {
		super(message);
	}
	
	/**
	 * Creates an exception wrapping a lower level exception
	 * @param message The error message
	 * @param e		  The wrapped exception
	 */
	public RequestNotFoundException(String message, Exception e) {
		super(message, e);
	}

}
