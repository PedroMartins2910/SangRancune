package Client;

import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.ServerSocket;
import java.net.Socket;
import Lib.Message;

public class Client {

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

		Thread ClientSend = new Thread(new ClientSend(socket, out));
		ClientSend.start();
		Thread ClientReceive = new Thread(new ClientReceive(this, socket));
		ClientReceive.start();
	}

	public void disconnectedServer() throws IOException {
		try {
			this.in.close();
			this.out.close();
			this.socket.close();
		} catch (IOException e) {
			e.printStackTrace();
		}
		System.exit(0);
	}

	public String sendMessage(String text) {
		return text;
	}

	Message messageReceived(Message mess) {
		System.out.println(mess);
		return mess;
	}
}
