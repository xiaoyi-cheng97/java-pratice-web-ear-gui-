package business.activities.exceptions;

/**
 * An exception that indicates that a given number of participants is insufficient 
 * to create an activity of the already chosen sport.
 *
 */
public class InsufficientParticipantsException extends InvalidActivityException {
	
	/**
	 * The serial version id (generated automatically by Eclipse)
	 */
	private static final long serialVersionUID = -4387912990269047182L;

	/**
	 * Creates an exception given an error message
	 * @param message The error message
	 */
	public InsufficientParticipantsException(String message) {
		super(message);
	}
	
	/**
	 * Creates an exception wrapping a lower level exception
	 * @param message The error message
	 * @param e		  The wrapped exception
	 */
	public InsufficientParticipantsException(String message, Exception e) {
		super(message, e);
	}
	
	

}
