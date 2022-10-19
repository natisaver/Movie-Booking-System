public class Cineplex {

    /**
     * This is the list of Cinema's under a particular Cineplex
     */
    private Cinema[] cinemas;

    /**
     * This is the Cinema's name
     */
    private String name;

    /**
     * This is the Cinema's location
     */
    private String location;

    /**
     * This creates a Cineplex
     * 
     * @param cinemas  This is the list of Cinema's under a particular Cineplex
     * @param name     This is the Cinema's name
     * @param location This is the Cinema's location
     */
    public Cineplex(Cinema[] cinemas, String name, String location) {
        this.cinemas = cinemas;
        this.location = location;
        this.cinemas = new Cinema[3];
    }

    /**
     * Get the list of Cinemas under a particular Cineplex
     * 
     * @return Cinema[]
     */
    public Cinema[] getCinemas() {
        return cinemas;
    }

    /**
     * Get the Cinema's name
     * 
     * @return String
     */
    public String getName() {
        return name;
    }

    /**
     * Set the Cinema's name
     * 
     * @param name
     */
    public void setName(String name) {
        this.name = name;
    }

    /**
     * Get the Cinema's location
     * 
     * @return String
     */
    public String getLocation() {
        return location;
    }

    /**
     * Set the Cinema's location
     * 
     * @param location
     */
    public void setLocation(String location) {
        this.location = location;
    }

}