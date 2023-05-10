package main.body;

import java.util.ArrayList;
import java.util.Random;

/**
 * This class is used to create a team object. 
 * It contains the team name, the players on the team, the players on the bench, the coach, and the rank of the team.
 */
public class Team {

    private final static int[] formation = {4, 3, 3, 1};
    private static ArrayList<Team> teamList = new ArrayList<Team>();
    private String name;
    private ArrayList<Player> onTeam;
    private ArrayList<Player> onBench;
    private ArrayList<Item> items;
    private Coach coach;
    private int points;

    /**
     * This is the constructor for the Team class.
     * @param name The name of the team.
     * @param onTeam The players on the team.
     * @param onBench The players on the bench.
     * @param coach The coach of the team.
     * @param rank The rank of the team.
     */
    public Team(String name, ArrayList<Player> onTeam, ArrayList<Player> onBench, ArrayList<Item> items, Coach coach, int points) {

        this.name = name;
        this.onTeam = onTeam;
        this.onBench = onBench;
        this.items = items;
        this.coach = coach;
        this.points = points;
        teamList.add(this);
    }

    /**
     * This method is used to generate a random team based on difficulty.
     * @param rarity The difficulty of the team.
     * @return A random team.
     */
     public static Team createRandomTeam(String rarity) {
        Random random = new Random();

        String name = "Team " + (int) (random.nextDouble() * 1000);
        ArrayList<Player> onTeam = new ArrayList<Player>();
        ArrayList<Player> onBench = new ArrayList<Player>();
        ArrayList<Item> items = new ArrayList<Item>();
        Coach coach = Coach.createRandomCoach(rarity);


        // Creates a onTeam of random playes depending on the formation.
        // Adds one player to the bench for each position.
        int i = 0;
        for (AvailablePositions position : AvailablePositions.values()) {
            for (int j = 0; j < formation[i]; j++) {
                Player teamPlayer = Player.createRandomPlayer(rarity, position);
                onTeam.add(teamPlayer);
            }
            Player benchPlayer = Player.createRandomPlayer(rarity, position);
            onBench.add(benchPlayer);
            i++;
            }

        Team team = new Team(name, onTeam, onBench, items, coach, 0);
        return team;
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
     * Throws an exception if one of the players does not exist or if the players do not have the same position.
     * @param playerOnTeam This is the player on the team going onto bench.
     * @param playerOnBench This is the player on the bench going onto the team.
     */
    public void subPlayerSwap(int numPlayerOnTeam, int numPlayerOnBench) throws Exception {
        if (this.onTeam.get(numPlayerOnTeam) != null && this.onBench.get(numPlayerOnBench) != null) {
            if (this.onTeam.get(numPlayerOnTeam).getPosition() == this.onBench.get(numPlayerOnBench).getPosition()) {
                Player temp = this.onTeam.get(numPlayerOnTeam);
                this.onTeam.set(numPlayerOnTeam, this.onBench.get(numPlayerOnBench));
                this.onBench.set(numPlayerOnBench, temp);
            }else {
                throw new IllegalArgumentException("Players must have the same position.");
            }
        }else {
            throw new IllegalArgumentException("Player does not exist.");
        }
    }

    /**
     * This method removes a player from the team.
     * Throws an exception if the player does not exist.
     */
    public void sellPlayer(int playerNum) throws Exception {
        if (this.onBench.get(playerNum) != null) {
            Player player = this.onBench.get(playerNum);
            this.onBench.remove(player);
            System.out.println("Player removed.");
        }else {
            throw new IllegalArgumentException("Player does not exist.");
        }
    }

    /**
     * This method is used to get the teams items.
     * @return The teams items.
     */
    public ArrayList<Item> getItems() {
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
     * This method is used to get the points of the team.
     * @return The points of the team.
     */
    public int getPoints() {
        return this.points;
    }

    /**
     * This method is used to set the rank of the team.
     * @param rank The rank of the team.
     */
    public void setPoints(int points) {
        this.points = points;
    }
    
    /**
     * Adds a player onto the bench.
     * @param player	The player.
     */
    public void addPlayerToBench(Player player) {
    	this.onBench.add(player);
    }

    /**
     * This method is used to sort the teamList based on ponits.
     */
    public static void sortTeamList() {
        teamList.sort((team1, team2) -> team2.getPoints() - team1.getPoints());
    }
}