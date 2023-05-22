package main.UI.GUI;

import main.body.Player;
import main.body.Team;
import main.body.Coach;

import javax.swing.JFrame;
import javax.swing.JLabel;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;

import javax.swing.SwingConstants;
import javax.swing.event.ListSelectionEvent;
import javax.swing.event.ListSelectionListener;

import main.body.GameManager;

import javax.swing.JList;
import javax.swing.ListSelectionModel;
import javax.swing.DefaultListModel;
import javax.swing.JButton;

/**
 * The GUI for the draft menu.
 * Used to display the draft menu and allow the user to interact with it.
 */
public class DraftMenuGUI extends Window {

    // Setup variables
    private int initialMoney;
    private int money;
    private JList<Player> playerJList;
    private JList<Coach> coachJList;
    private JButton btnStartGame;
    private JButton btnQuit;
    private ArrayList<Player> selectedPlayers = new ArrayList<>();
    private Coach selectedCoach;
    private boolean playersValid = false;
    private boolean coachValid = false;
    private JLabel lblMoney;
    private int previousCoachValue = 0;

	/**
	 * Create the application.
     * 
     * @param manager The {@link GameManager} instance.
	 */
	public DraftMenuGUI(GameManager manager) {
        super("Draft Menu", manager);
        this.initialMoney = getManager().getMoney();
        this.money = initialMoney;
	}

	/**
	 * Initialize the contents of the frame.
	 */
    @Override
	protected void initialise(JFrame frame) {
		frame.setBounds(0, 0, 570, 450);

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
        JLabel lblDraftYourTeam = new JLabel("Draft Your Team");
		lblDraftYourTeam.setHorizontalAlignment(SwingConstants.CENTER);
		lblDraftYourTeam.setFont(new Font("Tahoma", Font.PLAIN, 18));
		lblDraftYourTeam.setBounds(185, 10, 200, 50);
		frame.getContentPane().add(lblDraftYourTeam);
		
		JLabel lblPickCoach = new JLabel("Pick a Coach:");
		lblPickCoach.setBounds(360, 55, 200, 30);
		frame.getContentPane().add(lblPickCoach);

        JLabel lblCoachList = new JLabel("Name       ATK MID DEF Value");
		lblCoachList.setBounds(360, 85, 200, 15);
        lblCoachList.setFont(new Font("Monospaced", Font.PLAIN, 11));
		frame.getContentPane().add(lblCoachList);
		
		JLabel lblPickPlayers = new JLabel("Pick Players (4-3-3-1): (Hold ctrl)");
		lblPickPlayers.setBounds(10, 55, 300, 20);
		frame.getContentPane().add(lblPickPlayers);

        JLabel lblPlayerList = new JLabel("Name       ATK MID DEF STAM   Position     Value");
		lblPlayerList.setBounds(10, 85, 340, 15);
        lblPlayerList.setFont(new Font("Monospaced", Font.PLAIN, 11));
		frame.getContentPane().add(lblPlayerList);

        lblMoney = new JLabel();
        lblMoney.setText("Money: " + getManager().getMoney());
        lblMoney.setHorizontalAlignment(SwingConstants.CENTER);
        lblMoney.setVerticalAlignment(SwingConstants.CENTER);
		lblMoney.setFont(new Font("Tahoma", Font.BOLD, 14));
		lblMoney.setBounds(360, 185, 200, 100);
		frame.getContentPane().add(lblMoney);
    }

    /**
     * Adds the lists to the frame.
     * 
     * @param frame The frame to add the lists to.
     */
    private void addLists(JFrame frame) {

        // Set up the player and coach lists
        DefaultListModel<Player> playerList = new DefaultListModel<Player>();
        DefaultListModel<Coach> coachList = new DefaultListModel<Coach>();
        for (Player player : getManager().getDraftStore().getStorePlayers()) {
            playerList.addElement(player);
        }
        for (Coach coach : getManager().getDraftStore().getDraftCoaches()) {
            coachList.addElement(coach);
        }

        // Create the players JList
        playerJList = new JList<>(playerList);
        playerJList.setSelectionMode(ListSelectionModel.MULTIPLE_INTERVAL_SELECTION);
		playerJList.setBounds(10, 100, 340, 310);
        playerJList.setFont(new Font("Monospaced", Font.PLAIN, 11));
		frame.getContentPane().add(playerJList);
        playerJList.addListSelectionListener(new ListSelectionListener() {
            public void valueChanged(ListSelectionEvent e) {
                handlePlayerSelection();
            }
        });
		
        // Create the coaches JList
		coachJList = new JList<>(coachList);
        coachJList.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
		coachJList.setBounds(360, 100, 200, 90);
        coachJList.setFont(new Font("Monospaced", Font.PLAIN, 11));
		frame.getContentPane().add(coachJList);
        coachJList.addListSelectionListener(new ListSelectionListener() {
            public void valueChanged(ListSelectionEvent e) {
                handleCoachSelection();
            }
        });
    }

    /**
     * Adds the button to the frame.
     * 
     * @param frame The frame to add the button too.
     */
    private void addButtons(JFrame frame) {
        // Create the back button
        btnStartGame = new JButton("Start Game");
		btnStartGame.setFont(new Font("Tahoma", Font.PLAIN, 14));
		btnStartGame.setBounds(360, 280, 200, 60);
        btnStartGame.setEnabled(false);
		frame.getContentPane().add(btnStartGame);
        btnStartGame.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                getManager().onDraftMenuFinish(selectedPlayers, selectedCoach, money);
            }
        });

        // Create the quit button
        btnQuit = new JButton("Quit");
		btnQuit.setFont(new Font("Tahoma", Font.PLAIN, 14));
		btnQuit.setBounds(360, 350, 200, 60);
		frame.getContentPane().add(btnQuit);
        btnQuit.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                getManager().quit();;
            }
        });
    }

    /**
     * Handles the player selection.
     * Checks if the player has selected 11 players with the correct positions.
     * If it is, and the player has enough money it runs {@link #checkValid()}
     */
    private void handlePlayerSelection() {
        // Add the value of the previous players back to the money
        for (Player player : selectedPlayers) {
            money += player.getValue();
        }

        selectedPlayers.clear();

        // Add the value of the new players to the money and add them to the selected players list.
        for (Player player : playerJList.getSelectedValuesList()) {
            selectedPlayers.add(player);
            money -= player.getValue();
        }

        // Update the money label
        lblMoney.setText("Money: " + money);

        // Check if the players are in the correct positions
        for (int i = 0; i < selectedPlayers.size(); i++) {
            Player player = selectedPlayers.get(i);
            if (player.getPosition() != Team.getFormationPosition()[i]) {
                playersValid = false;
                btnStartGame.setEnabled(false);
                return;
            }
        }

        // Check if the player has selected 11 players
        if (selectedPlayers.size() == 11) {
            playersValid = true;
            checkValid();
        } else {
            playersValid = false;
            btnStartGame.setEnabled(false);
        }
    }

    /**
     * Handles the coach selection.
     * Checks if the player has selected a coach.
     * If they have it updates the money label and runs {@link #checkValid()}
     */
    private void handleCoachSelection() {
        if (coachJList.getSelectedValue() != null) {
            money += previousCoachValue;
            selectedCoach = coachJList.getSelectedValue();
            previousCoachValue = selectedCoach.getValue();
            money -= selectedCoach.getValue();
            lblMoney.setText("Money: " + money);
            coachValid = true;
            checkValid();
        } else {
            money += previousCoachValue;
            previousCoachValue = 0;
            lblMoney.setText("Money: " + money);
            coachValid = false;
            btnStartGame.setEnabled(false);
        }
    }

    /**
     * Checks if the players and coach are valid and if the player has enough money.
     * If they are it enables the start game button.
     */
    private void checkValid() {
        if (playersValid && coachValid && money >= 0) {
            btnStartGame.setEnabled(true);
        }
    }
    
}