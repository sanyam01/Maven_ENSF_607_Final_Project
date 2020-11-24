package Server.Controller.ModelController;

import java.io.IOException;
import java.net.Socket;
import java.util.ArrayList;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;

import Server.Controller.DatabaseController.DBController;
import Server.Model.CustomerList;
import Server.Model.ElectricalItem;
import Server.Model.InternationalSupplier;
import Server.Model.Items;
import Server.Model.ItemsList;
import Server.Model.NonElectricalItem;
import Server.Model.Shop;

public class ServerInventoryController {


	private int taskId;
	DBController dbController;
	private Items items ;
	private ElectricalItem eItem;
	private InternationalSupplier intSupplier;
	private ItemsList itemList;
	private Shop shop;

	ObjectMapper objectMapper;
	private String message;

	public ServerInventoryController(String response, DBController dbC, ObjectMapper objectMapper) throws IOException {
		System.out.println("CustomerController constructor called ");

		this.message = response;
		this.dbController = dbC;
		this.objectMapper = objectMapper;


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
			//list all tools
			System.out.println("Operation: list all tools");
			System.out.println(responseArr[1]);
			
//			items = dbController.getItemList();
//			
//			
//			if (!items.isEmpty()) {
//				
//				itemList = new ItemsList(items);
//				
//				
//				
//
//					try {
//						jsonItemList = objectMapper.writeValueAsString(itemList);
//						System.out.println("Sending to client: " + jsonItemList);
//
//					} catch (JsonProcessingException e) {
//						// TODO Auto-generated catch block
//						e.printStackTrace();
//					}
//				
//
//				
//
//			} else {
//				jsonItemList = "Item list not found!!";
//				System.out.println("Item list not found!!");
//
//			}
			
			elecItems = dbController.getElectricalItems();
			nonElecItems = dbController.getNonElectricalItems();
			
			itemList = new ItemsList(elecItems, nonElecItems);
			
			try {
				String temp1 = objectMapper.writeValueAsString(itemList);
//				String temp2 = objectMapper.writeValueAsString(nonElecItems);
				
				
				jsonItemList = temp1 ;
				
				System.out.println("Sending to client: " + jsonItemList);

			} catch (JsonProcessingException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			
			break;
			
//		case 2:
//			//Search for tool by toolId
//			System.out.println("Operation: Search for tool by toolId");
//			System.out.println(responseArr[1]);
//			
//			items = dbController.getItemById(Integer.parseInt(responseArr[1]));
//			
//			if (!items.isEmpty()) {
//				
//				itemList = new ItemsList(items);
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
//			} else {
//				jsonItemList = "Item Id not found!!";
//				System.out.println("Item Id not found!!");
//
//			}
//			break;
			
			
//		case 3:
//			//Search for tool by toolName
//			System.out.println("Operation: Search for tool by toolName");
//			System.out.println(responseArr[1]);
//			
//			items = dbController.getItemByName(responseArr[1]);
//			
//			if (!items.isEmpty()) {
//				
//				itemList = new ItemsList(items);
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
//			} else {
//				jsonItemList = "Item name not found!!";
//				System.out.println("Item name not found!!");
//
//			}
//			break;
			
//		case 4:
//			//Search for tool by toolName
//			System.out.println("Operation: Check item quantity");
//			System.out.println(responseArr[1]);
//			
//			if(responseArr[1] instanceof String) {
//				
//				items = dbController.getItemQty(responseArr[1]);
//			}
//			else {
//				items = dbController.getItemQty(Integer.parseInt(responseArr[1]));
//			}
//			
//			
//			
//			if (!items.isEmpty()) {
//				
//				itemList = new ItemsList(items);
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
//			} else {
//				jsonItemList = "Item not found!!";
//				System.out.println("Item not found!!");
//
//			}
//			break;
			
//		case 5:
//		
//		System.out.println("Operation: Decrease item quantity");
//		System.out.println(responseArr[1]); //will get id here
//		
//		if(responseArr[1] instanceof String) {
//			
//			
//			
////			shop.decreaseQuantity(responseArr[1]);
//			
//
//		}
//		else {
//
//		}
//		
//		
//		break;
//		
//		
		
		
		
		}
		
		return jsonItemList;
	}
	

}
