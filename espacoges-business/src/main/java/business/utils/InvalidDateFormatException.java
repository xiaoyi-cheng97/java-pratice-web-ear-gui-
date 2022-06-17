package business.utils;

/**
 * An exception that indicates that a given string representing a date or time
 * is not in the expected format.
 *
 */
public class InvalidDateFormatException extends Exception {
	
	/**
	 * The serial version id (generated automatically by Eclipse)
	 */
	private static final long serialVersionUID = 978859759120159642L;

	/**
	 * Creates an exception given an error message
	 * @param message The error message
	 */
	public InvalidDateFormatException(String message) {
		super(message);
	}
	
	/**
	 * Creates an exception wrapping a lower level exception
	 * @param message The error message
	 * @param e		  The wrapped exception
	 */
	public InvalidDateFormatException(String message, Exception e) {
		super(message, e);
	}

}
