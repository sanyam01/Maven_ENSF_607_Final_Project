package Server.Controller.DatabaseController;

import java.util.ArrayList;

import Server.Model.Customer;


public class DBController {
	
	
	
	private InventoryDBManager dbManager;
	
	
	
	public DBController() {
		
		Driver myDriver = new Driver();
		
		setDbManager(new InventoryDBManager(myDriver));
		CreateDBTables createDBTables = new CreateDBTables(myDriver);
		
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
	
	
	
	
	
	



}
