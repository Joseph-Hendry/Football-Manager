package main;

import java.util.List;

public class Match {
    private String[] teams = new String[2];
    private Stadium stadium;
    private int[] score = new int[2];
    private List[] teamStats = new List[2];
    private int time;

    /**
     * This is the constructor for the Match class.
     * @param teams The teams playing in the match.
     * @param stadium The stadium the match is being played in.
     * @param time The time of the match.
     */
    public Match(String[] teams, Stadium stadium, int time) {
        this.teams = teams;
        this.stadium = stadium;
        this.time = time;
    }

    /**
     * This method is used to play a match between two teams.
     * @return The score of the match.
     */
    public int playMatch() {
        // To be continued
        return 0;
    }

    private void finTeamStats() {
        // To be continued
    }
    
}
