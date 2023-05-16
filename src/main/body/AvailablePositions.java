package main.body;

import java.util.Random;

public enum AvailablePositions {
	/**
	 * An enumeration of the positions that a Player can play in.
	 */
	DEFENCE, MIDFIELD, STRIKER, GOALKEEPER;

	/**
	 * Returns the position in a String format.
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
	 * @return A random position.
	 */
	public static AvailablePositions getRandomPosition() {
		Random random = new Random();
		return values()[random.nextInt(values().length)];
	}
}