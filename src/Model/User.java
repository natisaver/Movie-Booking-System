package Model;
/**
 Represents a user in the MOBLIMA Cinema Application
 @author CS2002 Group 
 @version 1.0
 @since 17-10-2022
 */


public class User {
    /**
	 * User's Name
	 */
	private String name;

    /**
	 * User's Email Address
	 */
    private String email;
	
	/**
	 * User's Role (Admin, MovieGoer etc.)
	 */
	private int role; 

    /**
	 * User's Password
     */
	private String password; 
	
	/**
     * Current Available Roles
     */
    public static final int MOVIE_GOER = 0, ADMIN = 1;
    
	/** 
	 * Constructor
     * @param name		    The User's name 
	 * @param email			The User's email/username
	 * @param role			The User's role 
     * @param password
     * 
	 */
	public User (String name, String email, int role, String password) {
        this.name = name;
        this.email = email;
        this.role = role;
        this.password = password;
    }

    /** 
	 * Get the name of this User
	 * @return String   this user's name
	 */
	public String getName() {
		return this.name;
	}

    /** 
	 * Get the email of this User
	 * @return String   this user's email
	 */
	public String getEmail() {
    	return this.email;
	}
    
	/** Get the role of this User
	 * @return int   this User's role
	 */
	public int getRole() {
    	return this.role;
	}

    /**
     * Set user's name
     * @param name   user's name
     */
    public void setName(String name) {
        this.name = name;
    }

    /**
     * Set user's email
     * @param email   user's email
     */
    public void setEmail(String email){
        this.email = email;
    }

    /**
     * Set user's role
     * 0: MOVIE_GOER
     * 1: ADMIN
     * @param role   user's role
     */
    public void setRole(int role) {
        this.role = role;
    }

    /** Get the password of this User
	 * @return password   this User's password
	 */
	public String getPassword() {
    	return this.password;
	}

    /**
     * Set user's password
     * @param password   user's password to set
     */
    public void setPassword(String password) {
        this.password = password;
    }

}


