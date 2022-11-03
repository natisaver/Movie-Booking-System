package Controller;

import java.util.ArrayList;

import Model.Holiday;
import java.time.LocalDateTime;

import Model.Movie;
import Model.MovieSession;
import Model.ageGroup_Enum;
import Model.cinemaClass_Enum; 
import Model.movieType_Enum; 

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
        String movietype, agegroup, cinemaclass;
        LocalDateTime dateTime;
        Boolean isHoliday = false;

        movietype = session.getMovieType().toString();
        agegroup = ageGroup.toString();
        cinemaclass = cinemaClass.toString();
        dateTime = session.getShowtime();

        //check if current day is a public holiday
        ArrayList<Holiday> holidayArrayList = HolidayController.read();
        for(Holiday h : holidayArrayList) {
            isHoliday = dateTime.isEqual(h.getDate());
        }

        //for student pricing (before 6pm, excluding PH & weekends)
        if (!isHoliday && dateTime.getHour() < 18 && agegroup == "CHILD" && dateTime.getDayOfWeek().toString() != "SATURDAY" && dateTime.getDayOfWeek().toString() != "SUNDAY"){
            switch(movietype){
                case "THREED":
                    if (cinemaclass == "GOLD"){
                        return 11.00;
                    }
                    else if (cinemaclass == "MAX"){
                        return 10.00;

                    }
                    else {
                        return 9.00;
                    }   


                case "BLOCKBUSTER":
                    if (cinemaclass == "GOLD"){
                        return 10.00;
                    }
                    else if (cinemaclass == "MAX"){
                        return 9.00;
                    }
                    else {
                        return 8.00;
                    }   

                default:
                    if (cinemaclass == "GOLD"){
                        return 9.00;
                    }
                    else if (cinemaclass == "MAX"){
                        return 8.00;
                    }
                    else {
                        return 7.00;
                    }   
            }
        }
        //for seniors (before 6pm, excluding PH & weekends & 3D Movies)
        if (!isHoliday && dateTime.getHour() < 18 && agegroup == "SENIOR" && dateTime.getDayOfWeek().toString() != "SATURDAY" && dateTime.getDayOfWeek().toString() != "SUNDAY"){
            switch(movietype){
                case "BLOCKBUSTER":
                    if (cinemaclass == "GOLD"){
                        return 7.00;
                    }
                    else if (cinemaclass == "MAX"){
                        return 6.00;
                    }
                    else {
                        return 5.00;
                    }   

                case "TWOD":
                    if (cinemaclass == "GOLD"){
                        return 6.00;
                    }
                    else if (cinemaclass == "MAX"){
                        return 5.00;
                    }
                    else {
                        return 4.00;
                    }   
                //no case for "THREED", charged adult pricing
            }
        }

        //for adults check the day of the week
        switch(dateTime.getDayOfWeek().toString()){
            //friday special price
            case "FRIDAY":
                switch(movietype){
                    //3D pricing is regardless of time
                    case "THREED":
                        if (cinemaclass == "GOLD"){
                            return 17.00;
                        }
                        else if (cinemaclass == "MAX"){
                            return 16.00;
                        }
                        else {
                            return 15.00;
                        }   

                    case "BLOCKBUSTER":
                        if (cinemaclass == "GOLD"){
                            if (dateTime.getHour() < 18)
                                return 12.50;
                            else 
                                return 14.00;
                        }
                        else if (cinemaclass == "MAX"){
                            if (dateTime.getHour() < 18)
                                return 11.50;
                            else 
                                return 13.00;
                        }
                        else {
                            if (dateTime.getHour() < 18)
                                return 10.50;
                            else 
                                return 12.00;
                        }   
                    //case "TWOD"
                    default:
                        if (cinemaclass == "GOLD"){
                            if (dateTime.getHour() < 18)
                                return 12.50;
                            else 
                                return 14.00;
                        }
                        else if (cinemaclass == "MAX"){
                            if (dateTime.getHour() < 18)
                                return 11.50;
                            else 
                                return 13.00;
                        }
                        else {
                            if (dateTime.getHour() < 18)
                                return 10.50;
                            else 
                                return 12.00;
                        }  
                }

            //thursday all day special price
            case "THURSDAY":
                switch(movietype){
                    case "THREED":
                        if (cinemaclass == "GOLD"){
                            return 13.00;
                        }
                        else if (cinemaclass == "MAX"){
                            return 12.00;
                        }
                        else {
                            return 11.00;
                        }   

                    case "BLOCKBUSTER":
                        if (cinemaclass == "GOLD"){
                            return 12.50;
                        }
                        else if (cinemaclass == "MAX"){
                            return 11.50;
                        }
                        else {
                            return 10.50;
                        }   
                    //case "TWOD"
                    default:
                        if (cinemaclass == "GOLD"){
                            return 11.50;
                        }
                        else if (cinemaclass == "MAX"){
                            return 10.50;
                        }
                        else {
                            return 9.50;
                        }  
                }
            //weekend all day pricing
            case "SATURDAY":
            case "SUNDAY":
                switch(movietype){
                    case "THREED":
                        if (cinemaclass == "GOLD"){
                            return 17.00;
                        }
                        else if (cinemaclass == "MAX"){
                            return 16.00;
                        }
                        else {
                            return 15.00;
                        }   

                    case "BLOCKBUSTER":
                        if (cinemaclass == "GOLD"){
                            return 14.00;
                        }
                        else if (cinemaclass == "MAX"){
                            return 13.00;
                        }
                        else {
                            return 12.00;
                        }   

                    default:
                        if (cinemaclass == "GOLD"){
                            return 13.00;
                        }
                        else if (cinemaclass == "MAX"){
                            return 12.00;
                        }
                        else {
                            return 11.00;
                        }   
                }
            
            //mon to wed all day pricing
            default:
                switch(movietype){
                    case "THREED":
                        if (cinemaclass == "GOLD"){
                            return 13.00;
                        }
                        else if (cinemaclass == "MAX"){
                            return 12.00;
                        }
                        else {
                            return 11.00;
                        }   

                    case "BLOCKBUSTER":
                        if (cinemaclass == "GOLD"){
                            return 11.50;
                        }
                        else if (cinemaclass == "MAX"){
                            return 10.50;
                        }
                        else {
                            return 9.50;
                        }   

                    default:
                        if (cinemaclass == "GOLD"){
                            return 10.50;
                        }
                        else if (cinemaclass == "MAX"){
                            return 9.50;
                        }
                        else {
                            return 8.50;
                        }  
                }
            
        }
    }

}
