package model.players;

import java.awt.Color;
import java.util.Objects;

public class PlayerFactory {

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
