package Server.Model;

public class Shop {
	
	
	/**
	 * supplierList represents the list of all the suppliers
	 */
	private SupplierList supplierList;
	/**
	 * inventory represents the inventory of all the tools
	 */
	private Inventory inventory;

	/**
	 * Constructs the shop object
	 * 
	 * It calls the load methods in file manager to create the supplier list and the
	 * items list
	 * 
	 * @throws IOException
	 */
	public Shop() {

		
//		inventory.addSuppliers(supplierList);
		supplierList.addItems(inventory.getListItems());

	}
	
	public SupplierList getSupplierList() {
		return supplierList;
	}

	public void setSupplierList(SupplierList supplierList) {
		this.supplierList = supplierList;
	}

	public Inventory getInventory() {
		return inventory;
	}

	public void setInventory(Inventory inventory) {
		this.inventory = inventory;
	}
	
	/**
	 * decreaseQuantity calls the method in the Items to decrease the quantity of
	 * the item by 1, based on the tool name
	 * 
	 * @param toolName name of the tool corresponding to which quantity is to be
	 *                 decreased
	 * @return the quantity of the item
	 */
	public String decreaseQuantity(String toolName) {
		return this.inventory.decreaseQuantity(toolName);

	}

	/**
	 * decreaseQuantity calls the method in the Items to decrease the quantity of
	 * the item by 1, based on the tool ID
	 * 
	 * @param decreaseID id of the tool corresponding to which quantity is to be
	 *                   decreased
	 * @return the quantity of the item
	 */

	public String decreaseQuantity(int decreaseID) {
		String s = "Item not found";
		for (Items i : this.inventory.getListItems()) {
			if (decreaseID == i.getItemID())
				s = this.inventory.decreaseQuantity(i.getItemName());
		}

		return s;

	}


}
