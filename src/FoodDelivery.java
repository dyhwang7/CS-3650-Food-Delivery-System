import java.sql.*;
import java.util.*;
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
			//customer.createCustomer(c, "123 South St", "123-456-7890", "dy", "dy@mail.net", "123abc");
			boolean verified = customer.customerAuthentication(c, "r5dk9fr5tpp@mail.net", "56789DEF");
			System.out.println(customer.getCustomerID() + " " + customer.getDeliveryAddress() + " " + customer.getPhoneNum() + " " + customer.getCustomerName() + " " + customer.getCustomerEmail() + customer.getCustomerPassword());
			System.out.println(verified);
			Driver d = new Driver ();
			d.getDriver(c);
			System.out.println(d.getDriverID() + " " + d.getLicenseNumber() + " " + d.getLicensePlate() + " " + d.getCarModel());
		Menu m = new Menu();
		ArrayList <Item> i = new ArrayList<Item>(); 
		i = m.getMenuItem(c,4);
		for (Item j : i)
		{
			System.out.println(j.getItemID());
		}
		ShoppingCart s = new ShoppingCart();
		//s.addToCart(c, 1, 3);
		ArrayList <Item> i2 = new ArrayList<Item>();
		i2 = s.getCartItems(c, 3);
		for (Item j : i2)
		{
			System.out.println(j.getItemPrice());
		}
		//s.removeFromCart(c, 1, 3);
		System.out.println("Total = " + s.getCartTotal(c, 3));
		s.createCart(c, 3);
		//s.emptyCart(c, 1);
		System.out.println(s.getRestaurantID(c));
		
		Order o = new Order();
		o.createOrder(c, s);
		System.out.println("ORDER RESULT: " + o.getOrderID() + " " + o.getCustomerID() + " " + o.getRestaurantID() + " " + o.getOrderConfirmation() + " " + o.getTipAmount());
		
		CreditCard cc = new CreditCard();
		cc.addCreditCard(c, 7, "12345", "04/23", "dy", 123, "123 s st", "pomona", "CA", 90650);
		System.out.println("CARD ID CHECK: " + cc.existsCardID(c, "5266264206465619"));
		System.out.println("CARD AUTHENTICTAION: " + cc.creditCardAuthentication(c, 2, "5266264206465619", 294));
		
		
		ac.endConnection();
		
		
 
	}
	

}
