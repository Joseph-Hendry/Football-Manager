package main.body;

import java.util.ArrayList;
import java.util.Random;

/**
 * This class is used to create a store object.
 * It contains the players, coach, and items in the store.
 */
public class Store {
    private ArrayList<Player> storePlayers = new ArrayList<Player>();
    private Coach storeCoach;
    private ArrayList<Item> storeItems = new ArrayList<Item>();

    /**
     * This is the constructor for the Store class.
     * @param storePlayers The players in the store.
     * @param storeCoach   The coach in the store.
     * @param storeItems   The items in the store.
     */
    public Store(ArrayList<Player> storePlayers, Coach storeCoach, ArrayList<Item> storeItems) {
        this.storePlayers = storePlayers;
        this.storeCoach = storeCoach;
        this.storeItems = storeItems;
    }

    /**
     * This method is used to get the players in the store.
     * @return The players in the store.
     */
    public ArrayList<Player> getStorePlayers() {
        return storePlayers;
    }

    /**
     * This method is used to get the coach.
     * @return The coach.
     */
    public Coach getStoreCoach() {
        return storeCoach;
    }
    
    /**
     * Removes the coach from the store.
     */
    public void removeCoach() {
    	this.storeCoach = null;
    }
    
    /**
     * Removes a player from the store.
     * @param player	The player to be removed.
     */
    public void removePlayer(Player player) {
    	if (this.getStorePlayers().contains(player)) {
    		this.storePlayers.remove(player);
    	} else {
    		System.out.println("This player is not in the store");
    	}
    }

    /**
     * This method is used to get the items in the store.
     * @return The items in the store.
     */
    public ArrayList<Item> getStoreItems() {
        return storeItems;
    }

    /**
     * This method freshes the items in the store
     */
    public void refeshStore(String rarity) {
        storePlayers.clear();
        for (AvailablePositions position : AvailablePositions.values()) {
            Player teamPlayer = Player.createRandomPlayer(rarity, position);
            storePlayers.add(teamPlayer);
        }

        storeCoach = Coach.createRandomCoach(rarity);

        storeItems.clear();
        for (int i = 0; i < 5; i++) {
            Item item = Item.createRandomItem(rarity);
            storeItems.add(item);
        }
    }
    
    /**
     * Removes and item from the items available in the shop if the item is in the shop.
     * @param item	The item you want to remove.
     */
    public void removeItem(Item item) {
    	if (this.storeItems.contains(item)) {
    		this.storeItems.remove(item);
    	} else {
    		System.out.println("Item is not in the list of store items.");
    	}
    }
    
    
    
    /**
     * This function creates a random rarity to be used when creating new objects that have rarity.
     * @return
     */
    public static String randomRarity() {
    	String rarity;
    	Random  random = new Random();
    	int rarityPercent = random.nextInt(100);
    	if (rarityPercent <= 50) {
    		rarity = "Bronze";
    	} else if (50 < rarityPercent && rarityPercent <= 80) {
    		rarity = "Silver";
    	} else if (80 < rarityPercent && rarityPercent <= 95) {
    		rarity = "Gold";
    	} else {
    		rarity = "Platinum";
    	}
    	return rarity;
    }

    /**
     * This method generates a random basic store with rarity Bronze.
     * @return A random basic store.
     */
    public static Store createStore() {
    	String rarity;
    	
        ArrayList<Player> storePlayers = new ArrayList<Player>();
        Coach storeCoach;
        ArrayList<Item> storeItems = new ArrayList<Item>();

        // Creates a onTeam of random players including position.
        for (AvailablePositions position : AvailablePositions.values()) {
        	rarity = randomRarity();
            Player teamPlayer = Player.createRandomPlayer(rarity, position);
            storePlayers.add(teamPlayer);
        }
    
        storeCoach = Coach.createRandomCoach(randomRarity());
            
        for (int i = 0; i < 5; i++) {
            Item item = Item.createRandomItem(randomRarity());
            storeItems.add(item);
        }

        return new Store(storePlayers, storeCoach, storeItems);
    }
}
