package test;

import main.body.*;

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
	}
	
	@Test
	void testGetStats() {
		assertTrue(player.getStats().getClass() == int[].class);
	}
	
	@Test
	void testSetStats() {
		int[] oldStats = player.getStats();
		int[] newStats = {100, 100, 100};
		int[] badStats = {100};
		player.setStats(badStats);
		assertEquals(player.getStats(), oldStats);
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
		assertTrue(player.getStamina() == stamina);
		assertTrue(player.isInjured() == true);
		stamina = 10;
		player.setStamina(stamina);
		assertTrue(player.getStamina() == stamina);
		assertTrue(player.isInjured() == true);
	}
	
	
	
	

}
