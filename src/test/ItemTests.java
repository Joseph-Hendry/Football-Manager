package test;

import static org.junit.jupiter.api.Assertions.*;

import java.util.ArrayList;
import java.util.Arrays;

import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;

import main.body.Item;

class ItemTests {
	static Item item;

	@BeforeAll
	static void setUpBeforeClass() throws Exception {
		int[] itemStats = {99, 99, 99};
		item = new Item("newItem", 999, itemStats, "Platinum");
		
	}

	@Test
	void testConstructor() {
		assertTrue(item.getClass() == Item.class);
	}
	
	@Test
	void testCreateRandomItem() {
		ArrayList<Item> items = new ArrayList<Item>();
		
		items.add(Item.createRandomItem("Bronze"));
		items.add(Item.createRandomItem("Silver"));
		items.add(Item.createRandomItem("Gold"));
		items.add(Item.createRandomItem("Platinum"));
		
		int counter = 1;
		for (Item itemTest : items) {
			assertTrue(itemTest.getValue() >= items.get(counter - 1).getValue());
			for (int i = 0; i < itemTest.getStats().length; i++) {
				assertTrue(itemTest.getStats()[i] >= items.get(counter - 1).getStats()[i]);
			}
		}
	}
	
	@Test
	void testToString() {
		assertTrue(item.toString().getClass() == String.class);
	}
	
	@Test
	void testGetName() {
		assertEquals(item.getName(), "newItem");
	}
	
	@Test
	void testGetValue() {
		assertEquals(item.getValue(), 999);
	}
	
	@Test
	void testGetStats() {
		int[] itemStats = {99, 99, 99};
		assertTrue(Arrays.equals(item.getStats(), itemStats));
	}
	
	@Test
	void testGetRarity() {
		assertTrue(item.getRarity() == "Platinum");
	}
}
