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
 * The {@code Customer} class contains buyer name information
 *
 * @author Dang Viet Anh
 */
public class Customer {

    private String name;

    /**
     * This is constructor with out parameter.
     */
    public Customer() {
    }

    /**
     * This is the method of passing name parameters to the constructor
     *
     * @param name - name of customer.
     */
    public Customer(String name) {
        this.name = name;
    }

    /**
     * Get the name of the fruit buyer.
     *
     * @return string is customer name.
     */
    public String getCustomer() {
        return name;
    }
}
