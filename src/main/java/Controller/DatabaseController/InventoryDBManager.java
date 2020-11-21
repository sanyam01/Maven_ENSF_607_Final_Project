package Controller.DatabaseController;

import java.sql.*;

import Model.Customer;

public class InventoryDBManager {

	// Attributes
//	private Connection myConn;//Object of type connection from the JDBC class that deals with connecting to the database

	private Statement myStmt; // object of type statement from JDBC class that enables the creation "Query
								// statements"

	private ResultSet myres;// object of type ResultSet from the JDBC class that stores the result of the
							// query

	private PreparedStatement prepStatment;

	private Driver myDriver;
//	private CreateDBTables createDBTables;

	public InventoryDBManager() {

		myDriver = new Driver();
		CreateDBTables createDBTables = new CreateDBTables( myDriver);
//		createCustomerTable();
		

	}

	// Query to create Customer table in db.
	
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

	

	public String getItemPreparedStatement(int itemId) {

		System.out.println("getting item based on item id");
		String temp = "";
		String query = "select item_id,item_name from item where item_id = ?";

		try {
//			mystm = myConn.createStatement();

			prepStatment = myDriver.getMyConn().prepareStatement(query);
			prepStatment.setInt(1, itemId);

			myres = prepStatment.executeQuery();
//			System.out.println(myres);

			// 4. process the result set
			while (myres.next()) {

				temp = myres.getInt("item_id") + ", " + myres.getString("item_name");
			}
//			

		} catch (SQLException e) {

			e.printStackTrace();
		}
		return temp;

	}

	public Customer getCustomerPreparedStatementId(int customerId) {

		System.out.println("getting customer based on customer id");
		String temp = "";

		String query = "Select * from customer where customerId = ?";
		Customer customer = null;

		try {
			prepStatment = myDriver.getMyConn().prepareStatement(query);
			prepStatment.setInt(1, customerId);
			myres = prepStatment.executeQuery();
			// 4. process the result set
			while (myres.next()) {

				temp = myres.getInt("customer_id") + ", " + myres.getString("fname") + " , " + myres.getString("lname")
						+ " , " + myres.getString("customer_type");

				customer = new Customer(myres.getInt("customer_id"), myres.getString("fname"),
						myres.getString("lname"), myres.getString("address"), myres.getString("postal_code"), myres.getString("phone_number"),myres.getString("customer_type"));

//				return myCustomer;
			}

		} catch (SQLException e) {

			e.printStackTrace();
		}
		return customer;

	}

	/*
	 * public static void main(String[] args) throws SQLException { Statement mystm
	 * = null;
	 * 
	 * try { //1. get a connection Connection myConn =
	 * DriverManager.getConnection("jdbc:mysql://localhost:3306/inventory", "root",
	 * "password");
	 * 
	 * //2. craete a statement mystm = myConn.createStatement();
	 * 
	 * //3. execute sql query ResultSet myres =
	 * mystm.executeQuery("select item_id,item_name from item");
	 * 
	 * //4. process the result set while(myres.next()) {
	 * System.out.println(myres.getString("item_id") + ", " +
	 * myres.getString("item_name")); } } catch(Exception e) { e.printStackTrace();
	 * } finally { if(mystm != null) { mystm.close(); } } }
	 */

}
