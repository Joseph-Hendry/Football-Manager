package main.body;

/**
 * This class implements a Person that stores a name, rarity and value.
 * The Person class is used in the Player and Coach classes.
 */
public class Person {
	protected String name;
	protected String rarity;
	protected int value;

	/**
	 * Constructor for the Person object.
	 * @param theName	The name of the Person.
	 * @param theRarity	The rarity of the Person.
	 * @param theValue	The value of the Person.
	 */
	public Person(String name, String rarity, int value) {
		this.name = name;
		this.rarity = rarity;
		this.value = value;
	}
	
	/**
	 * Returns the name of the Person.
	 * @return The name of the Person.
	 */
	public String getName() {
		return this.name;
	}
	
	/**
	 * Sets the name of the person.
	 * @param name The name being given to the Person.
	 */
	public void setName(String name) {
		this.name = name;
	}
	
	/**
	 * Returns the rarity of the Person.
	 * @return the rarity of the Person.
	 */
	public String getRarity() {
		return this.rarity;
	}
	
	/**
	 * Sets the rarity of the person.
	 * @param rarity The rarity given to the Person.
	 */
	public void setRarity(String rarity) {
		this.rarity = rarity;
	}
	
	/**
	 * Returns the name of the Person.
	 * @return the name of the Person.
	 */
	public int getValue() {
		return this.value;
	}
	
	/**
	 * Sets the value of the Person in dollars.
	 * @param value The value of the person in dollars.
	 */
	public void setValue(int value) {
		this.value = value;
	}
}
