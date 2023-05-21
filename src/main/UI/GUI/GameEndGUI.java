package main.UI.GUI;

import main.body.GameManager;

import java.awt.Font;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.SwingConstants;

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
		frame.setBounds(0, 0, 300, 280);

		addLabels(frame);
		addButtons(frame);
	}

	/**
	 * Adds the labels to the frame.
	 * 
	 * @param frame The frame to add the labels to.
	 */
	private void addLabels(JFrame frame) {

		// Create the windows labels
		JLabel lblTitle = new JLabel("End of Season Results");
		lblTitle.setHorizontalAlignment(SwingConstants.CENTER);
		lblTitle.setFont(new Font("Tahoma", Font.PLAIN, 18));
		lblTitle.setBounds(0, 10, 300, 40);
		frame.getContentPane().add(lblTitle);

		JLabel lblTeamName = new JLabel("Team Name: " + getManager().getPlayerTeam().getName());
		lblTeamName.setBounds(10, 75, 180, 15);
		frame.getContentPane().add(lblTeamName);

		JLabel lblSeasonLength = new JLabel("Season Length: " + getManager().getSeasonLength());
		lblSeasonLength.setBounds(10, 100, 180, 15);
		frame.getContentPane().add(lblSeasonLength);

		JLabel lblMoney = new JLabel("Money: " + getManager().getMoney());
		lblMoney.setBounds(10, 125, 180, 15);
		frame.getContentPane().add(lblMoney);

		JLabel lblPoints = new JLabel("Points: " + getManager().getPlayerTeam().getPoints());
		lblPoints.setBounds(10, 150, 180, 15);
		frame.getContentPane().add(lblPoints);

		// Find the difficulty
		String difficulty = "";
		if (getManager().getDifficulty() == 0) {
			difficulty = "Easy";
		} else if (getManager().getDifficulty() == 1) {
			difficulty = "Hard";
		}

		JLabel lblDifficulty = new JLabel("Difficulty: " + difficulty);
		lblDifficulty.setBounds(10, 175, 180, 15);
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
		finishButton.setBounds(10, 200, 280, 40);
		frame.getContentPane().add(finishButton);
		finishButton.addActionListener(e -> {
			getManager().onEndGameFinish();
		});
	}
}
