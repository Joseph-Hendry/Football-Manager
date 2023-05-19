package main.body;
import java.util.ArrayList;

import main.UI.*;

public class GameManager {

	protected final GameManagerUI UI;
	protected int difficulty;
	protected int seasonLength;
	protected int money;
	protected int week;
	protected String teamName;
	protected Team playersTeam;
	protected Store drafStore;
	protected Store store;
	protected Stadium stadium;


	////////// Constructor //////////


	public GameManager(GameManagerUI ui) {
		this.UI = ui;
	}

	public void start() {
		UI.setup(this);
	}


	////////// Getters and Setters //////////


	public int getDifficulty() {
		return this.difficulty;
	}
	
	public int getSeasonLength() {
		return this.seasonLength;
	}

	public int getMoney() {
		return this.money;
	}
	
	public void setMoney(int amount) {
		this.money = amount;
	}

	public void incMoney (int amount) {
		this.money += amount;
	}

	public void decMoney (int amount) {
		this.money -= amount;
	}

	public int getWeek() {
		return this.week;
	}

	public Team getPlayerTeam() {
		return this.playersTeam;
	}

	public Store getDraftStore() {
		return this.drafStore;
	}

	public Store getStore() {
		return this.store;
	}

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
	 * 
	 * Using: When got values send them here. If they aren't valid, throw an exception.
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
	 * @param onTeam	The players on the team.
	 * @param coach		The coach.
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


	public void onClubMenuBack() {
		UI.mainMenu();
	}

	public void setNickname(Player player, String nickname) {
		try {
			player.setNickname(nickname);
			UI.clubMenu();
			UI.showMessage("Players nickname has been changed.");
		} catch (Exception e) {
			UI.showMessage("Player does not exist.");
		}
	}

	public void sellPlayer(Player player) {
		try {
			this.playersTeam.sellPlayer(this, player);
			UI.clubMenu();
			UI.showMessage("Player has been sold.");
		} catch (Exception e) {
			UI.showMessage(e.getMessage());
		}

	}

	public void swapPlayers(Player teamPlayer, Player benchPlayer) {
		try {
			playersTeam.subPlayerSwap(teamPlayer, benchPlayer);
			UI.clubMenu();
			UI.showMessage("Players have been swapped.");
		} catch (Exception e) {
			UI.showMessage(e.getMessage());
		}
	}

	public void sellItem(Item item) {
		try {
			this.playersTeam.sellItem(this, item);
			UI.clubMenu();
			UI.showMessage("Item has been sold.");
		} catch (Exception e) {
			UI.showMessage(e.getMessage());
		}
	}


	////////// Stadium Menu //////////


	/**
	 * This function is used when the stadium menu is finished.
	 * 
	 * @param redirect
	 * 
	 * Using: When got values send them here. If they aren't valid, throw an exception.
	 * If they are valid the function will deal with them.
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
		} catch (Exception e) {
			UI.showMessage(e.getMessage());
		}
	}

	/**
	 * This method is used to take a bye.
	 * It will either redirect the user to the main menu or end the game.
	 */
	public void takeBye() {
		try {
			this.stadium.takeBye();
			UI.showMessage("You have taken a bye.");
		} catch (Exception e) {
			UI.showMessage(e.getMessage());
		}
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
	 * @param redirect 		The input from the user.
	 * @throws Exception 	If the input is invalid.
	 */
	public void onStoreMenuBack() {
		UI.mainMenu();
	}


	public void buyPlayer(Player player, int teamOrBench) {
		try {
			this.playersTeam.buyPlayer(this, player, teamOrBench);
			UI.clubMenu();
			UI.showMessage("Player has been bought.");
		} catch (Exception e) {
			UI.showMessage(e.getMessage());
		}
	}

	public void buyCoach(Coach coach) {
		try {
			this.playersTeam.buyCoach(this, coach);
			UI.clubMenu();
			UI.showMessage("Coach has been bought.");
		} catch (Exception e) {
			UI.showMessage(e.getMessage());
		}
	}

	public void buyItem(Item item) {
		try {
			this.playersTeam.buyItem(this, item);
			UI.clubMenu();
			UI.showMessage("Item has been bought.");
		} catch (Exception e) {
			UI.showMessage(e.getMessage());
		}
	}


	////////// End Game //////////

	public void quit() {
		if (UI.confirmQuit()) {
			// If we had any clean up to do before quitting we should do it here before telling
			// the ui to quit.
			UI.quit();
		}
	}


	public void onEndGameFinish() {
		UI.quit();
	}
}
