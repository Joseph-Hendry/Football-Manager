package main.UI;
import main.body.GameManager;
import main.body.Match;

public interface GameManagerUI {
	
	/**
	 * Initializes the UI and allows the user to set up their team.
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
	 * Shows the players team.
	 */
	public void showClub();
	
	/**
	 * Shows the stadium menu which allows them to play a match.
	 */
	public void stadiumMenu();
	
	/**
	 * Shows the events of playing a match.
	 */
	public void playMatch(Match match);
	
	/**
	 * Shows the market where the player can buy and sell players, coaches and items.
	 */
	public void storeMenu();

	/**
	 * Shows message.
	 */
	public void showMessage(String message);

    public void endGame();

	public void quit();
}
