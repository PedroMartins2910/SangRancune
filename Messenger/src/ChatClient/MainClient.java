package ChatClient;

import java.io.IOException;
import java.net.UnknownHostException;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.Pane;
import javafx.stage.Stage;

/**
 * starts a client. Reads the address and port from the command line argument
 * 
 * @author Remi Watrigant *
 */
public class MainClient extends Application {
	/**
	 * construct a new client * @param args
	 */
	public static  Client client;
	 
	public static void main(String[] args) {
		
		Application.launch();
		//String address = "localhost";
		//int port = 8819;
		//client = new Client(address, port);
	}
			
			
	@Override
	public void start(Stage primaryStage) throws Exception {
		
		
		FXMLLoader loader = new FXMLLoader(MainClient.class.getResource("newLogin.fxml"));

        // Create a controller instance
        LoginUIController controller = new LoginUIController(this);
        // Set it in the FXMLLoader
        loader.setController(controller);
        Pane pane = (Pane)loader.load();
		primaryStage.setScene(new Scene(pane));
		primaryStage.show();
}
	
public change
public void createClient(String host, int port) 
{
	client = new Client(host,port);
}	
	}