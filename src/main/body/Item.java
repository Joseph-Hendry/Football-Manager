package main.body;

import java.util.Random;

/**
 * This class implements an Item 
 * This stores a name, price and bonuses.
 */
public class Item {
    private String name;
    private int value;
    private int[] stats = new int[3];
    private String rarity;

    /**
     * This is the constructor for the Item class.
     * 
     * @param name		The name of the item.
     * @param price 	The price of the item.
     * @param stats 	The bonuses of the item.
     * @param rarity	The rarity of the item. 
     */
    public Item(String name, int value, int[] stats, String rarity) {
        this.name = name;
        this.value = value;
        this.stats = stats;
        this.rarity = rarity;
    }

    /**
	 * Creates and returns a random Item with the given rarity.
     * 
	 * @param rarity    The rarity of the Item.
	 * @return          The random Item with the given rarity.
     * 
     * Using: Create a random item with the given rarity string.
	 */
	public static Item createRandomItem(String rarity) {

		Random random = new Random();

		// Generate the name of the Item (Item + random number)
		String name = "Item" + random.nextInt(1000);

		// Generate the value and stats of the Item based on the rarity
		int value = 6;
		int[] stats = new int[3];

		if (rarity == "Bronze") {
			// If the rarity is bronze the rating is between 40 - 60 and the value is between 400 - 600
			value = random.nextInt(200) + 400;
			stats[0] = random.nextInt(20) + 40;
			stats[1] = random.nextInt(20) + 40;
			stats[2] = random.nextInt(20) + 40;

		} else if (rarity == "Silver") {
			// If the rarity is silver the rating is between 60 - 80 and the value is between 600 - 800
			value = random.nextInt(200) + 600;
			stats[0] = random.nextInt(20) + 60;
			stats[1] = random.nextInt(20) + 60;
			stats[2] = random.nextInt(20) + 60;

		} else if (rarity == "Gold") {
			// If the rarity is gold the rating is between 80 - 90 and the value is between 800 - 900
			value = random.nextInt(100) + 800;
			stats[0] = random.nextInt(10) + 80;
			stats[1] = random.nextInt(10) + 80;
			stats[2] = random.nextInt(10) + 80;

		} else {
			// If the rarity is platinum the rating is between 90 - 100 and the value is between 900 - 1000
			value = random.nextInt(100) + 900;
			stats[0] = random.nextInt(10) + 90;
			stats[1] = random.nextInt(10) + 90;
			stats[2] = random.nextInt(10) + 90;
		}

		// Return the new Item
		return new Item(name, value, stats, rarity);
	}

    /**
     * Returns a string representation of the Item.
     * @return  The string representation of the Item.
     */
    public String toString() {
        return this.name + " " + this.value + " " + this.stats[0] + " " + this.stats[1] + " " + this.stats[2];
    }

    /**
     * This method is used to get the name of the item.
     * @return  The name of the item.
     */
    public String getName() {
        return this.name;
    }

    /**
     * This method is used to get the price of the item.
     * @return The price of the item.
     */
    public int getValue() {
        return this.value;
    }

    /**
     * This method is used to get the bonuses of the item.
     * @return The bonuses of the item.
     */
    public int[] getStats() {
        return this.stats;
    }
     /**
      * This method returns the rarity of the item.
      * @return The rarity of the item.
      */
    public String getRarity() {
    	return this.rarity;
    }
}
