package Server.Controller.DatabaseController;

import java.io.FileReader;
import java.sql.*;
import java.util.ArrayList;
import java.util.Scanner;

import Server.Model.Customer;
import Server.Model.CustomerList;
import Server.Model.ElectricalItem;
import Server.Model.InternationalSupplier;
import Server.Model.Items;
import Server.Model.ItemsList;
import Server.Model.NonElectricalItem;
import Server.Model.Suppliers;

public class InventoryDBManager {

	// Attributes
//	private Connection myConn;//Object of type connection from the JDBC class that deals with connecting to the database

	private Statement myStmt; // object of type statement from JDBC class that enables the creation "Query
								// statements"

	private ResultSet myres;// object of type ResultSet from the JDBC class that stores the result of the
							// query

	private PreparedStatement prepStatment;

	private Driver myDriver;

//	private Items items ;
//	private Suppliers supplier ;
//	CustomerList customerList;

	public InventoryDBManager(Driver myDriver) {

		this.myDriver = myDriver;

//		createCustomerTable();

	}

	public void closeSqlConn() {

		try {

			myStmt.close();
			myres.close();
			prepStatment.close();
			myDriver.getMyConn().close();

		} catch (Exception e) {
			e.printStackTrace();
		}
	}

//	public ItemsList getItemByIdPreparedStatement(int itemId) {
//
//		String query = "SELECT  item.item_id,  item_name,  item_quantity,  item_price,  item_type,  supplier_id , electrical_item.power_type\r\n"
//				+ "          FROM item \r\n"
//				+ "          LEFT  JOIN electrical_item  ON item.item_id=electrical_item.item_id \r\n"
//				+ "          where item.item_id = ?";
//
//		try {
//			prepStatment = myDriver.getMyConn().prepareStatement(query);
//			prepStatment.setInt(1, itemId);
//			myres = prepStatment.executeQuery();
//
////			ItemsList itemArrayList = new  ItemsList();
//			ArrayList<ElectricalItem> elecArrList = new ArrayList<>();
//			ArrayList<NonElectricalItem> nonElecArrList = new ArrayList<>();
//			
//
//			while (myres.next()) {
//
////				itemArrayList.add(new Items(myres.getInt("item_id"), myres.getString("item_name"), myres.getInt("item_quantity"), myres.getFloat("item_price"), 
////						myres.getString("item_type"), myres.getInt("supplier_id")));
//
//				if (myres.getString("power_type") != null) {
//					
//					
//					
//					elecArrList.add(new ElectricalItem(myres.getInt("item_id"), myres.getString("item_name"),
//							myres.getInt("item_quantity"), myres.getFloat("item_price"), myres.getString("item_type"),
//							myres.getInt("supplier_id"), myres.getString("power_type")));
//					
////					itemArrayList.setElecItemList(elecArrList);
//					
//				}
//				if (myres.getString("power_type") == null) {
//					
//					
//							
//					nonElecArrList.add(new NonElectricalItem(myres.getInt("item_id"), myres.getString("item_name"),
//							myres.getInt("item_quantity"), myres.getFloat("item_price"), myres.getString("item_type"),
//							myres.getInt("supplier_id")));
//					
////					
//				}
//
//			}
//			
//			ItemsList itemsList = new ItemsList(elecArrList, nonElecArrList);
//			
//
//			return itemsList;
//
//		} catch (SQLException e) {
//
//			e.printStackTrace();
//		}
//		return null;
//
//	}

	public ArrayList<Items> getItemByIdPreparedStatement(int itemId) {

		String query = "SELECT  item.item_id,  item_name,  item_quantity,  item_price,  item_type,  supplier_id , electrical_item.power_type\r\n"
				+ "          FROM item \r\n"
				+ "          LEFT  JOIN electrical_item  ON item.item_id=electrical_item.item_id \r\n"
				+ "          where item.item_id = ?";

		try {
			prepStatment = myDriver.getMyConn().prepareStatement(query);
			prepStatment.setInt(1, itemId);
			myres = prepStatment.executeQuery();

			ArrayList<Items> items = new ArrayList<>();

			while (myres.next()) {

//				itemArrayList.add(new Items(myres.getInt("item_id"), myres.getString("item_name"), myres.getInt("item_quantity"), myres.getFloat("item_price"), 
//						myres.getString("item_type"), myres.getInt("supplier_id")));

				if (myres.getString("power_type") != null) {

					items.add(new ElectricalItem(myres.getInt("item_id"), myres.getString("item_name"),
							myres.getInt("item_quantity"), myres.getFloat("item_price"), myres.getString("item_type"),
							myres.getInt("supplier_id"), myres.getString("power_type")));

//					itemArrayList.setElecItemList(elecArrList);

				}
				if (myres.getString("power_type") == null) {

					items.add(new NonElectricalItem(myres.getInt("item_id"), myres.getString("item_name"),
							myres.getInt("item_quantity"), myres.getFloat("item_price"), myres.getString("item_type"),
							myres.getInt("supplier_id")));

//					
				}

			}

			return items;

		} catch (SQLException e) {

			e.printStackTrace();
		}
		return null;

	}

//	public ItemsList getItemByNamePreparedStatement(String itemName) {
//		String query = "SELECT  item.item_id,  item_name,  item_quantity,  item_price,  item_type,  supplier_id , electrical_item.power_type\r\n"
//				+ "          FROM item \r\n"
//				+ "          LEFT  JOIN electrical_item  ON item.item_id=electrical_item.item_id \r\n"
//				+ "          where item.item_name = ?";
//
//		try {
//			prepStatment = myDriver.getMyConn().prepareStatement(query);
//			prepStatment.setString(1, itemName);
//			myres = prepStatment.executeQuery();
//
//			ArrayList<ElectricalItem> elecArrList = new ArrayList<>();
//			ArrayList<NonElectricalItem> nonElecArrList = new ArrayList<>();
//			
//
//			while (myres.next()) {
//
//
//
//				if (myres.getString("power_type") != null) {
//					
//					
//					
//					elecArrList.add(new ElectricalItem(myres.getInt("item_id"), myres.getString("item_name"),
//							myres.getInt("item_quantity"), myres.getFloat("item_price"), myres.getString("item_type"),
//							myres.getInt("supplier_id"), myres.getString("power_type")));
//					
//					
//				}
//				if (myres.getString("power_type") == null) {
//					
//					
//							
//					nonElecArrList.add(new NonElectricalItem(myres.getInt("item_id"), myres.getString("item_name"),
//							myres.getInt("item_quantity"), myres.getFloat("item_price"), myres.getString("item_type"),
//							myres.getInt("supplier_id")));
//					
////					
//				}
//
//			}
//			
//			ItemsList itemsList = new ItemsList(elecArrList, nonElecArrList);
//			
//
//			return itemsList;
//
//		} catch (SQLException e) {
//
//			e.printStackTrace();
//		}
//		return null;
//
//	}

	public ArrayList<Items> getItemByNamePreparedStatement(String itemName) {
		String query = "SELECT  item.item_id,  item_name,  item_quantity,  item_price,  item_type,  supplier_id , electrical_item.power_type\r\n"
				+ "          FROM item \r\n"
				+ "          LEFT  JOIN electrical_item  ON item.item_id=electrical_item.item_id \r\n"
				+ "          where item.item_name = ?";

		try {
			prepStatment = myDriver.getMyConn().prepareStatement(query);
			prepStatment.setString(1, itemName);
			myres = prepStatment.executeQuery();

			ArrayList<Items> items = new ArrayList<>();

			while (myres.next()) {

				if (myres.getString("power_type") != null) {

					items.add(new ElectricalItem(myres.getInt("item_id"), myres.getString("item_name"),
							myres.getInt("item_quantity"), myres.getFloat("item_price"), myres.getString("item_type"),
							myres.getInt("supplier_id"), myres.getString("power_type")));

				}
				if (myres.getString("power_type") == null) {

					items.add(new NonElectricalItem(myres.getInt("item_id"), myres.getString("item_name"),
							myres.getInt("item_quantity"), myres.getFloat("item_price"), myres.getString("item_type"),
							myres.getInt("supplier_id")));

//					
				}

			}

			return items;

		} catch (SQLException e) {

			e.printStackTrace();
		}
		return null;

	}

	public ArrayList<Items> getItemQtyPreparedStatement(String itemName) {

		String query = "Select item_id, item_name,item_quantity from item where item_name=?";

		try {
			prepStatment = myDriver.getMyConn().prepareStatement(query);
			prepStatment.setString(1, itemName);
			myres = prepStatment.executeQuery();

			ArrayList<Items> itemArrayList = new ArrayList<>();

			while (myres.next()) {

//				itemArrayList.add(new Items(myres.getInt("item_id"), myres.getString("item_name"),
//						myres.getInt("item_quantity"), myres.getFloat("item_price"), myres.getString("item_type"),
//						myres.getInt("supplier_id")));

			}

			return itemArrayList;

		} catch (SQLException e) {

			e.printStackTrace();
		}
		return null;

	}

	public ArrayList<Items> getItemQtyPreparedStatement(int itemId) {

		String query = "SELECT  item.item_id,  item_name,  item_quantity,  item_price,  item_type,  supplier_id , electrical_item.power_type\r\n"
				+ "          FROM item \r\n"
				+ "          LEFT  JOIN electrical_item  ON item.item_id=electrical_item.item_id  ";

		try {
			prepStatment = myDriver.getMyConn().prepareStatement(query);
			prepStatment.setInt(1, itemId);
			myres = prepStatment.executeQuery();

			ArrayList<Items> itemArrayList = new ArrayList<>();

			while (myres.next()) {

//				itemArrayList.add(new Items(myres.getInt("item_id"), myres.getString("item_name"),
//						myres.getInt("item_quantity"), myres.getFloat("item_price"), myres.getString("item_type"),
//						myres.getInt("supplier_id")));

			}

			return itemArrayList;

		} catch (SQLException e) {

			e.printStackTrace();
		}
		return null;

	}

	public ArrayList<Items> getItemListPreparedStatemen() {

		String query = "SELECT  item.item_id,  item_name,  item_quantity,  item_price,  item_type,  supplier_id , electrical_item.power_type\r\n"
				+ "          FROM item \r\n"
				+ "          LEFT  JOIN electrical_item  ON item.item_id=electrical_item.item_id  ";

		try {
			prepStatment = myDriver.getMyConn().prepareStatement(query);
//			prepStatment.setInt(1, customerId);
			myres = prepStatment.executeQuery();

			ArrayList<Items> itemArrayList = new ArrayList<>();

			while (myres.next()) {

				if (myres.getString("power_type") != null) {
					itemArrayList.add(new ElectricalItem(myres.getInt("item_id"), myres.getString("item_name"),
							myres.getInt("item_quantity"), myres.getFloat("item_price"), myres.getString("item_type"),
							myres.getInt("supplier_id"), myres.getString("power_type")));
				}
				if (myres.getString("power_type") == null) {
					itemArrayList.add(new NonElectricalItem(myres.getInt("item_id"), myres.getString("item_name"),
							myres.getInt("item_quantity"), myres.getFloat("item_price"), myres.getString("item_type"),
							myres.getInt("supplier_id")));
				}

//				System.out.println("search customer result in IDB: " + customerArrayList);

			}

			return itemArrayList;

		} catch (SQLException e) {

			e.printStackTrace();
		}
		return null;

	}

	public ArrayList<InternationalSupplier> getSuppListPreparedStatemen() {

		String query = "Select * from supplier  s, international_supplier i where s.supplier_id = i.supplier_id";

		try {
			prepStatment = myDriver.getMyConn().prepareStatement(query);
//			prepStatment.setInt(1, customerId);
			myres = prepStatment.executeQuery();

			ArrayList<InternationalSupplier> supplierList = new ArrayList<>();

			while (myres.next()) {

				supplierList.add(new InternationalSupplier(myres.getInt("supplier_id"),
						myres.getString("supplier_name"), myres.getString("address"), myres.getString("sales_contact"),
						myres.getString("company_name"), myres.getString("supplier_type"), myres.getInt("import_tax")));

//				System.out.println("search customer result in IDB: " + customerArrayList);

			}

			return supplierList;

		} catch (SQLException e) {

			e.printStackTrace();
		}
		return null;

	}

	public ArrayList<Customer> getCustomerPreparedStatementId(int customerId) {

		System.out.println("getting customer based on customer id");

		String query = "Select * from customer where customer_id = ?";

		try {
			prepStatment = myDriver.getMyConn().prepareStatement(query);
			prepStatment.setInt(1, customerId);
			myres = prepStatment.executeQuery();

			ArrayList<Customer> customerArrayList = new ArrayList<>();

			while (myres.next()) {

//				customer = new Customer(myres.getInt("customer_id"), myres.getString("fname"), myres.getString("lname"),
//						myres.getString("address"), myres.getString("postal_code"), myres.getString("phone_number"),
//						myres.getString("customer_type"));

				customerArrayList.add(new Customer(myres.getInt("customer_id"), myres.getString("fname"),
						myres.getString("lname"), myres.getString("address"), myres.getString("postal_code"),
						myres.getString("phone_number"), myres.getString("customer_type")));

//				System.out.println("search customer result in IDB: " + customerArrayList);

			}

			return customerArrayList;

		} catch (SQLException e) {

			e.printStackTrace();
		}
		return null;

	}

	public ArrayList<Customer> getCustomerPreparedStatementType(String customerType) {
		System.out.println("getting customer based on customer type");

		String query = "Select * from customer where customer_type = ?";

		try {
			prepStatment = myDriver.getMyConn().prepareStatement(query);
			prepStatment.setString(1, customerType);
			myres = prepStatment.executeQuery();

			ArrayList<Customer> customerArrayList = new ArrayList<>();

			while (myres.next()) {

				customerArrayList.add(new Customer(myres.getInt("customer_id"), myres.getString("fname"),
						myres.getString("lname"), myres.getString("address"), myres.getString("postal_code"),
						myres.getString("phone_number"), myres.getString("customer_type")));

//				System.out.println("search customer result in IDB: " + customerArrayList);

			}

			return customerArrayList;

		} catch (SQLException e) {

			e.printStackTrace();
		}
		return null;
	}

	public ArrayList<Customer> getCustomerPreparedStatementLname(String lname) {
		System.out.println("getting customer based on lastname ");

		String query = "Select * from customer where lname = ?";

		try {
			prepStatment = myDriver.getMyConn().prepareStatement(query);
			prepStatment.setString(1, lname);
			myres = prepStatment.executeQuery();

			ArrayList<Customer> customerArrayList = new ArrayList<>();

			while (myres.next()) {

				customerArrayList.add(new Customer(myres.getInt("customer_id"), myres.getString("fname"),
						myres.getString("lname"), myres.getString("address"), myres.getString("postal_code"),
						myres.getString("phone_number"), myres.getString("customer_type")));

//				System.out.println("search customer result in IDB: " + customerArrayList);

			}

			return customerArrayList;

		} catch (SQLException e) {

			e.printStackTrace();
		}
		return null;
	}

	public int updateCustomer(String firstName, String lastName, String address, String postalCode, String phoneNumber,
			String type, int id) {
		String updateClient = "UPDATE customer SET  fname=?,lname=?,address=?,postal_code=?,phone_number=?,customer_type=?"
				+ " WHERE customer_id=?";
		int rowCount = 0;

		try {
			PreparedStatement pStat = myDriver.getMyConn().prepareStatement(updateClient);
			pStat.setString(1, firstName);
			pStat.setString(2, lastName);
			pStat.setString(3, address);
			pStat.setString(4, postalCode);
			pStat.setString(5, phoneNumber);
			pStat.setString(6, type);
			pStat.setInt(7, id);

			rowCount = pStat.executeUpdate();
			System.out.println("row Count = " + rowCount);
		} catch (SQLException e) {
			e.printStackTrace();
		}

		return rowCount;

	}

	/**
	 * Delete client.
	 *
	 * @param id the id
	 */
	public int deleteCustomer(int id) {
		String deleteClient = "DELETE FROM customer WHERE customer_id=?";
		int rowCount = 0;
		try {
			PreparedStatement pStat = myDriver.getMyConn().prepareStatement(deleteClient);
			pStat.setInt(1, id);
			rowCount = pStat.executeUpdate();
			System.out.println("row Count = " + rowCount);
		} catch (SQLException e) {
			e.printStackTrace();
		}

		return rowCount;
	}

	public ArrayList<ElectricalItem> getElectricalItemListPrepStat() {

		String query = "select * from item i, electrical_item e where i.item_id = e.item_id";

		try {
			prepStatment = myDriver.getMyConn().prepareStatement(query);
//			prepStatment.setInt(1, customerId);
			myres = prepStatment.executeQuery();

			ArrayList<ElectricalItem> itemArrayList = new ArrayList<>();

			while (myres.next()) {

				if (myres.getString("power_type") != null) {
					itemArrayList.add(new ElectricalItem(myres.getInt("item_id"), myres.getString("item_name"),
							myres.getInt("item_quantity"), myres.getFloat("item_price"), myres.getString("item_type"),
							myres.getInt("supplier_id"), myres.getString("power_type")));
				}
//			if(myres.getString("power_type") == null) {
//				itemArrayList.add(new NonElectricalItem(myres.getInt("item_id"), myres.getString("item_name"), myres.getInt("item_quantity"), myres.getFloat("item_price"), 
//						myres.getString("item_type"), myres.getInt("supplier_id")));
//			}

//				System.out.println("search customer result in IDB: " + customerArrayList);

			}

			return itemArrayList;

		} catch (SQLException e) {

			e.printStackTrace();
		}
		return null;

	}

	public ArrayList<NonElectricalItem> getNonElectricalItemListPrepStat() {

		String query = "select * from item where item_type ='Non-Electrical'";

		try {
			prepStatment = myDriver.getMyConn().prepareStatement(query);
//			prepStatment.setInt(1, customerId);
			myres = prepStatment.executeQuery();

			ArrayList<NonElectricalItem> itemArrayList = new ArrayList<>();

			while (myres.next()) {

				itemArrayList.add(new NonElectricalItem(myres.getInt("item_id"), myres.getString("item_name"),
						myres.getInt("item_quantity"), myres.getFloat("item_price"), myres.getString("item_type"),
						myres.getInt("supplier_id")));

			}

			return itemArrayList;

		} catch (SQLException e) {

			e.printStackTrace();
		}
		return null;

	}

	public int updateItemQtyPrepStatement(int itemId, int itemqty) {

		String query = "update item set item_quantity = ? where item_id = ?";

		int rowCount = 0;

		try {
			PreparedStatement pStat = myDriver.getMyConn().prepareStatement(query);
			pStat.setInt(1, itemqty);
			pStat.setInt(2, itemId);

			rowCount = pStat.executeUpdate();
			System.out.println("row Count = " + rowCount);
		} catch (SQLException e) {
			e.printStackTrace();
		}

		return rowCount;

	}
}
