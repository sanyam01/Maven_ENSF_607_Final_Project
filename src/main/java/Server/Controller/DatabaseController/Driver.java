package Server.Controller.DatabaseController;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class Driver implements 	JDBCredentials{
	
	// Attributes
	private Connection myConn;//Object of type connection from the JDBC class that deals with connecting to the database
	
	
	public Driver() {
		
		try {

			setMyConn(DriverManager.getConnection("jdbc:mysql://localhost:3306/inventory", "root", "San@0103"));
			System.out.println("Connected to DB...");
			
		} catch (SQLException e) {
			System.out.println("Unable to connect to DB");
			e.printStackTrace();
		}
	}


	public Connection getMyConn() {
		return myConn;
	}


	public void setMyConn(Connection myConn) {
		this.myConn = myConn;
	}
	
//	public void initializeConnection() {
//		try {
//            //Register JDBC driver
////			Driver driver = new com.mysql.cj.jdbc.Driver();
////			DriverManager.registerDriver(driver);
//			
//            //Open a connection
//			myConn = DriverManager.getConnection(DB_URL, USERNAME, PASSWORD);
//			
//		} catch (SQLException e) {
//			System.out.println("Unable to connect to DB");
//			e.printStackTrace();
//		}
//
//	}
//	
	

}
