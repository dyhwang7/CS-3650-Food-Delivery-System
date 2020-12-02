import java.sql.*;
import java.util.*;
public class Restaurant {
	private int restaurantID;
	private String restaurantName;
	private String restaurantAddress;
	private boolean operational;
	
	Restaurant(){
		restaurantID = 0;
		restaurantName = "";
		restaurantAddress = "";
		operational = false; 
	}
	
	Restaurant(int rID, String rName, String rAddress, boolean o)
	{
		restaurantID = rID;
		restaurantName = rName;
		restaurantAddress = rAddress;
		operational = o;
	}
	
	public Restaurant getRestaurant(Connection c, int rID)
	{
		String sql = "SELECT * FROM Restaurant WHERE restaurantID = ?";
		Restaurant r= new Restaurant();
		
		PreparedStatement statement = null;
		try {
			statement = c.prepareStatement(sql);
			statement.setInt(1,rID);	
			ResultSet result = statement.executeQuery(); //this could be returned instead if we want to see all 
			while (result.next()) {
				r.restaurantID=result.getInt("restaurantID");
				r.restaurantName = result.getString("restaurantName");
				r.restaurantAddress = result.getString("restaurantAddress");
				r.operational = changeToBool(result.getString("operational"));
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return r;	
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
						result.getString("restaurantAddress"), changeToBool(result.getString("operational"))));
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
						result.getString("restaurantAddress"),changeToBool(result.getString("operational"))));
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

	public boolean isOperational() {
		return operational;
	}

	public void setOperational(boolean operational) {
		this.operational = operational;
	}
}
