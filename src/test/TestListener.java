package test;


import org.junit.jupiter.api.Test;

import controller.GameListener;
import model.SoccerGame;
import view.GamePanel;

import static org.junit.jupiter.api.Assertions.*;


import java.awt.Point;
import java.awt.event.KeyEvent;


import org.junit.jupiter.api.DisplayName;

public class TestListener {
	
	// A new gamePanel is createted for testing
	private final GamePanel gamePanel = new GamePanel();
	SoccerGame sg = gamePanel.getGame();
	private GameListener gl = new GameListener(gamePanel);
	
	
	@Test
	@DisplayName("Test Player Moves Up")
	public void testMovingUP() {
		 KeyEvent up = genEvent(KeyEvent.VK_UP);
		 Point mv = MovementHelper(up);
		assertTrue(mv.y > 0);
		
	}
	
	@Test
	@DisplayName("Test Player Moves Down")
	public void testMovingDown() {
		 KeyEvent up = genEvent(KeyEvent.VK_DOWN);
		 Point mv = MovementHelper(up);
		assertTrue(mv.y < 0);
		
	}
	
	@Test
	@DisplayName("Test Player Moves Right")
	public void testMovingRight() {
		 KeyEvent up = genEvent(KeyEvent.VK_RIGHT);
		 Point mv = MovementHelper(up);
		assertTrue(mv.x > 0);
		
	}
	
	@Test
	@DisplayName("Test Player Moves Left")
	public void testMovingLeft() {
		 KeyEvent up = genEvent(KeyEvent.VK_LEFT);
		 Point mv = MovementHelper(up);
		assertTrue(mv.x < 0);
		
	}
	
	
	
	public KeyEvent genEvent(int called) {
		KeyEvent key = new KeyEvent(gamePanel, KeyEvent.KEY_PRESSED, System.currentTimeMillis(), 0,  called,'Z');
	    gamePanel.getKeyListeners().keyPressed(key);
		return key;
	}
	
	
	public Point MovementHelper(KeyEvent e) {
		// Start Game to Simulate Movement 
		
		Point pos1  =  sg.getActivePlayer().getPlayerPosition();
		System.out.println(pos1);
		//Calls Keypress Method we now check if given event e the player moves accordingly
		gl.keyPressed(e);
	    Point pos2 = new Point();
		if (!sg.isPaused() && !sg.isOver()) {
			switch (e.getKeyCode()) {
				case KeyEvent.VK_LEFT:
					pos2 = sg.getActivePlayer().getPlayerPosition();
					break;
				case KeyEvent.VK_RIGHT:
					pos2 = sg.getActivePlayer().getPlayerPosition();
					break;
				case KeyEvent.VK_UP:
					pos2 = sg.getActivePlayer().getPlayerPosition();
					break;
				case KeyEvent.VK_DOWN:
					pos2 = sg.getActivePlayer().getPlayerPosition();
					break;
				
	}}
		int x  = (int) (pos1.getX() - pos2.getX());
		int y = (int) (pos1.getY()-pos2.getY());
	return new Point(x,y);
}


}
