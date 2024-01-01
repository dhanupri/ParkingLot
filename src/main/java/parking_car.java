public class parking_car {
    public String carId;
    public String inTime;
    public int slot;
    public String carmodel;
    public String lotID;

    public parking_car(String carId, String inTime, int slot, String carmodel, String lotID) {
        this.carId = carId;
        this.inTime = inTime;
        this.slot = slot;
        this.carmodel = carmodel;
        this.lotID = lotID;
    }

    public String getCarId() {
        return carId;
    }

    public void setCarId(String carId) {
        this.carId = carId;
    }

    public String getInTime() {
        return inTime;
    }

    public void setInTime(String inTime) {
        this.inTime = inTime;
    }

    public int getSlot() {
        return slot;
    }

    public void setSlot(int slot) {
        this.slot = slot;
    }

    public String getCarmodel() {
        return carmodel;
    }

    public void  setCarmodel() {
        this.carmodel = carmodel;

    }

    public String getLotID() {
        return lotID;
    }

    public void setLotID(String lotID) {
        this.lotID = lotID;
    }
}
