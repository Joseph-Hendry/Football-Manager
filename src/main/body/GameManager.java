package main.body;
import main.UI.*;
import main.UI.CLI.CmdLineUI;

public class GameManager {

	protected final GameManagerUI UI;
	protected int difficulty;
	protected int seasonLength;
	protected int money;
	protected int week;
	protected Team playersTeam;
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

	public Store getStore() {
		return this.store;
	}

	public Stadium getStadium() {
		return this.stadium;
	}


	////////// Setup //////////


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
		this.store = Store.createStore();
		this.money = 75000 - this.difficulty * 25000;

		// Create the players team
		this.playersTeam = Team.createRandomTeam(getRarityInt(), teamName);

		// Create the stadium which will create the other teams
		this.stadium = new Stadium(this, this.playersTeam); 

		// Redirect to the main menu
		UI.mainMenu(this);
	}


	////////// Main Menu //////////


	/**
	 * This function is used to redirect the user to the correct menu.
	 * 
	 * @param redirect The number of the menu to redirect to.
	 */
	public void onMainMenuFinish(int redirect) {
		if (redirect == 0) {
			UI.clubMenu(this);
		} else if (redirect == 1) {
			UI.stadiumMenu(this);
		} else {
			UI.storeMenu(this);
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
	public void onClubMenuFinish(String redirect) throws IllegalArgumentException {

		// If the player wants to change the nickname of a player
		if (redirect.matches("[0-9]+ [a-zA-Z]+")) {
			String[] splitInput = redirect.split(" ");
			int playerNum = Integer.parseInt(splitInput[0]);
			String nickname = splitInput[1].toString();
			System.out.println(nickname);
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
				UI.mainMenu(this);

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
	private void setNickname(int playerNum, String nickname) {
		try {
			this.playersTeam.setNickname(playerNum, nickname);
			UI.showClub(this);
			UI.showMessage("Players nickname has been changed.");
		} catch (Exception e) {
			UI.showMessage(e.getMessage());
		}
	}

	private void sellPlayer(int playerNum) {
		try {
			playerNum -= 12;
			this.playersTeam.sellPlayer(this, playerNum);
			UI.showClub(this);
			UI.showMessage("Player has been sold.");
		} catch (Exception e) {
			UI.showMessage(e.getMessage());
		}
	}

	private void swapPlayers(int teamPlayerNum, int benchPlayerNum) {
		try {
			playersTeam.subPlayerSwap(teamPlayerNum, benchPlayerNum);
			UI.showClub(this);
			UI.showMessage("Players have been swapped.");
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
	public void onStadiumMenuFinish(String redirect) throws IllegalArgumentException {
		if (redirect.equals("back")) {
			UI.mainMenu(this);
		}
		else {
			int redirectInt = Integer.parseInt(redirect);
			if (redirectInt >= 1 && redirectInt <= stadium.getPossibleMatches().size()) {
				playMatch(stadium.getPossibleMatches().get(redirectInt - 1));
			} else if (redirectInt == stadium.getPossibleMatches().size() + 1) {
				takeBye();
				UI.clubMenu(this);
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
			match.playMatch(this);
			onMatchFinish();
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
			onMatchFinish();
		} catch (Exception e) {
			UI.showMessage(e.getMessage());
		}
	}

	/**
	 * This method is used when a match is finished or a bye is taken.
	 * It will increase the week by 1 and update the store and other teams.
	 * If the season is over, it will end the game.
	 */
	private void onMatchFinish() {

		// Increase the week by 1
		this.week += 1;
		int rarity = getRarityInt();

		// Update shop with slightly better items
		this.store.refeshStore(rarity);

		// Update other players teams with slightly better players
		 this.playersTeam.resetNPCTeams(rarity);

		// If the season is over, end the game
		if (week > seasonLength) {
			UI.endGame(this);
		} else {
			UI.mainMenu(this);
		}
	}

	/**
	 * This method calculates the a number which represents
	 * the rarity of the players in the store and the opposing team.
	 * 
	 * @return The rarity int for the game.
	 */
	private int getRarityInt() {
		
		// Integers to represent the progression of the player (0 - 100)
		int playerProgression = (week / seasonLength) * 100;

		// Progression is then changed based on the difficulty
		// Easy: (50 - 100) as progression goes from (0 - 100)
		// Hard: (40 - 90)  as progression goes from (0 - 100)
		if (difficulty == 0) {
			return playerProgression / 2 + 50;
		} else {
			return playerProgression / 2 + 40;
		}
	}


	////////// Store Menu //////////


	public void onStoreMenuFinish() {
		UI.clubMenu(this);
	}


	////////// End Game //////////


	public void onGameFinish() {
		// TODO: UI.endGame();
		// TODO: UI.quit();
	}

	public static void main(String[] args) {
		CmdLineUI ui = new CmdLineUI();
		GameManager manager = new GameManager(ui);
		ui.setup(manager);
	}
}
