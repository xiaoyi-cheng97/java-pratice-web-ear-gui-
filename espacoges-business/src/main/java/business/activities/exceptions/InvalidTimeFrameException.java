package business.activities.exceptions;

/**
 * An exception that indicates that a given time interval is invalid in itself 
 * or for a certain user type.
 *
 */
public class InvalidTimeFrameException extends InvalidActivityException {
	
	/**
	 * The serial version id (generated automatically by Eclipse)
	 */
	private static final long serialVersionUID = -8664239152930690851L;

	/**
	 * Creates an exception given an error message
	 * @param message The error message
	 */
	public InvalidTimeFrameException(String message) {
		super(message);
	}
	
	/**
	 * Creates an exception wrapping a lower level exception
	 * @param message The error message
	 * @param e		  The wrapped exception
	 */
	public InvalidTimeFrameException(String message, Exception e) {
		super(message, e);
	}

}
