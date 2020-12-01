import java.sql.*;
public class CreditCard {
	private int paymentID;
	private int customerID;
	private String cardID;
	private String expirationDate;
	private String cardholderName;
	private int cvvNum;
	private String address;
	private String city;
	private String state;
	private int zip;
	
	public void addCreditCard(Connection c, int customerID, String cardID, String expirationDate, String cardholderName, int cvvNum,
			String address, String city, String state, int zip)
	{
		if (!existsCardID(c, cardID))
		{
			String sql = "INSERT INTO creditCard  (customerID, cardID, expirationDate, cardholderName, cvvNum, address, city, state, zip) "
				+ "VALUES (?, ?, ?, ?, ?, ?, ?, ?, ?)";
			try (PreparedStatement ps = c.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS)) {
				ps.setInt(1, customerID);
				ps.setString(2, cardID);
				ps.setString(3, expirationDate);
				ps.setString(4, cardholderName);
				ps.setInt(5, cvvNum);
				ps.setString(6, address);
				ps.setString(7, city);
				ps.setString(8, state);
				ps.setInt(9, zip);
				ps.execute();
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
	}
	public boolean existsCardID(Connection c, String cardID)
	{
		boolean cardCheck = false;
		try {
			String sql = "SELECT cardID FROM creditCard WHERE cardID = ?";
			PreparedStatement statement = null;			
			statement = c.prepareStatement(sql);
			statement.setString(1,cardID);			
			ResultSet result = statement.executeQuery(); //this could be returned instead if we want to see all 
			if (result.next()) {
				cardCheck = true;
			}
			else
				cardCheck = false;
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}	
		return cardCheck;
	}
	public boolean creditCardAuthentication(Connection c, int customerID, String cardID, int cvvNum)
	{
		String sql = "SELECT * FROM creditCard WHERE customerID= ?";
		String cardIDCheck = "";
		int cvvNumCheck = 0;
		PreparedStatement statement = null;
		try {
			statement = c.prepareStatement(sql);
			statement.setInt(1,customerID);			
			ResultSet result = statement.executeQuery(); 
			while (result.next()) {
				cardIDCheck = result.getString("cardID");
				cvvNumCheck = result.getInt("cvvNum");
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}		
		if (cardID.equals(cardIDCheck) && cvvNum == cvvNumCheck)
		{
			return true;
		}
		else
			return false;			
	}
	public int getPaymentID() {
		return paymentID;
	}
	public void setPaymentID(int paymentID) {
		this.paymentID = paymentID;
	}
	public String getCardID() {
		return cardID;
	}
	public void setCardID(String cardID) {
		this.cardID = cardID;
	}
	public String getExpirationDate() {
		return expirationDate;
	}
	public void setExpirationDate(String expirationDate) {
		this.expirationDate = expirationDate;
	}
	public String getCardholderName() {
		return cardholderName;
	}
	public void setCardholderName(String cardholderName) {
		this.cardholderName = cardholderName;
	}
	public int getCvvNum() {
		return cvvNum;
	}
	public void setCvvNum(int cvvNum) {
		this.cvvNum = cvvNum;
	}
	public String getAddress() {
		return address;
	}
	public void setAddress(String address) {
		this.address = address;
	}
	public String getCity() {
		return city;
	}
	public void setCity(String city) {
		this.city = city;
	}
	public String getState() {
		return state;
	}
	public void setState(String state) {
		this.state = state;
	}
	public int getZip() {
		return zip;
	}
	public void setZip(int zip) {
		this.zip = zip;
	}
	
	
}
