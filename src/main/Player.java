package main;

import java.util.Random;

enum AvailablePositions {
	/**
	 * An enumeration of the positions that a Player can play in.
	 */
	LEFT_DEFENCE, CENTER_DEFENCE, RIGHT_DEFENCE, LEFT_MIDFIELD, CENTER_MIDFIELD, RIGHT_MIDFIELD, LEFT_WING, STRIKER, RIGHT_WING
}

/**
 *	This class implements the Players the are in the Teams in the game.
 *  It inherits from the Person class.
 */
public class Player extends Person {
	private String nickname;
	private int[] stats;
	private AvailablePositions position;

	/**
	 * The constructor for the Player object.
	 * @param name		The name of the Player.
	 * @param rarity	The rarity of the Plater.
	 * @param value		The value of the Player.
	 * @param nickname	The nickname of the Player.
	 * @param stats		The statistics of the player of of 100 in order [passing, tackling, shooting].
	 * @param position	The position that the Player plays in.
	 */
	public Player(String name, String rarity, int value, String nickname, int[] stats, AvailablePositions position) {
		super(name, rarity, value);
		this.nickname = nickname;
		this.stats = stats;
		this.position = position;
	}

	/**
	 * Creates and returns a random Player with the given rarity.
	 * @param rarity The rarity of the Player.
	 * @return The random Player with the given rarity.
	 */
	public static Player createRandomPlayer(String rarity) {

		Random random = new Random();

		// Generate the name and nickname of the player (Player + random number)
		String name = "Player" + random.nextInt(1000);
		String nickname = name;

		// Generate the value and stats of the player based on the rarity
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

		// Generate the position of the player
		int position_int = random.nextInt(9);
		AvailablePositions position = AvailablePositions.LEFT_DEFENCE;
		switch (position_int) {
			case 0:
				position = AvailablePositions.LEFT_DEFENCE;
				break;
			case 1:
				position = AvailablePositions.CENTER_DEFENCE;
				break;
			case 2:
				position = AvailablePositions.RIGHT_DEFENCE;
				break;
			case 3:
				position = AvailablePositions.LEFT_MIDFIELD;
				break;
			case 4:
				position = AvailablePositions.CENTER_MIDFIELD;
				break;
			case 5:
				position = AvailablePositions.RIGHT_MIDFIELD;
				break;
			case 6:
				position = AvailablePositions.LEFT_WING;
				break;
			case 7:
				position = AvailablePositions.STRIKER;
				break;
			case 8:
				position = AvailablePositions.RIGHT_WING;
				break;
		}

		// Return the new player
		return new Player(name, rarity, value, nickname, stats, position);
	}

	/**
	 * Returns values of the Player in a String format.
	 * @return Player values in string format.
	 */
	public String toString() {
		return "Player [name=" +super.name+ ", nickname=" +this.nickname+ ", rarity=" +super.rarity+ ", value=" +super.value+ ", stats=[" +stats[0]+ ", " +stats[1]+ ", " +stats[2]+ "], position=" +this.position+ "]";
	}
	
	/**
	 * Returns the nickname of the Player.
	 * @return The nickname of the Player.
	 */
	public String getNickname() {
		return this.nickname;
	}
	
	/**
	 * Sets the nickname of the Player.
	 * @param nickname The nickname of the Player.
	 */
	public void setNickname(String nickname) {
		this.nickname = nickname;
	}
	
	/**
	 * Returns the stats of the Player.
	 * @return The stats of the Player.
	 */
	public int[] getStats() {
		return this.stats;
	}

	/**
	 * Sets the stats of the Player.
	 * @param stats The stats of the Player.
	 */
	public void setStats(int[] stats) {
		if (stats.length == 3) {
			this.stats = stats;
		} else {
			System.out.println("Stats must be lenght 3");
		}
	}

	/**
	 * Returns the position that the Player plays in. 
	 * @return the position that the Player plays in.
	 */
	public AvailablePositions getPosition() {
		return this.position;
	}
	
	/**
	 * Sets the position of the Player.
	 * @param position The new position of the Player.
	 */
	public void setPosition(AvailablePositions position) {
		this.position = position;
	}
}
