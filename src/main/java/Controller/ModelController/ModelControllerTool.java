package Controller.ModelController;

import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;

import com.fasterxml.jackson.core.JsonParseException;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.JsonMappingException;
import com.fasterxml.jackson.databind.ObjectMapper;

import ClientModel.ElectricalItem;
import ClientModel.Items;
import ClientModel.ItemsList;
import ClientModel.NonElectricalItem;
import Controller.ClientController.ClientControllerTool;
import ClientModel.Order;
import ClientModel.OrderLines;

public class ModelControllerTool {

	private ClientControllerTool clientControllerTool;
	private Items items, atIndexItem;
	private ItemsList itemsList;
	private Order order;
	private OrderLines orderLines;
//	private ElectricalItem electricalItem;
//	private NonElectricalItem nonElectricalItem;

	public String searchToolID(String toolID) {
		String searchID = "7 " + toolID;
		String response = clientControllerTool.sendQuery(searchID);
		if (response.split("!!")[0].contentEquals("ERROR"))
			return response;
		getToolListFromJson(response);
		String displayTool = getStringToolList();
		return displayTool;
	}

	public String searchToolName(String toolName) {
		String searchID = "8 " + toolName;
		String response = clientControllerTool.sendQuery(searchID);
		if (response.split("!!")[0].contentEquals("ERROR"))
			return response;
		getToolListFromJson(response);
		String displayTool = getStringToolList();
		return displayTool;
	}

	// convert JSon string into tool list
	private void getToolListFromJson(String itemsList) {
		ObjectMapper objectMapper = new ObjectMapper();
		objectMapper.enableDefaultTyping();
		try {
			this.itemsList = objectMapper.readValue(itemsList, ItemsList.class);
		} catch (IOException e) {
			System.out.println("Unable to convert json to items List");
			e.printStackTrace();
		}

	}

	// concatenates all the info in single String separated by lines
	public String getStringToolList() {

		String concat = "";

		for (Items i : itemsList.getItemsList()) {

			if (i instanceof ElectricalItem)

				concat = concat + i.getItemID() + " " + i.getItemName() + " " + i.getItemType() + " " + i.getItemPrice()
						+ " " + i.getItemQuantity() + " " + i.getSupplierID() + " "
						+ ((ElectricalItem) i).getPowerType() + "\n";
			else
				concat = concat + i.getItemID() + " " + i.getItemName() + " " + i.getItemType() + " " + i.getItemPrice()
						+ " " + i.getItemQuantity() + " " + i.getSupplierID() + "\n";

		}
		return concat;
	}

	public String checkToolID(String toolID) {
		String searchID = "7 " + toolID;
		String response = clientControllerTool.sendQuery(searchID);
		if (response.split("!!")[0].contentEquals("ERROR"))
			return response;
		getToolListFromJson(response);
		String displayQuantity = getStringQuantityList();
		return displayQuantity;
	}

	public String checkToolName(String name) {
		String searchID = "8 " + name;
		String response = clientControllerTool.sendQuery(searchID);
		if (response.split("!!")[0].contentEquals("ERROR"))
			return response;
		getToolListFromJson(response);
		String displayQuantity = getStringQuantityList();
		return displayQuantity;
	}

	private String getStringQuantityList() {
		String concatTool = "";
		for (Items item : this.itemsList.getItemsList())
			concatTool = concatTool + item.getItemID() + " " + item.getItemName() + " " + item.getItemQuantity() + " "
					+ "\n";
		return concatTool;
	}

	public String getAllTools() {

		String searchID = "6 ";
		String response = clientControllerTool.sendQuery(searchID);
		if (response.split("!!")[0].contentEquals("ERROR"))
			return response;
		getToolListFromJson(response);
		String displayTool = getStringToolList();

		return displayTool;
	}
	
	// send id of the item whose quantity needs to be decreased
	public String sendItemInfoDecrease(int id) {

		String response = "";
		String temp = "5 " + Integer.toString(id);
		response = clientControllerTool.sendQuery(temp);
		if (response.split("!!")[0].contentEquals("ERROR"))
			return response;
		else
			setUpdatedValueItem(id, response);
		
		return "Value decreased successflly";
	}
	
	// here response has the new count of the items
	// updates the count of the item after the decrease function
	public void setUpdatedValueItem(int id, String response) {
		
		for (Items i : this.itemsList.getItemsList()) {
			if (i.getItemID() == id)
				i.setItemQuantity(Integer.parseInt(response));
		}
		
	}
	
	
	// if object is passed for deleting
//	public String sendItemInfoDelete() {
//		ObjectMapper objectMapper = new ObjectMapper();
//		objectMapper.enableDefaultTyping();
//		String jsonItem;
//		String response = "";
//		try {
//			jsonItem = objectMapper.writeValueAsString(items);
//			String temp = "5 " + jsonItem;
//			// response = this.clientControllerTool.saveDeleteTool(temp);
//			response = clientControllerTool.sendQuery(temp);
//
//		} catch (JsonProcessingException e) {
//			e.printStackTrace();
//		}
//		return response;
//	}

	// when had separate electrical and non electrical type array list
//	public String getAllTools() {
//		
//		String searchID = "6 ";
//		// String response = clientControllerTool.searchTool(searchID);
//		String response = clientControllerTool.sendQuery(searchID);
//		getAllData(response);
//		String displayTool = getAllList();
//		
//		return displayTool;
//	}

	// trying that link

//	public void getAllData(String response) {
//
//		ObjectMapper objectMapper = new ObjectMapper();
//		objectMapper.enableDefaultTyping();
//		try {
//
//			this.itemsList = objectMapper.readValue(response, ItemsList.class);
//			System.out.println();
//
//		} catch (IOException e) {
//			System.out.println("Unable to convert json to items List");
//			// TODO Auto-generated catch block
//			e.printStackTrace();
//		}
//
//	}

//	public String getAllList() {
//		
//		String concat = "";
//		for (ElectricalItem i : this.itemsList.getElecItemList())
//			concat = concat + i.getItemID() + " " + i.getItemName() + " " + i.getItemType() + " " + i.getItemPrice()
//					+ " " + i.getItemQuantity() + " " + i.getSupplierID() + " " + i.getPowerType() + "\n";
//		for (NonElectricalItem i : this.itemsList.getNonElecItemList())
//			concat = concat + i.getItemID() + " " + i.getItemName() + " " + i.getItemType() + " " + i.getItemPrice()
//					+ " " + i.getItemQuantity() + " " + i.getSupplierID() + "\n";
//		
//		return concat;
//
//	}

	public String printOrder() {

		String orderID = "7";
		// String response = clientControllerTool.printOrder(orderID);
		String response = clientControllerTool.sendQuery(orderID);
		getOrderFromJson(response);
		String displayOrder = getStringOrder();
		writeToFile(displayOrder);
		return displayOrder;
	}

	private void writeToFile(String text) {
		try {
			FileWriter myWriter = new FileWriter("orders.txt");
			myWriter.write(text);
			myWriter.close();
			System.out.println("Successfully wrote to the file.");
		} catch (IOException e) {
			System.out.println("An error occurred.");
			e.printStackTrace();
		}
	}

	private void getOrderFromJson(String value) {
		ObjectMapper objectMapper = new ObjectMapper();
		try {
			this.order = objectMapper.readValue(value, Order.class);
		} catch (IOException e) {
			System.out.println("Unable to convert json to items List");
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	private String getStringOrder() {

		String concatOrder = "";
		concatOrder = concatOrder + order.getOrderId() + "\n";
		concatOrder = concatOrder + order.getDate() + "\n";
		for (OrderLines orderLines : this.order.getOrderLines())
			concatOrder = concatOrder + orderLines.getItem() + " " + orderLines.getAmount();
		return concatOrder;
	}

	// concat the tools into string
//	private String getStringToolList() {
//		String concatTool = "";
//		for (Items item : this.itemsList.getItemsList())
//			concatTool = concatTool + item.getItemID() + " " + item.getItemName() + " " + " " + item.getItemType()
//					+ item.getItemPrice() + " " + item.getItemQuantity() + " " + item.getSupplierID() + " " + "\n";
//		return concatTool;
//	}

	// need - gets the value at the passed index
	public String getIndexTool(int index) {

		atIndexItem = getItemsList().getItemsList().get(index);
		String values = "";

		if (atIndexItem instanceof ElectricalItem) {

			values = atIndexItem.getItemID() + "!!!" + atIndexItem.getItemName() + "!!!" + atIndexItem.getItemType()
					+ "!!!" + atIndexItem.getItemPrice() + "!!!" + atIndexItem.getItemQuantity() + "!!!"
					+ atIndexItem.getSupplierID() + "!!!" + ((ElectricalItem) atIndexItem).getPowerType();
		} else {
			values = atIndexItem.getItemID() + "!!!" + atIndexItem.getItemName() + "!!!" + atIndexItem.getItemType()
					+ "!!!" + atIndexItem.getItemPrice() + "!!!" + atIndexItem.getItemQuantity() + "!!!"
					+ atIndexItem.getSupplierID();
		}
		return values;
	}

	

	public ModelControllerTool() {
		clientControllerTool = new ClientControllerTool();
	}

	public ClientControllerTool getClientControllerTool() {
		return clientControllerTool;
	}

	public void setClientControllerTool(ClientControllerTool clientControllerTool) {
		this.clientControllerTool = clientControllerTool;
	}

	public Items getItems() {
		return items;
	}

	public void setItems(Items items) {
		this.items = items;
	}

	public ItemsList getItemsList() {
		return itemsList;
	}

	public void setItemsList(ItemsList itemsList) {
		this.itemsList = itemsList;
	}

}
