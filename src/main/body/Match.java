package main.body;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;
import java.util.Timer;

public class Match {
    private int[] score = {0, 0};
    private Team[] teams = new Team[2];
    private int[] playerTeamStats = new int[4];
    private int[] NPCTeamStats = new int[4];
    private int time;

    /**
     * This is the constructor for the Match class.
     * @param teams The teams playing in the match.
     * @param stadium The stadium the match is being played in.
     * @param time The time of the match.
     */
    public Match(Team playerTeam, Team NPCTeam) {
        this.teams[0] = playerTeam;
        this.teams[1] = NPCTeam;
        this.playerTeamStats = findTeamStats(playerTeam);
        this.NPCTeamStats = findTeamStats(NPCTeam);
    }

    /**
     * This method is used to play a match between two teams.
     * @return The score of the match.
     */
    public void playMatch(GameManager manager) {
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
        manager.UI.showMessage("[00:00]" + teams[teamWithBall].getName() + " starts with the ball.");

        // Play the match
        while (time > 0) {
            if (ballPosition == 0) {
                // If the team is taking a shot on goal
                winningProbability = teamStats.get(teamWithBall)[0] - teamStats.get((teamWithBall + 1) % 2)[3];
                winningProbability = (winningProbability + 100) / 2;

                random_chance = random.nextInt(100);

                if (random_chance <= winningProbability) {
                    // If the team scores
                    manager.UI.showMessage("[" + (90 - time) + ":00]" + teams[teamWithBall].getName() + " scores!!!");
                    score[teamWithBall]++;
                    teamWithBall = (teamWithBall + 1) % 2;
                    ballPosition = 2;
                } else {
                    // If the team misses
                    manager.UI.showMessage("[" + (90 - time) + ":00]" + teams[teamWithBall].getName() + " misses.");
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
                    manager.UI.showMessage("[" + (90 - time) + ":00]" + teams[teamWithBall].getName() + " striker gets a shot on goal.");
                    ballPosition = 0;
                } else {
                    // If the striker loses the ball
                    manager.UI.showMessage("[" + (90 - time) + ":00]" + teams[teamWithBall].getName() + " striker loses the ball.");
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
                    manager.UI.showMessage("[" + (90 - time) + ":00]" + teams[teamWithBall].getName() + " midfield makes a pass to the strikers.");
                    ballPosition = 1;
                } else {
                    // If the striker loses the ball
                    manager.UI.showMessage("[" + (90 - time) + ":00]" + teams[teamWithBall].getName() + " midfield loses the ball.");
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
                    manager.UI.showMessage("[" + (90 - time) + ":00]" + teams[teamWithBall].getName() + " defense makes a pass to the midfield.");
                    ballPosition = 2;
                } else {
                    // If the defense loses the ball
                    manager.UI.showMessage("[" + (90 - time) + ":00]" + teams[teamWithBall].getName() + " defense loses the ball.");
                    teamWithBall = (teamWithBall + 1) % 2;
                    ballPosition = 1;
                }
            }
            time -= 5;
        }
        manager.UI.showMessage("[" + (90 - time) + ":00]The match has ended.");
        manager.UI.showMessage("The final score is " + teams[0].getName() + " " + score[0] + " - " + score[1] + " " + teams[1].getName() + ".");

        // Update the points of the teams
        onGameEnd(manager);
    }

    private int[] findTeamStats(Team team) {
        // Initialize the stats
        int[] teamStats = {0, 0, 0, 0};

        // Stats depend on the formation and player position
        int[] formation = Team.getFormation();

        // Get the players on the team
        ArrayList<Player> teamList = team.getTeam();
        
        for (int i = 0; i < formation.length; i++) {
            for (int j = 0; j < formation[i]; j++) {
                Player player = teamList.get(j + i);
                int[] playerStats = player.getStats();
                if (i == 0) {
                    if (player.getPosition() == AvailablePositions.DEFENCE) {
                        teamStats[2] += (playerStats[0] * 0.2 + playerStats[1] * 0.2 + playerStats[2] * 0.6);
                    } else {
                        teamStats[2] += (playerStats[0] * 0.2 + playerStats[1] * 0.2 + playerStats[2] * 0.2);
                    }
                } else if (i == 1) {
                    if (player.getPosition() == AvailablePositions.MIDFIELD) {
                        teamStats[1] += (playerStats[0] * 0.2 + playerStats[1] * 0.6 + playerStats[2] * 0.2);
                    } else {
                        teamStats[1] += (playerStats[0] * 0.2 + playerStats[1] * 0.2 + playerStats[2] * 0.2);
                    }
                } else if (i == 2) {
                    if (player.getPosition() == AvailablePositions.STRIKER) {
                        teamStats[0] += (playerStats[0] * 0.6 + playerStats[1] * 0.2 + playerStats[2] * 0.2);
                    } else {
                        teamStats[0] += (playerStats[0] * 0.2 + playerStats[1] * 0.2 + playerStats[2] * 0.2);
                    }
                } else {
                    if (player.getPosition() == AvailablePositions.GOALKEEPER) {
                        teamStats[0] += (playerStats[0] * 0.3 + playerStats[1] * 0.3 + playerStats[2] * 0.4);
                    } else {
                        teamStats[0] += (playerStats[0] * 0.2 + playerStats[1] * 0.2 + playerStats[2] * 0.2);
                    }
                }
            }
            teamStats[i] /= formation[i];
        }
        return teamStats;
    }

    private void onGameEnd(GameManager manager) {
        if (score[0] > score[1]) {
            manager.UI.showMessage(teams[0].getName() + " has won the match.");
            teams[0].setPoints(teams[0].getPoints() + 3);
        } else if (score[0] < score[1]) {
            manager.UI.showMessage(teams[1].getName() + " has won the match.");
            teams[1].setPoints(teams[1].getPoints() + 3);
        } else {
            manager.UI.showMessage("The match has ended in a draw.");
            teams[0].setPoints(teams[0].getPoints() + 1);
            teams[1].setPoints(teams[1].getPoints() + 1);
        }
    }
}
