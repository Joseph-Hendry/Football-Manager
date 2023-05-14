package main.UI.GUI;
import main.UI.GameManagerUI;
import main.body.GameManager;
import main.body.Team;

public class GUI implements GameManagerUI {
	GameManager manager;

	@Override
	public void setup(GameManager manager) {
		this.manager = manager;
		StartupMenuGUI startupMenu = new StartupMenuGUI(manager);
		
	}

	@Override
	public void mainMenu() {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void clubMenu() {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void showClub() {
		// TODO Auto-generated method stub
	}

	@Override
	public void stadiumMenu() {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void playMatch(Team team) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void storeMenu() {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void showMessage(String message) {
		// TODO Auto-generated method stub
		
	}
	
	public static void main(String[] args) {
		GUI ui = new GUI();
		GameManager manager = new GameManager(ui);
		ui.setup(manager);
	}
}
