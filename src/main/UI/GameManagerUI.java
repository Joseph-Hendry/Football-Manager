package main.UI;
import main.*;

public interface GameManagerUI {
	
	/**
	 * Initializes the UI and allows the user to set up their team.
	 */
	public void setup();
	
	/**
	 * Shows the main menu screen.
	 */
	public void mainMenu();
	
	/**
	 * Shows the club page to manage their team.
	 */
	public void clubMenu();
	
	/**
	 * Shows the stadium menu which allows them to play a match.
	 */
	public void stadiumMenu();
	
	/**
	 * Shows the events of playing a match.
	 */
	public void playMatch();
	
	/**
	 * Shows the market where the player can buy and sell players, coaches and items.
	 */
	public void marketMenu();
}
