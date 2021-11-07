package model.players;

import model.SoccerBall;

import java.awt.*;
import java.util.Random;

/**
 * This class extends GamePlayer and represents 
 * the goalkeeper of the game.
 */
public class Goalkeeper extends GamePlayer {

	/**
	 * Represents the movement steps.
	 */
	private int movementStep;

	/**
	 * Constructor of class to initialize name and color
	 * and movement steps.
	 * @param name is the name of the Goalkeeper.
	 * @param color is the color of the Goalkeeper.
	 */
	public Goalkeeper(String name, Color color) {
		super(name, color);
		movementStep = 10;
	}

	
	/**
	 * Moves the Goalkeeper to the left.
	 */
	@Override
	public void moveLeft() {
		if (getPlayerPosition().x - 5 - movementStep > 0) {
			setPlayerPosition(new Point(getPlayerPosition().x - movementStep, getPlayerPosition().y));
		}
	}

	/**
	 * Moves the Goalkeeper to the right.
	 */
	@Override
	public void moveRight() {
		if (getPlayerPosition().x + 50 + movementStep < 600) {
			setPlayerPosition(new Point(getPlayerPosition().x + movementStep, getPlayerPosition().y));
		}
	}

	/**
	 * Moves the Goalkeeper up.
	 */
	@Override
	public void moveUp() {
		if (getPlayerPosition().y - 5 > 0) {
			setPlayerPosition(new Point(getPlayerPosition().x, getPlayerPosition().y - 5));
		}
	}

	/**
	 * Moves the Goalkeeper down.
	 */
	@Override
	public void moveDown() {
		if (getPlayerPosition().y + 50 < 300) {
			setPlayerPosition(new Point(getPlayerPosition().x, getPlayerPosition().y + 5));
		}
	}

	/**
	 * Shoots the ball.
	 */
	@Override
	public void shootBall() {
		SoccerBall.getSoccerBall().moveBall(-20, -5.0, -0.05);
	}

	/**
	 * Moves the Goalkeeper to a random position near goal.
	 */
	public void moveRandomly() {
		Random random = new Random();
		double SoccerBallPositionY = (double) SoccerBall.getSoccerBall().getPosition().y;
		double playerCurrentPositionX = (double) getPlayerPosition().x + 15;
		double chanceOfMovingUp = (SoccerBallPositionY-255)/ 30 -(random.nextGaussian());
		double chanceOfMovingLeft = (playerCurrentPositionX - 300) / 100 - (random.nextGaussian());
		movementStep = (int) Math.abs(30 * chanceOfMovingLeft);
		if (chanceOfMovingLeft > 0) {
			moveLeft();
		} else {
			moveRight();
		}
		
		if (chanceOfMovingUp > 0 && getPlayerPosition().y > 70) {
			moveUp();
		} else{
     	  moveDown();
		}
		
	}

	/**
	 * Sets the initial position
	 */
	@Override
	public void setInitialPosition() {
		setPlayerPosition(new Point(280, 70));
	}

	/**
	 * Prints the statistics of Goalkeeper.
	 */
	@Override
	public String toString() {
		return playerName + " caught " + playerStatistics.toString() + " balls";
	}
}
