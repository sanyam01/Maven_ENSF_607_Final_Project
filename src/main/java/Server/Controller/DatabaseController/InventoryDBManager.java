package Server.Controller.DatabaseController;

import java.io.FileReader;
import java.sql.*;
import java.util.ArrayList;
import java.util.Scanner;

import Server.Model.Customer;
import Server.Model.CustomerList;

public class InventoryDBManager {

	// Attributes
//	private Connection myConn;//Object of type connection from the JDBC class that deals with connecting to the database

	private Statement myStmt; // object of type statement from JDBC class that enables the creation "Query
								// statements"

	private ResultSet myres;// object of type ResultSet from the JDBC class that stores the result of the
							// query

	private PreparedStatement prepStatment;

	private Driver myDriver;

	Customer customer = null;
//	CustomerList customerList;

	public InventoryDBManager(Driver myDriver) {

		this.myDriver = myDriver;
		
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

				
				
				customerArrayList.add(new Customer(myres.getInt("customer_id"), myres.getString("fname"), myres.getString("lname"),
						myres.getString("address"), myres.getString("postal_code"), myres.getString("phone_number"),
						myres.getString("customer_type")));
				
//				System.out.println("search customer result in IDB: " + customerArrayList);

			}
			
			return customerArrayList;

		} catch (SQLException e) {

			e.printStackTrace();
		}
		return null;

	}
	
	
	public ArrayList<Customer> getCustomerPreparedStatementType(String customerType){
		System.out.println("getting customer based on customer type");

		String query = "Select * from customer where customer_type = ?";

		try {
			prepStatment = myDriver.getMyConn().prepareStatement(query);
			prepStatment.setString(1, customerType);
			myres = prepStatment.executeQuery();
			
			ArrayList<Customer> customerArrayList = new ArrayList<>();
			
			
			while (myres.next()) {
				
				customerArrayList.add(new Customer(myres.getInt("customer_id"), myres.getString("fname"), myres.getString("lname"),
						myres.getString("address"), myres.getString("postal_code"), myres.getString("phone_number"),
						myres.getString("customer_type")));
				
//				System.out.println("search customer result in IDB: " + customerArrayList);

			}
			
			return customerArrayList;

		} catch (SQLException e) {

			e.printStackTrace();
		}
		return null;
    }
	
	public ArrayList<Customer> getCustomerPreparedStatementLname(String lname){
		System.out.println("getting customer based on lastname ");

		String query = "Select * from customer where lname = ?";

		try {
			prepStatment = myDriver.getMyConn().prepareStatement(query);
			prepStatment.setString(1, lname);
			myres = prepStatment.executeQuery();
			
			ArrayList<Customer> customerArrayList = new ArrayList<>();
			
			
			while (myres.next()) {
				
				customerArrayList.add(new Customer(myres.getInt("customer_id"), myres.getString("fname"), myres.getString("lname"),
						myres.getString("address"), myres.getString("postal_code"), myres.getString("phone_number"),
						myres.getString("customer_type")));
				
//				System.out.println("search customer result in IDB: " + customerArrayList);

			}
			
			return customerArrayList;

		} catch (SQLException e) {

			e.printStackTrace();
		}
		return null;
    }

}
