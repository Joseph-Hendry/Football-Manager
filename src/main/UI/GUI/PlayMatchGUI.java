package main.UI.GUI;

import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JLabel;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.SwingConstants;
import javax.swing.Timer;

public class PlayMatchGUI {

	private JFrame frame;
	private JLabel commentaryString;
	private int currentIndex = 0;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					PlayMatchGUI window = new PlayMatchGUI();
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
	public PlayMatchGUI() {
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
		commentaryString.setBounds(10, 80, 414, 44);
        frame.getContentPane().add(commentaryString);
    }

    private void startTimer() {
        Timer timer = new Timer(500, new ActionListener() {

            @Override
            public void actionPerformed(ActionEvent e) {
                if (currentIndex < stringList.length) {
                	commentaryString.setText(stringList[currentIndex]);
                    currentIndex++;
                } else {
                    ((Timer)e.getSource()).stop(); // Stop the timer when all strings are displayed
                }
            }
        });
        timer.start();
    }

	
}
