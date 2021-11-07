package model;

import java.awt.*;
import java.util.Timer;
import java.util.TimerTask;

/**
 * This class uses Singleton pattern and represents a soccer ball.
 */
public class SoccerBall {

	/**
	 * Initialize a soccer ball as per pattern.
	 */
	private static final SoccerBall soccerBall = new SoccerBall();

	/**
	 * position used to represent where the ball is.
	 */
	private Point position;

	/**
	 * velocity used to represent speed.
	 */
	private double velocity;
	
	/**
	 * color used to represent ball color
	 * i.e. white in our case.
	 */
	private final Color color;

	/**
	 * This is the private constructor for SoccerBall 
	 * as per pattern. It initializes color and resets position.
	 */
	private SoccerBall() {
		color = Color.white;
		resetSoccerBall();
	}

	/**
	 * This method returns an instacne of the class. 
	 * @return SoccerBall the instance of class
	 */
	public static SoccerBall getSoccerBall() {
		return soccerBall;
	}
	
	/**
	 * This method moves the ball in a given direction based on parameter input.
	 * It repaints based on change in position.
	 * @param initialDistance represents initial distance
	 * @param initialVelocity represents initial velocity
	 * @param acceleration represents acceleration.
	 */
	public void moveBall(int initialDistance, double initialVelocity, double acceleration) {
		moveBallY(initialDistance);
		setVelocity(initialVelocity);
		Timer timer = new Timer();
		TimerTask repaintTask = new TimerTask() {
			@Override
			public void run() {
				if (Math.abs(velocity) < 2) {
					velocity = 0.0;
					timer.cancel();
				} else {
					velocity = velocity - acceleration;
				}
				moveBallY((int) velocity);
			}
		};
		timer.schedule(repaintTask, 0, 10);
	}
	
	/**
	 * Moves the ball based on distance along the y axis
	 * @param distance
	 */
	public void moveBallY(int distance) {
		if (getPosition().y + distance < 510 && getPosition().y - distance > 20) {
			setPosition(new Point(getPosition().x, getPosition().y - distance));
		} else {
			setVelocity(0.0);
		}
	}

	/**
	 * Resets the position of the ball
	 */
	public void resetSoccerBall() {
		setVelocity(0.0);
		setPosition(new Point(480, 500));
	}

	/**
	 * Checks to see if the ball is on the goalkeeper's side.
	 * @return boolean true or false.
	 */
	public boolean onGoalkeeperSide() {
		return getPosition().y < 200;
	}

	/**
	 * Checks if the ball is in the gate
	 * @return boolean true or false.
	 */
	public boolean inGate() {
		return getPosition().x > 180 && getPosition().x < 400 && getPosition().y > 10 && getPosition().y < 60;
	}

	/**
	 * Set the velocity of the ball.
	 * @param velocity represents the speed of the ball.
	 */
	public void setVelocity(double velocity) {
		this.velocity = velocity;
	}
	
	/**
	 * Get the velocity of ball.
	 * @return double representing velocity.
	 */
	public double getVelocity() {
		return this.velocity;
	}

	/**
	 * Gets the position of the ball
	 * @return a Point representing the position.
	 */
	public Point getPosition() {
		return position;
	}

	/**
	 * Sets the position of the ball
	 * @param position represents location.
	 */
	public void setPosition(Point position) {
		this.position = position;
	}

	/**
	 * Gets the color of the ball
	 * @return color
	 */
	public Color getColor() {
		return color;
	}
}
