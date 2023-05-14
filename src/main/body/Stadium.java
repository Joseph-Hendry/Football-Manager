package main.body;

import java.util.ArrayList;
import java.util.Random;

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


    ////////// Constructor //////////


    /**
     * This method is used to initialize the stadium.
     * It generates the NPC teams and sets the possible matches.
     * 
     * @param manager       The GameManager that is being used.
     * @param playerTeam    The team that the player is using.
     */
    public Stadium(GameManager manager, Team playerTeam) {
        this.manage = manager;
        this.playerTeam = playerTeam;
        generateNPCTeams(manager);
        setPossibleMatches();
    }

    /**
     * This method is used to generate the NPC teams.
     * It uses the difficulty calculated in the GameManager.
     * 
     * @param manager   The GameManager that is being used.
     */
    private void generateNPCTeams(GameManager manager) {
        Team.createNPCTeams(manager.getDifficulty());
    }

    /**
     * This method is used to set the possible matches.
     * Along with set the points and money that can be won from each match.
     */
    private void setPossibleMatches() {
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
            for (int i = 0; i < teamListSize; i++) {
                if (i != teamIndex) {
                    pointsToWin = 10 + (teamListSize - i) * 3;
                    moneyToWin = 1000 + (teamListSize - i) * 30;
                    possibleMatches.add(new Match(playerTeam, teamList.get(i), pointsToWin, moneyToWin));
                }
            }
        }

        this.possibleMatches = possibleMatches;
    }


    ////////// Getters //////////


    /**
     * This method is used to get the rankings of the teams.
     * 
     * @return  The rankings of the teams.
     */
    public ArrayList<Team> getRankings() {
        Team.sortTeamList();
        return Team.getTeamList();
    }

    /**
     * This method is used to get the possible matches.
     * 
     * @return  The possible matches.
     */
    public ArrayList<Match> getPossibleMatches() {
        setPossibleMatches();
        return possibleMatches;
    }


    ////////// Refresh Stadium //////////


    /**
     * This method is used to refresh the stadium.
     */
    public void refreshStadium() {
        possibleMatches.clear();
        setPossibleMatches();
    }


    ////////// Play Match //////////

    
    /**
     * This method is used to play a match.
     * 
     * @param match The match that is being played.
     */
    public void playMatch(Match match) throws Exception {
        canPlayMatch();
        match.playMatch(manage);
        updateMatchPoints(match);
    }

    /**
     * This method is used to check if the player can play a match.
     * 
     * @return  If the player can play a match.
     */
    private void canPlayMatch() throws Exception {
        // Check if any of the players are injured
        for (Player player : playerTeam.getTeam()) {
            if (player.isInjured()) {
                throw new Exception("You cannot play a match as one of your players is injured.");
            }
        }
    }

    /**
     * This method is used to update the points of the other teams.
     * Gives a random amount of points to the other teams.
     * 
     * @param playedMatch   The match that was played.
     */
    public void updateMatchPoints(Match playedMatch) {
        for (Match match : possibleMatches) {
            if (match != playedMatch) {
                Random random = new Random();
                int pointsToGive = random.nextInt(playedMatch.getPointsToWin()) + 10;
                Team opposingTeam = match.getOpposingTeam();
                opposingTeam.setPoints(opposingTeam.getPoints() + pointsToGive);
            }
        }
    }


    ////////// Take Bye //////////


    /**
     * This method is used to take a bye.
     */
    public void takeBye() {
        byeStamina();
        updateByePoints();
    }

    /**
     * This method is used to increase the stamina of the players when a bye is taken.
     */
    private void byeStamina() {
        // Increase all player stamina
        for (Player player : playerTeam.getTeam()) {
            player.incStamina(50);
        }
        for (Player player : playerTeam.getBench()) {
            player.incStamina(50);
        }
    }

    /**
     * This method is used to update the points of the other teams when a bye is taken.
     */
    private void updateByePoints() {
        for (Match match : possibleMatches) {
            Random random = new Random();
            int pointsToGive = random.nextInt(possibleMatches.get(0).getPointsToWin()) + 10;
            Team opposingTeam = match.getOpposingTeam();
            opposingTeam.setPoints(opposingTeam.getPoints() + pointsToGive);
        }
    }
}
