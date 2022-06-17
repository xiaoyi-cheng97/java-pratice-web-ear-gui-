package business.requests.exceptions;

/**
 * An exception that indicates a generic error in validating a request.
 *
 */
public class InvalidRequestException extends Exception {
	
	/**
	 * The serial version id (generated automatically by Eclipse)
	 */
	private static final long serialVersionUID = 6407259346708229698L;

	/**
	 * Creates an exception given an error message
	 * @param message The error message
	 */
	public InvalidRequestException(String message) {
		super(message);
	}
	
	/**
	 * Creates an exception wrapping a lower level exception
	 * @param message The error message
	 * @param e		  The wrapped exception
	 */
	public InvalidRequestException(String message, Exception e) {
		super(message, e);
	}

}
