package Server.Model;

import java.io.Serializable;
import java.util.ArrayList;

public class CustomerList {
	
	
	/**
	 * list of customers
	 */
	private ArrayList<Customer> customerList;
	
	public CustomerList(ArrayList<Customer> list) {
		this.setCustomerList(list);
	}

	public ArrayList<Customer> getCustomerList() {
		return customerList;
	}

	public void setCustomerList(ArrayList<Customer> customerList) {
		this.customerList = customerList;
	}
	
	
	/**
	 * addSupplier(Suppliers supplier) adds the supplier to the list of suppliers
	 * 
	 * @param supplier represents the supplier
	 */
	public void addCustomers(Customer customer) {
		customerList.add(customer);

	}
	

}
