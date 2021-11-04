package model.players;

public class PlayerStatistics {
	
	int goalCount = 0;

	public Integer getStatistics() {
		return goalCount;
	}

	public void setStatistics(Integer newStatistics) {
		goalCount = newStatistics;
	}
	
	public String toString(){
		return Integer.toString(getStatistics());
	}

}
