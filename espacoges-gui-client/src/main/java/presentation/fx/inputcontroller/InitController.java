package presentation.fx.inputcontroller;

import java.awt.Button;
import java.awt.event.ActionEvent;
import java.io.IOException;
import java.util.Locale;
import java.util.ResourceBundle;

import business.handlers.ProcessRequestsHandler;
import facade.Interface.IProcessRequestServiceRemote;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;
import presentation.fx.model.RequestModel;

public class InitController {

	@FXML
	private Button b;
	
	private IProcessRequestServiceRemote requestService;
	
	private void setProcessRequestsHandler(IProcessRequestServiceRemote resquestService) {
		this.requestService = requestService;
	}
	
	@FXML
	void initialize() {	}
	
	@FXML
	void processRequestAction(ActionEvent event) throws IOException {
		ResourceBundle i18nBundle = ResourceBundle.getBundle("i18n.Bundle", new Locale("pt", "PT"));

    	FXMLLoader processRequestLoader = new FXMLLoader(getClass().getResource("/fxml/processRequest.fxml"), i18nBundle);
    	Parent root = processRequestLoader.load();
    	ProcessRequestController processRequestController = processRequestLoader.getController();    	

    	RequestModel processaPedidoModel = new RequestModel();
    	processRequestController.setModel(processaPedidoModel);
    	processRequestController.setInstalacaoService(requestService);
    	processRequestController.setI18NBundle(i18nBundle);

        Scene scene = new Scene(root, 630, 500);

        Stage stage = new Stage();
        stage.setTitle("EspacoGes");
        stage.setScene(scene);
        stage.show();
        
        Stage stage2 = (Stage) button.getScene().getWindow();
		stage2.close();
	}

	}
	
}
