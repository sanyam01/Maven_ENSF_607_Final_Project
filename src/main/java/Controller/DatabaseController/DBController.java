package Controller.DatabaseController;

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
	
	
	
	
	

//	public static void main(String[] args) {
//		
//		DBController myDBController = new DBController();
//		
//		// TODO Auto-generated method stub
//		
//		String query_res = myDBController.dbManager.getItem(1001);
//		System.out.println("in DBController main: "+ query_res);
//		
//		
//
//	}

}
