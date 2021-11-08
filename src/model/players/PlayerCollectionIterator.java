package model.players;

import java.util.Iterator;
import java.util.List;
import java.util.ListIterator;

/**
 * This class implements Iterator to get the collection of players
 * and represents the iterator for the PlayerCollection.
 * It allows iterating the collection of players.
 */
public class PlayerCollectionIterator implements Iterator<GamePlayer>{
	
	/**
	 * Represents a ListIterator for players
	 */
	private ListIterator<GamePlayer> iterator;
	
	/**
	 * Constructor of class to initialize iterator
	 * @param players represents list of players.
	 */
	public PlayerCollectionIterator(List<GamePlayer> players) {
		iterator = players.listIterator();
	}
	
	/**
	 * Checks if there is a next element in the iterator.
	 */
	public boolean hasNext() {
		return iterator.hasNext();
	}
	
	/**
	 * Gets the next element in the list if available.
	 * @return next GamePlayer if exists.
	 */
	public GamePlayer next() {
		return iterator.next();
	}
	

}
