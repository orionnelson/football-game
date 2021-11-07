package model.players;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.Objects;

/**
 * This class extends Iterable<GamePlayer> 
 * and represents a collection of players as an ArrayList 
 * which includes Striker and Goalkeeper.
 *
 */
public class PlayerCollection implements Iterable<GamePlayer>{

	/**
	 * A list to store the players of the game.
	 */
	private List<GamePlayer> playerList;

	/**
	 * A constructor to initialize the list of players.
	 */
	public PlayerCollection() {
		playerList = new ArrayList<>();
	}
	
	/**
	 * Adds a player to collection.
	 * @param player is the player to add.
	 */
	public void add(GamePlayer player) {
		playerList.add(player);
	}

	/**
	 * Gets a player from the collection based on input.
	 * @param type is player to return.
	 * @return GamePlayer requested.
	 */
	public GamePlayer get(String type) {
		for (GamePlayer player: playerList) {
			if (Objects.equals(player.getPlayerName(), type)) {
				return player;
			}
		}
		return null;
	}

	/**
	 * Sorts players based on score and caught statistics.
	 */
	public void sort() {
		int stat1 = playerList.get(0).getPlayerStatistics();
		int stat2 = playerList.get(1).getPlayerStatistics();
		if (stat1 < stat2) {
			GamePlayer temp = playerList.get(0);
			playerList.set(0, playerList.get(1));
			playerList.set(1, temp);
		}
	}

	/**
	 * Get a iterator for playerList based on PlayerCollectionIterator.
	 * @returns Iterator<GamePlayer> to iterate player list.
	 */
	@Override
	public Iterator<GamePlayer> iterator() {
		return new PlayerCollectionIterator(playerList);
	}
	
	

}
