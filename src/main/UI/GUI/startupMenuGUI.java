package main.UI.GUI;

import java.awt.EventQueue;

import javax.swing.JFrame;
import java.awt.GridLayout;
import javax.swing.JLabel;
import java.awt.Font;
import javax.swing.SwingConstants;
import java.awt.Color;
import javax.swing.JTextField;
import javax.swing.JSpinner;
import javax.swing.SpinnerNumberModel;
import javax.swing.JRadioButton;
import javax.swing.JToggleButton;
import javax.swing.JComboBox;
import javax.swing.DefaultComboBoxModel;
import javax.swing.JButton;

public class startupMenuGUI {

	private JFrame frame;
	private JTextField txtTeamName;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					startupMenuGUI window = new startupMenuGUI();
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
	public startupMenuGUI() {
		initialize();
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
		
		JSpinner spnrSeasonLength = new JSpinner();
		spnrSeasonLength.setFont(new Font("Tahoma", Font.PLAIN, 14));
		spnrSeasonLength.setModel(new SpinnerNumberModel(5, 5, 15, 1));
		spnrSeasonLength.setBounds(378, 136, 218, 26);
		frame.getContentPane().add(spnrSeasonLength);
		
		JComboBox comboDifficulty = new JComboBox();
		comboDifficulty.setModel(new DefaultComboBoxModel(new String[] {"Easy", "Hard"}));
		comboDifficulty.setBounds(378, 187, 218, 29);
		frame.getContentPane().add(comboDifficulty);
		
		JLabel lblChooseDifficulty = new JLabel("Choose your difficulty setting:");
		lblChooseDifficulty.setFont(new Font("Tahoma", Font.PLAIN, 14));
		lblChooseDifficulty.setBounds(10, 190, 218, 18);
		frame.getContentPane().add(lblChooseDifficulty);
		
		JButton btnContinue = new JButton("Continue");
		btnContinue.setFont(new Font("Tahoma", Font.PLAIN, 14));
		btnContinue.setBounds(10, 247, 294, 63);
		frame.getContentPane().add(btnContinue);
		
		JButton btnQuit = new JButton("Quit\r\n");
		btnQuit.setFont(new Font("Tahoma", Font.PLAIN, 14));
		btnQuit.setBounds(314, 247, 300, 63);
		frame.getContentPane().add(btnQuit);
	}
}
