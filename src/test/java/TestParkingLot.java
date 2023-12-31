import org.junit.Assert;
import org.junit.Test;

import java.util.ArrayList;
import java.util.List;

public class TestParkingLot {
    @Test
    public void TestParkingCheck(){
        List<List<String>> arr=new ArrayList<>();
        Driver driver=new Driver(1,"arvind","10:57:39","general");
        arr.add(List.of("1","arvind","10:57:39","general"));
        Parkinglot_JDBC.insertParking_details(driver);

        Assert.assertEquals(arr,Parkinglot_JDBC.Display());
    }
}
