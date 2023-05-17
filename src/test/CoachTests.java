package test;

import static org.junit.jupiter.api.Assertions.*;

import java.util.ArrayList;
import java.util.Arrays;

import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;

import main.body.Coach;

class CoachTests {
	static Coach coach;
	
	@BeforeAll
	static void setUpBeforeClass() throws Exception {
		int[] stats = {75, 75, 75};
		coach = new Coach("Slam", "Platinum", 888, stats);
	}

	@Test
	void testConstructor() {
		assertTrue(coach.getClass() == Coach.class);
	}
	
	@Test
	void testCreateRandomCoach() {
		ArrayList<Coach> coaches = new ArrayList<Coach>();
		
		coaches.add(Coach.createRandomCoach("Bronze"));
		coaches.add(Coach.createRandomCoach("Silver"));
		coaches.add(Coach.createRandomCoach("Gold"));
		coaches.add(Coach.createRandomCoach("Platinum"));
		
		int counter = 1;
		for (Coach coachtest : coaches) {
			assertTrue(coachtest.getValue() >= coaches.get(counter - 1).getValue());
			for (int i = 0; i < coachtest.getStats().length; i++) {
				assertTrue(coachtest.getStats()[i] >= coaches.get(counter - 1).getStats()[i]);
			}
		}
	}
	
	@Test
	void testToString() {
		assertTrue(coach.toString().getClass() == String.class);
	}
	
	@Test
	void testGetStats() {
		int[] stats = {75, 75, 75};
		assertTrue(Arrays.equals(coach.getStats(), stats));
	}

}
