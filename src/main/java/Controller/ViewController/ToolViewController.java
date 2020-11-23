package Controller.ViewController;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.event.ListSelectionEvent;
import javax.swing.event.ListSelectionListener;

import ClientView.ToolView;
import Controller.ModelController.ModelControllerTool;
import Controller.ViewController.CustomerViewController.CustomerListener;

public class ToolViewController {

	private ToolView toolView;
	private ModelControllerTool modelControllerTool;

	public ToolViewController(ModelControllerTool modelControllerTool) {
		toolView = new ToolView();
		toolView.setVisible(true);
		toolView.pack();
		toolView.addToolListener(new ToolListener());
		this.modelControllerTool = modelControllerTool;
	}

	class ToolListener implements ActionListener, ListSelectionListener {

		public void actionPerformed(ActionEvent e) {
			// TODO Auto-generated method stub
			if (e.getSource() == toolView.getSearch())
				findSearchType();
			if (e.getSource() == toolView.getListAllTools())
				getAllTools();
			if(e.getSource() == toolView.getClearSearch())
				clearSearch();
		}



		private void clearSearch() {
			toolView.getGroup().clearSelection();
			toolView.getSearchParameter().setText("");
			//customerView.getCustomerList().removeAll();
			toolView.clearToolList();
			
		}



		@Override
		public void valueChanged(ListSelectionEvent e) {
			
			int index = toolView.getToolList().getSelectedIndex();
			System.out.println("First index is" + index);
			getSelectedToolInfo(index);
		}

	}

	// getting all the tools
	private void getAllTools() {
	
		String response = modelControllerTool.getAllTools();
		printToolListGUI(response);
		addListenerList();
		
	}
	private void getSelectedToolInfo(int index) {

		String values = this.modelControllerTool.getIndexTool(index);
		System.out.println("Values recieved to display on GUI are" + values);
		String[] data = values.split("!!!");
		setValuesToolGUI(data[0], data[1], data[2], data[3], data[4], data[5]);

	}

	private void setValuesToolGUI(String itemID, String itemType, String itemName, String itemPrice, String itemQuantity,
			String supplierID) {
		
		toolView.getToolID().setText(itemID);
		toolView.getToolName().setText(itemName);
		toolView.getToolPrice().setText(itemPrice);
		toolView.getToolQuantity().setText(itemQuantity);
		toolView.getSupplierID().setText(supplierID);
		System.out.println("The value of ttpe is " + itemType);
		if (itemType.contentEquals("Electrical"))
			toolView.getToolType().setSelectedIndex(1);
		else
			toolView.getToolType().setSelectedIndex(2);;
		
	}

	private void findSearchType() {
		if (toolView.getSearchToolID().isSelected())
			searchClientID();
		else if (toolView.getSearchToolName().isSelected())
			searchName();
		else
			System.out.println("Could find an option on which valid search is to be made");

	}

	private void searchName() {
		String toolName = toolView.getSearchParameter().getText();
		String response = modelControllerTool.searchToolName(toolName);
		printToolListGUI(response);
		addListenerList();
		
	}

	private void searchClientID() {
		String toolID = toolView.getSearchParameter().getText();
		String response = modelControllerTool.searchToolID(toolID);
		printToolListGUI(response);
		addListenerList();

	}

	// method for adding action listeners to the list
	public void addListenerList() {
		toolView.addListenerList(new ToolListener());
	}

	private void printToolListGUI(String response) {
		toolView.addToolList(response);

	}

	public ToolView getToolView() {
		return toolView;
	}

	public void setToolView(ToolView toolView) {
		this.toolView = toolView;
	}

	public ModelControllerTool getModelControllerTool() {
		return modelControllerTool;
	}

	public void setModelControllerTool(ModelControllerTool modelControllerTool) {
		this.modelControllerTool = modelControllerTool;
	}

}
