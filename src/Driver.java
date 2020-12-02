import java.sql.*;
public class Driver {
	private int driverID;
	private String licenseNumber;
	private String licensePlate;
	private String carModel;
	public Driver()
	{
		
	}
	public Driver(int driverID, String licenseNumber, String licensePlate, String carModel) {
		this.driverID = driverID;
		this.licenseNumber = licenseNumber;
		this.licensePlate = licensePlate;
		this.carModel = carModel;
	}
	public void getDriver(Connection c)
	{
		int count = 0;
		try {
			String sql = "SELECT * FROM Driver";		
			Statement s = c.createStatement(ResultSet.TYPE_SCROLL_INSENSITIVE, ResultSet.CONCUR_READ_ONLY);
			ResultSet rs = s.executeQuery(sql);
			rs.last();
			count = rs.getRow();
			System.out.println("Count is: " + count);

		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		int randDriver = (int)(Math.random()*(count)+1);
		System.out.println(randDriver);
		try {
			String sql = "SELECT * FROM Driver WHERE driverID = ?";
			PreparedStatement statement = null;			
			statement = c.prepareStatement(sql);
			statement.setInt(1,randDriver);			
			ResultSet result = statement.executeQuery(); //this could be returned instead if we want to see all 
			while (result.next()) {
				System.out.println(result.getInt("driverID"));
				this.setDriverID(result.getInt("driverID"));
				this.setLicenseNumber(result.getString("licenseNumber"));
				this.setLicensePlate(result.getString("licensePlate"));
				this.setCarModel(result.getString("carModel"));

			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	public int getDriverID() {
		return driverID;
	}
	public void setDriverID(int driverID) {
		this.driverID = driverID;
	}
	public String getLicenseNumber() {
		return licenseNumber;
	}
	public void setLicenseNumber(String licenseNumber) {
		this.licenseNumber = licenseNumber;
	}
	public String getLicensePlate() {
		return licensePlate;
	}
	public void setLicensePlate(String licensePlate) {
		this.licensePlate = licensePlate;
	}
	public String getCarModel() {
		return carModel;
	}
	public void setCarModel(String carModel) {
		this.carModel = carModel;
	}
}
