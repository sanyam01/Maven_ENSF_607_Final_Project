package Server.Controller.ModelController;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.Socket;

import com.fasterxml.jackson.databind.ObjectMapper;

import Server.Controller.DatabaseController.DBController;
import Server.Model.Customer;
import Server.Model.Items;

public class ServerController implements Runnable {

	ObjectMapper objectMapper;
	private DBController dbController;
	private Socket socket;
	private PrintWriter socketOut;
	private BufferedReader socketIn;


	public ServerController(Socket socket, DBController dbController) {

		objectMapper = new ObjectMapper();
		objectMapper.enableDefaultTyping();
//		objectMapper.enableDefaultTyping(ObjectMapper.DefaultTyping , JsonTypeInfo.As includeAs);

		this.dbController = dbController;
		this.socket = socket;
	}

	@Override
	public void run() {
		// TODO Auto-generated method stub
		String message = getClientMessage();
		int taskId = getTaskId(message);
		System.out.println(" taskid is " + taskId);

		if (taskId > 0 && taskId < 6) {
			System.out.println("customerController instantiated");
			
			//call runCustomerController
			runCustomerController(message);

//			try {
//				ServerCustomerController serverCustomerController = new ServerCustomerController(message, dbController,
//						objectMapper);
//				String messageToClient = serverCustomerController.readClientMessage();
//
//				// sending message to client
//				setClientMessage(messageToClient);
//				
//			} catch (IOException e) {
//				// TODO Auto-generated catch block
//				e.printStackTrace();
//			}

		} else if (taskId >= 6 && taskId < 12) {
			System.out.println("ServerToolController instantiated");

//			try {
//				ServerInventoryController serverToolController = new ServerInventoryController(message, dbController,
//						objectMapper);
//				
//				String messageToClient = serverToolController.readClientMessage();
//
//				// sending message to client
//				setClientMessage(messageToClient);
//				
//				
//			} catch (IOException e) {
//				// TODO Auto-generated catch block
//				e.printStackTrace();
//			}
			runInventoryController(message);
			

		}

	}

	public void runCustomerController(String message) {
		
		try {
			ServerCustomerController serverCustomerController = new ServerCustomerController(message, dbController,
					objectMapper);
			String messageToClient = serverCustomerController.readClientMessage();

			// sending message to client
			setClientMessage(messageToClient);
			
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		
	}
	
	public void runInventoryController(String message) {
		try {
			ServerInventoryController serverToolController = new ServerInventoryController(message, dbController,
					objectMapper);
			
			String messageToClient = serverToolController.readClientMessage();

			// sending message to client
			setClientMessage(messageToClient);
			
			
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
		System.out.println("Request from client: " + response);
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
		int taskId = 0;

		if (response != null) {
//		String jsonCustomer;
			// jsonCustomer = objectMapper.writeValueAsString(response);
			String[] responseArr = response.split(" ", 2);
			taskId = Integer.parseInt(responseArr[0]);

		}

		return taskId;
	}

}
