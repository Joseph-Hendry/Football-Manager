package main.UI.CLI;

import java.util.Scanner;

import main.UI.GameManagerUI;
import main.body.GameManager;

public class CmdLineUI implements GameManagerUI {

	private final Scanner scanner;

	public CmdLineUI() {
		this.scanner = new Scanner(System.in);
	}

	@Override
	public void setup(GameManager manager) {
		System.out.println("########## Game Setup ##########");
		String teamName = setTeamName();
		int difficulty = setDifficulty();
		int seasonLength = setSeasonLength();

		manager.onSetupFinish(teamName, difficulty, seasonLength);
	}

	private String setTeamName() {
		System.out.println("\nChoose the team name: (3 - 15 characters)");
		while (true) {
			String input = scanner.nextLine();
			if (input.matches("[a-zA-Z0-9]+")) {
				if (input.length() >= 3 && input.length() <= 15) {
					return input;
				} else {
					System.out.println("Please enter a team name 3 - 15 characters");
				}
			} else {
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
		System.out.println("\nChoose your difficulty: EASY (0), HARD (1)");
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
		System.out.println("\nChoose the season length: (5 - 15) weeks");
		String input_string = "";

		while (true) {
			input_string = scanner.nextLine();
			try {
				int input = Integer.parseInt(input_string);
				if (input >= 5 && input <= 15) {
					return input;
				} else {
					System.out.println("Please enter a number between 5 and 15");
				}
			} catch (Exception e) {
				System.out.println("Please enter a number between 5 and 15 not a string");
			}
		}
	}

	/**
	 * This function represents the main menu. It displays where the user can go.
	 */
	@Override
	public void mainMenu(GameManager manager) {
		// Be able to go into club, stadium, store

		System.out.println("\n\n########## Main Menu ##########");
		System.out.println("(0) Club");
		System.out.println("(1) Stadium");
		System.out.println("(2) Store");

		while (true) {
			try {
				int redirect = Integer.parseInt(scanner.nextLine());
				if (redirect >= 0 && redirect <= 2) {
					manager.onMainMenuFinish(redirect);
					return;
				}
			} catch (Exception e) {
				System.out.println(e);
				System.out.println("Please enter a number: 0, 1, 2");
			}
		}
	}

	/**
	 * This function represents the club menu. It displays the players team information.
	 */
	@Override
	public void clubMenu(GameManager manager) {
		System.out.println("\n\n########## Club Menu ##########");
		System.out.println("Team Name: " + manager.getPlayerTeam().getName());
		System.out.println("Coach: " + manager.getPlayerTeam().getCoach().toString());

		System.out.println("\nPlayers On Team: \nNum  Name           [ATK,  MID,  DEF]     Position");

		int i = 0;
		for (i = 0; i < manager.getPlayerTeam().getTeam().size(); i++) {
			System.out.println(manager.getPlayerTeam().getTeam().get(i).toString(i + 1));
		}

		System.out.println("\n\nPlayers On Bench: \nNum  Name           [ATK,  MID,  DEF]     Position");

		for (int j = 0; j < manager.getPlayerTeam().getBench().size(); j++) {
			System.out.println(manager.getPlayerTeam().getBench().get(j).toString(j + i + 1));
		}

		System.out.println("\n\nSell Player: 'sell <player number>'");
		System.out.println("Swap Players: 'swap < team player number> < bench player number>'");
		System.out.println("Back: 'back'");
		while (true) {
			String input = scanner.nextLine();
			if (input.matches("sell [1-15]+")) {
				int playerNum = Integer.parseInt(input.split(" ")[1]);
				try {
					manager.onClubMenuFinish(0, playerNum);
					return;
				} catch (Exception e) {
					System.out.println("Please enter a valid player number, player must be on bench");
				}
			} else if (input.matches("swap [1-11]+ [12-15]+")) {
				int teamPlayerNum = Integer.parseInt(input.split(" ")[1]);
				int benchPlayerNum = Integer.parseInt(input.split(" ")[2]);
				try {
					manager.onClubMenuFinish(teamPlayerNum, benchPlayerNum);
					return;
				} catch (Exception e) {
					System.out.println("Please enter valid player to swap (must be same position)");
				}
			} else if (input.matches("back")) {
				manager.onClubMenuFinish(0, 0);
				return;
			} else {
				System.out.println("Please enter a valid command");
			}
		}
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
		// TODO Show players coach and items for sale
		System.out.println("Store Menu:");

	}
}
