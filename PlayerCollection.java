package model.players;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.Objects;

public class PlayerCollection implements Iterable<GamePlayer>{

	private List<GamePlayer> playerList;

	public PlayerCollection() {
		playerList = new ArrayList<>();
	}
	
	public void add(GamePlayer player) {
		playerList.add(player);
	}

	public GamePlayer get(String type) {
		for (GamePlayer player: playerList) {
			if (Objects.equals(player.getPlayerName(), type)) {
				return player;
			}
		}
		return null;
	}

	public void sort() {
		int stat1 = playerList.get(0).getPlayerStatistics();
		int stat2 = playerList.get(1).getPlayerStatistics();
		if (stat1 < stat2) {
			GamePlayer temp = playerList.get(0);
			playerList.set(0, playerList.get(1));
			playerList.set(1, temp);
		}
	}

	@Override
	public Iterator<GamePlayer> iterator() {
		return new PlayerCollectionIterator(playerList);
	}
	
	

}
