package main;
import main.UI.*;


public class GameManager {

	protected final GameManagerUI UI;
	protected int difficulty;


	public GameManager(GameManagerUI ui) {
		this.UI = ui;
	}

	public void start() {
		UI.setup(this);

	}

	public void onSetupFinish(int difficulty) {
		this.difficulty = difficulty;
	}

}
