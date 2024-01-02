import org.junit.Assert;
import org.junit.Test;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

public class TestParkingLot {
    @Test
    public void TestParkingCheck(){
        List<List<String>> arr=new ArrayList<>();
        Driver driver=new Driver(1,"arvind","10:57:39","general");
        Driver driver1=new Driver(2,"aakash","10:59:39","general");
        arr.add(List.of("1","arvind","10:57:39","general"));
        arr.add(List.of("2","aakash","10:59:39","general"));
        Parkinglot_JDBC.insertParking_details(driver);
        Parkinglot_JDBC.insertParking_details(driver1);
        Assert.assertEquals(arr,Parkinglot_JDBC.Display());
    }
    //uc2 -unpark
    @Test
    public void TestUnPark(){
        Parkinglot_JDBC.Delete(1);
        List<List<String>> arr=new ArrayList<>();
        arr.add(List.of("2","aakash","10:59:39","general"));
        Assert.assertEquals(arr,Parkinglot_JDBC.Display());
    }
    //notify if parkinglot is full
    @Test
    public  void  TestParkingFull(){
        List<List<String>> arr=new ArrayList<>();
        ParkingLot parkingLot=new ParkingLot(1,"lot1",60,60,"1");
        arr.add(List.of("1","lot1","60","60"));
        int available=parkingLot.getCapacity()-arr.size();
        if(available ==0){
            Assert.assertEquals(0,Parkinglot_JDBC.Total_slots());
            System.out.println("the lot is full...");
        }
        else {
            Assert.assertEquals(available, Parkinglot_JDBC.Total_slots());
        }
    }
    //notify if parkinglot is full
    @Test
    public  void  TestRedirectToSecurity(){
        List<List<String>> arr=new ArrayList<>();
        ParkingLot parkingLot=new ParkingLot(1,"lot1",60,60,"1");
        arr.add(List.of("1","lot1","60","60"));
        int available=parkingLot.getCapacity()-arr.size();
        if(available ==0){
            Assert.assertEquals(0,Parkinglot_JDBC.Total_slots());
            Security security=new Security(1,"aaaa");
            String message= (String) security.update();
            Assert.assertEquals("Parking lot is full. Redirecting security staff.",message);
            System.out.println("the lot is full...");
        }
        else {
            Assert.assertEquals(available, Parkinglot_JDBC.Total_slots());
        }
    }
    //uc5-Availablity of slots
    @Test
    public void TestAvailablity_Parking_Lot(){
        List<List<String>> arr=new ArrayList<>();
        arr.add(List.of("2","aakash","10:59:39","general"));
        ParkingLot parkingLot=new ParkingLot(1,"lot1",60,60,"1");
        arr.add(List.of("1","lot1","60","60"));
        int available=parkingLot.getCapacity()-arr.size()+1;
        if(available ==0){
            Assert.assertEquals(0,Parkinglot_JDBC.Total_slots());
            Security security=new Security(1,"aaaa");
            String message= (String) security.update();
            Assert.assertEquals("Parking lot is full. Redirecting security staff.",message);
            System.out.println("the lot is full...");
        }
        else {
            Assert.assertEquals(available, Parkinglot_JDBC.Total_slots());
            System.out.println("Available slots:"+Parkinglot_JDBC.Total_slots());
        }

    }
    //uc6-parking attendent
    @Test
    public  void TestCarParked(){
        Assert.assertEquals("parked successfully",Parkinglot_JDBC.parkcar("tn1234","1","1","BMW",1));
    }
    //uc7-find my car
    @Test
    public void TestToFindTheParkedCar(){
        Assert.assertEquals(1,Parkinglot_JDBC.FindParkedCar("tn1234"));
    }
    //uc8-intime
    @Test
    public void TestInTimeOfParkedCar(){
        Assert.assertEquals("07:18:58",Parkinglot_JDBC.FindwhenCarparked("tn1234"));
    }
    //uc9-lots distribution

    @Test
    public void TestLotsDistribution(){
        ParkingLot parkingLot=new ParkingLot(1,"lot1",60,60,"1");
        ParkingLot parkingLot1=new ParkingLot(2,"lot2",30,30,"2");
        ParkingLot parkingLot2=new ParkingLot(3,"lot3",50,50,"3");
        Parkinglot_JDBC.lotDistributionTable(parkingLot);
        Parkinglot_JDBC.lotDistributionTable(parkingLot1);
        Parkinglot_JDBC.lotDistributionTable(parkingLot2);
        List<List<String>> arr=new ArrayList<>();
        arr.add( List.of("1","lot1","60","60","1"));
        arr.add( List.of("2","lot2","30","30","2"));
        arr.add( List.of("3","lot3","50","50","3"));
        Assert.assertEquals(arr,Parkinglot_JDBC.DisplayParkinglot_details());
    }
    //uc-10 parking handicap vechile
    @Test
    public void TestIfHandicapVechileParkedAtNearest(){
        Driver driver=new Driver(3,"sanjay","10:57:34","handicap");
        parking_car parkingCar=new parking_car("tn4567","11:11:11",5,"BMW","1");
        Assert.assertEquals("the car is parked to nearest space", Parkinglot_JDBC.handicap(driver,parkingCar));

    }
    //uc 11 the highest number of free space
    @Test
    public  void TestFindHighestNoFreeSpace(){
        Assert.assertEquals(1,Parkinglot_JDBC.FindHighestFreeSpace());
    }
    //uc 12 location of all parked white cars
    @Test
    public void TestLocationOfParkedWhiteCars() throws SQLException {
        Set<Integer> set=new HashSet<>();
        set.add(1);
        set.add(2);
        Assert.assertEquals(set,Parkinglot_JDBC.FindLocationOfWhiteCars("white"));
    }
    //uc13 location,plate no of parked  blue toyota
    @Test
    public void TestInvestgateARobberyCase(){
        List<List<String>> arr=new ArrayList<>();
        List<String> arr1=new ArrayList<>();
        arr.add(List.of("1","tn1454","1"));
        Assert.assertEquals(arr,Parkinglot_JDBC.FindBluToyoto("blue","toyoto"));
    }
    //uc14 location, of BMW to increase security
    @Test
    public void TestToImproveSecurity(){
        List<List<String>> arr=new ArrayList<>();
        List<String> arr1=new ArrayList<>();
        arr.add(List.of("tn1234","11:11:11","1","BMW","white","handicap","1"));
        arr.add(List.of("tn098","12:12:12","2","BMW","white","handicap","1"));
        Assert.assertEquals(arr,Parkinglot_JDBC.FindBMW("BMW"));
    }
    //uc15
    @Test
    public void TestParkedCarInTime(){
        List<List<String>> arr=new ArrayList<>();
        List<String> arr1=new ArrayList<>();
        arr.add(List.of("tn3778","12:55:21","6","Audi","black","general","1"));
        arr.add(List.of("tn3770","12:55:27","7","audi","black","general","2"));
        arr.add(List.of("tn3771","12:55:27","8","audi","blue","general","2"));
        Assert.assertEquals(arr,Parkinglot_JDBC.FindCarInTimeInterval());

    }
    //uc16 parked handicapcars
    @Test
    public void TestParkedhandicapCars(){
        List<List<String>> arr=new ArrayList<>();
        List<String> arr1=new ArrayList<>();
        arr.add(List.of("tn1234","11:11:11","1","BMW","white","handicap","1"));
        arr.add(List.of("tn098","12:12:12","2","BMW","white","handicap","1"));
        Assert.assertEquals(arr,Parkinglot_JDBC.FindHandicap(1,"handicap"));
    }
    //uc17 all cars a parking lot
    @Test
    public void TestAllParkedCarsInALot(){
        List<List<String>> arr=new ArrayList<>();
        List<String> arr1=new ArrayList<>();
        arr.add(List.of("tn3778", "20:37:15", "5", "toyoto", "white", "general", "2"));
        arr.add(List.of("tn3770","12:55:27","7","audi","black","general","2"));
        arr.add(List.of("tn3771","12:55:27","8","audi","blue","general","2"));
        Assert.assertEquals(arr,Parkinglot_JDBC.FindAllCarInALot(2));
    }
}
