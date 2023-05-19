package test;

import main.body.*;

import static org.junit.Assert.assertThrows;
import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;

class PlayerTests {
	static Player player;

	@BeforeAll
	static void setUpBeforeClass() throws Exception {
		AvailablePositions position = AvailablePositions.getRandomPosition();
		PlayerTests.player = Player.createRandomPlayer("Bronze", position);
	}

	@Test
	void testCreateRandomPlayer() {
		assertTrue(player.getClass() == Player.class);
		assertTrue(Player.createRandomPlayer("Silver", AvailablePositions.getRandomPosition()).getClass() == Player.class);
		assertTrue(Player.createRandomPlayer("Gold", AvailablePositions.getRandomPosition()).getClass() == Player.class);
		assertTrue(Player.createRandomPlayer("Platinum", AvailablePositions.getRandomPosition()).getClass() == Player.class);
	}
	
	@Test
	void testGetNickname() {
		assertTrue(player.getNickname().getClass() == String.class);
	}
	
	@Test
	void testSetNickname() {
		String nickname = "Slam";
		player.setNickname(nickname);
		assertEquals(nickname, player.getNickname());
	}
	
	@Test
	void testToString() {
		assertTrue(player.toString().getClass() == String.class);
		player.setName(null);
		assertTrue(player.toString() == "Empty");
	}
	
	@Test
	void testGetStats() {
		assertTrue(player.getStats().getClass() == int[].class);
	}
	
	@Test
	void testSetStats() {
		int[] newStats = {100, 100, 100};
		int[] badStats = {100};
		assertThrows(IllegalArgumentException.class, () -> player.setStats(badStats));
		player.setStats(newStats);
		assertEquals(player.getStats(), newStats);
	}
	
	@Test
	void testGetPosition() {
		assertTrue(player.getPosition().getClass() == AvailablePositions.class);
	}
	
	@Test
	void testSetPosition() {
		AvailablePositions position = AvailablePositions.getRandomPosition();
		player.setPosition(position);
		assertTrue(player.getPosition() == position);
	}
	
	@Test
	void testSetStamina() {
		int stamina = -1;
		player.setStamina(stamina);
		assertTrue(player.getStamina() == 0);
		assertTrue(player.isInjured() == true);
		stamina = 10;
		player.setStamina(stamina);
		assertTrue(player.getStamina() == stamina);
		assertTrue(player.isInjured() == false);
		stamina = 110;
		player.setStamina(stamina);
		assertTrue(player.getStamina() == 100);
		assertTrue(player.isInjured() == false);
	}
	
	@Test
	void testIncStamina() {
		player.setStamina(50);
		player.incStamina(10);
		assertTrue(player.getStamina() == 60);
	}
	
	@Test
	void testDecStamima() {
		player.setStamina(50);
		player.decStamina(10);
		assertTrue(player.getStamina() == 40);
	}
	
	@Test
	void testGetStamina() {
		int stamina = 10;
		player.setStamina(stamina);
		assertTrue(player.getStamina() == 10);
	}
	
	@Test
	void testSetInjured() {
		player.setInjured(true);
		assertTrue(player.isInjured() == true);
		player.setInjured(false);
		assertTrue(player.isInjured() == false);
	}
	
	@Test
	void testIsInjured()
	{
		player.setInjured(true);
		assertTrue(player.isInjured() == true);
		player.setInjured(false);
		assertTrue(player.isInjured() == false);
	}
	
	@Test
	void testGetName() {
		String newName = "Lez";
		player.setName(newName);
		assertTrue(player.getName() == newName);
	}
	
	@Test
	void testGetRarity() {
		assertTrue(player.getRarity() == "Bronze");
	}
	
	@Test
	void testSetValue() {
		int newValue = 100;
		player.setValue(newValue);
		assertTrue(player.getValue() == newValue);
	}
	
	
	

}
