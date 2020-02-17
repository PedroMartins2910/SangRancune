package Client;

import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.Socket;
import java.util.Scanner;
import java.lang.*;


import Lib.Message;

public class ClientReceive implements Runnable {

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
				System.out.println("En attente de message");
				Message mess = (Message) in.readObject();
				if (mess != null) {
					this.client.messageReceived(mess);
				} else {
					client.disconnectedServer();
					isActive = false;
				}
			}
		} catch (IOException e) {
			e.printStackTrace();
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
		}
	}

}
