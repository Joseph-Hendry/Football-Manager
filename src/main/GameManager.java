package main;
import main.UI.*;

public class GameManager {

	protected final GameManagerUI UI;
	protected int difficulty;
	protected int seasonLength;
	protected Team playersTeam;


	public GameManager(GameManagerUI ui) {
		this.UI = ui;
	}

	public void start() {
		UI.setup(this);

	}

	public void onSetupFinish(String teamName, int difficulty, int seasonLength) {
		this.difficulty = difficulty;
		this.seasonLength = seasonLength;
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
		this.playersTeam = team;
	}

}
