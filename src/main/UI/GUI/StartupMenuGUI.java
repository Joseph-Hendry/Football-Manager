package main.UI.GUI;

import java.awt.EventQueue;

import javax.swing.JFrame;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JLabel;
import javax.swing.JOptionPane;

import java.awt.Font;
import javax.swing.SwingConstants;

import main.body.GameManager;

import java.awt.Color;
import javax.swing.JTextField;
import javax.swing.JSpinner;
import javax.swing.SpinnerNumberModel;
import javax.swing.JComboBox;
import javax.swing.DefaultComboBoxModel;
import javax.swing.JButton;

public class StartupMenuGUI {

	private JFrame frame;
	private JTextField txtTeamName;
	private JSpinner spnrSeasonLength;
	private JComboBox<String> comboDifficulty;
    private JButton btnContinue;
    
    private final GameManager manager;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					GUI ui = new GUI();
					GameManager manager = new GameManager(ui);
					StartupMenuGUI window = new StartupMenuGUI(manager);
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
	public StartupMenuGUI(GameManager manager) {
		this.manager = manager;
		initialize();
	}
	
	/**
	 * Completes the setup of our {@link RocketManager}.
	 */
	private void setupComplete() {
		System.out.println(txtTeamName.getText());
		System.out.println((int) spnrSeasonLength.getValue());
		System.out.println(comboDifficulty.getSelectedIndex());
		manager.onSetupFinish(txtTeamName.getText(), comboDifficulty.getSelectedIndex(), (int) spnrSeasonLength.getValue());
	}

	/**
	 * Initialize the contents of the frame.
	 */
	private void initialize() {
		frame = new JFrame();
		frame.getContentPane().setBackground(new Color(255, 255, 255));
		frame.setBounds(100, 100, 640, 360);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.getContentPane().setLayout(null);
		
		JLabel lblWelcomeMessage = new JLabel("Welcome To Football Manager!");
		lblWelcomeMessage.setHorizontalAlignment(SwingConstants.CENTER);
		lblWelcomeMessage.setFont(new Font("Tahoma", Font.PLAIN, 18));
		lblWelcomeMessage.setBounds(168, 11, 287, 43);
		frame.getContentPane().add(lblWelcomeMessage);
		
		JLabel lbChooseName = new JLabel("Please choose a name for your team (3-15 characters):");
		lbChooseName.setFont(new Font("Tahoma", Font.PLAIN, 14));
		lbChooseName.setBounds(10, 81, 347, 29);
		
		frame.getContentPane().add(lbChooseName);
		
		txtTeamName = new JTextField();
		txtTeamName.setFont(new Font("Tahoma", Font.PLAIN, 14));
		txtTeamName.setText("\r\n");
		txtTeamName.setBounds(378, 81, 218, 29);
		frame.getContentPane().add(txtTeamName);
		txtTeamName.setColumns(10);
		
		JLabel lblSeasonLength = new JLabel("Choose how long your season should last (5-15 weeks):");
		lblSeasonLength.setFont(new Font("Tahoma", Font.PLAIN, 14));
		lblSeasonLength.setBounds(10, 134, 347, 29);
		frame.getContentPane().add(lblSeasonLength);
		
		spnrSeasonLength = new JSpinner();
		spnrSeasonLength.setFont(new Font("Tahoma", Font.PLAIN, 14));
		spnrSeasonLength.setModel(new SpinnerNumberModel(5, 5, 15, 1));
		spnrSeasonLength.setBounds(378, 136, 218, 26);
		frame.getContentPane().add(spnrSeasonLength);
		
		comboDifficulty = new JComboBox<String>();
		comboDifficulty.setModel(new DefaultComboBoxModel<String>(new String[] {"Easy", "Hard"}));
		comboDifficulty.setBounds(378, 187, 218, 29);
		frame.getContentPane().add(comboDifficulty);
		
		JLabel lblChooseDifficulty = new JLabel("Choose your difficulty setting:");
		lblChooseDifficulty.setFont(new Font("Tahoma", Font.PLAIN, 14));
		lblChooseDifficulty.setBounds(10, 190, 218, 18);
		frame.getContentPane().add(lblChooseDifficulty);
		
		btnContinue = new JButton("Continue");
		btnContinue.setFont(new Font("Tahoma", Font.PLAIN, 14));
		btnContinue.setBounds(10, 247, 294, 63);
		frame.getContentPane().add(btnContinue);

		// Add action listener to the "Continue" button
		btnContinue.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				// Get the team name and season length values
				String teamName = txtTeamName.getText();
				int seasonLength = (int) spnrSeasonLength.getValue();

				// Check if both fields have been filled out
				if (teamName.length() >= 3 && teamName.length() <= 15 && seasonLength >= 5 && seasonLength <= 15) {
					System.out.println("This is working");
					setupComplete();
				} else {
					// If one or both fields are invalid, display an error message
					JOptionPane.showMessageDialog(frame, "Please enter a valid team name (3-15 characters) and season length (5-15 weeks).", "Error", JOptionPane.ERROR_MESSAGE);
				}
			}
		});

		JButton btnQuit = new JButton("Quit");
		btnQuit.setFont(new Font("Tahoma", Font.PLAIN, 14));
		btnQuit.setBounds(314, 247, 300, 63);
		frame.getContentPane().add(btnQuit);

		btnQuit.addActionListener(new ActionListener() {
		    public void actionPerformed(ActionEvent e) {
		        System.exit(0);
		    }
		});
	}
}
