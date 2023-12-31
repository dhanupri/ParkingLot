import java.sql.Time;

public class Driver {
    public int driverId;
    public String name;
    public String inTime;
    public String driverType;

    public Driver(int driverId, String name, String inTime, String driverType) {
        this.driverId = driverId;
        this.name = name;
        this.inTime = inTime;
        this.driverType = driverType;
    }

    public int getDriverId() {
        return driverId;
    }

    public void setDriverId(int driverId) {
        this.driverId = driverId;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getInTime() {
        return inTime;
    }

    public void setInTime(String inTime) {
        this.inTime = inTime;
    }

    public String getDriverType() {
        return driverType;
    }

    public void setDriverType(String driverType) {
        this.driverType = driverType;
    }
}
