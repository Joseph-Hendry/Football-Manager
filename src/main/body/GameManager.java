package main.body;
import main.UI.*;
import main.UI.CLI.CmdLineUI;


public class GameManager {

	protected final GameManagerUI UI;
	protected int difficulty;
	protected int seasonLength;
	protected Team playersTeam;
	protected int money;
	protected int currentWeek = 0;
	protected Store currentStore;
	protected Stadium stadium;

	//private boolean finish = false;

	public GameManager(GameManagerUI ui) {
		this.UI = ui;
	}

	public void start() {
		UI.setup(this);
	}
	
	public int getMoney() {
		return this.money;
	}
	
	public void setMoney(int amount) {
		this.money = amount;
	}
	
	public int getCurrentWeek() {
		return this.currentWeek;
	}
	
	public int getSeasonLength() {
		return this.seasonLength;
	}
	
	public Store getCurrentStore() {
		return this.currentStore;
	}
	
	public int getDifficulty() {
		return this.difficulty;
	}

	public int getWeek() {
		return this.currentWeek;
	}

	public void onSetupFinish(String teamName, int difficulty, int seasonLength) {
		this.currentWeek = 1;
		this.difficulty = difficulty;
		this.seasonLength = seasonLength;
		this.currentStore = Store.createStore();
		this.money = 75000 - this.difficulty * 25000;
		Team team;

		if (difficulty == 0) {
			team = Team.createRandomTeam(80);
		} else {
			team = Team.createRandomTeam(50);
		}
		team.setName(teamName);
		this.playersTeam = team;

		UI.mainMenu(this);
	}

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
	 * This function is used to edit the players team.
	 * @param teamPlayerNum The number of the player on the team.
	 * @param benchPlayerNum The number of the player on the bench.
	 * @throws Exception
	 */
	public void onClubMenuFinish() {
		UI.mainMenu(this);
	}

	public void setNickname(int playerNum, String nickname) {
		try {
			this.playersTeam.setNickname(playerNum, nickname);
			UI.showClub(this);
			UI.showMessage("Players nickname has been changed.");
		} catch (Exception e) {
			UI.showMessage(e.getMessage());
		}
	}

	public void sellPlayer(int playerNum) {
		try {
			playerNum -= 12;
			this.playersTeam.sellPlayer(this, playerNum);
			UI.showClub(this);
			UI.showMessage("Player has been sold.");
		} catch (Exception e) {
			UI.showMessage(e.getMessage());
		}
	}

	public void swapPlayers(int teamPlayerNum, int benchPlayerNum) {
		try {
			this.playersTeam.subPlayerSwap(teamPlayerNum, benchPlayerNum);
			UI.showClub(this);
			UI.showMessage("Players have been swapped.");
		} catch (Exception e) {
			UI.showMessage(e.getMessage());
		}
	}
	
	public void incWeek() {

		this.currentWeek++;
		int rarity = getRarityInt();

		// Update shop with slightly better items
		this.currentStore.refeshStore(rarity);

		// Update players team with slightly better stats
		this.playersTeam.updateNPCTeam(rarity);
	}

	/**
	 * This method calculates the a number which represents
	 * the rarity of the players in the store and the opposing team.
	 * @return
	 */
	private int getRarityInt() {
		
		// Integers to represent the progression of the player (0 - 100)
		int playerProgression = (currentWeek / seasonLength) * 100;

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
	 * This method is used to play a match.
	 * @param match The match that is being played.
	 */
	public void playMatch(Match match) {
		try {
			match.playMatch(this);
		} catch (Exception e) {
			UI.showMessage(e.getMessage());
		}
	}

	public Team getPlayerTeam() {
		return this.playersTeam;
	}

	// public void quit() {
	// 	finish = true;
	// }

	public static void main(String[] args) {
		// Test play match
		GameManager manager = new GameManager(new CmdLineUI());
		Team team = Team.createRandomTeam(80);
		team.setName("Home");
		Team oppositionTeam = Team.createRandomTeam(80);
		oppositionTeam.setName("Opposition");

		Match match = new Match(team, oppositionTeam, 10, 1000);
		match.playMatch(manager);
		match.playMatch(manager);
		match.playMatch(manager);
	}
}
