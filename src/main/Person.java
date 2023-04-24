package main;

/**
 * This class implements a Person that stores a name, rarity and value.
 * The Person class is used in the Player and Coach classes.
 */
public class Person {
	private String name;
	private String rarity;
	private int value;
	
	public String getName() {
		/**
		 * Returns the name of the Person.
		 * 
		 * @return The name of the Person.
		 */
		return name;
	}
	
	public void setName(String theName) {
		/**
		 * Sets the name of the person.
		 * 
		 * @param theName the name being given to the Person.
		 */
		name = theName;
	}
	
	public String getRarity() {
		/**
		 * Returns the rarity of the Person.
		 * 
		 * @return the rarity of the Person.
		 */
		return rarity;
	}
	
	public void setRarity(String theRarity) {
		/**
		 * Sets the rarity of the person.
		 * 
		 * @param theRarity the rarity given to the Person.
		 */
		rarity = theRarity;
	}
	
	public int getValue() {
		/**
		 * Returns the name of the Person.
		 * 
		 * @return the name of the Person.
		 */
		return value;
	}
	
	public void setValue(int theValue) {
		/**
		 * Sets the value of the Person in dollars.
		 * 
		 * @param theValue the value of the person in dollars.
		 */
		value = theValue;
	}
}
