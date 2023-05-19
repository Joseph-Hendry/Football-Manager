package main.UI.GUI;

import main.body.GameManager;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;

public class GameEndGUI extends Window {

	/**
	 * Create the application.
	 */
	public GameEndGUI(GameManager manager) {
		super("Game End", manager);
	}


	/**
	 * Initialize the contents of the frame.
	 */
	@Override
	protected void initialise(JFrame frame) {
		frame.setBounds(100, 100, 450, 300);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

		JLabel lblTeamName = new JLabel("Team Name: " + getManager().getPlayerTeam().getName());
		lblTeamName.setBounds(10, 11, 414, 14);
		frame.getContentPane().add(lblTeamName);

		JLabel lblSeasonLength = new JLabel("Season Length: " + getManager().getSeasonLength());
		lblSeasonLength.setBounds(10, 36, 414, 14);
		frame.getContentPane().add(lblSeasonLength);

		JLabel lblMoney = new JLabel("Money: " + getManager().getMoney());
		lblMoney.setBounds(10, 61, 414, 14);
		frame.getContentPane().add(lblMoney);

		JLabel lblPoints = new JLabel("Points: " + getManager().getPlayerTeam().getPoints());
		lblPoints.setBounds(10, 86, 414, 14);
		frame.getContentPane().add(lblPoints);

		JLabel lblDifficulty = new JLabel("Difficulty: " + getManager().getDifficulty());
		lblDifficulty.setBounds(10, 111, 414, 14);
		frame.getContentPane().add(lblDifficulty);

		//Finish button
		JButton lblFinish = new JButton("Finish");
		lblFinish.setBounds(10, 136, 414, 14);
		frame.getContentPane().add(lblFinish);
		lblFinish.addActionListener(e -> {
			frame.dispose();
			getManager().onEndGameFinish();
		});
	}
}
