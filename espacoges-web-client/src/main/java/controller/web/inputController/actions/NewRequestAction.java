package controller.web.inputController.actions;

import java.io.IOException;

import javax.ejb.EJB;
import javax.ejb.Stateless;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import business.handlers.ReservationRequestHandler;
import facade.Interface.IReservationRequestServiceRemote;
import presentation.web.model.NewRequestModel;

@Stateless
public class NewRequestAction extends Action{
	
	@EJB
	private  IReservationRequestServiceRemote addRequestHandler;
	
	@Override
	public void process(HttpServletRequest request, HttpServletResponse response) 
			throws ServletException, IOException {

		NewRequestModel model = new NewRequestModel();
		model.setRequestHandler(addRequestHandler);
		request.setAttribute("model", model);
		request.getRequestDispatcher("/addRequest/newRequest.jsp").forward(request, response);
	}

}
