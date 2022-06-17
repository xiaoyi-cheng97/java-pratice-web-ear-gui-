package business.spaces.exceptions;

/**
 * An exception that indicates that a space is not available in a certain request period.
 *
 */
public class UnavailableSpaceException extends Exception {
	
	/**
	 * The serial version id (generated automatically by Eclipse)
	 */
	private static final long serialVersionUID = -3103416928741673346L;

	/**
	 * Creates an exception given an error message
	 * @param message The error message
	 */
	public UnavailableSpaceException(String message) {
		super(message);
	}
	
	/**
	 * Creates an exception wrapping a lower level exception
	 * @param message The error message
	 * @param e		  The wrapped exception
	 */
	public UnavailableSpaceException(String message, Exception e) {
		super(message, e);
	}

}
