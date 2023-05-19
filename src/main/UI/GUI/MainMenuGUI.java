package main.UI.GUI;

import main.body.GameManager;

import java.awt.Font;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.SwingConstants;

public class MainMenuGUI extends Window {

	private JButton btnClub;
	private JButton btnStadium;
	private JButton btnStore;

	/**
	 * Create the application.
	 */

	public MainMenuGUI(GameManager manager) {
		super("Main Menu", manager);
	}

	/**
	 * Initialize the contents of the frame.
	 */
	@Override
	protected void initialise(JFrame frame) {
		frame.setResizable(false);
		frame.setBounds(100, 100, 399, 365);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.getContentPane().setLayout(null);
		
		JLabel lblWelcomeMessage = new JLabel("Main Menu");
		lblWelcomeMessage.setHorizontalAlignment(SwingConstants.CENTER);
		lblWelcomeMessage.setFont(new Font("Tahoma", Font.PLAIN, 18));
		lblWelcomeMessage.setBounds(66, 12, 287, 43);
		frame.getContentPane().add(lblWelcomeMessage);
		
		JLabel lblMoney = new JLabel("Money: " + getManager().getMoney());
		lblMoney.setBounds(272, 57, 81, 15);
		frame.getContentPane().add(lblMoney);
		
		JLabel lblWeek = new JLabel("Week: " + getManager().getWeek() + "/" + getManager().getSeasonLength());
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

		// Perform actions for each of these buttons redirecting to the appropriate GUI
		btnClub.addActionListener(e -> {
			getManager().onMainMenuFinish(0);
		});
		btnStadium.addActionListener(e -> {
			getManager().onMainMenuFinish(1);
		});
		btnStore.addActionListener(e -> {
			getManager().onMainMenuFinish(2);
		});
	}
}
