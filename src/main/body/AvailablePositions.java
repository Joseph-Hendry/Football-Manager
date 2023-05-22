package main.body;

import java.util.Random;

/**
 * An enumeration of the positions that a Player can play in.
 */
public enum AvailablePositions {
	/**
	 * The defensive position.
	 */
	DEFENCE,
	
	/**
	 * The midfield position.
	 */
	MIDFIELD,
	
	/**
	 * The striker position.
	 */
	STRIKER,
	
	/**
	 * The goalkeeper position.
	 */
	GOALKEEPER;

	/**
	 * Returns the position in a String format.
	 * 
	 * @return The position in a String format.
	 */
	public String toString() {
		switch (this) {
			case DEFENCE:
				return "Defence";
			case MIDFIELD:
				return "Midfield";
			case STRIKER:
				return "Striker";
			case GOALKEEPER:
				return "Goalkeeper";
			default:
				return "Unknown";
		}
	}

	/**
	 * Returns a random position.
	 * 
	 * @return A random position.
	 */
	public static AvailablePositions getRandomPosition() {
		Random random = new Random();
		return values()[random.nextInt(values().length)];
	}
}