package facade.exceptions;

/**
 * The top level application exception. 
 *
 */
public class ApplicationException extends Exception {
	
	/**
	 * The serial version id (generated automatically by Eclipse)
	 */
	private static final long serialVersionUID = 5508181497404652801L;

	/**
	 * Creates an exception given an error message
	 * @param message The error message
	 */
	public ApplicationException(String message) {
		super(message);
	}
	
	/**
	 * Creates an exception wrapping a lower level exception
	 * @param message The error message
	 * @param e		  The wrapped exception
	 */
	public ApplicationException(String message, Exception e) {
		super(message, e);
	}

}