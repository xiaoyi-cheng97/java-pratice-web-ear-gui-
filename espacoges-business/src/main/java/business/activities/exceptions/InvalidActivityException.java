package business.activities.exceptions;

/**
 * The superclass of all activity exceptions. Indicates that something
 * went wrong in the creation of an activity.
 *
 */
public class InvalidActivityException extends Exception {
	
	/**
	 * The serial version id (generated automatically by Eclipse)
	 */
	private static final long serialVersionUID = 3492733295833218812L;

	/**
	 * Creates an exception given an error message
	 * @param message The error message
	 */
	public InvalidActivityException(String message) {
		super(message);
	}
	
	/**
	 * Creates an exception wrapping a lower level exception
	 * @param message The error message
	 * @param e		  The wrapped exception
	 */
	public InvalidActivityException(String message, Exception e) {
		super(message, e);
	}

}
