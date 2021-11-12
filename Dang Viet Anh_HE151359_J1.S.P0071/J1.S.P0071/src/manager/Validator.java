/*
 * Copyright(C) 2021, Dang Viet Anh
 * Code: J1.S.P0071
 * Title: Task TaskManager
 *
 * Record of change:
 * DATE            Version             AUTHOR           DESCRIPTION
 * 2021-10-9         1.0               AnhDV          First Implement
 */
package manager;

import entity.Task;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.time.ZoneId;
import java.util.ArrayList;
import java.util.Date;
;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 * The class contain method to checkValidId the validity of input data of task
 * class
 *
 * @author Dang Viet Anh
 */


public class Validator {

    /**
     * declare a final variable LOGGER to use logging for {@code InputReader}
     * class
     */
    private static final Logger LOGGER = Logger.getLogger(Validator.class.getName());

    /**
     * This method checks whether the number is in the range from min or max
     *
     * @param number is input number
     * @param min minimum number
     * @param max maximum number
     * @return true if number in range (min,max)
     */
    boolean checkValidNumberInRange(String inputNumber, int min, int max) {
        try {
            int number = Integer.parseInt(inputNumber);
            if (number >= min && number <= max) {
                // if the value is between min and max then return true
                return true;
            }
            LOGGER.log(Level.WARNING, "Choice must in [{0}-{1}], enter again !", new Object[]{min, max});
        } catch (NumberFormatException ex) {
            LOGGER.log(Level.WARNING, "Choice must in [{0}-{1}], enter again !", new Object[]{min, max});
        }
        return false;
    }

    /**
     * This method use to check that user input is valid date format
     * "month-date-year".
     *
     * @param inputDate - string that user input.
     * @return true if user input valid date type. Otherwise return false.
     */
    LocalDate checkValidDate(String inputDate) {
        try {
            SimpleDateFormat simpleDateFormat = new SimpleDateFormat("MMM-dd-yyyy");
            Date date = simpleDateFormat.parse(inputDate); // String to date
            String dateFormatted = simpleDateFormat.format(date); // Format to MMM-dd-yyyy
            LocalDate timeReturn = date.toInstant().atZone(ZoneId.systemDefault()).toLocalDate();
            if (inputDate.equalsIgnoreCase(dateFormatted) && timeReturn.compareTo(LocalDate.now()) >= 0) {
                return timeReturn;
            }
            LOGGER.warning("Your input is invalid. Please try again!");
        } catch (ParseException ex) {
            LOGGER.warning("Your input is invalid (It's must be in format MMM/dd/yyyy). Please try again!");
        }
        return null;
    }

    /**
     * This method checkValidId that is ID exist in list or not.
     *
     * @param id - id user input to find
     * @param taskList - list contain all task.
     * @return true if Id exist. Otherwise return false.
     */
    boolean checkValidId(int id, ArrayList<Task> taskList) {
        for (Task task : taskList) {
            if (task.getId() == id) {
                return true;
            }
        }
        return false;
    }

    /**
     * This method use to checkValidId that user input is valid time format (8h
     * -> 17h30 and 8.0, 8.5, 9.0, 9.5 … -> 17.5).
     *
     * @param time - time user inputted
     * @return true if user input valid integer. Otherwise return false.
     */
    boolean checkValidTime(String inputTime) {
        try {
            float time = Float.parseFloat(inputTime);
            // Check in range 8.0 -> 17.5 and non decimal part is .0 or .5
            if ((time >= 8.0) && (time <= 17.5) && (time % 0.5 == 0)) {
                return true;
            }
            LOGGER.warning("Invalid plan, plan within 8.0-17.5 !");
        } catch (NumberFormatException ex) {
            LOGGER.warning("Invalid plan, plan within 8.0-17.5 !");
        }
        return false;
    }

}
