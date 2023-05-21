package main.UI.GUI;

import javax.swing.JFrame;
import javax.swing.JLabel;
import java.awt.Font;
import javax.swing.SwingConstants;

import main.body.GameManager;

import javax.swing.JTextField;
import javax.swing.JSpinner;
import javax.swing.SpinnerNumberModel;
import javax.swing.JComboBox;
import javax.swing.DefaultComboBoxModel;
import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;


/**
 * The GUI for the startup menu.
 * Used to display the startup menu and allow users to select their settings.
 */
public class StartupMenuGUI extends Window {

	// Setup JTextFields
	private JTextField txtTeamName;

	// Setup JSpinners
	private JSpinner spnrSeasonLength;

	// Setup JComboBox
	private JComboBox<String> comboDifficulty;

	// Setup JButtons
    private JButton btnContinue;
	private JButton btnQuit;

	/**
	 * Create the application.
	 * 
	 * @param manager The {@link GameManager} instance.
	 */
	public StartupMenuGUI(GameManager manager) {
		super("Startup Menu", manager);
	}
		
	/**
	 * Completes the setup of {@link GameManager}.
	 */
	private void setupComplete() {
		getManager().onSetupFinish(txtTeamName.getText(), comboDifficulty.getSelectedIndex(), (int) spnrSeasonLength.getValue());
	}
	
	/**
	 * Initialize the contents of the frame.
	 * 
	 * @param frame The frame to add the contents to.
	 */
	@Override
	protected void initialise(JFrame frame) {
		frame.setBounds(0, 0, 640, 360);

		addLabels(frame);
		addInfoBoxes(frame);
		addButtons(frame);
	}

	/**
	 * Adds the labels to the frame.
	 * 
	 * @param frame The frame to add the labels to.
	 */
	private void addLabels(JFrame frame) {

		// Create the windows labels
		JLabel lblWelcomeMessage = new JLabel("Welcome To Football Manager!");
		lblWelcomeMessage.setHorizontalAlignment(SwingConstants.CENTER);
		lblWelcomeMessage.setFont(new Font("Tahoma", Font.PLAIN, 18));
		lblWelcomeMessage.setBounds(168, 11, 287, 43);
		frame.getContentPane().add(lblWelcomeMessage);
		
		JLabel lblChooseName = new JLabel("Please choose a team name (3-15 characters):");
		lblChooseName.setFont(new Font("Tahoma", Font.PLAIN, 14));
		lblChooseName.setBounds(10, 81, 347, 29);
		frame.getContentPane().add(lblChooseName);
		
		JLabel lblSeasonLength = new JLabel("Choose a season length (5-15 weeks):");
		lblSeasonLength.setFont(new Font("Tahoma", Font.PLAIN, 14));
		lblSeasonLength.setBounds(10, 134, 347, 29);
		frame.getContentPane().add(lblSeasonLength);
		
		JLabel lblChooseDifficulty = new JLabel("Choose your difficulty setting:");
		lblChooseDifficulty.setFont(new Font("Tahoma", Font.PLAIN, 14));
		lblChooseDifficulty.setBounds(10, 190, 218, 18);
		frame.getContentPane().add(lblChooseDifficulty);
	}

	/**
	 * Adds the text field and combo box and spinner to the frame.
	 * 
	 * @param frame The frame to add the items to.
	 */
	private void addInfoBoxes(JFrame frame) {

		// Create the team name text field
		txtTeamName = new JTextField();
		txtTeamName.setFont(new Font("Tahoma", Font.PLAIN, 14));
		txtTeamName.setText("\r\n");
		txtTeamName.setBounds(378, 81, 218, 29);
		frame.getContentPane().add(txtTeamName);
		txtTeamName.setColumns(10);

		// Create the season length spinner
		spnrSeasonLength = new JSpinner();
		spnrSeasonLength.setFont(new Font("Tahoma", Font.PLAIN, 14));
		spnrSeasonLength.setModel(new SpinnerNumberModel(5, 5, 15, 1));
		spnrSeasonLength.setBounds(378, 136, 218, 26);
		frame.getContentPane().add(spnrSeasonLength);
		
		// Create the difficulty combo box
		comboDifficulty = new JComboBox<String>();
		comboDifficulty.setModel(new DefaultComboBoxModel<String>(new String[] {"Easy", "Hard"}));
		comboDifficulty.setBounds(378, 187, 218, 29);
		frame.getContentPane().add(comboDifficulty);
	}

	/**
	 * Adds the buttons to the frame.
	 * 
	 * @param frame The frame to add the buttons to.
	 */
	private void addButtons(JFrame frame) {

		// Create the continue button
		btnContinue = new JButton("Continue");
		btnContinue.setFont(new Font("Tahoma", Font.PLAIN, 14));
		btnContinue.setBounds(10, 247, 294, 63);
		frame.getContentPane().add(btnContinue);
		btnContinue.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				setupComplete();
			}
		});
		
		// Create the quit button
		btnQuit = new JButton("Quit\r\n");
		btnQuit.setFont(new Font("Tahoma", Font.PLAIN, 14));
		btnQuit.setBounds(314, 247, 300, 63);
		frame.getContentPane().add(btnQuit);
		btnQuit.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				getManager().quit();
			}
		});
	}
}
