package main.UI.GUI;

import main.body.GameManager;

import javax.swing.*;
import java.awt.*;

public class StoreMenuGUI {

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
					StoreMenuGUI window = new StoreMenuGUI(manager);
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
	public StoreMenuGUI(GameManager manager) {
		this.manager = manager;
		initialize();
	}

	/**
	 * Initialize the contents of the frame.
	 */
	private void initialize() {
		frame = new JFrame();
		frame.setResizable(false);
		frame.setBounds(100, 100, 399, 365);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.getContentPane().setLayout(null);
		
		JLabel lblWelcomeMessage = new JLabel("Main Menu");
		lblWelcomeMessage.setHorizontalAlignment(SwingConstants.CENTER);
		lblWelcomeMessage.setFont(new Font("Tahoma", Font.PLAIN, 18));
		lblWelcomeMessage.setBounds(66, 12, 287, 43);
		frame.getContentPane().add(lblWelcomeMessage);
		
		 = new JButton("Club");
		btnClub.setFont(new Font("Tahoma", Font.PLAIN, 14));
		btnClub.setBounds(59, 77, 294, 63);
		frame.getContentPane().add(btnClub);
		
		btnStadium = new JButton("Stadium");
		btnStadium.setFont(new Font("Tahoma", Font.PLAIN, 14));
		btnStadium.setBounds(59, 152, 294, 63);
		frame.getContentPane().add(btnStadium);

		// Preform actions for each of these buttons redirecting to the appropriate GUI
		btnClub.addActionListener(e -> {
			ClubGUI clubGUI = new ClubGUI();
			clubGUI.main(null);
		});
		btnStadium.addActionListener(e -> {
			StadiumGUI stadiumGUI = new StadiumGUI();
			stadiumGUI.main(null);
		});
		btnStore.addActionListener(e -> {
			StoreGUI storeGUI = new StoreGUI();
			storeGUI.main(null);
		});
	}
}

