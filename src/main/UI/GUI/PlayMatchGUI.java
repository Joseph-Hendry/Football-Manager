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

public class PlayMatchGUI {

	private JFrame frame;
	private JList<String> commentaryJList;
	private int currentIndex = 0;
	private ArrayList<String> commentaryList = new ArrayList<String>();
	private DefaultListModel<String> activeList = new DefaultListModel<String>();
	private JButton btnContinue;
	private GameManager manager;
	private Match match;

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
		
		JLabel lblMatch = new JLabel("Match: ");
		lblMatch.setHorizontalAlignment(SwingConstants.CENTER);
		lblMatch.setFont(new Font("Dialog", Font.PLAIN, 18));
		lblMatch.setBounds(64, 12, 287, 43);
		frame.getContentPane().add(lblMatch);
		
		JLabel lblNewLabel = new JLabel("Live Commentary:");
		lblNewLabel.setBounds(38, 77, 132, 15);
		frame.getContentPane().add(lblNewLabel);
		
		commentaryJList = new JList<>(activeList);
		commentaryJList.setFont(new Font("Tahoma", Font.PLAIN, 18));
		commentaryJList.setBounds(10, 80, 800, 500);
        frame.getContentPane().add(commentaryJList);

		btnContinue = new JButton("Continue");
		btnContinue.setFont(new Font("Tahoma", Font.PLAIN, 18));
		btnContinue.setEnabled(false);
		btnContinue.setBounds(800, 10, 100, 25);
		btnContinue.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				frame.dispose();
				manager.onMatchFinish();
			}
		});
    }

    private void startTimer() {
        Timer timer = new Timer(500, new ActionListener() {

            @Override
            public void actionPerformed(ActionEvent e) {
                if (currentIndex < commentaryList.size()) {
					activeList.add(currentIndex, commentaryList.get(currentIndex));
                	commentaryJList.setModel(activeList);
                    currentIndex++;
                } else {
                    ((Timer)e.getSource()).stop();
					btnContinue.setEnabled(true);
                }
            }
        });
        timer.start();
    }
}
