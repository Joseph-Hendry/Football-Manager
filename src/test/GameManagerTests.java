package test;

import static org.junit.jupiter.api.Assertions.*;

import java.io.ByteArrayInputStream;
import java.io.InputStream;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.Test;

import main.body.GameManager;
import main.UI.GameManagerUI;
import main.UI.CLI.CmdLineUI;

class GameManagerTests {
	private final InputStream originalSystemIn = System.in;
    private ByteArrayInputStream testIn;

    void setUpInput(String text) {
    	try {
	        testIn = new ByteArrayInputStream(text.getBytes());
	        System.setIn(testIn);
    	} catch (Exception e) {
    		return;
    	}
	        
    }
    
    @AfterEach
    void restoreSystemInput() {
        System.setIn(originalSystemIn);
    }


//    @Test
//    void testMenus() {
//    	setUpInput("Team\n1\n10\n0\n");
//        GameManagerUI ui = new CmdLineUI();
//        GameManager manager = new GameManager(ui);
//
//        manager.start();
//        restoreSystemInput();
//        assertTrue(manager.getDifficulty() == 1);
//        assertTrue(manager.getSeasonLength() == 10);
//    }
    
    @Test
    void testGetDifficulty() {
    	
    }
}
