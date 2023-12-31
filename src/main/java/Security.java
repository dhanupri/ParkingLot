interface Observer {
    Object update();
}
public class Security implements Observer{
    public int securityId;
    public String SecurityName;

    public Security(int securityId, String securityName) {
        this.securityId = securityId;
        SecurityName = securityName;
    }

    public int getSecurityId() {
        return securityId;
    }

    public void setSecurityId(int securityId) {
        this.securityId = securityId;
    }

    public String getSecurityName() {
        return SecurityName;
    }

    public void setSecurityName(String securityName) {
        SecurityName = securityName;
    }


        @Override
        public Object update() {
            if(Parkinglot_JDBC.Total_slots()==0){
                System.out.println("Parking lot is full. Redirecting security staff.");
                return "Parking lot is full. Redirecting security staff.";
            }
            return null;
        }
    }

