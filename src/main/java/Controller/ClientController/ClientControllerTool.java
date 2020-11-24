package Controller.ClientController;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.Socket;

public class ClientControllerTool {

	private Socket toolSocket;
	private ClientController clientController;
	private PrintWriter socketOut;
	private BufferedReader socketIn;

	public ClientControllerTool() {

	}

	public Socket getToolSocket() {
		return toolSocket;
	}

	public void setToolSocket(Socket toolSocket) {
		this.toolSocket = toolSocket;
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
			this.socketOut = new PrintWriter(toolSocket.getOutputStream(), true);
		} catch (IOException e) {
			System.err.println("Unable to create SocketOut");
			e.printStackTrace();
		}
	}

	public BufferedReader getSocketIn() {
		return socketIn;
	}

	
	public void setSocketIn() {
		try {
			this.socketIn = new BufferedReader(new InputStreamReader(toolSocket.getInputStream()));
		} catch (IOException e) {
			
			System.err.println("Unable to create SocketIn");
			e.printStackTrace();
		};

}

	public void getSockets() {
		
		try {
			this.toolSocket = clientController.getaSocket();
			this.socketOut = new PrintWriter(toolSocket.getOutputStream(), true);
			this.socketIn = new BufferedReader(new InputStreamReader(toolSocket.getInputStream()));
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
	}

	public String searchTool(String toolID) {
		socketOut.println(toolID);
		String response = "";
		try {
			this.socketIn = new BufferedReader(new InputStreamReader(toolSocket.getInputStream()));
			response = response + socketIn.readLine();
			System.out.println("The input is " + response);
		} catch (IOException e) {
			System.out.println("In client controller couldnt get anything in return to the query for searching tool based on the tool ID");
			e.printStackTrace();
		}
		return response;
	}

	public String saveDeleteTool(String toolInfo) {
		getSockets();
		socketOut.println(toolInfo);
		String response = "";
		try {
			response = response + socketIn.readLine();
		} catch (IOException e) {
			System.out.println("In ClientControllerTool, unable to read from server after sending the tool information to save");
			e.printStackTrace();
		}
		System.out.println("Got the response");
		return response;
	}

	public String printOrder(String orderID) {
		socketOut.println(orderID);
		String response = "";
		try {
			this.socketIn = new BufferedReader(new InputStreamReader(toolSocket.getInputStream()));
			response = response + socketIn.readLine();
			System.out.println("The input is " + response);
		} catch (IOException e) {
			System.out.println("In client controller couldnt get anything in return to the query for printing order based on the tool ID");
			e.printStackTrace();
		}
		return response;
	}
	
	
}
