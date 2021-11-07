package model;

import java.util.Timer;
import java.util.TimerTask;

import model.players.*;

/**
 * This class represents the soccer game
 * and includes main features.
 */
public class SoccerGame {

	/**
	 * Represents the seconds left in game.
	 */
	private Integer timeRemaining;

	/**
	 * Represents the number of goals.
	 */
	private Integer goal;

	/**
	 * Represents if game is paused.
	 */
	private Boolean isPaused;

	/**
	 * Represents if the game is over.
	 */
	private Boolean isOver;

	/**
	 * Represents a collection of game players.
	 */
	private final PlayerCollection gamePlayers;

	/**
	 * Constructor for the class.
	 * Initializes the game with starting values.
	 */
	public SoccerGame() {
		timeRemaining = 60;
		goal = 0;
		isPaused = false;
		isOver = false;
		SoccerBall.getSoccerBall().resetSoccerBall();
		PlayerFactory playerFactory = new PlayerFactory();
		gamePlayers = new PlayerCollection();
		gamePlayers.add(playerFactory.getPlayer("striker"));
		gamePlayers.add(playerFactory.getPlayer("goalkeeper"));
		startGame();
	}

	/**
	 * Private constructor which acts as helper for the public 
	 * constructor to start the game and initialize fields.
	 */
	private void startGame() {
		Timer timer = new Timer();
		TimerTask timerTask = new TimerTask() {
			@Override
			public void run() {
				if (!isPaused()) {
					if (getTimeRemaining() <= 0) {
						setOver(true);
						timer.cancel();
					} else {
						setTimeRemaining(getTimeRemaining() - 1);
					}
					if (isScored()) {
						setPaused(true);
						setGoal(getGoal() + 1);
						getActivePlayer().setPlayerStatistics(getActivePlayer().getPlayerStatistics() + 1);
						getGamePlayers().get("Striker").setInitialPosition();
						SoccerBall.getSoccerBall().resetSoccerBall();
					} else {
						automateGoalkeeper();
					}
				}
			}
		};
		timer.schedule(timerTask, 1000, 1000);
	}

	/**
	 * Get the time remaining.
	 * @return seconds left
	 */
	public Integer getTimeRemaining() {
		return timeRemaining;
	}
	
	/**
	 * Set the time remaining
	 * @param timeRemaining
	 */
	public void setTimeRemaining(Integer timeRemaining) {
		this.timeRemaining = timeRemaining;
	}

	/**
	 * Get the number of goals
	 * @return total goals.
	 */
	public Integer getGoal() {
		return goal;
	}

	/**
	 * Set the amount of goals
	 * @param newGoal as new number of goals
	 */
	public void setGoal(Integer newGoal) {
		goal = newGoal;
	}

	/**
	 * Check if the game is paused 
	 * @return true of false.
	 */
	public Boolean isPaused() {
		return isPaused;
	}

	/**
	 * Set if the game is paused.
	 * @param paused true or false based on if paused.
	 */
	public void setPaused(Boolean paused) {
		isPaused = paused;
	}

	/**
	 * Check if the game is over.
	 * @return true or false.
	 */
	public Boolean isOver() {
		return isOver;
	}

	/**
	 * Set if the game is over
	 * @param over as true or false based on if over.
	 */
	public void setOver(Boolean over) {
		isOver = over;
	}

	/**
	 * Get a collection of game players.
	 * @return collection of players
	 */
	public PlayerCollection getGamePlayers() {
		return gamePlayers;
	}

	/**
	 * Method to automate Goalkeeper movement.
	 */
	public void automateGoalkeeper() {
		SoccerBall soccerBall = SoccerBall.getSoccerBall();
		Goalkeeper goalkeeper = (Goalkeeper) gamePlayers.get("Goalkeeper");
		if (soccerBall.onGoalkeeperSide()) {
			goalkeeper.grabsBall();
			goalkeeper.shootBall();
			goalkeeper.setPlayerStatistics(goalkeeper.getPlayerStatistics() + 1);
		} else {
			goalkeeper.moveRandomly();
		}
	}

	/**
	 * Check if player has scored.
	 * @return true or false based on if scored.
	 */
	public boolean isScored() {
		return SoccerBall.getSoccerBall().inGate();
	}

	/**
	 * Get the active player
	 * @return striker playing match
	 */
	public GamePlayer getActivePlayer() {
		return gamePlayers.get("Striker");
	}
}
