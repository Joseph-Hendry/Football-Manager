package main;

import java.util.ArrayList;

/**
<<<<<<< HEAD
 * This class is used to create a team object. 
 * It contains the team name, the players on the team, the players on the bench, the coach, and the rank of the team.
 */

class Team {

    private static ArrayList<Team> teamList = new ArrayList<Team>();
    private String name;
    private ArrayList<Player> onTeam;
    private ArrayList<Player> onBench;
    private ArrayList<Item> items;
    private Coach coach;
    private int rank;

    /**
     * This is the constructor for the Team class.
     * @param name The name of the team.
     * @param onTeam The players on the team.
     * @param onBench The players on the bench.
     * @param coach The coach of the team.
     * @param rank The rank of the team.
     */
=======
 * This class represents Teams that either the player is using, or against.
 */
>>>>>>> 493440f53514746fd0da9976889fe6bf844955b9

class Team {
    private static ArrayList<Team> teamList;
    private String name;
    private ArrayList<Player> onTeam;
    private ArrayList<Player> onBench;
    private ArrayList<Item> items;
    private Coach coach;
    private int rank;

    public Team(String name, ArrayList<Player> onTeam, ArrayList<Player> onBench, ArrayList<Item> items, Coach coach, int rank) {
    	/**
    	 * Constructor for the Team object.
    	 * 
    	 * @param name		The name of the Team.
    	 * @param onTeam	The list of players of type Player in the Team.
    	 * @param onBench	The list of players of type Player on the bench.
    	 * @param coach		The coach of type Coach of the team.
    	 * @param items		The ArrayList of items that the team has.
    	 * @param rank		The rank of the team in the rankings.
    	 */
        this.name = name;
        this.onTeam = onTeam;
        this.onBench = onBench;
        this.items = items;
        this.coach = coach;
        this.rank = rank;
        teamList.add(this);
    }

<<<<<<< HEAD
    /**
     * This method is used to generate a random team.
     */

     public static createRandomTeam() {

        // Creates a random team name.
        String name = "Team " + (int) (Math.random() * 1000);

        // Creates a onTeam of random players.
        ArrayList<Player> onTeam = new ArrayList<Player>();

        for (int i = 0; i < 11; i++) {
            Player player = Player.createRandomPlayer();
            this.onTeam.add(player);
        }

        // Creates a onBench of random players.
        ArrayList<Player> onBench = new ArrayList<Player>();

        for (int i = 0; i < 6; i++) {
            Player player = Player.createRandomPlayer();
            this.onTeam.add(player);
        }

        // Creates a random coach.
        Coach coach = Coach.createRandomCoach();


        // Creates a random team rank (1-3).
        int rank = (int) (Math.random() * 3) + 1;
        
        // Creates a new team.
        Team team = new Team(name, onTeam, onBench, coach, rank);
    }

    /**
     * This method is used to get the name of the team.
     * @return The name of the team.
     */

    public String getName() {
=======
    public String getName() {
    	/**
    	 * Returns the name of the Team.
    	 * 
    	 * @return The name of the Team.
    	 */
>>>>>>> 493440f53514746fd0da9976889fe6bf844955b9
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

    /**
     * This method is used to get the players on the team.
     * @return The players on the team.
     */

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
    	this.onTeam = playerList;
    }

    /**
     * This method is used to set the players on the team.
     * @param onTeam The players on the team.
     */

     public void setTeam(ArrayList<Player> onTeam) {
        this.onTeam = onTeam;
    }

    /**
     * This method is used to get the players on the bench.
     * @return The players on the bench.
     */

    public ArrayList<Player> getBench() {
    	/**
    	 * Returns the ArrayList of players that are one the bench.
    	 * 
    	 * @return The ArrayList of players that are on the bench.
    	 */
        return this.onBench;
    }
<<<<<<< HEAD

    /**
     * This method is used to set the players on the bench.
     * @param onBench The players on the bench.
     */

    public void setBench(ArrayList<Player> onBench) {
        this.onBench = onBench;
    }

    /**
     * This method is used to sub two players.
     * @param playerOnTeam This is the player on the team going onto bench.
     * @param playerOnBench This is the player on the bench going onto the team.
     */

    public void subPlayerSwap(Player playerOnTeam, Player playerOnBench) {
        this.onTeam.remove(playerOnTeam);
        this.onBench.remove(playerOnBench);
        this.onTeam.add(playerOnBench);
        this.onBench.add(playerOnTeam);
    }

    /**
     * This method is used to get the teams items.
     * @return The teams items.
     */

    public ArrayList<Items> getItems() {
        return this.items;
    }

    /**
     * This method is used to add an item to the team.
     * @param item The added item.
     */

    public void addItem(Item item) {
        this.items.add(item);
    }

    /**
     * This method is used to remove an item from the team.
     * @param item The removed item.
     */

    public void removeItem(Item item) {
        this.items.remove(item);
    }

    /**
     * This method is used to get the coach of the team.
     * @return The coach of the team.
     */

    public Coach getCoach() {
        return this.coach;
    }

    /**
     * This method is used to set the coach of the team.
     * @param coach The coach of the team.
     */

    public void setCoach(Coach coach) {
        this.coach = coach;
    }

    /** 
     * This method is used to get the rank of the team.
     * @return The rank of the team.
     */

    public int getRank() {
        return this.rank;
    }

    /**
     * This method is used to set the rank of the team.
     * @param rank The rank of the team.
     */

    public void setRank(int rank) {
        this.rank = rank;
=======
    
    public void setBench(ArrayList<Player> newBench) {
    	/**
    	 * Sets the players that are on the bench.
    	 * 
    	 * @param newBench The ArrayList of players being benched.
    	 */
    	this.onBench = newBench;
    }
    
    public ArrayList<Item> getItems() {
    	/**
    	 * Returns the ArrayList of items that the Team has.
    	 * 
    	 * @return The ArrayList of items that the Team has.
    	 */
    	return this.items;
    }
    
    public void setItems(ArrayList<Item> items) {
    	/**
    	 * Sets the items that the Team has.
    	 * 
    	 * @param The ArrayList of items to give the Team.
    	 */
    	this.items = items;
    }
    
    public Coach getCoach() {
    	/**
    	 * Returns the Coach of the Team.
    	 * 
    	 * @return The Coach of the Team.
    	 */
    	return this.coach;
    }
    
    public void setCoach(Coach coach) {
    	/**
    	 * Sets the Coach of the Team.
    	 * 
    	 * @param coach The Coach to be set to the Team.
    	 */
    	this.coach = coach;
    }
    
    public int getRank() {
    	/**
    	 * Returns the rank of the Team.
    	 * 
    	 * @return The rank of the Team.
    	 */
    	return this.rank;
    }
    
    public void setRank(int rank) {
    	/**
    	 * Sets the rank of the Team.
    	 * 
    	 * @param rank The new rank of the Team.
    	 */
    	this.rank = rank;
>>>>>>> 493440f53514746fd0da9976889fe6bf844955b9
    }
}