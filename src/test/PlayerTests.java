package test;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.Test;

import main.body.Player;
import main.body.AvailablePositions;

class PlayerTests {

	@Test
	void constructRandomPlayer() {
		
		Player player = Player.createRandomPlayer("Bronze", position);
	}

}
