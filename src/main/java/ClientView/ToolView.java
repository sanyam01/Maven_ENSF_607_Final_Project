package ClientView;

import java.awt.BorderLayout;
import java.awt.Container;

import java.awt.*;
import java.awt.event.*;
import javax.swing.*;

public class ToolView extends JFrame {
	
	private JButton checkQuantity;
	private JButton decrease;
	private JButton clear;
	private JTextField toolID;
	private JTextField toolName;
	private JTextField toolType;
	private JTextField toolQuantity;
	private JTextField toolPrice;
	private JTextField supplierID;
	private JTextArea displayText;
	private JRadioButton searchToolID; 
    private JRadioButton searchToolName;
    private JTextField searchParameter;
    private JButton search;
    private JButton clearSearch;
    
    private ButtonGroup group; 
	
	public ToolView() {
		
		
		//high-level container
		Container contentPanel = getContentPane();
		contentPanel.setLayout(new BorderLayout());
		
		contentPanel.add("North", new JLabel("Tool Management Screen"));
		
		JPanel rightView = new JPanel(new  BorderLayout());// JPanel for adding right side of Client GUI
		rightView.add("North", new JLabel("Tool Information"));
		
		JPanel buttons = new JPanel();// JPanel for adding for adding buttons Save, delete, and clear
		
		checkQuantity = new JButton("Check Quantity");
		decrease = new JButton("Decrease");
		clear = new JButton("Clear");
		
		// adding buttons to JPanel
		buttons.add(checkQuantity);
		buttons.add(decrease);
		buttons.add(clear);
		
		rightView.add("South", buttons);// added buttns to the rightView
		
		JPanel centerRightView = new JPanel();
		centerRightView.setLayout(new GridLayout(6,2,2,2));
		
		centerRightView.add(new JLabel("ToolID"));//adding toolID
		toolID = new JTextField(20);
		centerRightView.add(toolID);
		
		centerRightView.add(new JLabel("Name"));//adding Name
		toolName = new JTextField();
		centerRightView.add(toolName);
		
		centerRightView.add(new JLabel("ToolType"));//adding Name
		toolType = new JTextField();
		centerRightView.add(toolType);
		
		centerRightView.add(new JLabel("Tool Quantity"));//adding Tool Quantity
		toolQuantity = new JTextField();
		centerRightView.add(toolQuantity);
		
		centerRightView.add(new JLabel("Tool Price"));//adding tool price
		toolPrice = new JTextField();
		centerRightView.add(toolPrice);
		
		centerRightView.add(new JLabel("Supplier ID"));//adding Phone no
		supplierID = new JTextField();
		centerRightView.add(supplierID);
		
		rightView.add("Center", centerRightView);
		contentPanel.add("East", rightView);
		
		JPanel leftView = new JPanel(new BorderLayout());
		
		displayText = new JTextArea(10,10);
		displayText.setEditable(false);
		
		JScrollPane scroll = new JScrollPane(displayText);
		leftView.add("Center", new JLabel("Search Results :"));
		leftView.add("South", scroll);
		
		JPanel leftNorth = new JPanel(new GridLayout(7,1,2,2));
		leftNorth.add(new JLabel("Search Tools"));
		leftNorth.add(new JLabel("Select type of search to be performed"));
		
		searchToolID = new JRadioButton("Tool ID");
		searchToolName = new JRadioButton("Name");
	    
	    group = new  ButtonGroup();
	    group.add(searchToolID);
	    group.add(searchToolName);
	    
	    leftNorth.add(searchToolID);
	    leftNorth.add(searchToolName);
	    leftNorth.add(new JLabel("Enter the search parameter below"));
	    
	    JPanel leftViewButtons = new JPanel();// for adding text field, search and clear search
	    searchParameter = new JTextField(20);// for inputting the search text
	    search = new JButton("Search");
	    clearSearch = new JButton("Clear Search");
	    
	    leftViewButtons.add(searchParameter);
	    leftViewButtons.add(search);
	    leftViewButtons.add(clearSearch);
	    leftView.add(leftViewButtons);
		leftView.add("North", leftNorth);
		
		contentPanel.add("West", leftView);
			
	}
	
	public void addToolListener(ActionListener toolListener) {
		// TODO Auto-generated method stub
		search.addActionListener(toolListener);
	}

	public JButton getCheckQuantity() {
		return checkQuantity;
	}

	public void setCheckQuantity(JButton checkQuantity) {
		this.checkQuantity = checkQuantity;
	}

	public JButton getDecrease() {
		return decrease;
	}

	public void setDecrease(JButton decrease) {
		this.decrease = decrease;
	}

	public JButton getClear() {
		return clear;
	}

	public void setClear(JButton clear) {
		this.clear = clear;
	}

	public JTextField getToolID() {
		return toolID;
	}

	public void setToolID(JTextField toolID) {
		this.toolID = toolID;
	}

	public JTextField getToolName() {
		return toolName;
	}

	public void setToolName(JTextField toolName) {
		this.toolName = toolName;
	}

	public JTextField getToolType() {
		return toolType;
	}

	public void setToolType(JTextField toolType) {
		this.toolType = toolType;
	}

	public JTextField getToolQuantity() {
		return toolQuantity;
	}

	public void setToolQuantity(JTextField toolQuantity) {
		this.toolQuantity = toolQuantity;
	}

	public JTextField getToolPrice() {
		return toolPrice;
	}

	public void setToolPrice(JTextField toolPrice) {
		this.toolPrice = toolPrice;
	}

	public JTextField getSupplierID() {
		return supplierID;
	}

	public void setSupplierID(JTextField supplierID) {
		this.supplierID = supplierID;
	}

	public JTextArea getDisplayText() {
		return displayText;
	}

	public void setDisplayText(JTextArea displayText) {
		this.displayText = displayText;
	}

	public JRadioButton getSearchToolID() {
		return searchToolID;
	}

	public void setSearchToolID(JRadioButton searchToolID) {
		this.searchToolID = searchToolID;
	}

	public JRadioButton getSearchToolName() {
		return searchToolName;
	}

	public void setSearchToolName(JRadioButton searchToolName) {
		this.searchToolName = searchToolName;
	}

	public JTextField getSearchParameter() {
		return searchParameter;
	}

	public void setSearchParameter(JTextField searchParameter) {
		this.searchParameter = searchParameter;
	}

	public JButton getSearch() {
		return search;
	}

	public void setSearch(JButton search) {
		this.search = search;
	}

	public JButton getClearSearch() {
		return clearSearch;
	}

	public void setClearSearch(JButton clearSearch) {
		this.clearSearch = clearSearch;
	}

	public ButtonGroup getGroup() {
		return group;
	}

	public void setGroup(ButtonGroup group) {
		this.group = group;
	}
	
	
}

