package test;

import static org.junit.jupiter.api.Assertions.*;

import java.util.ArrayList;

import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;

import main.body.AvailablePositions;
import main.body.Store;
import main.body.Coach;
import main.body.Item;
import main.body.Player;

class StoreTests {
	static Store store;
	static Store draftStore;

	@BeforeAll
	static void setUpBeforeClass() throws Exception {
		store = Store.createStore(1);
		draftStore = Store.createDraftStore(1);
	}

	@Test
	void testGetStorePlayers() {
		assertTrue(store.getStorePlayers().size() <= 11 && store.getStorePlayers().size() >= 0);
	}
	
	@Test
	void testGetStoreCoach() {
		assertTrue(store.getStoreCoach().getClass() == Coach.class);
	}
	
	@Test 
	void testGetStoreItems() {
		assertTrue(store.getStoreItems().size() == 5);
	}
	
	@Test
	void testGetSetCoachAvailable() {
		store.setCoachAvailable(true);
		assertTrue(store.getCoachAvailable() == true);
		store.setCoachAvailable(false);
		assertTrue(store.getCoachAvailable() == false);
	}
	
	@Test
	void testGetDraftCoaches() {
		ArrayList<Coach> coaches = draftStore.getDraftCoaches();
		for (Coach coach : coaches) {
			assertTrue(coach.getClass() == Coach.class);
		}
	}
	
	@Test
	void testRemovePlayer() {
		Player playerToRemove = store.getStorePlayers().get(0);
		int oldPlayerListSize = store.getStorePlayers().size();
		store.removePlayer(playerToRemove);
		assertTrue(store.getStorePlayers().size() == oldPlayerListSize - 1);
		assertThrows(IllegalArgumentException.class, () -> store.removePlayer(playerToRemove));
	}
	
	@Test
	void testRemoveItem() {
		Item itemToRemove = store.getStoreItems().get(0);
		int oldItemListSize = store.getStoreItems().size();
		store.removeItem(itemToRemove);
		assertTrue(store.getStoreItems().size() == oldItemListSize - 1);
		assertThrows(IllegalArgumentException.class, () -> store.removeItem(itemToRemove));
	}
	
	@Test
	void testRefreshStore() {
		store.setCoachAvailable(false);
		store.refreshStore(1);
		assertTrue(store.getStorePlayers().size() == AvailablePositions.values().length);
		assertTrue(store.getCoachAvailable() == true);
	}
	

}
