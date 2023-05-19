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
		frame.setResizable(false);
		frame.setBounds(100, 100, 480, 270);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.getContentPane().setLayout(null);
		
		JLabel lblMatchesToPlay = new JLabel("Matches to play:");
		lblMatchesToPlay.setBounds(51, 40, 126, 15);
		frame.getContentPane().add(lblMatchesToPlay);
		
		list = new JList<>(matchList);
		list.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
		list.setBounds(51, 59, 371, 115);
		list.setFont(new Font("Monospaced", Font.PLAIN, 11));
		frame.getContentPane().add(list);
		
		JLabel lblWelcomeMessage = new JLabel("Stadium Menu");
		lblWelcomeMessage.setHorizontalAlignment(SwingConstants.CENTER);
		lblWelcomeMessage.setFont(new Font("Dialog", Font.PLAIN, 18));
		lblWelcomeMessage.setBounds(63, 12, 287, 43);
		frame.getContentPane().add(lblWelcomeMessage);
		
		btnPlayMatch = new JButton("Play Match");
		btnPlayMatch.setBounds(51, 184, 117, 25);
		frame.getContentPane().add(btnPlayMatch);
		
		btnBack = new JButton("Back");
		btnBack.setBounds(305, 184, 117, 25);
		frame.getContentPane().add(btnBack);
		
		btnTakeBye = new JButton("Take Bye");
		btnTakeBye.setBounds(178, 184, 117, 25);
		frame.getContentPane().add(btnTakeBye);

		btnPlayMatch.addActionListener(e -> {
			try {
				manager.onStadiumMenuFinish(String.valueOf(list.getSelectedIndex() + 1));
				frame.dispose();
			} catch (Exception error) {
				ShowMessage.showMessage(error.getMessage());
			}
		});
		btnBack.addActionListener(e -> {
			try {
				manager.onStadiumMenuFinish("back");
				frame.dispose();
			} catch (Exception error) {
				ShowMessage.showMessage(error.getMessage());
			}
		});
		btnTakeBye.addActionListener(e -> {
			try {
				manager.onStadiumMenuFinish("6");
				frame.dispose();
			} catch (Exception error) {
				ShowMessage.showMessage(error.getMessage());
			}
		});
	}
}