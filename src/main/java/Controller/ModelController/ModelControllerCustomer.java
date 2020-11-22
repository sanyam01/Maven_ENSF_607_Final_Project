package Controller.ModelController;

import ClientModel.Customer;
import Controller.ClientController.ClientControllerCustomer;

public class ModelControllerCustomer {
	
	//private CustomerViewController customerViewController;
	private Customer customer;
	private ClientControllerCustomer clientControllerCustomer;
	
	public ModelControllerCustomer() {
		clientControllerCustomer = new ClientControllerCustomer();
	}

	// this is called when save is pressed for storing customer information
	public void setCustomer(int iD, String firstName, String lastName, String address, String postalCode, String phoneNo, String type) {

		this.customer = new Customer(iD, firstName, lastName, address, postalCode,phoneNo,type);
		System.out.println(firstName  +  lastName);
	}
	public Customer getCustomer() {
		return customer;
	}
	
//	public void setCustomerViewController(CustomerViewController customerViewController) {
//		this.customerViewController = customerViewController;
//		
//	}
//	
	
	

}
