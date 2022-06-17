package business.sports.exceptions;

/**
 * An exception that indicates that a sport is not allowed in a space.
 *
 */
public class InvalidSportException extends Exception {

	/**
	 * The serial version id (generated automatically by Eclipse)
	 */
	private static final long serialVersionUID = 2720068322691782743L;

	/**
	 * Creates an exception given an error message
	 * @param message The error message
	 */
	public InvalidSportException(String message) {
		super(message);
	}
	
	/**
	 * Creates an exception wrapping a lower level exception
	 * @param message The error message
	 * @param e		  The wrapped exception
	 */
	public InvalidSportException(String message, Exception e) {
		super(message, e);
	}

}
