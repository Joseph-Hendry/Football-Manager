package main;

import main.UI.*;
import main.UI.CLI.CmdLineUI;
import main.UI.GUI.*;
import main.body.GameManager;


/**
 * 
 * Class where the application is executed.
 * If "cmd" is passed as a program argument the application will run on the command line,
 * otherwise the GUI is used.
 *
 */
public class Main {

	/**
	 * Main method, runs the application.
	 * 
	 * @param args The command line arguments. Only supports "cmd" to make it run in
	 * the command line. Otherwise uses GUI.
	 */
	
	static GameManagerUI ui;
	
	public static void main(String[] args) {
		if (args.length == 0) {
			ui = new GUI();
			GameManager manager = new GameManager(ui);
			manager.start();
		} else if (args.length == 1 && (args[0].equals("cmd"))) {
			ui = new CmdLineUI();
			GameManager manager = new GameManager(ui);
			manager.start();
		} else {
			System.out.println("Invalid arguments");
		}
	}
}
