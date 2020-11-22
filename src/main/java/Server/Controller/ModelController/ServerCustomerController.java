package Server.Controller.ModelController;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.io.PrintWriter;
import java.net.Socket;
import java.util.Map;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.core.JsonParseException;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.JsonMappingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.google.gson.Gson;
import com.google.gson.JsonObject;

import Server.Controller.DatabaseController.DBController;
import Server.Model.Customer;

public class ServerCustomerController implements Runnable {

//	private Socket socket;
//	private PrintWriter socketOut;
//	private BufferedReader socketIn;
	private int taskId;
	DBController dbController;
	Customer customer = null;
//	ObjectInputStream serverInputStream;
//	ObjectOutputStream serverOutputStream;
	ObjectMapper objectMapper;
	private String response;

	public ServerCustomerController(String response, DBController dbC, ObjectMapper objectMapper) throws IOException {
		System.out.println("CustomerController constructor called ");

		this.response = response;
		this.dbController = dbC;
		this.objectMapper = objectMapper;

		// Socket input Stream
//		socketIn = new BufferedReader(new InputStreamReader(socket.getInputStream()));

		// Socket output Stream
//					socketOut = new PrintWriter(socket.getOutputStream(), true);

	}


	public String run_temp() throws IOException {
		String[] responseArr = null;
		System.out.println("in run_temp");
		String switchBoardResponse = null;
		
//			socketIn = new BufferedReader(new InputStreamReader(socket.getInputStream()));
//			System.out.println(socketIn.readLine());
//			response = socketIn.readLine();

		System.out.println("Response from client: " + response);

		if (response != null) {
//			String jsonCustomer = objectMapper.writeValueAsString(response);
			responseArr = response.split(" ", 2);
			switchBoardResponse = switchBoard(responseArr);

			
		}

		return switchBoardResponse;

	}

	public String switchBoard(String[] responseArr)  {

		// 1 search based on client-id
		// 2 search based on lastname
		// 3 search based on client type
		// 4 save
		// 5 delete

		int choice = Integer.parseInt(responseArr[0]);
		System.out.println("choice is: "+ choice);
		String jsonCustomer = null ;

		switch (choice) {

		case 1:
			// get customer based on id
//			customer = testCustomerObject();

			System.out.println("Sending getcustomer search to client");
			System.out.println(responseArr[1]);

	        customer = dbController.getCustomerbyId(Integer.parseInt(responseArr[1]));
			
			try {
				jsonCustomer = objectMapper.writeValueAsString(customer);
				System.out.println("Sending to client: "+jsonCustomer);
				
			} catch (JsonProcessingException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			


			break;

		}
		
		return jsonCustomer;

	}

	@Override
	public void run() {
		// TODO Auto-generated method stub

	}

}
