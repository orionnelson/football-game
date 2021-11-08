package test;
import org.junit.jupiter.api.Test;

import controller.GameListener;
import controller.MenubarListener;
import model.SoccerBall;
import model.SoccerGame;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

import java.awt.AWTException;
import java.awt.Point;
import java.awt.Robot;
import java.awt.event.KeyEvent;

import javax.swing.JFrame;

import org.junit.jupiter.api.DisplayName;
import test.TestListener;
import view.GameMenuBar;
import view.GamePanel;

/**
 *	Class to test models and in game events
 */
public class TestModels{
	
	
	/**
	 * Frame for testing Game
	 */
    public static JFrame gameFrame = new JFrame("Mini Soccer");
    
    /**
     * Game panel used for testing Game
     */
	public static GamePanel gamePanel = new GamePanel();
	
	/**
	 * Listens for input from player and used for testing
	 */
	public static GameListener gl = new GameListener(gamePanel);
	
	/**
	 * Listens for input from player via menu bar and used for testing
	 */
	public static MenubarListener menubarListener = new MenubarListener(gamePanel);
	
	/**
	 * Menu bar used to listen for game bar events
	 */
	public static GameMenuBar gameMenuBar = new GameMenuBar(menubarListener);
	
	/**
	 * Main game panel used to setup the game
	 */
	SoccerGame sg = gamePanel.getGame();
	
	/**
	 * Setup game using specified parameters and starts the game via Resume (R)
	 * @param sp Sets the start location of active player
	 */	
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
	
	/**
	 * @param called Uses keyboard events to simulate player movement
	 * @return returns a new point if the game is not paused and not over
	 */	
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
	
	
	/**
	 * Test if the game defender is active or null
	 */
	@Test 
	@DisplayName("Test Get Unknown Player is Null")
	public void getUnknownPlayer() {
		setupGame(new Point(265,305));
		//Teleports the ball to the player 
		assertEquals(sg.getGamePlayers().get("Defence"),null);
		
	}
	
	
	/**
	 * Test if the player is active
	 */
	@Test
	@DisplayName("Test If Get Striker is working")
	public void getStriker() {
		setupGame(new Point(265,305));
		//Teleports the ball to the player 
		assertNotEquals(sg.getGamePlayers().get("Striker"),null);
	}
	
	
	/**
	 * Test if Goal keeper is active
	 */
	@Test
	@DisplayName("Test If Get Goalie is working")
	public void getGoalie() {
		setupGame(new Point(265,305));
		//Teleports the ball to the player 
		assertNotEquals(sg.getGamePlayers().get("Goalkeeper"),null);
	}	
	
	/**
	 * Test if Goal keeper is not active
	 */
	@Test
	@DisplayName("Test If Get Goalie is working")
	public void testGoalieStats() {
		setupGame(new Point(265,305));
		//Teleports the ball to the player 
		assertTrue(sg.getGamePlayers().get("Goalkeeper").getPlayerStatistics()>=0);
	}
	
	/**
	 * Test if player is able to score
	 */
	@Test
	@DisplayName("Test If Get Striker is working")
	public void testStrikerStats() {
		setupGame(new Point(265,305));
		//Teleports the ball to the player 
		assertTrue(sg.getGamePlayers().get("Striker").getPlayerStatistics()>=0);
	}
		
	
	
}
