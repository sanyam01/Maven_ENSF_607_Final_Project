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
	private ElectricalItem electricalItem;
	private NonElectricalItem nonElectricalItem;

	public ModelControllerTool() {
		clientControllerTool = new ClientControllerTool();
	}

	public ClientControllerTool getClientControllerTool() {
		return clientControllerTool;
	}

	public void setClientControllerTool(ClientControllerTool clientControllerTool) {
		this.clientControllerTool = clientControllerTool;
	}

	public String searchToolID(String toolID) {
		String searchID = "3 " + toolID;
		// String response = clientControllerTool.searchTool(searchID);
		String response = clientControllerTool.sendQuery(searchID);
		getToolListFromJson(response);
		String displayTool = getStringToolList();
		return displayTool;
	}

	public String checkToolID(String toolID) {
		String searchID = "4 " + toolID;
		// String response = clientControllerTool.searchTool(searchID);
		String response = clientControllerTool.sendQuery(searchID);
		getToolListFromJson(response);
		String displayQuantity = getStringToolList();
		return displayQuantity;
	}

	public String checkToolName(String name) {
		String searchID = "6 " + name;
		// String response = clientControllerTool.searchTool(searchID);
		String response = clientControllerTool.sendQuery(searchID);
		getToolListFromJson(response);
		String displayQuantity = getStringToolList();
		return displayQuantity;
	}

	public String searchToolName(String toolName) {
		String searchID = "2 " + toolName;
		// String response = clientControllerTool.searchTool(searchID);
		String response = clientControllerTool.sendQuery(searchID);
		getToolListFromJson(response);
		String displayTool = getStringToolList();
		return displayTool;
	}

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
	
	public String getAllTools() throws JsonParseException, JsonMappingException, IOException {
	
	String searchID = "6 ";
	// String response = clientControllerTool.searchTool(searchID);
	String response = clientControllerTool.sendQuery(searchID);
	getAllData(response);
	String displayTool = getNewAllList();
	
	return displayTool;
}

	// trying that link
	public String getNewAllList() throws JsonParseException, JsonMappingException, IOException{
		
		ObjectMapper objectMapper = new ObjectMapper();
		String concat = "";
		//ItemsList deserializedFleet = objectMapper.readValue(response, ItemsList.class);
//		System.out.println("deserializedFleet: "+ deserializedFleet);
//		System.out.println(deserializedFleet.getItemsList().get(0));
//		System.out.println(deserializedFleet.getItemsList().get(1));
		
		for(Items i: itemsList.getItemsList()) {
			
			if(i instanceof ElectricalItem) {
				
				//System.out.println("electrical item");
				concat = concat + i.getItemID() + " " + i.getItemName() + " " + i.getItemType() + " " + i.getItemPrice()
				+ " " + i.getItemQuantity() + " " + i.getSupplierID() + " " + ((ElectricalItem)i).getPowerType() + "\n";
				//System.out.println(((ElectricalItem) i).getPowerType() + i.getItemName());
				
			}
			else {
				concat = concat + i.getItemID() + " " + i.getItemName() + " " + i.getItemType() + " " + i.getItemPrice()
				+ " " + i.getItemQuantity() + " " + i.getSupplierID() + "\n";
			}
		
		
	}
		return concat;
	}
	
	public void getAllData(String response) {

		ObjectMapper objectMapper = new ObjectMapper();
		objectMapper.enableDefaultTyping();
		try {

			this.itemsList = objectMapper.readValue(response, ItemsList.class);
			System.out.println();

		} catch (IOException e) {
			System.out.println("Unable to convert json to items List");
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

	}

	public String getAllList() {
		
		String concat = "";
		for (ElectricalItem i : this.itemsList.getElecItemList())
			concat = concat + i.getItemID() + " " + i.getItemName() + " " + i.getItemType() + " " + i.getItemPrice()
					+ " " + i.getItemQuantity() + " " + i.getSupplierID() + " " + i.getPowerType() + "\n";
		for (NonElectricalItem i : this.itemsList.getNonElecItemList())
			concat = concat + i.getItemID() + " " + i.getItemName() + " " + i.getItemType() + " " + i.getItemPrice()
					+ " " + i.getItemQuantity() + " " + i.getSupplierID() + "\n";
		
		return concat;

	}

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

	private String getStringQuantityList() {
		String concatTool = "";
		for (Items item : this.itemsList.getItemsList())
			concatTool = concatTool + item.getItemID() + " " + item.getItemName() + " " + " " + item.getItemType()
					+ item.getItemQuantity() + " " + "\n";
		return concatTool;
	}

	// concat the tools into string
	private String getStringToolList() {
		String concatTool = "";
		for (Items item : this.itemsList.getItemsList())
			concatTool = concatTool + item.getItemID() + " " + item.getItemName() + " " + " " + item.getItemType()
					+ item.getItemPrice() + " " + item.getItemQuantity() + " " + item.getSupplierID() + " " + "\n";
		return concatTool;
	}

	// convert JSon string into tool list
	private void getToolListFromJson(String itemsList) {
		ObjectMapper objectMapper = new ObjectMapper();
		try {
			this.itemsList = objectMapper.readValue(itemsList, ItemsList.class);
		} catch (IOException e) {
			System.out.println("Unable to convert json to items List");
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

	}

//	// this is called when save is pressed for storing tool information
//	public void setItem(int iD, String name, String type, int price, int quantity, int supplierID, ) {
//
//		this.items = new Items(iD, name, quantity, price, type, supplierID);
//	}

	
	//when electrical and non -electrical were diff
//	public String getIndexTool(int index) {
//
//		int lenElectrical = itemsList.getElecItemList().size();
//		int lenNon = itemsList.getNonElecItemList().size();
//		if (index < lenElectrical)
//			atIndexItem = getItemsList().getElecItemList().get(index);
//		else
//			atIndexItem = getItemsList().getNonElecItemList().get(index-lenElectrical);
//		String values = atIndexItem.getItemID() + "!!!" + atIndexItem.getItemName() + "!!!" + atIndexItem.getItemType()
//				+ "!!!" + atIndexItem.getItemPrice() + "!!!" + atIndexItem.getItemQuantity() + "!!!"
//				+ atIndexItem.getSupplierID();
//		return values;
//	}
	
	public String getIndexTool(int index) {

		atIndexItem = getItemsList().getElecItemList().get(index);
		String values ="";
		
		if(atIndexItem instanceof ElectricalItem) {
			
		values = atIndexItem.getItemID() + "!!!" + atIndexItem.getItemName() + "!!!" + atIndexItem.getItemType()
				+ "!!!" + atIndexItem.getItemPrice() + "!!!" + atIndexItem.getItemQuantity() + "!!!"
				+ atIndexItem.getSupplierID() + "!!!" +  ((ElectricalItem)atIndexItem).getPowerType();
		}
		else {
			values = atIndexItem.getItemID() + "!!!" + atIndexItem.getItemName() + "!!!" + atIndexItem.getItemType()
			+ "!!!" + atIndexItem.getItemPrice() + "!!!" + atIndexItem.getItemQuantity() + "!!!"
			+ atIndexItem.getSupplierID();
		}
		return values;
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

	public String sendItemInfoDelete() {
		ObjectMapper objectMapper = new ObjectMapper();
		String jsonItem;
		String response = "";
		try {
			jsonItem = objectMapper.writeValueAsString(items);
			String temp = "5 " + jsonItem;
			// response = this.clientControllerTool.saveDeleteTool(temp);
			response = clientControllerTool.sendQuery(temp);

		} catch (JsonProcessingException e) {
			e.printStackTrace();
		}
		return response;
	}

}
