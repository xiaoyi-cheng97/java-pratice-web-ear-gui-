package controller.web.inputController.actions;

import java.awt.Dialog.ModalExclusionType;
import java.io.IOException;

import javax.ejb.EJB;
import javax.ejb.Stateless;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import business.handlers.ReservationRequestHandler;
import business.requests.exceptions.InvalidRequestException;
import facade.Interface.IReservationRequestServiceRemote;
import facade.exceptions.ApplicationException;
import presentation.web.model.NewRequestModel;

/**
 * Handles the criar cliente event.
 * If the request is valid, it dispatches it to the domain model (the application's business logic)
 * Notice as well the use of an helper class to assist in the jsp response. 
 * 
 * @author fmartins
 *
 */
@Stateless
public class CreateRequestAction extends Action {
	
	@EJB private IReservationRequestServiceRemote addRequestrHandler;
	
	@Override
	public void process(HttpServletRequest request, HttpServletResponse response) 
			throws ServletException, IOException {

		NewRequestModel model = createHelper(request);
		request.setAttribute("model", model);
		
		if (validInput(model)) {
			try {
				addRequestrHandler.giveId(intValue(model.getId()));
				addRequestrHandler.chooseSport(model.getSports());
				addRequestrHandler.createReservation(model.getDescription(), model.getStartDate(),  model.getEndDate(), intValue(model.getnPartecipante()));
				model.clearFields();
				model.addMessage("Request successfully added.");
			} 
			catch (ApplicationException e) {
				model.addMessage("Error adding customer:  " + e.getMessage() + " invalid VAT number");
			}
            catch (Exception e) {
				model.addMessage("Error adding customer: " + e.getMessage());
			}
		} else
			model.addMessage("Error validating customer data");
		
		request.getRequestDispatcher("/addRequest/newRequest.jsp").forward(request, response);
	}

	
	private boolean validInput(NewRequestModel model) {
		
		// check if designation is filled
		boolean result = isFilled(model, model.getDescription(), "Designation must be filled.");
		
		// check if id is filled and a valid number
		result &= isFilled(model, model.getId(), "Client id must be filled")
				 			&& isInt(model, model.getId(), "Client id with invalid characters");
		
		// check in case phoneNumber is filled if it contains a valid number
		if (!model.getSports().equals(""))
			result &= isFilled(model, model.getDescription(), "Description must be filled.");
System.out.println(result);
		// check if discount type is filled and a valid number
		result &= isFilled(model, model.getStartDate(), "Start Date type must be filled") 
					&& isDate(model, model.getStartDate(), "Start Date with invalid characters");
		System.out.println(result);	
		result &= isFilled(model, model.getEndDate(), "End Date must be filled") 
				&& isDate(model, model.getEndDate(), " End Date invalid characters");
		System.out.println(result);
		result &= isFilled(model, model.getStartTime(), "Start Time type must be filled") 
				&& isTime(model, model.getStartTime(), "Start Time with invalid characters");
		System.out.println(result);
	result &= isFilled(model, model.getEndTime(), "End Time must be filled") 
			&& isTime(model, model.getEndTime(), " End Time invalid characters");
	System.out.println(result);
		result &= isFilled(model, model.getnPartecipante(), "nPartecipante must be filled")
	 			&& isInt(model, model.getnPartecipante(), "nPartecipante with invalid characters");
		System.out.println(result);
		
		return result;
	}


	private NewRequestModel createHelper(HttpServletRequest request) {
		// Create the object model
		NewRequestModel model = new NewRequestModel();
		model.setRequestHandler(addRequestrHandler);

		// fill it with data from the request
		model.setDescription(request.getParameter("description"));
		model.setId(request.getParameter("id"));
		model.setSports(request.getParameter("sport"));
		model.setStartDate(request.getParameter("startDate"));
		model.setEndDate(request.getParameter("endDate"));
		model.setStartTime(request.getParameter("startTime"));
		model.setEndTime(request.getParameter("endTime"));
		model.setnPartecipante(request.getParameter("nPartecipante"));
		
		return model;
	}	
}
