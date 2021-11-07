package test;


import org.junit.jupiter.api.Test;

import controller.GameListener;
import controller.MenubarListener;
import model.SoccerBall;
import model.SoccerGame;
import view.GameMenuBar;
import view.GamePanel;
import java.awt.Robot;

import static org.junit.jupiter.api.Assertions.*;

import java.awt.AWTException;
import java.awt.Point;
import java.awt.event.KeyEvent;

import javax.swing.JFrame;

import org.junit.jupiter.api.DisplayName;

public class TestListener {
	
	// A new gamePanel is createted for testing
	
	
    public static JFrame gameFrame = new JFrame("Mini Soccer");
	public static GamePanel gamePanel = new GamePanel();
	public static GameListener gl = new GameListener(gamePanel);
	public static MenubarListener menubarListener = new MenubarListener(gamePanel);
	public static GameMenuBar gameMenuBar = new GameMenuBar(menubarListener);
	SoccerGame sg = gamePanel.getGame();
	public void setupGame(Point sp) {
		gameFrame.add(gamePanel);
		gameFrame.addKeyListener(gl);
		gameFrame.setJMenuBar(gameMenuBar);
		gameFrame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		gameFrame.setSize(600, 600);
		gameFrame.setLocationRelativeTo(null);
		gameFrame.setResizable(false);
		gameFrame.setVisible(true);
		sg.getActivePlayer().setPlayerPosition(sp);
		genEvent(KeyEvent.VK_R);
		 
	}
	
	@Test
	@DisplayName("Test Player Moves Up")
	public void testMovingUP() {
		setupGame(new Point(265,305));
		genEvent(KeyEvent.VK_UP);
		Point up = genEvent(KeyEvent.VK_UP);
		assertTrue(up.y < 0);
		
	}
	
	@Test
	@DisplayName("Test Player Moves Down")
	public void testMovingDown() {
		setupGame(new Point(265,305));
		genEvent(KeyEvent.VK_DOWN);
		 Point down = genEvent(KeyEvent.VK_DOWN);
		assertTrue(down.y > 0);
		
	}
	
	@Test
	@DisplayName("Test Player Moves Right")
	public void testMovingRight() {
		setupGame(new Point(265,305));
		genEvent(KeyEvent.VK_RIGHT);
		Point right = genEvent(KeyEvent.VK_RIGHT);
		assertTrue(right.x > 0);
		
	}
	
	@Test
	@DisplayName("Test Player Moves Left")
	public void testMovingLeft() {
		 setupGame(new Point(265,305));
		 genEvent(KeyEvent.VK_LEFT);
		 Point left = genEvent(KeyEvent.VK_LEFT);
		 assertTrue(left.x < 0);
		
	}
	
	
	@Test
	@DisplayName("Player Able to Pickup SoccerBall")
	public void testPlayerPickupBall() {
		//Sets up the game
		setupGame(new Point(265,305));
		//Teleports the ball to the player 
		SoccerBall.getSoccerBall().setPosition(sg.getActivePlayer().getPlayerPosition());
		assertTrue(sg.getActivePlayer().isPlayerHasBall());
	}
	
	
	@Test
	@DisplayName("Player able to shoot SoccerBall")
	public void testPlayerKick() {
		setupGame(new Point(450,305));
		//Teleports the ball to the player 
		SoccerBall.getSoccerBall().setPosition(sg.getActivePlayer().getPlayerPosition());
		genEvent(KeyEvent.VK_UP);
		genEvent(KeyEvent.VK_SPACE);
		assertFalse(sg.getActivePlayer().isPlayerHasBall());
	}
	
	@Test 
	@DisplayName("Shot Out of bounds does not Count")
	public void shotMissNet() {
		setupGame(new Point(450,305));
		int cgl = sg.getActivePlayer().getPlayerStatistics();
		genEvent(KeyEvent.VK_RIGHT);
		genEvent(KeyEvent.VK_RIGHT);  
		SoccerBall.getSoccerBall().setPosition(sg.getActivePlayer().getPlayerPosition());
		genEvent(KeyEvent.VK_SPACE);
		assertEquals(sg.getActivePlayer().getPlayerStatistics()-cgl,0);
		
	}
	
	@Test 
	@DisplayName("Teleporting the ball into the goal counts as a goal")
	public void runGoal() {
		setupGame(new Point(450,305));
		int cgl = sg.getActivePlayer().getPlayerStatistics();
		SoccerBall.getSoccerBall().setPosition(new Point(181,11));
		genEvent(KeyEvent.VK_R);
		
		assertEquals(sg.getActivePlayer().getPlayerStatistics()-cgl,1);
		
	}
	
	@Test
	@DisplayName("The Player Cannot run into the net")
	public void runPlayerStupid() {
		setupGame(new Point(265,200));
		int cgl = sg.getActivePlayer().getPlayerStatistics();
		SoccerBall.getSoccerBall().setPosition(sg.getActivePlayer().getPlayerPosition());
		genEvent(KeyEvent.VK_UP);
		genEvent(KeyEvent.VK_UP);
		genEvent(KeyEvent.VK_UP);
		genEvent(KeyEvent.VK_UP);
		genEvent(KeyEvent.VK_UP);
		assertEquals(sg.getActivePlayer().getPlayerStatistics()-cgl,0);
	}
	
	
	@Test 
	@DisplayName("Test Ball Stops On Game Pause")
	public void runShootPause() {
		setupGame(new Point(265,305));
		//Teleports the ball to the player 
		SoccerBall.getSoccerBall().setPosition(sg.getActivePlayer().getPlayerPosition());
		genEvent(KeyEvent.VK_SPACE);
		genEvent(KeyEvent.VK_P);
		assertEquals(SoccerBall.getSoccerBall().getVelocity(),0.0);
		
	}
	
	
	
	
	public Point genEvent(int called) {
		Point pos1  =  sg.getActivePlayer().getPlayerPosition();
		try {
			Robot r = new Robot();
			 r.setAutoWaitForIdle(true);
		     r.delay(1500);
		     r.waitForIdle();
			r.keyPress(called);r.keyRelease(called);
			r.keyPress(called);r.keyRelease(called);
			
		} catch (AWTException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		Point pos2 = new Point();
		if (!sg.isPaused() && !sg.isOver()) {
		
					pos2 = sg.getActivePlayer().getPlayerPosition();
				
	}
		int x  = ( (int)pos2.getX() - (int)pos1.getX());
		int y = (int) (pos2.getY()-pos1.getY());
	return new Point(x,y);
	}
	
}
