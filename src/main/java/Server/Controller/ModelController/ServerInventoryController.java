package Server.Controller.ModelController;

import java.io.IOException;
import java.net.Socket;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.fasterxml.jackson.core.JsonParseException;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.JsonMappingException;
import com.fasterxml.jackson.databind.ObjectMapper;
//import com.sun.org.apache.bcel.internal.generic.INSTANCEOF;

import Server.Controller.DatabaseController.DBController;
import Server.Model.CustomerList;
import Server.Model.ElectricalItem;
import Server.Model.InternationalSupplier;
import Server.Model.Items;
import Server.Model.ItemsList;
import Server.Model.NonElectricalItem;
import Server.Model.OrderLines;
import Server.Model.Inventory;

public class ServerInventoryController {

	private int taskId;
	DBController dbController;
	private Items items;
	private ElectricalItem eItem;
	private InternationalSupplier intSupplier;
	private ItemsList itemList;
	private Inventory inventory;

	ObjectMapper objectMapper;
	private String message;

	public ServerInventoryController(String response, DBController dbC, ObjectMapper objectMapper) throws IOException {
		System.out.println("CustomerController constructor called ");

		this.message = response;
		this.dbController = dbC;
		this.objectMapper = objectMapper;
//		items = dbController.getItemList();
		this.inventory = new Inventory(dbController.getItemList());

		// load data in order table
		System.out.println("loading data in order table for values: " + inventory.getTheOrder().getOrderId() + " "
				+ inventory.getTheOrder().getDate());

		boolean flag = this.dbController.addOrder(inventory.getTheOrder().getOrderId(),
				inventory.getTheOrder().getDate());
		if (!flag) {
			flag = false;
			System.out.println("Add order failed");

		}

	}

	public String readClientMessage() {
		String[] responseArr = null;
		String switchBoardResponse = null;

		System.out.println("Request from client: " + message);

		if (message != null) {
//			String jsonCustomer = objectMapper.writeValueAsString(response);
			responseArr = message.split(" ", 2);
			switchBoardResponse = switchBoard(responseArr);

		}

		return switchBoardResponse;

	}

	private String switchBoard(String[] responseArr) {

		int choice = Integer.parseInt(responseArr[0]);
		System.out.println("choice is: " + choice);
		String jsonItemList = null;
		ArrayList<Items> items;
		ArrayList<ElectricalItem> elecItems;
		ArrayList<NonElectricalItem> nonElecItems;
		boolean flag;

		switch (choice) {

		case 6:
			// list all tools
			System.out.println("Operation: list all tools");
			System.out.println(responseArr[1]);

			items = dbController.getItemList();
			if (!items.isEmpty()) {

				ItemsList serializedFleet = new ItemsList();
				serializedFleet.setItemsList(items);

				try {
					jsonItemList = objectMapper.writeValueAsString(serializedFleet);
				} catch (JsonProcessingException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}

			} else {
				jsonItemList = "ERROR!! Items list is empty!!";
			}

//			elecItems = dbController.getElectricalItems();
//			nonElecItems = dbController.getNonElectricalItems();
//			
//			itemList = new ItemsList(elecItems, nonElecItems);
//			if ((!itemList.getElecItemList().isEmpty()) && (!itemList.getNonElecItemList().isEmpty())) {
//				
//				try {
//					jsonItemList = objectMapper.writeValueAsString(itemList);
//			
//					
//					System.out.println("Sending to client: " + jsonItemList);
//
//				} catch (JsonProcessingException e) {
//					// TODO Auto-generated catch block
//					e.printStackTrace();
//				}
//				
//				
//				
//			}
//			else {
//				
//				jsonItemList = "Items list is empty!!";
//				
//			}

			break;

		case 7:
			// Search for tool by toolId
			System.out.println("Operation: Search for tool by toolId");
			System.out.println(responseArr[1]);

//			itemList = dbController.getItemById(Integer.parseInt(responseArr[1]));
//			
//			if (itemList.getElecItemList().isEmpty() && itemList.getNonElecItemList().isEmpty()) {
//				
//				jsonItemList = "Item Id not found!!";
//				System.out.println("Item Id not found!!");
//				
//				
//
//			} else {
//				
//
//				try {
//					jsonItemList = objectMapper.writeValueAsString(itemList);
//					System.out.println("Sending to client: " + jsonItemList);
//
//				} catch (JsonProcessingException e) {
//					// TODO Auto-generated catch block
//					e.printStackTrace();
//				}
//				
//
//			}

			items = dbController.getItemById(Integer.parseInt(responseArr[1]));

			if (!items.isEmpty()) {

				ItemsList serializedFleet = new ItemsList();
				serializedFleet.setItemsList(items);

				try {
					jsonItemList = objectMapper.writeValueAsString(serializedFleet);
				} catch (JsonProcessingException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}

			} else {
				jsonItemList = "ERROR!! Items Id not found!!";
			}

			break;

		case 8:
			// Search for tool by toolName
			System.out.println("Operation: Search for tool by toolName");

//			itemList = dbController.getItemByName(responseArr[1]);
//
//			
//			if (itemList.getElecItemList().isEmpty() && itemList.getNonElecItemList().isEmpty()) {
//				
//				jsonItemList = "Item Name not found!!";
//				System.out.println("Item Name not found!!");
//				
//				
//
//			} else {
//				
//
//				try {
//					jsonItemList = objectMapper.writeValueAsString(itemList);
//					System.out.println("Sending to client: " + jsonItemList);
//
//				} catch (JsonProcessingException e) {
//					// TODO Auto-generated catch block
//					e.printStackTrace();
//				}
//				
//
//			}

			items = dbController.getItemByName(responseArr[1]);

			if (!items.isEmpty()) {

				ItemsList serializedFleet = new ItemsList();
				serializedFleet.setItemsList(items);

				try {
					jsonItemList = objectMapper.writeValueAsString(serializedFleet);
				} catch (JsonProcessingException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}

			} else {
				jsonItemList = "ERROR!! Item Name not found!!";
			}
			break;

		case 9:

			System.out.println("Operation: Decrease item quantity");
			System.out.println(responseArr[1]); // will get id here
			int new_quantity = 0;
			int tempItemId = Integer.parseInt(responseArr[1]);
			

			if (!responseArr[1].isEmpty()) {
				

				int orderLineSizeOld = inventory.getTheOrder().getOrderLines().size();

				new_quantity = inventory.decreaseQuantity(Integer.parseInt(responseArr[1]));

			

					// check if order lines are geenrated
					int orderLineSizeNew = inventory.getTheOrder().getOrderLines().size();

//					if (orderLineSizeNew > 0) {
//
//						OrderLines orderLine = inventory.getTheOrder().getOrderLines().get(orderLineSizeNew - 1);
//
//					}

					if (orderLineSizeNew > orderLineSizeOld) {
						
						OrderLines orderLine = inventory.getTheOrder().getOrderLines().get(orderLineSizeNew - 1);

						System.out.println("new order line generated for: ");

						System.out.println(inventory.getTheOrder().getOrderId() + " " + orderLine.getItem().getItemID()
								+ " " + orderLine.getItem().getSupplierID() + " "
								+ orderLine.getItem().getItemQuantity());

						// orderline table insert
						flag = dbController.addOrderLine(inventory.getTheOrder().getOrderId(),
								orderLine.getItem().getItemID(), orderLine.getItem().getSupplierID(),
								orderLine.getItem().getItemQuantity());
						if (!flag) {
							flag = false;
							System.out.println("Add orderline failed");

						}

//						System.out.println("qty to jasonstring: "+Integer.toString(orderLine.getItem().getItemQuantity()));
//						jsonItemList = Integer.toString(orderLine.getItem().getItemQuantity());

					}

					for(Items i : inventory.getListItems()) {
						
						if(i.getItemID() == tempItemId) {
							
							System.out.println("updating item quantity for item: " + tempItemId + " "
									+ i.getItemQuantity());
							// update statement to update qty in item
							flag = dbController.updateItemQty(i.getItemQuantity(),
									tempItemId);
							if (!flag) {
								flag = false;
								System.out.println("Item update failed");

							}
							
							
						}
						
					}
					jsonItemList = Integer.toString(new_quantity);
					System.out.println("json qty: " + jsonItemList);

				
			}

			else {
				jsonItemList = "ERROR !! Request to decrease item failed";
			}

			break;

		case 10:
			
			try {
				jsonItemList = objectMapper.writeValueAsString(inventory.getTheOrder());
			} catch (JsonProcessingException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			
			
			break;
		}

		return jsonItemList;
	}

}
