package main.UI.GUI;

import main.body.GameManager;
import javax.swing.*;
import java.awt.*;
import java.util.ArrayList;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;

public class StoreMenuGUI{

	private JFrame frame;
	private JTextField txtTeamName;
	private JSpinner spnrSeasonLength;
	private JComboBox<String> comboDifficulty;
    private JButton btnClub;
    private JButton btnS;
    
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
		
		JList<String> astronautList = new JList<>(stringListModel);
		astronautList.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
		astronautList.setBounds(37, 83, 267, 135);
		frame.getContentPane().add(astronautList);
		
		JLabel lblPlayersForSale = new JLabel("Players for sale:");
		lblPlayersForSale.setBounds(37, 54, 147, 15);
		frame.getContentPane().add(lblPlayersForSale);
		
		JLabel lblCoachForSale = new JLabel("Coach for sale:");
		lblCoachForSale.setBounds(331, 54, 147, 15);
		frame.getContentPane().add(lblCoachForSale);
		
		JLabel lblItemsForSale = new JLabel("Items for sale:");
		lblItemsForSale.setBounds(331, 110, 123, 15);
		frame.getContentPane().add(lblItemsForSale);
		
		JList list = new JList();
		list.setBounds(331, 83, 233, 15);
		frame.getContentPane().add(list);
		
		JList<String> astronautList_1 = new JList<String>((ListModel) null);
		astronautList_1.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
		astronautList_1.setBounds(332, 137, 233, 81);
		frame.getContentPane().add(astronautList_1);
		
		JButton btnBack = new JButton("Back");
		btnBack.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
			}
		});
		btnBack.setFont(new Font("Dialog", Font.PLAIN, 14));
		btnBack.setBounds(331, 243, 294, 63);
		frame.getContentPane().add(btnBack);
		
		JLabel lblNewLabel = new JLabel("Money: ");
		lblNewLabel.setBounds(463, 12, 70, 15);
		frame.getContentPane().add(lblNewLabel);
	}
}


