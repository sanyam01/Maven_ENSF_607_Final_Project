package Controller.ViewController;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import ClientView.CustomerView;
import Controller.ModelController.ModelControllerCustomer;

public class CustomerViewController  {
	
	private CustomerView customerView;
	private ModelControllerCustomer modelControllerCustomer;

	public CustomerViewController(ModelControllerCustomer modelControllerCustomer){
		
//		this.modelControllerCustomer = modelControllerCustomer;
		customerView = new CustomerView();
		customerView.setVisible(true);
		customerView.pack();
		customerView.addCustomerListener(new CustomerListener());
		this.modelControllerCustomer = new ModelControllerCustomer();
	}
	

	class CustomerListener implements ActionListener{

		public void actionPerformed(ActionEvent e) {
			// TODO Auto-generated method stub
			if (e.getSource() == customerView.getSearch())
				System.out.println("got search button");
			if (e.getSource() == customerView.getSave()) {
				System.out.println("Save has been called");
				fetchCustomerInformation();
			}
			
		}
		
	}


	public CustomerView getCustomerView() {
		return customerView;
	}


	// gets information of the customer from GUI
	public void fetchCustomerInformation() {
		
		int iD = Integer.parseInt(this.getCustomerView().getCustomerID().getText());
		String firstName = this.getCustomerView().getFirstName().getText();
		String lastName = this.getCustomerView().getLastName().getText();
		String address = this.getCustomerView().getAddress().getText();
		String postalCode = this.getCustomerView().getPostalCode().getText();
		String phoneNo = this.getCustomerView().getPhoneNo().getText();
		String type = this.getCustomerView().getCustomerID().getText();
		this.modelControllerCustomer.setCustomer(iD, firstName, lastName, address, postalCode, phoneNo, type);
		System.out.println(firstName  +  lastName);
		
	}


	public void setCustomerView(CustomerView customerView) {
		this.customerView = customerView;
	}
	
	
}
