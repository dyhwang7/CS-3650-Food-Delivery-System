import java.sql.*;
public class FoodDelivery {

	public static void main(String[] args) {
		AccessConnection ac = new AccessConnection();
		Connection c = null;
		c = ac.getCurrentConnection();
			//Insert 
//			String sql = "INSERT INTO Driver (driverID, licenseNumber, licencePlate, carModel, amountEarned) VALUES(?, ?, ?, ?, ?)";
//			PreparedStatement statement = connection.prepareStatement(sql);
//			statement.setString(1, "4");
//			statement.setString(2, "12345");
//			statement.setString(3, "123456");
//			statement.setString(4, "Hyundai Genesis");
//			statement.setString(5, "0.00");
//			int rows = statement.executeUpdate();
//			if (rows > 0) {
//				System.out.println("A new contact has been inserted.");
//			}
			
			Restaurant r = new Restaurant();
			r.searchRestaurant(c, "%Jessie%");
			r.showAllRestaurants(c);
			
			Customer customer = new Customer();
			customer.createCustomer(c, "123 South St", "123-456-7890", "dy", "dy@mail.net", "123abc");
			boolean verified = customer.customerAuthentication(c, 3, "56789DEF");
			System.out.println(verified);
			Driver d = new Driver ();
			d.printDriver(c);
		ac.endConnection();

		
		
 
	}
	

}
