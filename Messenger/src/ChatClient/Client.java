package ChatClient;

import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.io.Serializable;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.Scanner;

import Lib.UserCon;
import javafx.application.Application;
import javafx.stage.Stage;


public class Client  implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private String address;
	private int port;
	private Socket socket;
	private ObjectInputStream in;
	private ObjectOutputStream out;

	public Client(String address, int port) {
		
		this.address = address;
		this.port = port;
		try {
			this.socket = new Socket(address, port);
			out = new ObjectOutputStream(socket.getOutputStream());
		} catch (IOException e) {
			e.printStackTrace();
		}
			Scanner myObj = new Scanner(System.in);  // Create a Scanner object
		    System.out.println("Enter username");

		    
		UserCon user = new UserCon();
		user.canConnect = false;
		user.login = myObj.nextLine(); 
		user.password = "";
		Object o = (Object) user;
		sendObject(o);
		Thread ClientReceive = new Thread(new ClientReceive(this, socket));
		ClientReceive.start();
	}
	
	//
	public void disconnectedServer() throws IOException {
		try {
			this.in.close();
			this.out.close();
			this.socket.close();
		} catch (IOException e) {
			e.printStackTrace();
		}
		
	}

	//Method that sends info to the server
	public void sendObject(Object obj) {
		
		try {
			out.writeObject(obj);
			out.flush();
		} catch (IOException e) {
			e.printStackTrace();
		}

	}


}
