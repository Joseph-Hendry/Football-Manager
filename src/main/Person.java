package main;

/**
 * This class implements a Person that stores a name, rarity and value.
 * The Person class is used in the Player and Coach classes.
 */
public class Person {
	private String name;
	private String rarity;
	private int value;
	
	public Person(String name, String rarity, int value) {
		/**
		 * Constructor for the Person object.
		 * 
		 * @param theName	The name of the Person.
		 * @param theRarity	The rarity of the Person.
		 * @param theValue	The value of the Person.
		 */
		this.name = name;
		this.rarity = rarity;
		this.value = value;
		
	}
	
	public String getName() {
		/**
		 * Returns the name of the Person.
		 * 
		 * @return The name of the Person.
		 */
		return this.name;
	}
	
	public void setName(String name) {
		/**
		 * Sets the name of the person.
		 * 
		 * @param name The name being given to the Person.
		 */
		this.name = name;
	}
	
	public String getRarity() {
		/**
		 * Returns the rarity of the Person.
		 * 
		 * @return the rarity of the Person.
		 */
		return this.rarity;
	}
	
	public void setRarity(String rarity) {
		/**
		 * Sets the rarity of the person.
		 * 
		 * @param rarity The rarity given to the Person.
		 */
		this.rarity = rarity;
	}
	
	public int getValue() {
		/**
		 * Returns the name of the Person.
		 * 
		 * @return the name of the Person.
		 */
		return this.value;
	}
	
	public void setValue(int value) {
		/**
		 * Sets the value of the Person in dollars.
		 * 
		 * @param value The value of the person in dollars.
		 */
		this.value = value;
	}
}
