package model.players;

import java.util.Iterator;
import java.util.List;
import java.util.ListIterator;

public class PlayerCollectionIterator implements Iterator<GamePlayer>{
	
	private ListIterator<GamePlayer> iterator;
	
	public PlayerCollectionIterator(List<GamePlayer> players) {
		iterator = players.listIterator();
	}
	
	public boolean hasNext() {
		return iterator.hasNext();
	}
	
	public GamePlayer next() {
		return iterator.next();
	}
	

}
