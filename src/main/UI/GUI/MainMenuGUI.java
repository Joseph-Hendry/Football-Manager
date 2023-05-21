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
	private JButton btnClub;
	private JButton btnStadium;
	private JButton btnStore;
	private JButton btnFinish;

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
		JLabel lblWelcomeMessage = new JLabel("Main Menu");
		lblWelcomeMessage.setHorizontalAlignment(SwingConstants.CENTER);
		lblWelcomeMessage.setFont(new Font("Tahoma", Font.PLAIN, 18));
		lblWelcomeMessage.setBounds(0, 10, 420, 50);
		frame.getContentPane().add(lblWelcomeMessage);
		
		JLabel lblMoney = new JLabel("Money: " + getManager().getMoney());
		lblMoney.setBounds(260, 60, 100, 15);
		frame.getContentPane().add(lblMoney);
		
		JLabel lblWeek = new JLabel("Week: " + getManager().getWeek() + "/" + getManager().getSeasonLength());
		lblWeek.setBounds(59, 60, 70, 15);
		frame.getContentPane().add(lblWeek);
	}

	/**
	 * Adds the buttons to the frame.
	 * 
	 * @param frame The frame to add the buttons to.
	 */
	private void addButtons(JFrame frame) {

		// Create the club button
		btnClub = new JButton("Club");
		btnClub.setFont(new Font("Tahoma", Font.PLAIN, 14));
		btnClub.setBounds(60, 75, 300, 60);
		frame.getContentPane().add(btnClub);
		btnClub.addActionListener(e -> {
			getManager().onMainMenuFinish(0);
		});
		
		// Create the stadium button
		btnStadium = new JButton("Stadium");
		btnStadium.setFont(new Font("Tahoma", Font.PLAIN, 14));
		btnStadium.setBounds(60, 150, 300, 60);
		frame.getContentPane().add(btnStadium);
		btnStadium.addActionListener(e -> {
			getManager().onMainMenuFinish(1);
		});
		
		// Create the store button
		btnStore = new JButton("Store");
		btnStore.setFont(new Font("Tahoma", Font.PLAIN, 14));
		btnStore.setBounds(60, 225, 300, 60);
		frame.getContentPane().add(btnStore);
		btnStore.addActionListener(e -> {
			getManager().onMainMenuFinish(2);
		});

		// Create the finish season button
		btnFinish = new JButton("Finish Season");
		btnFinish.setFont(new Font("Tahoma", Font.PLAIN, 14));
		btnFinish.setBounds(60, 300, 300, 60);
		frame.getContentPane().add(btnFinish);
		btnFinish.addActionListener(e -> {
			// Check if the user wants to finish the season
			int dialogResult = JOptionPane.showConfirmDialog(null, "Are you sure you want to finish the season?", "Finish Season", JOptionPane.YES_NO_OPTION);
			if (dialogResult == JOptionPane.YES_OPTION) {
				getManager().onGameFinish();;
			}
		});
	}
}
