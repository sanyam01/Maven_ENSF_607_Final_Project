package Server.Controller.ServerController;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.ServerSocket;
import java.net.Socket;
import java.net.SocketException;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;

import Server.Controller.DatabaseController.DBController;
import Server.Controller.ModelController.ServerModelController;
import Server.Controller.ModelController.ServerCustomerController;

public class Server {

	private Socket socket;
	private PrintWriter socketOut;
	private BufferedReader socketIn;

	private ServerSocket serverSocket;
	private ExecutorService pool;

//	private Customer customer;

	public Server() {

		try {
			// Server socket accepts the port as a parameter
			serverSocket = new ServerSocket(9090);

			// System.out.println("Server is running!");
			pool = Executors.newFixedThreadPool(20);
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	public void runServer() {
		System.out.println("Server is running...");
		String messageToClient = null;
//		try {
//			socket = serverSocket.accept();
//		} catch (IOException e1) {
//			// TODO Auto-generated catch block
//			e1.printStackTrace();
//		}

		DBController dbController = new DBController();
		System.out.println("dbController instantiated");
//		ServerModelController serverModelController = new ServerModelController(socket, dbController);

//		pool.execute(serverModelController);

		while (true) {
			
			try {
				socket = serverSocket.accept(); //for multiple clients
			} catch (IOException e1) {

				e1.printStackTrace();
			}


			ServerModelController serverModelController = new ServerModelController(socket, dbController);
			
//			serverModelController.run();
				pool.execute(serverModelController);
//				
		}

	}

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		Server myServer = new Server();
		myServer.runServer();

	}

}
