public class ParkingAttendent {
    public int  attendentId;
    public String attendentName;

    public ParkingAttendent(int atttendrntId, String attendentName) {
        this.attendentId = atttendrntId;
        this.attendentName = attendentName;
    }

    public int getAtttendrntId() {
        return attendentId;
    }

    public void setAtttendrntId(int atttendrntId) {
        this.attendentId = atttendrntId;
    }

    public String getAttendentName() {
        return attendentName;
    }

    public void setAttendentName(String attendentName) {
        this.attendentName = attendentName;
    }
}
