package Controller.ModelController;

import java.io.IOException;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;

import ClientModel.Items;
import ClientModel.ItemsList;
import Controller.ClientController.ClientControllerTool;
import ClientModel.Order;
import ClientModel.OrderLines;

public class ModelControllerTool {

	private ClientControllerTool clientControllerTool;
	private Items items, atIndexItem;
	private ItemsList itemsList;
	private Order order;
	private OrderLines orderLines;
	
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
		String response = clientControllerTool.searchTool(searchID);
		getToolListFromJson(response);
		String displayTool = getStringToolList();
		return displayTool;
	}
	
	
	
	public String checkToolID(String toolID) {
		String searchID = "4 " + toolID;
		String response = clientControllerTool.searchTool(searchID);
		getToolListFromJson(response);
		String displayQuantity = getStringToolList();
		return displayQuantity;
	}
	
	public String checkToolName(String name) {
		String searchID = "6 " + name;
		String response = clientControllerTool.searchTool(searchID);
		getToolListFromJson(response);
		String displayQuantity = getStringToolList();
		return displayQuantity;
	}
	
	

	
	public String searchToolName(String toolName) {
		String searchID = "2 " + toolName;
		String response = clientControllerTool.searchTool(searchID);
		getToolListFromJson(response);
		String displayTool = getStringToolList();
		return displayTool;
	}
	
	public String getAllTools() {
		String searchID = "1 ";
		String response = clientControllerTool.searchTool(searchID);
		getToolListFromJson(response);
		String displayTool = getStringQuantityList();
		return displayTool;
	}
	
	public String printOrder() {
		
		String orderID = "7";
		String response = clientControllerTool.printOrder(orderID);
		getOrderFromJson(response);
		String displayOrder = getStringOrder();
		return displayOrder;
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
			concatOrder = concatOrder + orderLines.getItem()  + " " +orderLines.getAmount();
		return concatOrder;
	}
	
	private String getStringQuantityList() {
		String concatTool = "";
		for (Items item : this.itemsList.getItemsList())
			concatTool = concatTool + item.getItemID()  + " " + item.getItemName() + " "+ " " + item.getItemType()
					+ item.getItemQuantity() + " " + "\n";
		return concatTool;
	}

	// concat the tools into string
	private String getStringToolList() {
		String concatTool = "";
		for (Items item : this.itemsList.getItemsList())
			concatTool = concatTool + item.getItemID()  + " " + item.getItemName() + " "+ " " + item.getItemType()
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
	
	// this is called when save is pressed for storing customer information
		public void setItem(int iD, String name, String type, int price,
				int quantity, int supplierID) {

			this.items = new Items(iD, name, type, price, quantity, supplierID);
		}


	public String getIndexTool(int index) {
		
		atIndexItem = getItemsList().getItemsList().get(index);
		String values = atIndexItem.getItemID() + "!!!"  + atIndexItem.getItemName() + "!!!" + atIndexItem.getItemType() + "!!!"
				 + atIndexItem.getItemPrice() + "!!!" + atIndexItem.getItemQuantity() + "!!!" + atIndexItem.getSupplierID();
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
			response = this.clientControllerTool.saveDeleteTool(temp);

		} catch (JsonProcessingException e) {
			e.printStackTrace();
		}
		return response;
	}

	

	
	
	

}
