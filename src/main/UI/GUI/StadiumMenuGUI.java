package main.UI.GUI;

import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JList;
import java.awt.Font;
import javax.swing.SwingConstants;
import javax.swing.JButton;

public class StadiumMenuGUI {

	private JFrame frame;


	/**
	 * Create the application.
	 */
	public StadiumMenuGUI() {
		initialize();
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
		
		JList list = new JList();
		list.setBounds(51, 108, 204, 136);
		frame.getContentPane().add(list);
		
		JLabel lblWelcomeMessage = new JLabel("Store Menu");
		lblWelcomeMessage.setHorizontalAlignment(SwingConstants.CENTER);
		lblWelcomeMessage.setFont(new Font("Dialog", Font.PLAIN, 18));
		lblWelcomeMessage.setBounds(63, 12, 287, 43);
		frame.getContentPane().add(lblWelcomeMessage);
		
		JButton btnPlayMatch = new JButton("Play Match");
		btnPlayMatch.setBounds(27, 303, 117, 25);
		frame.getContentPane().add(btnPlayMatch);
		
		JButton btnBack = new JButton("Back");
		btnBack.setBounds(184, 303, 117, 25);
		frame.getContentPane().add(btnBack);
		
		JButton btnTakeBye = new JButton("Take Bye");
		btnTakeBye.setBounds(37, 340, 117, 25);
		frame.getContentPane().add(btnTakeBye);
	}
}
