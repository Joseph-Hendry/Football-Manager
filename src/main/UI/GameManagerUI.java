package main.UI;
import main.body.GameManager;

public interface GameManagerUI {
	
	/**
	 * Initializes the UI and allows the user to set up their team.
	 */
	public void setup(GameManager manager);
	
	/**
	 * Shows the main menu screen.
	 */
	public void mainMenu(GameManager manager);
	
	/**
	 * Shows the club page to manage their team.
	 */
	public void clubMenu(GameManager manager);

	/**
	 * Shows the players team.
	 */
	public void showClub(GameManager manager);
	
	/**
	 * Shows the stadium menu which allows them to play a match.
	 */
	public void stadiumMenu(GameManager manager);
	
	/**
	 * Shows the events of playing a match.
	 */
	public void playMatch(GameManager manager);
	
	/**
	 * Shows the market where the player can buy and sell players, coaches and items.
	 */
	public void storeMenu(GameManager manager);

	/**
	 * Shows message.
	 */
	public void showMessage(String message);
}
