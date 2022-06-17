package business.sports.exceptions;

/**
 * An exception that indicates that a sport does not exist in the system.
 *
 */
public class SportNotFoundException extends Exception {
	
	/**
	 * The serial version id (generated automatically by Eclipse)
	 */
	private static final long serialVersionUID = 6681823505462044065L;

	/**
	 * Creates an exception given an error message
	 * @param message The error message
	 */
	public SportNotFoundException(String message) {
		super(message);
	}
	
	/**
	 * Creates an exception wrapping a lower level exception
	 * @param message The error message
	 * @param e		  The wrapped exception
	 */
	public SportNotFoundException(String message, Exception e) {
		super(message, e);
	}

}
