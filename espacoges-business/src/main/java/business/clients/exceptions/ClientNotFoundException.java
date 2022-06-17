package business.clients.exceptions;

/**
 * An exception that indicates that a client is not registered in the system.
 *
 */
public class ClientNotFoundException extends Exception {
	
	/**
	 * The serial version id (generated automatically by Eclipse)
	 */
	private static final long serialVersionUID = -8202543563191661175L;

	/**
	 * Creates an exception given an error message
	 * @param message The error message
	 */
	public ClientNotFoundException(String message) {
		super(message);
	}
	
	/**
	 * Creates an exception wrapping a lower level exception
	 * @param message The error message
	 * @param e		  The wrapped exception
	 */
	public ClientNotFoundException(String message, Exception e) {
		super(message, e);
	}

}
