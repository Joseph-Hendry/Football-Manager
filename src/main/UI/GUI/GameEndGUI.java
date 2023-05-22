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
		JLabel titleLabel = new JLabel("End of Season Results");
		titleLabel.setHorizontalAlignment(SwingConstants.CENTER);
		titleLabel.setFont(new Font("Tahoma", Font.PLAIN, 18));
		titleLabel.setBounds(0, 10, 300, 40);
		frame.getContentPane().add(titleLabel);

		JLabel teamNameLabel = new JLabel("Team Name: " + getManager().getPlayerTeam().getName());
		teamNameLabel.setBounds(10, 75, 180, 15);
		frame.getContentPane().add(teamNameLabel);

		JLabel seasonLengthLabel = new JLabel("Season Length: " + getManager().getSeasonLength());
		seasonLengthLabel.setBounds(10, 100, 180, 15);
		frame.getContentPane().add(seasonLengthLabel);

		JLabel moneyLabel = new JLabel("Money: " + getManager().getMoney());
		moneyLabel.setBounds(10, 125, 180, 15);
		frame.getContentPane().add(moneyLabel);

		JLabel pointsLabel = new JLabel("Points: " + getManager().getPlayerTeam().getPoints());
		pointsLabel.setBounds(10, 150, 180, 15);
		frame.getContentPane().add(pointsLabel);

		// Find the difficulty
		String difficulty = "";
		if (getManager().getDifficulty() == 0) {
			difficulty = "Easy";
		} else if (getManager().getDifficulty() == 1) {
			difficulty = "Hard";
		}

		JLabel difficultyLabel = new JLabel("Difficulty: " + difficulty);
		difficultyLabel.setBounds(10, 175, 180, 15);
		frame.getContentPane().add(difficultyLabel);
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
