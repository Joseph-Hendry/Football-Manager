package main.UI.GUI;
import main.UI.GameManagerUI;
import main.body.GameManager;
import main.body.Match;

/**
 *  The GUI interface for th {@link GameManager}.
 */
public class GUI implements GameManagerUI {

	// The game manager and window.
	GameManager manager;

	// The window to display.
	Window window;

	/**
	 * Sets up the game manager.
	 * 
	 * @param manager The {@link GameManager} instance.
	 */
	@Override
	public void setup(GameManager manager) {
		this.manager = manager;
		window = new StartupMenuGUI(manager);
		window.show();
	}

	/**
	 * Shows the draft menu.
	 */
	@Override
	public void draftMenu() {
		window.quit();
		window = new DraftMenuGUI(manager);
		window.show();;
	}
	
	/**
	 * Shows the main menu.
	 */
	@Override
	public void mainMenu() {
		window.quit();
		window = new MainMenuGUI(manager);
		window.show();
	}

	/**
	 * Shows the club menu.
	 */
	@Override
	public void clubMenu() {
		window.quit();
		window = new ClubMenuGUI(manager);
		window.show();
	}

	/**
	 * Shows the stadium menu.
	 */
	@Override
	public void stadiumMenu() {
		window.quit();
		window = new StadiumMenuGUI(manager);
		window.show();
	}

	/**
	 * Shows the play match menu.
	 */
	@Override
	public void playMatch(Match match) {
		window.quit();
		window = new PlayMatchGUI(manager, match);
		window.show();
		
	}

	/**
	 * Shows the store menu.
	 */
	@Override
	public void storeMenu() {
		window.quit();
		window = new StoreMenuGUI(manager);
		window.show();
		
	}

	/**
	 * Shows the game end menu.
	 */
	@Override
	public void showMessage(String message) {
		ShowMessage.showMessage(message);
	}

	/**
	 * Shows the game end menu.
	 */
	@Override
	public void endGame() {
		window.quit();
		window = new GameEndGUI(manager);
		window.show();
	}

	/**
	 * Shows the game end menu.
	 */
    @Override
    public boolean confirmQuit() {
        return window.confirmQuit();
    }

	/**
	 * Quits the game.
	 */
    @Override
    public void quit() {
        window.quit();
    }

	public static void main(String[] args) {
		GUI ui = new GUI();
		GameManager manager = new GameManager(ui);
		ui.setup(manager);
	}
}
