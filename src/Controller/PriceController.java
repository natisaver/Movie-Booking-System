package Controller;

import Model.Movie;
import Model.MovieSession;
import Model.ageGroup_Enum;
import Model.cinemaClass_Enum; 

/**
 * Controller to determine the price of a ticket in MOBLIMA Cinema Application
 * @author Sally Carrera
 * @version 1.0
 * @since 21-10-2022
 */

public class PriceController {
    /**
     * Calculates the price of the ticket based on: Date/Time, Age Group, Movie Type, Cinema Class
     * @param MovieSession          Input Movie Session to attain details on Date/Time, Movie Type
     * @param ageGroup_Enum         Input Age Group               
     * @param cinemaClass_Enum      Input Cinema Class
     * @return price                Return price of ticket
     */
    public static double calculatePrice(MovieSession session, ageGroup_Enum ageGroup, cinemaClass_Enum cinemaClass) {
        double price;
        session.getClass
        return price;
    }

}
