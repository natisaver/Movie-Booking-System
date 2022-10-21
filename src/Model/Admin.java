package Model;

/**
 Represents an admin in the MOBLIMA Cinema Application
 @author Sally Carrera 
 @version 1.0
 @since 17-10-2022
 */

public class Admin extends User{
    /**
     * Current Available Roles
     */
    public static final int MOVIE_GOER = 0, ADMIN = 1;

	/** 
	 * Constructor
     * @param name		    The User's name 
	 * @param email			The User's email/username
	 * @param password		The User's age 
	 */
	public Admin (String name, String email, String password) {
        super(name, email, 1, password);
    }

}
