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
			this.socketIn = new BufferedReader(new InputStreamReader(customerSocket.getInputStream()));
			this.socketOut = new PrintWriter(customerSocket.getOutputStream(), true);
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

//	public String saveDeleteCustomer(String customerInfo) {
//		
//		getSockets();
//		socketOut.println(customerInfo);
//		String response = "";
//		try {
//			response = response + socketIn.readLine();
//		} catch (IOException e) {
//			System.out.println("In ClientControllerCustomer, unable to read from server after sending the customer information to save");
//			e.printStackTrace();
//		}
//		System.out.println("Got response");
//		return response;
//	}
//
//	public String searchClient(String searchID) {
//		socketOut.println(searchID);
//		String response = "";
//		try {
//			this.socketIn = new BufferedReader(new InputStreamReader(customerSocket.getInputStream()));
//			response = response + socketIn.readLine();
//			System.out.println("The input is " + response);
//		} catch (IOException e) {
//			System.out.println("In client controller couldnt get anything in return to the query for searching customer based on the customer ID");
//			e.printStackTrace();
//		}
//		return response;
//	}

	public String sendQuery(String query) {
		getSockets();
		socketOut.println(query);
		String response = "";
		if ((response.split("!!!")[0]).contentEquals("ERROR")){
			System.out.println("ERROR");
		}
		try {
			this.socketIn = new BufferedReader(new InputStreamReader(customerSocket.getInputStream()));
			response = response + socketIn.readLine();
			System.out.println("The input is " + response);
		} catch (IOException e) {
			System.out.println("Didn't get any response from server");
			e.printStackTrace();
		}
		return response;
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

	public BufferedReader getSocketIn() {
		return socketIn;
	}

}
