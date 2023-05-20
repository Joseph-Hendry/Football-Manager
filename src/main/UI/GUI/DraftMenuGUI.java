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

    // Setup varlables
    private int initialMoney;
    private int money;
    private JList<Player> playerJList;
    private JList<Coach> coachJList;
    private JButton btnStartGame;
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
		frame.setBounds(100, 100, 640, 360);
		frame.getContentPane().setLayout(null);

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
		lblDraftYourTeam.setBounds(179, 10, 287, 43);
		frame.getContentPane().add(lblDraftYourTeam);
		
		JLabel lblPick = new JLabel("Pick a Coach:");
		lblPick.setFont(new Font("Tahoma", Font.PLAIN, 14));
		lblPick.setBounds(335, 63, 161, 29);
		frame.getContentPane().add(lblPick);
		
		JLabel lblPickStrikers = new JLabel("Pick Players");
		lblPickStrikers.setFont(new Font("Tahoma", Font.PLAIN, 14));
		lblPickStrikers.setBounds(10, 63, 161, 29);
		frame.getContentPane().add(lblPickStrikers);

        lblMoney = new JLabel();
        lblMoney.setText("Money: " + getManager().getMoney());
		lblMoney.setFont(new Font("Tahoma", Font.PLAIN, 14));
		lblMoney.setBounds(335, 207, 161, 29);
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
		playerJList.setBounds(10, 102, 340, 310);
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
		coachJList.setBounds(350, 102, 200, 80);
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
		btnStartGame.setBounds(335, 358, 287, 63);
        btnStartGame.setEnabled(false);
		frame.getContentPane().add(btnStartGame);
        btnStartGame.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                getManager().onDraftMenuFinish(selectedPlayers, selectedCoach, money);
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
            if (player.getPosition() != Team.getFormationPostion()[i]) {
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