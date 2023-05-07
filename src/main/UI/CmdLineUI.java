package main.UI;

import java.util.Scanner;

import main.GameManager;

public class CmdLineUI implements GameManagerUI {

	public CmdLineUI() {
	}

	@Override
	public void setup(GameManager manager) {
		int difficulty = setDifficulty();
		String teamName = setTeamName();
		int seasonLength = setSeasonLength();

		manager.onSetupFinish(difficulty, teamName, seasonLength);

	}

	public String setTeamName() {
		Scanner scanner = new Scanner(System.in);

		// Prompt user to enter a team name
		System.out.println("Choose the team name: (3 - 15 characters)");

		while (true) {
			String input = scanner.nextLine();

			// If name only contains letters or numbers then type is valid
			if (input.matches("[a-zA-Z0-9]+")) {
				// Check the string has the correct length
				if (input.length() >= 3 && input.length() <= 15) {
					scanner.close();
					return input;
				}
				// Otherwise prompt the user to enter the right size string
				else {
					System.out.println("Please enter a team name 3 - 15 characters");
				}
			}
			// Otherwise prompt the user to not use any special strings
			else {
				System.out.println("Please enter a team name with no special characters");
			}
		}
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

	public int setSeasonLength() {
		Scanner scanner = new Scanner(System.in);

		// Set the season length
		System.out.println("Choose the season length: (5 - 15) weeks");
		String input_string = "";

		while (true) {
			input_string = scanner.nextLine();

			// Try convert the input to an int
			try {
				int input = Integer.parseInt(input_string);

				// If it's an int return it
				if (input >= 5 && input <= 15) {
					scanner.close();
					return input;
				} 
				// Else prompt use to enter and in range int
				else {
					System.out.println("Please enter a number between 5 and 15");
				}
			
			}
			// If input cant be converted to a string then prompt use for a int
			catch (Exception e) {
				System.out.println("Please enter a number between 5 and 15 not a string");
			}
		}
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
