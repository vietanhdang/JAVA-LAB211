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

import entity.Customer;
import entity.Fruit;
import entity.Order;
import java.util.ArrayList;
import java.util.Hashtable;

/**
 * The {@code ShopManager} class that can create new fruit products and store,
 * customize the quantity of products in the store, allow customers to purchase
 * products, and display successful customer orders
 *
 * @author Dang Viet Anh
 */
public class ShopManager {

    private final ArrayList<Fruit> fruitsList = new ArrayList<>();  // List contain fruit in shop
    private final InputReader inputReader = new InputReader();
    private final Hashtable<Integer, Order> orderList = new Hashtable<>();
    private int fruitId = 1; // default fruidId is 1
    private int orderId = 1; // default orderId is 1

    /**
     * This method allow owner can create new fruit in shop.
     */
    private void createFruit() {
        System.out.println("=========CREATE FRUIT=========");
        while (true) {
            displayFruits();
            String fruitName = inputReader.getInputString("Enter fruit name: ", 15);
            int quantity = inputReader.getInputInteger("Enter fruit quantity: ", 0);
            String origin = inputReader.getInputString("Enter fruit origin: ", 15);
            float price = inputReader.getInputFloat("Enter fruit price: ", 0);
            fruitsList.add(new Fruit(fruitId++, fruitName, quantity, origin, price));
            System.out.println("Fruit added successfully.");
            displayFruits();
            if (!inputReader.getInputYesNo("Do you want to continue adding fruit? (Y/N):")) {
                return;
            }
        }
    }

    /**
     * This method allow owner can update quantity and price of fruit in shop.
     */
    private void updateFruit() {
        System.out.println("=========UPDATE FRUIT=========");
        displayFruits();
        while (true) {
            int fruitID = inputReader.getInputInteger("Enter ID: ", 1);
            Fruit fruit = getFruitById(fruitID);
            if (fruit == null) {    // Fruit not found.
                if (!inputReader.getInputYesNo("Fruit not found, Do you want to create? (Y/N): ")) {
                    System.out.println("-----------------------------");
                    System.out.println("Create fruit canceled");
                    System.out.println("-----------------------------");
                    return;
                } else {
                    createFruit();  // Create new fruit
                    return;
                }
            }
            System.out.printf("|%-18s|%-14s|\n", " ++ Fruit Name ++", " + Quantity +");
            System.out.printf("| %-17s| %-13d|\n", fruit.getName(), fruit.getQuantity());
            fruit.setQuantity(inputReader.getInputInteger("Enter quantity: ", 0));
            System.out.println("-----------------------------");
            System.out.println("Update successfully");
            System.out.println("-----------------------------");
            if (!inputReader.getInputYesNo("Do you want to continue updating? (Y/N): ")) {
                return;
            }
        }
    }

    /**
     * This method displayMainScreen all fruit that exist in shop and fruit
     * information.
     */
    private void displayFruits() {
        if (fruitsList.isEmpty()) {
            System.out.println("There are no fruits in the shop!");
            return;
        }
        System.out.println("-------------------------LIST OF FRUITS-------------------------");
        System.out.printf("|%-10s|%-18s|%-14s|%-16s|%-15s|\n", "++ Item ++", " ++ Fruit Name ++ ", " + Quantity + ", " ++ Origin ++ ", " ++ Price ++ ");
        fruitsList.forEach((fruit) -> {
            fruit.displayFruit();
        });
    }

    /**
     * This method displayMainScreen all order that buyer add and total money of
     * products. If don't have order return to main screen.
     */
    private void viewOrders() {
        System.out.println("=========VIEW ORDERS=========");
        if (orderList.isEmpty()) {
            System.out.println("No orders found");
            return;
        }
        orderList.keySet().forEach((orderID) -> {
            Order order = orderList.get(orderID);
            System.out.println("Customer: " + order.getCustomer().getCustomer());
            displayOrders(orderList.get(orderID).getFruits());
        });
    }

    /**
     * This method display all order that exist in order list. Then print out
     * total amount all order worth.
     *
     * @param orders - array list contain orders.
     */
    private void displayOrders(ArrayList<Fruit> orders) {
        System.out.printf("|%-10s|%-15s|%-16s|%-13s|%-14s|\n", " ++ ID ++ ", " ++ Product ++ ", " ++ Quantity ++ ", " ++ Price ++ ", " ++ Amount ++");
        float total = 0;
        int orderID = 1;
        for (Fruit order : orders) {
            System.out.printf("| %-9d| %-14s| %-15d| $%-11.2f| $%-12.2f|\n", orderID++, order.getName(), order.getQuantity(),
                    order.getPrice(), order.getQuantity() * order.getPrice());
            total += order.getQuantity() * order.getPrice();
        }
        System.out.println("Total: $" + String.format("%.2f", total));
        System.out.println("----------------------");
    }

    /**
     * This method check for order exist in order list or not.
     *
     * @param orders - list contain all order user buy.
     * @param fruitId - id of fruit that use want to add.
     * @return true if order exist, otherwise return false.
     */
    private boolean checkOrderExist(ArrayList<Fruit> orders, int fruitId) {
        for (Fruit fruit : orders) {
            if (fruit.getFruitId() == fruitId) {
                return true;    // Order founded.
            }
        }
        return false;
    }

    /**
     * This method update an order if it exist before.
     *
     * @param orders - list contain all order user buy.
     * @param fruit - fruit that buyer added.
     * @param quantity - quantity of fruit buyer added.
     */
    private void updateOrder(ArrayList<Fruit> orders, Fruit fruit, int quantity) {
        for (Fruit order : orders) {
            if (order.getFruitId() == fruit.getFruitId()) {
                order.setQuantity(order.getQuantity() + quantity);  // Update quantity
            }
        }
    }

    /**
     * This method get a fruit in shop by it id.
     *
     * @param id - id of fruit want to find
     */
    private Fruit getFruitById(int id) {
        for (Fruit fruit : fruitsList) {
            if (fruit.getFruitId() == id) {
                return fruit;   // Fruit found
            }
        }
        return null;
    }

    /**
     * This method allow buyer add product to their cart.
     */
    private void shopping() {
        if (fruitsList.isEmpty()) {
            System.out.println("There are no fruits in the shop!");
            return;
        }
        ArrayList<Fruit> orders = new ArrayList<>();    // Contain all buyer order.
        while (true) {
            displayFruits();
            int item = inputReader.getInputId(fruitsList);
            Fruit fruit = getFruitById(item);
            // Check that fruit have quantity greater than 0
            if (fruit.getQuantity() == 0) {
                System.out.println("There are no other " + fruit.getName() + " in shop");
                if (!inputReader.getInputYesNo("Do you want to continue buying? (Y/N): ")) {
                    break;
                }
                continue;
            }
            int quantity = inputReader.getInputNumberInRange("Enter quantity: ", 1, fruit.getQuantity(),
                    "Shop has only " + fruit.getQuantity() + " " + fruit.getName() + " left");
            fruit.setQuantity(fruit.getQuantity() - quantity);      // Update fruit quantity after buyer buy.
            if (checkOrderExist(orders, fruit.getFruitId())) {
                updateOrder(orders, fruit, quantity);   // Order that exist before.
            } else {    // New order.
                orders.add(new Fruit(fruit.getFruitId(), fruit.getName(), quantity, fruit.getOrigin(), fruit.getPrice()));
            }
            if (!inputReader.getInputYesNo("Do you want to continue buying? (Y/N): ")) {
                break;
            }
        }
        if (orders.isEmpty()) {
            System.out.println("-----------------------------");
            System.out.println("CAN'T ORDER BECAUSE CART IS EMPTY!");
            System.out.println("-----------------------------");
        } else {
            System.out.println("------LIST OF FRUITS IN THE CART-----");
            displayOrders(orders);
            String buyerName = inputReader.getInputString("Enter name: ", 15);
            Order order = new Order(new Customer(buyerName), orders);
            orderList.put(orderId++, order);      // Add order to list.
            System.out.println("-----------------------------");
            System.out.println("Add order successfull");
            System.out.println("-----------------------------");
        }
    }

    /**
     * This method use to add sample fruit in shop.
     */
    private void addSampleData() {
        fruitsList.add(new Fruit(fruitId++, "Coconut", 20, "VietNam", 3.5f));
        fruitsList.add(new Fruit(fruitId++, "Orange", 10, "US", 2f));
        fruitsList.add(new Fruit(fruitId++, "Apple", 35, "Thailand", 2.5f));
        fruitsList.add(new Fruit(fruitId++, "Grape", 40, "France", 1.5f));
    }

    /**
     * This method display main screen and get user input option and call method
     * according user input.
     */
    private void displayMainScreen() {
        addSampleData(); // add sample data to fruitList
        while (true) {
            System.out.println("=========FRUIT SHOP SYSTEM=========");
            System.out.println("1. Create Fruit");
            System.out.println("2. Update Fruit");
            System.out.println("3. View orders");
            System.out.println("4. Shopping (for buyer)");
            System.out.println("0. Exit");
            int option = inputReader.getInputNumberInRange("Enter choice: ", 0, 4, "Choice must in [0-4]");
            switch (option) {
                case 1:
                    createFruit(); // create fruit
                    break;
                case 2:
                    updateFruit(); // update fruit
                    break;
                case 3:
                    viewOrders(); // view order
                    break;
                case 4:
                    shopping(); // shopping
                    break;
                default:
                    return; // exit
            }
        }
    }

    /**
     * This method create an object to call displayMainScreen method to run the
     * program.
     *
     * @param args command-line parameter.
     */
    public static void main(String[] args) {
        ShopManager shopManager = new ShopManager(); // Create object to call method.
        shopManager.displayMainScreen();
    }
}
