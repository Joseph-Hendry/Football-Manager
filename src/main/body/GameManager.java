package main.body;
import main.UI.*;


public class GameManager {

	protected final GameManagerUI UI;
	protected int difficulty;
	protected int seasonLength;
	protected Team playersTeam;
	protected int money;
	protected int currentWeek;

	private boolean finish = false;


	public GameManager(GameManagerUI ui) {
		this.UI = ui;
	}

	public void start() {
		UI.setup(this);
	}
	
	public int getMoney() {
		return this.money;
	}
	
	public int getCurrentWeek() {
		return this.currentWeek;
	}
	
	public int getSeasonLength() {
		return this.seasonLength;
	}

	public void onSetupFinish(String teamName, int difficulty, int seasonLength) {
		this.currentWeek = 0;
		this.difficulty = difficulty;
		this.seasonLength = seasonLength;
		this.money = 75000 - this.difficulty * 25000;
		Team team;

		if (difficulty == 0) {
			team = Team.createRandomTeam("Silver");
			for (int i = 0; i < seasonLength; i++) {
				Team.createRandomTeam("Bronze");
			}
		} else {
			team = Team.createRandomTeam("Bronze");
			for (int i = 0; i < seasonLength; i++) {
				Team.createRandomTeam("Bronze");
			}
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
	public void onClubMenuFinish(int teamPlayerNum, int benchPlayerNum) throws Exception {
		if (teamPlayerNum == 0 && benchPlayerNum == 0) {
			UI.clubMenu(this);
			return;
		} else if (teamPlayerNum == 0) {
			try {
				this.playersTeam.sellPlayer(benchPlayerNum);
			} catch (Exception e) {
				throw e;
			}
		} else {
			try {
				this.playersTeam.subPlayerSwap(teamPlayerNum, benchPlayerNum);
			} catch (Exception e) {
				throw e;
			}
		}
	}
	

	public Team getPlayerTeam() {
		return this.playersTeam;
	}

	public void quit() {
		finish = true;
	}
}
