package Server.Controller.DatabaseController;

import java.util.ArrayList;

import Server.Model.Customer;


public class DBController {
	
	
	
	private InventoryDBManager dbManager;
	private CreateDBTables createDBTables;
	
	
	
	public DBController() {
		
		Driver myDriver = new Driver();
		
		setDbManager(new InventoryDBManager(myDriver));
		createDBTables = new CreateDBTables(myDriver);
		
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
	
	
	
	
	
	



}
