package main;
import main.UI.*;

import java.util.Scanner;

public class GameManager {

	protected final GameManagerUI UI;
	protected int difficulty;
	protected int seasonLength;
	protected Team playersTeam;
	protected String teamName;
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
		this.playersTeam = team;

		Scanner scanner = new Scanner(System.in);
		UI.mainMenu(this, scanner);
		scanner.close();
	}

	public static void main(String[] args) {
		GameManager game = new GameManager(new CmdLineUI());
		game.start();
	}
}
