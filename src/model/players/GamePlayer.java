package model.players;

import model.SoccerBall;

import java.awt.*;

/**
 * This is an abstract class that implements Comparable to get final score 
 * and represents players (goal keeper and player) of the game.  
 *
 */
public abstract class GamePlayer implements Comparable<GamePlayer> {

	/**
	 * Represents the name of a player as String.
	 */
	protected final String playerName;

	/**
	 * Represents the color of a player using Color.
	 */
	protected final Color playerColor;

	/**
	 * Represents the player position as a Point
	 */
	protected Point playerPosition;

	/**
	 * Represents statistics of player as PlayerStatistics
	 */
	protected final PlayerStatistics playerStatistics;

	/**
	 * Constructor for the player.
	 * @param name is the name of player
	 * @param color is the color of player
	 */
	public GamePlayer(String name, Color color) {
		playerName = name;
		playerColor = color;
		playerStatistics = new PlayerStatistics();
		setInitialPosition();
	}

	/**
	 * Checks to see if player has the ball
	 * @return true or false.
	 */
	public boolean isPlayerHasBall() {
		Point playerPositionCenter = new Point(getPlayerPosition().x + 15, getPlayerPosition().y + 30);
		return playerPositionCenter.distance(SoccerBall.getSoccerBall().getPosition()) < 55;
	}

	/**
	 * Grabs the ball if a player is next to it.
	 */
	public void grabsBall() {
		SoccerBall ball = SoccerBall.getSoccerBall();
		if (getPlayerPosition().x + 15 > ball.getPosition().x) {
			ball.setPosition(new Point(getPlayerPosition().x - 10, getPlayerPosition().y + 55));
		} else {
			ball.setPosition(new Point(getPlayerPosition().x + 20, getPlayerPosition().y + 55));
		}
	}

	/**
	 * Abstract method to move player to left.
	 */
	public abstract void moveLeft();

	/**
	 * Abstract method to move player to right.
	 */
	public abstract void moveRight();

	/**
	 * Abstract method to move player up.
	 */
	public abstract void moveUp();

	/**
	 * Abstract method to move player down.
	 */
	public abstract void moveDown();

	/**
	 * Abstract method to shoot ball.
	 */
	public abstract void shootBall();

	/**
	 * Get the player's name.
	 * @return name of player
	 */
	public String getPlayerName() {
		return playerName;
	}

	/**
	 * Get the player's color.
	 * @return color of player
	 */
	public Color getPlayerColor() {
		return playerColor;
	}

	/**
	 * Get the position of a player.
	 * @return position of player.
	 */
	public Point getPlayerPosition() {
		return playerPosition;
	}

	/**
	 * Abstract method to set initial position.
	 */
	public abstract void setInitialPosition();

	/**
	 * Set the position of a player.
	 * @param newPosition position of player
	 */
	public void setPlayerPosition(Point newPosition) {
		playerPosition = newPosition;
		if (isPlayerHasBall()) {
			grabsBall();
		}
	}

	/**
	 * Get the statistics of a player.
	 * @return integer representing catch or score of player.
	 */
	public Integer getPlayerStatistics() {
		return playerStatistics.getStatistics();
	}

	/**
	 * Set the statistics of a player
	 * @param newStatistics represents new number.
	 */
	public void setPlayerStatistics(Integer newStatistics) {
		playerStatistics.setStatistics(newStatistics);
	}

	/**
	 * Compare statistics of two players.
	 * @param otherPlayer represents other player to compare to.
	 */
	@Override
	public int compareTo(GamePlayer otherPlayer) {
		return otherPlayer.getPlayerStatistics().compareTo(this.getPlayerStatistics());
	}

	/**
	 * Abstract method to print player details.
	 */
	@Override
	public abstract String toString();
}
