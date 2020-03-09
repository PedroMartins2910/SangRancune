package ChatClient;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.Hyperlink;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.scene.input.MouseEvent;

public class LoginUIController {

	private MainClient client ;
	public LoginUIController(MainClient mainClient) 
	{
		this.client = mainClient ;
		
	}
	@FXML
    private Button clientLoginButton;

    @FXML
    private Button clientNewAccButton;

    @FXML
    private TextField hostAddressField;

    @FXML
    private TextField hostPortField;

    @FXML
    private TextField clientUsernameField;

    @FXML
    private PasswordField clientPasswordField;

    @FXML
    private Button quit;

    @FXML
    private TextArea alertBox;

    @FXML
    void onClickExit(MouseEvent event) {

    }

    @FXML
    void onClickLogin(MouseEvent event) {
    		String login = clientUsernameField.getText();
    		String host = hostAddressField.getText();
    		String port  = hostPortField.getText();
    	
    		if(!login.equals("") && !host.equals("") && !port.equals(""))
	    		{
    			try {
    				client.createClient(host,Integer.parseInt(port));
    				}catch(Exception e) 
    			{
    					alertBox.setText("The hostname or the port are not correct");
    			}
    			
	    		}  else 
	    		{
	    			alertBox.setText("Fill all blanks please");	
	    		}
}

    @FXML
    void onClickNewAccount(MouseEvent event) {

    }


}
