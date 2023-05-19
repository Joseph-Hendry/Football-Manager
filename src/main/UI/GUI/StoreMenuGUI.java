package main.UI.GUI;

import main.body.GameManager;
import main.body.Item;
import main.body.Player;

import javax.swing.*;
import java.awt.*;
import java.util.ArrayList;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;

public class StoreMenuGUI{

	private JFrame frame;
    private final GameManager manager;
	private DefaultListModel<String> Players = new DefaultListModel<String>();
	private DefaultListModel<String> Coaches = new DefaultListModel<String>();
	private DefaultListModel<String> Items = new DefaultListModel<String>();


	/**
	 * Create the application.
	 */
	public StoreMenuGUI(GameManager manager) {
		this.manager = manager;



		for (Player player : manager.getStore().getStorePlayers()) {
			Players.addElement(player.toString());
		}
		if (manager.getStore().getcoachAvailable()) {
			Coaches.addElement(manager.getStore().getStoreCoach().toString());
		}
		for (Item item : manager.getStore().getStoreItems()) {
			Items.addElement(item.toString());
		}

		initialize();
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
		frame.setResizable(false);
		frame.setBounds(100, 100, 640, 360);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.getContentPane().setLayout(null);
		
		JLabel lblWelcomeMessage = new JLabel("Store Menu");
		lblWelcomeMessage.setHorizontalAlignment(SwingConstants.CENTER);
		lblWelcomeMessage.setFont(new Font("Tahoma", Font.PLAIN, 18));
		lblWelcomeMessage.setBounds(183, 12, 287, 43);
		frame.getContentPane().add(lblWelcomeMessage);
		
		JButton btnStadium = new JButton("Buy");
		btnStadium.setFont(new Font("Tahoma", Font.PLAIN, 14));
		btnStadium.setBounds(12, 243, 294, 63);
		frame.getContentPane().add(btnStadium);
		
		ArrayList<String> thisList = new ArrayList<String>();
		thisList.add("This ");
		thisList.add("is ");
		thisList.add("working!");
		DefaultListModel<String> stringListModel = new DefaultListModel<>();
		stringListModel.addAll(thisList);
		
		JList<String> listPlayers = new JList<>(Players);
		listPlayers.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
		listPlayers.setBounds(37, 83, 267, 135);
		listPlayers.setFont(new Font("Monospaced", Font.PLAIN, 11));
		frame.getContentPane().add(listPlayers);

		JLabel lblPlayersForSale = new JLabel("Players for sale:");
		lblPlayersForSale.setBounds(37, 54, 147, 15);
		frame.getContentPane().add(lblPlayersForSale);
		
		JLabel lblCoachForSale = new JLabel("Coach for sale:");
		lblCoachForSale.setBounds(331, 54, 147, 15);
		frame.getContentPane().add(lblCoachForSale);
		
		JLabel lblItemsForSale = new JLabel("Items for sale:");
		lblItemsForSale.setBounds(331, 110, 123, 15);
		frame.getContentPane().add(lblItemsForSale);
		
		JList<String> listCoach = new JList<>(Coaches);
		listCoach.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
		listCoach.setBounds(331, 83, 233, 15);
		listCoach.setFont(new Font("Monospaced", Font.PLAIN, 11));
		frame.getContentPane().add(listCoach);
		
		JList<String> listItems = new JList<String>(Items);
		listItems.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
		listItems.setBounds(332, 137, 233, 81);
		listItems.setFont(new Font("Monospaced", Font.PLAIN, 11));
		frame.getContentPane().add(listItems);
		
		JButton btnBack = new JButton("Back");
		btnBack.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
			}
		});
		btnBack.setFont(new Font("Dialog", Font.PLAIN, 14));
		btnBack.setBounds(331, 243, 294, 63);
		frame.getContentPane().add(btnBack);
		
		JLabel lblNewLabel = new JLabel("Money: " + manager.getMoney());
		lblNewLabel.setBounds(463, 12, 70, 15);
		frame.getContentPane().add(lblNewLabel);

		listPlayers.addListSelectionListener(e -> {
			listItems.clearSelection();
			listCoach.clearSelection();
		});

		listItems.addListSelectionListener(e -> {
			listPlayers.clearSelection();
			listCoach.clearSelection();
		});

		listCoach.addListSelectionListener(e -> {
			listPlayers.clearSelection();
			listItems.clearSelection();
		});

		// Add actions
		btnStadium.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				if (listPlayers.getSelectedIndex() != -1) {
					// Ask the user if they want the player on the bench or in the starting lineup
					String question = "Would you like to put this player on the bench or in the starting lineup?";
					String[] options = {"Bench", "Starting Lineup"};
					int benchOrLineup = JOptionPane.showOptionDialog(frame, question, "Player Position", JOptionPane.YES_NO_OPTION, JOptionPane.QUESTION_MESSAGE, null, options, "Bench");
					System.out.println(benchOrLineup);
					if (benchOrLineup == 0) {
						try {
							manager.onStoreMenuFinish("buy " + (listPlayers.getSelectedIndex() + 1) + " bench");
							lblNewLabel.setText("Money: " + manager.getMoney());
							Players.remove(listPlayers.getSelectedIndex());
							frame.dispose();
						} catch (Exception exception) {
							ShowMessage.showMessage(exception.getMessage());
						}
					} else {
						try {
							manager.onStoreMenuFinish("buy " + (listPlayers.getSelectedIndex() + 1) + " team");
							lblNewLabel.setText("Money: " + manager.getMoney());
							Players.remove(listPlayers.getSelectedIndex());
							frame.dispose();
						} catch (Exception exception) {
							ShowMessage.showMessage(exception.getMessage());
						}
					}
				} else if (listItems.getSelectedIndex() != -1) {
					try {
						manager.onStoreMenuFinish("buy item " + (listItems.getSelectedIndex() + 1));
						lblNewLabel.setText("Money: " + manager.getMoney());
						Items.remove(listItems.getSelectedIndex());
						frame.dispose();
					} catch (Exception exception) {
						ShowMessage.showMessage(exception.getMessage());
					}
				} else if (listCoach.getSelectedIndex() != -1) {
					try {
						manager.onStoreMenuFinish("buy coach");
						lblNewLabel.setText("Money: " + manager.getMoney());
						Coaches.remove(listCoach.getSelectedIndex());
						frame.dispose();
					} catch (Exception exception) {
						ShowMessage.showMessage(exception.getMessage());
					}
				} else {
					ShowMessage.showMessage("Please select a player or item to buy.");
				}
			}
		});

		// Back button
		btnBack.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				try {
					manager.onStoreMenuFinish("back");
					frame.dispose();
				} catch (Exception exception) {
					ShowMessage.showMessage(exception.getMessage());
				}
			}
		});
	}
}


