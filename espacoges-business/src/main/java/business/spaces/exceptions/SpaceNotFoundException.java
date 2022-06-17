package business.spaces.exceptions;

/**
 * An exception that indicates that a space does not exist in the system.
 *
 */
public class SpaceNotFoundException extends Exception {
	
	/**
	 * The serial version id (generated automatically by Eclipse)
	 */
	private static final long serialVersionUID = -562908330411204000L;

	/**
	 * Creates an exception given an error message
	 * @param message The error message
	 */
	public SpaceNotFoundException(String message) {
		super(message);
	}
	
	/**
	 * Creates an exception wrapping a lower level exception
	 * @param message The error message
	 * @param e		  The wrapped exception
	 */
	public SpaceNotFoundException(String message, Exception e) {
		super(message, e);
	}

}
