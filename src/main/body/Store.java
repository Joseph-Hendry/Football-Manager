package main.body;

import java.util.ArrayList;
import java.util.Random;

/**
 * This class is used to create a store object.
 * It contains the players, coach, and items in the store.
 */
public class Store {

    // The players in the store
    private ArrayList<Player> storePlayers = new ArrayList<Player>();

    // The coach in the store
    private Coach storeCoach;

    // The items in the store
    private ArrayList<Item> storeItems = new ArrayList<Item>();

    // The draft coaches
    private ArrayList<Coach> draftCoaches = new ArrayList<Coach>();

    // The availability of the coach
    private boolean coachAvailability;


    ////////// Constructor //////////


    /**
     * This is the constructor for the Store class.
     * 
     * @param storePlayers The players in the store.
     * @param storeCoach The coach in the store.
     * @param storeItems The items in the store.
     */
    public Store(ArrayList<Player> storePlayers, Coach storeCoach, ArrayList<Item> storeItems, ArrayList<Coach> draftCoaches) {
        this.storePlayers = storePlayers;
        this.storeCoach = storeCoach;
        this.storeItems = storeItems;
        this.coachAvailability = true;
        this.draftCoaches = draftCoaches;
    }


    ////////// Getters and Setters //////////


    /**
     * This method is used to get the players in the store.
     * 
     * @return The players in the store.
     */
    public ArrayList<Player> getStorePlayers() {
        return storePlayers;
    }

    /**
     * This method is used to get the coach.
     * 
     * @return The coach.
     */
    public Coach getStoreCoach() {
        return storeCoach;
    }

    /**
     * This method is used to get the items in the store.
     * 
     * @return The items in the store.
     */
    public ArrayList<Item> getStoreItems() {
        return storeItems;
    }
    
    /**
     * Returns whether the coach is available to sell or not.
     * 
     * @return Bool of whether the coach can be bought.
     */
    public boolean getCoachAvailable() {
    	return this.coachAvailability;
    }

    /**
     * Sets whether the coach is available to buy or not.
     * 
     * @param available Bool of whether the coach can be bought.
     */
    public void setCoachAvailable(boolean available) {
    	this.coachAvailability = available;
    }

    /**
     * Get the draft coaches.
     * 
     * @return The draft coaches.
     */
    public ArrayList<Coach> getDraftCoaches() {
        return draftCoaches;
    }


    ////////// Removers //////////


    /**
     * Removes a player from the store.
     * 
     * @param player The player to be removed.
     * @throws IllegalArgumentException If the player is not in the store.
     */
    public void removePlayer(Player player) throws IllegalArgumentException {
    	if (this.getStorePlayers().contains(player)) {
    		this.storePlayers.remove(player);
    	} else {
    		throw new IllegalArgumentException("This player is not in the store");
    	}
    }
    
    /**
     * Removes and item from the items available in the shop if the item is in the shop.
     * 
     * @param item  The item you want to remove.
     * @throws IllegalArgumentException If the item is not in the store.
     */
    public void removeItem(Item item) throws IllegalArgumentException {
    	if (this.storeItems.contains(item)) {
    		this.storeItems.remove(item);
    	} else {
    		throw new IllegalArgumentException("This item is not in the store");
    	}
    }


    ////////// Store Creation/Regeneration //////////


    /**
     * This method is used to create a random draft store.
     * 
     * @param intRarity The rarity of the items in the store.
     */
    public static Store createDraftStore(int intRarity) {
        Random random = new Random();
        int variety;
        int rarity;
        int coachRarity;

        // Creates empty lists
        ArrayList<Player> storePlayers = new ArrayList<Player>();
        ArrayList<Coach> draftCoaches = new ArrayList<Coach>();

        // Creates a onTeam of random players for each position.
        int i = 0;
        for (AvailablePositions position : AvailablePositions.values()) {
            for (int j = 0; j < Team.getFormation()[i] + 2; j++) {
                variety = random.nextInt(30) - 15; 
                rarity = intRarity + variety >= 0 ? intRarity + variety : 0;
                Player teamPlayer = Player.createRandomPlayer(Team.getStrRarity(rarity), position);
                storePlayers.add(teamPlayer);
            }
            i++;
        }

        // Creates 5 random coaches
        for (int j = 0; j < 5; j++) {
            variety = random.nextInt(30) - 15;
<<<<<<< Updated upstream
            coachRarity = intRarity + variety;
            draftCoaches.add(Coach.createRandomCoach(Team.getStrRarity(coachRarity)));
=======
            coahcrarity = intRarity + variety >= 0 ? intRarity + variety : 0;
            draftCoaches.add(Coach.createRandomCoach(Team.getStrRarity(coahcrarity)));
>>>>>>> Stashed changes
        }

        // Returns the store
        return new Store(storePlayers, null, null, draftCoaches);
    }

    /**
     * This method freshes the items in the store.
     * 
     * @param intRarity The rarity of the items in the store.
     */
    public void refreshStore(int intRarity) {
        Random random = new Random();
        int variety;
        int rarity;

        // Clear the store
        storePlayers.clear();

        // Creates a onTeam of random players for each position.
        for (AvailablePositions position : AvailablePositions.values()) {
            variety = random.nextInt(20) - 10; 
        	rarity = intRarity + variety >= 0 ? intRarity + variety : 0;
            Player teamPlayer = Player.createRandomPlayer(Team.getStrRarity(rarity), position);
            storePlayers.add(teamPlayer);
        }

        // Creates a random coach
        variety = random.nextInt(20) - 10;
<<<<<<< Updated upstream
        int coachRarity= intRarity + variety;
        storeCoach = Coach.createRandomCoach(Team.getStrRarity(coachRarity));
=======
        int coahcrarity = intRarity + variety >= 0 ? intRarity + variety : 0;
        storeCoach = Coach.createRandomCoach(Team.getStrRarity(coahcrarity));
>>>>>>> Stashed changes
        this.coachAvailability = true;

        // Creates 5 random items
        storeItems.clear();
        for (int i = 0; i < 5; i++) {
            variety = random.nextInt(20) - 10;
            rarity = intRarity + variety >= 0 ? intRarity + variety : 0;
            Item item = Item.createRandomItem(Team.getStrRarity(rarity));
            storeItems.add(item);
        }
    }

    /**
     * This method generates a random basic store with rarity Bronze.
     * 
     * @return A random basic store.
     */
    public static Store createStore(int intRarity) {
    	Random random = new Random();
        int variety;
        int rarity;

        // Initialise the store variables
    	Coach storeCoach;
        ArrayList<Player> storePlayers = new ArrayList<Player>();
        ArrayList<Item> storeItems = new ArrayList<Item>();

        // Creates a onTeam of random players including position.
        for (AvailablePositions position : AvailablePositions.values()) {
        	variety = random.nextInt(20) - 10; 
        	rarity = intRarity + variety >= 0 ? intRarity + variety : 0;
            Player teamPlayer = Player.createRandomPlayer(Team.getStrRarity(rarity), position);
            storePlayers.add(teamPlayer);
        }
    
        // Creates a random coach
        variety = random.nextInt(20) - 10; 
        
        rarity = intRarity + variety >= 0 ? intRarity + variety : 0;
        storeCoach = Coach.createRandomCoach(Team.getStrRarity(rarity));
        
        // Creates 5 random items
        for (int i = 0; i < 5; i++) {
            variety = random.nextInt(20) - 10; 
        	rarity = intRarity + variety >= 0 ? intRarity + variety : 0;
            Item item = Item.createRandomItem(Team.getStrRarity(rarity));
            storeItems.add(item);
        }

        // Returns the store
        return new Store(storePlayers, storeCoach, storeItems, null);
    }
}
