package main.UI.GUI;

import java.awt.EventQueue;

import javax.swing.JFrame;

import main.body.GameManager;

public class ClubMenuGUI {

	private JFrame frame;
	private GameManager manager;

	/**
	 * Launch the application.
	 */
//	public static void main(String[] args) {
//		EventQueue.invokeLater(new Runnable() {
//			public void run() {
//				try {
//					ClubMenuGUI window = new ClubMenuGUI();
//					window.frame.setVisible(true);
//				} catch (Exception e) {
//					e.printStackTrace();
//				}
//			}
//		});
//	}

	/**
	 * Create the application.
	 */
	public ClubMenuGUI(GameManager manager) {
		this.manager = manager;
		initialize();
	}
	
	public JFrame getFrame() {
		return this.frame;
	}
	
	public GameManager getManager() {
		return this.getManager();
	}

	/**
	 * Initialize the contents of the frame.
	 */
	private void initialize() {
		frame = new JFrame();
		frame.setBounds(100, 100, 450, 300);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
	}

}
