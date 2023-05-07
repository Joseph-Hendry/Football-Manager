package main.body;

/**
 * This class implements a Stadium.
 * This stores a name, home team and home advantage.
 */
public class Stadium {
    private String name;
    private Team homeTeam;
    private float homeAdvantage;

    /**
     * This is the constructor for the Stadium class.
     * @param name The name of the stadium.
     * @param homeTeam The home team of the stadium.
     * @param homeAdvantage The home advantage of the stadium.
     */
    public Stadium(String name, Team homeTeam, float homeAdvantage) {
        this.name = name;
        this.homeTeam = homeTeam;
        this.homeAdvantage = homeAdvantage;
    }

    /**
     * This method is used to get the name of the stadium.
     * @return The name of the stadium.
     */
    public String getName() {
        return this.name;
    }

    /**
     * This method is used to get the home team of the stadium.
     * @return The home team of the stadium.
     */
    public Team getHomeTeam() {
        return this.homeTeam;
    }

    /**
     * This method is used to get the home advantage boost of the stadium.
     * @return The home advantage of the stadium.
     */
    public float getHomeAdvantage() {
        return this.homeAdvantage;
    }

    /**
     * This method is used to set the name of the stadium.
     * @param homeAdvantage The home advantage of the stadium.
     */
    public void setHomeAdvantage(float homeAdvantage) {
        this.homeAdvantage = homeAdvantage;
    }
}
