package main.UI.GUI;
import main.UI.GameManagerUI;
import main.body.GameManager;
import main.body.Match;

public class GUI implements GameManagerUI {
	GameManager manager;
	Window window;

	@Override
	public void setup(GameManager manager) {
		this.manager = manager;
		window = new StartupMenuGUI(manager);
		window.show();
		
	}

	@Override
	public void draftMenu() {
		window.quit();
		window = new DraftMenuGUI(manager);
		window.show();;
	}

	@Override
	public void clubMenu() {
		window.quit();
		window = new ClubMenuGUI(manager);
		window.show();
	}

	@Override
	public void stadiumMenu() {
		window.quit();
		window = new StadiumMenuGUI(manager);
		window.show();
	}

	@Override
	public void playMatch(Match match) {
		window.quit();
		window = new PlayMatchGUI(manager, match);
		window.show();
		
	}

	@Override
	public void storeMenu() {
		window.quit();
		window = new StoreMenuGUI(manager);
		window.show();
		
	}

	@Override
	public void showMessage(String message) {
		ShowMessage.showMessage(message);
	}

	@Override
	public void mainMenu() {
		window.quit();
		window = new MainMenuGUI(manager);
		window.show();
	}

	@Override
	public void endGame() {
		window.quit();
		window = new GameEndGUI(manager);
		window.show();
	}

    @Override
    public boolean confirmQuit() {
        return window.confirmQuit();
    }

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
