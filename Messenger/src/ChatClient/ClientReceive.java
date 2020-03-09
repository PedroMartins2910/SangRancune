package ChatClient;

import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.io.Serializable;
import java.net.Socket;
import java.util.ArrayList;
import java.util.Scanner;

import Lib.Logged;
import Lib.UserCon;

import java.lang.*;

public class ClientReceive implements Runnable,Serializable {

	private Client client;
	private ObjectInputStream in;
	private Socket socket;

	public ClientReceive(Client client, Socket socket) {
		this.client = client;
		this.socket = socket;
	}

	public void run() {
		boolean isActive = true;

		try {
			in = new ObjectInputStream(socket.getInputStream());
			while (isActive) {
				//System.out.println("En attente de message");
				Object obj = in.readObject();
				if (obj != null) {
					// treat received packages

					if (obj instanceof UserCon) {
						UserCon user  = (UserCon)obj;
						System.out.println("Utilisateur: "+user.login+ " mot de passe: "+user.password +" canConnect? : " +user.canConnect);
					}
					if(obj instanceof Logged) 
					{
						Logged user = (Logged) obj;
						System.out.println("Un utilsateur s'est connecte: " + user.login );
					}

					
					if(obj instanceof ArrayList<?>) 
					{
						
						@SuppressWarnings("unchecked")
						ArrayList<String> list = (ArrayList<String>) obj;
						for (String log : list ) 
						{
							System.out.println("Online: " + log );
						}
					}
				} else {
					//client.disconnectedServer();
					//isActive = false;
				}
			}
		} catch (IOException e) {
			e.printStackTrace();
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
		}
	}

}
