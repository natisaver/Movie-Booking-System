package Model;

/**
 Represents an admin in the MOBLIMA Cinema Application
 @author CS2002 Group 
 @version 1.0
 @since 17-10-2022
 */

public class Admin extends User{
    /**
     * Current Available Roles
     */
    public static final int MOVIE_GOER = 0, ADMIN = 1;

    /**
	 * MovieGoer's phone number
	 */
	private String password;

	/** 
	 * Constructor
     * @param name		    The User's name 
	 * @param email			The User's email/username
	 * @param password		The User's age 
	 */
	public Admin (String name, String email, String password) {
        super(name, email, 1);
        this.password = password;
    }

	/** Get the password of this User
	 * @return password   this User's password
	 */
	public String getPassword() {
    	return this.password;
	}

    /**
     * Set user's password
     * @param password   user's password
     */
    public void setPassword(String password) {
        this.password = password;
    }
}
