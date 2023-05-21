package test;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;

import main.body.GameManager;
import main.body.Match;
import main.body.Team;

import java.util.ArrayList; 

class MatchTests {
	static Match match;
	static GameManager manager;

	@BeforeAll
	static void setUpBeforeClass() throws Exception {
		match = new Match(Team.createRandomTeam(50, "Team1"), Team.createRandomTeam(50, "Team2"), 5, 1000);
		manager = new GameManager(null);
	}

	@Test
	void testConstructor() {
		assertTrue(match.getClass() == Match.class);
	}
	
	@Test
	void testGetScore() {
		assertTrue(match.getScore().getClass() == int[].class);
	}
	
	@Test
	void testGetPoints() {
		assertTrue(match.getPoints() == 5);
	}
	
	@Test
	void testGetMoney() {
		assertTrue(match.getMoney() == 1000);
	}
	
	@Test
	void testGetOpposingTeam() {
		assertTrue(match.getOpposingTeam().getClass() == Team.class);
	}
	
	@Test
	void testGetNPCTeamStats() {
		assertTrue(match.getNPCTeamStats().getClass() == int[].class);
	}
	
	@Test
	void testGetPointsToWin() {
		assertTrue(match.getPointsToWin() == 5);
	}
	
	@Test
	void testGetMoneyToWin() {
		assertTrue(match.getMoneyToWin() == 1000);
	}
	
	
	@Test
	void testToString() {
		assertTrue(match.toString().getClass() == String.class);
	}
	
	@Test
	void testPlayMatch() {
		match.playMatch(manager);
		for (String commentary : match.getCommentaryList()) {
			assertTrue(commentary.getClass() == String.class);
		}
	}
	
	@Test
	void testGetCommentaryList() {
		for (String commentary : match.getCommentaryList()) {
			assertTrue(commentary.getClass() == String.class);
		}
	}
}
