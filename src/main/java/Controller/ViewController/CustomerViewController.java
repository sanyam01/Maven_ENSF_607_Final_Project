package Controller.ViewController;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import ClientView.CustomerView;
import Controller.ModelController.ModelControllerCustomer;

public class CustomerViewController  {
	
	private CustomerView customerView;
	private ModelControllerCustomer modelControllerCustomer;

	public CustomerViewController(ModelControllerCustomer modelControllerCustomer){
		
		customerView = new CustomerView();
		customerView.setVisible(true);
		customerView.pack();
		customerView.addCustomerListener(new CustomerListener());
		this.modelControllerCustomer = modelControllerCustomer;
	}
	

	class CustomerListener implements ActionListener{

		public void actionPerformed(ActionEvent e) {
			
			if (e.getSource() == customerView.getSearch()) {
				System.out.println("got search button");
				findSearchType();
				
			}
			if (e.getSource() == customerView.getSave()) 
				saveCustomer();
				
			
		}
		
	}

	// checks the type on which search is to be done for entered text
	public void findSearchType() {
		if (customerView.getSearchCustomerID().isSelected())
			searchClientID();
		else if (customerView.getSearchLastName().isSelected())
			searchLastName();
		else if (customerView.getSearchCustomerType().isSelected())
			searchCustomerType();
		else
			System.out.println("Could find an option on which valid search is to be made");
	}

	// gets the client ID and passes it
	private void searchClientID() {
		String clientID = customerView.getSearchParameter().getText();
		String response = modelControllerCustomer.searchClientID(clientID);
		printCustListGUI(response);
		
	}

	private void printCustListGUI(String response) {
		
		
	}

	public CustomerView getCustomerView() {
		return customerView;
	}


	// method for saving the customer info
	public void saveCustomer() {
		System.out.println("Save has been called");
		fetchCustomerInformation();
		String response = modelControllerCustomer.sendCustomerInfo();
		customerView.getStatusText().setText(response);
	}
	
	// reads information of the customer from GUI
	public void fetchCustomerInformation() {
		
		int iD = Integer.parseInt(this.getCustomerView().getCustomerID().getText());
		String firstName = this.getCustomerView().getFirstName().getText();
		String lastName = this.getCustomerView().getLastName().getText();
		String address = this.getCustomerView().getAddress().getText();
		String postalCode = this.getCustomerView().getPostalCode().getText();
		String phoneNo = this.getCustomerView().getPhoneNo().getText();
		String type = (String)this.getCustomerView().getTypeClient().getSelectedItem();
		this.modelControllerCustomer.setCustomer(iD, firstName, lastName, address, postalCode, phoneNo, type);
		System.out.println(firstName  +  lastName);
		
	}


	public void setCustomerView(CustomerView customerView) {
		this.customerView = customerView;
	}


	public ModelControllerCustomer getModelControllerCustomer() {
		return modelControllerCustomer;
	}
	
	
	
	
}
