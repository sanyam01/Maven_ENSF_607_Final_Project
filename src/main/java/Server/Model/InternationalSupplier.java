package Server.Model;

public class InternationalSupplier extends Suppliers{
	
	private double importTax;
	
	
	public InternationalSupplier(int supplierID, String supplierName, String supplierAddress, String supplierContact,
			String companyName, String supplierType, double importTax) {
		
		super(supplierID, supplierName, supplierAddress, supplierContact, companyName, supplierType);
		this.importTax = importTax;

	}

	

	public double getImportTax() {
		return importTax;
	}

	public void setImportTax(double importTax) {
		this.importTax = importTax;
	}

}
