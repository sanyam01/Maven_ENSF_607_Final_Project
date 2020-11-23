package Controller.ModelController;

import java.io.IOException;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;

import ClientModel.Customer;
import ClientModel.CustomerList;
import Controller.ClientController.ClientControllerCustomer;

public class ModelControllerCustomer {
	

	private Customer customer;
	private CustomerList customerList;
	private ClientControllerCustomer clientControllerCustomer;
	
	public ModelControllerCustomer() {
		clientControllerCustomer = new ClientControllerCustomer();
	}

	// this is called when save is pressed for storing customer information
	public void setCustomer(int iD, String firstName, String lastName, String address, String postalCode, String phoneNo, String type) {

		this.customer = new Customer(iD, firstName, lastName, address, postalCode,phoneNo,type);
		System.out.println(firstName  +  lastName);
	}
	
	public String sendCustomerInfo() {
		
		ObjectMapper objectMapper = new ObjectMapper();
		String jsonCustomer;
		String response = "";
		try {
			jsonCustomer = objectMapper.writeValueAsString(customer);
			String temp = "4 " + jsonCustomer;
			response = this.clientControllerCustomer.saveCustomer(temp);
			
		} catch (JsonProcessingException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return response;
		
		
	}
	public Customer getCustomer() {
		return customer;
	}
	
	public ClientControllerCustomer getClientControllerCustomer() {
		return clientControllerCustomer;
	}

	public void setClientControllerCustomer(ClientControllerCustomer clientControllerCustomer) {
		this.clientControllerCustomer = clientControllerCustomer;
	}

	public void setCustomer(Customer customer) {
		this.customer = customer;
	}

	// search based on customerID
	public String searchClientID(String clientID) {
		String searchID = "1 " + clientID;
		String response = clientControllerCustomer.searchClientID(searchID);
		//String listNameID = getCustomerFromJson(response);
		//String listNameID = getStringCustList();
		return response;
	}
	
	// get string from customer
	
	// concatating the customers into string
	public String getStringCustList() {
		
		String concatCust = "";
		for (Customer cus: this.customerList.getCustomerList())
			concatCust = concatCust + cus.getCustomerID() + " " + cus.getFirstName() + " " + cus.getLastName() + " " + cus.getCustomerType() + "\n";
		return concatCust;
	}
	
	// convert Json to customer
	private void getCustomerFromJson(String customer) {
		ObjectMapper objectMapper = new ObjectMapper();
		//CustomerList custList = null;
		try {
			this.customer = objectMapper.readValue(customer, Customer.class);
		} catch (IOException e) {
			System.out.println("Unable to convert json to customer List");
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	// convert json string into customer list
	private void getCustomerListFromJson(String customerList) {
		
		ObjectMapper objectMapper = new ObjectMapper();
		//CustomerList custList = null;
		try {
			this.customerList = objectMapper.readValue(customerList, CustomerList.class);
		} catch (IOException e) {
			System.out.println("Unable to convert json to customer List");
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	
	

}
