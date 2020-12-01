import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class Order {
	int orderID;
	int customerID;
	int restaurantID;
	boolean orderConfirmation;
	double tipAmount;
	
//	public Order createOrder(Connection c, ShoppingCart s)
//	{
//		Order o = new Order();
//		int restaurantID = s.getRestaurantID(c, s.getCustomerID());
//		PreparedStatement statement;
//		try {
//				String sql = "INSERT INTO Order (customerID, RestaurantID) VALUES (?,?)";
//				statement = c.prepareStatement(sql);
//				statement.setInt(1, customerID);
//				statement.setInt(2, s.);
//				statement.executeUpdate();
//				sql = "SELECT * FROM Order WHERE customerID = ?";
//				statement = c.prepareStatement(sql);
//				statement.setInt(1, customerID);
//				ResultSet result = statement.executeQuery(); 			
//				while (result.next()) 
//				{
//					o.setCustomerID(result.getInt("customerID"));
//					System.out.println("ORDER ID2 = " + o.customerID);
//					o.setRestaurantID(restaurantID);
//					o.setOrderConfirmation(Restaurant.changeToBool(result.getString("orderConfirmation")));
//					o.setTipAmount(result.getDouble("tipAmount"));					
//				}				
//				
//		} catch (SQLException e) {
//			// TODO Auto-generated catch block
//			e.printStackTrace();
//		}
//		return o;	
//	}
//	
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
	public boolean isOrderConfirmation() {
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
