/*
 * Copyright(C) 2021, Dang Viet Anh
 * Code: J1.S.H211
 * Title: Bubble sort algorithm. 
 *
 * Record of change:
 * DATE            Version             AUTHOR           DESCRIPTION
 * 2021-9-9         1.0                AnhDV          First Implement
 */

package bubblesort;

import java.util.Arrays;
import java.util.Random;
import java.util.Scanner;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 * The {@code BubbleSort} class contains methods that can be used that get
 * random values in a user-entered range and sort them in ascending order.
 *
 * @author Dang Viet Anh
 */
public class BubbleSort {

    /**
     * declare a final variable LOGGER to use logging for {@code BubbleSort}
     * class
     */
    private static final Logger LOGGER = Logger.getLogger(BubbleSort.class.getName());

    /**
     * This method input number from keyboard
     *
     * @param msg notification message 
     * @param checkPositive if is true: positive integer number
     * @return integer number
     */
    private int getInputNumber(String msg, boolean checkPositive) {
        Scanner scanner = new Scanner(System.in);
        while (true) {
            try {
                System.out.print(msg);
                int number = Integer.parseInt(scanner.nextLine().trim());
                if (checkPositive == true && number > 0) {
                    return number;
                }
                if (checkPositive == false) {
                    return number;
                }
                LOGGER.log(Level.INFO, "Please input a valid number: ");
            } catch (NumberFormatException e) {
                LOGGER.log(Level.WARNING, "Please input a valid number: ");
            }
        }
    }

    /**
     * This method will generate random elements from the range entered by the
     * user from the keyboard and returns a list of integers within that range
     *
     * @param range (size of array)
     * @return integer type list
     */
    private int[] getRandomArray(int range) {
        int[] list = new int[range]; // list store integer number
        Random getRandomNumber = new Random();
        int minNumber = getInputNumber("Enter min number: ", false);
        while (true) {
            int maxNumber = getInputNumber("Enter max number: ", false);
            if (minNumber <= maxNumber) {
                for (int i = 0; i < range; i++) {
                    list[i] = getRandomNumber.nextInt(maxNumber - minNumber) + minNumber;
                    // generate random integer from min to max
                }
                break;
            } else {
                // if minNumber > maxNumber then go back and re-enter
                LOGGER.warning("Min number > Max number, re-enter");
            }
        }
        return list;
    }

    /**
     * This method will sort and print the elements in the array in ascending
     * order using the bubble sort algorithm
     *
     * @param list (integer type array)
     */
    private void printBubbleSort(int[] list) {
        System.out.println("Unsorted array: " + Arrays.toString(list));
        for (int i = 0; i < list.length - 1; i++) {                    
            for (int j = 0; j < list.length -i - 1; j++) {
                if (list[j] > list[j + 1]) {
                    int temp = list[j];
                    list[j] = list[j + 1];
                    list[j + 1] = temp;
                }
            }
        }
        System.out.println("Sorted array  : " + Arrays.toString(list));
    }

    /**
     * This is main method to run the program
     *
     * @param args command-line arguments.
     */
    public static void main(String[] args) {
        BubbleSort bubbleSort = new BubbleSort();
        int range = bubbleSort.getInputNumber("Enter number of array: ", true); // input size of array
        int[] list = bubbleSort.getRandomArray(range); // Initialize array of random integers
        bubbleSort.printBubbleSort(list); // call printSortBubble(list)
    }

}
