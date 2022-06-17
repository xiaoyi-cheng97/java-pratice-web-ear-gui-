package controller.web.inputController.actions;

import java.io.IOException;

import javax.ejb.EJB;
import javax.ejb.Stateless;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import business.handlers.CheckOcupationHandler;
import facade.Interface.ICheckOccupationServiceRemote;
import presentation.web.model.NewCheckOcupationModel;

@Stateless
public class NewCheckOcupationAction extends Action{
			
	@EJB
	private  ICheckOccupationServiceRemote checkOcupationHandler;
	
	@Override
	public void process(HttpServletRequest request, HttpServletResponse response) 
			throws ServletException, IOException {

		NewCheckOcupationModel model = new NewCheckOcupationModel();
		model.setOcupationHandler(checkOcupationHandler);
		request.setAttribute("model", model);
		request.getRequestDispatcher("/checkOcupation/newCheck.jsp").forward(request, response);
	}

	
}
