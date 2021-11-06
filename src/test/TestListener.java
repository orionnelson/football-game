package test;


import org.junit.jupiter.api.Test;

import controller.GameListener;
import controller.MenubarListener;
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
	public void setupGame() {
		gameFrame.add(gamePanel);
		gameFrame.addKeyListener(gl);
		gameFrame.setJMenuBar(gameMenuBar);
		gameFrame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		gameFrame.setSize(600, 600);
		gameFrame.setLocationRelativeTo(null);
		gameFrame.setResizable(false);
		gameFrame.setVisible(true);
		 Point sp = new Point(265,305);
		 sg.getActivePlayer().setPlayerPosition(sp);
	}
	
	@Test
	@DisplayName("Test Player Moves Up")
	public void testMovingUP() {
		 setupGame();
		 Point up = genEvent(KeyEvent.VK_UP);
		assertTrue(up.y < 0);
		
	}
	
	@Test
	@DisplayName("Test Player Moves Down")
	public void testMovingDown() {
		setupGame();
		 Point down = genEvent(KeyEvent.VK_DOWN);
		assertTrue(down.y > 0);
		
	}
	
	@Test
	@DisplayName("Test Player Moves Right")
	public void testMovingRight() {
		setupGame();
		 System.out.println(sg.getActivePlayer().getPlayerPosition());
		 Point right = genEvent(KeyEvent.VK_RIGHT);
		 System.out.println(right.x);
		 System.out.println(sg.getActivePlayer().getPlayerPosition());
		assertTrue(right.x > 0);
		
	}
	
	@Test
	@DisplayName("Test Player Moves Left")
	public void testMovingLeft() {
		 setupGame();
		 System.out.println(sg.getActivePlayer().getPlayerPosition());
		 Point left = genEvent(KeyEvent.VK_LEFT);
		 System.out.println(left.x);
		 System.out.println(sg.getActivePlayer().getPlayerPosition());
		 assertTrue(left.x < 0);
		
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
