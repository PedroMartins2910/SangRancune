package server;
import java.io.*;
import java.net.Socket;

import common.Message;

import java.lang.*;

public class ConnectedClient implements Runnable {

private static int idCounter = 0;
private int id;
private Server server;
private Socket socket;
private ObjectInputStream in;
private ObjectOutputStream out;

public ConnectedClient(Server server, Socket socket) {
	this.server = server;
	this.socket = socket;
	this.id = idCounter++;
	try {
		out = new ObjectOutputStream(socket.getOutputStream());
	} catch (IOException e) {
		e.printStackTrace();
	}
	System.out.println("Nouvelle connexion, id = " + id);
}

public void run() {
	 try {
		in = new ObjectInputStream(socket.getInputStream());
	} catch (IOException e) {
		e.printStackTrace();
	}
	 boolean isActive = true;
	 while (isActive) {
		 Message mess = null;
		try {
			System.out.println("attente message");

			mess = (Message) in.readObject();
			System.out.println("Message reçu");
			if(mess == null) {
				isActive = false;
			}
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
		 mess.setSender(String.valueOf(id));
		 server.broadcastMessage(mess, id);
		 
	 }
}

public void sendMessage(Message mess) {
	try {
		this.out.writeObject(mess);
		this.out.flush();
	} catch (IOException e) {
		e.printStackTrace();
	}      
}

public void closeClient() {
try {
	this.in.close();
	this.out.close();
	this.socket.close();
} catch (IOException e) {
	e.printStackTrace();
}
}

public int getId() {
	return id;
};

}
