package main.body;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

/**
 * This class is used to play a match.
 */
public class Match {
	
	// Setup the variables
    private int[] score = {0, 0};
    private Team[] teams = new Team[2];
    private int[] playerTeamStats = new int[4];
    private int[] NPCTeamStats = new int[4];
    private ArrayList<String> commentaryList = new ArrayList<String>();
    private int pointsToWin;
    private int moneyToWin;
    private int time;


    ////////// Match Setup //////////


    /**
     * This is the constructor for the Match class.
     * 
     * @param playerTeam The player's team.
     * @param NPCTeam The NPC's team.
     * @param pointsToWin The points to win.
     * @param moneyToWin The money to win.
     */
    public Match(Team playerTeam, Team NPCTeam, int pointsToWin, int moneyToWin) {
        this.teams[0] = playerTeam;
        this.teams[1] = NPCTeam;
        this.playerTeamStats = findTeamStats(playerTeam);
        this.NPCTeamStats = findTeamStats(NPCTeam);
        this.pointsToWin = pointsToWin;
        this.moneyToWin = moneyToWin;
    }

    /**
     * This method is used to find the overall stats of a team.
     * 
     * @param team The team to find the stats of.
     * @return The overall stats of the team.
     */
    private int[] findTeamStats(Team team) {
        // Initialize the stats
        int[] teamStats = {0, 0, 0, 0};

        // Stats depend on the formation and player position
        int[] formation = Team.getFormation();

        // Get the players on the team
        ArrayList<Player> teamList = team.getTeam();
        
        for (Player player : teamList) {
            if (player != null) {
                    int[] playerStats = player.getStats();
                    AvailablePositions position = player.getPosition();
                    switch (position) {
                        case DEFENCE:
                            teamStats[2] += (playerStats[0] * 0.2 + playerStats[1] * 0.2 + playerStats[2] * 0.6);
                            break;
                        case MIDFIELD:
                            teamStats[1] += (playerStats[0] * 0.2 + playerStats[1] * 0.6 + playerStats[2] * 0.2);
                            break;
                        case STRIKER:
                            teamStats[0] += (playerStats[0] * 0.6 + playerStats[1] * 0.2 + playerStats[2] * 0.2);
                            break;
                        case GOALKEEPER:
                            teamStats[3] += (playerStats[0] * 0.3 + playerStats[1] * 0.3 + playerStats[2] * 0.4);
                            break;
                    }
                }
            }
        // Add the coach
        for (int i = 0; i < 3; i++) {
            teamStats[i] += team.getCoach().getStats()[i];
        }
        
        // Add the items
        ArrayList<Item> teamItems = team.getItems();
        for (Item item : teamItems) {
            if (item != null) {
                teamStats[0] += item.getStats()[0];
                teamStats[1] += item.getStats()[1];
                teamStats[2] += item.getStats()[2];
            }
        }

        // Divide the stats by the number of players in each position + 1 for the coach and items (excluding goalkeeper)
        teamStats[0] /= (formation[2] + teamItems.size() + 1);
        teamStats[1] /= (formation[1] + teamItems.size() + 1);
        teamStats[2] /= (formation[0] + teamItems.size() + 1);
        teamStats[3] /= (formation[3] + teamItems.size() + 1);

        return teamStats;
    }


    ////////// Getters and Setters //////////

    
    /**
     * This method is used to get the score of the match.
     * 
     * @return The score of the match.
     */
    public int[] getScore() {
        return score;
    }

    /**
     * This method is used to get the points to win.
     * 
     * @return The points to win.
     */
    public int getPoints() {
        return pointsToWin;
    }

    /**
     * This method is used to get the money to win
     * .
     * @return The money to win.
     */
    public int getMoney() {
        return moneyToWin;
    }

    /**
     * This method returns the opposing team.
     * 
     * @return The opposing team.
     */
    public Team getOpposingTeam() {
        return teams[1];
    }

    /**
     * Get NPC team stats
     * 
     * @return The NPC team stats.
     */
    public int[] getNPCTeamStats() {
        return NPCTeamStats;
    }

    /**
     * Get home team stats
     * 
     * @return The home team stats.
     */
    public int[] getPlayerTeamStats() {
        return playerTeamStats;
    }

    /**
     * This is the getter for the points to win.
     * 
     * @return The points to win.
     */
    public int getPointsToWin() {
        return pointsToWin;
    }

    /**
     * This is the getter for the money to win.
     * 
     * @return The money to win.
     */
    public int getMoneyToWin() {
        return moneyToWin;
    }

    /**
     * This is the getter for the commentary list.
     * 
     * @return The commentary list.
     */
    public ArrayList<String> getCommentaryList() {
        return commentaryList;
    }

    /**
     * To string method for the match.
     * 
     * @return The string representation of the match.
     */
    public String toString() {
        int[] stats = getNPCTeamStats();
        return String.format("%-15s %-4s %-4s %-6s %-7s %-1s", teams[1].getName(), stats[0], stats[1], stats[2], pointsToWin, moneyToWin);
    }


    ////////// Play Match //////////


    /**
     * This method is used to play the match.
     * 
     * @param manager The game manager.
     */
    public void playMatch(GameManager manager) {

        // Set the commentary
        setGameCommentary(manager);

        // Update the game
        onGameEnd(manager);
    }

    /**
     * This method is used to set the commentary of the match.
     * 
     * @param manager The game manager.
     */
    private void setGameCommentary(GameManager manager) {
        // Initialize the variables
        time = 90;
        Random random = new Random();
        int teamWithBall;
        int winningProbability = 0;
        int random_chance = 0;

        // List that stores both teams stats
        List<int[]> teamStats = new ArrayList<int[]>();
        teamStats.add(playerTeamStats);
        teamStats.add(NPCTeamStats);

        // Possession team ball position (0 = Shooting, 1 = Attacking, 2 = Midfield, 4 = Defense)
        int ballPosition = 1;

        // Random team starts with the ball
        teamWithBall = random.nextInt(2);
        commentaryList.add(teams[teamWithBall].getName() + " starts with the ball.");

        // Play the match
        while (time > 0) {

            if (ballPosition == 0) {
                // If the team is taking a shot on goal
                winningProbability = teamStats.get(teamWithBall)[0] - teamStats.get((teamWithBall + 1) % 2)[3];
                winningProbability = (winningProbability + 100) / 2;

                random_chance = random.nextInt(100);

                if (random_chance <= winningProbability) {
                    // If the team scores
                    commentaryList.add("[" + (90 - time) + ":00]" + teams[teamWithBall].getName() + " scores!!!");
                    score[teamWithBall] += 1;
                    teamWithBall = (teamWithBall + 1) % 2;
                    ballPosition = 2;
                } else {
                    // If the team misses
                    commentaryList.add("[" + (90 - time) + ":00]" + teams[teamWithBall].getName() + " misses.");
                    teamWithBall = (teamWithBall + 1) % 2;
                    ballPosition = 4;
                }
            }
            else if (ballPosition == 1) {
                // If the ball is with the striker
                winningProbability = teamStats.get(teamWithBall)[0] - teamStats.get((teamWithBall + 1) % 2)[2];
                winningProbability = (winningProbability + 100) / 2;

                random_chance = random.nextInt(100);

                if (random_chance <= winningProbability) {
                    // If the striker gets a shot on goal
                    commentaryList.add("[" + (90 - time) + ":00]" + teams[teamWithBall].getName() + " striker gets a shot on goal.");
                    ballPosition = 0;
                } else {
                    // If the striker loses the ball
                    commentaryList.add("[" + (90 - time) + ":00]" + teams[teamWithBall].getName() + " striker loses the ball.");
                    teamWithBall = (teamWithBall + 1) % 2;
                    ballPosition = 2;
                }
            }
            else if (ballPosition == 2) {
                // If the ball is with the midfield
                winningProbability = teamStats.get(teamWithBall)[1] - teamStats.get((teamWithBall + 1) % 2)[1];
                winningProbability = (winningProbability + 100) / 2;

                random_chance = random.nextInt(100);

                if (random_chance <= winningProbability) {
                    // If the midfield gets ball to the striker
                    commentaryList.add("[" + (90 - time) + ":00]" + teams[teamWithBall].getName() + " midfield makes a pass to the strikers.");
                    ballPosition = 1;
                } else {
                    // If the striker loses the ball
                    commentaryList.add("[" + (90 - time) + ":00]" + teams[teamWithBall].getName() + " midfield loses the ball.");
                    teamWithBall = (teamWithBall + 1) % 2;
                    ballPosition = 2;
                }
            }
            else if (ballPosition == 4) {
                // If the ball is with the defense
                winningProbability = teamStats.get(teamWithBall)[2] - teamStats.get((teamWithBall + 1) % 2)[0];
                winningProbability = (winningProbability + 100) / 2;

                random_chance = random.nextInt(100);

                if (random_chance <= winningProbability) {
                    // If the defense gets ball to the midfield
                    commentaryList.add("[" + (90 - time) + ":00]" + teams[teamWithBall].getName() + " defense makes a pass to the midfield.");
                    ballPosition = 2;
                } else {
                    // If the defense loses the ball
                    commentaryList.add("[" + (90 - time) + ":00]" + teams[teamWithBall].getName() + " defense loses the ball.");
                    teamWithBall = (teamWithBall + 1) % 2;
                    ballPosition = 1;
                }
            }
            time -= 5; // Decrease the time by 5 minutes
        }
        commentaryList.add("[" + (90 - time) + ":00]The match has ended.");
        commentaryList.add("The final score is " + teams[0].getName() + " " + score[0] + " - " + score[1] + " " + teams[1].getName() + ".");
    }

    /**
     * This method is used to update the game after it has ended.
     * 
     * @param manager The game manager.
     */
    private void onGameEnd(GameManager manager) {
        // Decrease the stamina of the players
        for (Player player : teams[0].getTeam()) {
            player.decStamina(20);
        }

        // Increase the stamina of the players on bench
        for (Player player : teams[0].getBench()) {
            if (player != null) {
                player.incStamina(50);
            }
        }

        // Update the points and money of the teams
        if (score[0] > score[1]) {
            teams[0].setPoints(teams[0].getPoints() + pointsToWin);
            manager.money = (manager.money + moneyToWin);
        } else {
            teams[1].setPoints(teams[0].getPoints() + (pointsToWin/2));
        }
    }
}
