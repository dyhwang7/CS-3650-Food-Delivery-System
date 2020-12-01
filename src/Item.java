
public class Item {
	int itemID;
	String itemDescription;
	double itemPrice;
	String itemName;
	
	Item (int iID, String iDescription, double iPrice, String iName)
	{
		itemID = iID;
		itemDescription = iDescription;
		itemPrice = iPrice;
		itemName = iName;
	}

	public int getItemID() {
		return itemID;
	}

	public void setItemID(int itemID) {
		this.itemID = itemID;
	}

	public String getItemDescription() {
		return itemDescription;
	}

	public void setItemDescription(String itemDescription) {
		this.itemDescription = itemDescription;
	}

	public double getItemPrice() {
		return itemPrice;
	}

	public void setItemPrice(double itemPrice) {
		this.itemPrice = itemPrice;
	}

	public String getItemName() {
		return itemName;
	}

	public void setItemName(String itemName) {
		this.itemName = itemName;
	}

}
