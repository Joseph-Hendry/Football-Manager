package main;
import main.UI.*;

public class GameManager {

	protected final GameManagerUI UI;
	protected int difficulty;
	protected int seasonLength;
	protected Team playersTeam;
	protected int money;


	public GameManager(GameManagerUI ui) {
		this.UI = ui;
	}

	public void start() {
		UI.setup(this);

	}

	public void onSetupFinish(String teamName, int difficulty, int seasonLength) {
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

	public Team getPlayerTeam() {
		return this.playersTeam;
	}
}
