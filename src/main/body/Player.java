package main.body;

import java.util.Random;


/**
 *	This class implements the Players the are in the Teams in the game.
 *  It inherits from the Person class.
 */
public class Player extends Person {
	private String nickname;
	private int[] stats;
	private AvailablePositions position;
	private int stamina;
	private boolean injured;

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
		this.injured = false;
		this.stamina = 100;
	}

	/**
	 * Creates and returns a random Player with the given rarity.
	 * @param rarity The rarity of the Player.
	 * @return The random Player with the given rarity.
	 */
	public static Player createRandomPlayer(String rarity, AvailablePositions position) {

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

		// Return the new player
		return new Player(name, rarity, value, nickname, stats, position);
	}

	/**
	 * Returns values of the Player in a String format on one line all evenly spaced.
	 * @return Player values in string format.
	 */
	public String toString() {
		if (this.name == null) {
			return "Empty";
		} else {
			return String.format("%-10s %-2s %-2s %-5s %-6s %-12s %-4s", this.nickname, this.stats[0], this.stats[1], this.stats[2], this.stamina, this.position, this.value);
		}
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
	public void setStats(int[] stats) throws IllegalArgumentException {
		this.stats = stats;
		if (stats.length == 3) {
			this.stats = stats;
		} else {
			throw new IllegalArgumentException("Stats must be length 3");
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
	/**
	 * Sets the stamina of the player. If it is equal to or below 0 it is set to zero and the 
	 * player is injured.
	 * @param stamina	The stamina value to give to the player.
	 */
	public void setStamina(int stamina) {
		if (stamina < 0) {
			this.setInjured(true);
			this.stamina = 0;
		} else if (stamina > 100) {
			this.setInjured(false);
			this.stamina = 100;
		} else {
			this.stamina = stamina;
			this.setInjured(false);
		}		
	}

	/**
	 * Incriments the stamina of the player by the given amount.
	 * @param	the stamina amount.
	 */
	public void incStamina(int amount) {
		this.setStamina(this.stamina + amount);
	}

	/**
	 * Decriments the stamina of the player by the given amount.
	 * @param	the stamina amount
	 */
	public void decStamina(int amount) {
		this.setStamina(this.stamina - amount);
	}
	
	/**
	 * Returns the stamina of the player.
	 * @return	The stamina of the player.
	 */
	public int getStamina() {
		return this.stamina;
	}
	
	/**
	 * Sets whether the player is injured or not.
	 * @param injured	The injury status (true or false).
	 */
	public void setInjured(boolean injured) {
		this.injured = injured;
	}
	
	/**
	 * Returns whether the playter is injured or not.
	 * @return	Bool the injury status.
	 */
	public boolean isInjured() {
		return this.injured;
	}
}
