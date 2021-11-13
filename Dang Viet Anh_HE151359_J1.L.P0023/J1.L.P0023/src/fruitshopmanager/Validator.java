/*
 * Copyright(C) 2021, Dang Viet Anh
 * Code: J1.S.P0023
 * Title: Fruit Shop Manager
 *
 * Record of change:
 * DATE            Version             AUTHOR           DESCRIPTION
 * 2021-10-10         1.0               AnhDV          First Implement
 */
package fruitshopmanager;

import entity.Fruit;
import java.util.ArrayList;
import java.util.logging.Logger;

/**
 * The {@code Validator} class contains methods to check whether the input is
 * valid or not.
 *
 * @author Dang Viet Anh
 */
public class Validator {

    /**
     * declare a final variable LOGGER to use logging for {@code Validator}
     * class
     */
    private static final Logger LOGGER = Logger.getLogger(Validator.class.getName());

    /**
     * This method valid string that user input is not empty and length is small
     * than fixed length
     *
     * @param inputString - string user input.
     * @param length - fixed length
     * @return true if input valid otherwise return false.
     */
    boolean checkValidString(String inputString, int length) {
        return (!inputString.isEmpty() && (inputString.length() <= length));
    }

    /**
     * This method checks whether the number is in the range from min or max
     *
     * @param number is input number
     * @param min minimum number
     * @param max maximum number
     * @param error message error for input
     * @return true if number in range (min,max)
     */
    boolean checkValidNumberInRange(String inputNumber, int min, int max, String error) {
        try {
            int number = Integer.parseInt(inputNumber);
            if (number >= min && number <= max) {
                // if the value is between min and max then return true
                return true;
            }
            LOGGER.warning(error);
        } catch (NumberFormatException ex) {
            LOGGER.warning(error);
        }
        return false;
    }

    /**
     * This method use to check that user input is real number and great that
     * minValue.
     *
     * @param inputNumber - number user input
     * @param minValue - min value method can return.
     * @return true if input valid otherwise return false.
     */
    boolean checkValidFloatNumber(String inputNumber, double minValue) {
        try {
            float number = Float.parseFloat(inputNumber);
            if (number >= minValue) {
                return true;
            }
            LOGGER.warning("Your input must be a positive real number). Please try again!");
        } catch (NumberFormatException ex) {
            LOGGER.warning("Your input must be a positive real number). Please try again!");
        }
        return false;
    }

    /**
     * This method use to check that user input is integer number and great that
     * minValue.
     *
     * @param inputNumber - number user input
     * @param minValue - min value method can return.
     * @return true if input valid otherwise return false.
     */
    boolean checkValidIntegerNumber(String inputNumber, int minValue) {
        try {
            float number = Integer.parseInt(inputNumber);
            if (number >= minValue) {
                return true;
            }
            LOGGER.warning("Your input must be a positive integer number). Please try again!");
        } catch (NumberFormatException ex) {
            LOGGER.warning("Your input must be a positive integer number). Please try again!");
        }
        return false;
    }

    /**
     * This method use to check that id of fruit exist in list or not.
     *
     * @param fruitsList - array list contain all fruitsList
     * @param id - id need to check
     * @return true if id exist in list. Otherwise return false.
     */
    boolean checkFruitIdExists(ArrayList<Fruit> fruitsList, int id) {
        return fruitsList.stream().anyMatch((fruit) -> (fruit.getFruitId() == id));
    }
}
