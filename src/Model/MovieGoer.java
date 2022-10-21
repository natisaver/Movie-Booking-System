package Model;

import java.util.ArrayList;

/**
 Represents a regular Movie Goer in the MOBLIMA Cinema Application
 @author Sally Carrera
 @version 1.0
 @since 17-10-2022
 */

public class MovieGoer extends User{
    /**
	 * MovieGoer's phone number
	 */
	private String phoneNum;

    /**
	 * MovieGoer's age
	 */
    private int age;
	
	/**
	 * MovieGoer's bookings
	 */
    private ArrayList<Ticket> bookings = new ArrayList<Ticket>();
	
	/**
     * Current Available Roles
     */
    public static final int MOVIE_GOER = 0, ADMIN = 1;

	/** 
	 * Constructor
     * @param name		    The User's name 
	 * @param email			The User's email/username
     * @param phoneNumber	The User's phone number
	 * @param age			The User's age 
	 */
	public MovieGoer (String name, String email, String phoneNumber, int age, String password) {
        super(name, email, 0, password);
        this.phoneNum = phoneNumber;
        this.age = age;
    }

    /** 
	 * Get the phone number of this User
	 * @return String   this user's phone number
	 */
	public String getPhoneNum() {
		return this.phoneNum;
	}

    /** 
	 * Get the age of this User
	 * @return int   this user's age
	 */
	public int getAge() {
    	return this.age;
	}
    
	/** Get the bookings of this User
	 * @return ArrayList   this User's bookings
	 */
	public ArrayList<Ticket> getBooking() {
    	return this.bookings;
	}

    /**
     * Set user's phone number
     * @param phoneNum   user's phoneNumber
     */
    public void setName(String phoneNum) {
        this.phoneNum = phoneNum;
    }

    /**
     * Set user's age
     * @param age   user's age
     */
    public void setAge(int age){
        this.age = age;
    }

    /**
     * Add to user's bookings
     * @param ticket   user's bookings
     */
    public void setBooking(Ticket ticket) {
        this.bookings.add(ticket);
    }

}
