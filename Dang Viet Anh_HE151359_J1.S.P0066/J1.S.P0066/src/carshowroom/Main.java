/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package carshowroom;

import java.util.ArrayList;
import java.util.Scanner;

/**
 *
 * @author Benjamin
 */
public class Main {

    private Scanner scanner = new Scanner(System.in);

    private String getInputString(String message) throws ExceptionCar {
        while (true) {
            System.out.print(message);
            String result = scanner.nextLine();
            if (!result.isEmpty()) {
                return result;
            }
            throw new ExceptionCar("Not empty");
        }
    }
    
    private int getInputIntPrice(String message) throws ExceptionCar {
        while (true) {
            try {
                System.out.print(message);
                int result = Integer.parseInt(scanner.nextLine());
                if (result > 0) {
                    return result;
                }
                throw new ExceptionCar("Price greater than zero");
            } catch (NumberFormatException ex) {
                throw new ExceptionCar("Not valid price. Enter again!");
            }

        }
    }

    private boolean checkYN(String message) throws ExceptionCar {
        while (true) {
            String result = getInputString(message);
            if (result.equalsIgnoreCase("Y")) {
                return true;
            } else if (result.equalsIgnoreCase("N")) {
                return false;
            } else {
                System.err.println("Re-input");
            }
        }
    }

    private boolean checkCarExist(Car car, String color, int price,
            String today) throws ExceptionCar {
        boolean check = false;
        String[] listColor = car.getColor();
        int[] listPrice = car.getPrice();
        String[] listSold = car.getSoldOn();
        if (!color.equalsIgnoreCase("no color")) {
            for (int i = 0; i < listColor.length; i++) {
                if (color.equalsIgnoreCase(listColor[i])) {
                    check = true;
                    break;
                }
            }
        } else {
            check = true;
        }
        if (check == false) {
            throw new ExceptionCar("Color Car does not exist");
        }
        check = false;
        for (int i = 0; i < listPrice.length; i++) {
            if (price == listPrice[i] - 100) {
                check = true;
            }
        }
        if (check == false) {
            throw new ExceptionCar("Price Car does not exist");
        }
        check = false;
        for (int i = 0; i < listSold.length; i++) {
            if (today.equalsIgnoreCase(listSold[i])) {
                check = true;
            }
        }
        if (check == false) {
            throw new ExceptionCar("Car can't sell today");
        }
        return true;
    }

    private void addCar(ArrayList<Car> lc) {
        String[] listColorAudi = {"White", "Yellow", "Orange"};
        int[] listPriceAudi = {5500, 3000, 4500};
        String[] listSoldDayAudi = {"Friday", "Sunday", "Monday"};
        lc.add(new Car("Audi", listColorAudi, listPriceAudi, listSoldDayAudi));

        String[] listColorMercedes = {"Green", "Blue", "Purple"};
        int[] listPriceMercedes = {5000, 6000, 8500};
        String[] listSoldDayMercedes = {"TueDay", "Saturday", "Wednesday"};
        lc.add(new Car("Mercedes", listColorMercedes, listPriceMercedes,
                listSoldDayMercedes));

        String[] listColorBMW = {"Pink", "Red", "Brown"};
        int[] listPriceBMW = {2500, 3000, 3500};
        String[] listSoldDayBMW = {"Monday", "Sunday", "Thurday"};
        lc.add(new Car("BMW", listColorBMW, listPriceBMW, listSoldDayBMW));
    }

    private boolean checkNameCar(ArrayList<Car> lc, String name, String color,
            int price, String today) throws ExceptionCar {
        boolean check = false;
        for (int i = 0; i < lc.size(); i++) {
            if (name.equalsIgnoreCase(lc.get(i).getNameCar())) {
                if (checkCarExist(lc.get(i), color, price, today)) {
                    System.out.println("Sell Car");
                    if (!checkYN("Do you want find more?(Y/N): ")) {
                        return false;
                    }
                } else {
                    System.out.println("Car break");
                }
                check = true;
                break;
            }
        }
        if (check == false) {
            throw new ExceptionCar("Can't find car.");
        }
        return true;
    }

    private void display() throws ExceptionCar {
        ArrayList<Car> lc = new ArrayList<>();
        addCar(lc);
        System.out.println("Input information of car");
        while (true) {
            String name = getInputString("Name: ");
            String color = getInputString("Color: ");
            int price = getInputIntPrice("Price: ");
            String today = getInputString("Today: ");
            if (!checkNameCar(lc, name, color, price, today)) {
                return;
            }
        }
    }

    public static void main(String[] args) throws ExceptionCar {
        Main a = new Main();
        a.display();
    }
}
