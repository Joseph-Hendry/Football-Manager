package main.UI.GUI;
import main.UI.GameManagerUI;
import main.body.GameManager;
import main.body.Team;

public class GUI implements GameManagerUI {

	@Override
	public void setup(GameManager manager) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void mainMenu(GameManager manager) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void clubMenu(GameManager manager) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void showClub(GameManager manager) {
		// TODO Auto-generated method stub
	}

	@Override
	public void stadiumMenu(GameManager manager) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void playMatch(GameManager manager, Team team) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void storeMenu(GameManager manager) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void showMessage(String message) {
		// TODO Auto-generated method stub
		
	}
	
	public static void main(String[] args) {
		CmdLineUI ui = new CmdLineUI();
		GameManager manager = new GameManager(ui);
		ui.setup(manager);
	}
}
