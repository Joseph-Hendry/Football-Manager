package main;

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
	private String name;
	private String nickname;
	private String rarity;
	private int value;
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
	 * Returns the statistics of the Player.
	 * @return The statistics of the Player.
	 */
	public int[] getStats() {
		return this.stats;
	}
	
	/**
	 * Sets the statistics of the Player.
	 * @param stats The statistics of the Player.
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
