package main.UI.GUI;

import main.body.GameManager;

import java.awt.Font;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.SwingConstants;

/**
 * The GUI for the main menu.
 * Used to display the main menu and allow the user to choose where to go.
 */
public class MainMenuGUI extends Window {

	// Setup JButtons
	private JButton clubButton;
	private JButton stadiumButton;
	private JButton storeButton;
	private JButton finishButton;

	/**
	 * Create the application.
	 * 
	 * @param manager The {@link GameManager} instance.
	 */

	public MainMenuGUI(GameManager manager) {
		super("Main Menu", manager);
	}

	/**
	 * Initialize the contents of the frame.
	 * 
	 * @param frame The frame to add the contents to.
	 */
	@Override
	protected void initialise(JFrame frame) {
		frame.setBounds(0, 0, 420, 420);

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
		JLabel titleLabel = new JLabel("Main Menu");
		titleLabel.setHorizontalAlignment(SwingConstants.CENTER);
		titleLabel.setFont(new Font("Tahoma", Font.PLAIN, 18));
		titleLabel.setBounds(0, 10, 420, 50);
		frame.getContentPane().add(titleLabel);
		
		JLabel moneyLabel = new JLabel("Money: " + getManager().getMoney());
		moneyLabel.setBounds(260, 60, 100, 15);
		frame.getContentPane().add(moneyLabel);
		
		JLabel weekLabel = new JLabel("Week: " + getManager().getWeek() + "/" + getManager().getSeasonLength());
		weekLabel.setBounds(59, 60, 100, 15);
		frame.getContentPane().add(weekLabel);
	}

	/**
	 * Adds the buttons to the frame.
	 * 
	 * @param frame The frame to add the buttons to.
	 */
	private void addButtons(JFrame frame) {

		// Create the club button
		clubButton = new JButton("Club");
		clubButton.setFont(new Font("Tahoma", Font.PLAIN, 14));
		clubButton.setBounds(60, 75, 300, 60);
		frame.getContentPane().add(clubButton);
		clubButton.addActionListener(e -> {
			getManager().onMainMenuFinish(0);
		});
		
		// Create the stadium button
		stadiumButton = new JButton("Stadium");
		stadiumButton.setFont(new Font("Tahoma", Font.PLAIN, 14));
		stadiumButton.setBounds(60, 150, 300, 60);
		frame.getContentPane().add(stadiumButton);
		stadiumButton.addActionListener(e -> {
			getManager().onMainMenuFinish(1);
		});
		
		// Create the store button
		storeButton = new JButton("Store");
		storeButton.setFont(new Font("Tahoma", Font.PLAIN, 14));
		storeButton.setBounds(60, 225, 300, 60);
		frame.getContentPane().add(storeButton);
		storeButton.addActionListener(e -> {
			getManager().onMainMenuFinish(2);
		});

		// Create the finish season button
		finishButton = new JButton("Finish Season");
		finishButton.setFont(new Font("Tahoma", Font.PLAIN, 14));
		finishButton.setBounds(60, 300, 300, 60);
		frame.getContentPane().add(finishButton);
		finishButton.addActionListener(e -> {
			// Check if the user wants to finish the season
			int dialogResult = JOptionPane.showConfirmDialog(null, "Are you sure you want to finish the season?", "Finish Season", JOptionPane.YES_NO_OPTION);
			if (dialogResult == JOptionPane.YES_OPTION) {
				getManager().onGameFinish();;
			}
		});
	}
}
