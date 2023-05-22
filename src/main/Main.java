package main;

import main.UI.*;
import main.UI.GUI.*;
import main.body.GameManager;


/**
 * 
 * Class where the application is executed.
 *
 */
public class Main {
	// This code is based off the GUI tutorial example.
	
	static GameManagerUI ui;

	/**
	 * Main method, runs the application.
	 * 
	 * @param args The command line arguments.
	 */
	public static void main(String[] args) {
		if (args.length == 0) {
			ui = new GUI();
			GameManager manager = new GameManager(ui);
			manager.start();
		} else {
			System.out.println("Invalid arguments");
		}
	}
}
