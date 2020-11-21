package Server.Controller.DatabaseController;

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
	
	
	
	
	



}
