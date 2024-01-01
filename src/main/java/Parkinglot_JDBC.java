import java.sql.*;
import java.util.ArrayList;
import java.util.List;

import static com.mysql.cj.x.protobuf.MysqlxCrud.Order.Direction.DESC;

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
        ParkingLot parkingLot=new ParkingLot(1,"lot1",60,60,"1");
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

    public static String parkcar(String carID, String attendentID, String lotId,String carType,int slot){
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
                String insertQuery="insert into ParkingCars(carID, lotID, slot,cartype,inTime)VALUES (?, ?,?,?, CURRENT_TIMESTAMP)";
                try (PreparedStatement insertStatement = connection.prepareStatement(insertQuery)) {
                    insertStatement.setString(1, carID);
                    insertStatement.setString(2, lotId);
                    insertStatement.setInt(3,slot);
                    insertStatement.setString(4,carType);
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
    //find the car
    public  static String FindwhenCarparked(String car_no){
        Connection connection=null;
        try{
            connection=Sql_connection.getCon();
            PreparedStatement ps= connection.prepareStatement("select inTime from ParkingCars where carId=?");
            ps.setString(1,car_no);
            ResultSet resultSet=ps.executeQuery();
            if(resultSet.next()){
                Time inTime=resultSet.getTime("inTime");
                return String.valueOf(inTime);
            }

        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        return null;

    }

    //insert in lot distribution table
    public static void lotDistributionTable(ParkingLot parkingLot){
        Connection connection=null;
        try {
            connection = Sql_connection.getCon();
            PreparedStatement ps = connection.prepareStatement("INSERT INTO  ParkingLot VALUES(?,?,?,?,?)");
            ps.setInt(1,parkingLot.getLotId());
            ps.setString(2,parkingLot.getName1());
            ps.setInt(3,parkingLot.getCapacity());
            ps.setInt(4,parkingLot.getAvailablespace());
            ps.setString(5,parkingLot.getAttendentId());
            ps.executeUpdate();

        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }
    public static List<List<String>> DisplayParkinglot_details() {
        List<List<String>> arr2 = new ArrayList<>();

        Connection connection = null;
        try {
            connection = Sql_connection.getCon();
            Statement ps1 = connection.createStatement();
            ResultSet resultSet = ps1.executeQuery("select * from ParkingLot ");
            while (resultSet.next()) {
                ArrayList<String> arr1 = new ArrayList<>();
                int lot= resultSet.getInt("lotID");
                String name=resultSet.getString("name1");
                String capacity=resultSet.getString("capacity");
                int availableSpace = resultSet.getInt("availableSpace");
                String attendentId= resultSet.getString("attendantID");
                arr1.add(String.valueOf(lot));
                arr1.add(name);
                arr1.add(String.valueOf(capacity));
                arr1.add(String.valueOf(availableSpace));
                arr1.add(attendentId);
                arr2.add(arr1);
            }
            return arr2;
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return arr2;
    }
    //driver is a handicap
    public static String handicap(Driver driver,parking_car parkingCar){
        Connection connection=null;
        try {
            connection=Sql_connection.getCon();
            int n=1;
            if(driver.getDriverType().toLowerCase().equals("handicap")){
                Statement ps = connection.createStatement();
                ResultSet resultSet = ps.executeQuery("select * from ParkingCars order by slot ");
                int slot=0;
                while (resultSet.next()){
                    if(resultSet.getInt("slot")!=n){
                        n++;
                    }
                    else {
                        parkingCar.setSlot(n);
                        slot=n;
                        break;
                    }
                }
                PreparedStatement ps1= connection.prepareStatement(" insert into ParkingCars values(?,?,?,?,?)");
                ps1.setString(1,parkingCar.getCarId());
                ps1.setString(2,String.valueOf(parkingCar.getInTime()));
                ps1.setInt(3,parkingCar.getSlot());
                ps1.setString(4,parkingCar.getCarmodel());
                ps1.setString(5,parkingCar.getLotID());
               ps1.executeUpdate();
               return "the car is parked to nearest space";

            }

        } catch (Exception e) {
            throw new RuntimeException(e);
        }
        return null;
    }
    //find highest number of free spaces
    public static int FindHighestFreeSpace(){
        Connection connection=null;
        try{
            connection=Sql_connection.getCon();
            PreparedStatement ps= connection.prepareStatement("select * from ParkingLot order by availableSpace");
            ResultSet resultSet=ps.executeQuery();
            int n=0;
            int id=0;
            while (resultSet.next()){
                n=resultSet.getInt("availableSpace");
                id=resultSet.getInt("lotID");
            }
            System.out.println(id);
            return id;
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

}
