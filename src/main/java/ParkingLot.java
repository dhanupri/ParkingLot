public class ParkingLot {
    public int lotId;
    public String name;
    public int capacity;
    public int availablespace;

    public ParkingLot(int lotId, String name, int capacity, int availablespace) {
        this.lotId = lotId;
        this.name = name;
        this.capacity = capacity;
        this.availablespace = availablespace;
    }

    public int getLotId() {
        return lotId;
    }

    public void setLotId(int lotId) {
        this.lotId = lotId;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public  int getCapacity() {
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
