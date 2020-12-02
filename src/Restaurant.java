import java.sql.*;
import java.util.*;
public class Restaurant {
	private int restaurantID;
	private String restaurantName;
	private String restaurantAddress;
	private String restaurantNum;
	private boolean operational;
	
	Restaurant(){
		restaurantID = 0;
		restaurantName = "";
		restaurantAddress = "";
		restaurantNum = "";
		operational = false; 
	}
	
	Restaurant(int rID, String rName, String rAddress, String rNum, boolean o)
	{
		restaurantID = rID;
		restaurantName = rName;
		restaurantAddress = rAddress;
		restaurantNum = rNum;
		operational = o;
	}
	
	public void getRestaurant(Connection c, int rID)
	{
		String sql = "SELECT * FROM Restaurant WHERE restaurantID = ?";
			
		PreparedStatement statement = null;
		try {
			statement = c.prepareStatement(sql);
			statement.setInt(1,rID);	
			ResultSet result = statement.executeQuery(); //this could be returned instead if we want to see all 
			while (result.next()) {
				this.restaurantID=result.getInt("restaurantID");
				this.restaurantName = result.getString("restaurantName");
				this.restaurantAddress = result.getString("restaurantAddress");
				this.restaurantNum = result.getString("restaurantNum");
				this.operational = changeToBool(result.getString("operational"));
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	public ArrayList<Restaurant> showAllRestaurants(Connection c) {
		ArrayList<Restaurant> showAll = new ArrayList<Restaurant>();
		String sql = "SELECT * FROM Restaurant";
		PreparedStatement statement = null;
		try {
			statement = c.prepareStatement(sql);			
			ResultSet result = statement.executeQuery(); //this could be returned instead if we want to see all 
			while (result.next()) {
				showAll.add(new Restaurant(result.getInt("restaurantID"),result.getString("restaurantName"),
						result.getString("restaurantAddress"), result.getString("restaurantNum"),changeToBool(result.getString("operational"))));
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return showAll; 
	}

	public ArrayList<Restaurant> searchRestaurant(Connection c, String s){
		ArrayList<Restaurant> searchResult = new ArrayList<Restaurant>();
		String sql = "SELECT * FROM Restaurant WHERE restaurantName like ?";
		
		PreparedStatement statement = null;
		try {
			statement = c.prepareStatement(sql);
			statement.setString(1,s);			
			ResultSet result = statement.executeQuery(); //this could be returned instead if we want to see all 
			while (result.next()) {
				searchResult.add(new Restaurant(result.getInt("restaurantID"),result.getString("restaurantName"),
						result.getString("restaurantAddress"),result.getString("restaurantNum"),changeToBool(result.getString("operational"))));
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return searchResult;
	}
	public static boolean changeToBool(String s)
	{
		boolean b;
		if (s == null)
		{
			b = false;
			return false;
		}
		else if (s.equals("Yes"))
			b = true;
		else
			b = false;
		return b;
	}

	public int getRestaurantID() {
		return restaurantID;
	}

	public void setRestaurantID(int restaurantID) {
		this.restaurantID = restaurantID;
	}

	public String getRestaurantName() {
		return restaurantName;
	}

	public void setRestaurantName(String restaurantName) {
		this.restaurantName = restaurantName;
	}

	public String getRestaurantAddress() {
		return restaurantAddress;
	}

	public void setRestaurantAddress(String restaurantAddress) {
		this.restaurantAddress = restaurantAddress;
	}

	public String getRestaurantNum() {
		return restaurantNum;
	}

	public void setRestaurantNum(String restaurantNum) {
		this.restaurantNum = restaurantNum;
	}

	public boolean isOperational() {
		return operational;
	}

	public void setOperational(boolean operational) {
		this.operational = operational;
	}
}
