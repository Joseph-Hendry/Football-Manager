package main.body;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

public class Match {
    private int[] score = {0, 0};
    private Team[] teams = new Team[2];
    public int[] playerTeamStats = new int[4];
    private int[] NPCTeamStats = new int[4];
    private ArrayList<String> commentaryList = new ArrayList<String>();
    private int pointsToWin;
    private int moneyToWin;
    private int time;


    ////////// Match Setup //////////


    /**
     * This is the constructor for the Match class.
     * 
     * @param playerTeam 	The player's team.
     * @param NPCTeam 		The NPC's team.
     * @param pointsToWin 	The points to win.
     * @param moneyToWin 	The money to win.
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
     * @param team 	The team to find the stats of.
     * @return 		The overall stats of the team.
     */
    private int[] findTeamStats(Team team) {
        // Initialize the stats
        int[] teamStats = {0, 0, 0, 0};

        // Stats depend on the formation and player position
        int[] formation = Team.getFormation();

        // Get the players on the team
        ArrayList<Player> teamList = team.getTeam();
        
        for (Player player : teamList) {
                int[] playerStats = player.getStats();
                AvailablePositions position = player.getPosition();
                switch (position) {
                    case DEFENCE:
                        teamStats[0] += (playerStats[0] * 0.2 + playerStats[1] * 0.2 + playerStats[2] * 0.6);
                        break;
                    case MIDFIELD:
                        teamStats[1] += (playerStats[0] * 0.2 + playerStats[1] * 0.6 + playerStats[2] * 0.2);
                        break;
                    case STRIKER:
                        teamStats[2] += (playerStats[0] * 0.6 + playerStats[1] * 0.2 + playerStats[2] * 0.2);
                        break;
                    case GOALKEEPER:
                        teamStats[3] += (playerStats[0] * 0.3 + playerStats[1] * 0.3 + playerStats[2] * 0.4);
                        break;
                }
            }
        // Divide the stats by the number of players in each position
        teamStats[0] /= formation[0];
        teamStats[1] /= formation[1];
        teamStats[2] /= formation[2];
        teamStats[3] /= formation[3];
        return teamStats;
    }


    ////////// Getters and Setters //////////

    
    /**
     * This method is used to get the score of the match.
     * 
     * @return  The score of the match.
     */
    public int[] getScore() {
        return score;
    }

    /**
     * This method is used to get the points to win.
     * @return  The points to win.
     */
    public int getPoints() {
        return pointsToWin;
    }

    /**
     * This method is used to get the money to win
     * .
     * @return  The money to win.
     */
    public int getMoney() {
        return moneyToWin;
    }

    /**
     * This method returns the opposing team.
     * 
     * @return  The opposing team.
     */
    public Team getOpposingTeam() {
        return teams[1];
    }

    /**
     * Get NPC team stats
     * 
     * @return  The NPC team stats.
     */
    public int[] getNPCTeamStats() {
        return NPCTeamStats;
    }

    /**
     * This is the getter for the points to win.
     * 
     * @return  The points to win.
     */
    public int getPointsToWin() {
        return pointsToWin;
    }

    /**
     * This is the getter for the money to win.
     * 
     * @return  The money to win.
     */
    public int getMoneyToWin() {
        return moneyToWin;
    }

    /**
     * This is the getter for the commentary list.
     * 
     * @return  The commentary list.
     */
    public ArrayList<String> getCommentaryList() {
        return commentaryList;
    }


    ////////// Play Match //////////


    /**
     * This method is used to play the match.
     * 
     * @param manager 	The game manager.
     */
    public void playMatch(GameManager manager) {
        setGameCommentary(manager);
        onGameEnd(manager);
    }

    /**
     * This method is used to set the commentary of the match.
     * 
     * @param manager 	The game manager.
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

        // Possetion team ball position (0 = Shooting, 1 = Attacking, 2 = Midfeild, 4 = Defense)
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
     * @param manager 	The game manager.
     */
    private void onGameEnd(GameManager manager) {
        // Decrease the stamina of the players
        for (Player player : teams[0].getTeam()) {
            player.decStamina(20);
        }

        // Increase the stamina of the players on bench
        for (Player player : teams[0].getBench()) {
            player.incStamina(50);
        }

        // Update the points and money of the teams
        if (score[0] > score[1]) {
            manager.UI.showMessage("Congratulations, " + teams[0].getName() + " has won the match.\n+ " + pointsToWin + " points and $" + moneyToWin + " has been added to your account.");
            teams[0].setPoints(teams[0].getPoints() + pointsToWin);
            manager.money = (manager.money + moneyToWin);
        } else if (score[0] < score[1]) {
            manager.UI.showMessage("Unfortunitly " +teams[1].getName() + " has won the match.");
        } else {
            manager.UI.showMessage("The match has ended in a draw.");
            teams[1].setPoints(teams[0].getPoints() + (pointsToWin/2));
        }
    }

    public static void main(String[] args) {
        // Test team stats
        Team team = Team.createRandomTeam(100, "Test1");
        Team team2 = Team.createRandomTeam(100, "Test2");
        Match match = new Match(team, team2, 3, 1000);
        int[] teamStats = match.playerTeamStats;
        System.out.println("Team 1 stats: " + teamStats[0] + " " + teamStats[1] + " " + teamStats[2] + " " + teamStats[3]);
        // System.out.println("Team 2 stats: " + team2Stats[0] + " " + team2Stats[1] + " " + team2Stats[2] + " " + team2Stats[3]);

        // Prints players in eam statistics
        System.out.println("Team 1 players:");
        for (Player player : team.getTeam()) {
            System.out.println(player.getName() + " " + player.getPosition() + " " + player.getStats()[0] + " " + player.getStats()[1] + " " + player.getStats()[2]);
        }
    }
}
