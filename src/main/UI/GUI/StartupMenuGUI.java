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
	private JTextField teamNameTextField;

	// Setup JSpinners
	private JSpinner seasonLengthSpinner;

	// Setup JComboBox
	private JComboBox<String> difficultyComboBox;

	// Setup JButtons
    private JButton continueButton;
	private JButton quitButton;

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
		getManager().onSetupFinish(teamNameTextField.getText(), difficultyComboBox.getSelectedIndex(), (int) seasonLengthSpinner.getValue());
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
		JLabel titleLabel = new JLabel("Welcome To Football Manager!");
		titleLabel.setHorizontalAlignment(SwingConstants.CENTER);
		titleLabel.setFont(new Font("Tahoma", Font.PLAIN, 18));
		titleLabel.setBounds(168, 11, 287, 43);
		frame.getContentPane().add(titleLabel);
		
		JLabel nameLabel = new JLabel("Please choose a team name (3-15 characters):");
		nameLabel.setFont(new Font("Tahoma", Font.PLAIN, 14));
		nameLabel.setBounds(10, 81, 347, 29);
		frame.getContentPane().add(nameLabel);
		
		JLabel seasonLengthLabel = new JLabel("Choose a season length (5-15 weeks):");
		seasonLengthLabel.setFont(new Font("Tahoma", Font.PLAIN, 14));
		seasonLengthLabel.setBounds(10, 134, 347, 29);
		frame.getContentPane().add(seasonLengthLabel);
		
		JLabel difficultyLabel = new JLabel("Choose your difficulty setting:");
		difficultyLabel.setFont(new Font("Tahoma", Font.PLAIN, 14));
		difficultyLabel.setBounds(10, 190, 218, 18);
		frame.getContentPane().add(difficultyLabel);
	}

	/**
	 * Adds the text field and combo box and spinner to the frame.
	 * 
	 * @param frame The frame to add the items to.
	 */
	private void addInfoBoxes(JFrame frame) {

		// Create the team name text field
		teamNameTextField = new JTextField();
		teamNameTextField.setFont(new Font("Tahoma", Font.PLAIN, 14));
		teamNameTextField.setText("\r\n");
		teamNameTextField.setBounds(378, 81, 218, 29);
		frame.getContentPane().add(teamNameTextField);
		teamNameTextField.setColumns(10);

		// Create the season length spinner
		seasonLengthSpinner = new JSpinner();
		seasonLengthSpinner.setFont(new Font("Tahoma", Font.PLAIN, 14));
		seasonLengthSpinner.setModel(new SpinnerNumberModel(5, 5, 15, 1));
		seasonLengthSpinner.setBounds(378, 136, 218, 26);
		frame.getContentPane().add(seasonLengthSpinner);
		
		// Create the difficulty combo box
		difficultyComboBox = new JComboBox<String>();
		difficultyComboBox.setModel(new DefaultComboBoxModel<String>(new String[] {"Easy", "Hard"}));
		difficultyComboBox.setBounds(378, 187, 218, 29);
		frame.getContentPane().add(difficultyComboBox);
	}

	/**
	 * Adds the buttons to the frame.
	 * 
	 * @param frame The frame to add the buttons to.
	 */
	private void addButtons(JFrame frame) {

		// Create the continue button
		continueButton = new JButton("Continue");
		continueButton.setFont(new Font("Tahoma", Font.PLAIN, 14));
		continueButton.setBounds(10, 247, 294, 63);
		frame.getContentPane().add(continueButton);
		continueButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				setupComplete();
			}
		});
		
		// Create the quit button
		quitButton = new JButton("Quit\r\n");
		quitButton.setFont(new Font("Tahoma", Font.PLAIN, 14));
		quitButton.setBounds(314, 247, 300, 63);
		frame.getContentPane().add(quitButton);
		quitButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				getManager().quit();
			}
		});
	}
}
