package Controller;

import java.io.*;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.*;
import java.nio.file.Files;
import java.nio.file.Paths;
import Model.Holiday;

/**
 * Reads name and date of public holidays from csv file in the MOBLIMA Cinema
 * Application
 * 
 * @author Sally Carrera
 * @version 1.0
 * @since 21-10-2022
 */
public class HolidayController {
    /**
     * Path in database
     */
    public final static String PATH = DataController.getPath("Holiday");


    /**
     * READ a list of holidays from Database
     * @return {@code ArrayList} of {@code Holiday} objects if database exists, else
     *        empty {@code ArrayList}
     */
    public static ArrayList<Holiday> read() {
        // Check if database exists
        BufferedReader reader = null;
        try {
            reader = new BufferedReader(new FileReader(PATH));
        } catch (FileNotFoundException e) {
            e.printStackTrace();
            return new ArrayList<Holiday>();
        }

        // If Database Exists
        String line = "";
        ArrayList<Holiday> holidayArrayList = new ArrayList<>();
        try {
            reader.readLine();
            while ((line = reader.readLine()) != null) {
                String[] tokens = line.split(",(?=(?:[^\"]*\"[^\"]*\")*[^\"]*$)", -1);
                String dateTime = tokens[0] + " 00:00";
                DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm");
                LocalDateTime newObj = LocalDateTime.parse(dateTime, formatter);
                holidayArrayList.add(new Holiday(tokens[1], newObj));
            }

            reader.close();
            return holidayArrayList;
        } catch (IOException e) {
            e.printStackTrace();
            return holidayArrayList;
        }
    }

    /**
     * Display all holidays in database
     * 
     * @return {@code true} if holidays were displayed , else
     *         {@code false}
     */
    public static Boolean printHolidays() {
        // Check if database exists
        BufferedReader reader = null;
        try {
            reader = new BufferedReader(new FileReader(PATH));
        } catch (FileNotFoundException e) {
            e.printStackTrace();
            return false;
        }

        // If Database Exists
        String line = "";
        int lineCount = 0;
        try {
            reader.readLine();
            while ((line = reader.readLine()) != null) {
                String[] tokens = line.split(",(?=(?:[^\"]*\"[^\"]*\")*[^\"]*$)", -1);
                System.out.println(tokens[0] + ", " + tokens[1]);
                lineCount++;
            }
            reader.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
        if (lineCount > 0)
            return true;
        return false;
    }

    /**
     * CREATE a holiday to the database
     * 
     * @param holiday {@code Holiday} object to be added
     * @param holidayName {@code String} name of holiday
     * @return <code>true</code> if holiday was added, else
     *       <code>false</code>
     */

    public static Boolean addHoliday(LocalDateTime holiday, String holidayName) {
        File inputFile = new File(DataController.getPath("Holiday"));
        File tempFile = new File(DataController.getPath("Temp"));

        BufferedReader reader = null;
        BufferedWriter writer = null;

        try {
            reader = new BufferedReader(new FileReader(inputFile));
            writer = new BufferedWriter(new FileWriter(tempFile));
        } catch (FileNotFoundException e) {
            e.printStackTrace();
            return false;

        } catch (IOException e) {
            e.printStackTrace();
            return false;
        }

        try {
            writer.append("Date");
            writer.append(",");
            writer.append("Name");
            writer.append("\n");

        } catch (IOException e) {
            e.printStackTrace();
        }

        Boolean Found = false;
        String line;
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm");

        try {
            reader.readLine();
            while ((line = reader.readLine()) != null) {
                String[] tokens = line.split(",");
                String holidayDate = tokens[0];
                if (holiday.format(formatter).substring(0, 10).equals(holidayDate)) {
                    Found = true;
                    writer.append(holiday.format(formatter).substring(0, 10));
                    writer.append(",");
                    writer.append(holidayName);
                    writer.append("\n");
                }
                writer.append(tokens[0]);
                writer.append(",");
                writer.append(tokens[1]);
                writer.append("\n");
            }
            if (!Found) {
                writer.append(holiday.format(formatter).substring(0, 10));
                writer.append(",");
                writer.append(holidayName);
                writer.append("\n");
            }
            writer.close();
            reader.close();
            if (Found) {
                Files.delete(Paths.get(DataController.getPath("Temp")));
                return false;
            }
            // delete old file
            Files.delete(Paths.get(DataController.getPath("Holiday")));
        } catch (IOException e) {
            e.printStackTrace();
        }
        // replace with the new file
        tempFile.renameTo(new File(DataController.getPath("Holiday")));
        return true;
    }
    
    /**
     * UPDATE a holiday in the database
     * 
     * @param oldHoliday {@code LocalDateTime} object to be updated
     * @param newHoliday {@code LocalDateTime} object to be updated to
     * @param holidayName {@code String} name of holiday
     * @return <code>true</code> if holiday was updated, else 
     *         return <code>false</code>
     */

    public static Boolean updateHoliday(LocalDateTime oldHoliday, LocalDateTime newHoliday, String holidayName) {
        File inputFile = new File(DataController.getPath("Holiday"));
        File tempFile = new File(DataController.getPath("Temp"));

        BufferedReader reader = null;
        BufferedWriter writer = null;

        try {
            reader = new BufferedReader(new FileReader(inputFile));
            writer = new BufferedWriter(new FileWriter(tempFile));
        } catch (FileNotFoundException e) {
            e.printStackTrace();
            return false;

        } catch (IOException e) {
            e.printStackTrace();
            return false;
        }

        try {
            writer.append("Date");
            writer.append(",");
            writer.append("Name");
            writer.append("\n");

        } catch (IOException e) {
            e.printStackTrace();
        }

        Boolean Found = false;
        String line;
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm");

        try {
            reader.readLine();
            while ((line = reader.readLine()) != null) {
                String[] tokens = line.split(",");
                String holidayDate = tokens[0];
                if (oldHoliday.format(formatter).substring(0, 10).equals(holidayDate)) {
                    Found = true;
                    writer.append(newHoliday.format(formatter).substring(0, 10));
                    writer.append(",");
                    writer.append(holidayName);
                    writer.append("\n");
                } else {
                    writer.append(tokens[0]);
                    writer.append(",");
                    writer.append(tokens[1]);
                    writer.append("\n");
                }
            }
            writer.close();
            reader.close();
            if (!Found) {
                Files.delete(Paths.get(DataController.getPath("Temp")));
                return false;
            }
            // delete old file
            Files.delete(Paths.get(DataController.getPath("Holiday")));
        } catch (IOException e) {
            e.printStackTrace();
        }
        // replace with the new file
        tempFile.renameTo(new File(DataController.getPath("Holiday")));
        return true;
    }

    /**
     * DELETE a holiday from the database
     * 
     * @param holiday {@code LocalDateTime} object to be deleted
     * @return <code>true</code> if holiday was deleted, else
     *        return <code>false</code>
     */

    public static Boolean deleteSingleHoliday(LocalDateTime holiday) {
        File inputFile = new File(DataController.getPath("Holiday"));
        File tempFile = new File(DataController.getPath("Temp"));

        BufferedReader reader = null;
        BufferedWriter writer = null;

        try {
            reader = new BufferedReader(new FileReader(inputFile));
            writer = new BufferedWriter(new FileWriter(tempFile));
        } catch (FileNotFoundException e) {
            e.printStackTrace();
            return false;

        } catch (IOException e) {
            e.printStackTrace();
            return false;
        }

        try {
            writer.append("Date");
            writer.append(",");
            writer.append("Name");
            writer.append("\n");

        } catch (IOException e) {
            e.printStackTrace();
        }

        Boolean Found = false;
        String line;
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm");

        try {
            reader.readLine();
            while ((line = reader.readLine()) != null) {
                String[] tokens = line.split(",");
                String holidayDate = tokens[0];
                if (holiday.format(formatter).substring(0, 10).equals(holidayDate))
                    Found = true;
                else {
                    writer.append(tokens[0]);
                    writer.append(",");
                    writer.append(tokens[1]);
                    writer.append("\n");
                }
            }
            writer.close();
            reader.close();
            if (!Found) {
                Files.delete(Paths.get(DataController.getPath("Temp")));
                return false;
            }
            // delete old file
            Files.delete(Paths.get(DataController.getPath("Holiday")));
        } catch (IOException e) {
            e.printStackTrace();
        }
        // replace with the new file
        tempFile.renameTo(new File(DataController.getPath("Holiday")));
        return true;
    }

    /**
     * DELETE all holidays with holiday Name from the database
     * 
     * @param {@code String} holidayName to be deleted
     * @return <code>true</code> if holiday was deleted, else
     *       return <code>false</code>
     */
    public static Boolean deleteHolidayName(String holidayName) {
        File inputFile = new File(DataController.getPath("Holiday"));
        File tempFile = new File(DataController.getPath("Temp"));

        BufferedReader reader = null;
        BufferedWriter writer = null;

        try {
            reader = new BufferedReader(new FileReader(inputFile));
            writer = new BufferedWriter(new FileWriter(tempFile));
        } catch (FileNotFoundException e) {
            e.printStackTrace();
            return false;

        } catch (IOException e) {
            e.printStackTrace();
            return false;
        }

        try {
            writer.append("Date");
            writer.append(",");
            writer.append("Name");
            writer.append("\n");

        } catch (IOException e) {
            e.printStackTrace();
        }

        Boolean Found = false;
        String line;

        try {
            reader.readLine();
            while ((line = reader.readLine()) != null) {
                String[] tokens = line.split(",");
                String name = tokens[1];
                if (holidayName.equals(name))
                    Found = true;
                else {
                    writer.append(tokens[0]);
                    writer.append(",");
                    writer.append(tokens[1]);
                    writer.append("\n");
                }
            }
            writer.close();
            reader.close();
            if (!Found) {
                Files.delete(Paths.get(DataController.getPath("Temp")));
                return false;
            }
            // delete old file
            Files.delete(Paths.get(DataController.getPath("Holiday")));
        } catch (IOException e) {
            e.printStackTrace();
        }
        // replace with the new file
        tempFile.renameTo(new File(DataController.getPath("Holiday")));
        return true;
    }

    /**
     * READ all holidays from the database by date
     * 
     * @param {@code LocalDateTime} date to be read
     * @return <code>true</code> if holiday was found, else
     *       return <code>false</code>
     */

    public static Boolean readByDate(LocalDateTime holiday) {
        // Check if database exists
        BufferedReader reader = null;
        try {
            reader = new BufferedReader(new FileReader(PATH));
        } catch (FileNotFoundException e) {
            // e.printStackTrace();
            return null;
        }

        // If Database Exists
        String line = "";

        try {
            reader.readLine();
            while ((line = reader.readLine()) != null) {
                DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm");
                String[] tokens = line.split(",(?=(?:[^\"]*\"[^\"]*\")*[^\"]*$)", -1);
                if (LocalDateTime.parse(tokens[0] + " 00:00",formatter).isEqual(holiday)) 
                {
                    reader.close();
                    return true;
                }
            }
            reader.close();
            return false;
        } catch (IOException e) {
            // e.printStackTrace();
            return null;
        }
    }
}
