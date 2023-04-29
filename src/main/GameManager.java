package main;
import main.UI.*;


public class GameManager {
	
	private final GameManagerUI ui;
	
	public GameManager(GameManagerUI ui) {
		this.ui = ui;
	}
	
	public void start() {
		this.ui.setup();
	}
	
}
