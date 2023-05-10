package main.UI.CLI;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Random;
import java.util.Scanner;

import main.UI.GameManagerUI;
import main.body.*;

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

	/**
	 * This function gets input from the user about how long the season should be in weeks.
	 * @return input	The number of weeks
	 */
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
		System.out.println("(2) Store\n");

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
		showClub(manager);
		getClubInput(manager);
	}

	public void showClub(GameManager manager) {
		System.out.println("\n\n########## Club Menu ##########");
		System.out.println("Team Name: " + manager.getPlayerTeam().getName());
		System.out.println("Team Money: " + manager.getMoney());
		System.out.println("Coach: " + manager.getPlayerTeam().getCoach().toString());

		System.out.println("\nPlayers On Team: \nNum  Name           [ATK,  MID,  DEF]    [Position]      [Value]");

		int i = 0;
		for (i = 0; i < manager.getPlayerTeam().getTeam().size(); i++) {
			System.out.println(manager.getPlayerTeam().getTeam().get(i).toString(i + 1));
		}

		System.out.println("\n\nPlayers On Bench: \nNum  Name           [ATK,  MID,  DEF]     Position");

		for (int j = 0; j < 4; j++) {
			try {
				System.out.println(manager.getPlayerTeam().getBench().get(j).toString(j + i + 1));
			} catch (Exception e) {
				System.out.println("(" + (j + i + 1) + ")" + " Empty");
			}
		}
		System.out.println("\nNickname Player: '<player number> <name>'");
		System.out.println("Sell Player: 'sell <player number>'");
		System.out.println("Swap Players: 'swap <team player number> <bench player number>'");
		System.out.println("Back: 'back'\n");
	}

	private String getClubInput(GameManager manager) {
		while (true) {
			String input = scanner.nextLine();
			if (input.matches("[0-9]+ [a-zA-Z]+")) {
				String[] splitInput = input.split(" ");
				int playerNum = Integer.parseInt(splitInput[0]);
				String nickname = splitInput[1].toString();
				System.out.println(nickname);
				manager.setNickname(playerNum, nickname);
			} else {
				input = input.toLowerCase();

				if (input.matches("swap [0-9]+ [0-9]+")) {
					String[] splitInput = input.split(" ");
					int teamPlayerNum = Integer.parseInt(splitInput[1]);
					int benchPlayerNum = Integer.parseInt(splitInput[2]);
					manager.swapPlayers(teamPlayerNum, benchPlayerNum);

				} else if (input.matches("sell [0-9]+")) {
					int playerNum = Integer.parseInt(input.split(" ")[1]);
					manager.sellPlayer(playerNum);

				} else if (input.equals("back")) {
					mainMenu(manager);

				} else {
					System.out.println("Please enter a valid input");
				}
			}
		}
	}

	/**
	 * This function displays the stadium menu, which allows the user to choose a match to play.
	 */
	@Override
	public void stadiumMenu(GameManager manager) {
		// TODO: View rankings and possible matches
		ArrayList<Team> teamsToPlay = new ArrayList<Team>();
		Random random = new Random();
		int scaleValue = manager.getDifficulty() == 0 ? 2 : 4;
		for (int i = 0; i < 4; i++) {
			// Sets how good the teams are based off the current week and the difficulty.
			int rarity = random.nextInt(50) + scaleValue * manager.getCurrentWeek();
			teamsToPlay.add(Team.createRandomTeam(rarity));
		}
		
		System.out.println("\n\n########## Stadium Menu ##########");
		System.out.println("\nChoose which team you would like to play:");
		for (int i = 0; i < teamsToPlay.size(); i++) {
			Team team = teamsToPlay.get(i);
			System.out.println("(" + i + ") " + team.getName() + " Rarity: " + team.getCoach().getRarity());
		}
		System.out.println("\n	'back' Back to main menu\n");
		
		ArrayList<String> validInputs = new ArrayList<String>(Arrays.asList("0", "1", "2", "3", "back"));
		String userInput = getValidInput(validInputs);
		if (userInput.equals("back")) {
			mainMenu(manager);
		} else {
			
			Team oppositionTeam = teamsToPlay.get(Integer.parseInt(userInput));
			
			// Checks that the players team is full.
			boolean fullTeam = manager.getPlayerTeam().getTeam().size() == 11 ? true : false;
			
			// Testing whether all the players in the users team are injured.
			boolean allInjured = true;
			for (Player player : manager.getPlayerTeam().getTeam()) {
				if (!player.isInjured()) {
					allInjured = false;
				}
			}
			
			if(!allInjured && fullTeam) {
				playMatch(manager, oppositionTeam);
			} else {
				System.out.println("\n You must have a full team with at least one\nnon-injured player to enter a match.");
				stadiumMenu(manager);
			}
		}
	}
	

	@Override
	public void playMatch(GameManager manager, Team oppositionTeam) {
		// TODO Auto-generated method stub

	}

	@Override
	public void storeMenu(GameManager manager) {
		System.out.println("\n\n########## Store Menu ##########");
		Store store = manager.getCurrentStore();
		
		// Prints the options available to the user
		System.out.println("\n(0) Buy players and coaches");
		System.out.println("(1) Buy items");
		System.out.println("'back' Back to main menu\n");
		
		// Gets input from the player.
		boolean validInput = false;
		while (!validInput) {
			String string_input = scanner.nextLine();
			if (string_input.equals("back")) {
				validInput = true;
				mainMenu(manager);
			} else {
				try {
					int int_input = Integer.parseInt(string_input);
					if (int_input == 0) {
						validInput = true;
						storePlayerMenu(store, manager);
					} else if (int_input == 1) {
						validInput = true;
						storeItemMenu(store, manager);
					} else {
						System.out.println("Please enter either 0, 1, or back");
					}
				} catch (Exception e) {
					System.out.println("Please enter either 0, 1, or back");
				}
			}
		}
	}
	/**
	 * Allows the player to see which players and coach are available for sale in the store, and 
	 * allows them to purchase these people for their team.
	 * @param store	The store currently being displayed.
	 */
	public void storePlayerMenu(Store store, GameManager manager) {
		// Prints the players available for sale.
		System.out.println("\nPlayers for sale:");
		System.out.println("Balance: " + manager.getMoney());
		System.out.println("    NAME        RARITY    POSITION    ATK MID DEF   COST");
		int counter = 0;
		for (Player player : store.getStorePlayers()) {
			int[] statList = player.getStats();
			String line = String.format("(%d) %-10s  %-8s  %-10s %3d %3d %3d    %d",
				counter, player.getName(),player.getRarity(), player.getPosition(), statList[0], statList[1], statList[2], player.getValue());
			System.out.println(line);
			counter += 1;
		}
		// Prints the coach available for sale.
		if (store.coachAvailable()) {
			Coach coach = store.getStoreCoach();
			int[] coachStats = coach.getStats();
			System.out.println("\nCoach for sale:");
			String line = String.format("(4) NAME: %s  RARITY: %s  ATK: %d MID: %d DEF: %d COST: %d (Will replace current coach)",
					coach.getName(), coach.getRarity(), coachStats[0], coachStats[1], coachStats[2], coach.getValue());
			System.out.println(line);
		}
		System.out.println("\n'back' Back to store menu"); 	
		
		// Generates the available inputs a user can select.
		ArrayList<String> validInputs = new ArrayList<String>();
		for (int i = 0; i < store.getStorePlayers().size(); i++) {
			validInputs.add(Integer.toString(i));
		}
		if (store.coachAvailable()) {
			validInputs.add(Integer.toString(store.getStorePlayers().size() + 1));
		}
		validInputs.add("back");
		
		String userInput = getValidInput(validInputs);
		if (userInput.equals("back")) {
			storeMenu(manager);
		} else if (Integer.parseInt(userInput) == store.getStorePlayers().size() - 1){
			if (manager.getMoney() >= store.getStoreCoach().getValue()) {
				manager.getPlayerTeam().setCoach(store.getStoreCoach());
				manager.setMoney(manager.getMoney() - store.getStoreCoach().getValue());
				store.setCoachAvailable(false);
			} else {
				System.out.println("You do not have enough money to buy this coach.");
			}
		} else {
			int i = Integer.parseInt(userInput);
			Player player = store.getStorePlayers().get(i);
			if (manager.getMoney() >= player.getValue()) {
				manager.getPlayerTeam().addPlayerToBench(player);
				manager.setMoney(manager.getMoney() - player.getValue());
				System.out.println("\n" + player.getName() + " has been added to your bench.");
				System.out.println("Your remaining balance is " + manager.getMoney());
				store.removePlayer(player);
				storePlayerMenu(store, manager);
			} else {
				System.out.println("You do not have enough money to buy this player.");
				storeItemMenu(store, manager);
			}
		}
	}
	
	/**
	 * Allows the player to see and purchase items that are available in the store.
	 * @param store	The store currently being displayed.
	 */
	public void storeItemMenu(Store store, GameManager manager) {
		// Prints the items available for sale.
		System.out.println("\nItems for sale");
		System.out.println("Balance: " + manager.getMoney());
		System.out.println("    NAME        RARITY     ATK MID DEF  COST");
		int counter = 0;
		for (Item item : store.getStoreItems()) {
			int[] statList = item.getStats();
			String line = String.format("(%d) %-10s  %-8s  %3d %3d %3d   %d", 
					counter, item.getName(), item.getRarity(), statList[0], statList[1], statList[2], item.getValue());
			System.out.println(line);
			counter += 1;
		}
		
		System.out.println("\n'back' Back to store menu");
		
		// Goes back to the main menu or buys an item, depending on the players input.
		ArrayList<String> validInputs = new ArrayList<String>();
		for (int i = 0; i < store.getStoreItems().size(); i++) {
			validInputs.add(Integer.toString(i));
		}
		validInputs.add("back");
			
		String userInput = getValidInput(validInputs);
		if (userInput.equals("back")) {
			storeMenu(manager);
		} else {
			int i = Integer.parseInt(userInput);
			Item item = store.getStoreItems().get(i);
			if (manager.getMoney() >= item.getValue()) {
				manager.getPlayerTeam().addItem(item);
				manager.setMoney(manager.getMoney() - item.getValue());
				System.out.println("\nYou have bought " + item.getName());
				System.out.println("Your remaining balance is " + manager.getMoney());
				store.removeItem(item);
				storeItemMenu(store, manager);
			} else {
				System.out.println("You do not have enough money to buy this item.");
				storeItemMenu(store, manager);
			}
		}
	}

	/**
	 * Gets a valid input from the user. Is valid if the input is in validInputList.
	 * @param validInputList	The list of valid inputs
	 * @return the valid input when one is given.
	 */
	public String getValidInput(ArrayList<String> validInputList) {
		boolean validInput = false;
		String string_input;
		while(!validInput) {
			string_input = scanner.nextLine();
			for (String valid : validInputList) {
				if (string_input.equals(valid)) {
					validInput = true;
					return string_input;
				}
			}
			System.out.println("Please enter one of the following options: " + String.join(", ", validInputList));
		}
		return "";
	}

	public void showMessage(String message) {
		System.out.println(message);
	}

	public static void main(String[] args) {
		CmdLineUI ui = new CmdLineUI();
		GameManager manager = new GameManager(ui);
		ui.setup(manager);
	}
}
