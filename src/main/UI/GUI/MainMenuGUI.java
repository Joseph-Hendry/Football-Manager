package main.UI.GUI;

import main.body.GameManager;

import java.awt.EventQueue;
import java.awt.Font;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.SwingConstants;

import java.awt.BorderLayout;

public class MainMenuGUI {

	JFrame frame;
	private JButton btnClub;
	private JButton btnStadium;
	private JButton btnStore;

	protected GameManager manager;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					GUI ui = new GUI();
					MainMenuGUI window = new MainMenuGUI();
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
	public MainMenuGUI() {
		initialize();
	}
	
	public void setManager(GameManager manager) {
		this.manager = manager;
	}
	
	public JFrame getFrame() {
		return this.frame;
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
		
		JLabel lblMoney = new JLabel("Money: " + manager.getMoney());
		lblMoney.setBounds(283, 57, 70, 15);
		frame.getContentPane().add(lblMoney);
		
		JLabel lblWeek = new JLabel("Week: " + manager.getWeek() + "/" + manager.getSeasonLength());
		lblWeek.setBounds(59, 57, 70, 15);
		frame.getContentPane().add(lblWeek);
		
		btnClub = new JButton("Club");
		btnClub.setFont(new Font("Tahoma", Font.PLAIN, 14));
		btnClub.setBounds(59, 77, 294, 63);
		frame.getContentPane().add(btnClub);
		
		btnStadium = new JButton("Stadium");
		btnStadium.setFont(new Font("Tahoma", Font.PLAIN, 14));
		btnStadium.setBounds(59, 152, 294, 63);
		frame.getContentPane().add(btnStadium);
		
		btnStore = new JButton("Store");
		btnStore.setFont(new Font("Tahoma", Font.PLAIN, 14));
		btnStore.setBounds(59, 227, 294, 63);
		frame.getContentPane().add(btnStore);

		// Preform actions for each of these buttons redirecting to the appropriate GUI
		btnClub.addActionListener(e -> {
//			ClubGUI clubGUI = new ClubGUI();
//			clubGUI.main(null);
		});
		btnStadium.addActionListener(e -> {
//			StadiumMenuGUI.StadiumMenuGUI(manager);
		});
		btnStore.addActionListener(e -> {
//			StoreGUI storeGUI = new StoreGUI();
//			storeGUI.main(null);
		});
	}
}
