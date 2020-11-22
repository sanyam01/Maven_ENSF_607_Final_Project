package Server.Controller.DatabaseController;

import Server.Model.Customer;

public class DBController {
	
	
	
	private InventoryDBManager dbManager;
	
	public DBController() {
		
		setDbManager(new InventoryDBManager());
		
	}

	public InventoryDBManager getDbManager() {
		return dbManager;
	}

	public void setDbManager(InventoryDBManager dbManager) {
		this.dbManager = dbManager;
	}
	
	
	public Customer getCustomerbyId(int customerId) {
		return dbManager.getCustomerPreparedStatementId( customerId) ;
	}
	
	
	
	



}
