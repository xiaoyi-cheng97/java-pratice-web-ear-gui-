package business.clients.exceptions;

/**
 * An exception that indicates a generic error in validating a client.
 *
 */
public class InvalidClientException extends Exception {
	
	/**
	 * The serial version id (generated automatically by Eclipse)
	 */
	private static final long serialVersionUID = -6427603092468852610L;

	/**
	 * Creates an exception given an error message
	 * @param message The error message
	 */
	public InvalidClientException(String message) {
		super(message);
	}
	
	/**
	 * Creates an exception wrapping a lower level exception
	 * @param message The error message
	 * @param e		  The wrapped exception
	 */
	public InvalidClientException(String message, Exception e) {
		super(message, e);
	}
}
