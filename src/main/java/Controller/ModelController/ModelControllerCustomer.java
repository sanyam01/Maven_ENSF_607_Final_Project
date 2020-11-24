package Controller.ModelController;

import java.io.IOException;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;

import ClientModel.Customer;
import ClientModel.CustomerList;
import Controller.ClientController.ClientControllerCustomer;

public class ModelControllerCustomer {

	private Customer customer, atIndexCustomer;
	private CustomerList customerList;
	private ClientControllerCustomer clientControllerCustomer;

	public ModelControllerCustomer() {
		clientControllerCustomer = new ClientControllerCustomer();
	}

	// this is called when save is pressed for storing customer information
	public void setCustomer(int iD, String firstName, String lastName, String address, String postalCode,
			String phoneNo, String type) {

		this.customer = new Customer(iD, firstName, lastName, address, postalCode, phoneNo, type);
		System.out.println(firstName + lastName);
	}

	public String sendCustomerInfo() {

		ObjectMapper objectMapper = new ObjectMapper();
		String jsonCustomer;
		String response = "";
		try {
			jsonCustomer = objectMapper.writeValueAsString(customer);
			String temp = "4 " + jsonCustomer;
			//response = this.clientControllerCustomer.saveDeleteCustomer(temp);
			response = clientControllerCustomer.sendQuery(temp);

		} catch (JsonProcessingException e) {
			e.printStackTrace();
		}
		return response;

	}

	public String sendCustomerInfoDelete() {

		ObjectMapper objectMapper = new ObjectMapper();
		String jsonCustomer;
		String response = "";
		try {
			jsonCustomer = objectMapper.writeValueAsString(customer);
			String temp = "5 " + jsonCustomer;
			//response = this.clientControllerCustomer.saveDeleteCustomer(temp);
			response = clientControllerCustomer.sendQuery(temp);

		} catch (JsonProcessingException e) {
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

	public CustomerList getCustomerList() {
		return customerList;
	}

	public void setCustomerList(CustomerList customerList) {
		this.customerList = customerList;
	}

	// get customer at a particular index
	public String getIndexCustomer(int index) {

		atIndexCustomer = getCustomerList().getCustomerList().get(index);
		String values = atIndexCustomer.getCustomerID() + "!!!" + atIndexCustomer.getFirstName() + "!!!"
				+ atIndexCustomer.getLastName() + "!!!" + atIndexCustomer.getAddress() + "!!!"
				+ atIndexCustomer.getPostalCode() + "!!!" + atIndexCustomer.getPhoneNumber() + "!!!"
				+ atIndexCustomer.getCustomerType();
		return values;

	}

	// search based on customerID
	public String searchClientID(String clientID) {
		String searchID = "1 " + clientID;
		//String response = clientControllerCustomer.searchClient(searchID);
		String response = clientControllerCustomer.sendQuery(searchID);
		getCustomerListFromJson(response);
		String displayCustomer = getStringCustList();
		return displayCustomer;
	}

	// search based on lastName
	public String searchLastName(String lastName) {
		String searchID = "2 " + lastName;
		//String response = clientControllerCustomer.searchClient(searchID);
		String response = clientControllerCustomer.sendQuery(searchID);
		getCustomerListFromJson(response);
		String displayCustomer = getStringCustList();
		return displayCustomer;
	}

	// search based on customer type
	public String searchCustomerType(String type) {
		String searchID = "3 " + type;
		//String response = clientControllerCustomer.searchClient(searchID);
		String response = clientControllerCustomer.sendQuery(searchID);
		getCustomerListFromJson(response);
		String displayCustomer = getStringCustList();
		return displayCustomer;
	}

	// getting representational form of customer to displa on GUI
	private String getDisplayStringFromCust() {

		String disp = customer.getCustomerID() + " " + customer.getFirstName() + " " + customer.getLastName() + " "
				+ customer.getCustomerType();
		return disp;
	}

	// get string from customer
	// concatinating the customers into string
	public String getStringCustList() {

		String concatCust = "";
		for (Customer cus : this.customerList.getCustomerList())
			concatCust = concatCust + cus.getCustomerID() + " " + cus.getFirstName() + " " + cus.getLastName() + " "
					+ cus.getAddress() + " " + cus.getPostalCode() + " " + cus.getPhoneNumber() + " "
					+ cus.getCustomerType() + "\n";
		return concatCust;
	}

	// convert Json to customer
	private void getCustomerFromJson(String customer) {
		ObjectMapper objectMapper = new ObjectMapper();
		try {
			this.customer = objectMapper.readValue(customer, Customer.class);
		} catch (IOException e) {
			System.out.println("Unable to convert json to customer List");
			e.printStackTrace();
		}
	}

	// convert json string into customer list
	private void getCustomerListFromJson(String customerList) {

		ObjectMapper objectMapper = new ObjectMapper();
		try {
			this.customerList = objectMapper.readValue(customerList, CustomerList.class);
		} catch (IOException e) {
			System.out.println("Unable to convert json to customer List");
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

}
