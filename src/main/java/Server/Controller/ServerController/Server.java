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
import Server.Controller.ModelController.ServerCustomerController;

public class Server {
	
	
	private Socket socket;
	private PrintWriter socketOut;
	private BufferedReader socketIn;
	
	private ServerSocket serverSocket;
	private ExecutorService pool;
	ObjectMapper objectMapper;

	
//	private Customer customer;
	
	
	public Server() {
		
		objectMapper = new ObjectMapper();
		
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
		String messageToClient = null;
		try {
			socket = serverSocket.accept();
		} catch (IOException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}

		try {
			while (true) {
				
				System.out.println("Server is running");
				
				String response = getClientMessage();
				int taskId = getTaskId(response);
				System.out.println(" taskid is "+ taskId);

				
				DBController dbController = new DBController();
				System.out.println("dbController instantiated");

				if(taskId > 0 && taskId < 6) {
					System.out.println("customerController instantiated");
					ServerCustomerController customerController = new ServerCustomerController(response, dbController, objectMapper);
					
					messageToClient = customerController.run_temp();
					//sending message to client
					setClientMessage(messageToClient);
//					if(messageToClient != null) {
//						socketOut.println(messageToClient);
//						socketOut.flush();
//					}
//					
					
				}
				else if (taskId >= 6 && taskId < 12) {
					
//					ServerCustomerController customerController = new ServerCustomerController(socket, dbController);
					System.out.println("ServerToolController instantiated");
					
				}
				
				
				

//				pool.execute(customerController);
//				
			}
			
			
			
			
			
			

		}catch (SocketException e) {
			System.out.println("Socket exception!");
		}
		catch (IOException e) {
			e.printStackTrace();
		}

		try {
			socketIn.close();
			socketOut.close();
			socket.close();
			
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	public String getClientMessage() {
		
		System.out.println("getting taskid");
		String response = null;
		
		
		try {
			socketIn = new BufferedReader(new InputStreamReader(socket.getInputStream()));
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		try {
			response = socketIn.readLine();
		} catch (IOException e) {

			e.printStackTrace();
		}
		System.out.println("Response from client: " + response);
		return response;
		
	}
	
	public void setClientMessage(String message) {
		
		// Socket output Stream
		try {
			socketOut = new PrintWriter(socket.getOutputStream(), true);
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		System.out.println("Response to client: " + message);
		socketOut.println(message);
		socketOut.flush();
	}
	
	public int getTaskId(String response) {
		int taskId=0;
		
		
		if(response != null) {
//			String jsonCustomer;
			//				jsonCustomer = objectMapper.writeValueAsString(response);
							String[] responseArr = response.split(" ", 2);
							taskId = Integer.parseInt(responseArr[0]);
			
			
		}
		
		
		return taskId;
	}

	
	public static void main(String[] args) {
		// TODO Auto-generated method stub
		Server myServer = new Server();
		myServer.runServer();
		

	}

}
