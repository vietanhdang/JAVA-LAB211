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

import java.util.ArrayList;

/**
 * The {@code Order} class contain an array list contain fruit that buyer
 * choose.
 *
 * @author Dang Viet Ạnh
 */
public class Order {

    private Customer customer;  // customer object 
    private ArrayList<Fruit> fruits; // list containing fruit objects

    /**
     * This is constructor with out parameter.
     */
    public Order() {
    }

    /**
     * Constructs a new Customer whose information are specified by the
     *
     * @param customer - Object contain orders.
     * @param fruits - list of fruit.
     */
    public Order(Customer customer, ArrayList<Fruit> fruits) {
        this.customer = customer;
        this.fruits = fruits;
    }

    /**
     * This method get customer object and return it.
     *
     * @return customer objects
     */
    public Customer getCustomer() {
        return customer;
    }

    /**
     * This method get list of fruit and return it.
     *
     * @return list of fruit
     */
    public ArrayList<Fruit> getFruits() {
        return fruits;
    }
}
