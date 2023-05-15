package main.UI.GUI;

import main.body.GameManager;

import javax.swing.JFrame;

public class GameEndGUI {

	private JFrame frame;
	private GameManager manager;

	/**
	 * Create the application.
	 */
	public GameEndGUI(GameManager manager) {
		this.manager = manager;
		initialize();
	}

	/**
	 * Get the frame.
	 */
	public JFrame getFrame() {
		return this.frame;
	}

	/**
	 * Initialize the contents of the frame.
	 */
	private void initialize() {
		frame = new JFrame();
		frame.setBounds(100, 100, 450, 300);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

		// TODO: Add game end GUI elements here
	}
}
