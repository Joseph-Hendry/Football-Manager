package main.UI;

import java.util.Scanner;

import main.GameManager;

public class CmdLineUI implements GameManagerUI {
	
	Scanner scanner;

	public CmdLineUI() {
	}
	

	@Override
	public void setup(GameManager manager, Scanner scanner) {
		this.scanner = scanner;
		String teamName = setTeamName();
		int difficulty = setDifficulty();
		int seasonLength = setSeasonLength();

		manager.onSetupFinish(teamName, difficulty, seasonLength);

	}

	private String setTeamName() {

		// Prompt user to enter a team name
		System.out.println("Choose the team name: (3 - 15 characters)");

		while (true) {
			String input = scanner.nextLine();

			// If name only contains letters or numbers then type is valid
			if (input.matches("[a-zA-Z0-9]+")) {
				// Check the string has the correct length
				if (input.length() >= 3 && input.length() <= 15) {
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
	private int setDifficulty() {
		String invalidInputMessage = "Enter either 0 or 1 to choose your difficulty.";
		System.out.println("Choose your difficulty: EASY (0), HARD (1)");
		int number = -1;
		
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
		
		int chosenDiff = (number == 0 ? 0 : 1);
		return chosenDiff;
	}

	private int setSeasonLength() {

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
					return input;
				} 
				// Else prompt use to enter and in range int
				else {
					System.out.println("Please enter a number between 5 and 15");
				}
			
			}
			// If input can't be converted to a string then prompt use for a int
			catch (Exception e) {
				System.out.println("Please enter a number between 5 and 15 not a string");
			}
		}
	}

	@Override
	public void mainMenu(GameManager manager) {
		// Be able to go into club, stadium, store

		System.out.println("Main Menu:");
		System.out.println("\nMoney: " + manager.getMoney() + " Week: " + manager.getCurrentWeek()
		+ " Weeks remaining: " + (manager.getSeasonLength() - manager.getCurrentWeek()) + "\n");
		System.out.println("(0) Club");
		System.out.println("(1) Stadium");
		System.out.println("(2) Store");

		String input_string = "";
		int input = -1;
		boolean validInput = false;

		while (!validInput) { // Gets an int from 0 - 2 as the players' choice.
			input_string = scanner.nextLine();
			try {
				input = Integer.parseInt(input_string);

				// If it's an int return it
				if (input >= 0 && input <= 2) {
					validInput = true;
				} 
				// Else prompt use to enter and in range int
				else {
					System.out.println("Please enter a number between 0 and 2");
				}
			}
			// If input can't be converted to a string then prompt use for a int
			catch (Exception e) {
				System.out.println("Please enter a number between 0 and 2 not a string");
			}
		}
		switch(input) { // Selects what to do based on user input.
		case 0:
			clubMenu(manager);
			break;
		case 1:
			stadiumMenu(manager);
			break;
		case 2:
			storeMenu(manager);
			break;
		}
	}

	@Override
	public void clubMenu(GameManager manager) {
		// TODO: View current team properties, player properties
		System.out.println("Club Menu:");
	}

	@Override
	public void stadiumMenu(GameManager manager) {
		// TODO: View rankings and possible matches
		System.out.println("Stadium Menu:");
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
