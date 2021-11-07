package controller;

import model.SoccerGame;
import view.GamePanel;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

/**
 * This class extends ActionListener and 
 * listens for menu selection by player.
 */
public class MenubarListener implements ActionListener {

	/**
	 * Game panel instance
	 */
	private final GamePanel gamePanel;

	/**
	 * This is the constructor for GameListener
	 * @param panel represents the GamePanel
	 */
	public MenubarListener(GamePanel panel) {
		gamePanel = panel;
	}


	/**
	 * Listens for menubar selection by user such as
	 * pause or resume under Control, and new game or exit under Game.
	 * @param e represents a menu selection by user
	 */
	@Override
	public void actionPerformed(ActionEvent e) {
		SoccerGame soccerGame = gamePanel.getGame();
		switch (e.getActionCommand()) {
			case "NEW":
				gamePanel.setupSoccerGame();
				break;
			case "EXIT":
				System.exit(0);
				break;
			case "PAUSE":
				if (!soccerGame.isPaused() && !soccerGame.isOver()) {
					soccerGame.setPaused(true);
				} else if (soccerGame.isPaused()) {
					System.out.println("game is already on pause!");
				} else if (soccerGame.isOver()) {
					System.out.println("game is over, please start a new game.");
				}
				break;
			case "RESUME":
				if (soccerGame.isPaused() && !soccerGame.isOver()) {
					soccerGame.setPaused(false);
				} else if (!soccerGame.isPaused()) {
					System.out.println("game is already running!");
				} else if (soccerGame.isOver()) {
					System.out.println("game is over, please start a new game.");
				}
				break;
			default:
				throw new RuntimeException("Invalid action command " + e.getActionCommand());
		}
	}
}
