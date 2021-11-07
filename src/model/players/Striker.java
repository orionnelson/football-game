package model.players;

import model.SoccerBall;

import java.awt.*;

/**
 * This class represents the Striker which is a type of GamePlayer.
 */
public class Striker extends GamePlayer {
	
	/**
	 * Constructor to initialize a Striker
	 * @param name is the name of Striker.
	 * @param color is the color of Striker.
	 */
	public Striker(String name, Color color) {
		super(name, color);
	}

	/**
	 * Method to move Striker to left.
	 */
	@Override
	public void moveLeft() {
		if (getPlayerPosition().x - 10 > 0) {
			setPlayerPosition(new Point(getPlayerPosition().x - 5, getPlayerPosition().y));
		}
	}

	/**
	 * Method to move Striker to right.
	 */
	@Override
	public void moveRight() {
		if (getPlayerPosition().x + 50 < 600) {
			setPlayerPosition(new Point(getPlayerPosition().x + 5, getPlayerPosition().y));
		}
	}

	/**
	 * Method to move Striker up.
	 */
	@Override
	public void moveUp() {
		if (getPlayerPosition().y - 5 > 200) {
			setPlayerPosition(new Point(getPlayerPosition().x, getPlayerPosition().y - 5));
		}
	}

	/**
	 * Method to move Striker down.
	 */
	@Override
	public void moveDown() {
		if (getPlayerPosition().y + 50 < 500) {
			setPlayerPosition(new Point(getPlayerPosition().x, getPlayerPosition().y + 5));
		}
	}

	/**
	 * Method to shoot the ball.
	 */
	@Override
	public void shootBall() {
		SoccerBall.getSoccerBall().moveBall(60, 5.0, 0.05);
	}

	/**
	 * Method to set the initial position of Striker.
	 */
	@Override
	public void setInitialPosition() {
		setPlayerPosition(new Point(500, 450));
	}

	/**
	 * Get string of Striker statistics.
	 * @return String of Striker statistics.
	 */
	@Override
	public String toString() {
		return playerName + " scored " + playerStatistics.toString() + " goals";
	}
}
