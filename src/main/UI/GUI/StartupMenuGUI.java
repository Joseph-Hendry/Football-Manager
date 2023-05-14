package main.UI.GUI;

import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JLabel;
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
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;

public class StartupMenuGUI {

	private JFrame frame;
	private JTextField txtTeamName;
	private GameManager manager;
	private JSpinner spnrSeasonLength;
	private JComboBox<String> comboDifficulty;
    private JButton btnContinue;

	/**
	 * Create the application.
	 */
	public StartupMenuGUI(GameManager manager) {
		this.manager = manager;
		initialize();
	}
		
	/**
	 * Completes the setup of {@link GameManager}.
	 */
	private void setupComplete() {
		manager.onSetupFinish(txtTeamName.getText(), comboDifficulty.getSelectedIndex(), (int) spnrSeasonLength.getValue());
		MainMenuGUI menu = new MainMenuGUI(manager);
		menu.getFrame().setVisible(true);
		frame.dispose();
	}
	
	public JFrame getFrame() {
		return this.frame;
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
		
		JLabel lblChooseName = new JLabel("Please choose a name for your team (3-15 characters):");
		lblChooseName.setFont(new Font("Tahoma", Font.PLAIN, 14));
		lblChooseName.setBounds(10, 81, 347, 29);
		frame.getContentPane().add(lblChooseName);
		
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
		btnContinue.addActionListener(new ActionListener() {
			
			public void actionPerformed(ActionEvent e) {
				String text = txtTeamName.getText().strip();
				if (txtTeamName.getText().strip().matches("[a-zA-Z0-9]+")) {
					if (text.length() >= 3 && text.length() <= 15) {
						setupComplete();
					}
				}
				else {
					String[] errorArg = {"Please enter a name with 3-15 characters."};
					ErrorMessageGUI.main(errorArg);
				}
			}
		});
		btnContinue.setFont(new Font("Tahoma", Font.PLAIN, 14));
		btnContinue.setBounds(10, 247, 294, 63);
		frame.getContentPane().add(btnContinue);
		
		JButton btnQuit = new JButton("Quit\r\n");
		btnQuit.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				System.exit(0);
			}
		});
		btnQuit.setFont(new Font("Tahoma", Font.PLAIN, 14));
		btnQuit.setBounds(314, 247, 300, 63);
		frame.getContentPane().add(btnQuit);
	}
}
