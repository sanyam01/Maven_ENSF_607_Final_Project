package Controller.ClientController;

import java.net.Socket;

public class ClientControllerTool {

	private ClientController clientController;

	public ClientControllerTool() {

		try {
			clientController = new ClientController("localhost", 9090);
		} catch (ClassNotFoundException e) {
			System.out.println("Inside ClientControllerCustomer server not found");
			e.printStackTrace();
		}

	}

}
