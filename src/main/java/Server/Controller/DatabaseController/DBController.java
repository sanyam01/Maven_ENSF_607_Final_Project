package Server.Controller.DatabaseController;

import java.util.ArrayList;

import Server.Model.Customer;
import Server.Model.ElectricalItem;
import Server.Model.InternationalSupplier;
import Server.Model.Items;
import Server.Model.ItemsList;
import Server.Model.NonElectricalItem;
import Server.Model.Suppliers;


public class DBController {
	
	
	
	private InventoryDBManager dbManager;
	private CreateDBTables createDBTables;
	
	
	
	public DBController() {
		
		Driver myDriver = new Driver();
		
		setDbManager(new InventoryDBManager(myDriver));
		createDBTables = new CreateDBTables(myDriver);
		createDB(); //create database if not exists
		createTables(); //create tables
		fillTables(); //fill tables
		
	}

	public InventoryDBManager getDbManager() {
		return dbManager;
	}

	public void setDbManager(InventoryDBManager dbManager) {
		this.dbManager = dbManager;
	}
	
	
	public ArrayList<Customer> getCustomerbyId(int customerId) {
		return dbManager.getCustomerPreparedStatementId( customerId) ;
	}
	
	
	public ArrayList<Customer> getCustomerbyType(String customerType) {
		return dbManager.getCustomerPreparedStatementType( customerType) ;
	}
	
	
	public ArrayList<Customer> getCustomerbyLname(String lname) {
		return dbManager.getCustomerPreparedStatementLname(lname);
	}
	
	
	public boolean updateCustomer(String firstName, String lastName, String address, String postalCode, String phoneNumber,
			String type, int id) {
		
		boolean updateFlag = false;
		
		 int rowcount = dbManager.updateCustomer(firstName, lastName, address, postalCode, phoneNumber, type, id);
		 if(rowcount == 1) {
			 //customer updated successfully
			 updateFlag = true;
		 }
		 
		 return updateFlag;
	}
	
	
	public boolean insertCustomer(String firstName, String lastName, String address, String postalCode, String phoneNumber,
			String type, int id) {
		
		boolean updateFlag = false;
		
		 int rowcount = createDBTables.insertCustomerPreparedStatment(id, firstName, lastName, address, postalCode, phoneNumber, type);
		 if(rowcount == 1) {
			 //customer inserted successfully
			 updateFlag = true;
		 }
		 
		 return updateFlag;
	}
	

	

	public boolean deleteCustomer(int id) {
		
		boolean updateFlag = false;
		int rowcount = dbManager.deleteCustomer(id);
		if(rowcount == 1) {
			 //customer deleted successfully
			 updateFlag = true;
		 }
		 
		 return updateFlag;
	}
	
	public void createDB() {
		
		createDBTables.createDB();
		
	}
	
	public void createTables() {
		createDBTables.createCustomerTable();
		createDBTables.createSupplierTable();
		createDBTables.createItemTable();
		createDBTables.createPurchaseTable();
		createDBTables.createOrderTable();
		createDBTables.createOrderLineTable();
		createDBTables.createElectricalItemTable();
		createDBTables.createInternationalSuppTable();
	}
	
	
	
	public void fillTables() {
		createDBTables.fillCustomerTable();
		
		createDBTables.fillSupplierTable();
		createDBTables.fillIntSupplierTable();
		createDBTables.fillItemTable();
		createDBTables.fillElecItemTable();
	}
	
	
	public ArrayList<Items> getItemList() {
		return dbManager.getItemListPreparedStatemen();
	}
	
	public ItemsList getItemById(int itemId) {
		return dbManager.getItemByIdPreparedStatement(itemId);
	}
	
	public ItemsList getItemByName(String itemName) {
		return dbManager.getItemByNamePreparedStatement(itemName);
	}
	
	public ArrayList<Items> getItemQty(String itemName) {
		return dbManager.getItemQtyPreparedStatement(itemName);
	}
	
	public ArrayList<Items> getItemQty(int itemId) {
		return dbManager.getItemQtyPreparedStatement(itemId);
	}
	
	
	public ArrayList<InternationalSupplier> getSupplierList() {
		return dbManager.getSuppListPreparedStatemen();
	}
	
	public ArrayList<ElectricalItem> getElectricalItems() {
		return dbManager.getElectricalItemListPrepStat();
	}
	
	public ArrayList<NonElectricalItem> getNonElectricalItems() {
		return dbManager.getNonElectricalItemListPrepStat();
	}
	
	



}
