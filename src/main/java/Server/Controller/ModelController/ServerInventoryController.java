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
			
			
		//trying new stuff
			items = dbController.getItemList();
			
			
//			Fleet serializedFleet = new Fleet();
			ItemsList serializedFleet = new ItemsList();
			serializedFleet.setItemsList(items);
			String jsonItemList_temp = null ;
			
			
			try {
				 jsonItemList_temp = objectMapper.writeValueAsString(serializedFleet);
			} catch (JsonProcessingException e1) {
				// TODO Auto-generated catch block
				e1.printStackTrace();
			}
			System.out.println("jsonItemList_temp: "+jsonItemList_temp);
			
			//doing desialization
			try {
				ItemsList deserializedFleet = objectMapper.readValue(jsonItemList_temp, ItemsList.class);
				System.out.println("deserializedFleet: "+ deserializedFleet);
				System.out.println(deserializedFleet.getItemsList().get(0));
				System.out.println(deserializedFleet.getItemsList().get(1));
				
				for(Items i: deserializedFleet.getItemsList()) {
					
					if(i instanceof ElectricalItem) {
						
						System.out.println("electrical item");
						System.out.println(((ElectricalItem) i).getPowerType() + i.getItemName());
						
					}
					else {
						System.out.println(((NonElectricalItem) i).getItemID()+ i.getItemName());
					}
					
					
//					if(jsonItemList_temp.contains("electricalItem")) {
//						System.out.println("electrical");
//						
//					}
					System.out.println((i.getItemName() + " " + i.getItemID() ));
				}
//				assertThat(deserializedFleet.getItemsList().get(0), ElectricalItem.class);
//				assertThat(deserializedFleet.getItemsList().get(1), instanceof(NonElectricalItem.class));
	//
				
				
				
			} catch (JsonParseException e1) {
				// TODO Auto-generated catch block
				e1.printStackTrace();
//			} catch (JsonMappingException e1) {
				// TODO Auto-generated catch block
				e1.printStackTrace();
			} catch (IOException e1) {
				// TODO Auto-generated catch block
				e1.printStackTrace();
			}
			
			
			

			
			
////			this.items = objectMapper.readValue(jsonItemList, ItemsList.class);
//			try {
//				List<Map<String,Object>> list = Arrays.asList(objectMapper.readValue(jsonItemList, Map.class));
////				Map<String,Object> result =   new ObjectMapper().readValue(jsonItemList, Map.class);
////				for(Items t: list) {
////					System.out.println(t);
////				}
////				System.out.println("POWERTYPE: "+result.get("powerType"));
//				for(Map<String,Object> t: list) {
//					System.out.println(t.get("itemName"));
//				}
//			} catch (JsonParseException e1) {
//				// TODO Auto-generated catch block
//				e1.printStackTrace();
//			} catch (JsonMappingException e1) {
//				// TODO Auto-generated catch block
//				e1.printStackTrace();
//			} catch (IOException e1) {
//				// TODO Auto-generated catch block
//				e1.printStackTrace();
//			}
			
			//new stuff ends
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
			if ((!itemList.getElecItemList().isEmpty()) && (!itemList.getNonElecItemList().isEmpty())) {
				
				try {
					jsonItemList = objectMapper.writeValueAsString(itemList);
			
					
					System.out.println("Sending to client: " + jsonItemList);

				} catch (JsonProcessingException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
				
				
				
			}
			else {
				
				jsonItemList = "Items list is empty!!";
				
			}
			
			
			
			break;
			
		case 7:
			//Search for tool by toolId
			System.out.println("Operation: Search for tool by toolId");
			System.out.println(responseArr[1]);
			
			itemList = dbController.getItemById(Integer.parseInt(responseArr[1]));
			
			if (itemList.getElecItemList().isEmpty() && itemList.getNonElecItemList().isEmpty()) {
				
				jsonItemList = "Item Id not found!!";
				System.out.println("Item Id not found!!");
				
				

			} else {
				
//				itemList = new ItemsList(items);

				try {
					jsonItemList = objectMapper.writeValueAsString(itemList);
					System.out.println("Sending to client: " + jsonItemList);

				} catch (JsonProcessingException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
				

			}
			break;
			
			
		case 8:
			//Search for tool by toolName
			System.out.println("Operation: Search for tool by toolName");

			itemList = dbController.getItemByName(responseArr[1]);

			
			if (itemList.getElecItemList().isEmpty() && itemList.getNonElecItemList().isEmpty()) {
				
				jsonItemList = "Item Name not found!!";
				System.out.println("Item Name not found!!");
				
				

			} else {
				
//				itemList = new ItemsList(items);

				try {
					jsonItemList = objectMapper.writeValueAsString(itemList);
					System.out.println("Sending to client: " + jsonItemList);

				} catch (JsonProcessingException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
				

			}
			break;
			
		case 9:
	
		System.out.println("Operation: Decrease item quantity");
		System.out.println(responseArr[1]); //will get id here
	
		if(responseArr[1] instanceof String) {
			
			
			
//			shop.decreaseQuantity(responseArr[1]);
			

		}
		else {

		}
		
		
		break;
		
		
		
		
		
		}
		
		return jsonItemList;
	}
	

}
