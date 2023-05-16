package main.UI.GUI;

import javax.swing.JFrame;

import main.body.Coach;
import main.body.GameManager;
import main.body.Player;
import main.body.Item;

import java.awt.Color;
import javax.swing.JLabel;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.SwingConstants;
import javax.swing.JList;
import javax.swing.JOptionPane;
import javax.swing.ListSelectionModel;
import javax.swing.border.LineBorder;
import javax.swing.DefaultListModel;
import javax.swing.JButton;
import javax.swing.UIManager;
import javax.swing.JSeparator;
import javax.swing.JTextArea;


public class ClubMenuGUI {

	private JFrame frame;
	private GameManager manager;
	private DefaultListModel<String> reservesList = new DefaultListModel<String>();
	private DefaultListModel<String> playerList = new DefaultListModel<String>();
	private DefaultListModel<String> itemsList = new DefaultListModel<String>();
	private int recentSelectedPlayer = -1;

	/**
	 * Create the application.
	 */
	public ClubMenuGUI(GameManager manager) {
		this.manager = manager;
		for (Player player : manager.getPlayerTeam().getTeam()) {
			try {
				playerList.addElement(player.toString());
			} catch (Exception e) {
				playerList.addElement("Empty");
			}
		}
		for (Player player : manager.getPlayerTeam().getBench()) {
			try {
				reservesList.addElement(player.toString());
			} catch (Exception e) {
				reservesList.addElement("Empty");
			}
		}
		for (Item item : manager.getPlayerTeam().getItems()) {
			try {
				itemsList.addElement(item.toString());
			} catch (Exception e) {
				itemsList.addElement("Empty");
			}
		}
		initialize();
	}
	
	public GameManager getManager() {
		return this.manager;
	}

	/**
	 * Get the frame.
	 */
	public JFrame getFrame() {
		return this.frame;
	}

	/**
	 * Initialize the contents of the frame.
	 */
	private void initialize() {
		frame = new JFrame();
		frame.getContentPane().setBackground(new Color(240, 240, 240));
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
		
		JList<String> listPlayers = new JList<String>(playerList);
		listPlayers.setLayoutOrientation(JList.VERTICAL_WRAP);
		listPlayers.addListSelectionListener(e -> {
			if (listPlayers.getSelectedIndex() != -1) {
				recentSelectedPlayer = listPlayers.getSelectedIndex() + 1;
			}
		});

		listPlayers.setBorder(new LineBorder(new Color(0, 0, 0), 1, true));
		listPlayers.setVisibleRowCount(11);
		listPlayers.setFont(new Font("Monospaced", Font.PLAIN, 11));
		listPlayers.setToolTipText("");
		listPlayers.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
		listPlayers.setBounds(10, 173, 353, 200);
		frame.getContentPane().add(listPlayers);
		
		JLabel lblPlayerListDesc1 = new JLabel("Name             Stats                    Stamina     Position           Value");
		lblPlayerListDesc1.setBounds(10, 157, 359, 14);
		frame.getContentPane().add(lblPlayerListDesc1);

		JList<String> listReserves = new JList<String>(reservesList);
		listReserves.setVisibleRowCount(11);
		listReserves.setToolTipText("");
		listReserves.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
		listReserves.setLayoutOrientation(JList.VERTICAL_WRAP);
		listReserves.setFont(new Font("Monospaced", Font.PLAIN, 11));
		listReserves.setBorder(new LineBorder(new Color(0, 0, 0), 1, true));
		listReserves.setBounds(371, 173, 353, 200);
		frame.getContentPane().add(listReserves);
		listReserves.addListSelectionListener(e -> {
			if (listReserves.getSelectedIndex() != -1) {
				recentSelectedPlayer = listReserves.getSelectedIndex() + 12;
			}
		});
		
		JLabel lblPlayerListDesc1_1 = new JLabel("Name             Stats                    Stamina     Position           Value");
		lblPlayerListDesc1_1.setBounds(371, 157, 359, 14);
		frame.getContentPane().add(lblPlayerListDesc1_1);
		
		JLabel lblTeamTitle = new JLabel("Team");
		lblTeamTitle.setHorizontalAlignment(SwingConstants.CENTER);
		lblTeamTitle.setFont(new Font("Tahoma", Font.PLAIN, 14));
		lblTeamTitle.setBounds(161, 129, 46, 14);
		frame.getContentPane().add(lblTeamTitle);
		
		JLabel lblReserveTitle = new JLabel("Reserves (5 Max)");
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
		textAreaCoach.setBackground(UIManager.getColor("Button.background"));
		textAreaCoach.setEditable(false);
		textAreaCoach.setWrapStyleWord(true);
		textAreaCoach.setLineWrap(true);
		textAreaCoach.setFont(new Font("Tahoma", Font.PLAIN, 14));
		textAreaCoach.setText(String.format("Name: %s Stats: [%d %d %d] Rarity: %s Value: %s", coach.getName(), coachStats[0], coachStats[1], coachStats[2], coach.getRarity(), coach.getValue()));
		textAreaCoach.setBounds(495, 74, 229, 44);
		frame.getContentPane().add(textAreaCoach);
		frame.setBounds(100, 100, 750, 500);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

		JButton renamePlayerButton = new JButton("Rename Player");
		renamePlayerButton.setBounds(10, 383, 152, 39);
		frame.getContentPane().add(renamePlayerButton);

		JButton btnNewButton = new JButton("Back");
		btnNewButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				try {
					frame.dispose();
					manager.onClubMenuFinish("back");
				} catch (Exception error) {
					ShowMessage.showMessage(error.getMessage());
				}
			}
		});
		btnNewButton.setBounds(36, 388, 152, 39);
		frame.getContentPane().add(btnNewButton);
		
		JButton btnSellPlayer = new JButton("Sell Player");
		btnSellPlayer.setBounds(221, 383, 107, 39);
		frame.getContentPane().add(btnSellPlayer);
		btnSellPlayer.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				if (listReserves.getSelectedIndex() != -1) {
					try {
						String selectedPlayer = String.valueOf(listReserves.getSelectedIndex() + 12);
						manager.onClubMenuFinish("sell " + selectedPlayer);
						frame.dispose();
					} catch (Exception error) {
						ShowMessage.showMessage(error.getMessage());
					}
				}
			}
		});
		
		JButton btnNewButton_1_1 = new JButton("Substitute Player");
		btnNewButton_1_1.setBounds(360, 383, 127, 44);
		frame.getContentPane().add(btnNewButton_1_1);
		frame.setBounds(100, 100, 750, 500);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		btnNewButton_1_1.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				if (listPlayers.getSelectedIndex() != -1) {
					try {
						String selectedPlayer = String.valueOf(listPlayers.getSelectedIndex() + 1);
						String selectedReserve = String.valueOf(listReserves.getSelectedIndex() + 12);
						manager.onClubMenuFinish("swap " + selectedPlayer + " " + selectedReserve);
						frame.dispose();
					} catch (Exception error) {
						ShowMessage.showMessage(error.getMessage());

					}
				}
			}
		});

		// Assuming you have a button named "renamePlayerButton" in your main window
		renamePlayerButton.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				// Show an input dialog to get the new name from the player
				String newName = JOptionPane.showInputDialog(frame, "Enter the new name for the player:", "Rename Player", JOptionPane.PLAIN_MESSAGE);
				
				// Handle the new name (you can implement your logic here)
				if (recentSelectedPlayer != -1) {
					try {
						manager.onClubMenuFinish(recentSelectedPlayer  + " " + newName);
						frame.dispose();
					} catch (Exception error) {
						ShowMessage.showMessage(error.getMessage());
					}
				} else {
					JOptionPane.showMessageDialog(frame, "Please select a player to rename", "Player Not Selected", JOptionPane.ERROR_MESSAGE);
				}
			}
		});
	}
}


