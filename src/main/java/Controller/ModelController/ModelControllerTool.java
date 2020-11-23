package Controller.ModelController;

import java.io.IOException;

import com.fasterxml.jackson.databind.ObjectMapper;

import ClientModel.Items;
import ClientModel.ItemsList;
import Controller.ClientController.ClientControllerTool;

public class ModelControllerTool {

	private ClientControllerTool clientControllerTool;
	private Items items, atIndexItem;
	private ItemsList itemsList;

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
	
	public String searchToolName(String toolName) {
		String searchID = "2 " + toolName;
		String response = clientControllerTool.searchTool(searchID);
		getToolListFromJson(response);
		String displayTool = getStringToolList();
		return displayTool;
	}
	
	public String getAllTools() {
		String searchID = "2 ";
		String response = clientControllerTool.searchTool(searchID);
		getToolListFromJson(response);
		String displayTool = getStringToolList();
		return displayTool;
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


	
	
	

}
