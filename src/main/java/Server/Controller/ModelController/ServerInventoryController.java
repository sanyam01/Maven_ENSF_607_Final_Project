package Server.Controller.ModelController;

import java.io.IOException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
//import com.sun.org.apache.bcel.internal.generic.INSTANCEOF;

//import Server.Model.ElectricalItem;
//import Server.Model.InternationalSupplier;

import Server.Controller.DatabaseController.DBController;
import Server.Model.Items;
import Server.Model.ItemsList;
import Server.Model.OrderLines;
import Server.Model.Inventory;

public class ServerInventoryController {

	DBController dbController;
//	private Items items;
//	private ElectricalItem eItem;
//	private InternationalSupplier intSupplier;
//	private ItemsList itemList;
	private Inventory inventory;

	ObjectMapper objectMapper;
	private String message;

//	public ServerInventoryController(String response, DBController dbC, ObjectMapper objectMapper) throws IOException {
//		System.out.println("CustomerController constructor called ");
//
//		this.message = response;
//		this.dbController = dbC;
//		this.objectMapper = objectMapper;
////		items = dbController.getItemList();
//		this.inventory = new Inventory(dbController.getItemList());
//
//		// load data in order table
//		System.out.println("loading data in order table for values: " + inventory.getTheOrder().getOrderId() + " "
//				+ inventory.getTheOrder().getDate());
//
//		boolean flag = this.dbController.addOrder(inventory.getTheOrder().getOrderId(),
//				inventory.getTheOrder().getDate());
//		if (!flag) {
//			flag = false;
//			System.out.println("Add order failed");
//
//		}
//
//	}
	
	
	public ServerInventoryController( DBController dbC, ObjectMapper objectMapper) throws IOException {
//		System.out.println("CustomerController constructor called ");

		this.message = null;
		this.dbController = dbC;
		this.objectMapper = objectMapper;
//		items = dbController.getItemList();
		this.inventory = new Inventory(dbController.getItemList());

		

	}

//	public String readClientMessage() {
//		String[] responseArr = null;
//		String switchBoardResponse = null;
//
//		System.out.println("Request from client: " + message);
//
//		if (message != null) {
////			String jsonCustomer = objectMapper.writeValueAsString(response);
//			responseArr = message.split(" ", 2);
//			switchBoardResponse = switchBoard(responseArr);
//
//		}
//
//		return switchBoardResponse;
//
//	}
	
	
	public String readClientMessage(String clientRequest) {
		String[] responseArr = null;
		String switchBoardResponse = null;
		message = clientRequest;

		System.out.println("Request from client: " + message);

		if (message != null) {
			responseArr = message.split(" ", 2);
			switchBoardResponse = switchBoard(responseArr);

		}

		return switchBoardResponse;

	}

	public boolean callUpdateItemQuantity(int itemId) {
		boolean flag = false;
		for (Items i : inventory.getListItems()) {

			if (i.getItemID() == itemId) {

				System.out.println("updating item quantity for item: " + itemId + " " + i.getItemQuantity());
				// update statement to update qty in item
				flag = dbController.updateItemQty(i.getItemQuantity(), itemId);
				if (!flag) {
					flag = false;
					System.out.println("Item update failed");

				}

			}

		}
		return flag;

	}
	
	public boolean checkOrderLineGeneration(int itemId) {
		boolean flag = false;
		
		int orderLineSizeOld = inventory.getTheOrder().getOrderLines().size();
		int new_quantity = inventory.decreaseQuantity(itemId);


		int orderLineSizeNew = inventory.getTheOrder().getOrderLines().size();


		if (orderLineSizeNew > orderLineSizeOld) {
			
			flag = true;
			
			
		}
		return flag;
	}
	
	public boolean checkOrder() {
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy/MM/dd ");
		Date orderDate = null;
		String stringOrderDate = inventory.getTheOrder().getDate().trim();
		Date todayDate = new Date();
		String stringTodayDate = sdf.format(todayDate).trim(); 
		
		System.out.println("\nstringTodayDate: "+  stringTodayDate);
		System.out.println("\nstringOrderDate: "+  stringOrderDate);
		
		
		if(stringTodayDate.equals(stringOrderDate)) {
			
			System.out.println("in check order, both dates are same\n");
			return true;
			
		}
		else {
			System.out.println("in check order, both dates are different\n");
		}
		return false;
		
//		System.out.println("\ntoday date: "+todayDate);
//		System.out.println("inventory order date: "+inventory.getTheOrder().getDate());
		
		
		
//		
//		try {
//			 orderDate = sdf.parse(stringOrderDate);
//			System.out.println("inventory order date converetd : "+orderDate);
//		} catch (ParseException e) {
//			// TODO Auto-generated catch block
//			e.printStackTrace();
//		}
		
//		if(todayDate.equals(orderDate)) {
//			System.out.println("today is same as orderDate\n");
//		}
		
//		if(todayDate.equals(stringOrderDate)) {
//			System.out.println("today is same as stringOrderDate\n");
//		}
	}

	public boolean generateOrder() {
		
		boolean flag = false;
		// load data in order table
				System.out.println("loading data in order table for values: " + inventory.getTheOrder().getOrderId() + " "
						+ inventory.getTheOrder().getDate()+"\n");
				System.out.println(checkOrder()+"\n");
				if(!checkOrder()) {
					
					
					
					flag = this.dbController.addOrder(inventory.getTheOrder().getOrderId(),
							inventory.getTheOrder().getDate());
					
				}else {
					System.out.println("Order is already generated for today!!: "+ inventory.getTheOrder().getOrderId());
					flag = true;
				}
				
//				if(inventory.getTheOrder().getDate() ) {
//					
//				}
				return flag;
				
	}
	public int generateOrderLine(OrderLines orderLine) {
		
		System.out.println("new order line generated for: ");

		System.out.println(inventory.getTheOrder().getOrderId() + " " + orderLine.getItem().getItemID()
				+ " " + orderLine.getItem().getSupplierID() + " " + orderLine.getItem().getItemQuantity());

		// orderline table insert
		 dbController.addOrderLine(inventory.getTheOrder().getOrderId(),
				orderLine.getItem().getItemID(), orderLine.getItem().getSupplierID(),
				orderLine.getItem().getItemQuantity());
		
		
		
		return orderLine.getItem().getItemQuantity();
	}
	private String switchBoard(String[] responseArr) {

		int choice = Integer.parseInt(responseArr[0]);
		System.out.println("choice is: " + choice);
		String jsonItemList = null;
		ArrayList<Items> items;

		boolean flag;

		switch (choice) {

		case 6:
			// list all tools
			System.out.println("Operation: list all tools");
			System.out.println(responseArr[1]);
			
			if(responseArr[1].isEmpty() || responseArr[1].isBlank()) {
				System.out.println("\nInvalid input\n");
				jsonItemList = "ERROR!! Invalid input!!";
				
			}else {
				
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
			
		
			
			if(responseArr[1].isEmpty() || responseArr[1].isBlank()) {
				System.out.println("\nInvalid input\n");
				jsonItemList = "ERROR!! Invalid input!!";
				
			}else {
				
				try {
					System.out.println(Integer.parseInt(responseArr[1].trim()));
				}
				catch(NumberFormatException e ) {
					jsonItemList = "ERROR !! Invalid input, enter integer value";
					break;
				}
				items = dbController.getItemById(Integer.parseInt(responseArr[1].trim()));
				

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
				
			}

			

			break;

		case 8:
			// Search for tool by toolName
			System.out.println("Operation: Search for tool by toolName");

			if(responseArr[1].isEmpty() || responseArr[1].isBlank()) {
				System.out.println("\nInvalid input\n");
				jsonItemList = "ERROR!! Invalid input!!";
				
			}else {
				
				items = dbController.getItemByName(responseArr[1].trim());

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
				
			}
			
			break;

		case 9:

			System.out.println("Operation: Decrease item quantity");
			System.out.println(responseArr[1]); // will get id here
			
			if(responseArr[1].isEmpty() || responseArr[1].isBlank()) {
				System.out.println("\nInvalid input\n");
				jsonItemList = "ERROR!! Invalid input!!";
				
			}else {
				
				int new_quantity = 0;

				if (!responseArr[1].isEmpty()) {
					
					if( !generateOrder() ) {
						jsonItemList = "ERROR !! Order generation failed";
						break;
					}
					
					
					if( checkOrderLineGeneration(Integer.parseInt(responseArr[1].trim()))) {
						System.out.println("orderline is generated");
						

						OrderLines orderLine = inventory.getTheOrder().getOrderLines().get(inventory.getTheOrder().getOrderLines().size() - 1);

						new_quantity = generateOrderLine(orderLine);
						if(new_quantity == 0) {
							jsonItemList = "ERROR !! Orderline generation failed";
							break;
						}
					}

					if (callUpdateItemQuantity(Integer.parseInt(responseArr[1]))) {
						jsonItemList = Integer.toString(new_quantity);
					} else {
						jsonItemList = "ERROR !! Update tool quantity failed";
					}

				}

				else {
					jsonItemList = "ERROR !! Request to decrease item failed";
				}

			}

			break;

		case 10:

			System.out.println("Operation: Print order");
			try {
				jsonItemList = objectMapper.writeValueAsString(inventory.getTheOrder());
			} catch (JsonProcessingException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}

			break;
			
		default:
			System.out.println("Invalid choice!");
		}

		return jsonItemList;
	}

}
