package com.MarSen.app.SangRancune_Client;

import java.io.IOException;
import java.net.UnknownHostException;

/**
 * starts a client. Reads the address and port from the command line argument
 * 
 * @author Remi Watrigant *
 */
public class MainClient {
	/**
	 * construct a new client * @param args
	 */
	public static void main(String[] args) {
		if (args.length != 2) {
			printUsage();
		} else {
			String address = args[0];
			Integer port = new Integer(args[1]);
			Client c = new Client(address, port);
		}
	}

	private static void printUsage() {
	}
}