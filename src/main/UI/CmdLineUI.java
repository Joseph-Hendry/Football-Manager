package main.UI;

import java.util.Scanner;

import main.GameManager;

public class CmdLineUI implements GameManagerUI {

	public CmdLineUI() {
	}

	@Override
	public void setup(GameManager manager) {
		int difficulty = setDifficulty();

		manager.onSetupFinish(difficulty);

	}

	public int setDifficulty() {
		Scanner scanner = new Scanner(System.in);
		System.out.println("Choose your difficulty: EASY (0), HARD (1)");
		String input = "";
		while (input != "0" || input != "1") {
			input = scanner.nextLine();
			if (input != "0" || input != "1") {
				System.out.println("Write either 0 or 1 to choose your difficulty");
			}
		}
		scanner.close();
		int chosenDiff = (input == "0" ? 0 : 1);
		return chosenDiff;
	}

	@Override
	public void mainMenu(GameManager manager) {
		// TODO Auto-generated method stub

	}

	@Override
	public void clubMenu(GameManager manager) {
		// TODO Auto-generated method stub

	}

	@Override
	public void stadiumMenu(GameManager manager) {
		// TODO Auto-generated method stub

	}

	@Override
	public void playMatch(GameManager manager) {
		// TODO Auto-generated method stub
	}

	@Override
	public void storeMenu(GameManager manager) {
		// TODO Auto-generated method stub

	}

}
