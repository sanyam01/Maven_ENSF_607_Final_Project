package Server.Model;

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
	private ArrayList<ElectricalItem> elecItemList;
	private ArrayList<NonElectricalItem> nonElecItemList;
	
//	public ItemsList(ArrayList<ElectricalItem> elecItemList, ArrayList<NonElectricalItem> nonElecItemList) {
//		
//		this.setElecItemList(elecItemList);
//		this.nonElecItemList = nonElecItemList;
//
//
//	}
	
	public ItemsList(ArrayList<Items> list) {
		this.setItemsList(list);
	}

	public ItemsList() {
		// TODO Auto-generated constructor stub
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

	

	public ArrayList<NonElectricalItem> getNonElecItemList() {
		return nonElecItemList;
	}

	public void setNonElecItemList(ArrayList<NonElectricalItem> nonElecItemList) {
		this.nonElecItemList = nonElecItemList;
	}

	public ArrayList<ElectricalItem> getElecItemList() {
		return elecItemList;
	}

	public void setElecItemList(ArrayList<ElectricalItem> elecItemList) {
		this.elecItemList = elecItemList;
	}
	
	
}
