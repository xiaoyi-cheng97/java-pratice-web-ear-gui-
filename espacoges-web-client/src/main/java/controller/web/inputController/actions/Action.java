package controller.web.inputController.actions;

import java.io.IOException;
import java.text.ParseException;
import java.text.SimpleDateFormat;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import presentation.web.model.Model;

/**
 * An abstract HTTP action request handler. 
 * Think of it as an operation in the SSD diagram.
 * It has an init method, because objects are
 * create from the prototype (vide UseCaseFrontController)
 * and its easier to use a no parameters construct.
 * 
 * It allows subclasses to define how to handle individual 
 * actions.
 * 
 * We need to store the http request context, since
 * actions are not http servlets and do not have access to
 * the request data.
 *  
 * @author fmartins
 *
 */
public abstract class Action {

	/**
	 * Strategy method for processing each request
	 * @throws ServletException
	 * @throws IOException
	 */
	public abstract void process(HttpServletRequest request, HttpServletResponse response) 
			throws ServletException, IOException;
	
	// the following methods might need to be refactored in order to avoid
	// duplicated code
	protected boolean isInt(Model help, String num, String message) {
		try {
			Integer.parseInt(num);
			return true;
		} catch (NumberFormatException e) {
			help.addMessage(message);
			return false;
		}
	}

	protected int intValue(String num) {
		try {
			return Integer.parseInt(num);
		} catch (NumberFormatException e) {
			return 0;
		}
	}

	protected boolean isDate(Model help, String date, String message){
		if (date == null || !date.matches("[0-3]\\d/[01]\\d/\\d{4}"))
	        return false;
	    SimpleDateFormat df = new SimpleDateFormat("dd/mm/yyyy");
	    df.setLenient(false);
	    try {
	        df.parse(date);
	        return true;
	    } catch (ParseException ex) {
	    	help.addMessage(message);
	        return false;
	    }
		
	}
	
	protected boolean isTime(Model help, String time, String message){
		if (time == null || !time.matches("[0-2]\\d:[0-9]\\d"))
	        return false;
	    SimpleDateFormat df = new SimpleDateFormat("hh:mm");
	    df.setLenient(false);
	    try {
	        df.parse(time);
	        return true;
	    } catch (ParseException ex) {
	    	help.addMessage(message);
	        return false;
	    }
		
	}

	protected boolean isFilled (Model helper, String value, String message) {
		if (value == null || (value != null && value.equals(""))) {
			helper.addMessage(message);
			return false;
		}
		return true;
	}

}
