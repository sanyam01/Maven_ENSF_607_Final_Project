package Server.Model;

public class Residential extends Customer{

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	public Residential(int customerID, String firstName, String lastName, String address, String postalCode, String phoneNumber, String customerType) {
		super(customerID, firstName, lastName, address, postalCode, phoneNumber, customerType);
	}
	
	public Residential() {
		super();
	}
}
