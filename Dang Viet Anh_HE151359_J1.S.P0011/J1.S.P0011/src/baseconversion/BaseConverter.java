/*
 * Copyright(C) 2021, Dang Viet Anh
 * Code: J1.S.P0011
 * Title: Base System Conversion. 
 *
 * Record of change:
 * DATE            Version             AUTHOR           DESCRIPTION
 * 2021-9-9         1.0                AnhDV          First Implement
 */
package baseconversion;

import java.math.BigInteger;

/**
 * The {@code BaseConverter} class contains methods, displayOptions
 * getInputNumberToDecimal, convertToDecimal, convertDecimalToBase,
 * printNumberAfterConvert used to display and convert numbers to decimal and
 * from decimal to other number systems
 *
 * @author Dang Viet Anh
 */
public class BaseConverter {

    private InputReader inputReader = new InputReader(); // Call InputReader class to use to get getUserInput data

    /**
     * This method will display the user's options and ask the user to enter the
     * selection.
     *
     * @param message notify user input
     * @return choice between 1 - 4
     */
    private int displayOptions(String message) {
        System.out.println(message);
        System.out.println("1. Binary");
        System.out.println("2. Octal");
        System.out.println("3. Decimal");
        System.out.println("4. Hexadecimal");
        return inputReader.getInputOption("Choose: ", 1, 4);
    }

    /**
     * This method will convert numbers in base 2,8,16 to base 10.
     *
     * @param inputNumber input value
     * @param base the base value
     * @return
     */
    private BigInteger convertToDecimal(String inputNumber, int base) {
        int length = inputNumber.length(); // get the length of input value
        BigInteger decimalNumber = new BigInteger("0"); // declare a decimal variable of type BigInteger
        int indexOfValue = 0; // index of the last digit
        if (base != 10) {
            while (length != 0) { // the loop stops until length = 0
                char character = inputNumber.charAt(length - 1);
                int value = (character) <= '9' ? (character - '0') : ((character - 'A') + 10);
                // if the rightmost character is less than or equal to 9 then subtract 48 to get the corresponding int number. If greater than 10, subtract 55
                decimalNumber = decimalNumber.add(BigInteger.valueOf(value).multiply((BigInteger.valueOf(base)).pow(indexOfValue++)));
                length -= 1; // reduce the length by 1
            }
        } else {
            decimalNumber = new BigInteger(inputNumber); // if the value is in base 10 then assign inputNumber to decimalNumber
        }
        return decimalNumber;
    }

    /**
     * This method requires the user to choose a base system for entering
     * numbers and convert it to base 10.
     *
     * @return BigInteger decimal number
     */
    private BigInteger getInputNumberToDecimal() {
        int inputChoice = displayOptions("===== Enter base input number =====");
        switch (inputChoice) {
            case 1:
                return convertToDecimal(inputReader.getInputBinaryNumber(), 2);
            case 2:
                return convertToDecimal(inputReader.getInputOctalNumber(), 8);
            case 3:
                return convertToDecimal(inputReader.getInputDecimalNumber(), 10);
            case 4:
                return convertToDecimal(inputReader.getInputHexadecimalNumber(), 16);
        }
        return null;
    }

    /**
     * This method will convert base 10 to base 2,8,16.
     *
     * @param decimalNumber decimal number
     * @param base the base system that you want to convert
     * @return string contains number after converted
     */
    private String convertDecimalToBase(BigInteger decimalNumber, int base) {
        StringBuilder result = new StringBuilder(); // save the remainder
        if (base != 10) {
            while (decimalNumber.compareTo(BigInteger.valueOf(0)) == 1) { // if decimal division is less than or equal to 0 then stop the loop
                if ((decimalNumber.mod(BigInteger.valueOf(base))).compareTo(BigInteger.valueOf(10)) < 0) {
                    result.append(decimalNumber.mod(BigInteger.valueOf(base)));
                } else {
                    BigInteger tempValue = decimalNumber.mod(BigInteger.valueOf(base)).add(BigInteger.valueOf(55));
                    result.append((char) (tempValue.intValue()));
                }
                decimalNumber = decimalNumber.divide(BigInteger.valueOf(base));
            }
        }
        result.reverse(); // after all is done we reverse the list
        return result.toString();
    }

    /**
     * This method will print the number after converting from base 10 to base
     * 2,8,16.
     */
    private void printNumberAfterConvert(BigInteger decimalNumber) {
        int inputChoice = displayOptions("===== Enter base output number =====");
        System.out.print("Number after convert: ");
        switch (inputChoice) {
            case 1:
                System.out.print(convertDecimalToBase(decimalNumber, 2)); // convert decimal to binary
                break;
            case 2:
                System.out.print(convertDecimalToBase(decimalNumber, 8)); // convert decimal to octal
                break;
            case 3:
                System.out.print(decimalNumber);
                break;
            case 4:
                System.out.print(convertDecimalToBase(decimalNumber, 16)); // convert decimal to hexa
                break;
        }
        System.out.println("");
    }

    /**
     * This is a main method have an Object to call BaseConverter methods and
     * run the program.
     *
     * @param args command-line arguments.
     */
    public static void main(String[] args) {
        BaseConverter baseConverter = new BaseConverter(); // call class BaseConverter
        BigInteger decimalNumber = baseConverter.getInputNumberToDecimal(); // enter numbers and convert to base 10
        baseConverter.printNumberAfterConvert(decimalNumber); // print out the number after converting from base 10 to other bases
    }

}
