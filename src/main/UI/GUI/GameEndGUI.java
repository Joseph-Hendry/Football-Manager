package main.UI.GUI;

import main.body.GameManager;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;

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

		JLabel lblTeamName = new JLabel("Team Name: " + manager.getPlayerTeam().getName());
		lblTeamName.setBounds(10, 11, 414, 14);
		frame.getContentPane().add(lblTeamName);

		JLabel lblSeasonLength = new JLabel("Season Length: " + manager.getSeasonLength());
		lblSeasonLength.setBounds(10, 36, 414, 14);
		frame.getContentPane().add(lblSeasonLength);

		JLabel lblMoney = new JLabel("Money: " + manager.getMoney());
		lblMoney.setBounds(10, 61, 414, 14);
		frame.getContentPane().add(lblMoney);

		JLabel lblPoints = new JLabel("Points: " + manager.getPlayerTeam().getPoints());
		lblPoints.setBounds(10, 86, 414, 14);
		frame.getContentPane().add(lblPoints);

		JLabel lblDifficulty = new JLabel("Difficulty: " + manager.getDifficulty());
		lblDifficulty.setBounds(10, 111, 414, 14);
		frame.getContentPane().add(lblDifficulty);

		//Finish button
		JButton lblFinish = new JLabel("Finish");
		lblFinish.setBounds(10, 136, 414, 14);
		frame.getContentPane().add(lblFinish);
		lblFinish.addActionListener(e -> {
			frame.dispose();
			manager.onGameEnd();
		}
	}
}
