package main.UI.GUI;

import main.body.GameManager;
import main.body.Item;
import main.body.Player;
import main.body.Coach;

import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.SwingConstants;
import javax.swing.JList;
import javax.swing.JOptionPane;
import javax.swing.ListSelectionModel;
import javax.swing.DefaultListModel;
import javax.swing.JButton;

import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import java.awt.Font;

/**
 * The GUI for the store menu.
 * Used to display the store menu and allow the user to interact with it.
 */
public class StoreMenuGUI extends Window {

	// Setup JLists for the players, coaches and items
	private JList<Player> playerJList;
	private JList<Coach> coachJList;
	private JList<Item> itemJList;

	/**
	 * Create the application.
	 * 
	 * @param manager The {@link GameManager} instance.
	 */
	public StoreMenuGUI(GameManager manager) {
		super("Store Menu", manager);
	}

	/**
	 * Initialize the contents of the frame.
	 * 
	 * @param frame The frame to add the contents to.
	 */
	@Override
	protected void initialise(JFrame frame) {
		frame.setBounds(0, 0, 380, 462);

		addLabels(frame);
		addLists(frame);
		addButtons(frame);
	}

	/**
	 * Adds the labels to the frame.
	 * 
	 * @param frame The frame to add the labels to.
	 */
	private void addLabels(JFrame frame) {

		// Create the windows labels
		JLabel titleLabel = new JLabel("Store Menu");
		titleLabel.setHorizontalAlignment(SwingConstants.CENTER);
		titleLabel.setFont(new Font("Tahoma", Font.PLAIN, 18));
		titleLabel.setBounds(0, 10, 380, 40);
		frame.getContentPane().add(titleLabel);

		JLabel playersLabel = new JLabel("Players for sale:");
		playersLabel.setBounds(10, 75, 150, 15);
		frame.getContentPane().add(playersLabel);
		
		JLabel coachLabel = new JLabel("Coach for sale:");
		coachLabel.setBounds(10, 190, 150, 15);
		frame.getContentPane().add(coachLabel);
		
		JLabel itemsLabel = new JLabel("Items for sale:");
		itemsLabel.setBounds(10, 255, 150, 15);
		frame.getContentPane().add(itemsLabel);

		JLabel playerStatsLabel = new JLabel("Name       ATK MID DEF STAM   Position     Value");
		playerStatsLabel.setBounds(10, 100, 360, 14);
		playerStatsLabel.setFont(new Font("Monospaced", Font.PLAIN, 11));
		frame.getContentPane().add(playerStatsLabel);

		JLabel coachStatsLabel = new JLabel("Name       ATK MID DEF Value");
		coachStatsLabel.setBounds(10, 215, 360, 15);
		coachStatsLabel.setFont(new Font("Monospaced", Font.PLAIN, 11));
		frame.getContentPane().add(coachStatsLabel);

		JLabel itemsStatsLabel = new JLabel("Name       ATK MID DEF Value");
		itemsStatsLabel.setBounds(10, 280, 360, 15);
		itemsStatsLabel.setFont(new Font("Monospaced", Font.PLAIN, 11));
		frame.getContentPane().add(itemsStatsLabel);
		
		JLabel moneyLabel = new JLabel("Money: " + getManager().getMoney());
		moneyLabel.setBounds(10, 10, 150, 15);
		frame.getContentPane().add(moneyLabel);
	}

	/**
	 * Adds the lists to the frame.
	 * 
	 * @param frame The frame to add the lists to.
	 */
	private void addLists(JFrame frame) {

		// Set up the lists
		DefaultListModel<Player> Players = new DefaultListModel<>();
		DefaultListModel<Coach> Coaches = new DefaultListModel<>();
		DefaultListModel<Item> Items = new DefaultListModel<>();
		for (Player player : getManager().getStore().getStorePlayers()) {
			Players.addElement(player);
		}
		if (getManager().getStore().getCoachAvailable()) {
			Coaches.addElement(getManager().getStore().getStoreCoach());
		}
		for (Item item : getManager().getStore().getStoreItems()) {
			Items.addElement(item);
		}

		// Create the players JList
		playerJList = new JList<>(Players);
		playerJList.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
		playerJList.setBounds(10, 115, 360, 64);
		playerJList.setFont(new Font("Monospaced", Font.PLAIN, 11));
		frame.getContentPane().add(playerJList);

		// Create the coach JList
		coachJList = new JList<>(Coaches);
		coachJList.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
		coachJList.setBounds(10, 229, 360, 16);
		coachJList.setFont(new Font("Monospaced", Font.PLAIN, 11));
		frame.getContentPane().add(coachJList);
		
		// Create the items JList
		itemJList = new JList<>(Items);
		itemJList.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
		itemJList.setBounds(10, 295, 360, 80);
		itemJList.setFont(new Font("Monospaced", Font.PLAIN, 11));
		frame.getContentPane().add(itemJList);

		// Add the action listeners
		playerJList.addListSelectionListener(e -> {
			coachJList.clearSelection();
			itemJList.clearSelection();
		});

		coachJList.addListSelectionListener(e -> {
			playerJList.clearSelection();
			itemJList.clearSelection();
		});

		itemJList.addListSelectionListener(e -> {
			playerJList.clearSelection();
			coachJList.clearSelection();
		});
	}

	/**
	 * Adds the buttons to the frame.
	 * 
	 * @param frame The frame to add the buttons to.
	 */
	private void addButtons(JFrame frame) {

		// Create the buy player button
		JButton buyPlayerButton = new JButton("Buy");
		buyPlayerButton.setBounds(10, 385, 175, 40);
		frame.getContentPane().add(buyPlayerButton);
		buyPlayerButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				// Ask the user if they want the player on the bench or in the starting lineup
				if (playerJList.getSelectedIndex() != -1) {
					String question = "Would you like to put this player on the bench or in the starting lineup?";
					String[] options = {"Bench", "Starting Lineup"};
					int benchOrLineup = JOptionPane.showOptionDialog(frame, question, "Player Position", JOptionPane.YES_NO_OPTION, JOptionPane.QUESTION_MESSAGE, null, options, "Bench");
					getManager().buyPlayer(playerJList.getSelectedValue(), benchOrLineup);
				} else if (coachJList.getSelectedIndex() != -1) {
					getManager().buyCoach(coachJList.getSelectedValue());
				} else if (itemJList.getSelectedIndex() != -1) {
					getManager().buyItem(itemJList.getSelectedValue());
				}
			}
		});

		// Create the back button
		JButton btnBack = new JButton("Back");
		btnBack.setBounds(195, 385, 175, 40);
		frame.getContentPane().add(btnBack);
		btnBack.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				getManager().onStoreMenuBack();
			}
		});
	}
}


