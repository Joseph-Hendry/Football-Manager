package main;

import java.util.ArrayList;

/**
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
    public Team(String name, ArrayList<Player> onTeam, ArrayList<Player> onBench, ArrayList<Item> items, Coach coach, int rank) {

        this.name = name;
        this.onTeam = onTeam;
        this.onBench = onBench;
        this.items = items;
        this.coach = coach;
        this.rank = rank;
        teamList.add(this);
    }

    /**
     * This method is used to generate a random team.
     */
     public static void createRandomTeam() {

        // Creates a random team name.
        String name = "Team " + (int) (Math.random() * 1000);

        // Creates a onTeam of random players.
        ArrayList<Player> onTeam = new ArrayList<Player>();

        for (int i = 0; i < 11; i++) {
            Player player = Player.createRandomPlayer();
            onTeam.add(player);
        }

        // Creates a onBench of random players.
        ArrayList<Player> onBench = new ArrayList<Player>();

        for (int i = 0; i < 6; i++) {
            Player player = Player.createRandomPlayer();
            onTeam.add(player);
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
        return this.name;
    }

    /**
     * This method is used to set the name of the team.
     * @param name The name of the team.
     */
    public void setName(String name) {
    	this.name = name;
    }

    /**
     * This method is used to get the players on the team.
     * @return The players on the team.
     */
    public ArrayList<Player> getTeam() {
        return this.onTeam;
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
        return this.onBench;
    }

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
    public ArrayList<Item> getItems() {
        return this.item;
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
    }
}