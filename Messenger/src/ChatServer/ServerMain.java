package ChatServer;

import java.io.IOException;
import java.io.OutputStream;
import java.net.ServerSocket;
import java.net.Socket;

public class ServerMain {

		public static void main(String[] args)
		{
			int port = 8819;
			Server server = new Server(port);
			server.start();
			
			
		}

		
}
