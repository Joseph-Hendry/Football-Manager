package main.body;

/**
 * This class implements a Person that stores a name, rarity and value.
 * The Person class is the parent class of {@link Player} and {@link Coach}.
 */
public class Person {

	// The name of the Person
	protected String name;

	// The rarity of the Person
	protected String rarity;

	// The value of the Person
	protected int value;

	/**
	 * Constructor for the Person object.
	 * 
	 * @param name The name of the Person.
	 * @param rarity	The rarity of the Person.
	 * @param value The value of the Person.
	 */
	public Person(String name, String rarity, int value) {
		this.name = name;
		this.rarity = rarity;
		this.value = value;
	}
	
	/**
	 * Returns the name of the Person.
	 * 
	 * @return The name of the Person.
	 */
	public String getName() {
		return this.name;
	}
	
	/**
	 * Sets the name of the person.
	 * 
	 * @param name The name being given to the Person.
	 */
	public void setName(String name) {
		this.name = name;
	}
	
	/**
	 * Returns the rarity of the Person.
	 * 
	 * @return the rarity of the Person.
	 */
	public String getRarity() {
		return this.rarity;
	}
	
	/**
	 * Sets the rarity of the person.
	 * 
	 * @param rarity The rarity given to the Person.
	 */
	public void setRarity(String rarity) {
		this.rarity = rarity;
	}
	
	/**
	 * Returns the name of the Person.
	 * 
	 * @return the name of the Person.
	 */
	public int getValue() {
		return this.value;
	}
	
	/**
	 * Sets the value of the Person in dollars.
	 * 
	 * @param value The value of the person in dollars.
	 */
	public void setValue(int value) {
		this.value = value;
	}
}
