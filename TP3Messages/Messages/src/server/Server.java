package server;

import java.net.SocketImpl;
import java.util.*;

import common.Message;

public class Server {
	
	private int port;
	List <ConnectedClient> clients;
	
	public Server(int port) {
		this.port = port;
		this.clients = new ArrayList<ConnectedClient>();
		Thread threadConnection = new Thread(new Connection(this)); 
		threadConnection.start();
	}

	public Server() {
	}
	
	public int getPort() {
		return port;
	}

	public void addClient(ConnectedClient newClient) {
		Message mess = new Message(Integer.toString(newClient.getId())," vient de se connecter");
		mess.setSender("Serveur");
		for (ConnectedClient client : clients) {
			client.sendMessage(mess);
			}
		this.clients.add(newClient);
	}
	
	public void  broadcastMessage(Message mess, int id) {
		System.out.println("Broadcast afficher message de "+id);
		for (ConnectedClient client : clients) {
			System.out.println("Envoi à " + client.getId());
			if (client.getId() != id) {
				client.sendMessage(mess);
				}
			}
	}
	
	public void disconnectedClient(ConnectedClient discClient) {
		for (ConnectedClient client : clients) {
			client.sendMessage(new Message("server", "Le client " + discClient.getId() + " nous a quitté"));
			}
	}
//	public void close() {
//		for (ConnectedClient client : clients) {
//			
//		}
//	}
}
