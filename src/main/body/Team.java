package main.body;

import java.util.ArrayList;
import java.util.Random;

/**
 * This class is used to create a team object. 
 * It contains the team name, the players on the team, the players on the bench, the coach, and the rank of the team.
 */
public class Team {

    private final static int[] formation = {4, 3, 3, 1};
    private final static AvailablePositions[] formationPostion = {AvailablePositions.DEFENCE, AvailablePositions.DEFENCE, AvailablePositions.DEFENCE, AvailablePositions.DEFENCE,
                                                                AvailablePositions.MIDFIELD, AvailablePositions.MIDFIELD, AvailablePositions.MIDFIELD, 
                                                                AvailablePositions.STRIKER, AvailablePositions.STRIKER, AvailablePositions.STRIKER, 
                                                                AvailablePositions.GOALKEEPER};
    private final static String[] names = {"Arsenal", "Aston Villa", "Bournemouth", "Brighton", "Burnley"};
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
     * This method is used to create a random team.
     * @param rarity The difficulty of the team.
     * @return A random team.
     */
    public static void createNPCTeams(int intRarity) {
        for (int i = 0; i < names.length; i++) {
            createRandomTeam(intRarity, names[i]);
        }
    }

    public void resetNPCTeams(int intRarity) {
        for (Team temp : teamList) {
            if (temp != this) {
                Coach tempCoach = Coach.createRandomCoach(getStrRarity(intRarity));
                temp.setCoach(tempCoach);

                ArrayList<Player> tempOnTeam = new ArrayList<Player>();
                ArrayList<Player> tempOnBench = new ArrayList<Player>();

                int i = 0;
                for (AvailablePositions position : AvailablePositions.values()) {
                    for (int j = 0; j < formation[i]; j++) {
                        Player teamPlayer = Player.createRandomPlayer(getStrRarity(intRarity), position);
                        tempOnTeam.add(teamPlayer);
                    }
                    int benchVariety = new Random().nextInt(20) - 10; 
                	int benchRarity = intRarity + benchVariety;
                    Player benchPlayer = Player.createRandomPlayer(getStrRarity(benchRarity), position);
                    tempOnBench.add(benchPlayer);
                    i++;
                }

                temp.setTeam(tempOnTeam);
                temp.setBench(tempOnBench);
            }
        }
    }

    /**
     * This method is used to generate a random team based on difficulty.
     * @param rarity The difficulty of the team.
     * @return A random team.
     */
     public static Team createRandomTeam(int intRarity, String name) {
        Random random = new Random();

        ArrayList<Player> onTeam = new ArrayList<Player>();
        ArrayList<Player> onBench = new ArrayList<Player>();
        ArrayList<Item> items = new ArrayList<Item>();
        
        int coachVariety = random.nextInt(20) - 10; 
        
        Coach coach = Coach.createRandomCoach(getStrRarity(intRarity + coachVariety));


        // Creates a onTeam of random players depending on the formation.
        // Adds one player to the bench for each position.
        int i = 0;
        for (AvailablePositions position : AvailablePositions.values()) {
            for (int j = 0; j < formation[i]; j++) {
            	int playerVariety = random.nextInt(20) - 10; 
            	int playerRarity = intRarity + playerVariety;
                Player teamPlayer = Player.createRandomPlayer(getStrRarity(playerRarity), position);
                onTeam.add(teamPlayer);
            }
            int benchVariety = random.nextInt(20) - 10; 
        	int benchRarity = intRarity + benchVariety;
            Player benchPlayer = Player.createRandomPlayer(getStrRarity(benchRarity), position);
            onBench.add(benchPlayer);
            i++;
            }

        Team team = new Team(name, onTeam, onBench, items, coach, 0);
        return team;
    }
     
 	/**
 	 * Converts a range of 0-100 into its rarity type.
 	 * @param intRarity	The int value of the rarity.
 	 * @return	The string rarity.
 	 */
 	public static String getStrRarity(int intRarity) {
 	if (intRarity <= 50) {
 		return "Bronze";
 	} else if (intRarity <= 80) {
 		return "Silver";
 	} else if (intRarity <= 95) {
 		return "Gold";
 	} else {
 		return "Platinum";
 		}
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
     * This method removes a player from the team.
     * 
     * @param player	The player.
     */
    public void removePlayer(int playerNum) {
        this.onTeam.set(playerNum, null);
    }

    public void addPlayerToBench(Player player, int playerNum) {
        this.onBench.set(playerNum, player);
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
     * Get the player position.
     */
    public static AvailablePositions[] getFormationPostion() {
        return formationPostion;
    }

    /**
     * This method removes a player from the team.
     * Throws an exception if the player does not exist.
     */
    public void sellPlayer(GameManager manager, Player player) throws Exception {
        if (player != null) {
            if (this.onTeam.contains(player)) {
                int playerNum = this.onTeam.indexOf(player);
                this.onTeam.set(playerNum, null);
                manager.incMoney(player.getValue());
            } else if (this.onBench.contains(player)) {
                int playerNum = this.onBench.indexOf(player);
                this.onBench.set(playerNum, null);
                manager.incMoney(player.getValue());
            } else {
                throw new IllegalArgumentException("Player does not exist");
            }
        } else {
            throw new IllegalArgumentException("Player does not exist");
        }
    }

    /**
     * This method is used to sub two players.
     * Throws an exception if one of the players does not exist or if the players do not have the same position.
     * @param playerOnTeam This is the player on the team going onto bench.
     * @param playerOnBench This is the player on the bench going onto the team.
     */
    public void subPlayerSwap(Player teamPlayer, Player benchPlayer) throws Exception {
        if (this.onTeam.contains(teamPlayer) && this.onBench.contains(benchPlayer)) {
            if (teamPlayer != null && benchPlayer != null) {
                if (teamPlayer.getPosition() == benchPlayer.getPosition()) {
                    int teamPlayerNum = this.onTeam.indexOf(teamPlayer);
                    int benchPlayerNum = this.onBench.indexOf(benchPlayer);
                    this.onTeam.set(teamPlayerNum, benchPlayer);
                    this.onBench.set(benchPlayerNum, teamPlayer);
                } else {
                throw new IllegalArgumentException("Players must have the same position.");
                }
            } else {
            throw new IllegalArgumentException("Player does not exist.");
            }
        } else {
            throw new IllegalArgumentException("Invalid Number");
        }
    }

    public void sellItem(GameManager manager, Item item) throws IllegalArgumentException {
        if (this.items.contains(item)) {
            manager.incMoney(item.getValue());
            this.items.remove(item);
        } else {
            throw new IllegalArgumentException("Item does not exist");
        }
    }

    public void buyItem(GameManager manager, Item item) throws IllegalArgumentException {
        if (this.items.size() >= 3) {
            throw new IllegalArgumentException("You can only have 3 items.");
        }
        if (manager.getStore().getStoreItems().contains(item))
            if (manager.getMoney() >= item.getValue()) {
                manager.decMoney(item.getValue());
                manager.getStore().removeItem(item);
                this.items.add(item);
            } else {
                throw new IllegalArgumentException("You do not have enough money.");
            }
    }

    public void buyPlayer(GameManager manager, Player player, int teamOrBench) throws IllegalArgumentException {
        if (teamOrBench == 0) {
            if (this.onTeam.size() >= 1) {
                throw new IllegalArgumentException("You can only have 11 players on your team.");
            }
            if (manager.getStore().getStorePlayers().contains(player)) {
                if (manager.getMoney() >= player.getValue()) {
                    manager.decMoney(player.getValue());
                    manager.getStore().removePlayer(player);
                    this.onTeam.add(player);
                } else {
                    throw new IllegalArgumentException("You do not have enough money.");
                }
            } else {
                throw new IllegalArgumentException("Player does not exist.");
            }
        } else if (teamOrBench == 0) {
            if (this.onBench.size() >= 1) {
                throw new IllegalArgumentException("You can only have 5 players on your bench.");
            }
            if (manager.getStore().getStorePlayers().contains(player)) {
                if (manager.getMoney() >= player.getValue()) {
                    manager.decMoney(player.getValue());
                    manager.getStore().removePlayer(player);
                    this.onBench.add(player);
                } else {
                    throw new IllegalArgumentException("You do not have enough money.");
                }
            } else {
                throw new IllegalArgumentException("Player does not exist.");
            }
        } else {
            throw new IllegalArgumentException("Invalid input.");
        }
    }

    public void buyCoach(GameManager manager, Coach coach) throws IllegalArgumentException {
        if (manager.getStore().getStoreCoach() == coach) {
            if (manager.getMoney() >= coach.getValue()) {
                manager.decMoney(coach.getValue());
                manager.getStore().setCoachAvailable(false);
                this.coach = coach;
            } else {
                throw new IllegalArgumentException("You do not have enough money.");
            }
        } else {
            throw new IllegalArgumentException("Coach does not exist.");
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
    public void addItem(Item item) throws Exception {
        if (this.items.size() >= 3) {
            throw new Exception("You can only have 3 items.");
        }
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
    public void addPlayerToBench(Player player) throws Exception {
        for (int i = 0; i < this.onBench.size(); i++) {
            if (this.onBench.get(i) == null) {
                this.onBench.set(i, player);
                return;
            }
        }
        throw new Exception("Bench is full");
    }
    
    /**
     * Adds a player onto the bench.
     * @param player	The player.
     */
    public void addPlayerToTeam(Player player) throws Exception {
        AvailablePositions position = player.getPosition();
        int i = 0;
        for (AvailablePositions pos : AvailablePositions.values()) {
            if (pos == position) {
                for (int j = 0; j < formation[i]; j++) {
                    if (this.onTeam.get(j) == null) {
                        this.onTeam.set(j, player);
                        return;
                    }
                }
            }
            i++;
        }
        throw new Exception("No room in team for this players position.");
    }

    /**
     * This method is used to sort the teamList based on ponits.
     */
    public static void sortTeamList() {
        teamList.sort((team1, team2) -> team2.getPoints() - team1.getPoints());
    }

    /**
     * This method is used to get the teamList.
     * @return The teamList.
     */
    public static ArrayList<Team> getTeamList() {
        return teamList;
    }

    /**
     * This method return the formation of the team.
     * @return The formation of the team.
     */
    public static int[] getFormation() {
        return formation;
    }
}