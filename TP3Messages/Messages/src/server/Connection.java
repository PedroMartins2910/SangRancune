package server;

import java.io.IOException;
import java.lang.*;
import java.net.*;

public class Connection implements Runnable {

	/**
	 * Server : ref vers le serveur qui l'a créé Server Socket : permet d'accepter
	 * de nouvelles connections
	 */
	private Server server;
	private ServerSocket serverSocket;

	public Connection(Server server) {
		this.server = server;
		try {
			this.serverSocket = new ServerSocket(server.getPort());
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	public void run() {
		Socket sockNewClient;
		while (true) {
			try {
				System.out.println("En attente de connection client");
				sockNewClient = serverSocket.accept();
				ConnectedClient newClient = new ConnectedClient(server, sockNewClient);
				server.addClient(newClient);
				Thread threadNewClient = new Thread(newClient);
				threadNewClient.start();
			} catch (IOException e) {
				e.printStackTrace();
			}
		}
	}

}
