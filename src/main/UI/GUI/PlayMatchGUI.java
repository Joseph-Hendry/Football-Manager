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

public class PlayMatchGUI extends Window {

	private JList<String> commentaryJList;
	private int currentIndex = 0;
	private ArrayList<String> commentaryList = new ArrayList<String>();
	private DefaultListModel<String> activeList = new DefaultListModel<String>();
	private JButton btnContinue;
	private GameManager manager;

	/**
	 * Create the application.
	 */
	public PlayMatchGUI(GameManager manager, Match match) {
		super("Play Match", manager);
		this.commentaryList = match.getCommentaryList();
		startTimer();
	}

	/**
	 * Initialize the contents of the frame.
	 */
	protected void initialise(JFrame frame) {
		frame.setResizable(false);
		frame.setBounds(100, 50, 560, 1000);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.getContentPane().setLayout(null);
		
		JLabel lblMatch = new JLabel("Match: ");
		lblMatch.setHorizontalAlignment(SwingConstants.CENTER);
		lblMatch.setFont(new Font("Dialog", Font.PLAIN, 18));
		lblMatch.setBounds(38, 10, 287, 43);
		frame.getContentPane().add(lblMatch);
		
		JLabel lblNewLabel = new JLabel("Live Commentary:");
		lblNewLabel.setBounds(38, 77, 132, 15);
		frame.getContentPane().add(lblNewLabel);
		
		commentaryJList = new JList<>(activeList);
		commentaryJList.setFont(new Font("Tahoma", Font.PLAIN, 18));
		commentaryJList.setBounds(38, 122, 483, 550);
        frame.getContentPane().add(commentaryJList);

		btnContinue = new JButton("Continue");
		btnContinue.setFont(new Font("Tahoma", Font.PLAIN, 18));
		btnContinue.setEnabled(false);
		btnContinue.setBounds(38, 700, 483, 80);
		frame.getContentPane().add(btnContinue);
		btnContinue.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				frame.dispose();
				manager.onMatchFinish();
			}
		});
    }

    private void startTimer() {
        Timer timer = new Timer(1, new ActionListener() {

            @Override
            public void actionPerformed(ActionEvent e) {
                if (currentIndex < commentaryList.size()) {
					activeList.add(currentIndex, commentaryList.get(currentIndex));
                	commentaryJList.setModel(activeList);
                    currentIndex++;
                } else {
                	btnContinue.setEnabled(true);
                    ((Timer)e.getSource()).stop();
                }
            }
        });
        timer.start();
    }
}