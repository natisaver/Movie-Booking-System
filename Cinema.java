public class Cinema {

    /**
     * This is the Cinema's code which is a 3 letters String (eg. XXX)
     * This Cinema's code will be used in the TransactionID)
     */
    private String cinemaCode;

    /**
     * This is the Cinema's class type (eg. Standard, Platinum Movie Suites)
     */
    private cinemaClass_Enum cinemaClass;

    /**
     * This is the list of movie sessions available
     */
    private MovieSession[] showings;

    /**
     * This creates a Cinema
     * 
     * @param cinemaCode  This is the Cinema's code
     * @param cinemaClass This is the Cinema's class type
     * @param showings    This is the Cinema's list of movies sessions available
     */
    public Cinema(String cinemaCode, cinemaClass_Enum cinemaClass, MovieSession[] showings) {
        this.cinemaCode = cinemaCode;
        this.cinemaClass = cinemaClass;
        this.showings = new MovieSession[100];
    }

    /**
     * Get the Cinema's code
     * 
     * @return String
     */
    public String getCinemaCode() {
        return cinemaCode;
    }

    /**
     * Set the Cinema's code
     * 
     * @param cinemaCode This is the Cinema's code
     */
    public void setCinemaCode(String cinemaCode) {
        this.cinemaCode = cinemaCode;
    }

    /**
     * Get the Cinema's class type
     * 
     * @return cinemaClass_Enum
     */
    public cinemaClass_Enum getCinemaClass() {
        return cinemaClass;
    }

    /**
     * Set the Cinema's class type
     * 
     * @param cinemaClass This is the Cinema's class type
     */
    public void setCinemaClass(cinemaClass_Enum cinemaClass) {
        this.cinemaClass = cinemaClass;
    }

    /**
     * Get the list of movies sessions available
     * 
     * @return MovieSession[] This is the Cinema's list of movies sessions available
     */
    public MovieSession[] getShowings() {
        return showings;
    }

}