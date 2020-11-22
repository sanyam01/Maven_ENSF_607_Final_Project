package Controller.ViewController;

import ClientView.*;
import Controller.ModelController.ModelControllerCustomer;
import Controller.ModelController.ModelControllerTool;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class MainViewController implements ActionListener {

	MainView view;
	CustomerViewController customerViewController;
	ToolViewController toolViewController;
	

	public MainViewController(MainView view) {
		this.view = view;
	}


	public void actionPerformed(ActionEvent e) {
		
		
		if (e.getSource() == view.getCustomerView()) {
			System.out.println("CustomerView");
			customerViewController = new CustomerViewController(new ModelControllerCustomer());

		}

		else if (e.getSource() == view.getToolView()) {
			System.out.println("ToolView");
			toolViewController = new ToolViewController(new ModelControllerTool());
			
		}

		
	}

}
