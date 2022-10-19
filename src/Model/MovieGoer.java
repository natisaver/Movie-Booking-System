package Model;

public class MovieGoer {

    private String phoneNum;
    private Ticket[] bookings;

    public String getPhoneNum() {
        return this.phoneNum;
    }

    /**
     * 
     * @param phoneNum
     */
    public void setPhoneNum(String phoneNum) {
        this.phoneNum = phoneNum;
    }

}
