package Controller.ModelController;



import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.io.PrintWriter;
import java.net.Socket;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;

import Controller.DatabaseController.DBController;
import Model.Customer;



public class ServerCustomerController implements Runnable {

	private Socket socket;
	private PrintWriter socketOut;
	private BufferedReader socketIn;
	private int choice;
	DBController dbController;
	Customer customer = null;
	ObjectInputStream serverInputStream;
	ObjectOutputStream serverOutputStream;

	public ServerCustomerController(Socket socket, DBController dbC) throws IOException {
		System.out.println("CustomerController constructor called ");

		this.socket = socket;
		this.dbController = dbC;
		
		// Socket input Stream
//		socketIn = new BufferedReader(new InputStreamReader(socket.getInputStream()));

					// Socket output Stream
					socketOut = new PrintWriter(socket.getOutputStream(), true);
//		try {
//			System.out.println("bfore");
//			serverInputStream = new ObjectInputStream(socket.getInputStream());
//			System.out.println("after");
//
//		} catch (IOException e) {
//			// TODO Auto-generated catch block
//			e.printStackTrace();
//		}

//		try {
//			serverOutputStream = new ObjectOutputStream(socket.getOutputStream());
//		} catch (IOException e) {
//			// TODO Auto-generated catch block
//			e.printStackTrace();
//		}

	}

	public Customer getCustomerObject() {
		try {
			serverInputStream = new ObjectInputStream(socket.getInputStream());
			customer = (Customer) serverInputStream.readObject();
		} catch (ClassNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		return customer;

	}
	
	public Customer testCustomerObject() {
		
		customer = new Customer(1, "sanyam","empty", "st no0", "23123", "152116", "res");
		return customer;
		
	}



	public void run() {
		String response = "";
		System.out.println("CustomerController instantiated here");
//		try {
//			response = socketIn.readLine();
//			System.out.println("Response from client: " + response);
//			choice = Integer.parseInt(response);
//		} catch (IOException e) {
//
//			e.printStackTrace();
//		}
//
//		if (response != null) {
//			try {
//				switchBoard(choice);
//			} catch (ClassNotFoundException e) {
//				// TODO Auto-generated catch block
//				e.printStackTrace();
//			}
//		}
	}
	
	public void run_temp() throws JsonProcessingException {
		String response = "";
		System.out.println("in run_temp");
		try {
			socketIn = new BufferedReader(new InputStreamReader(socket.getInputStream()));
			response = socketIn.readLine();
			System.out.println("Response from client: " + response);
			choice = Integer.parseInt(response);
		} catch (IOException e) {

			e.printStackTrace();
		}

		if (response != null) {
			System.out.println("I am inside response");
			try {
				switchBoard(choice);
			} catch (ClassNotFoundException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
		
		try {
			socketIn.close();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		try {
			socket.close();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		
	}

	public void switchBoard(int choice) throws ClassNotFoundException, JsonProcessingException {

		switch (choice) {

		case 1:
			// get customer based on id
			customer = testCustomerObject();
			
			System.out.println("Sending getcustomer search to client");
			//String jsonCustomer = JSON.stringify(customer);
			ObjectMapper objectMapper = new ObjectMapper();
			String jsonCustomer = objectMapper.writeValueAsString(customer);
			
			System.out.println(jsonCustomer);
			int id = 1; //customer.getCustomerID()
//			customer = dbController.getDbManager().getCustomerPreparedStatementId(id);
//			System.out.println(customer);
			try {
				//serverOutputStream = new ObjectOutputStream(socket.getOutputStream());
				//serverOutputStream.writeObject((Object)customer);
				socketOut = new PrintWriter(socket.getOutputStream(), true);
				socketOut.println(jsonCustomer);
			} catch (IOException e) {

				e.printStackTrace();
			}

			break;

		}

	}

}
