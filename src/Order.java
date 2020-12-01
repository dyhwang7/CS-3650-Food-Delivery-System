import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class Order {
	private int orderID;
	private int customerID;
	private int restaurantID;
	private boolean orderConfirmation;
	private double tipAmount;
	
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
