package main.UI.GUI;

import javax.swing.JFrame;

import main.body.Coach;
import main.body.GameManager;
import main.body.Player;
import main.body.Team;

import java.awt.Color;
import javax.swing.JLabel;
import java.awt.Font;
import java.util.ArrayList;
import java.util.List;

import javax.swing.SwingConstants;
import javax.swing.JList;
import javax.swing.ListSelectionModel;
import javax.swing.border.LineBorder;
import javax.swing.border.BevelBorder;
import javax.swing.AbstractListModel;
import javax.swing.UIManager;
import javax.swing.JSeparator;
import javax.swing.JTextArea;

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
		lblTitle.setBounds(322, 11, 93, 28);
		frame.getContentPane().add(lblTitle);
		
		JLabel lblTeamNameDesc = new JLabel("Team Name");
		lblTeamNameDesc.setHorizontalAlignment(SwingConstants.CENTER);
		lblTeamNameDesc.setFont(new Font("Tahoma", Font.BOLD, 14));
		lblTeamNameDesc.setBounds(10, 56, 239, 17);
		frame.getContentPane().add(lblTeamNameDesc);
	
		String[] playersDisplay = getPlayersDisplay(getManager().getPlayerTeam().getTeam());
		
		JList listPlayers = new JList();
		listPlayers.setLayoutOrientation(JList.VERTICAL_WRAP);
		listPlayers.setModel(new AbstractListModel() {
			String[] values = playersDisplay;
			public int getSize() {
				return values.length;
			}
			public Object getElementAt(int index) {
				return values[index];
			}
		});
		listPlayers.setBorder(new LineBorder(new Color(0, 0, 0), 1, true));
		listPlayers.setVisibleRowCount(11);
		listPlayers.setFont(new Font("Monospaced", Font.PLAIN, 11));
		listPlayers.setToolTipText("");
		listPlayers.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
		listPlayers.setBounds(10, 173, 353, 200);
		frame.getContentPane().add(listPlayers);
		
		JLabel lblPlayerListDesc1 = new JLabel("Name                 Stats                   Position            Rarity            Value");
		lblPlayerListDesc1.setBounds(10, 157, 359, 14);
		frame.getContentPane().add(lblPlayerListDesc1);
		
		String[] reservesDisplay = getPlayersDisplay(getManager().getPlayerTeam().getBench());
		
		JList listReserves = new JList();
		listReserves.setModel(new AbstractListModel() {
			String[] values = reservesDisplay;
			public int getSize() {
				return values.length;
			}
			public Object getElementAt(int index) {
				return values[index];
			}
		});
		listReserves.setVisibleRowCount(11);
		listReserves.setToolTipText("");
		listReserves.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
		listReserves.setLayoutOrientation(JList.VERTICAL_WRAP);
		listReserves.setFont(new Font("Monospaced", Font.PLAIN, 11));
		listReserves.setBorder(new LineBorder(new Color(0, 0, 0), 1, true));
		listReserves.setBounds(371, 173, 353, 200);
		frame.getContentPane().add(listReserves);
		
		JLabel lblPlayerListDesc1_1 = new JLabel("Name                 Stats                   Position            Rarity            Value");
		lblPlayerListDesc1_1.setBounds(371, 157, 359, 14);
		frame.getContentPane().add(lblPlayerListDesc1_1);
		
		JLabel lblTeamTitle = new JLabel("Team");
		lblTeamTitle.setHorizontalAlignment(SwingConstants.CENTER);
		lblTeamTitle.setFont(new Font("Tahoma", Font.PLAIN, 14));
		lblTeamTitle.setBounds(161, 129, 46, 14);
		frame.getContentPane().add(lblTeamTitle);
		
		JLabel lblReserveTitle = new JLabel("Reserves");
		lblReserveTitle.setHorizontalAlignment(SwingConstants.CENTER);
		lblReserveTitle.setFont(new Font("Tahoma", Font.PLAIN, 14));
		lblReserveTitle.setBounds(517, 129, 67, 14);
		frame.getContentPane().add(lblReserveTitle);
		
		JLabel lblTeamMoneyDesc = new JLabel("Team Money");
		lblTeamMoneyDesc.setHorizontalAlignment(SwingConstants.CENTER);
		lblTeamMoneyDesc.setFont(new Font("Tahoma", Font.BOLD, 14));
		lblTeamMoneyDesc.setBounds(248, 56, 239, 17);
		frame.getContentPane().add(lblTeamMoneyDesc);
		
		JLabel lblCoachDesc = new JLabel("Coach");
		lblCoachDesc.setHorizontalAlignment(SwingConstants.CENTER);
		lblCoachDesc.setFont(new Font("Tahoma", Font.BOLD, 14));
		lblCoachDesc.setBounds(485, 56, 239, 17);
		frame.getContentPane().add(lblCoachDesc);
		
		JLabel lblTeamName = new JLabel(getManager().getPlayerTeam().getName());
		lblTeamName.setHorizontalAlignment(SwingConstants.CENTER);
		lblTeamName.setFont(new Font("Tahoma", Font.PLAIN, 14));
		lblTeamName.setBounds(10, 76, 239, 17);
		frame.getContentPane().add(lblTeamName);
		
		JLabel lblTeamMoney = new JLabel("" + getManager().getMoney());
		lblTeamMoney.setHorizontalAlignment(SwingConstants.CENTER);
		lblTeamMoney.setFont(new Font("Tahoma", Font.PLAIN, 14));
		lblTeamMoney.setBounds(248, 79, 239, 17);
		frame.getContentPane().add(lblTeamMoney);
		
		Coach coach = getManager().getPlayerTeam().getCoach();
		int[] coachStats = coach.getStats();
		
		JSeparator separator = new JSeparator();
		separator.setOrientation(SwingConstants.VERTICAL);
		separator.setBackground(new Color(0, 0, 0));
		separator.setBounds(259, 56, 1, 62);
		frame.getContentPane().add(separator);
		
		JSeparator separator_1 = new JSeparator();
		separator_1.setOrientation(SwingConstants.VERTICAL);
		separator_1.setBackground(Color.BLACK);
		separator_1.setBounds(485, 56, 1, 62);
		frame.getContentPane().add(separator_1);
		
		JTextArea textAreaCoach = new JTextArea();
		textAreaCoach.setEditable(false);
		textAreaCoach.setWrapStyleWord(true);
		textAreaCoach.setLineWrap(true);
		textAreaCoach.setFont(new Font("Tahoma", Font.PLAIN, 14));
		textAreaCoach.setText(String.format("Name: %s Stats: [%d %d %d] Rarity: %s Value: %s", coach.getName(), coachStats[0], coachStats[1], coachStats[2], coach.getRarity(), coach.getValue()));
		textAreaCoach.setBounds(495, 74, 229, 44);
		frame.getContentPane().add(textAreaCoach);
		frame.setBounds(100, 100, 750, 500);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
	}

	/**
	 * Takes in a list of players and returns a String[] array of , used to display in the JList of players.
	 * @param playerList	The list of players.
	 * @return				String[] of player names.
	 */
	public String[] getPlayersDisplay(ArrayList<Player> playerList) {
		ArrayList<String> playerNames = new ArrayList<String>();
		for (Player player : playerList) {
			playerNames.add(player.toString());
		}
		String[] nameArray = playerNames.toArray(new String[playerNames.size()]);
		return nameArray;
	}
}


