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
		frame.setBounds(0, 0, 370, 252);
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
		JLabel lblWelcomeMessage = new JLabel("Stadium Menu");
		lblWelcomeMessage.setHorizontalAlignment(SwingConstants.CENTER);
		lblWelcomeMessage.setFont(new Font("Dialog", Font.PLAIN, 18));
		lblWelcomeMessage.setBounds(0, 10, 370, 40);
		frame.getContentPane().add(lblWelcomeMessage);

		JLabel lblMatchesToPlay = new JLabel("Matches to play:");
		lblMatchesToPlay.setBounds(10, 60, 150, 15);
		frame.getContentPane().add(lblMatchesToPlay);

		JLabel lblMatchesInfo = new JLabel(String.format("%-15s %-4s %-4s %-6s %-7s %-1s", "Team Name", "ATK", "MID", "DEF", "Points", "Reward($)"));
		lblMatchesInfo.setBounds(10, 85, 350, 15);
		lblMatchesInfo.setFont(new Font("Monospaced", Font.PLAIN, 11));
		frame.getContentPane().add(lblMatchesInfo);
		
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
		btnPlayMatch = new JButton("Play Match");
		btnPlayMatch.setBounds(10, 190, 110, 25);
		btnPlayMatch.setFont(new Font("Dialog", Font.BOLD, 11));
		frame.getContentPane().add(btnPlayMatch);
		btnPlayMatch.addActionListener(e -> {
			getManager().playMatch(matchJList.getSelectedValue());
		});
		
		// Create the take bye button
		btnTakeBye = new JButton("Take Bye");
		btnTakeBye.setBounds(130, 190, 110, 25);
		btnTakeBye.setFont(new Font("Dialog", Font.BOLD, 11));
		frame.getContentPane().add(btnTakeBye);
		btnTakeBye.addActionListener(e -> {
			// Ask the user what player they would like to train
			ArrayList<Player> players = new ArrayList<>();
			players.addAll(getManager().getPlayerTeam().getTeam());
			players.addAll(getManager().getPlayerTeam().getBench());

			Player playerToTrain = (Player) JOptionPane.showInputDialog(frame, "Which player would you like to train?",
					"Train Player", JOptionPane.QUESTION_MESSAGE, null, getManager().getPlayerTeam().getTeam().toArray(), null);
			
			getManager().takeBye(playerToTrain);
		});

		// Create the back button
		btnBack = new JButton("Back");
		btnBack.setBounds(250, 190, 110, 25);
		btnBack.setFont(new Font("Dialog", Font.BOLD, 11));
		frame.getContentPane().add(btnBack);
		btnBack.addActionListener(e -> {
			getManager().onStadiumMenuBack();
		});
	}
}