package Server.Controller.DatabaseController;

import java.io.FileNotFoundException;
import java.io.FileReader;
import java.sql.Date;
import java.sql.PreparedStatement;
//import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.Scanner;

public class CreateDBTables {

	private Driver myDriver;

	private Statement myStmt; // object of type statement from JDBC class that enables the creation "Query
	// statements"

//	private ResultSet myres;// object of type ResultSet from the JDBC class that stores the result of the
// query

//	private PreparedStatement prepStatment;

	private String databaseName = "inventory";

	public CreateDBTables(Driver myDriver) {

		this.myDriver = myDriver;
//		createDB();
//		createCustomerTable();
//		createSupplierTable();
//		createItemTable();
//		createPurchaseTable();
//		createOrderTable();
//		createOrderLineTable();
//		createElectricalItemTable();
//		createInternationalSuppTable();
//		fillCustomerTable();
//		fillItemTable();
//		fillSupplierTable();
//		fillIntSupplierTable();
//		fillElecItemTable();
//	insertCustomerPreparedStatment(1,"Fred","Evil","address","123456","123-234-234","C") ;
	}

	/**
	 * Create db.
	 */
	public void createDB() {

		String sql_temp = "drop database if exists " + databaseName;
		String sql_new = "use  " + databaseName;

		String sql = "CREATE DATABASE IF NOT EXISTS " + databaseName;

		// **********
		// Add a check to see if the Database is already created
		try {
			PreparedStatement pStat = myDriver.getMyConn().prepareStatement(sql);
			// pStat.setString(1,databaseName);
			pStat.executeUpdate(sql_temp);
			pStat.executeUpdate(sql);
			pStat.executeUpdate(sql_new);
		} catch (SQLException e) {
			System.out.println("DB already exists");
			// e.printStackTrace();
		}
	}

	public void createCustomerTable() {

		/*
		 * create table customer( customer_id integer not null, fname varchar(20) not
		 * null, lname varchar(20) not null, address varchar(50), postal_code
		 * varchar(7), phone_number varchar(12), customer_type char(10), primary key
		 * (customer_id) );
		 */
		String sql_temp = "DROP TABLE IF EXISTS CUSTOMER";

		String sql = "create table customer " + "(customer_id integer not null, " + "fname varchar(20) not null, "
				+ "lname varchar(20) not null, " + "address varchar(50), " + "postal_code varchar(7), "
				+ "phone_number varchar(12), " + "customer_type char(10), " + "primary key (customer_id)\r\n"
				+ "		) ";

		try {

			myStmt = myDriver.getMyConn().createStatement();
			myStmt.executeUpdate(sql_temp);
			myStmt.executeUpdate(sql);
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		System.out.println("Created customer table in given database...");
	}

	public void createSupplierTable() {
		String sql_temp = "DROP TABLE IF EXISTS supplier";

		/*
		 * CREATE TABLE supplier ( supplier_id integer not null, supplier_name
		 * varchar(25), supplier_type char(10), address varchar(25), company_name
		 * varchar(25), sales_contact varchar(25), primary key (supplier_id) );
		 */
		String sql = "CREATE TABLE supplier ( " + " supplier_id       integer not null, "
				+ " supplier_name		varchar(25), " + " supplier_type 	varchar(25), "
				+ " address      		varchar(50), " + " company_name 		varchar(25), "
				+ "sales_contact 	varchar(25), " + " primary key (supplier_id) \r\n" + "  ) ";

		try {
			myStmt = myDriver.getMyConn().createStatement();
			myStmt.executeUpdate(sql_temp);
			myStmt.executeUpdate(sql);
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		System.out.println("Created Supplier table in given database...");

	}

// Query to create Item table in db.
	public void createItemTable() {
		String sql_temp = "DROP TABLE IF EXISTS item";

		/*
		 * CREATE TABLE item ( item_id integer not null, item_name varchar(25),
		 * item_quantity integer, item_price decimal(10,2), item_type varchar(25),
		 * supplier_id integer, primary key (item_id), foreign key (supplier_id)
		 * references supplier(supplier_id) );
		 */
//		String sql = "create table item " + "(item_id integer not null, " + "item_name varchar(25), "
//				+ "item_quantity integer, " + "item_price decimal(10,2), " + "item_type varchar(25),, "
//				+ "supplier_id  integer, " + "primary key (item_id), "
//				+ "foreign key (supplier_id) references supplier(supplier_id)\r\n" + "			) ";

		String sql = "CREATE TABLE item ( item_id integer not null, item_name varchar(25),\r\n"
				+ "		item_quantity integer, item_price decimal(10,2), item_type varchar(25),\r\n"
				+ "		  supplier_id integer, primary key (item_id), foreign key (supplier_id)\r\n"
				+ "		  references supplier(supplier_id) );";
		try {
			myStmt = myDriver.getMyConn().createStatement();
			myStmt.executeUpdate(sql_temp);
			myStmt.executeUpdate(sql);
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		System.out.println("Created item table in given database...");
	}

// Query to create purchase table in db.
	public void createPurchaseTable() {
		String sql_temp = "DROP TABLE IF EXISTS purchase";

		/*
		 * CREATE TABLE purchase ( item_id integer not null, customer_id integer not
		 * null, primary key (item_id,customer_id), foreign key (item_id) references
		 * item(item_id), foreign key (customer_id) references customer(customer_id) );
		 */
		String sql = "create table purchase " + "(item_id integer not null, " + "customer_id  integer not null, "

				+ "primary key (item_id,customer_id), " + " foreign key (item_id) references item(item_id), "
				+ " foreign key (customer_id) references customer(customer_id)\r\n" + "			) ";

		try {
			myStmt = myDriver.getMyConn().createStatement();
			myStmt.executeUpdate(sql_temp);
			myStmt.executeUpdate(sql);
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		System.out.println("Created purchase table in given database...");
	}

// Query to create order table in db.
	public void createOrderTable() {
		String sql_temp = "DROP TABLE IF EXISTS orders";

		/*
		 * CREATE TABLE order ( order_id integer not null, order_date date, primary key
		 * (order_id)
		 * 
		 * );
		 */
		String sql = "create table orders " + "(order_id integer not null, " + "order_date  date, "

				+ "primary key (order_id) \r\n" + "			) ";

		try {
			myStmt = myDriver.getMyConn().createStatement();
			myStmt.executeUpdate(sql_temp);
			myStmt.executeUpdate(sql);
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		System.out.println("Created order table in given database...");
	}

// Query to create orderline table in db.
	public void createOrderLineTable() {
		String sql_temp = "DROP TABLE IF EXISTS orderline";

		/*
		 * CREATE TABLE orderline ( order_id integer not null, item_id integer not null,
		 * supplier_id integer , amount_ordered integer, primary key (order_id, item_id)
		 * foreign key (item_id) references item(item_id), foreign key (order_id)
		 * references order(order_id) foreign key (supplier_id) references
		 * supplier(supplier_id)
		 * 
		 * );
		 */
		String sql = "CREATE TABLE orderline ( order_id integer not null, item_id integer not null,\r\n"
				+ "		 supplier_id integer , amount_ordered integer, primary key (order_id, item_id),\r\n"
				+ "		 foreign key (item_id) references item(item_id), foreign key (order_id)\r\n"
				+ "		references orders(order_id), foreign key (supplier_id) references\r\n"
				+ "		 supplier(supplier_id)	);";

		try {
			myStmt = myDriver.getMyConn().createStatement();
			myStmt.executeUpdate(sql_temp);
			myStmt.executeUpdate(sql);
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		System.out.println("Created orderline table in given database...");
	}

// Query to create electrical_item table in db.
	public void createElectricalItemTable() {
		String sql_temp = "DROP TABLE IF EXISTS electrical_item";

		/*
		 * CREATE TABLE electrical_item ( item_id integer not null, power_type
		 * varchar(10), primary key (item_id)
		 * 
		 * );
		 */
		String sql = "create table electrical_item " + "(item_id integer not null, " + " power_type varchar(10), "

				+ "primary key (item_id) \r\n" + "			) ";

		try {
			myStmt = myDriver.getMyConn().createStatement();
			myStmt.executeUpdate(sql_temp);
			myStmt.executeUpdate(sql);
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		System.out.println("Created electrical_item table in given database...");
	}

// Query to create electrical_item table in db.
	public void createInternationalSuppTable() {
		String sql_temp = "DROP TABLE IF EXISTS international_supplier";

		/*
		 * CREATE TABLE international_supplier ( supplier_id integer not null,
		 * import_tax decimal, primary key (supplier_id)
		 * 
		 * );
		 */
		String sql = "create table international_supplier " + "(supplier_id integer not null, "
				+ " import_tax decimal , "

				+ "primary key (supplier_id) \r\n" + "			) ";

		try {
			myStmt = myDriver.getMyConn().createStatement();
			myStmt.executeUpdate(sql_temp);
			myStmt.executeUpdate(sql);
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		System.out.println("Created international_supplier table in given database...");
	}

	public int insertCustomerPreparedStatment(int id, String fName, String lName, String address, String postal_code,
			String phone_number, String customer_type) {

		int rowCount = 0;
		try {
			String query = "INSERT INTO customer ( customer_id,  fName,  lName,  address,  postal_code,  phone_number, customer_type) values (?,?,?,?,?,?,?)";
			PreparedStatement pStat = myDriver.getMyConn().prepareStatement(query);
			pStat.setInt(1, id);
			pStat.setString(2, fName);
			pStat.setString(3, lName);
			pStat.setString(4, address);
			pStat.setString(5, postal_code);
			pStat.setString(6, phone_number);
			pStat.setString(7, customer_type);
			rowCount = pStat.executeUpdate();
			System.out.println("row Count = " + rowCount);
			System.out.println("Added data in customer table");
			pStat.close();
		} catch (SQLException e) {
			e.printStackTrace();
		}

		return rowCount;

	}

	public int insertItemPreparedStatment(int id, String itemName, int itemQty, double itemPrice, String itemType,
			int suppId) {

		int rowCount = 0;
		try {
			String query = "INSERT INTO item ( item_id,  item_name,  item_quantity,  item_price,  item_type,  supplier_id) values (?,?,?,?,?,?)";
			PreparedStatement pStat = myDriver.getMyConn().prepareStatement(query);
			pStat.setInt(1, id);
			pStat.setString(2, itemName);
			pStat.setInt(3, itemQty);
			pStat.setDouble(4, itemPrice);
			pStat.setString(5, itemType);
			pStat.setInt(6, suppId);

			rowCount = pStat.executeUpdate();
			System.out.println("row Count = " + rowCount);
			System.out.println("Added data in item table");
			pStat.close();
		} catch (SQLException e) {
			e.printStackTrace();
		}

		return rowCount;

	}

	public int insertSupplierPreparedStatment(int id, String suppName, String supplierType, String address,
			String companyName, String salesContact) {

		int rowCount = 0;
		try {
			String query = "INSERT INTO supplier ( supplier_id,  supplier_name,  supplier_type,  address,  company_name,  sales_contact) values (?,?,?,?,?,?)";
			PreparedStatement pStat = myDriver.getMyConn().prepareStatement(query);
			pStat.setInt(1, id);
			pStat.setString(2, suppName);
			pStat.setString(3, supplierType);
			pStat.setString(4, address);
			pStat.setString(5, companyName);
			pStat.setString(6, salesContact);

			rowCount = pStat.executeUpdate();
//			System.out.println("row Count = " + rowCount);
			System.out.println("Added data in supplier table");
			pStat.close();
		} catch (SQLException e) {
			e.printStackTrace();
		}

		return rowCount;

	}

	public int insertIntSupplierPrepStatment(int id, double importTax) {

		int rowCount = 0;
		try {
			String query = "INSERT INTO international_supplier ( supplier_id,  import_tax) values (?,?)";
			PreparedStatement pStat = myDriver.getMyConn().prepareStatement(query);
			pStat.setInt(1, id);
			pStat.setDouble(2, importTax);

			rowCount = pStat.executeUpdate();
//			System.out.println("row Count = " + rowCount);
			System.out.println("Added data in international_supplier table");
			pStat.close();
		} catch (SQLException e) {
			e.printStackTrace();
		}

		return rowCount;

	}

	public int insertElecItemPrepStatment(int id, String powerType) {

		int rowCount = 0;
		try {
			String query = "INSERT INTO electrical_item ( item_id,  power_type) values (?,?)";
			PreparedStatement pStat = myDriver.getMyConn().prepareStatement(query);
			pStat.setInt(1, id);
			pStat.setString(2, powerType);

			rowCount = pStat.executeUpdate();
//			System.out.println("row Count = " + rowCount);
			System.out.println("Added data in electrical_item table");
			pStat.close();
		} catch (SQLException e) {
			e.printStackTrace();
		}

		return rowCount;

	}

	public void fillCustomerTable() {
		try {
			Scanner sc = new Scanner(new FileReader("clients.txt"));
			while (sc.hasNext()) {
				String customerInfo[] = sc.nextLine().split(";");
				insertCustomerPreparedStatment(Integer.parseInt(customerInfo[0]), customerInfo[1], customerInfo[2],
						customerInfo[3], customerInfo[4], customerInfo[5], customerInfo[6]);
			}
			sc.close();
		} catch (FileNotFoundException e) {
			System.err.println("File " + "clients.txt" + " Not Found!");
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	public void fillItemTable() {
		try {
			Scanner sc = new Scanner(new FileReader("items.txt"));
			while (sc.hasNext()) {
				String items[] = sc.nextLine().split(";");
				insertItemPreparedStatment(Integer.parseInt(items[0]), items[1], Integer.parseInt(items[2]),
						Double.parseDouble(items[3]), items[4], Integer.parseInt(items[5]));
			}
			sc.close();
		} catch (FileNotFoundException e) {
			System.err.println("File " + "items.txt" + " Not Found!");
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	public void fillSupplierTable() {

		try {
			Scanner sc = new Scanner(new FileReader("suppliers.txt"));
			while (sc.hasNext()) {
				String suppliers[] = sc.nextLine().split(";");
				insertSupplierPreparedStatment(Integer.parseInt(suppliers[0]), suppliers[1], suppliers[2], suppliers[3],
						suppliers[4], suppliers[5]);
			}
			sc.close();
		} catch (FileNotFoundException e) {
			System.err.println("File " + "suppliers.txt" + " Not Found!");
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	public void fillIntSupplierTable() {
		try {
			Scanner sc = new Scanner(new FileReader("International_suppliers.txt"));
			while (sc.hasNext()) {
				String intSuppliers[] = sc.nextLine().split(";");
				insertIntSupplierPrepStatment(Integer.parseInt(intSuppliers[0]), Double.parseDouble(intSuppliers[1]));
			}
			sc.close();
		} catch (FileNotFoundException e) {
			System.err.println("File " + "International_suppliers.txt" + " Not Found!");
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	public void fillElecItemTable() {
		try {
			Scanner sc = new Scanner(new FileReader("electrical_items.txt"));
			while (sc.hasNext()) {
				String elecItems[] = sc.nextLine().split(";");
				insertElecItemPrepStatment(Integer.parseInt(elecItems[0]), elecItems[1]);
			}
			sc.close();
		} catch (FileNotFoundException e) {
			System.err.println("File " + "electrical_items.txt" + " Not Found!");
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	public int insertOrderLinePrepStatment(int orderId, int toolId, int supplierId, int quantity) {

		int rowCount = 0;

		try {
			String query = "INSERT INTO orderline ( order_id,  item_id,  supplier_id,  amount_ordered) values (?,?,?,?)";

			PreparedStatement pStat = myDriver.getMyConn().prepareStatement(query);
			pStat.setInt(1, orderId);
			pStat.setInt(2, toolId);
			pStat.setInt(3, supplierId);
			pStat.setInt(4, quantity);

			rowCount = pStat.executeUpdate();
//			System.out.println("row Count = " + rowCount);
			System.out.println("Added data in orderline table");
			pStat.close();
		} catch (SQLException e) {
			e.printStackTrace();
		}

		return rowCount;

	}

	public int insertOrderPrepStatment(int orderId, String date) {

		int rowCount = 0;

		try {
			String query = "INSERT INTO orders ( order_id,  order_date) values (?,?)";

			PreparedStatement pStat = myDriver.getMyConn().prepareStatement(query);
			pStat.setInt(1, orderId);
			pStat.setString(2, date);
		
			rowCount = pStat.executeUpdate();
//			System.out.println("row Count = " + rowCount);
			System.out.println("Added data in orders table");
			pStat.close();
		} catch (SQLException e) {
			e.printStackTrace();
		}

		return rowCount;

	}

}