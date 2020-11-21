package Controller.ServerController;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.PrintWriter;
import java.net.ServerSocket;
import java.net.Socket;
import java.net.SocketException;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

import Controller.DatabaseController.DBController;
import Controller.ModelController.ServerCustomerController;

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
		// Accepting the fist connection as the x-Player and second connection as
		// o-Player
		try {
			while (true) {
				socket = serverSocket.accept();
				System.out.println("Server is running");
				
				DBController dbController = new DBController();
				System.out.println("dbController instantiated");
				ServerCustomerController customerController = new ServerCustomerController(socket, dbController);
				System.out.println("customerController instantiated");

//				pool.execute(customerController);
				customerController.run_temp();
			}

		}catch (SocketException e) {
			System.out.println("Socket exception!");
		}
		catch (IOException e) {
			e.printStackTrace();
		}

	}

	
	

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		Server myServer = new Server();
		myServer.runServer();
		

	}

}
