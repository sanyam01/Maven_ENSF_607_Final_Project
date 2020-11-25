package Controller.ViewController;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.IOException;

import javax.swing.event.ListSelectionEvent;
import javax.swing.event.ListSelectionListener;

import com.fasterxml.jackson.core.JsonParseException;
import com.fasterxml.jackson.databind.JsonMappingException;

import ClientModel.ElectricalItem;
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

	private void findSearchType() {
		if (toolView.getSearchToolID().isSelected())
			searchClientID();
		else if (toolView.getSearchToolName().isSelected())
			searchName();
		else {
			toolView.getStatusText().setText("Please select a valid search based type");
		}

	}

	private void searchClientID() {
		String toolID = toolView.getSearchParameter().getText();
		String response = modelControllerTool.searchToolID(toolID);
		if (response.split("!!")[0].contentEquals("ERROR"))
			toolView.getStatusText().setText(response.split("!!")[1]);
		else {
			printToolListGUI(response);
			addListenerList();
		}

	}

	private void searchName() {
		String toolName = toolView.getSearchParameter().getText();
		String response = modelControllerTool.searchToolName(toolName);
		if (response.split("!!")[0].contentEquals("ERROR"))
			toolView.getStatusText().setText(response.split("!!")[1]);
		else {
			printToolListGUI(response);
			addListenerList();
		}

	}

	private void findCheckQuantity() {
		if (toolView.getSearchToolID().isSelected())
			checkClientID();
		else if (toolView.getSearchToolName().isSelected())
			checkName();
		else
			toolView.getStatusText().setText("Please select a valid type for checking the quantity");

	}

	private void checkClientID() {

		String toolID = toolView.getSearchParameter().getText();
		String response = modelControllerTool.checkToolID(toolID);
		if (response.split("!!")[0].contentEquals("ERROR"))
			toolView.getStatusText().setText(response.split("!!")[1]);
		else {
			printToolListGUI(response);
			addListenerList();
		}

	}

	private void checkName() {
		String name = toolView.getSearchParameter().getText();
		String response = modelControllerTool.checkToolName(name);
		if (response.split("!!")[0].contentEquals("ERROR"))
			toolView.getStatusText().setText(response.split("!!")[1]);
		else {
			printToolListGUI(response);
			addListenerList();
		}

	}

	// getting all the tools
	private void getAllTools() {

		String response = modelControllerTool.getAllTools();
		if (response.split("!!")[0].contentEquals("ERROR"))
			toolView.getStatusText().setText(response.split("!!")[1]);
		else {
			printToolListGUI(response);
			addListenerList();
		}

	}

	private void clearSearch() {
		toolView.getGroup().clearSelection();
		toolView.getSearchParameter().setText("");
		toolView.clearToolList();
		toolView.getStatusText().setText("Clear Search has been performed");

	}

	private void clearItem() {

		System.out.println("Clear has been called");
		clearToolFields();
		toolView.getStatusText().setText("Item Info is cleared");
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

	private void decreaseItem() {
		System.out.println("Decrease has been called");
		String response = fetchItemInformation();
		toolView.getStatusText().setText(response);
		clearToolFields();

	}

	private String fetchItemInformation() {
		String response = "";
		try {
			int iD = Integer.parseInt(this.getToolView().getToolID().getText());
			response = this.modelControllerTool.sendItemInfoDecrease(iD);

		} catch (Exception e) {
			toolView.getStatusText().setText("Please enter the valid values in all the text fields");
			System.err.println("Please enter valid inputs");
		}
		return response;

	}

//	private void clearDecreaseGUI() {
//
//	}

	// if item to be decreased is passed using the whole information
//	private void fetchItemInformation() {
//
//		try {
//			int iD = Integer.parseInt(this.getToolView().getToolID().getText());
//			String name = this.getToolView().getToolName().getText();
//			String type = (String) this.getToolView().getToolType().getSelectedItem();
//			int quantity = Integer.parseInt(this.getToolView().getToolQuantity().getText());
//			float price = Float.parseFloat(this.getToolView().getToolPrice().getText());
//			int supplierID = Integer.parseInt(this.getToolView().getSupplierID().getText());
//			String powerType = this.toolView.getPowerType().getText();
//			
//			System.out.println("Select item is " + type);
//			if (type.contentEquals("Electrical"))
//				this.modelControllerTool.setItem(new ElectricalItem(iD, name, quantity, price, type, supplierID, powerType));
//		} catch (Exception e) {
//			toolView.getStatusText().setText("Please enter the valid values in all the text fields");
//			System.err.println("Please enter valid inputs");
//		}
//
//	}

	// need
	private void getSelectedToolInfo(int index) {

		String values = this.modelControllerTool.getIndexTool(index);
		System.out.println("Values recieved to display on GUI are" + values);
		String[] data = values.split("!!!");
		if (data[2].contentEquals("Electrical"))
			setValuesToolGUI(data[0], data[1], data[2], data[3], data[4], data[5], data[6]);
		else
			setValuesToolGUI(data[0], data[1], data[2], data[3], data[4], data[5], "");

	}

	private void setValuesToolGUI(String itemID, String itemName, String itemType, String itemPrice,
			String itemQuantity, String supplierID, String powerType) {

		toolView.getToolID().setText(itemID);
		toolView.getToolName().setText(itemName);
		toolView.getToolPrice().setText(itemPrice);
		toolView.getToolQuantity().setText(itemQuantity);
		toolView.getSupplierID().setText(supplierID);
		toolView.getPowerType().setText(powerType);
		System.out.println("The value of ttpe is " + itemType);
		if (itemType.contentEquals("Electrical")) {
			toolView.getToolType().setSelectedIndex(1);

		} else
			toolView.getToolType().setSelectedIndex(2);

	}

	// method for adding action listeners to the list
	public void addListenerList() {
		toolView.addListenerList(new ToolListener());
	}

	private void printToolListGUI(String response) {
		toolView.addToolList(response);

	}

	public void printOrder() {

		String value = this.getModelControllerTool().printOrder();
		printToolListGUI(value);
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

	class ToolListener implements ActionListener, ListSelectionListener {

		public void actionPerformed(ActionEvent e) {

			if (e.getSource() == toolView.getSearch())
				findSearchType();
			if (e.getSource() == toolView.getListAllTools())
				getAllTools();
			if (e.getSource() == toolView.getClearSearch())
				clearSearch();
			if (e.getSource() == toolView.getDecrease())
				decreaseItem();
			if (e.getSource() == toolView.getClear())
				clearItem();
			if (e.getSource() == toolView.getCheckQuantity())
				findCheckQuantity();
			if (e.getSource() == toolView.getPrintOrder())
				printOrder();
		}

		@Override
		public void valueChanged(ListSelectionEvent e) {

			int index = toolView.getToolList().getSelectedIndex();
			System.out.println("First index is" + index);
			getSelectedToolInfo(index);
		}

	}

}
