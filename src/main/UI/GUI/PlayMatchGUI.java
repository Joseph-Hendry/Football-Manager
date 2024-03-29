package main.UI.GUI;

import java.util.ArrayList;

import javax.swing.DefaultListModel;
import javax.swing.JFrame;
import javax.swing.JLabel;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.SwingConstants;
import javax.swing.Timer;
import javax.swing.JList;
import javax.swing.JButton;

import main.body.GameManager;
import main.body.Match;

/**
 * The GUI for the play match menu.
 * Used to display the play match commentary.
 */
public class PlayMatchGUI extends Window {

	// Setup JButton
	private JButton continueButton;

	// Set match JLabel
	private JLabel titleLabel;

	// Setup JList
	private JList<String> commentaryJList;

	// Match
	private Match match;

	// Current index
	private int currentIndex = 0;

	/**
	 * Create the application.
	 * 
	 * @param manager The {@link GameManager} instance.
	 * @param match The match being played.
	 */
	public PlayMatchGUI(GameManager manager, Match match) {
		super("Play Match", manager);
		this.match = match;
		titleLabel.setText("Match: " + getManager().getPlayerTeam().getName() + " vs " + match.getOpposingTeam().getName());
		startTimer();
	}

	/**
	 * Initialize the contents of the frame.
	 * 
	 * @param frame The frame to add the contents to.
	 */
	protected void initialise(JFrame frame) {
		frame.setBounds(0, 0, 470, 583);

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
		titleLabel = new JLabel("Match");
		titleLabel.setHorizontalAlignment(SwingConstants.CENTER);
		titleLabel.setFont(new Font("Tahoma", Font.PLAIN, 18));
		titleLabel.setBounds(0, 10, 470, 40);
		frame.getContentPane().add(titleLabel);
		
		JLabel commentaryLabel = new JLabel("Live Commentary:");
		commentaryLabel.setBounds(10, 75, 150, 15);
		frame.getContentPane().add(commentaryLabel);
	}

	/**
	 * Adds the list to the frame.
	 * 
	 * @param frame The frame to add the list to.
	 */
	private void addLists(JFrame frame) {

		// Create the commentary JList
		DefaultListModel<String> activeList = new DefaultListModel<String>();
		commentaryJList = new JList<>(activeList);
		commentaryJList.setBounds(10, 100, 450, 355);
        frame.getContentPane().add(commentaryJList);
	}

	/**
	 * Adds the button to the frame.
	 * 
	 * @param frame The frame to add the button to.
	 */
	private void addButtons(JFrame frame) {

		// Add the continue button
		continueButton = new JButton("Continue");
		continueButton.setFont(new Font("Tahoma", Font.PLAIN, 18));
		continueButton.setEnabled(false);
		continueButton.setBounds(10, 465, 450, 80);
		frame.getContentPane().add(continueButton);
		continueButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				getManager().onMatchFinish();
			}
		});
	}

	/**
	 * Starts the timer to display the commentary.
	 */
    private void startTimer() {
        Timer timer = new Timer(750, new ActionListener() {
			ArrayList<String> commentaryList = match.getCommentaryList();
			DefaultListModel<String> activeList = new DefaultListModel<String>();

            @Override
            public void actionPerformed(ActionEvent e) {
                if (currentIndex < commentaryList.size()) {
					activeList.add(currentIndex, commentaryList.get(currentIndex));
                	commentaryJList.setModel(activeList);
                    currentIndex++;
                } else {
                	continueButton.setEnabled(true);
                    ((Timer)e.getSource()).stop();
                }
            }
        });
		timer.start();
    }
}