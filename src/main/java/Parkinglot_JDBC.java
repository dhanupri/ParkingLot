import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class Parkinglot_JDBC {
    public static void insertParking_details(Driver driver){
        Connection connection=null;
        ArrayList<Driver> arr1=new ArrayList<>();
        try {
            connection = Sql_connection.getCon();
            PreparedStatement ps = connection.prepareStatement("INSERT INTO  Driver1 VALUES(?,?,?,?)");
            ps.setInt(1,driver.getDriverId());
            ps.setString(2,driver.getName());
            ps.setString(3,driver.getDriverType());
            ps.setString(4,driver.getInTime());
            ps.executeUpdate();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }
    public static List<List<String>> Display(){
        List<List<String>> arr2=new ArrayList<>();

        Connection connection=null;
        try {
            connection = Sql_connection.getCon();
            Statement ps1 = connection.createStatement();
            ResultSet resultSet = ps1.executeQuery("select * from Driver1");
            while (resultSet.next()) {
                ArrayList<String> arr1=new ArrayList<>();
                int driverID = resultSet.getInt("driverID");
                String name = resultSet.getString("name");
                String type = resultSet.getString("driverType");
                String inTime = resultSet.getString("inTime");
                arr1.add(String.valueOf(driverID));
                arr1.add(name);
                arr1.add(inTime);
                arr1.add(type);
                arr2.add(arr1);
            }
            return arr2;
        }catch (SQLException e){
            e.printStackTrace();
        }

        return null;
    }
    //delete the detail using a slot number
    public static void Delete(int diverId) {
        Connection connection = null;
        try {
            connection = Sql_connection.getCon();
            PreparedStatement ps = connection.prepareStatement("delete from Driver1 where DriverId=?");
            ps.setInt(1, diverId);
            ps.executeUpdate();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }
}
