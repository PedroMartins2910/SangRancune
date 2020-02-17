package Server;
import java.net.SocketImpl;
import java.util.*;

import Lib.Message;


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
			this.clients.add(newClient);
		}
		
		public void  broadcastMessage(Message mess, int id) {
			System.out.println("Broadcast afficher message de "+id);
			
			for (ConnectedClient client : clients) {  // Boucle de broadcast cest ici que lobjet est envoye
				System.out.println("Envoi a  " + client.getId());
				if (client.getId() != id) {
					client.sendMessage(mess);
					}
				}
		}
		
		public void disconnectedClient(ConnectedClient discClient) {  //Message a envoyer en cas de deconnexion.
			for (ConnectedClient client : clients) {
				client.sendMessage(new Message("abc", "cbd") );
				}
		}
	}
