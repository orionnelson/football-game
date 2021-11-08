package test;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import controller.GameListener;
import controller.MenubarListener;
import model.SoccerBall;
import model.SoccerGame;
import view.GameMenuBar;
import view.GamePanel;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

import java.awt.AWTException;
import java.awt.Point;
import java.awt.Rectangle;
import java.awt.Robot;
import java.awt.event.KeyEvent;
import java.awt.event.InputEvent;

import javax.swing.JFrame;
import javax.swing.border.Border;

public class TestMenuListener{
	
	
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
	
	
	public void genRobotClicker(int x, int y) {
			Robot r;
			try {
				 r = new Robot();
				 r.mouseMove(x,y);
				 r.mousePress(InputEvent.BUTTON2_DOWN_MASK);
				 try{Thread.sleep(250);}catch(InterruptedException e){}
				 r.mouseRelease(InputEvent.BUTTON2_DOWN_MASK);
			} catch (AWTException e1) {
				// TODO Auto-generated catch block
				e1.printStackTrace();
			}
			
	}

	
	@Test
	@DisplayName("Make sure That Menubar Reset Works as Intended")
    public void testMenuReset() {
		setupGame(new Point(265,305));
		SoccerBall.getSoccerBall().setPosition(sg.getActivePlayer().getPlayerPosition());
		Point frame = gameFrame.getLocationOnScreen();
		genEvent(KeyEvent.VK_UP);
        genRobotClicker((int)frame.getX()+25,(int)frame.getY()+45);
        genRobotClicker((int)frame.getX()+25,(int)frame.getY()+55);
        genEvent(KeyEvent.VK_DOWN);
        
        genEvent(KeyEvent.VK_UP);
        genEvent(KeyEvent.VK_UP);
        genEvent(KeyEvent.VK_DOWN);
        genEvent(KeyEvent.VK_DOWN);
        assertTrue(SoccerBall.getSoccerBall().getPosition().getX()==265 && SoccerBall.getSoccerBall().getPosition().getY()==305);
    }

}
