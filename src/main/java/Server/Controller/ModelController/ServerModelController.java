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

public class ServerModelController implements Runnable {

	ObjectMapper objectMapper;
	private DBController dbController;
	private Socket socket;
	private PrintWriter socketOut;
	private BufferedReader socketIn;
	private ServerCustomerController serverCustomerController;
	private ServerInventoryController serverToolController;

	public ServerModelController(Socket socket, DBController dbController) {

		objectMapper = new ObjectMapper();
		objectMapper.enableDefaultTyping();
//		objectMapper.enableDefaultTyping(ObjectMapper.DefaultTyping , JsonTypeInfo.As includeAs);

		this.dbController = dbController;
		this.socket = socket;

		try {
			this.serverCustomerController = new ServerCustomerController(dbController, objectMapper);
			System.out.println("serverCustomerController instantiated");
		} catch (IOException e) {
			System.out.println("serverCustomerController instantiation failed");
			e.printStackTrace();
		}
		try {
			this.serverToolController = new ServerInventoryController(dbController, objectMapper);
			System.out.println("ServerToolController instantiated");

		} catch (IOException e) {
			System.out.println("ServerToolController instantiation failed");
			e.printStackTrace();

		}
	}

	@Override
	public void run() {
		// TODO Auto-generated method stub
		String message = getClientMessage();
		int taskId = getTaskId(message);
//		System.out.println(" taskid is " + taskId);

		while (true) {

			if (taskId > 0 && taskId < 6) {
				System.out.println("Calling CustomerController");

				// call runCustomerController
				runCustomerController(message);

			} else if (taskId >= 6 && taskId < 12) {

				System.out.println("Calling InventoryController");
				runInventoryController(message);

			}

		}
//		closeSockets();

	}

	public void runCustomerController(String message) {

		// ServerCustomerController serverCustomerController = new
		// ServerCustomerController(message, dbController,
		// objectMapper);
		String messageToClient = serverCustomerController.readClientMessage(message);

		// sending message to client
		setClientMessage(messageToClient);

	}

	public void runInventoryController(String message) {
		String messageToClient = serverToolController.readClientMessage(message);

		// sending message to client
		setClientMessage(messageToClient);

	}

	public String getClientMessage() {

		System.out.println("Getting taskid");
		String request = null;

		try {
			socketIn = new BufferedReader(new InputStreamReader(socket.getInputStream()));
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		try {
			request = socketIn.readLine();
		} catch (IOException e) {

			e.printStackTrace();
		}
		System.out.println("Request from client: " + request);
		return request;

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

	public void closeSockets() {
		try {
			// Close all sockets and streams
			socket.close();
			socketIn.close();
			socketOut.close();

		} catch (IOException e) {
			System.out.println("Error closing sockets!");
		}
	}

}
