package test;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;

import main.body.AvailablePositions;

class AvailablePositionsTest {

	@BeforeAll
	static void setUpBeforeClass() throws Exception {
	}

	@Test
	void testGetRandomPosition() {
		assertTrue(AvailablePositions.getRandomPosition().getClass() == AvailablePositions.class);
	}

}
