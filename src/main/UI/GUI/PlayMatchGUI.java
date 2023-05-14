package main.UI.GUI;

import java.util.ArrayList;

import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JLabel;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.SwingConstants;
import javax.swing.Timer;

import main.body.GameManager;
import main.body.Match;
import main.body.Team;

public class PlayMatchGUI {

	private JFrame frame;
	private JLabel commentaryString;
	private int currentIndex = 0;
	private ArrayList<String> commentaryList;
	private GameManager manager;
	private Match match;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					// Test the PlayMatchGUI
					GUI ui = new GUI();
					GameManager manager = new GameManager(ui);
					Team playerTeam = Team.createRandomTeam(50, "HomeTeam");
					Team NPCTeam = Team.createRandomTeam(50, "AwayTeam");
					Match match = new Match(playerTeam, NPCTeam, 5, 5);
					PlayMatchGUI window = new PlayMatchGUI(manager, match);
					window.frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Create the application.
	 */
	public PlayMatchGUI(GameManager manager, Match match) {
		this.manager = manager;
		this.match = match;
		match.playMatch(manager);
		commentaryList = match.getCommentaryList();
		initialize();
		startTimer();
	}

	/**
	 * Initialize the contents of the frame.
	 */
	private void initialize() {
		frame = new JFrame();
		frame.getContentPane().setLayout(null);
		
		JLabel lblMatch = new JLabel("Match: ");
		lblMatch.setHorizontalAlignment(SwingConstants.CENTER);
		lblMatch.setFont(new Font("Dialog", Font.PLAIN, 18));
		lblMatch.setBounds(64, 12, 287, 43);
		frame.getContentPane().add(lblMatch);
		
		JLabel lblNewLabel = new JLabel("Live Commentary:");
		lblNewLabel.setBounds(38, 77, 132, 15);
		frame.getContentPane().add(lblNewLabel);
		
		commentaryString = new JLabel("");
		commentaryString.setHorizontalAlignment(SwingConstants.CENTER);
		commentaryString.setFont(new Font("Tahoma", Font.PLAIN, 18));
		commentaryString.setBounds(10, 80, 800, 500);
        frame.getContentPane().add(commentaryString);
    }

    private void startTimer() {
        Timer timer = new Timer(500, new ActionListener() {

            @Override
            public void actionPerformed(ActionEvent e) {
                if (currentIndex < commentaryList.size()) {
                	commentaryString.setText(commentaryString.getText() + "<html>" + commentaryList.get(currentIndex) + "<br/>");
                    currentIndex++;
                } else {
                    ((Timer)e.getSource()).stop();
                }
            }
        });
        timer.start();
    }
}
