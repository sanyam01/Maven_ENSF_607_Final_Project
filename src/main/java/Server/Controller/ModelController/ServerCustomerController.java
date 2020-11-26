package Server.Controller.ModelController;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.io.PrintWriter;
import java.net.Socket;
import java.util.ArrayList;
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
import Server.Model.CustomerList;

public class ServerCustomerController {

	private int taskId;
	DBController dbController;
	private Customer customer = null;
	private CustomerList customerList;

	ObjectMapper objectMapper;
	private String message;

//	public ServerCustomerController(String response, DBController dbC, ObjectMapper objectMapper) throws IOException {
//		System.out.println("CustomerController constructor called ");
//
//		this.message = response;
//		this.dbController = dbC;
//		this.objectMapper = objectMapper;
//
//	}
	
	
	public ServerCustomerController( DBController dbC, ObjectMapper objectMapper) throws IOException {
//		System.out.println("CustomerController constructor called ");

		this.message = null;
		this.dbController = dbC;
		this.objectMapper = objectMapper;

	}

	public String readClientMessage(String clientRequest) {
		String[] responseArr = null;
		String switchBoardResponse = null;
		message = clientRequest;

		System.out.println("Request from client: " + message);

		if (message != null) {
			responseArr = message.split(" ", 2);
			switchBoardResponse = switchBoard(responseArr);

		}

		return switchBoardResponse;

	}

	public String switchBoard(String[] responseArr) {

		// 1 search based on client-id
		// 2 search based on lastname
		// 3 search based on client type
		// 4 save
		// 5 delete

		int choice = Integer.parseInt(responseArr[0]);
		System.out.println("choice is: " + choice);
		String jsonCustomerList = null;
		ArrayList<Customer> cust;
		boolean flag;

		switch (choice) {

		case 1:

			System.out.println("Operation: Get customer by id");
			System.out.println(responseArr[1]);

			cust = dbController.getCustomerbyId(Integer.parseInt(responseArr[1]));
			if (!cust.isEmpty()) {
				customerList = new CustomerList(cust);

				try {
					jsonCustomerList = objectMapper.writeValueAsString(customerList);
					System.out.println("Sending to client: " + jsonCustomerList);

				} catch (JsonProcessingException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			} else {
				jsonCustomerList = "ERROR !! Customer not found!!";
//				System.out.println("Customer not found!!");

			}

			break;

		case 2:
			// get customer based on lastname

			System.out.println("Operation: Get customer by lastname");
			System.out.println(responseArr[1]);

			cust = dbController.getCustomerbyLname(responseArr[1]);

			if (!cust.isEmpty()) {
				customerList = new CustomerList(cust);

				try {
					jsonCustomerList = objectMapper.writeValueAsString(customerList);
					System.out.println("Sending to client: " + jsonCustomerList);

				} catch (JsonProcessingException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}

			} else {
				jsonCustomerList = "ERROR !! Customer not found!!";
//				System.out.println("Customer not found!!");

			}

			break;

		case 3:
			// get customer based on type

			System.out.println("Operation: Get customer by type");
			System.out.println(responseArr[1]);

			cust = dbController.getCustomerbyType(responseArr[1]);
			if (!cust.isEmpty()) {
				customerList = new CustomerList(cust);

				try {
					jsonCustomerList = objectMapper.writeValueAsString(customerList);
					System.out.println("Sending to client: " + jsonCustomerList);

				} catch (JsonProcessingException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}

			} else {
				jsonCustomerList = "ERROR !! Customer not found!!";
//				System.out.println("Customer not found!!");

			}

			break;

		case 4:
			// save customer

			System.out.println("Operation: Save");

//			System.out.println("printing responseArr[1]");
			System.out.println(responseArr[1]);

			try {
				this.customer = objectMapper.readValue(responseArr[1], Customer.class);
			} catch (JsonParseException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			} catch (JsonMappingException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}

			// check if customer is new or already present

			cust = dbController.getCustomerbyId(customer.getCustomerID());
			customerList = new CustomerList(cust);
			if (!cust.isEmpty()) {
				System.out.println("customer is present, updating");

				// update
				flag = dbController.updateCustomer(customer.getFirstName(), customer.getLastName(),
						customer.getAddress(), customer.getPostalCode(), customer.getPhoneNumber(),
						customer.getCustomerType(), customer.getCustomerID());
				if (flag) {

//					jsonCustomerList = "4 " + flag + " update";
					jsonCustomerList = "Customer updated successfully";

				} else {
					jsonCustomerList = "ERROR !! Customer update failed";
				}
			} else {
				System.out.println("customer not present, inserting");
				// insert
				flag = dbController.insertCustomer(customer.getFirstName(), customer.getLastName(),
						customer.getAddress(), customer.getPostalCode(), customer.getPhoneNumber(),
						customer.getCustomerType(), customer.getCustomerID());
				if (flag) {

					jsonCustomerList = "Customer inserted successfully";

				} else {
					jsonCustomerList = "ERROR !! Customer insert failed";
				}

			}

			break;

		case 5:
			// delete customer

			System.out.println("Operation: Delete customer");

			System.out.println(responseArr[1]);

			try {
				this.customer = objectMapper.readValue(responseArr[1], Customer.class);
			} catch (JsonParseException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			} catch (JsonMappingException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}

			// check if customer is new or already present

			cust = dbController.getCustomerbyId(customer.getCustomerID());
			customerList = new CustomerList(cust);
			if (!cust.isEmpty()) {
				System.out.println("customer is present, deleting");
				// delete
				flag = dbController.deleteCustomer(customer.getCustomerID());
				if (flag) {

					jsonCustomerList = "Customer deleted successfully";

				} else {
					jsonCustomerList = "ERROR !! Customer delete failed";
				}

			} else {
				System.out.println("ERROR !! customer is not present to delete");
				// customer does not exist
				jsonCustomerList = "ERROR !! Delete failed, Customer does not exist.";
			}

			break;

		default:
			System.out.println("Invalid choice!");

		}

		return jsonCustomerList;

	}

}
