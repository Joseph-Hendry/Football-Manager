package main;
import main.UI.*;


public class GameManager {

	protected final GameManagerUI UI;
	protected int difficulty;
	protected String teamName;
	protected int seasonLength;
	protected int money;


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
		this.money = 75000 - this.difficulty * 25000; // Easy starts with 75,000, hard with 50,000
	}

}
