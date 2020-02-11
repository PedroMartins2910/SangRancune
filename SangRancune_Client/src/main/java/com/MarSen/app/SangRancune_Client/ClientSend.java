package com.MarSen.app.SangRancune_Client;

import java.io.IOException;
import java.io.ObjectOutputStream;
import java.net.Socket;
import java.util.Scanner;

import com.MarSen.app.SangRancune_Lib.*;

public class ClientSend implements Runnable {

	private ObjectOutputStream out;
	private Socket socket;
	
	public ClientSend(Socket socket, ObjectOutputStream out) {
		this.socket = new Socket();
		this.out = out;//new ObjectOutputStream(socket.getOutputStream());
		
	}
	
	public void run() {
		Scanner sc = new Scanner(System.in);
		while (true) {
			System.out.print("Votre message >> ");
			String m = sc.nextLine();
			Message mess = new Message("client", m);
			try {
				out.writeObject(mess);
				out.flush();
			} catch (IOException e) {
				e.printStackTrace();
			}
		}
	}

}
