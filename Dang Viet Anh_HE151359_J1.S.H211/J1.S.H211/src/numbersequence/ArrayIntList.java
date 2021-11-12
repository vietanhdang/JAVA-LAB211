/*
 * Copyright(C) 2021, Dang Viet Anh
 * Code: J1.S.H211
 * Title: From Counts
 *
 * Record of change:
 * DATE            Version             AUTHOR           DESCRIPTION
 * 2021-9-9         1.0                AnhDV            First Implement
 */
package numbersequence;

import java.util.Arrays;
import java.util.Scanner;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 * The {@code ArrayIntList} class contains methods getInputNumber,
 * getInputArrayElement, fromCounts that can be used to input a new list from
 * the keyboard and create a new ArrayIntList of values given an existing
 * ArrayIntList of counts
 *
 * @author Dang Viet Anh
 */
public class ArrayIntList {

    // declare a final variable LOGGER to use logging for {@code ArrayIntList} class
    private static final Logger LOGGER = Logger.getLogger(ArrayIntList.class.getName());

    private int[] elementData; // contain sequence of integer pairs
    private int size; // size of elementData

    /**
     * This method requires the user to enter a number from the keyboard
     *
     * @param msg notification message
     * @param checkPositive if is true: positive integer number
     * @return integer number
     */
    private int getInputNumber(String msg, boolean checkPositive) {
        Scanner scanner = new Scanner(System.in);
        while (true) {
            try {
                System.out.print(msg); // notification message for input
                int number = Integer.parseInt(scanner.nextLine().trim()); // enter numbers from the keyboard
                if (checkPositive == true && number <= 0) {
                    // if checkPositive == true and number <=0 (i.e. it's not a positive integer then ask user to re-enter)
                    LOGGER.log(Level.INFO, "Please enter a positive integer: ");
                } else {
                    return number;
                }
            } catch (NumberFormatException e) {
                LOGGER.log(Level.WARNING, "Please input a valid number!");
                // If the number above is entered as a non-numeric character, the catch will notify you to re-enter it
            }
        }
    }

    /**
     * This method requires the user to enter numbers into the array
     */
    private void getInputArrayElement() {
        while (true) {
            size = getInputNumber("Enter the length of the array: ", true); // enter the length of the array
            if (size % 2 == 0) {
                // if the length of the array is a number divisible by 2 then stop
                break;
            }
            LOGGER.warning("The length of the array must be an even number!");
        }
        elementData = new int[size]; // Initialize array with above input length
        for (int i = 0; i < size; i++) {
            if (i % 2 == 0) {
                // Enter a positive integer at an even position and add to array
                elementData[i] = getInputNumber("Enter element [" + i + "]: ", true);
            } else {
                // Enter a integer at an odd position and add to array
                elementData[i] = getInputNumber("Enter element [" + i + "]: ", false);
            }
        }
    }

    /**
     * This method will returns a new ArrayIntList of values given an existing
     * ArrayIntList of counts
     *
     * @return integer array
     */
    private int[] fromCounts() {
        int getLength = 0; // total length in even position
        for (int i = 0; i < size; i += 2) {
            // if index is in even position then sum to get length
            getLength += elementData[i];
        }
        int[] tempList = new int[getLength]; // initialize new array with length in even position
        int index = 0; // index in tempList
        for (int i = 0; i < size; i += 2) { // index traversal at even position
            for (int j = 0; j < elementData[i]; j++) {
                tempList[index] = elementData[i + 1];
                // store elementData[i] element into tempList array at elementData[i+1]
                index++;
            }
        }
        return tempList;
    }
    
    /**
     * This is a main method have an Object to call ArrayIntList methods and run
     * the program.
     *
     * @param args command-line parameter.
     */
    public static void main(String[] args) {
        ArrayIntList arrayIntList = new ArrayIntList();
        arrayIntList.getInputArrayElement(); // input array
        int[] list2 = arrayIntList.fromCounts(); // store in list 2 the values from fromCount
        System.out.println(Arrays.toString(list2)); // print list 2
    }
}
