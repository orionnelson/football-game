package model.players;

import java.awt.Color;
import java.util.Objects;

/**
 * This is a factory class that creates players
 * for the soccer game as per factory pattern.
 */
public class PlayerFactory {

	/**
	 * Factory method to create an instance of player requested.
	 * @param type is the type of player desired.
	 * @return GamePlayer requested if available. Otherwise null.
	 */
	public GamePlayer getPlayer(String type) {
		if(Objects.equals(type, "striker")) {
			return new Striker("Striker", Color.BLUE);
		}
		else if(Objects.equals(type, "goalkeeper")) {
			return new Goalkeeper("Goalkeeper", Color.YELLOW);
		}
		return null;
	}

}
