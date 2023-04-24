package main;

import java.util.ArrayList;

/**
 * This class represents Teams that either the player is using, or against.
 */

class Team {
    private static ArrayList<Team> teamList;
    private String name;
    private ArrayList<Player> onTeam;
    private ArrayList<Player> onBench;
    private ArrayList<Item> 
    private Coach coach;
    private int rank;

    public Team(String name, ArrayList<Player> onTeam, ArrayList<Player> onBench, Coach coach, int rank) {
    	/**
    	 * Constructor for the Team object.
    	 * 
    	 * @param name		The name of the Team.
    	 * @param onTeam	The list of players of type Player in the Team.
    	 * @param onBench	The list of players of type Player on the bench.
    	 * @param coach		The coach of type Coach of the team.
    	 * @param rank		The rank of the team in the rankings.
    	 */
        this.name = name;
        this.onTeam = onTeam;
        this.onBench = onBench;
        this.coach = coach;
        this.rank = rank;
    }

    public String getName() {
    	/**
    	 * Returns the name of the Team.
    	 * 
    	 * @return The name of the Team.
    	 */
        return this.name;
    }
    
    public void setName(String name) {
    	/**
    	 * Sets the name of the Team.
    	 * 
    	 * @param name The name that the Team will be set to.
    	 */
    	this.name = name;
    }

    public ArrayList<Player> getTeam() {
    	/**
    	 * Returns an ArrayList of players that are in the Team.
    	 * 
    	 * @return The ArrayList of players in the Team.
    	 */
        return this.onTeam;
    }
    
    public void setTeam(ArrayList<Player> playerList) {
    	/**
    	 * Sets the ArrayList of players in the Team.
    	 * 
    	 * @param playerList The ArrayList of players that the Team will be given.
    	 */
    	this.onTeam = playerlist;
    }

    public ArrayList<Player> getBench() {
    	/**
    	 * Returns the ArrayList of players that are one the bench.
    	 * 
    	 * @return The ArrayList of players that are on the bench.
    	 */
        return this.onBench;
    }
    
    public void setBench(ArrayList<Player> newBench) {
    	/**
    	 * Sets the players that are on the bench.
    	 * 
    	 * @param newBench The ArrayList of players being benched.
    	 */
    }
}