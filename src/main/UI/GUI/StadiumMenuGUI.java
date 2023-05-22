package main.UI.GUI;

import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JList;
import javax.swing.JOptionPane;
import javax.swing.ListSelectionModel;

import java.awt.Font;
import java.util.ArrayList;

import javax.swing.SwingConstants;
import javax.swing.DefaultListModel;
import javax.swing.JButton;

import main.body.Match;
import main.body.Player;
import main.body.GameManager;

/**
 * The GUI for the stadium menu.
 * Used to display the matches and let users play match or take bye.
 */
public class StadiumMenuGUI extends Window {

	// Setup JButtons
	private JButton playMatchButton;
	private JButton backButton;
	private JButton takeByeButton;

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
		frame.setBounds(0, 0, 370, 252);

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
		JLabel titleLabel = new JLabel("Stadium Menu");
		titleLabel.setHorizontalAlignment(SwingConstants.CENTER);
		titleLabel.setFont(new Font("Dialog", Font.PLAIN, 18));
		titleLabel.setBounds(0, 10, 370, 40);
		frame.getContentPane().add(titleLabel);

		JLabel matchesLabel = new JLabel("Matches to play:");
		matchesLabel.setBounds(10, 60, 150, 15);
		frame.getContentPane().add(matchesLabel);

		JLabel matchInfoLabel = new JLabel(String.format("%-15s %-4s %-4s %-6s %-7s %-1s", "Team Name", "ATK", "MID", "DEF", "Points", "Reward($)"));
		matchInfoLabel.setBounds(10, 85, 350, 15);
		matchInfoLabel.setFont(new Font("Monospaced", Font.PLAIN, 11));
		frame.getContentPane().add(matchInfoLabel);
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

		// Create the match JList
		matchJList = new JList<>(matchList);
		matchJList.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
		matchJList.setBounds(10, 100, 350, 80);
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
		playMatchButton = new JButton("Play Match");
		playMatchButton.setBounds(10, 190, 110, 25);
		playMatchButton.setFont(new Font("Dialog", Font.BOLD, 11));
		frame.getContentPane().add(playMatchButton);
		playMatchButton.addActionListener(e -> {
			getManager().playMatch(matchJList.getSelectedValue());
		});
		
		// Create the take bye button
		takeByeButton = new JButton("Take Bye");
		takeByeButton.setBounds(130, 190, 110, 25);
		takeByeButton.setFont(new Font("Dialog", Font.BOLD, 11));
		frame.getContentPane().add(takeByeButton);
		takeByeButton.addActionListener(e -> {
			// Ask the user what player they would like to train
			ArrayList<Player> players = new ArrayList<>();
			players.addAll(getManager().getPlayerTeam().getTeam());
			players.addAll(getManager().getPlayerTeam().getBench());

			Player playerToTrain = (Player) JOptionPane.showInputDialog(frame, "Which player would you like to train?",
					"Train Player", JOptionPane.QUESTION_MESSAGE, null, getManager().getPlayerTeam().getTeam().toArray(), null);
			
			getManager().takeBye(playerToTrain);
		});

		// Create the back button
		backButton = new JButton("Back");
		backButton.setBounds(250, 190, 110, 25);
		backButton.setFont(new Font("Dialog", Font.BOLD, 11));
		frame.getContentPane().add(backButton);
		backButton.addActionListener(e -> {
			getManager().onStadiumMenuBack();
		});
	}
}