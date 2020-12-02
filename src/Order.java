import java.sql.*;
import java.util.ArrayList;

public class Order {
	private int orderID;
	private int customerID;
	private int restaurantID;
	private boolean orderConfirmation;
	private double tipAmount;
	
	public Order ()
	{
		
	}
	public Order (int oID, int cID, int rID, boolean oc, double tip)
	{
		orderID = oID;
		customerID = cID;
		restaurantID = rID;
		orderConfirmation = oc;
		tipAmount = tip;
	}
	public void createOrder(Connection c, ShoppingCart s)
	{
		PreparedStatement statement;
		System.out.println("ORDER: " + s.getCustomerID() + " " + s.getRestaurantID(c));
		
		try {
				String sql = "INSERT INTO Order (customerID, RestaurantID) VALUES (?,?)";
				statement = c.prepareStatement(sql);
				statement.setInt(1, s.getCustomerID());
				statement.setInt(2, s.getRestaurantID(c));
				statement.executeUpdate();
				sql = "SELECT * FROM Order WHERE customerID = ?";
				statement = c.prepareStatement(sql);
				statement.setInt(1, s.getCustomerID());
				ResultSet result = statement.executeQuery(); 			
				while (result.next()) 
				{
					this.setOrderID(result.getInt("orderID"));
					this.setCustomerID(result.getInt("customerID"));
					this.setRestaurantID(result.getInt("restaurantID"));
					this.setOrderConfirmation(Restaurant.changeToBool(result.getString("orderConfirmation")));
					this.setTipAmount(result.getDouble("tipAmount"));					
				}				
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	public ArrayList<Order> getOrderHistory(Connection c, int customerID)
	{
		ArrayList<Order> orderHistory = new ArrayList<Order>();
		String sql = "SELECT * FROM Order WHERE customerID like = ? LIMIT 4";
		PreparedStatement statement = null;
		try {
			statement = c.prepareStatement(sql);
			statement.setInt(1,customerID);			
			ResultSet result = statement.executeQuery(); 
			while (result.next()) {
				orderHistory.add(new Order(result.getInt("orderID"),result.getInt("customerID"),result.getInt("restaurantID"),
						Restaurant.changeToBool(result.getString("orderConfirmation")), result.getDouble("tipAmount")));
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return orderHistory;	
	}
	public void updateOrderConfirmation(Connection c)
	{
		String sql = "UPDATE Order SET orderConfirmation = ? WHERE orderID = ?";
		try { 
			PreparedStatement ps = c.prepareStatement(sql);
			if (this.getOrderConfirmation())
				ps.setString(1, "Yes");
			else
				ps.setString(1, "No");
			ps.setInt(2, this.getOrderID());
			ps.executeUpdate();			
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}		
	}
	public void updateTipAmount(Connection c)
	{
		String sql = "UPDATE Order SET tipAmount = ? WHERE orderID = ?";
		try { 
			PreparedStatement ps = c.prepareStatement(sql);
				ps.setDouble(1, this.getTipAmount());
			ps.setInt(2, this.getOrderID());
			ps.executeUpdate();			
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}		
	}	
	
	public int getOrderID() {
		return orderID;
	}
	public void setOrderID(int orderID) {
		this.orderID = orderID;
	}
	public int getCustomerID() {
		return customerID;
	}
	public void setCustomerID(int customerID) {
		this.customerID = customerID;
	}
	public int getRestaurantID() {
		return restaurantID;
	}
	public void setRestaurantID(int restaurantID) {
		this.restaurantID = restaurantID;
	}
	public boolean getOrderConfirmation() {
		return orderConfirmation;
	}
	public void setOrderConfirmation(boolean orderConfirmation) {
		this.orderConfirmation = orderConfirmation;
	}
	public double getTipAmount() {
		return tipAmount;
	}
	public void setTipAmount(double tipAmount) {
		this.tipAmount = tipAmount;
	}

	
}
