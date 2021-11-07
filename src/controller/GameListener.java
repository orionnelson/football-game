package controller;

import model.SoccerGame;
import view.GamePanel;

import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
/**
 * This class extends KeyListener and listens for input
 * from the player.
 */
public class GameListener implements KeyListener {

	/**
	 * Game panel instance
	 */
	private final GamePanel gamePanel;

	/**
	 * This is the constructor for GameListener
	 * @param panel represents the GamePanel
	 */
	public GameListener(GamePanel panel) {
		gamePanel = panel;
	}

	
	/**
	 * Unneeded overridden method from KeyListener 
	 * left empty
	 */
	@Override
	public void keyTyped(KeyEvent e) {

	}

	
	/**
	 * This method listens for keyboard input from user 
	 * to move striker and shoot.
	 * @param e represents input from user
	 */
	@Override
	public void keyPressed(KeyEvent e) {
		SoccerGame soccerGame = gamePanel.getGame();
		if (!soccerGame.isPaused() && !soccerGame.isOver()) {
			switch (e.getKeyCode()) {
				case KeyEvent.VK_LEFT:
					soccerGame.getActivePlayer().moveLeft();
					break;
				case KeyEvent.VK_RIGHT:
					soccerGame.getActivePlayer().moveRight();
					break;
				case KeyEvent.VK_UP:
					soccerGame.getActivePlayer().moveUp();
					break;
				case KeyEvent.VK_DOWN:
					soccerGame.getActivePlayer().moveDown();
					break;
				case KeyEvent.VK_SPACE:
					if (soccerGame.getActivePlayer().isPlayerHasBall()) {
						soccerGame.getActivePlayer().shootBall();
					}
					break;
			}
		}
	}

	
	/**
	 * Unneeded overridden method from KeyListener 
	 * left empty
	 */
	@Override
	public void keyReleased(KeyEvent e) {

	}
}
