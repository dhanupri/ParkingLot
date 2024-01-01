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
    //insert into parking lot owner
    public static void insertParkinglotOwner(ParkingLot_Owner parkingLotOwner){
        Connection connection=null;
        try {
            connection = Sql_connection.getCon();
            PreparedStatement ps = connection.prepareStatement("INSERT INTO  ParkingLotOwner VALUES(?,?)");
            ps.setInt(1,parkingLotOwner.getOwnerId());
            ps.setString(2,parkingLotOwner.getOwnerName());
            ps.executeUpdate();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }
    //notify if parkinglotfull or not

    public static int Total_slots(){
        Connection connection=null;
        ParkingLot parkingLot=new ParkingLot(1,"lot1",60,60);
        try{
            connection=Sql_connection.getCon();
            PreparedStatement ps=connection.prepareStatement("SELECT COUNT(*) AS row_count FROM Driver1 ");
            ResultSet resultSet=ps.executeQuery();
            int rowcount = 0;
            if(resultSet.next()) {
                rowcount = resultSet.getInt("row_count");
                System.out.println("the total number of slots filled" + rowcount);
            }
            parkingLot.setAvailablespace(parkingLot.getCapacity()-rowcount);
            if (parkingLot.getAvailablespace()==0){
                System.out.println("The parkings are full");
            }
            else {
                System.out.println("The number of slots availabe:"+parkingLot.getAvailablespace());
            }
            return parkingLot.getAvailablespace();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    //Attendent to park the car

    public static String parkcar(String carID, String attendentID, String lotId){
        Connection connection=null;
        try{
            connection=Sql_connection.getCon();
            PreparedStatement ps= connection.prepareStatement("select lotId=? from ParkingLot where attendantID =?");
            ps.setString(1,"1");
            ps.setString(2,"1");
            ResultSet resultSet=ps.executeQuery();
            if(resultSet.next()){
                String updateQuery = "UPDATE ParkingLot SET attendantID = ?, availableSpace = availableSpace - 1 WHERE lotID = ?";
                ps.setString(1,"1");
                ps.setString(2,"1");
                try(PreparedStatement updatestatement=connection.prepareStatement(updateQuery)){
                    updatestatement.setString(1, attendentID);
                    updatestatement.setString(2, "1");
                    updatestatement.executeUpdate();

                }
                String insertQuery="insert into ParkingCars(carID, lotID, inTime)VALUES (?, ?, CURRENT_TIMESTAMP)";
                try (PreparedStatement insertStatement = connection.prepareStatement(insertQuery)) {
                    insertStatement.setString(1, carID);
                    insertStatement.setString(2, "1");
                    insertStatement.executeUpdate();
                }

                System.out.println("parked successfully");
                return "parked successfully";
            }
            else{
                System.out.println("No available parking lot");
                return  "No available parking lot";
            }

        } catch (SQLException e) {
            throw new RuntimeException(e);
        }

    }
    //find the car
    public  static int FindParkedCar(String car_no){
        Connection connection=null;
        try{
            connection=Sql_connection.getCon();
            PreparedStatement ps= connection.prepareStatement("select lotID from ParkingCars where carId=?");
            ps.setString(1,car_no);
            ResultSet resultSet=ps.executeQuery();
            if(resultSet.next()){
                int lotid=resultSet.getInt("lotId");
                System.out.println("the car no:"+car_no+"is at parking lot:"+lotid);
                return lotid;
            }

        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        return 0;

    }

}
