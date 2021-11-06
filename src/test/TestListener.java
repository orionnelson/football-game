package test;


import org.junit.jupiter.api.Test;

import controller.GameListener;
import model.SoccerGame;
import view.GamePanel;
import java.awt.Robot;

import static org.junit.jupiter.api.Assertions.*;

import java.awt.AWTException;
import java.awt.Point;
import java.awt.event.KeyEvent;


import org.junit.jupiter.api.DisplayName;

public class TestListener {
	
	// A new gamePanel is createted for testing
	JFrame gameFrame = new JFrame("Mini Soccer");
	GamePanel gamePanel = new GamePanel();
	GameListener gl = new GameListener(gamePanel);
	MenubarListener menubarListener = new MenubarListener(gamePanel);
	GameMenuBar gameMenuBar = new GameMenuBar(menubarListener);
	gameFrame.add(gamePanel);
	gameFrame.addKeyListener(gl);
	gameFrame.setJMenuBar(gameMenuBar);
	gameFrame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
	gameFrame.setSize(600, 600);
	gameFrame.setLocationRelativeTo(null);
	gameFrame.setResizable(false);
	gameFrame.setVisible(true);
	SoccerGame sg = gamePanel.getGame();
	private GameListener gl = new GameListener(gamePanel);
	
	
	@Test
	@DisplayName("Test Player Moves Up")
	public void testMovingUP() {
		 Point up = genEvent(KeyEvent.VK_UP);
		assertTrue(up.y > 0);
		
	}
	
	@Test
	@DisplayName("Test Player Moves Down")
	public void testMovingDown() {
		 Point down = genEvent(KeyEvent.VK_DOWN);
		assertTrue(down.y < 0);
		
	}
	
	@Test
	@DisplayName("Test Player Moves Right")
	public void testMovingRight() {
		 Point right = genEvent(KeyEvent.VK_RIGHT);
		assertTrue(right.x > 0);
		
	}
	
	@Test
	@DisplayName("Test Player Moves Left")
	public void testMovingLeft() {
		 Point left = genEvent(KeyEvent.VK_LEFT);
		assertTrue(left.x < 0);
		
	}
	
	
	
	public Point genEvent(int called) {
		Point pos1  =  sg.getActivePlayer().getPlayerPosition();
		try {
			Robot r = new Robot();
			r.keyPress(called);
			r.keyRelease(called);
			
		} catch (AWTException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		Point pos2 = new Point();
		if (!sg.isPaused() && !sg.isOver()) {
		
					pos2 = sg.getActivePlayer().getPlayerPosition();
				
	}
		int x  = (int) (pos1.getX() - pos2.getX());
		int y = (int) (pos1.getY()-pos2.getY());
	return new Point(x,y);
	}
	
}
