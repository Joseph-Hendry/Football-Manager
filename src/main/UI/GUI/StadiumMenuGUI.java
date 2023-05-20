package main.UI.GUI;

import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JList;
import javax.swing.ListSelectionModel;

import java.awt.Font;
import javax.swing.SwingConstants;
import javax.swing.DefaultListModel;
import javax.swing.JButton;

import main.body.Match;

import main.body.GameManager;

/**
 * The GUI for the stadium menu.
 * Used to display the matches and let users play match or take bye.
 */
public class StadiumMenuGUI extends Window {

	// Setup JButtons
	private JButton btnPlayMatch;
	private JButton btnBack;
	private JButton btnTakeBye;

	// Setup JList
	private JList<Match> matchJList;

	/**
	 * Create the application.
	 * 
	 * @param manager The {@link GameManager} instance.
	 */
	public StadiumMenuGUI(GameManager manager) {
		super("Stadium Menu", manager);
	}

	/**
	 * Initialize the contents of the frame.
	 */
	@Override
	protected void initialise(JFrame frame) {
		frame.setBounds(100, 100, 480, 270);
		frame.getContentPane().setLayout(null);
		frame.setResizable(false);

		addLabels(frame);
		addLists(frame);
		addButtons(frame);
	}

	/**
	 * Adds the labels to the frame.
	 * 
	 * @param frame The frame to add the labels to.
	 */
	private void addLabels(JFrame frame) {

		// Create the windows labels
		JLabel lblMatchesToPlay = new JLabel("Matches to play:");
		lblMatchesToPlay.setBounds(51, 40, 126, 15);
		frame.getContentPane().add(lblMatchesToPlay);

		JLabel lblWelcomeMessage = new JLabel("Stadium Menu");
		lblWelcomeMessage.setHorizontalAlignment(SwingConstants.CENTER);
		lblWelcomeMessage.setFont(new Font("Dialog", Font.PLAIN, 18));
		lblWelcomeMessage.setBounds(63, 12, 287, 43);
		frame.getContentPane().add(lblWelcomeMessage);
	}

	/**
	 * Adds the lists to the frame.
	 * 
	 * @param frame The frame to add the lists to.
	 */
	private void addLists(JFrame frame) {
		
		// Set up the match list
		DefaultListModel<Match> matchList = new DefaultListModel<>();
		for (Match match : getManager().getStadium().getPossibleMatches()) {
			matchList.addElement(match);
		}

		// Create the match Jlist
		matchJList = new JList<>(matchList);
		matchJList.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
		matchJList.setBounds(51, 59, 371, 115);
		matchJList.setFont(new Font("Monospaced", Font.PLAIN, 11));
		frame.getContentPane().add(matchJList);
	}

	/**
	 * Adds the buttons to the frame.
	 * 
	 * @param frame The frame to add the buttons to.
	 */
	private void addButtons(JFrame frame) {

		// Create the play match button
		btnPlayMatch = new JButton("Play Match");
		btnPlayMatch.setBounds(51, 184, 117, 25);
		frame.getContentPane().add(btnPlayMatch);
		btnPlayMatch.addActionListener(e -> {
			getManager().playMatch(matchJList.getSelectedValue());
		});
		
		// Create the take bye button
		btnTakeBye = new JButton("Take Bye");
		btnTakeBye.setBounds(178, 184, 117, 25);
		frame.getContentPane().add(btnTakeBye);
		btnTakeBye.addActionListener(e -> {
			getManager().takeBye();
		});

		// Create the back button
		btnBack = new JButton("Back");
		btnBack.setBounds(305, 184, 117, 25);
		frame.getContentPane().add(btnBack);
		btnBack.addActionListener(e -> {
			getManager().onStadiumMenuBack();
		});
	}
}