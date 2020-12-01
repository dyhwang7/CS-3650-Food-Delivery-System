import java.sql.*;
import java.util.ArrayList;
public class ShoppingCart {
	int cartID;
	int customerID;
	
	ShoppingCart()
	{
		cartID=0;
		customerID=0;
	}
	
	ShoppingCart(int caID, int cuID)
	{
		cartID = caID;
		customerID = cuID;
	}
	public ShoppingCart createCart(Connection c, int customerID)
	{
		ShoppingCart cart = new ShoppingCart();
		PreparedStatement statement;
		try {
			String sql = "SELECT * FROM shoppingCart WHERE customerID = ?";
			statement = c.prepareStatement(sql);
			statement.setInt(1, customerID);
			ResultSet result = statement.executeQuery(); 			
			if (result.next()) 
			{
				cart.setCartID(result.getInt("cartID"));
				System.out.println("CART ID = " + cart.cartID);
				cart.setCustomerID(customerID);
			}
			else
			{
				sql = "INSERT INTO shoppingCart (customerID) VALUES (?)";
				statement = c.prepareStatement(sql);
				statement.setInt(1, customerID);
				statement.executeUpdate();
				sql = "SELECT * FROM shoppingCart WHERE customerID = ?";
				statement = c.prepareStatement(sql);
				statement.setInt(1, customerID);
				result = statement.executeQuery(); 			
				if (result.next()) 
				{
					cart.setCartID(result.getInt("cartID"));
					System.out.println("CART2 ID = " + cart.cartID);
					cart.setCustomerID(customerID);
				}				
				
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return cart;
	}
	public void emptyCart(Connection c, int cartID)
	{
		try {
			String sql = "DELETE FROM cartItem WHERE cartID = ?";				
			PreparedStatement statement;			
			statement = c.prepareStatement(sql);
			statement.setInt(1, cartID);
			statement.executeUpdate();			
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
			
	}	
	public void addToCart(Connection c, int cartID, int itemID)
	{
		PreparedStatement statement;
		try {
			String sql = "INSERT INTO cartItem (cartID, itemID) VALUES(?, ?)";
			statement = c.prepareStatement(sql);
			statement.setInt(1, cartID);
			statement.setInt(2, itemID);
			statement.executeUpdate();			
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}	
	}
	public void removeFromCart(Connection c, int cartID, int itemID)
	{
		int rows = 0, cartItemID = 0;
		try {
			String sql = "SELECT MAX(cartItemID) FROM cartItem WHERE cartID = ? AND itemID = ?";		
			PreparedStatement s = c.prepareStatement(sql);
			s.setInt(1, cartID);
			s.setInt(2, itemID);
			ResultSet rs = s.executeQuery();
			while (rs.next())
			{
				cartItemID = rs.getInt("C1");
			}
			System.out.println("CART ITEM ID " + cartItemID);
			sql = "DELETE FROM cartItem WHERE cartItemID = ?";				
			PreparedStatement statement;			
			statement = c.prepareStatement(sql);
			statement.setInt(1, cartItemID);
			rows = statement.executeUpdate();			
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		if (rows > 0) {
			System.out.println("A shoppingcart item has been inserted.");
		}				
	}
	public ArrayList<Item> getCartItems(Connection c, int customerID)
	{
		ArrayList<Item> cartItems = new ArrayList<Item>();
		String sql = "SELECT * FROM Item I, shoppingCart S, cartItem C WHERE S.customerID like ? "
				+ "AND S.cartID = C.cartID AND C.itemID = I.itemID";
		PreparedStatement statement = null;
		try {
			statement = c.prepareStatement(sql);
			statement.setInt(1,customerID);			
			ResultSet result = statement.executeQuery(); 
			while (result.next()) {
				cartItems.add(new Item(result.getInt("itemID"),result.getString("itemDescription"),
						result.getDouble("itemPrice"), result.getString("itemName")));
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return cartItems;		
	}
	public double getCartTotal(Connection c, int customerID)
	{	
		double total = 0;
		String sql = "SELECT * FROM Item I, shoppingCart S, cartItem C WHERE S.customerID like ? "
				+ "AND S.cartID = C.cartID AND C.itemID = I.itemID";
		PreparedStatement statement = null;
		try {
			statement = c.prepareStatement(sql);
			statement.setInt(1,customerID);			
			ResultSet result = statement.executeQuery(); 
			while (result.next()) {
				total += result.getDouble("itemPrice");
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return total;
	}
//	public int getRestaurantID(Connection c)
//	{
//		int restaurantID = 0;
//		String sql = "SELECT restaurantID FROM Item I, shoppingCart S, cartItem C WHERE S.customerID like ? "
//				+ "AND S.cartID = C.cartID AND C.itemID = I.itemID";
//		PreparedStatement statement = null;
//		try {
//			statement = c.prepareStatement(sql);
//			statement.setInt(1,customerID);			
//			ResultSet result = statement.executeQuery(); 
//			while (result.next()) {
//				total += result.getDouble("itemPrice");
//			}
//		} catch (SQLException e) {
//			// TODO Auto-generated catch block
//			e.printStackTrace();
//		}
//		return restaurantID;		
//	}
	public int getCartID() {
		return cartID;
	}

	public void setCartID(int cartID) {
		this.cartID = cartID;
	}

	public int getCustomerID() {
		return customerID;
	}

	public void setCustomerID(int customerID) {
		this.customerID = customerID;
	}
}
