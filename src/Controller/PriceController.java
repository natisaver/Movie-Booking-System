package Controller;

import java.util.ArrayList;

import Model.Holiday;
import java.time.LocalDateTime;

import Model.MovieSession;
import Model.ageGroup_Enum;
import Model.cinemaClass_Enum; 
import Model.movieType_Enum;
import Model.seatType_Enum; 

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
    public static double calculatePrice(MovieSession session, ageGroup_Enum ageGroup, cinemaClass_Enum cinemaClass, seatType_Enum seatType) {
        String movietype, agegroup, cinemaclass, seattype;
        LocalDateTime dateTime;
        Boolean isHoliday = false;

        dateTime = session.getShowtime();

        movietype = session.getMovieType().name();
        agegroup = ageGroup.name();
        cinemaclass = cinemaClass.name();
        seattype = seatType.name();

        //check if current day is a public holiday
        ArrayList<Holiday> holidayArrayList = HolidayController.read();
        for(Holiday h : holidayArrayList) {
            isHoliday = dateTime.isEqual(h.getDate());
        }

        //for student pricing (before 6pm, excluding PH & weekends)
        if (!isHoliday && dateTime.getHour() < 18 && agegroup == "CHILD" && dateTime.getDayOfWeek().toString() != "SATURDAY" && dateTime.getDayOfWeek().toString() != "SUNDAY"){
            switch(movietype){
                case "THREED":
                    if (cinemaclass == "GOLD" && seattype == "ELITE"){
                        return PriceDataController.readByID("0")[2] + PriceDataController.readByID("0")[3] + PriceDataController.readByID("0")[4];
                    }
                    else if (cinemaclass == "GOLD"){
                        return PriceDataController.readByID("0")[2] + PriceDataController.readByID("0")[3];
                    }
                    else if (seattype == "ELITE"){
                        return PriceDataController.readByID("0")[2] + PriceDataController.readByID("0")[4];
                    }
                    else {
                        return PriceDataController.readByID("0")[2];
                    }   


                case "BLOCKBUSTER":
                    if (cinemaclass == "GOLD" && seattype == "ELITE"){
                        return PriceDataController.readByID("0")[1] + PriceDataController.readByID("0")[3] + PriceDataController.readByID("0")[4];
                    }
                    else if (cinemaclass == "GOLD"){
                        return PriceDataController.readByID("0")[1] + PriceDataController.readByID("0")[3];
                    }
                    else if (seattype == "ELITE"){
                        return PriceDataController.readByID("0")[1] + PriceDataController.readByID("0")[4];
                    }
                    else {
                        return PriceDataController.readByID("0")[1];
                    }    

                default:
                    if (cinemaclass == "GOLD" && seattype == "ELITE"){
                        return PriceDataController.readByID("0")[0] + PriceDataController.readByID("0")[3] + PriceDataController.readByID("0")[4];
                    }
                    else if (cinemaclass == "GOLD"){
                        return PriceDataController.readByID("0")[0] + PriceDataController.readByID("0")[3];
                    }
                    else if (seattype == "ELITE"){
                        return PriceDataController.readByID("0")[0] + PriceDataController.readByID("0")[4];
                    }
                    else {
                        return PriceDataController.readByID("0")[0];
                    }      
            }
        }
        //for seniors (before 6pm, excluding PH & weekends & 3D Movies)
        if (!isHoliday && dateTime.getHour() < 18 && agegroup == "SENIOR" && dateTime.getDayOfWeek().toString() != "SATURDAY" && dateTime.getDayOfWeek().toString() != "SUNDAY"){
            switch(movietype){
                case "THREED":
                    if (cinemaclass == "GOLD" && seattype == "ELITE"){
                        return PriceDataController.readByID("1")[2] + PriceDataController.readByID("1")[3] + PriceDataController.readByID("0")[4];
                    }
                    else if (cinemaclass == "GOLD"){
                        return PriceDataController.readByID("1")[2] + PriceDataController.readByID("1")[3];
                    }
                    else if (seattype == "ELITE"){
                        return PriceDataController.readByID("1")[2] + PriceDataController.readByID("1")[4];
                    }
                    else {
                        return PriceDataController.readByID("1")[2];
                    }   


                case "BLOCKBUSTER":
                    if (cinemaclass == "GOLD" && seattype == "ELITE"){
                        return PriceDataController.readByID("1")[1] + PriceDataController.readByID("1")[3] + PriceDataController.readByID("0")[4];
                    }
                    else if (cinemaclass == "GOLD"){
                        return PriceDataController.readByID("1")[1] + PriceDataController.readByID("1")[3];
                    }
                    else if (seattype == "ELITE"){
                        return PriceDataController.readByID("1")[1] + PriceDataController.readByID("1")[4];
                    }
                    else {
                        return PriceDataController.readByID("1")[1];
                    }    

                default:
                    if (cinemaclass == "GOLD" && seattype == "ELITE"){
                        return PriceDataController.readByID("1")[0] + PriceDataController.readByID("1")[3] + PriceDataController.readByID("0")[4];
                    }
                    else if (cinemaclass == "GOLD"){
                        return PriceDataController.readByID("1")[0] + PriceDataController.readByID("1")[3];
                    }
                    else if (seattype == "ELITE"){
                        return PriceDataController.readByID("1")[0] + PriceDataController.readByID("1")[4];
                    }
                    else {
                        return PriceDataController.readByID("1")[0];
                    }  
            }
        }

        //for adults check the day of the week
        switch(dateTime.getDayOfWeek().toString()){
            //friday special price
            case "FRIDAY":
                if (dateTime.getHour() < 18) {
                    switch(movietype){
                        case "THREED":
                            if (cinemaclass == "GOLD" && seattype == "ELITE"){
                                return PriceDataController.readByID("4")[2] + PriceDataController.readByID("4")[3] + PriceDataController.readByID("4")[4];
                            }
                            else if (cinemaclass == "GOLD"){
                                return PriceDataController.readByID("4")[2] + PriceDataController.readByID("4")[3];
                            }
                            else if (seattype == "ELITE"){
                                return PriceDataController.readByID("4")[2] + PriceDataController.readByID("4")[4];
                            }
                            else {
                                return PriceDataController.readByID("4")[2];
                            }   
        
        
                        case "BLOCKBUSTER":
                            if (cinemaclass == "GOLD" && seattype == "ELITE"){
                                return PriceDataController.readByID("4")[1] + PriceDataController.readByID("4")[3] + PriceDataController.readByID("4")[4];
                            }
                            else if (cinemaclass == "GOLD"){
                                return PriceDataController.readByID("4")[1] + PriceDataController.readByID("4")[3];
                            }
                            else if (seattype == "ELITE"){
                                return PriceDataController.readByID("")[1] + PriceDataController.readByID("4")[4];
                            }
                            else {
                                return PriceDataController.readByID("4")[1];
                            }    
        
                        default:
                            if (cinemaclass == "GOLD" && seattype == "ELITE"){
                                return PriceDataController.readByID("4")[0] + PriceDataController.readByID("4")[3] + PriceDataController.readByID("4")[4];
                            }
                            else if (cinemaclass == "GOLD"){
                                return PriceDataController.readByID("4")[0] + PriceDataController.readByID("4")[3];
                            }
                            else if (seattype == "ELITE"){
                                return PriceDataController.readByID("4")[0] + PriceDataController.readByID("4")[4];
                            }
                            else {
                                return PriceDataController.readByID("4")[0];
                            }      
                    }
                }
                else {
                    switch(movietype){
                        case "THREED":
                            if (cinemaclass == "GOLD" && seattype == "ELITE"){
                                return PriceDataController.readByID("5")[2] + PriceDataController.readByID("5")[3] + PriceDataController.readByID("5")[4];
                            }
                            else if (cinemaclass == "GOLD"){
                                return PriceDataController.readByID("5")[2] + PriceDataController.readByID("5")[3];
                            }
                            else if (seattype == "ELITE"){
                                return PriceDataController.readByID("5")[2] + PriceDataController.readByID("5")[4];
                            }
                            else {
                                return PriceDataController.readByID("5")[2];
                            }   
        
        
                        case "BLOCKBUSTER":
                            if (cinemaclass == "GOLD" && seattype == "ELITE"){
                                return PriceDataController.readByID("5")[1] + PriceDataController.readByID("5")[3] + PriceDataController.readByID("5")[4];
                            }
                            else if (cinemaclass == "GOLD"){
                                return PriceDataController.readByID("5")[1] + PriceDataController.readByID("5")[3];
                            }
                            else if (seattype == "ELITE"){
                                return PriceDataController.readByID("5")[1] + PriceDataController.readByID("5")[4];
                            }
                            else {
                                return PriceDataController.readByID("5")[1];
                            }    
        
                        default:
                            if (cinemaclass == "GOLD" && seattype == "ELITE"){
                                return PriceDataController.readByID("5")[0] + PriceDataController.readByID("5")[3] + PriceDataController.readByID("5")[4];
                            }
                            else if (cinemaclass == "GOLD"){
                                return PriceDataController.readByID("5")[0] + PriceDataController.readByID("5")[3];
                            }
                            else if (seattype == "ELITE"){
                                return PriceDataController.readByID("5")[0] + PriceDataController.readByID("5")[4];
                            }
                            else {
                                return PriceDataController.readByID("5")[0];
                            }      
                    }
                }
                

            //thursday all day special price
            case "THURSDAY":
                switch(movietype){
                    case "THREED":
                        if (cinemaclass == "GOLD" && seattype == "ELITE"){
                            return PriceDataController.readByID("3")[2] + PriceDataController.readByID("3")[3] + PriceDataController.readByID("3")[4];
                        }
                        else if (cinemaclass == "GOLD"){
                            return PriceDataController.readByID("3")[2] + PriceDataController.readByID("3")[3];
                        }
                        else if (seattype == "ELITE"){
                            return PriceDataController.readByID("3")[2] + PriceDataController.readByID("3")[4];
                        }
                        else {
                            return PriceDataController.readByID("3")[2];
                        }   


                    case "BLOCKBUSTER":
                        if (cinemaclass == "GOLD" && seattype == "ELITE"){
                            return PriceDataController.readByID("3")[1] + PriceDataController.readByID("3")[3] + PriceDataController.readByID("3")[4];
                        }
                        else if (cinemaclass == "GOLD"){
                            return PriceDataController.readByID("3")[1] + PriceDataController.readByID("3")[3];
                        }
                        else if (seattype == "ELITE"){
                            return PriceDataController.readByID("3")[1] + PriceDataController.readByID("3")[4];
                        }
                        else {
                            return PriceDataController.readByID("3")[1];
                        }    

                    default:
                        if (cinemaclass == "GOLD" && seattype == "ELITE"){
                            return PriceDataController.readByID("3")[0] + PriceDataController.readByID("3")[3] + PriceDataController.readByID("3")[4];
                        }
                        else if (cinemaclass == "GOLD"){
                            return PriceDataController.readByID("3")[0] + PriceDataController.readByID("3")[3];
                        }
                        else if (seattype == "ELITE"){
                            return PriceDataController.readByID("3")[0] + PriceDataController.readByID("3")[4];
                        }
                        else {
                            return PriceDataController.readByID("3")[0];
                        }      
                }
            //weekend all day pricing
            case "SATURDAY":
            case "SUNDAY":
                switch(movietype){
                    case "THREED":
                        if (cinemaclass == "GOLD" && seattype == "ELITE"){
                            return PriceDataController.readByID("6")[2] + PriceDataController.readByID("6")[3] + PriceDataController.readByID("6")[4];
                        }
                        else if (cinemaclass == "GOLD"){
                            return PriceDataController.readByID("6")[2] + PriceDataController.readByID("6")[3];
                        }
                        else if (seattype == "ELITE"){
                            return PriceDataController.readByID("6")[2] + PriceDataController.readByID("6")[4];
                        }
                        else {
                            return PriceDataController.readByID("6")[2];
                        }   


                    case "BLOCKBUSTER":
                        if (cinemaclass == "GOLD" && seattype == "ELITE"){
                            return PriceDataController.readByID("6")[1] + PriceDataController.readByID("6")[3] + PriceDataController.readByID("6")[4];
                        }
                        else if (cinemaclass == "GOLD"){
                            return PriceDataController.readByID("6")[1] + PriceDataController.readByID("6")[3];
                        }
                        else if (seattype == "ELITE"){
                            return PriceDataController.readByID("6")[1] + PriceDataController.readByID("6")[4];
                        }
                        else {
                            return PriceDataController.readByID("6")[1];
                        }    

                    default:
                        if (cinemaclass == "GOLD" && seattype == "ELITE"){
                            return PriceDataController.readByID("6")[0] + PriceDataController.readByID("6")[3] + PriceDataController.readByID("6")[4];
                        }
                        else if (cinemaclass == "GOLD"){
                            return PriceDataController.readByID("6")[0] + PriceDataController.readByID("6")[3];
                        }
                        else if (seattype == "ELITE"){
                            return PriceDataController.readByID("6")[0] + PriceDataController.readByID("6")[4];
                        }
                        else {
                            return PriceDataController.readByID("6")[0];
                        }      
                }
            
            //mon to wed all day pricing
            default:
                switch(movietype){
                    case "THREED":
                        if (cinemaclass == "GOLD" && seattype == "ELITE"){
                            return PriceDataController.readByID("2")[2] + PriceDataController.readByID("2")[3] + PriceDataController.readByID("2")[4];
                        }
                        else if (cinemaclass == "GOLD"){
                            return PriceDataController.readByID("2")[2] + PriceDataController.readByID("2")[3];
                        }
                        else if (seattype == "ELITE"){
                            return PriceDataController.readByID("2")[2] + PriceDataController.readByID("2")[4];
                        }
                        else {
                            return PriceDataController.readByID("2")[2];
                        }   


                    case "BLOCKBUSTER":
                        if (cinemaclass == "GOLD" && seattype == "ELITE"){
                            return PriceDataController.readByID("2")[1] + PriceDataController.readByID("2")[3] + PriceDataController.readByID("2")[4];
                        }
                        else if (cinemaclass == "GOLD"){
                            return PriceDataController.readByID("2")[1] + PriceDataController.readByID("2")[3];
                        }
                        else if (seattype == "ELITE"){
                            return PriceDataController.readByID("2")[1] + PriceDataController.readByID("2")[4];
                        }
                        else {
                            return PriceDataController.readByID("2")[1];
                        }    

                    default:
                        if (cinemaclass == "GOLD" && seattype == "ELITE"){
                            return PriceDataController.readByID("2")[0] + PriceDataController.readByID("2")[3] + PriceDataController.readByID("2")[4];
                        }
                        else if (cinemaclass == "GOLD"){
                            return PriceDataController.readByID("2")[0] + PriceDataController.readByID("2")[3];
                        }
                        else if (seattype == "ELITE"){
                            return PriceDataController.readByID("2")[0] + PriceDataController.readByID("2")[4];
                        }
                        else {
                            return PriceDataController.readByID("2")[0];
                        }      
                }
            
        }
    }
    public static double calculatePriceDirect(LocalDateTime timeinput, movieType_Enum movieType, ageGroup_Enum ageGroup, cinemaClass_Enum cinemaClass, seatType_Enum seatType) {
        String movietype, agegroup, cinemaclass, seattype;
        LocalDateTime dateTime;
        Boolean isHoliday = false;

        dateTime = timeinput;
        movietype = movieType.name();
        agegroup = ageGroup.name();
        cinemaclass = cinemaClass.name();
        seattype = seatType.name();

        //check if current day is a public holiday
        ArrayList<Holiday> holidayArrayList = HolidayController.read();
        for(Holiday h : holidayArrayList) {
            isHoliday = dateTime.isEqual(h.getDate());
        }

        //for student pricing (before 6pm, excluding PH & weekends)
        if (!isHoliday && dateTime.getHour() < 18 && agegroup == "CHILD" && dateTime.getDayOfWeek().toString() != "SATURDAY" && dateTime.getDayOfWeek().toString() != "SUNDAY"){
            switch(movietype){
                case "THREED":
                    if (cinemaclass == "GOLD" && seattype == "ELITE"){
                        return PriceDataController.readByID("0")[2] + PriceDataController.readByID("0")[3] + PriceDataController.readByID("0")[4];
                    }
                    else if (cinemaclass == "GOLD"){
                        return PriceDataController.readByID("0")[2] + PriceDataController.readByID("0")[3];
                    }
                    else if (seattype == "ELITE"){
                        return PriceDataController.readByID("0")[2] + PriceDataController.readByID("0")[4];
                    }
                    else {
                        return PriceDataController.readByID("0")[2];
                    }   


                case "BLOCKBUSTER":
                    if (cinemaclass == "GOLD" && seattype == "ELITE"){
                        return PriceDataController.readByID("0")[1] + PriceDataController.readByID("0")[3] + PriceDataController.readByID("0")[4];
                    }
                    else if (cinemaclass == "GOLD"){
                        return PriceDataController.readByID("0")[1] + PriceDataController.readByID("0")[3];
                    }
                    else if (seattype == "ELITE"){
                        return PriceDataController.readByID("0")[1] + PriceDataController.readByID("0")[4];
                    }
                    else {
                        return PriceDataController.readByID("0")[1];
                    }    

                default:
                    if (cinemaclass == "GOLD" && seattype == "ELITE"){
                        return PriceDataController.readByID("0")[0] + PriceDataController.readByID("0")[3] + PriceDataController.readByID("0")[4];
                    }
                    else if (cinemaclass == "GOLD"){
                        return PriceDataController.readByID("0")[0] + PriceDataController.readByID("0")[3];
                    }
                    else if (seattype == "ELITE"){
                        return PriceDataController.readByID("0")[0] + PriceDataController.readByID("0")[4];
                    }
                    else {
                        return PriceDataController.readByID("0")[0];
                    }      
            }
        }
        //for seniors (before 6pm, excluding PH & weekends & 3D Movies)
        if (!isHoliday && dateTime.getHour() < 18 && agegroup == "SENIOR" && dateTime.getDayOfWeek().toString() != "SATURDAY" && dateTime.getDayOfWeek().toString() != "SUNDAY"){
            switch(movietype){
                case "THREED":
                    if (cinemaclass == "GOLD" && seattype == "ELITE"){
                        return PriceDataController.readByID("1")[2] + PriceDataController.readByID("1")[3] + PriceDataController.readByID("0")[4];
                    }
                    else if (cinemaclass == "GOLD"){
                        return PriceDataController.readByID("1")[2] + PriceDataController.readByID("1")[3];
                    }
                    else if (seattype == "ELITE"){
                        return PriceDataController.readByID("1")[2] + PriceDataController.readByID("1")[4];
                    }
                    else {
                        return PriceDataController.readByID("1")[2];
                    }   


                case "BLOCKBUSTER":
                    if (cinemaclass == "GOLD" && seattype == "ELITE"){
                        return PriceDataController.readByID("1")[1] + PriceDataController.readByID("1")[3] + PriceDataController.readByID("0")[4];
                    }
                    else if (cinemaclass == "GOLD"){
                        return PriceDataController.readByID("1")[1] + PriceDataController.readByID("1")[3];
                    }
                    else if (seattype == "ELITE"){
                        return PriceDataController.readByID("1")[1] + PriceDataController.readByID("1")[4];
                    }
                    else {
                        return PriceDataController.readByID("1")[1];
                    }    

                default:
                    if (cinemaclass == "GOLD" && seattype == "ELITE"){
                        return PriceDataController.readByID("1")[0] + PriceDataController.readByID("1")[3] + PriceDataController.readByID("0")[4];
                    }
                    else if (cinemaclass == "GOLD"){
                        return PriceDataController.readByID("1")[0] + PriceDataController.readByID("1")[3];
                    }
                    else if (seattype == "ELITE"){
                        return PriceDataController.readByID("1")[0] + PriceDataController.readByID("1")[4];
                    }
                    else {
                        return PriceDataController.readByID("1")[0];
                    }  
            }
        }

        //for adults check the day of the week
        switch(dateTime.getDayOfWeek().toString()){
            //friday special price
            case "FRIDAY":
                if (dateTime.getHour() < 18) {
                    switch(movietype){
                        case "THREED":
                            if (cinemaclass == "GOLD" && seattype == "ELITE"){
                                return PriceDataController.readByID("4")[2] + PriceDataController.readByID("4")[3] + PriceDataController.readByID("4")[4];
                            }
                            else if (cinemaclass == "GOLD"){
                                return PriceDataController.readByID("4")[2] + PriceDataController.readByID("4")[3];
                            }
                            else if (seattype == "ELITE"){
                                return PriceDataController.readByID("4")[2] + PriceDataController.readByID("4")[4];
                            }
                            else {
                                return PriceDataController.readByID("4")[2];
                            }   
        
        
                        case "BLOCKBUSTER":
                            if (cinemaclass == "GOLD" && seattype == "ELITE"){
                                return PriceDataController.readByID("4")[1] + PriceDataController.readByID("4")[3] + PriceDataController.readByID("4")[4];
                            }
                            else if (cinemaclass == "GOLD"){
                                return PriceDataController.readByID("4")[1] + PriceDataController.readByID("4")[3];
                            }
                            else if (seattype == "ELITE"){
                                return PriceDataController.readByID("")[1] + PriceDataController.readByID("4")[4];
                            }
                            else {
                                return PriceDataController.readByID("4")[1];
                            }    
        
                        default:
                            if (cinemaclass == "GOLD" && seattype == "ELITE"){
                                return PriceDataController.readByID("4")[0] + PriceDataController.readByID("4")[3] + PriceDataController.readByID("4")[4];
                            }
                            else if (cinemaclass == "GOLD"){
                                return PriceDataController.readByID("4")[0] + PriceDataController.readByID("4")[3];
                            }
                            else if (seattype == "ELITE"){
                                return PriceDataController.readByID("4")[0] + PriceDataController.readByID("4")[4];
                            }
                            else {
                                return PriceDataController.readByID("4")[0];
                            }      
                    }
                }
                else {
                    switch(movietype){
                        case "THREED":
                            if (cinemaclass == "GOLD" && seattype == "ELITE"){
                                return PriceDataController.readByID("5")[2] + PriceDataController.readByID("5")[3] + PriceDataController.readByID("5")[4];
                            }
                            else if (cinemaclass == "GOLD"){
                                return PriceDataController.readByID("5")[2] + PriceDataController.readByID("5")[3];
                            }
                            else if (seattype == "ELITE"){
                                return PriceDataController.readByID("5")[2] + PriceDataController.readByID("5")[4];
                            }
                            else {
                                return PriceDataController.readByID("5")[2];
                            }   
        
        
                        case "BLOCKBUSTER":
                            if (cinemaclass == "GOLD" && seattype == "ELITE"){
                                return PriceDataController.readByID("5")[1] + PriceDataController.readByID("5")[3] + PriceDataController.readByID("5")[4];
                            }
                            else if (cinemaclass == "GOLD"){
                                return PriceDataController.readByID("5")[1] + PriceDataController.readByID("5")[3];
                            }
                            else if (seattype == "ELITE"){
                                return PriceDataController.readByID("5")[1] + PriceDataController.readByID("5")[4];
                            }
                            else {
                                return PriceDataController.readByID("5")[1];
                            }    
        
                        default:
                            if (cinemaclass == "GOLD" && seattype == "ELITE"){
                                return PriceDataController.readByID("5")[0] + PriceDataController.readByID("5")[3] + PriceDataController.readByID("5")[4];
                            }
                            else if (cinemaclass == "GOLD"){
                                return PriceDataController.readByID("5")[0] + PriceDataController.readByID("5")[3];
                            }
                            else if (seattype == "ELITE"){
                                return PriceDataController.readByID("5")[0] + PriceDataController.readByID("5")[4];
                            }
                            else {
                                return PriceDataController.readByID("5")[0];
                            }      
                    }
                }
                

            //thursday all day special price
            case "THURSDAY":
                switch(movietype){
                    case "THREED":
                        if (cinemaclass == "GOLD" && seattype == "ELITE"){
                            return PriceDataController.readByID("3")[2] + PriceDataController.readByID("3")[3] + PriceDataController.readByID("3")[4];
                        }
                        else if (cinemaclass == "GOLD"){
                            return PriceDataController.readByID("3")[2] + PriceDataController.readByID("3")[3];
                        }
                        else if (seattype == "ELITE"){
                            return PriceDataController.readByID("3")[2] + PriceDataController.readByID("3")[4];
                        }
                        else {
                            return PriceDataController.readByID("3")[2];
                        }   


                    case "BLOCKBUSTER":
                        if (cinemaclass == "GOLD" && seattype == "ELITE"){
                            return PriceDataController.readByID("3")[1] + PriceDataController.readByID("3")[3] + PriceDataController.readByID("3")[4];
                        }
                        else if (cinemaclass == "GOLD"){
                            return PriceDataController.readByID("3")[1] + PriceDataController.readByID("3")[3];
                        }
                        else if (seattype == "ELITE"){
                            return PriceDataController.readByID("3")[1] + PriceDataController.readByID("3")[4];
                        }
                        else {
                            return PriceDataController.readByID("3")[1];
                        }    

                    default:
                        if (cinemaclass == "GOLD" && seattype == "ELITE"){
                            return PriceDataController.readByID("3")[0] + PriceDataController.readByID("3")[3] + PriceDataController.readByID("3")[4];
                        }
                        else if (cinemaclass == "GOLD"){
                            return PriceDataController.readByID("3")[0] + PriceDataController.readByID("3")[3];
                        }
                        else if (seattype == "ELITE"){
                            return PriceDataController.readByID("3")[0] + PriceDataController.readByID("3")[4];
                        }
                        else {
                            return PriceDataController.readByID("3")[0];
                        }      
                }
            //weekend all day pricing
            case "SATURDAY":
            case "SUNDAY":
                switch(movietype){
                    case "THREED":
                        if (cinemaclass == "GOLD" && seattype == "ELITE"){
                            return PriceDataController.readByID("6")[2] + PriceDataController.readByID("6")[3] + PriceDataController.readByID("6")[4];
                        }
                        else if (cinemaclass == "GOLD"){
                            return PriceDataController.readByID("6")[2] + PriceDataController.readByID("6")[3];
                        }
                        else if (seattype == "ELITE"){
                            return PriceDataController.readByID("6")[2] + PriceDataController.readByID("6")[4];
                        }
                        else {
                            return PriceDataController.readByID("6")[2];
                        }   


                    case "BLOCKBUSTER":
                        if (cinemaclass == "GOLD" && seattype == "ELITE"){
                            return PriceDataController.readByID("6")[1] + PriceDataController.readByID("6")[3] + PriceDataController.readByID("6")[4];
                        }
                        else if (cinemaclass == "GOLD"){
                            return PriceDataController.readByID("6")[1] + PriceDataController.readByID("6")[3];
                        }
                        else if (seattype == "ELITE"){
                            return PriceDataController.readByID("6")[1] + PriceDataController.readByID("6")[4];
                        }
                        else {
                            return PriceDataController.readByID("6")[1];
                        }    

                    default:
                        if (cinemaclass == "GOLD" && seattype == "ELITE"){
                            return PriceDataController.readByID("6")[0] + PriceDataController.readByID("6")[3] + PriceDataController.readByID("6")[4];
                        }
                        else if (cinemaclass == "GOLD"){
                            return PriceDataController.readByID("6")[0] + PriceDataController.readByID("6")[3];
                        }
                        else if (seattype == "ELITE"){
                            return PriceDataController.readByID("6")[0] + PriceDataController.readByID("6")[4];
                        }
                        else {
                            return PriceDataController.readByID("6")[0];
                        }      
                }
            
            //mon to wed all day pricing
            default:
                switch(movietype){
                    case "THREED":
                        if (cinemaclass == "GOLD" && seattype == "ELITE"){
                            return PriceDataController.readByID("2")[2] + PriceDataController.readByID("2")[3] + PriceDataController.readByID("2")[4];
                        }
                        else if (cinemaclass == "GOLD"){
                            return PriceDataController.readByID("2")[2] + PriceDataController.readByID("2")[3];
                        }
                        else if (seattype == "ELITE"){
                            return PriceDataController.readByID("2")[2] + PriceDataController.readByID("2")[4];
                        }
                        else {
                            return PriceDataController.readByID("2")[2];
                        }   


                    case "BLOCKBUSTER":
                        if (cinemaclass == "GOLD" && seattype == "ELITE"){
                            return PriceDataController.readByID("2")[1] + PriceDataController.readByID("2")[3] + PriceDataController.readByID("2")[4];
                        }
                        else if (cinemaclass == "GOLD"){
                            return PriceDataController.readByID("2")[1] + PriceDataController.readByID("2")[3];
                        }
                        else if (seattype == "ELITE"){
                            return PriceDataController.readByID("2")[1] + PriceDataController.readByID("2")[4];
                        }
                        else {
                            return PriceDataController.readByID("2")[1];
                        }    

                    default:
                        if (cinemaclass == "GOLD" && seattype == "ELITE"){
                            return PriceDataController.readByID("2")[0] + PriceDataController.readByID("2")[3] + PriceDataController.readByID("2")[4];
                        }
                        else if (cinemaclass == "GOLD"){
                            return PriceDataController.readByID("2")[0] + PriceDataController.readByID("2")[3];
                        }
                        else if (seattype == "ELITE"){
                            return PriceDataController.readByID("2")[0] + PriceDataController.readByID("2")[4];
                        }
                        else {
                            return PriceDataController.readByID("2")[0];
                        }      
                }
            
        }
    }

}
