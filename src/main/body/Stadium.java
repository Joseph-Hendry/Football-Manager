package main.body;

import java.util.ArrayList;

/**
 * This class implements a Stadium.
 * From here the player can view and play possible matches
 * It will also show the player the current standings of the league.
 * It will also check to see if the player can play a match.
 * From the stadium the player can also take a bye.
 */
public class Stadium {
    private GameManager manage;
    private Team playerTeam;
    private ArrayList<Match> possibleMatches = new ArrayList<Match>();

    /**
     * This method is used to initialize the stadium.
     */
    public Stadium(GameManager manager, Team playerTeam) {
        this.manage = manager;
        this.playerTeam = playerTeam;
        generateNPCTeams();
        setPossibleMatches();
    }

    /**
     * This method is used to take a bye.
     */
    public void takeBye() {
        // Increase all player stamina
        for (Player player : playerTeam.getTeam()) {
            player.incStamina(50);
        }
        for (Player player : playerTeam.getBench()) {
            player.incStamina(50);
        }
    }

    /**
     * This method is used to get the rankings of the teams.
     * @return The rankings of the teams.
     */
    public ArrayList<Team> getRankings() {
        Team.sortTeamList();
        return Team.getTeamList();
    }

    public void generateNPCTeams() {
        int teamStrength = 50;

        Team.createNPCTeams(teamStrength);
    }

    /**
     * This method is used to set the possible matches.
     * Along with set the points and money that can be won from each match.
     */
    public void setPossibleMatches() {
        ArrayList<Match> possibleMatches = new ArrayList<Match>();
        ArrayList<Team> teamList = getRankings();
        int teamIndex = teamList.indexOf(playerTeam);
        int teamListSize = teamList.size();
        int pointsToWin;
        int moneyToWin;

        // Generate the possible matches
        if (manage.getWeek() == 1) {
            for (int i = 0; i < teamListSize; i++) {
                if (i != teamIndex) {
                    possibleMatches.add(new Match(playerTeam, teamList.get(i), 3, 1000));
                }
            }
        } else {
            for (int i = 0; i < teamIndex; i++) {
                if (i != teamIndex) {
                    pointsToWin = (teamListSize - teamIndex) * 3;
                    moneyToWin = (teamListSize - teamIndex) * 1000;
                    possibleMatches.add(new Match(playerTeam, teamList.get(i), pointsToWin, moneyToWin));
                }
            }
        }

        this.possibleMatches = possibleMatches;
    }

    /**
     * This method is used to get the possible matches.
     * @return The possible matches.
     */
    public ArrayList<Match> getPossibleMatches() {
        return possibleMatches;
    }
    
    /**
     * This method is used to play a match.
     */
    public void playMatch(Match match) throws Exception {
        canPlayMatch();
        match.playMatch(manage);
    }

    /**
     * This method is used to check if the player can play a match.
     * @return If the player can play a match.
     */
    public void canPlayMatch() throws Exception {
        // Check if any of the players are injured
        for (Player player : playerTeam.getTeam()) {
            if (player.isInjured()) {
                throw new Exception("You cannot play a match as one of your players is injured.");
            }
        }
    }
}
