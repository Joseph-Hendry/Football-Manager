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
	protected int currentWeek;


	public GameManager(GameManagerUI ui) {
		this.UI = ui;
	}

	public void start() {
		Scanner scanner = new Scanner(System.in);
		UI.setup(this, scanner);

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
		this.playersTeam = team;
		UI.mainMenu(this);
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

	public static void main(String[] args) {
		GameManager game = new GameManager(new CmdLineUI());
		game.start();
	}
}
