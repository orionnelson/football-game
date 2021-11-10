package model.players;

/**
 * This class represents player statistics 
 * either count of catches or goals scored.
 */
public class PlayerStatistics {
	
	/**
	 * Represents numerical statistic of either
	 * count of goals or catches.
	 */
	private int count = 0;

	/**
	 * Get the statistic of player.
	 * @return the count of goals or catches.
	 */
	public Integer getStatistics() {
		return count;
	}

	/**
	 * Sets the statistics of player.
	 * @param newStatistics is the integer count change.
	 */
	public void setStatistics(Integer newStatistics) {
		count = newStatistics;
	}
	
	/**
	 * Get a string representation of player statistics.
	 * @return Converts the number to an Integer
	 */
	public String toString(){
		return Integer.toString(getStatistics());
	}

}
