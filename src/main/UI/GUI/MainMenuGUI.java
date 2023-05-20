package main.UI.GUI;

import main.body.GameManager;

import java.awt.Font;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
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
		frame.setBounds(100, 100, 399, 365);
		frame.getContentPane().setLayout(null);
		frame.setResizable(false);

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
		lblWelcomeMessage.setBounds(66, 12, 287, 43);
		frame.getContentPane().add(lblWelcomeMessage);
		
		JLabel lblMoney = new JLabel("Money: " + getManager().getMoney());
		lblMoney.setBounds(272, 57, 81, 15);
		frame.getContentPane().add(lblMoney);
		
		JLabel lblWeek = new JLabel("Week: " + getManager().getWeek() + "/" + getManager().getSeasonLength());
		lblWeek.setBounds(59, 57, 70, 15);
		frame.getContentPane().add(lblWeek);
	}

	/**
	 * Adds the buttons to the frame.
	 * 
	 * @param frame The frame to add the buttons to.
	 */
	private void addButtons(JFrame frame) {
		btnClub = new JButton("Club");
		btnClub.setFont(new Font("Tahoma", Font.PLAIN, 14));
		btnClub.setBounds(59, 77, 294, 63);
		frame.getContentPane().add(btnClub);
		btnClub.addActionListener(e -> {
			getManager().onMainMenuFinish(0);
		});
		
		btnStadium = new JButton("Stadium");
		btnStadium.setFont(new Font("Tahoma", Font.PLAIN, 14));
		btnStadium.setBounds(59, 152, 294, 63);
		frame.getContentPane().add(btnStadium);
		btnStadium.addActionListener(e -> {
			getManager().onMainMenuFinish(1);
		});
		
		btnStore = new JButton("Store");
		btnStore.setFont(new Font("Tahoma", Font.PLAIN, 14));
		btnStore.setBounds(59, 227, 294, 63);
		frame.getContentPane().add(btnStore);
		btnStore.addActionListener(e -> {
			getManager().onMainMenuFinish(2);
		});
	}
}
