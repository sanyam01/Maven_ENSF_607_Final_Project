package Server.Model;

import java.io.Serializable;

public  class Items implements Serializable {

	

	/**
	 * itemID represents the ID of the item. Each item has unique ID
	 */
	private int itemID;

	
	/**
	 * itemType represents the type of the item i.e. Electrical and
	 * non-electrical
	 */
	private String itemType;
	
	/**
	 * itemName represents the name of the item
	 */
	private String itemName;

	/**
	 * itemQuantity tells the quantity of the item in the inventory
	 */
	private int itemQuantity;

	/**
	 * itemPrice tells the price of the item
	 */
	private float itemPrice;

	/**
	 * supplierID represents the ID of the supplier
	 */
	private int supplierID;
	
	private Suppliers sup;
	
	
	
	


	/**
	 * Constructs a constructor of Items and assigns all the values to its member
	 * variables
	 * 
	 * @param itemID       iD of an item
	 * @param itemName     name of an item
	 * @param itemQuantity quantity left in the stock
	 * @param itemPrice    price of an item
	 * @param supplierID   iD of the supplier that sells that item
	 */
	public Items(int itemID, String itemName, int itemQuantity, float itemPrice, String itemType, int supplierID) {

		this.setItemID(itemID);
		this.setItemName(itemName);
		this.setItemPrice(itemPrice);
		this.setItemQuantity(itemQuantity);
		this.setItemType(itemType);
		this.setSupplierID(supplierID);
//		this.setPowerType(null);
	}

	/**
	 * toString method overrides the method of the super class.
	 * 
	 * @return string comprising the tool name, id, price and supplier id of the
	 *         item.
	 */
	@Override
	public String toString() {
		String s = this.getItemName();
		s = "itemName: " + s + ", itemID: " + this.getItemID() + ", supplierID: " + this.getSupplierID()
				+ ", quantity: " + this.getItemQuantity() + ", price : " + this.getItemPrice();
		return s;
	}

	public int getItemID() {
		return itemID;
	}

	public void setItemID(int itemID) {
		this.itemID = itemID;
	}

	public String getItemName() {
		return itemName;
	}

	public void setItemName(String itemName) {
		this.itemName = itemName;
	}

	public int getItemQuantity() {
		return itemQuantity;
	}

	public void setItemQuantity(int itemQuantity) {
		this.itemQuantity = itemQuantity;
	}

	public float getItemPrice() {
		return itemPrice;
	}

	public void setItemPrice(float itemPrice) {
		this.itemPrice = itemPrice;
	}

	public int getSupplierID() {
		return supplierID;
	}

	public void setSupplierID(int supplierID) {
		this.supplierID = supplierID;
	}

	public String getItemType() {
		return itemType;
	}

	public void setItemType(String itemType) {
		this.itemType = itemType;
	}
	
	
	public String decreaseQuantity(Order order) {

		OrderLines ol = null;
		String s = "\nNo new OrderLine generated. Printing order: \n";
		s = s + "....................................................................\n\n";
		if (this.getItemQuantity() > 0)
			this.setItemQuantity(this.getItemQuantity() - 1);
		if (this.getItemQuantity() < 40) {
			int initiallyItem = this.getItemQuantity();
			ol = new OrderLines(this, 50 - initiallyItem);
			order.addOrderLine(ol);
			this.setItemQuantity(50);
			s = "\nNew order line generated. Updated Order for the day is:\n\n";
			s = s + "....................................................................\n";
			s = s + "Order Id : " + order.getOrderId() + "\n" + "Order date : " + order.getDate() + "\n\n";

		}

		else {
			s = s + "Order Id : " + order.getOrderId() + "\n" + "Order date : " + order.getDate() + "\n\n";
		}
		for (OrderLines k : order.getOrderLines()) {
			s = s + "ItemName: " + k.getItem().getItemName() + ", Quantity ordered: " + k.getAmount()
					+ ", Supplier name: " + k.getItem().getSup().getSupplierName() + "\n";
		}

		s = s + "\n....................................................................\n\n";
		return s;

	}

	public Suppliers getSup() {
		return sup;
	}

	public void setSup(Suppliers sup) {
		this.sup = sup;
	}
	
	
	public void addSupplier(SupplierList list) {
		for (Suppliers s : list.getList()) {
			if (s.getSupplierID() == this.getSupplierID())
				this.sup = s;

		}
	}

	

	

}
