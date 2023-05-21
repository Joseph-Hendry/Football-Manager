package main.body;

import java.util.Random;

/**
 * This class implements a Coach.
 * This stores a name, rarity, value and stats.
 * Extends the {@link Person} class.
 */
public class Coach extends Person {

	// The stats of the Coach
    private int[] stats = new int[3];

    /**
     * The constructor for the Coach object.
	 * 
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
	 * Creates and returns a random coach with the given rarity.
	 * 
	 * @param rarity The rarity of the Coach.
	 * @return The random Coach with the given rarity.
	 */
	public static Coach createRandomCoach(String rarity) {

		Random random = new Random();

		// Generate the name of the coach (Coach + random number)
		String name = "Coach" + random.nextInt(1000);

		// Generate the value and stats of the coach based on the rarity
		int value = 6;
		int[] stats = new int[3];

		if (rarity == "Bronze") {
			// If the rarity is bronze the rating is between 40 - 60 and the value is between 400 - 600
			value = random.nextInt(200) + 400;
			stats[0] = random.nextInt(20) + 40;
			stats[1] = random.nextInt(20) + 40;
			stats[2] = random.nextInt(20) + 40;

		} else if (rarity == "Silver") {
			// If the rarity is silver the rating is between 60 - 80 and the value is between 600 - 800
			value = random.nextInt(200) + 600;
			stats[0] = random.nextInt(20) + 60;
			stats[1] = random.nextInt(20) + 60;
			stats[2] = random.nextInt(20) + 60;

		} else if (rarity == "Gold") {
			// If the rarity is gold the rating is between 80 - 90 and the value is between 800 - 900
			value = random.nextInt(100) + 800;
			stats[0] = random.nextInt(10) + 80;
			stats[1] = random.nextInt(10) + 80;
			stats[2] = random.nextInt(10) + 80;

		} else {
			// If the rarity is platinum the rating is between 90 - 100 and the value is between 900 - 1000
			value = random.nextInt(100) + 900;
			stats[0] = random.nextInt(10) + 90;
			stats[1] = random.nextInt(10) + 90;
			stats[2] = random.nextInt(10) + 90;
		}

		// Return the new coach
		return new Coach(name, rarity, value, stats);
	}

    /**
	 * Returns values of the Coach in a String format.
	 * 
	 * @return Coach values in string format.
	 */
	public String toString() {
		return String.format("%-10s %-2s %-2s %-5s %-4s", this.name, this.stats[0], this.stats[1], this.stats[2], this.value);
	}

    /**
     * This method is used to get the stats of the Coach.
	 * 
     * @return The stats of the Coach.
     */
    public int[] getStats() {
        return this.stats;
    }
}
