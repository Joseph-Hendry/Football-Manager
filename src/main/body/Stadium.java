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

    // The GameManager that is being used
    private GameManager manager;

    // The team that the player is using
    private Team playerTeam;

    // The possible matches
    private ArrayList<Match> possibleMatches = new ArrayList<Match>();


    ////////// Constructor //////////


    /**
     * This method is used to initialize the stadium.
     * It generates the NPC teams and sets the possible matches.
     * 
     * @param manager The GameManager that is being used.
     * @param playerTeam The team that the player is using.
     */
    public Stadium(GameManager manager, Team playerTeam) {

        // Set the GameManager and playerTeam
        this.manager = manager;
        this.playerTeam = playerTeam;

        // Generate the NPC teams and set the possible matches
        generateNPCTeams(manager);
        setPossibleMatches();
    }

    /**
     * This method is used to generate the NPC teams.
     * It uses the difficulty calculated in the GameManager.
     * 
     * @param manager The GameManager that is being used.
     */
    private void generateNPCTeams(GameManager manager) {
        Team.createNPCTeams(manager.getRarityInt());
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
        if (manager.getWeek() == 1) {
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
    public void playMatch(Match match) throws IllegalArgumentException {
        canPlayMatch(match);
        match.playMatch(manager);
        updateMatchPoints(match);
    }

    /**
     * This method is used to check if the player can play a match.
     * 
     * @return If the player can play a match.
     * @throws IllegalArgumentException If the player cannot play a match.
     */
    private void canPlayMatch(Match match) throws IllegalArgumentException {
        // Check if any of the players are injured
        if (match != null) {
            for (Player player : playerTeam.getTeam()) {
                if (player.isInjured() || player == null) {
                    throw new IllegalArgumentException("You cannot play a match as one of your players is injured.");
                }
            }
        } else {
            throw new IllegalArgumentException("Please select a match.");
        }
    }

    /**
     * This method is used to update the points of the other teams.
     * Gives a random amount of points to the other teams.
     * 
     * @param playedMatch The match that was played.
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
     * 
     * @param playerToTrain The player that is being trained.
     */
    public void takeBye(Player playerToTrain) {

        // Train the player and increase the stamina of the players
        trainPlayer(playerToTrain);

        // Increase all player stamina
        byeStamina();

        // Update the points of the other teams
        updateByePoints();
    }

    /**
     * This method is used to train a player when a bye is taken.
     * 
     * @param playerToTrain The player that is being trained.
     */
    private void trainPlayer(Player playerToTrain) {
        int[] baseStats = playerToTrain.getStats();
        int statsIncrease = manager.getRarityInt()/2;
        for (int i = 0; i < 3; i++) {
            baseStats[i] += statsIncrease;
            if (baseStats[i] > 100) {
                baseStats[i] = 100;
            }
        }
        playerToTrain.setStats(baseStats);
    }

    /**
     * This method is used to increase the stamina of the players when a bye is taken.
     */
    private void byeStamina() {
        // Increase all player stamina
        for (Player player : playerTeam.getTeam()) {
            if (player != null) {
                player.incStamina(100);
            }
        }
        for (Player player : playerTeam.getBench()) {
            if (player != null) {
                player.incStamina(100);
            }
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


    ////////// Random Event //////////


    /**
     * This method is used to generate a random event.
     * 
     * @param manager The GameManager that is being used.
     */
    public void randomEvent(GameManager manager) {
        Random random = new Random();
        int randomInt = random.nextInt(100);
        if (randomInt < 10) {
            randomPlayerStats(manager);
        } else if (randomInt < 20) {
            randomPlayerQuit(manager);
        } else if (randomInt < 30) {
            randomNewPlayer(manager);
        }
    }

    /**
     * This method is used to increase the stats of a random player.
     * 
     * @param manager The GameManager that is being used.
     */
    private void randomPlayerStats(GameManager manager) {

        // Find a random player on the team
        Random random = new Random();
        int randomPlayerNum = random.nextInt(11);
        Player randomPlayer = playerTeam.getTeam().get(randomPlayerNum);

        // Increase the stats of the player
        int[] baseStats = randomPlayer.getStats();
        int statsIncrease = manager.getRarityInt()/2;
        for (int i = 0; i < 3; i++) {
            baseStats[i] += statsIncrease;
            if (baseStats[i] > 100) {
                baseStats[i] = 100;
            }
        }
        randomPlayer.setStats(baseStats);
        manager.UI.showMessage("A player on your team has increased stats.");
    }

    /**
     * This method is used to make a random player quit.
     * 
     * @param manager The GameManager that is being used.
     */
    private void randomPlayerQuit(GameManager manager) {

        // Find a random player number and remove them from the team
        Random random = new Random();
        int randomPlayerNum = random.nextInt(11);
        playerTeam.removePlayer(randomPlayerNum);
        manager.UI.showMessage("A player has quit your team.");
    }

    /**
     * This method is used to add a random player to the team.
     * 
     * @param manager The GameManager that is being used.
     */
    private void randomNewPlayer(GameManager manager) {

        // Create a random player
        Player randomPlayer = Player.createRandomPlayer(Team.getStrRarity((manager.getRarityInt() + 20)), AvailablePositions.getRandomPosition());

        // If can find a empty spot on the bench, add the player to the bench
        for (int i = 0; i < playerTeam.getBench().size(); i++) {
            Player player = playerTeam.getBench().get(i);
            if (player == null) {
                playerTeam.addPlayerToBench(randomPlayer, i);
                manager.UI.showMessage("A new player has joined your team.");
                return;
            }
        }

        // Find a random player number
        Random random = new Random();
        int randomPlayerNum = random.nextInt(playerTeam.getBench().size() - 1);

        // If can find a empty spot on the team, swap the player with the random player on the bench
        playerTeam.addPlayerToBench(randomPlayer, randomPlayerNum);
        manager.UI.showMessage("A new player has joined your team.");
    }
}
