/*
 * Copyright(C) 2021, Dang Viet Anh
 * Code: J1.S.H209
 * Title: Count Coins
 *
 * Record of change:
 * DATE            Version             AUTHOR           DESCRIPTION
 * 2021-9-9         1.0                AnhDV          First Implement
 */
package coin;

import java.io.File;
import java.io.IOException;
import java.io.RandomAccessFile;
import java.util.Scanner;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 * The {@code CountCoin} class. This class contains methods that can be used
 * add up the cash values of all the coins and print the total money at the end
 * order.
 * <p>
 * Bugs: Still don't have it or not yet know it
 *
 * @author Dang Viet Anh
 */
public class CountCoin {

    /**
     * Declare a final variable LOGGER to use logging for class
     * {@code CountCoin}
     */
    private static final Logger LOGGER = Logger.getLogger(CountCoin.class.getName());

    /**
     * This method requires the user to enter the file path from the keyboard
     *
     * @return string path to file
     */
    private String enterPath() {
        Scanner scanner = new Scanner(System.in);
        System.out.print("Enter path of file: ");
        String path = scanner.nextLine(); // enter path file from keyboard
        return path;
    }

    /**
     * This method takes a path to the file and returns the total amount
     *
     * @param pathFile path to file data.
     * @exception IOException if an I/O error occurs.
     */
    private String countCoins(String pathFile) throws IOException {
        File g = new File(pathFile);
        if (!g.exists()) {
            // if the file does not exist then do nothing
            LOGGER.log(Level.WARNING, "The file " + "{0}" + " does not exist!", pathFile);
        } else {
            RandomAccessFile f = new RandomAccessFile(pathFile, "r"); // open file
            String line;   // read line until empty
            StringBuilder tempStr = new StringBuilder(); // store all the words retrieved in the file on one line
            while ((line = f.readLine()) != null) {
                /* add all words to tempStr and add a space character to separate the lines*/
                tempStr.append(line.trim().toLowerCase()).append(" ");
            }
            double totalCoin = 0; // store total coin
            try {
                /* Store value, and name in 2 parallel arrays (normalized to remove extra spaces) */
                String[] pairCoin = tempStr.toString().trim().split("\\s+");
                for (int i = 0; i < pairCoin.length; i += 2) {
                    String temp = pairCoin[i + 1];
                    if (temp.contentEquals("pennies")) {
                        totalCoin += Double.parseDouble(pairCoin[i]) * 1;
                    } else if (temp.contentEquals("nickels")) {
                        totalCoin += Double.parseDouble(pairCoin[i]) * 5;
                    } else if (temp.contentEquals("dimes")) {
                        totalCoin += Double.parseDouble(pairCoin[i]) * 10;
                    } else if (temp.contentEquals("quarters")) {
                        totalCoin += Double.parseDouble(pairCoin[i]) * 25;
                    } else {
                        LOGGER.log(Level.WARNING, "Wrong input , the input consists of a series of pairs of token. Ex (1 pennies) is right");
                        totalCoin = -1;
                        break;
                    }
                }
            } catch (Exception e) {
                // if data in files are not pairs then print an error
                LOGGER.log(Level.WARNING, "Wrong input , the input consists of a series of pairs of token. Ex (1 pennies) is right");
            }
            if (totalCoin >= 0) { // if total coins >0 then add dollar symbol and return result
                LOGGER.log(Level.INFO, valueToString(totalCoin / 100));
                return valueToString(totalCoin / 100.0);
            }
        }
        return null;
    }

    /**
     * This method takes as input a double value of money and then adds a $
     * character to the beginning
     *
     * @param value value of money
     * @return string
     */
    private String valueToString(double value) {
        String result = value + ""; // convert doublr to string 
        /* if double value is 1.3 -> add 0 to end to 1.30*/
        if (result.indexOf(".") == result.length() - 2) {
            result += "0";
        }
        result = "$" + result;
        return result;
    }

    /**
     * This is main method to run the program
     *
     * @param args
     * @throws java.lang.Exception
     */
    public static void main(String[] args) throws Exception {
        CountCoin getCoin = new CountCoin(); // initialize class CountCoin to use
        String pathfile = getCoin.enterPath(); // input pathfile from keyboard
        getCoin.countCoins(pathfile); // count total coint
    }
}
