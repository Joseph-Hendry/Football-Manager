package main.body;
import java.util.ArrayList;

import main.UI.*;

/**
 * This class is used to create a game manager object.
 * 
 * The {@link GameManager} is the main class of the game.
 * This class acts as a facade for the {@link GameManagerUI}.
 */
public class GameManager {

	// The UI interface for the {@link GameManager}.
	protected final GameManagerUI UI;

	// The game variables
	protected int difficulty;
	protected int seasonLength;
	protected int money;
	protected int week;
	protected String teamName;

	// The players team
	protected Team playersTeam;

	// The games draft store
	protected Store drafStore;

	// The games store
	protected Store store;

	// The games stadium
	protected Stadium stadium;


	////////// Constructor //////////


	/**
	 * This is the constructor for the {@link GameManager} class.
	 * 
	 * @param ui The UI interface for the game.
	 */
	public GameManager(GameManagerUI ui) {
		this.UI = ui;
	}

	/**
	 * This method is used to start the game.
	 */
	public void start() {
		UI.setup(this);
	}


	////////// Getters and Setters //////////


	/**
	 * This method is used to get the difficulty of the game.
	 * 
	 * @return The difficulty of the game.
	 */
	public int getDifficulty() {
		return this.difficulty;
	}
	
	/**
	 * This method is used to get the length of the season.
	 * 
	 * @return The length of the season.
	 */
	public int getSeasonLength() {
		return this.seasonLength;
	}

	/**
	 * This method is used to get the money of the game.
	 * 
	 * @return The money of the game.
	 */
	public int getMoney() {
		return this.money;
	}
	
	/**
	 * This method is used to set the money of the game.
	 * 
	 * @param amount The amount of money to set.
	 */
	public void setMoney(int amount) {
		this.money = amount;
	}

	/**
	 * This method is used for incriment the money of the game.
	 * 
	 * @param amount The amount of money to incriment by.
	 */
	public void incMoney (int amount) {
		this.money += amount;
	}

	/**
	 * This method is used for decriment the money of the game.
	 * 
	 * @param amount The amount of money to decriment by.
	 */
	public void decMoney (int amount) {
		this.money -= amount;
	}

	/**
	 * This method is used to get the week of the game.
	 * 
	 * @return The week of the game.
	 */
	public int getWeek() {
		return this.week;
	}

	/**
	 * This method is used to get the player team.
	 * 
	 * @return The players team.
	 */
	public Team getPlayerTeam() {
		return this.playersTeam;
	}

	/**
	 * This method is used to get the draft store.
	 * 
	 * @return The draft store.
	 */
	public Store getDraftStore() {
		return this.drafStore;
	}

	/**
	 * This method is used to get the store.
	 * 
	 * @return The store.
	 */
	public Store getStore() {
		return this.store;
	}

	/**
	 * This method is used to get the stadium.
	 * 
	 * @return The stadium.
	 */
	public Stadium getStadium() {
		return this.stadium;
	}


	////////// Setup & Draft //////////


	/**
	 * This function is used to set up the game.
	 * 
	 * @param teamName The name of the players team.
	 * @param difficulty The difficulty of the game.
	 * @param seasonLength The length of the season.
	 */
	public void onSetupFinish(String teamName, int difficulty, int seasonLength) {
		// Set up the game variables
		this.week = 1;

		// Check the difficulty is valid
		if (difficulty == 0 || difficulty == 1) {
			this.difficulty = difficulty;
			this.money = 75000 - this.difficulty * 25000;
		}
		else {
			UI.showMessage("Please enter a valid difficulty.");
		}
		
		// Check the season length is valid
		if (seasonLength >= 5 && seasonLength <= 15) {
			this.seasonLength = seasonLength;
		} else {
			UI.showMessage("Please enter a season length between 5 and 15.");
		}

		// Check the team name is valid
		if (teamName.strip().matches("[a-zA-Z0-9]+")) {
			if (teamName.length() >= 3 && teamName.length() <= 15) {
				this.teamName = teamName;
			}
		}
		else {
			UI.showMessage("Please enter a name with 3-15 characters, using only letters and numbers.");
		}

		// Create the draft store
		this.drafStore = Store.createDraftStore(getRarityInt());

		// Create the store
		this.store = Store.createStore(getRarityInt());

		// Redirect to the main menu
		UI.draftMenu();
	}

	/**
	 * This function is used when the draft menu is finished to create the players team.
	 * 
	 * @param onTeam The players on the team.
	 * @param coach	The coach.
	 */
	public void onDraftMenuFinish(ArrayList<Player> onTeam, Coach coach, int money) {
		// Set money
		this.money = money;

		// Creat Empty Bench and Items
		ArrayList<Player> onBench = new ArrayList<Player>(5);
		ArrayList<Item> items = new ArrayList<Item>();
		for (int i = 0; i < 5; i++) {
			onBench.add(null);
		}

		this.playersTeam = new Team(teamName, onTeam, onBench, items, coach, 0);
		this.stadium = new Stadium(this, this.playersTeam);

		UI.mainMenu();
	}


	////////// Main Menu //////////


	/**
	 * This function is used to redirect the user to the correct menu.
	 * 
	 * @param redirect The number of the menu to redirect to.
	 */
	public void onMainMenuFinish(int redirect) {
		if (redirect == 0) {
			UI.clubMenu();
		} else if (redirect == 1) {
			UI.stadiumMenu();
		} else if (redirect == 2){
			UI.storeMenu();
		} else {
			UI.endGame();
		}
	}


	////////// Club Menu //////////


	/**
	 * This function is used when the club menu is finished.
	 */
	public void onClubMenuBack() {
		UI.mainMenu();
	}

	/**
	 * This function is used to set a players nickname.
	 * 
	 * @param player The player to set the nickname of.
	 * @param nickname The nickname to set.
	 */
	public void setNickname(Player player, String nickname) {
		try {
			player.setNickname(nickname);
			UI.clubMenu();
			UI.showMessage("Players nickname has been changed.");
		} catch (IllegalArgumentException e) {
			UI.showMessage("Player does not exist.");
		}
	}

	/**
	 * This function is used to name an item.
	 * 
	 * @param item The item to name.
	 * @param name The name to set.
	 */
	public void setItemName(Item item, String name) {
		try {
			item.setName(name);
			UI.clubMenu();
			UI.showMessage("Items name has been changed.");
		} catch (IllegalArgumentException e) {
			UI.showMessage("Item does not exist.");
		}
	}

	/**
	 * This function is used to sell a player
	 * 
	 * @param player The player to sell.
	 */
	public void sellPlayer(Player player) {
		try {
			this.playersTeam.sellPlayer(this, player);
			UI.clubMenu();
			UI.showMessage("Player has been sold.");
		} catch (IllegalArgumentException e) {
			UI.showMessage(e.getMessage());
		}

	}

	/**
	 * This method is used to swap players.
	 *
	 * @param teamPlayer The player on the team.
	 * @param benchPlayer The player on the bench.
	 */
	public void swapPlayers(Player teamPlayer, Player benchPlayer) {
		try {
			playersTeam.subPlayerSwap(teamPlayer, benchPlayer);
			UI.clubMenu();
			UI.showMessage("Players have been swapped.");
		} catch (IllegalArgumentException e) {
			UI.showMessage(e.getMessage());
		}
	}

	/**
	 * This method is used to sell an item.
	 * 
	 * @param item The item being sold.
	 */
	public void sellItem(Item item) {
		try {
			this.playersTeam.sellItem(this, item);
			UI.clubMenu();
			UI.showMessage("Item has been sold.");
		} catch (IllegalArgumentException e) {
			UI.showMessage(e.getMessage());
		}
	}


	////////// Stadium Menu //////////


	/**
	 * This method is used when the stadium menu is finished.
	 */
	public void onStadiumMenuBack() {
		UI.mainMenu();
	}

	/**
	 * This method is used to play a match.
	 * 
	 * @param match The match that is being played.
	 */
	public void playMatch(Match match) {
		try {
			stadium.playMatch(match);
			UI.playMatch(match);
		} catch (IllegalArgumentException e) {
			UI.showMessage(e.getMessage());
		}
	}

	/**
	 * This method is used to take a bye.
	 * 
	 * @param playerToTrain The player that is being trained.
	 */
	public void takeBye(Player playerToTrain) {
		this.stadium.takeBye(playerToTrain);
		UI.showMessage("You have taken a bye.");
	}


	////////// Match //////////


	/**
	 * This method is used when a match is finished or a bye is taken.
	 * It will increase the week by 1 and update the store and other teams.
	 * If the season is over, it will end the game.
	 */
	public void onMatchFinish() {

		// Random event
		stadium.randomEvent(this);

		// Increase the week by 1
		this.week += 1;
		int rarity = getRarityInt();

		// Update shop with slightly better items
		this.store.refeshStore(rarity);

		// Update other players teams with slightly better players
		 this.playersTeam.resetNPCTeams(rarity);

		// If the season is over, end the game
		if (week > seasonLength) {
			UI.endGame();
		} else {
			UI.mainMenu();
		}
	}

	/**
	 * This method calculates the a number which represents
	 * the rarity of the players in the store and the opposing team.
	 * 
	 * @return The rarity int for the game.
	 */
	public int getRarityInt() {

		// Integers to represent the progression of the player (0 - 100)
		float playerProgression = ((float) this.week / (float) this.seasonLength) * 100;

		// Progression is then changed based on the difficulty
		// Easy: (50 - 100) as progression goes from (0 - 100)
		// Hard: (40 - 90)  as progression goes from (0 - 100)
		if (difficulty == 0) {
			return (int) (playerProgression / 2 + 50);
		} else {
			return (int) playerProgression / 2 + 40;
		}
	}


	////////// Store Menu //////////


	/**
	 * This method is used when the store menu is finished.
	 */
	public void onStoreMenuBack() {
		UI.mainMenu();
	}

	/**
	 * This method is used to buy a player.
	 * 
	 * @param player The player being bought.
	 * @param teamOrBench Where the player is being added to.
	 */
	public void buyPlayer(Player player, int teamOrBench) {
		try {
			this.playersTeam.buyPlayer(this, player, teamOrBench);
			UI.clubMenu();
			UI.showMessage("Player has been bought.");
		} catch (IllegalArgumentException e) {
			UI.showMessage(e.getMessage());
		}
	}

	/**
	 * This method is used to buy a coach.
	 * 
	 * @param coach The coach being bought.
	 */
	public void buyCoach(Coach coach) {
		try {
			this.playersTeam.buyCoach(this, coach);
			UI.clubMenu();
			UI.showMessage("Coach has been bought.");
		} catch (IllegalArgumentException e) {
			UI.showMessage(e.getMessage());
		}
	}

	/**
	 * This method is used to buy an item.
	 * 
	 * @param item The item being bought.
	 */
	public void buyItem(Item item) {
		try {
			this.playersTeam.buyItem(this, item);
			UI.clubMenu();
			UI.showMessage("Item has been bought.");
		} catch (IllegalArgumentException e) {
			UI.showMessage(e.getMessage());
		}
	}


	////////// End Game //////////


	/**
	 * This method is used to finish the game.
	 */
	public void onGameFinish() {
		UI.endGame();
	}

	/**
	 * This method is used when the end game menu is finished.
	 */
	public void onEndGameFinish() {
		UI.quit();
	}

	/**
	 * This method is used to quit the game.
	 */
	public void quit() {
		if (UI.confirmQuit()) {
			UI.quit();
			System.exit(0);
		} 
	}	
}
