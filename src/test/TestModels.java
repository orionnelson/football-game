

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

public class TestModels{
	
	
	
	
	
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
	
	
	
	
	
	@Test 
	@DisplayName("Test Get Unknown Player is Null")
	public void getUnknownPlayer() {
		setupGame(new Point(265,305));
		//Teleports the ball to the player 
		assertEquals(sg.getGamePlayers().get("Defence"),null);
		
	}
	
	
	@Test
	@DisplayName("Test Striker is not Null in GetGamePlayers")
	public void getStriker() {
		setupGame(new Point(265,305));
		//Teleports the ball to the player 
		assertNotEquals(sg.getGamePlayers().get("Striker"),null);
	}
	
	
	
		@Test
		@DisplayName("Test Goalie is not null in getGamePlayers")
		public void getGoalie() {
			setupGame(new Point(265,305));
			//Teleports the ball to the player 
			assertNotEquals(sg.getGamePlayers().get("Goalkeeper"),null);
		}	
		
		@Test
		@DisplayName("Test If Get Goalie is working")
		public void testGoalieStats() {
			setupGame(new Point(265,305));
			//Teleports the ball to the player 
			assertEquals(sg.getGamePlayers().get("Goalkeeper").getPlayerStatistics()>=0,true);
		}
		
		
		@Test
		@DisplayName("Test If Get Striker is working")
		public void testStrikerStats() {
			setupGame(new Point(265,305));
			//Teleports the ball to the player 
			assertEquals(sg.getGamePlayers().get("Striker").getPlayerStatistics()>=0,true);
		}
		
	
	        @Test
		@DisplayName("Test Entitys To String")
		public void testToString(){
			setupGame(new Point(265,305));
			assertTrue(sg.getGamePlayers().get("Striker").toString()!=null&&sg.getGamePlayers().get("Goalkeeper").toString()!=null);
		}
		
		@Test
		@DisplayName("Test Sorting")
		public void testSort() {
			sg.getGamePlayers().sort();
			assertTrue(true);
		}
	
	
}
