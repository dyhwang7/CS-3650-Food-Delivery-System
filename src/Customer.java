import java.sql.*;
public class Customer {
	String deliveryAddress;
	String phoneNum;
	String customerName;
	String customerEmail;
	String customerPassword;	
	
	Customer ()
	{
		
	}
	Customer (String d, String p, String n, String e, String pw)
	{
		deliveryAddress = d;
		phoneNum = p;
		customerName = n;
		customerEmail = e;
		customerPassword = pw;
	}

	//maybe a problem with customer being a foreign key
	public void createCustomer(Connection c, String deliveryAddress, String phoneNum,
								String customerName, String customerEmail, String customerPassword)
	{
		String sql = "INSERT INTO Customer  (deliveryAddress, phoneNum, customerName, customerEmail, customerPassword) VALUES (?, ?, ?, ?, ?)";
		try (PreparedStatement ps = c.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS)) {
			
			ps.setString(1, deliveryAddress);
			ps.setString(2, phoneNum);
			ps.setString(3, customerName);
			ps.setString(4, customerEmail);
			ps.setString(5, customerPassword);
			ps.execute();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	public boolean customerAuthentication(Connection c, String email, String pw)
	{
		String sql = "SELECT * FROM Customer WHERE customerEmail= ?";
		String customerPassword = "";
		PreparedStatement statement = null;
		try {
			statement = c.prepareStatement(sql);
			statement.setString(1,email);			
			ResultSet result = statement.executeQuery(); //this could be returned instead if we want to see all
			while (result.next()) {
				customerPassword = result.getString("customerPassword");
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}		
		if (customerPassword.equals(pw))
		{
			try {
				statement = c.prepareStatement(sql);
				statement.setString(1,email);			
				ResultSet result = statement.executeQuery(); //this could be returned instead if we want to see all
				while (result.next()) {
					setDeliveryAddress(result.getString("deliveryAddress"));
					setPhoneNum(result.getString("phoneNum"));
					setCustomerName(result.getString("customerName"));
					setCustomerEmail(result.getString("customerEmail"));
					setCustomerPassword(result.getString("customerPassword"));
				}
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}		
			return true;
		}
		else
			return false;	
	}
	
	public int availableCustomerID(Connection c)
	{
		int nextAvailableID = 0;
		String sql = "SELECT MAX(customerID) FROM Customer WHERE customerName IS NOT NULL"; //WHERE optional
		PreparedStatement statement = null;
		try {
			statement = c.prepareStatement(sql);			
			ResultSet result = statement.executeQuery(); //this could be returned instead if we want to see all 
				nextAvailableID = result.getInt("customerID");
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return nextAvailableID;
	}

	public String getDeliveryAddress() {
		return deliveryAddress;
	}

	public void setDeliveryAddress(String deliveryAddress) {
		this.deliveryAddress = deliveryAddress;
	}

	public String getPhoneNum() {
		return phoneNum;
	}

	public void setPhoneNum(String phoneNum) {
		this.phoneNum = phoneNum;
	}

	public String getCustomerName() {
		return customerName;
	}

	public void setCustomerName(String customerName) {
		this.customerName = customerName;
	}

	public String getCustomerEmail() {
		return customerEmail;
	}

	public void setCustomerEmail(String customerEmail) {
		this.customerEmail = customerEmail;
	}

	public String getCustomerPassword() {
		return customerPassword;
	}

	public void setCustomerPassword(String customerPassword) {
		this.customerPassword = customerPassword;
	}
}
