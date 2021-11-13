/*
 * Copyright(C) 2021, Dang Viet Anh
 * Code: J1.S.P0023
 * Title: Fruit Shop Manager
 *
 * Record of change:
 * DATE            Version             AUTHOR           DESCRIPTION
 * 2021-10-10         1.0               AnhDV          First Implement
 */
package entity;

/**
 * The {@code Fruit} class contain information of Fruits. Those information
 * include fruitId, name, quantity, origin and price.
 *
 * @author Dang Viet Anh
 */
public class Fruit {

    private int fruitId;    // Fruit identification
    private String name;    // Name of fruit
    private int quantity;   // Quantity of fruit
    private String origin;  // Origin of fruit
    private float price;   // Price of fruit

    /**
     * This is constructor with out parameter.
     */
    public Fruit() {
    }

    /**
     * Constructs a new Fruit whose information are specified by the parameter.
     *
     * @param fruitId - Id of fruit.
     * @param name - name of fruit.
     * @param quantity - quantity of fruit.
     * @param origin - origin of fruit.
     * @param price - price of fruit.
     */
    public Fruit(int fruitId, String name, int quantity, String origin, float price) {
        this.fruitId = fruitId;
        this.name = name;
        this.quantity = quantity;
        this.origin = origin;
        this.price = price;
    }

    /**
     * This method get fruitId of fruit and return it.
     *
     * @return id of fruit
     */
    public int getFruitId() {
        return fruitId;
    }

    /**
     * This method get fruit name and return it.
     *
     * @return String that is fruit name.
     */
    public String getName() {
        return name;
    }

    /**
     * This method get fruit origin and return it.
     *
     * @return String that is fruit origin.
     */
    public String getOrigin() {
        return origin;
    }

    /**
     * This method get fruit price and return it.
     *
     * @return real number that is fruit price.
     */
    public float getPrice() {
        return price;
    }

    /**
     * This method use to set fruit price.
     *
     * @param price price want to set
     */
    public void setPrice(float price) {
        this.price = price;
    }

    /**
     * This method get fruit quantity and return it.
     *
     * @return an integer number that is fruit quantity.
     */
    public int getQuantity() {
        return quantity;
    }

    /**
     * This method use to set fruit quantity.
     *
     * @param quantity quantity want to set
     */
    public void setQuantity(int quantity) {
        this.quantity = quantity;
    }

    public void displayFruit() {
        System.out.println(String.format("| %-9d| %-17s| %-13d| %-15s| $%-13.2f|", fruitId, name, quantity, origin, price));
    }
}
