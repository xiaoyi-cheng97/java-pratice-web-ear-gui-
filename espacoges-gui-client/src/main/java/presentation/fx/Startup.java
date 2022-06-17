package presentation.fx;

import java.io.IOException;
import java.util.Locale;
import java.util.ResourceBundle;

import facade.handlers.ICustomerServiceRemote;
import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;
import presentation.fx.inputcontroller.NewCustomerController;
import presentation.fx.model.NewCustomerModel;

public class Startup extends Application {
    
	private static ICustomerServiceRemote customerService;

	@Override 
    public void start(Stage stage) throws IOException {
    
		// This line to resolve keys against Bundle.properties
		// ResourceBundle i18nBundle = ResourceBundle.getBundle("i18n.Bundle", new Locale("en", "UK"));
        ResourceBundle i18nBundle = ResourceBundle.getBundle("i18n.Bundle", new Locale("pt", "PT"));
		
    	FXMLLoader createCustomerLoader = new FXMLLoader(getClass().getResource("/fxml/newCustomer.fxml"), i18nBundle);
    	Parent root = createCustomerLoader.load();
    	NewCustomerController newCustomerController = createCustomerLoader.getController();    	
    	
    	NewCustomerModel newCustomerModel = new NewCustomerModel(customerService);
    	newCustomerController.setModel(newCustomerModel);
    	newCustomerController.setCustomerService(customerService);
    	newCustomerController.setI18NBundle(i18nBundle);
    	
        Scene scene = new Scene(root, 450, 275);
       
        stage.setTitle(i18nBundle.getString("application.title"));
        stage.setScene(scene);
        stage.show();
    }
	
	public static void startGUI(ICustomerServiceRemote addCustomerHandler) {
		Startup.customerService = addCustomerHandler;
        launch();
	}
}
