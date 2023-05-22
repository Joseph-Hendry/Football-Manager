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

	// The selected object
	private Object selectedObject;

	// The JLists for the players and reserves
	private JList<Player> playersJList;
	private JList<Player> reservesJList;
	private JList<Item> itemsJList;

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
		frame.setBounds(0, 0, 750, 470);

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
		JLabel TitleLabel = new JLabel("Club Menu");
		TitleLabel.setHorizontalAlignment(SwingConstants.CENTER);
		TitleLabel.setFont(new Font("Tahoma", Font.PLAIN, 18));
		TitleLabel.setBounds(0, 10, 750, 30);
		frame.getContentPane().add(TitleLabel);
		
		JLabel teamNameTitleLabel = new JLabel("Team Name");
		teamNameTitleLabel.setHorizontalAlignment(SwingConstants.CENTER);
		teamNameTitleLabel.setFont(new Font("Tahoma", Font.BOLD, 14));
		teamNameTitleLabel.setBounds(10, 56, 239, 17);
		frame.getContentPane().add(teamNameTitleLabel);

		JLabel playerListLabel = new JLabel("Name       ATK MID DEF STAM   Poistion     Value");
		playerListLabel.setBounds(10, 155, 360, 14);
		playerListLabel.setFont(new Font("Monospaced", Font.PLAIN, 11));
		frame.getContentPane().add(playerListLabel);
		
		JLabel reserveListLabel = new JLabel("Name       ATK MID DEF STAM   Poistion     Value");
		reserveListLabel.setBounds(380, 155, 360, 15);
		reserveListLabel.setFont(new Font("Monospaced", Font.PLAIN, 11));
		frame.getContentPane().add(reserveListLabel);

		JLabel itemListLabel = new JLabel("Name       ATK MID DEF Value");
		itemListLabel.setBounds(380, 285, 360, 15);
		itemListLabel.setFont(new Font("Monospaced", Font.PLAIN, 11));
		frame.getContentPane().add(itemListLabel);

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
		
		JLabel teamTitleLabel = new JLabel("Team");
		teamTitleLabel.setHorizontalAlignment(SwingConstants.CENTER);
		teamTitleLabel.setFont(new Font("Tahoma", Font.PLAIN, 14));
		teamTitleLabel.setBounds(10, 130, 360, 15);
		frame.getContentPane().add(teamTitleLabel);
		
		JLabel reserveTitleLabel = new JLabel("Reserves (5 Max)");
		reserveTitleLabel.setHorizontalAlignment(SwingConstants.CENTER);
		reserveTitleLabel.setFont(new Font("Tahoma", Font.PLAIN, 14));
		reserveTitleLabel.setBounds(380, 130, 360, 15);
		frame.getContentPane().add(reserveTitleLabel);

		JLabel itemTitleLabel = new JLabel("Items (3 Max)");
		itemTitleLabel.setHorizontalAlignment(SwingConstants.CENTER);
		itemTitleLabel.setFont(new Font("Tahoma", Font.PLAIN, 14));
		itemTitleLabel.setBounds(380, 270, 360, 15);
		frame.getContentPane().add(itemTitleLabel);
		
		JLabel teamMoneyTitleLabel = new JLabel("Team Money");
		teamMoneyTitleLabel.setHorizontalAlignment(SwingConstants.CENTER);
		teamMoneyTitleLabel.setFont(new Font("Tahoma", Font.BOLD, 14));
		teamMoneyTitleLabel.setBounds(248, 56, 239, 17);
		frame.getContentPane().add(teamMoneyTitleLabel);
		
		JLabel coachLabel = new JLabel("Coach");
		coachLabel.setHorizontalAlignment(SwingConstants.CENTER);
		coachLabel.setFont(new Font("Tahoma", Font.BOLD, 14));
		coachLabel.setBounds(485, 56, 239, 17);
		frame.getContentPane().add(coachLabel);
		
		JLabel teamNameLabel = new JLabel(getManager().getPlayerTeam().getName());
		teamNameLabel.setHorizontalAlignment(SwingConstants.CENTER);
		teamNameLabel.setFont(new Font("Tahoma", Font.PLAIN, 14));
		teamNameLabel.setBounds(10, 76, 239, 17);
		frame.getContentPane().add(teamNameLabel);

		int[] teamStats = getManager().getStadium().getPossibleMatches().get(0).getPlayerTeamStats();
		JLabel teamStatsLabel = new JLabel(String.format("ATK:%d  MID:%d  DEF:%d", teamStats[0], teamStats[1], teamStats[2]));
		teamStatsLabel.setHorizontalAlignment(SwingConstants.CENTER);
		teamStatsLabel.setFont(new Font("Tahoma", Font.PLAIN, 14));
		teamStatsLabel.setBounds(10, 93, 239, 17);
		frame.getContentPane().add(teamStatsLabel);
		
		JLabel teamMoneyLabel = new JLabel("" + getManager().getMoney());
		teamMoneyLabel.setHorizontalAlignment(SwingConstants.CENTER);
		teamMoneyLabel.setFont(new Font("Tahoma", Font.PLAIN, 14));
		teamMoneyLabel.setBounds(248, 79, 239, 17);
		frame.getContentPane().add(teamMoneyLabel);

		// Create the coach text area
		JTextArea coachStatsLabel = new JTextArea();
		Coach coach = getManager().getPlayerTeam().getCoach();
		int[] coachStats = coach.getStats();
		coachStatsLabel.setBackground(UIManager.getColor("Button.background"));
		coachStatsLabel.setEditable(false);
		coachStatsLabel.setWrapStyleWord(true);
		coachStatsLabel.setLineWrap(true);
		coachStatsLabel.setFont(new Font("Tahoma", Font.PLAIN, 14));
		coachStatsLabel.setText(String.format("Name: %s Stats: [%d %d %d] Rarity: %s Value: %s", coach.getName(), coachStats[0], coachStats[1], coachStats[2], coach.getRarity(), coach.getValue()));
		coachStatsLabel.setBounds(495, 74, 229, 44);
		frame.getContentPane().add(coachStatsLabel);
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
		playersJList = new JList<>(playerList);
		playersJList.setLayoutOrientation(JList.VERTICAL_WRAP);
		playersJList.setBorder(new LineBorder(new Color(0, 0, 0), 1, true));
		playersJList.setVisibleRowCount(11);
		playersJList.setFont(new Font("Monospaced", Font.PLAIN, 11));
		playersJList.setToolTipText("");
		playersJList.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
		playersJList.setBounds(10, 180, 360, 200);
		frame.getContentPane().add(playersJList);
		playersJList.addListSelectionListener(e -> {
			if (playersJList.getSelectedValue() != null) {
				selectedObject = playersJList.getSelectedValue();
			}
		});

		// Create the JList of reserves
		reservesJList = new JList<>(reservesList);
		reservesJList.setVisibleRowCount(11);
		reservesJList.setToolTipText("");
		reservesJList.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
		reservesJList.setLayoutOrientation(JList.VERTICAL_WRAP);
		reservesJList.setFont(new Font("Monospaced", Font.PLAIN, 11));
		reservesJList.setBorder(new LineBorder(new Color(0, 0, 0), 1, true));
		reservesJList.setBounds(380, 180, 360, 80);
		frame.getContentPane().add(reservesJList);
		reservesJList.addListSelectionListener(e -> {
			if (reservesJList.getSelectedIndex() != -1) {
				selectedObject = reservesJList.getSelectedValue();
			}
		});
		
		itemsJList = new JList<>(itemsList);
		itemsJList.setVisibleRowCount(11);
		itemsJList.setToolTipText("");
		itemsJList.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
		itemsJList.setLayoutOrientation(JList.VERTICAL_WRAP);
		itemsJList.setFont(new Font("Monospaced", Font.PLAIN, 11));
		itemsJList.setBorder(new LineBorder(new Color(0, 0, 0), 1, true));
		itemsJList.setBounds(380, 300, 360, 80);
		frame.getContentPane().add(itemsJList);
		itemsJList.addListSelectionListener(e -> {
			if (itemsJList.getSelectedIndex() != -1) {
				selectedObject = itemsJList.getSelectedValue();
			}
		});
	}

	/**
	 * Adds the buttons to the frame.
	 * 
	 * @param frame The frame to add the buttons to.
	 */
	private void addButtons(JFrame frame) {

		// Create the rename player button
		JButton renamePlayerButton = new JButton("Rename");
		renamePlayerButton.setBounds(195, 390, 175, 40);
		frame.getContentPane().add(renamePlayerButton);
		renamePlayerButton.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
			if (selectedObject == null) {
				JOptionPane.showMessageDialog(frame, "Please select something to rename.", "Rename", JOptionPane.ERROR_MESSAGE);
			} else if (selectedObject.getClass() == Player.class) {
					String newName = JOptionPane.showInputDialog(frame, "Enter the new name for the player:", "Rename Player", JOptionPane.PLAIN_MESSAGE);
					if (newName != null) {
						getManager().setNickname((Player) selectedObject, newName);
					}
				} else if (selectedObject.getClass() == Item.class) {
					String newName = JOptionPane.showInputDialog(frame, "Enter the new name for the item:", "Rename Item", JOptionPane.PLAIN_MESSAGE);
					if (newName != null) {
						getManager().setItemName((Item) selectedObject, newName);
					}
				}
			}
		});
			
		// Create the substitute player button
		JButton subPlayersButton = new JButton("Substitute Player");
		subPlayersButton.setBounds(10, 390, 175, 40);
		frame.getContentPane().add(subPlayersButton);
		subPlayersButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				if (playersJList.getSelectedValue() != null && reservesJList.getSelectedValue() != null) {
					getManager().swapPlayers(playersJList.getSelectedValue(), reservesJList.getSelectedValue());
				}
			}
		});	

		// Create the sell player button
		JButton btnSellPlayer = new JButton("Sell");
		btnSellPlayer.setBounds(380, 390, 175, 40);
		frame.getContentPane().add(btnSellPlayer);
		btnSellPlayer.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				if (selectedObject == null) {
					JOptionPane.showMessageDialog(frame, "Please select something to sell.", "Sell", JOptionPane.ERROR_MESSAGE);
				} else if (selectedObject.getClass() == Player.class) {
					if (selectedObject != null) {
						getManager().sellPlayer((Player) selectedObject);
					}
				} else if (selectedObject.getClass() == Item.class) {
					if (selectedObject != null) {
						getManager().sellItem((Item) selectedObject);
					}
				}
			}
		});

		// Create the back button
		JButton backButton = new JButton("Back");
		backButton.setBounds(565, 390, 175, 40);
		frame.getContentPane().add(backButton);
		backButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				getManager().onClubMenuBack();
			}
		});
	}
}


