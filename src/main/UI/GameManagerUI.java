package main.UI;

import main.body.GameManager;
import main.body.Match;


/**
 * The interface for the {@link GameManager} UI.
 */
public interface GameManagerUI {
	
	/**
	 * Initializes the UI and allows the user to set up their team.
	 * 
	 * @param manager The game {@link GameManager}.
	 */
	public void setup(GameManager manager);

	/**
	 * Shows the draft menu screen.
	 */
	public void draftMenu();
	
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
	public void playMatch(Match match);
	
	/**
	 * Shows the market where the player can buy players, coaches and items.
	 */
	public void storeMenu();

	/**
	 * Shows message to the user.
	 */
	public void showMessage(String message);

	/**
	 * Shows the end game screen.
	 */
    public void endGame();

	/**
	 * Confirms if the user wants to quit the game.
	 */
	public boolean confirmQuit();

	/**
	 * Quits the game.
	 */
	public void quit();
}
