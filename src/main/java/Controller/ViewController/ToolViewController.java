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
			if (e.getSource() == toolView.getClearSearch())
				clearSearch();
			if (e.getSource() == toolView.getDecrease())
				decreaseItem();
			if(e.getSource() == toolView.getClear())
				clearItem();
			if(e.getSource() == toolView.getCheckQuantity())
				findCheckQuantity();
			if(e.getSource() == toolView.getPrintOrder())
				printOrder();
		}

		@Override
		public void valueChanged(ListSelectionEvent e) {

			int index = toolView.getToolList().getSelectedIndex();
			System.out.println("First index is" + index);
			getSelectedToolInfo(index);
		}

	}
	
	public void printOrder() {
		
		String value = this.getModelControllerTool().printOrder();
		printToolListGUI(value);
	}
	
	private void clearItem() {
		
		System.out.println("Clear has been called");
		clearToolFields();
		toolView.getStatusText().setText("Item Info is cleared");
	}

	private void clearSearch() {
		toolView.getGroup().clearSelection();
		toolView.getSearchParameter().setText("");
		toolView.clearToolList();

	}

	private void decreaseItem() {
		System.out.println("Decrease has been called");
		fetchItemInformation();
		String response = modelControllerTool.sendItemInfoDelete();
		toolView.getStatusText().setText(response);
		clearDecreaseGUI();

	}
	
	private void clearDecreaseGUI() {
		clearToolFields();
		
	}
	
	
	private void clearToolFields() {
		
		toolView.getToolID().setText("");
		toolView.getToolName().setText("");
		toolView.getToolType().setSelectedItem("");
		toolView.getToolPrice().setText("");
		toolView.getToolQuantity().setText("");
		toolView.getSupplierID().setText("");
		toolView.getPowerType().setText("");
	}

	private void fetchItemInformation() {

		int iD = Integer.parseInt(this.getToolView().getToolID().getText());
		String name = this.getToolView().getToolName().getText();
		String type = (String)this.getToolView().getToolType().getSelectedItem();
		int quantity = Integer.parseInt(this.getToolView().getToolQuantity().getText());
		int price =  Integer.parseInt(this.getToolView().getToolPrice().getText());
		int supplierID = Integer.parseInt(this.getToolView().getSupplierID().getText());
		String itemType = this.toolView.getPowerType().getText();
		System.out.println("Selecte item is " + type);
		this.modelControllerTool.setItem(iD, name, type, quantity, price, supplierID);

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

	private void setValuesToolGUI(String itemID, String itemName, String itemType, String itemPrice,
			String itemQuantity,String supplierID) {

		toolView.getToolID().setText(itemID);
		toolView.getToolName().setText(itemName);
		toolView.getToolPrice().setText(itemPrice);
		toolView.getToolQuantity().setText(itemQuantity);
		toolView.getSupplierID().setText(supplierID);
		System.out.println("The value of ttpe is " + itemType);
		if (itemType.contentEquals("Electrical"))
			toolView.getToolType().setSelectedIndex(1);
		else
			toolView.getToolType().setSelectedIndex(2);

	}

	private void findSearchType() {
		if (toolView.getSearchToolID().isSelected())
			searchClientID();
		else if (toolView.getSearchToolName().isSelected())
			searchName();
		else
			System.out.println("Could find an option on which valid search is to be made");

	}
	
	private void findCheckQuantity() {
		if (toolView.getSearchToolID().isSelected())
			checkClientID();
		else if (toolView.getSearchToolName().isSelected())
			checkName();
		else
			System.out.println("Could find an option on which checkQuantity can be done");

		
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
	
	private void checkClientID() {
		
		String toolID = toolView.getSearchParameter().getText();
		String response = modelControllerTool.checkToolID(toolID);
		printToolListGUI(response);
		addListenerList();
		
	}
	
	private void checkName() {
		String name = toolView.getSearchParameter().getText();
		String response = modelControllerTool.checkToolName(name);
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
