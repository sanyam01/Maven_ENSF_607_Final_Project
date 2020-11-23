package ClientModel;

import java.io.Serializable;
import java.util.ArrayList;

public class ItemsList implements Serializable{

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	/**
	 * list of customers
	 */
	private ArrayList<Items> itemsList;
	
	public ItemsList() {
		super();
	}
	
	public ItemsList(ArrayList<Items> list) {
		this.setItemsList(list);
	}

	public ArrayList<Items> getItemsList() {
		return itemsList;
	}

	public void setItemsList(ArrayList<Items> itemsList) {
		this.itemsList = itemsList;
	}
	
	
	public void addItems(Items item) {
		itemsList.add(item);

	}
	
	
}
