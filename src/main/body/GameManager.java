package main.body;
import main.UI.*;
import main.UI.CLI.CmdLineUI;

public class GameManager {

	protected final GameManagerUI UI;
	private boolean finish = false;
	protected int difficulty;
	protected int seasonLength;
	protected int money;
	protected int week;
	protected Team playersTeam;
	protected Store store;
	protected Stadium stadium;

	public GameManager(GameManagerUI ui) {
		this.UI = ui;
	}

	public void start() {
		UI.setup(this);
	}

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

		
	private void incWeek() {

		this.week += 1;
		int rarity = getRarityInt();

		// Update shop with slightly better items
		this.store.refeshStore(rarity);

		// Update other players teams with slightly better players
		 this.playersTeam.resetNPCTeams(rarity);
	}

	/**
	 * This method calculates the a number which represents
	 * the rarity of the players in the store and the opposing team.
	 * @return
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

	/**
	 * This function is used to set up the game.
	 * @param teamName The name of the players team.
	 * @param difficulty The difficulty of the game.
	 * @param seasonLength The length of the season.
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

		// Create the stadium
		this.stadium = new Stadium(this, this.playersTeam);

		// Redirect to the main menu
		UI.mainMenu(this);
	}

	/**
	 * This function is used to redirect the user to the correct menu.
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

	/**
	 * This function is used when the club menu is finished.
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
	 * This function is used when the player wants to set the nick
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

	public void onStadiumMenuFinish(String redirect) {
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
				UI.clubMenu(this);
			}
		}
	}

	/**
	 * This method is used to play a match.
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

	private void onMatchFinish() {
		incWeek();
		if (week > seasonLength) {
			UI.showMessage("You have finished the season!");
			UI.endGame(this);
		} else {
			UI.mainMenu(this);
		}
	}


	/**
	 * This method is used to take a bye.
	 */
	private void takeBye() {
		try {
			this.stadium.takeBye();
			UI.showMessage("You have taken a bye.");
			onMatchFinish();
			UI.mainMenu(this);
		} catch (Exception e) {
			UI.showMessage(e.getMessage());
		}
	}

	public void onStoreMenuFinish() {
		UI.clubMenu(this);
	}

	public void onGameFinish() {
		UI.showMessage("Thanks for playing!");
		quit();
	}

	public void quit() {
		finish = true;
	}

	public static void main(String[] args) {
		CmdLineUI ui = new CmdLineUI();
		GameManager manager = new GameManager(ui);
		ui.setup(manager);
	}
}
