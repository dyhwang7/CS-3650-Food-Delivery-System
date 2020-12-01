import java.sql.*;
import java.util.ArrayList;
public class Menu {
	public ArrayList<Item> getMenuItem(Connection c, int restaurantID)
	{
		ArrayList<Item> menuItems = new ArrayList<Item>();
		String sql = "SELECT * FROM Item I, Menu M, menuItem MI  WHERE M.restaurantID like ? "
				+ "AND M.menuID = MI.menuID AND MI.itemID = I.itemID";
		PreparedStatement statement = null;
		try {
			statement = c.prepareStatement(sql);
			statement.setInt(1,restaurantID);			
			ResultSet result = statement.executeQuery(); 
			while (result.next()) {
				menuItems.add(new Item(result.getInt("itemID"),result.getString("itemDescription"),
						result.getDouble("itemPrice"), result.getString("itemName")));
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return menuItems;		
	}
}
