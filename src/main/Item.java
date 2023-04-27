package main;

import java.util.ArrayList;

/**
 * This class implements an Item 
 * This stores a name, price and bonuses.
 */
public class Item {
    private String name;
    private int price;
    private ArrayList<Integer> bonuses = new ArrayList<Integer>();

    /**
     * This is the constructor for the Item class.
     * @param name The name of the item.
     * @param price The price of the item.
     * @param bonuses The bonuses of the item.
     */
    public Item(String name, int price, ArrayList<Integer> bonuses) {
        this.name = name;
        this.price = price;
        this.bonuses = bonuses;
    }

    /**
     * This method is used to get the name of the item.
     * @return The name of the item.
     */
    public String getName() {
        return this.name;
    }

    /**
     * This method is used to get the price of the item.
     * @return The price of the item.
     */
    public int getPrice() {
        return this.price;
    }

    /**
     * This method is used to get the bonuses of the item.
     * @return The bonuses of the item.
     */
    public ArrayList<Integer> getBonuses() {
        return this.bonuses;
    }
}
