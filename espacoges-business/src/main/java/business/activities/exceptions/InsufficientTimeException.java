package business.activities.exceptions;

/**
 * An exception that indicates that a given time interval does not comply
 * with the minimum minutes for an activity of previously chose sport.
 *
 */
public class InsufficientTimeException extends InvalidActivityException {
	
	/**
	 * The serial version id (generated automatically by Eclipse)
	 */
	private static final long serialVersionUID = 8059656484032734383L;

	/**
	 * Creates an exception given an error message
	 * @param message The error message
	 */
	public InsufficientTimeException(String message) {
		super(message);
	}
	
	/**
	 * Creates an exception wrapping a lower level exception
	 * @param message The error message
	 * @param e		  The wrapped exception
	 */
	public InsufficientTimeException(String message, Exception e) {
		super(message, e);
	}

}
