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

/**
 * The GUI for the club menu.
 * Used to display the club menu and allow the user to interact with it.
 */
public class ClubMenuGUI extends Window{

	// The selected player
	private Player selectedPlayer;

	// The JLists for the players and reserves
	private JList<Player> listPlayers;
	private JList<Player> listReserves;

	/**
	 * Create the application.
	 * 
	 * @param manager The {@link GameManager} instance.
	 */
	public ClubMenuGUI(GameManager manager) {
		super("Club Menu", manager);
	}

	/**
	 * Initialize the contents of the frame.
	 * 
	 * @param frame The frame to add the contents to.
	 */
	@Override
	protected void initialise(JFrame frame) {
		frame.getContentPane().setBackground(new Color(240, 240, 240));
		frame.setBounds(100, 100, 750, 500);
		frame.getContentPane().setLayout(null);

		addLabels(frame);
		addLists(frame);
		addButtons(frame);
	}

	/**
	 * Adds the labels and text areas to the frame.
	 * 
	 * @param frame The frame to add the labels to.
	 */
	private void addLabels(JFrame frame) {

		// Create the windows labels
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

		JLabel lblPlayerList = new JLabel("Name             Stats                    Stamina     Position           Value");
		lblPlayerList.setBounds(10, 157, 359, 14);
		frame.getContentPane().add(lblPlayerList);
		
		JLabel lblReserveList = new JLabel("Name             Stats                    Stamina     Position           Value");
		lblReserveList.setBounds(371, 157, 359, 14);
		frame.getContentPane().add(lblReserveList);

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

		// Create the coach text area
		JTextArea textAreaCoach = new JTextArea();
		Coach coach = getManager().getPlayerTeam().getCoach();
		int[] coachStats = coach.getStats();
		textAreaCoach.setBackground(UIManager.getColor("Button.background"));
		textAreaCoach.setEditable(false);
		textAreaCoach.setWrapStyleWord(true);
		textAreaCoach.setLineWrap(true);
		textAreaCoach.setFont(new Font("Tahoma", Font.PLAIN, 14));
		textAreaCoach.setText(String.format("Name: %s Stats: [%d %d %d] Rarity: %s Value: %s", coach.getName(), coachStats[0], coachStats[1], coachStats[2], coach.getRarity(), coach.getValue()));
		textAreaCoach.setBounds(495, 74, 229, 44);
		frame.getContentPane().add(textAreaCoach);
	}

	/**
	 * Adds the lists to the frame.
	 * 
	 * @param frame The frame to add the lists to.
	 */
	private void addLists(JFrame frame) {

		// Set up the lists for the JLists
		DefaultListModel<Player> reservesList = new DefaultListModel<Player>();
		DefaultListModel<Player> playerList = new DefaultListModel<Player>();
		DefaultListModel<Item> itemsList = new DefaultListModel<Item>();
		for (Player player : getManager().getPlayerTeam().getTeam()) {
			playerList.addElement(player);
		}
		for (Player player : getManager().getPlayerTeam().getBench()) {
			reservesList.addElement(player);
		}
		for (Item item : getManager().getPlayerTeam().getItems()) {
			itemsList.addElement(item);
		}
		
		// Create the JList of players
		listPlayers = new JList<>(playerList);
		listPlayers.setLayoutOrientation(JList.VERTICAL_WRAP);
		listPlayers.setBorder(new LineBorder(new Color(0, 0, 0), 1, true));
		listPlayers.setVisibleRowCount(11);
		listPlayers.setFont(new Font("Monospaced", Font.PLAIN, 11));
		listPlayers.setToolTipText("");
		listPlayers.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
		listPlayers.setBounds(10, 173, 353, 200);
		frame.getContentPane().add(listPlayers);
		listPlayers.addListSelectionListener(e -> {
			if (listPlayers.getSelectedValue() != null) {
				selectedPlayer = listPlayers.getSelectedValue();
			}
		});

		// Create the JList of reserves
		listReserves = new JList<>(reservesList);
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
				selectedPlayer = listReserves.getSelectedValue();
			}
		});
		
		// TODO: Create the JList of items
	}

	/**
	 * Adds the buttons to the frame.
	 * 
	 * @param frame The frame to add the buttons to.
	 */
	private void addButtons(JFrame frame) {

		// Create the rename player button
		JButton renamePlayerButton = new JButton("Rename Player");
		renamePlayerButton.setBounds(10, 383, 152, 39);
		frame.getContentPane().add(renamePlayerButton);
		renamePlayerButton.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				String newName = JOptionPane.showInputDialog(frame, "Enter the new name for the player:", "Rename Player", JOptionPane.PLAIN_MESSAGE);
				if (newName != null) {
					getManager().setNickname(selectedPlayer, newName);
				}
			}
		});
			
		// Create the substitue player button
		JButton subPlayersButton = new JButton("Substitute Player");
		subPlayersButton.setBounds(360, 383, 127, 44);
		frame.getContentPane().add(subPlayersButton);
		subPlayersButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				if (listPlayers.getSelectedValue() != null && listReserves.getSelectedValue() != null) {
					getManager().swapPlayers(listPlayers.getSelectedValue(), listReserves.getSelectedValue());
				}
			}
		});	

		// Create the sell player button
		JButton btnSellPlayer = new JButton("Sell Player");
		btnSellPlayer.setBounds(221, 383, 107, 39);
		frame.getContentPane().add(btnSellPlayer);
		btnSellPlayer.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				if (listReserves.getSelectedValue() != null) {
					getManager().sellPlayer(listReserves.getSelectedValue());
				}
			}
		});

		// Create the back button
		JButton backButton = new JButton("Back");
		backButton.setBounds(36, 388, 152, 39);
		frame.getContentPane().add(backButton);
		backButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				getManager().onClubMenuBack();
			}
		});
	}
}


