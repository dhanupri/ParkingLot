public class ParkingLot_Owner {
    public int OwnerId;
    public String OwnerName;

    public ParkingLot_Owner(int ownerId, String ownerName) {
        OwnerId = ownerId;
        OwnerName = ownerName;
    }

    public int getOwnerId() {
        return OwnerId;
    }

    public void setOwnerId(int ownerId) {
        OwnerId = ownerId;
    }

    public String getOwnerName() {
        return OwnerName;
    }

    public void setOwnerName(String ownerName) {
        OwnerName = ownerName;
    }
}
