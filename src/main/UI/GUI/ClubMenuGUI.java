package main.UI.GUI;

import javax.swing.JFrame;

import main.body.Coach;
import main.body.GameManager;
import java.awt.Color;
import javax.swing.JLabel;
import java.awt.Font;
import javax.swing.SwingConstants;

public class ClubMenuGUI {

	private JFrame frame;
	private GameManager manager;

	/**
	 * Launch the application.
	 */
//	public static void main(String[] args) {
//		EventQueue.invokeLater(new Runnable() {
//			public void run() {
//				try {
//					ClubMenuGUI window = new ClubMenuGUI();
//					window.frame.setVisible(true);
//				} catch (Exception e) {
//					e.printStackTrace();
//				}
//			}
//		});
//	}

	/**
	 * Create the application.
	 */
	public ClubMenuGUI(GameManager manager) {
		this.manager = manager;
		initialize();
	}
	
	public JFrame getFrame() {
		return this.frame;
	}
	
	public GameManager getManager() {
		return this.manager;
	}

	/**
	 * Initialize the contents of the frame.
	 */
	private void initialize() {
		frame = new JFrame();
		frame.getContentPane().setBackground(new Color(255, 255, 255));
		frame.getContentPane().setLayout(null);
		
		JLabel lblTitle = new JLabel("Club Menu");
		lblTitle.setHorizontalAlignment(SwingConstants.CENTER);
		lblTitle.setFont(new Font("Tahoma", Font.PLAIN, 18));
		lblTitle.setBounds(264, 11, 93, 28);
		frame.getContentPane().add(lblTitle);
		
		JLabel lblTeamName = new JLabel("Team Name: " + getManager().getPlayerTeam().getName());
		lblTeamName.setFont(new Font("Tahoma", Font.PLAIN, 14));
		lblTeamName.setBounds(10, 58, 186, 28);
		frame.getContentPane().add(lblTeamName);
		
		JLabel lblTeamMoney = new JLabel("Team Money: " + getManager().getMoney());
		lblTeamMoney.setFont(new Font("Tahoma", Font.PLAIN, 14));
		lblTeamMoney.setBounds(431, 58, 193, 28);
		frame.getContentPane().add(lblTeamMoney);
		
		Coach coach = getManager().getPlayerTeam().getCoach();
		int[] coachStats = coach.getStats();
		String coachString = String.format("Coach: %s, Rarity: %s, ATK:%d MID:%d DEF:%d", coach.getName(), coach.getRarity(), coachStats[0], coachStats[1], coachStats[2]);
		JLabel lblCoach = new JLabel(coachString);
		lblCoach.setFont(new Font("Tahoma", Font.PLAIN, 14));
		lblCoach.setBounds(10, 97, 614, 28);
		frame.getContentPane().add(lblCoach);
		frame.setBounds(100, 100, 650, 500);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
	}

}
