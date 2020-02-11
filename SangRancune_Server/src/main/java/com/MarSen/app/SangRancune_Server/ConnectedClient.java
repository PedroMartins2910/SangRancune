package com.MarSen.app.SangRancune_Server;
import com.MarSen.app.SangRancune_Lib.*;
import java.io.*;
import java.net.Socket;



import java.lang.*;

public class ConnectedClient implements Runnable {

private static int idCounter = 0;
private int id;
private String login;
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
		 Object obj = null;
		try {
			System.out.println("attente message");

			obj = (Message) in.readObject();
			System.out.println("Message recu");
			if(obj == null) {
				isActive = false;
			}
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
		
			if(obj instanceof Message) {
				Message mess = (Message) obj; // verifie quel est le ype de l'obect recu
			}
		
		
		
		
		
		 
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
