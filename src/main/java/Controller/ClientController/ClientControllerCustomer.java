package Controller.ClientController;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.Socket;

public class ClientControllerCustomer {

	private Socket customerSocket;
	private ClientController clientController;
	private PrintWriter socketOut;
	private BufferedReader socketIn;

	public ClientControllerCustomer() {

	}
	
	public void getSockets() {
		try {
			this.customerSocket = clientController.getaSocket();
			//this.socketIn = new BufferedReader(new InputStreamReader(customerSocket.getInputStream()));
			this.socketOut = new PrintWriter(customerSocket.getOutputStream(), true);
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	public String saveCustomer(String customerInfo) {
		
		getSockets();
		socketOut.println(customerInfo);
		return("Customer has been saved");
	}

	public Socket getCustomerSocket() {
		return customerSocket;
	}

	public void setCustomerSocket(Socket customerSocket) {
		this.customerSocket = customerSocket;
	}

	public ClientController getClientController() {
		return clientController;
	}

	public void setClientController(ClientController clientController) {
		this.clientController = clientController;
	}

	public PrintWriter getSocketOut() {
		return socketOut;
	}

	public void setSocketOut() {
		try {
			this.socketOut = new PrintWriter(customerSocket.getOutputStream(), true);
		} catch (IOException e) {
			System.err.println("Unable to create SocketOut");
			e.printStackTrace();
		}
	}

	public BufferedReader getSocketIn() {
		return socketIn;
	}

	
//	public void setSocketIn() {
//		try {
//			this.socketIn = new BufferedReader(new InputStreamReader(customerSocket.getInputStream()));
//		} catch (IOException e) {
//			
//			System.err.println("Unable to create SocketIn");
//			e.printStackTrace();
//		};
//	}

	public String searchClientID(String searchID) {
		socketOut.println(searchID);
		String response = "";
		try {
			System.out.println("I am waiting for the input");
			this.socketIn = new BufferedReader(new InputStreamReader(customerSocket.getInputStream()));
			response = socketIn.readLine();
			System.out.println("I have recived the input");
			System.out.println("The input is " + response);
		} catch (IOException e) {
			System.out.println("In client controller couldnt get anything in return to the query for searching customer based on the customer ID");
			e.printStackTrace();
		}
		return response;
	}
	
	

}
