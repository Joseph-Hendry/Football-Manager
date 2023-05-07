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
	/**
	 * This function gets input from the user about what difficulty they would like
	 * to play the game at.
	 * @return chosenDiff	The chosen difficulty
	 */
	public int setDifficulty() {
		String invalidInputMessage = "Enter either 0 or 1 to choose your difficulty.";
		Scanner scanner = new Scanner(System.in);
		System.out.println("Choose your difficulty: EASY (0), HARD (1)");
		int number = 2;
		
		while (number != 0 && number != 1) { // Asks user for input until a valid input is given.
			String input = scanner.nextLine();
			try {
				number = Integer.parseInt(input);
				if (number != 0 && number != 1) {
					System.out.println(invalidInputMessage);
				}
			} catch (NumberFormatException e) {
				System.out.println(invalidInputMessage);
			}
		}
		
		scanner.close();
		int chosenDiff = (number == 0 ? 0 : 1);
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
