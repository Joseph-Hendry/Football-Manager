package main;
import main.UI.*;


public class GameManager {

	protected final GameManagerUI UI;
	protected int difficulty;
	protected String teamName;
	protected int seasonLength;


	public GameManager(GameManagerUI ui) {
		this.UI = ui;
	}

	public void start() {
		UI.setup(this);

	}

	public void onSetupFinish(int difficulty, String teamName, int seasonLength) {
		this.difficulty = difficulty;
		this.teamName = teamName;
		this.seasonLength = seasonLength;
	}

}
