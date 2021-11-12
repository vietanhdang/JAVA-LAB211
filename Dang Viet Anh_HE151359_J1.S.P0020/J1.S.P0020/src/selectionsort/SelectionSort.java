/*
 * Copyright(C) 2021, Dang Viet Anh
 * Code: J1.S.P0020
 * Title: Selection sort algorithm. 
 *
 * Record of change:
 * DATE            Version             AUTHOR           DESCRIPTION
 * 2021-9-9         1.0                AnhDV          First Implement
 */

package selectionsort;

import java.util.Arrays;
import java.util.Scanner;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 * The {@code SelectionSort} class contains methods getInputNumber, createArray,
 * sortBySelectionSort that can be used to ask the user to enter values from the
 * keyboard into an array and sort them in ascending order.
 *
 * @author Dang Viet Anh
 */
public class SelectionSort {

    /**
     * declare a final variable LOGGER to use logging for {@code SelectionSort}
     * class
     */
    private static final Logger LOGGER = Logger.getLogger(SelectionSort.class.getName());

    /**
     * This method requires the user to enter size of array is a positive
     * integer from the keyboard
     *
     * @return positive integer size of array
     */
    private int getInputSizeArray() {
        Scanner scanner = new Scanner(System.in);
        while (true) {
            try {
                System.out.print("Enter size of Array: ");
                int sizeArray = Integer.parseInt(scanner.nextLine().trim());
                if (sizeArray > 0) {
                    return sizeArray;
                }
                // if number is an integer <= 0 then re-enter
                LOGGER.log(Level.INFO, "Please input a positive integer: ");
            } catch (NumberFormatException e) {
                // if the input is not a numeric character, re-enter it
                LOGGER.log(Level.WARNING, "Please input a valid number: ");
            }
        }
    }

    /**
     * This method requires the user to enter element of array from the keyboard
     *
     * @param msg notify the user to enter
     * @return real number (element of array)
     */
    private double getInputElementArray(String msg) {
        Scanner scanner = new Scanner(System.in);
        while (true) {
            try {
                System.out.print(msg); // notify the user to enter
                return Double.parseDouble(scanner.nextLine().trim());
            } catch (NumberFormatException e) {
                // if the input is not a numeric character, re-enter it
                LOGGER.log(Level.WARNING, "Please input a valid number: ");
            }
        }
    }

    /**
     * This method requires the user enter the elements in the array
     *
     * @return a double array
     */
    private double[] createArray() {
        int size = getInputSizeArray();
        double[] newArray = new double[size]; // initialize newArray array with length size
        for (int i = 0; i < size; i++) {
            newArray[i] = getInputElementArray("Enter element[" + i + "]: ");
        }
        return newArray; // return array
    }

    /**
     * This method will get parameter as a array and sorts the array in
     * ascending order using the selection sort algorithm
     *
     * @return double array (after sort)
     */
    private double[] sortBySelectionSort(double[] array) {
        for (int i = 0; i < array.length - 1; i++) {
            // Find the minimum element in unsorted array
            int min_index = i;
            for (int j = i + 1; j < array.length; j++) {
                if (array[j] < array[min_index]) {
                    min_index = j;
                }
            }
            // Swap the found minimum element with the first element
            double temp = array[min_index];
            array[min_index] = array[i];
            array[i] = temp;
        }
        return array;
    }

    /**
     * This is a main method have an Object to call SelectionSort methods and
     * run the program.
     *
     * @param args command-line arguments.
     */
    public static void main(String[] args) {
        SelectionSort selectionSort = new SelectionSort();
        double[] array = selectionSort.createArray(); // create new array
        System.out.println("Unsorted array: " + Arrays.toString(array)); // array before sorting using selection sort
        selectionSort.sortBySelectionSort(array); // sort array by selection sort 
        System.out.println("Sorted array  : " + Arrays.toString(array)); // array after sorting using selection sort
    }
}
