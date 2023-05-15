package main.UI.GUI;

import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JList;
import java.awt.Font;
import javax.swing.SwingConstants;
import javax.swing.DefaultListModel;
import javax.swing.JButton;

import main.body.Match;

import java.util.ArrayList;

import main.body.GameManager;

public class StadiumMenuGUI {

	private JFrame frame;
	private final GameManager manager;
	private JButton btnPlayMatch;
	private JButton btnBack;
	private JButton btnTakeBye;
	private JList<String> list;
	private DefaultListModel<String> matchList = new DefaultListModel<String>();


	/**
	 * Create the application.
	 */
	public StadiumMenuGUI(GameManager manager) {
		this.manager = manager;
		ArrayList<Match> matches = manager.getStadium().getPossibleMatches();
		for (Match match : matches) {
			matchList.addElement(match.toString());
		}
		initialize();
	}

	/**
	 * Get the frame.
	 */
	public JFrame getFrame() {
		return this.frame;
	}

	/**
	 * Initialize the contents of the frame.
	 */
	private void initialize() {
		frame = new JFrame();
		frame.getContentPane().setLayout(null);
		
		JLabel lblMatchesToPlay = new JLabel("Matches to play:");
		lblMatchesToPlay.setBounds(51, 81, 126, 15);
		frame.getContentPane().add(lblMatchesToPlay);
		
		list = new JList<>(matchList);
		list.setBounds(51, 108, 204, 136);
		frame.getContentPane().add(list);
		
		JLabel lblWelcomeMessage = new JLabel("Store Menu");
		lblWelcomeMessage.setHorizontalAlignment(SwingConstants.CENTER);
		lblWelcomeMessage.setFont(new Font("Dialog", Font.PLAIN, 18));
		lblWelcomeMessage.setBounds(63, 12, 287, 43);
		frame.getContentPane().add(lblWelcomeMessage);
		
		btnPlayMatch = new JButton("Play Match");
		btnPlayMatch.setBounds(27, 303, 117, 25);
		frame.getContentPane().add(btnPlayMatch);
		
		btnBack = new JButton("Back");
		btnBack.setBounds(184, 303, 117, 25);
		frame.getContentPane().add(btnBack);
		
		btnTakeBye = new JButton("Take Bye");
		btnTakeBye.setBounds(37, 340, 117, 25);
		frame.getContentPane().add(btnTakeBye);

		// Add listeners
		btnPlayMatch.addActionListener(e -> {
			try {
				manager.onStadiumMenuFinish(String.valueOf(list.getSelectedIndex() + 1));
				frame.dispose();
			} catch (Exception e1) {
				System.out.println(e1.getMessage());
				System.out.println(list.getSelectedIndex());
				//error.getFrame().setVisible(true);
			}
		});
		btnBack.addActionListener(e -> {
			try {
				manager.onStadiumMenuFinish("back");
				frame.dispose();
			} catch (Exception e1) {
				System.out.println(e1.getMessage());
				//error.getFrame().setVisible(true);
			}
		});
		btnTakeBye.addActionListener(e -> {
			try {
				manager.onStadiumMenuFinish("6");
				frame.dispose();
			} catch (Exception e1) {
				System.out.println(e1.getMessage());
				//error.getFrame().setVisible(true);
			}
		});
	}
}
