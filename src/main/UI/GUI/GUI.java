package main.UI.GUI;
import main.UI.GameManagerUI;
import main.body.GameManager;
import main.body.Match;

public class GUI implements GameManagerUI {
	GameManager manager;

	@Override
	public void setup(GameManager manager) {
		this.manager = manager;
		StartupMenuGUI startup = new StartupMenuGUI(manager);
		startup.getFrame().setVisible(true);
		
	}

	@Override
	public void clubMenu() {
		ClubMenuGUI club = new ClubMenuGUI(manager);
		club.getFrame().setVisible(true);
	}

	@Override
	public void showClub() {
		// TODO Auto-generated method stub
	}

	@Override
	public void stadiumMenu() {
		StadiumMenuGUI stadium = new StadiumMenuGUI(manager);
		stadium.getFrame().setVisible(true);
	}

	@Override
	public void playMatch(Match match) {
		PlayMatchGUI playMatch = new PlayMatchGUI(manager, match);
		playMatch.getFrame().setVisible(true);
		
	}

	@Override
	public void storeMenu() {
		StoreMenuGUI store = new StoreMenuGUI(manager);
		store.getFrame().setVisible(true);
		
	}

	@Override
	public void showMessage(String message) {
		ShowMessage.showMessage(message);
	}

	@Override
	public void mainMenu() {
		MainMenuGUI menu = new MainMenuGUI(manager);
		menu.getFrame().setVisible(true);
	}

	@Override
	public void endGame() {
		GameEndGUI end = new GameEndGUI(manager);
		end.getFrame().setVisible(true);
	}

	public static void main(String[] args) {
		GUI ui = new GUI();
		GameManager manager = new GameManager(ui);
		ui.setup(manager);
		//ui.mainMenu();
		//ui.clubMenu();
		//ui.stadiumMenu();
		//ui.playMatch(manager.getStadium().getOppositionTeam());
	}
}
