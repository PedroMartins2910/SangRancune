package ChatServer;

import java.io.IOException;
import java.io.InputStream;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.io.OutputStream;
import java.net.Socket;
import java.util.ArrayList;
import java.util.List;

import Lib.Logged;
import Lib.Message;
import Lib.UserCon;

public class ServerWorker extends Thread {
	private final Server server;
	private Socket clientSocket;
	private String login = null;
	private ObjectOutputStream outputStream;

	public ServerWorker(Server server, Socket clientSocket) {
		this.server = server;
		this.clientSocket = clientSocket;
	}

	// Launches handleClient
	@Override
	public void run() {
		try {
			handleClient();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	// Handle the reception of client packets
	private void handleClient() throws IOException {
		this.outputStream = new ObjectOutputStream(clientSocket.getOutputStream());
		ObjectInputStream in = new ObjectInputStream(clientSocket.getInputStream());
		boolean isActive = true;
		while (isActive) {
			Object obj = null;
			try {
				System.out.println("attente de paquet");

				obj = (Object) in.readObject();
				System.out.println("Paquet recu");
				if (obj == null) {
					isActive = false;
				} else {
					// Start of packet handling

					// Checks if user has a valid account
					if (obj instanceof UserCon) {
						UserCon user = (UserCon) obj;
						UserCon conUser = new UserCon();
						UserController control = new UserController();
						conUser = control.CheckUser(user);
						if (conUser.canConnect) {
							this.login = conUser.login;
							Logged log = new Logged();
							log.login = this.login;
							log.logged = true;
							ArrayList<String> onlineLogs = new ArrayList<String>(); 
							for(ServerWorker worker : server.getWorkerList()) 
							{
								if(worker.login != null && !worker.login .isEmpty()) 
								{
									onlineLogs.add(worker.login);
								}
							}
							broadcast(onlineLogs);
						}
						send(conUser);
					}

					// Sends private or group message
					if (obj instanceof Message) {
						Message msg = (Message) obj;
						if (msg.to.equalsIgnoreCase("all")) {
							broadcast(obj);
						} else {
							sendToLogin(msg);
						}
					}

					if (obj instanceof Logged) {

					}

					// end of packet handling
				
				
				}
			} catch (ClassNotFoundException e) {
				e.printStackTrace();
			} catch (IOException e) {
				e.printStackTrace();
			}
		}

	}

	// gets off the client of the connected clients list and closes the socket
	public void handleLogOff() throws IOException {
		server.removeWorker(this);
		clientSocket.close();
	}

	// Other kinds of info
	private void send(Object obj) {
		try {
			this.outputStream.writeObject(obj);
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	// Sends a private message to a client
	private void sendToLogin(Message msg) {
		List<ServerWorker> workerList = server.getWorkerList();
		for (ServerWorker worker : workerList) {
			if (worker.login.equalsIgnoreCase(msg.to)) {
				try {
					worker.outputStream.writeObject(msg);
				} catch (IOException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			}
		}

	}

	// Notifies the clients if a client logs off/in / sends group message
	private void broadcast(Object obj) {
		List<ServerWorker> workerList = server.getWorkerList();
		for (ServerWorker worker : workerList) {
			try {
				worker.outputStream.writeObject(obj);
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}

		}

		// Disconnects and closes socket if client logged off
		if (obj instanceof Logged) {
			Logged log = (Logged) obj;
			if (!log.logged) {
				try {
					handleLogOff();
				} catch (IOException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			}

		}
	}

}
