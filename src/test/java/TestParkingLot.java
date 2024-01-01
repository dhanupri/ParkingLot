import org.junit.Assert;
import org.junit.Test;

import java.util.ArrayList;
import java.util.List;

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
        ParkingLot parkingLot=new ParkingLot(1,"lot1",60,60);
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
        ParkingLot parkingLot=new ParkingLot(1,"lot1",60,60);
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
        ParkingLot parkingLot=new ParkingLot(1,"lot1",60,60);
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
        Assert.assertEquals("parked successfully",Parkinglot_JDBC.parkcar("tn1234","1","1"));
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


}
