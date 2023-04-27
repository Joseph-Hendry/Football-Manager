package main;

/**
 * This class implements a Coach.
 * This stores a name, rarity, value and stats.
 */
public class Coach extends Person {
    private int[] stats = new int[3];

    /**
     * The constructor for the Coach object.
     * @param name The name of the Coach.
     * @param rarity The rarity of the Coach.
     * @param value The value of the Coach.
     * @param stats The statistics of the Coach of of 100 in order [passing, tackling, shooting].
     */
    public Coach(String name, String rarity, int value, int[] stats) {
        super(name, rarity, value);
        this.stats = stats;
    }

    /**
     * This method is used to geth the stats of the Coach.
     * @return The stats of the Coach.
     */
    public int[] getStats() {
        return this.stats;
    }
}
