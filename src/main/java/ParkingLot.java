public class ParkingLot {
    public int lotId;
    public String name1;
    public int capacity;
    public int availablespace;
    public String attendentId;

    public ParkingLot(int lotId, String name1, int capacity, int availablespace,String attendentId) {
        this.lotId = lotId;
        this.name1 = name1;
        this.capacity = capacity;
        this.availablespace = availablespace;
        this.attendentId=attendentId;
    }

    public String getName1() {
        return name1;
    }

    public void setName1(String name1) {
        this.name1 = name1;
    }

    public String getAttendentId() {
        return attendentId;
    }

    public void setAttendentId(String attendentId) {
        this.attendentId = attendentId;
    }

    public int getLotId() {
        return lotId;
    }

    public void setLotId(int lotId) {
        this.lotId = lotId;
    }



    public int getCapacity() {
        return capacity;
    }

    public void setCapacity(int capacity) {
        this.capacity = capacity;
    }

    public int getAvailablespace() {
        return availablespace;
    }

    public void setAvailablespace(int availablespace) {
        this.availablespace = availablespace;
    }
}
