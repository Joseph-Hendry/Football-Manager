package main.UI.GUI;

import main.body.GameManager;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;

/**
 * The GUI for the game end screen.
 * Used to display the game end screen.
 */
public class GameEndGUI extends Window {

	/**
	 * Create the application.
	 * 
	 * @param manager The {@link GameManager} instance.
	 */
	public GameEndGUI(GameManager manager) {
		super("Game End", manager);
	}

	/**
	 * Initialize the contents of the frame.
	 * 
	 * @param frame The frame to add the contents to.
	 */
	@Override
	protected void initialise(JFrame frame) {
		frame.setBounds(0, 0, 450, 300);

		addLabels(frame);
		addButtons(frame);
	}

	/**
	 * Adds the labels to the frame.
	 * 
	 * @param frame The frame to add the labels to.
	 */
	private void addLabels(JFrame frame) {
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
	}

	/**
	 * Adds the buttons to the frame.
	 * 
	 * @param frame The frame to add the buttons to.
	 */
	private void addButtons(JFrame frame) {

		// Create the finish button
		JButton finishButton = new JButton("Finish");
		finishButton.setBounds(10, 136, 414, 14);
		frame.getContentPane().add(finishButton);
		finishButton.addActionListener(e -> {
			getManager().onEndGameFinish();
		});
	}
}
