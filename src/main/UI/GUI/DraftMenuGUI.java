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

public class DraftMenuGUI extends Window {

    private int initialMoney;
    private int money;
    //private DefaultListModel<Player> playerList = new DefaultListModel<Player>();
    //private DefaultListModel<Coach> coachList = new DefaultListModel<Coach>();
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
	 */
	public DraftMenuGUI(GameManager manager) {
        super("Draft Menu", manager);
        this.initialMoney = manager.getMoney();
        this.money = initialMoney;
	}

	/**
	 * Initialize the contents of the frame.
	 */
    @Override
	protected void initialise(JFrame frame) {
		frame.setBounds(100, 100, 640, 360);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.getContentPane().setLayout(null);
		
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
        DefaultListModel<Player> playerList = new DefaultListModel<Player>();
        // Create the player list
        for (Player player : getManager().getDraftStore().getStorePlayers()) {
            playerList.addElement(player);
        }
        DefaultListModel<Coach> coachList = new DefaultListModel<Coach>();
        
                // Creat the coach list
        for (Coach coach : getManager().getDraftStore().getDraftCoaches()) {
            coachList.addElement(coach);
        }
		
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
		
		lblMoney = new JLabel();
        lblMoney.setText("Money: " + money);
		lblMoney.setFont(new Font("Tahoma", Font.PLAIN, 14));
		lblMoney.setBounds(335, 207, 161, 29);
		frame.getContentPane().add(lblMoney);
		
		btnStartGame = new JButton("Start Game");
		btnStartGame.setFont(new Font("Tahoma", Font.PLAIN, 14));
		btnStartGame.setBounds(335, 358, 287, 63);
        btnStartGame.setEnabled(false);
		frame.getContentPane().add(btnStartGame);
        btnStartGame.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                frame.dispose();
                ShowMessage.showMessage("You have drafted your team!");
                getManager().onDraftMenuFinish(selectedPlayers, selectedCoach, money);
            }
        });
	}

    private void handlePlayerSelection() {
        for (Player player : selectedPlayers) {
            money += player.getValue();
        }
        selectedPlayers.clear();
        for (Player player : playerJList.getSelectedValuesList()) {
            selectedPlayers.add(player);
            money -= player.getValue();
        }
        lblMoney.setText("Money: " + money);

        // Check that the right players are in the right positions
        for (int i = 0; i < selectedPlayers.size(); i++) {
            Player player = selectedPlayers.get(i);
            if (player.getPosition() != Team.getFormationPostion()[i]) {
                playersValid = false;
                btnStartGame.setEnabled(false);
                return;
            }
        }
        if (selectedPlayers.size() == 11) {
            playersValid = true;
            checkValid();
        } else {
            playersValid = false;
            btnStartGame.setEnabled(false);
        }
    }

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

    private void checkValid() {
        if (playersValid && coachValid && money >= 0) {
            btnStartGame.setEnabled(true);
        }
    }
    
}