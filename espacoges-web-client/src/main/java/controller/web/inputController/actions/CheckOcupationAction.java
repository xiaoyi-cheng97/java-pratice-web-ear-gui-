package controller.web.inputController.actions;

import java.awt.Dialog.ModalExclusionType;
import java.io.IOException;

import javax.ejb.EJB;
import javax.ejb.Stateless;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import business.handlers.CheckOcupationHandler;
import business.handlers.ReservationRequestHandler;
import business.requests.exceptions.InvalidRequestException;
import facade.Interface.ICheckOccupationServiceRemote;
import facade.exceptions.ApplicationException;
import presentation.web.model.NewCheckOcupationModel;
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
public class CheckOcupationAction extends Action {
	
	@EJB private ICheckOccupationServiceRemote checkOcupationHandler;
	
	@Override
	public void process(HttpServletRequest request, HttpServletResponse response) 
			throws ServletException, IOException {

		NewCheckOcupationModel model = createHelper(request);
		request.setAttribute("model", model);
		
		if (validInput(model)) {
			try {
				//cos.checkUseOfSpace(space1, "01/07/2019 00:00" , "08/07/2019 00:00")
				checkOcupationHandler.checkUseOfSpace(model.getSpace(), model.getStartDate() + " " + model.getStartTime(),
						model.getEndDate() + " " + model.getEndTime());
				model.addMessage("check of Ocupation...");
			} 
			catch (ApplicationException e) {
				model.addMessage("Error adding customer:  " + e.getMessage() + " invalid VAT number");
			}
            catch (Exception e) {
				model.addMessage("Error adding customer: " + e.getMessage());
			}
		} else
			model.addMessage("Error validating customer data");
		
		request.getRequestDispatcher("/checkOcupation/newCheck.jsp").forward(request, response);
	}

	
	private boolean validInput(NewCheckOcupationModel model) {
		
	
		boolean result = isFilled(model, model.getSpace(), "Space must be filled.");
		
	
		result &= isFilled(model, model.getStartDate(), "Start date must be filled")
				&& isDate(model, model.getStartDate(), " Start date with invalid characters");
		result &= isFilled(model, model.getEndDate(), "End date must be filled")
				&& isDate(model, model.getEndDate(), " End date with invalid characters");
		
		result &= isFilled(model, model.getStartTime(), "Start time must be filled")
				&& isTime(model, model.getStartTime(), "Start time with invalid characters");
		
		result &= isFilled(model, model.getEndTime(), "End time must be filled")
				&& isTime(model, model.getEndTime(), "End time with invalid characters");
		
	
		
		return result;
	}


	private NewCheckOcupationModel createHelper(HttpServletRequest request) {
		// Create the object model
		NewCheckOcupationModel model = new NewCheckOcupationModel();
		model.setOcupationHandler(checkOcupationHandler);

		// fill it with data from the request
		model.setSpace(request.getParameter("space"));
		model.setStartDate(request.getParameter("startDate"));
		model.setEndDate(request.getParameter("endDate"));
		model.setStartTime(request.getParameter("startTime"));
		model.setEndTime(request.getParameter("endTime"));
		
		return model;
	}	
}
