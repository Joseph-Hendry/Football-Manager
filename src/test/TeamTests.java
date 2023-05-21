package test;

import static org.junit.jupiter.api.Assertions.*;

import java.util.ArrayList;

import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;

import main.body.AvailablePositions;
import main.body.Coach;
import main.body.Item;
import main.body.Player;
import main.body.Team;

class TeamTests {
	static Team team;

	@BeforeAll
	static void setUpBeforeClass() throws Exception {
		team = Team.createRandomTeam(50, "NewTeam");
		
	}

	@Test
	void testGetTeamList() {
		Team.createNPCTeams(50);
		ArrayList<Team> teamList = Team.getTeamList();
		for (Team team : teamList) {
			assertTrue(team.getClass() == Team.class);
		}
	}
	
	@Test
	void testSetName() {
		String newName = "NewerTeam";
		String oldName = team.getName();
		team.setName(newName);
		assertTrue(team.getName() != oldName);
		assertTrue(team.getName() == newName);
	}
	
	@Test
	void testSetTeamBench() {
		String testName = "Testing";
		team.getTeam().get(0).setName(testName);
		Team newTeam = Team.createRandomTeam(50, "Test");
		team.setTeam(newTeam.getTeam());
		team.setBench(newTeam.getBench());
		assertTrue(!team.getTeam().get(0).getName().equals(testName));
		assertTrue(team.getTeam() == newTeam.getTeam());
		assertTrue(team.getBench() == newTeam.getBench());
	}
	
	@Test
	void testSetCoach() {
		Coach newCoach = Coach.createRandomCoach("Gold");
		team.setCoach(newCoach);
		assertTrue(team.getCoach() == newCoach);
	}
	
	@Test
	void testGetSetPoints() {
		int newPoints = 20;
		team.setPoints(newPoints);
		assertTrue(team.getPoints() == newPoints);
	}
	
	@Test
	void testGetStrRarity() {
		for (int i = -1; i < 101; i++) {
			int test = i;
			if (i < 0 || i > 100) {
				assertThrows(IllegalArgumentException.class, () -> Team.getStrRarity(test));
			} else if (i <= 50) {
				assertTrue(Team.getStrRarity(test) == "Bronze");
			} else if (i <= 80) {
				assertTrue(Team.getStrRarity(test) == "Silver");
			} else if (i <= 95) {
				assertTrue(Team.getStrRarity(test) == "Gold");
			} else {
				assertTrue(Team.getStrRarity(test) == "Platinum");
			}
		}
	}
	
	@Test
	void testSubPlayerSwap() {
		ArrayList<Player> onTeam = team.getTeam();
		ArrayList<Player> onBench = team.getBench();
		team.subPlayerSwap(onTeam.get(0), onBench.get(0));
		assertThrows(IllegalArgumentException.class, () -> team.subPlayerSwap(Player.createRandomPlayer("Gold", AvailablePositions.DEFENCE), onBench.get(0)));
	}
	
	@Test
	void testRemovePlayer() {
		Player testPlayer = team.getTeam().get(0);
		team.removePlayer(testPlayer);
		team.removePlayer(team.getBench().get(0));
		assertThrows(IllegalArgumentException.class, () -> team.removePlayer(Player.createRandomPlayer("Bronze", AvailablePositions.DEFENCE)));
	}
	
	@Test
	void testAddItem() {
		team.addItem(Item.createRandomItem("Gold"));
		team.addItem(Item.createRandomItem("Gold"));
		team.addItem(Item.createRandomItem("Gold"));
		assertThrows(IllegalArgumentException.class, () -> team.addItem(Item.createRandomItem("Gold")));
	}
	
	@Test
	void testRemoveItem() {
		team.removeItem(team.getItems().get(0));
		assertThrows(IllegalArgumentException.class, () -> team.removeItem(Item.createRandomItem("Gold")));
	}
	
	@Test
	void testAddPlayerToTeam() {
		team.addPlayerToTeam(Player.createRandomPlayer("Gold", AvailablePositions.MIDFIELD));
		assertThrows(IllegalArgumentException.class, () -> team.addPlayerToTeam(Player.createRandomPlayer("Gold", AvailablePositions.MIDFIELD)));
	}
	
	@Test
	void testAddPlayerToBench() {
		team.addPlayerToBench(Player.createRandomPlayer("Gold", AvailablePositions.MIDFIELD));
		assertThrows(IllegalArgumentException.class, () -> team.addPlayerToBench(Player.createRandomPlayer("Gold", AvailablePositions.MIDFIELD)));
	}
	
	@Test
	void testSortTeamList() {
		Team.sortTeamList();
		for (int i = 1; i < Team.getTeamList().size(); i ++) {
			assertTrue(Team.getTeamList().get(i - 1).getPoints() >= Team.getTeamList().get(i).getPoints());
		}
	}
}
