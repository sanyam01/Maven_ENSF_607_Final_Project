package Controller.ViewController;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.ListSelectionModel;
import javax.swing.event.ListSelectionEvent;
import javax.swing.event.ListSelectionListener;

//import ClientView.CustomerListener;
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
	

	class CustomerListener implements ActionListener, ListSelectionListener{

		public void actionPerformed(ActionEvent e) {
			
			if (e.getSource() == customerView.getSearch()) {
				System.out.println("got search button");
				findSearchType();
				
			}
			if (e.getSource() == customerView.getSave()) 
				saveCustomer();
				
			
		}

		@Override
		public void valueChanged(ListSelectionEvent e) {
			// TODO Auto-generated method stub
			int index = e.getFirstIndex();
			System.out.println("First index is" + e.getFirstIndex());
			getSelectedCustInfo(index);
		}
		
	}
	
	// displaying the info of the selected customer
	private void getSelectedCustInfo(int index) {
		
		String values = this.modelControllerCustomer.getIndexCustomer(index);
		String[] data = values.split(" ");
		setValuesCustGUI(data[0], data[1], data[2], data[3], data[4], data[5], data[6]);
		
	}
	
	// sets the passed values in GUI
	private void setValuesCustGUI(String id, String fname, String lname, String address, String postalCode, String phoneNumber, String type) {
		
		customerView.getCustomerID().setText(id);
		customerView.getFirstName().setText(fname);
		customerView.getLastName().setText(lname);
		customerView.getAddress().setText(address);
		customerView.getPostalCode().setText(postalCode);
		customerView.getPhoneNo().setText(phoneNumber);
		customerView.getTypeClient().setSelectedItem(type);;
		
	}

	// checks the type on which search is to be done for entered text
	public void findSearchType() {
		if (customerView.getSearchCustomerID().isSelected())
			searchClientID();
//		else if (customerView.getSearchLastName().isSelected())
//			searchLastName();
//		else if (customerView.getSearchCustomerType().isSelected())
//			searchCustomerType();
		else
			System.out.println("Could find an option on which valid search is to be made");
	}

	// gets the client ID and passes it
	private void searchClientID() {
		String clientID = customerView.getSearchParameter().getText();
		String response = modelControllerCustomer.searchClientID(clientID);
		printCustListGUI(response);
		addListenerList();
		
	}
	
	// method for adding action listeners to the list 
		public void addListenerList() {
			//ListSelectionModel listSelectionModel = customerView..getSelectionModel();
		    //listSelectionModel.addListSelectionListener(new CustomerListener());
			customerView.addListenerList(new CustomerListener());
		}

	private void printCustListGUI(String response) {
		customerView.addCustomerList(response);
		
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
		
	}


	public void setCustomerView(CustomerView customerView) {
		this.customerView = customerView;
	}


	public ModelControllerCustomer getModelControllerCustomer() {
		return modelControllerCustomer;
	}
	
	
	
	
}
