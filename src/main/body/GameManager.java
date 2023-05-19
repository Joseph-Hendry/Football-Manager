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
		this.difficulty = difficulty;
		this.seasonLength = seasonLength;
		this.money = 75000 - this.difficulty * 25000;
		this.teamName = teamName;

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
		} else {
			UI.storeMenu();
		}
	}


	////////// Club Menu //////////


	/**
	 * This function is used when the club menu is finished.
	 * 
	 * @param redirect The input from the user.
	 * 
	 * Using: When got values send them here. If they aren't valid, throw an exception.
	 * If they are valid the function will deal with them.
	 */
	public void onClubMenuFinish(String redirect) throws Exception {

		// If the player wants to change the nickname of a player
		if (redirect.matches("[0-9]+ [a-zA-Z]+")) {
			String[] splitInput = redirect.split(" ");
			int playerNum = Integer.parseInt(splitInput[0]);
			String nickname = splitInput[1].toString();
			setNickname(playerNum, nickname);
		} else {
			redirect = redirect.toLowerCase();

			// If the player wants to swap players
			if (redirect.matches("swap [0-9]+ [0-9]+")) {
				String[] splitInput = redirect.split(" ");
				swapPlayers(Integer.parseInt(splitInput[1]), Integer.parseInt(splitInput[2]));

			// If the player wants to sell a player
			} else if (redirect.matches("sell [0-9]+")) {
				int playerNum = Integer.parseInt(redirect.split(" ")[1]);
				sellPlayer(playerNum);

			// If the player wants to go back
			} else if (redirect.equals("back")) {
				UI.mainMenu();

			// If the input isn't valid
			} else {
				throw new IllegalArgumentException("Please enter a valid input");
			}
		}
	}

	/**
	 * This function is used when the player wants to set the nickname.
	 * 
	 * @param playerNum
	 * @param nickname
	 */
	private void setNickname(int playerNum, String nickname) throws Exception {
		this.playersTeam.setNickname(playerNum, nickname);
		UI.clubMenu();
		UI.showMessage("Players nickname has been changed.");
	}

	private void sellPlayer(int playerNum) throws Exception {
		playerNum -= 12;
		this.playersTeam.sellPlayer(this, playerNum);
		UI.clubMenu();
		UI.showMessage("Player has been sold.");

	}

	private void swapPlayers(int teamPlayerNum, int benchPlayerNum) throws Exception {
		playersTeam.subPlayerSwap(teamPlayerNum, benchPlayerNum);
		UI.clubMenu();
		UI.showMessage("Players have been swapped.");
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
	public void onStadiumMenuFinish(String redirect) throws IllegalArgumentException {
		if (redirect.equals("back")) {
			UI.mainMenu();
		}
		else {
			int redirectInt = Integer.parseInt(redirect);
			if (redirectInt >= 1 && redirectInt <= stadium.getPossibleMatches().size()) {
				playMatch(stadium.getPossibleMatches().get(redirectInt - 1));
			} else if (redirectInt == stadium.getPossibleMatches().size() + 1) {
				takeBye();
				UI.clubMenu();
			} else {
				throw new IllegalArgumentException("Please enter a valid input");
			}
		}
	}

	/**
	 * This method is used to play a match.
	 * 
	 * @param match The match that is being played.
	 */
	private void playMatch(Match match) {
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
	private void takeBye() {
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
	public void onStoreMenuFinish(String redirect) throws Exception {

		// If the player wants to buy a player
		if (redirect.matches("buy [0-9]+ [a-z]+")) {

			// Check valid number
			Player player;
			try {
				player = store.getStorePlayers().get(Integer.parseInt(redirect.split(" ")[1]) - 1);
			} catch (Exception e) {
				throw new IllegalArgumentException("Please enter a valid input");
			}

			// Add to team or bench else throw exception
			if (redirect.matches("buy [0-9]+ team")) {
				playersTeam.addPlayerToTeam(player);
				money -= player.getValue();
				store.removePlayer(player);
			} else if (redirect.matches("buy [0-9]+ bench")) {
				playersTeam.addPlayerToBench(player);
				money -= player.getValue();
				store.removePlayer(player);

			} else {
				throw new IllegalArgumentException("Please enter a valid input");
			} UI.storeMenu();

		// Buy Coach
		} else if (redirect.toLowerCase() == "buy coach") {
			int temp = money - (playersTeam.getCoach().getValue() - store.getStoreCoach().getValue());
			if (temp < 0) {
				throw new Exception("You don't have enough money to buy this coach.");
			} else {
				money = temp;
				playersTeam.setCoach(store.getStoreCoach());
				UI.storeMenu();
			}

		// If the player wants to sell a player
		} else if (redirect.matches("buy item [0-9]+")) {
			int itemNum = Integer.parseInt(redirect.split(" ")[2]);
			Item item;
			try {
				item = store.getStoreItems().get(itemNum - 1);
			} catch (Exception e) {
				throw new IllegalArgumentException("Please enter a valid input");
			}
			int temp = money - item.getValue();
			if (temp < 0) {
				throw new Exception("You don't have enough money to buy this item.");
			} else {
				money = temp;
				playersTeam.addItem(item);
				store.removeItem(item);
				UI.storeMenu();
			}
		
		// back
		} else if (redirect.equals("back")) {
			UI.mainMenu();
		} else {
			throw new IllegalArgumentException("Please enter a valid input");
		}
	}


	////////// End Game //////////


	public void onEndGameFinish() {
		UI.quit();
	}
}
