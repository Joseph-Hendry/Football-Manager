package main;

import java.util.ArrayList;

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
        for (int i = 0; i < 5; i++) {
            Player player = Player.createRandomPlayer(rarity);
            storePlayers.add(player);
        }

        storeCoach = Coach.createRandomCoach(rarity);

        storeItems.clear();
        for (int i = 0; i < 5; i++) {
            Item item = Item.createRandomItem(rarity);
            storeItems.add(item);
        }
    }

    /**
     * This method generates a random basic store with rarity Bronze.
     * @return A random basic store.
     */
    public static Store createStore() {
        String rarity = "Bronze";
        ArrayList<Player> storePlayers = new ArrayList<Player>();
        Coach storeCoach;
        ArrayList<Item> storeItems = new ArrayList<Item>();

        for (int i = 0; i < 5; i++) {
            Player player = Player.createRandomPlayer(rarity);
            storePlayers.add(player);
        }
    
        storeCoach = Coach.createRandomCoach(rarity);
            
        for (int i = 0; i < 5; i++) {
            Item item = Item.createRandomItem(rarity);
            storeItems.add(item);
        }

        return new Store(storePlayers, storeCoach, storeItems);
    }
}
