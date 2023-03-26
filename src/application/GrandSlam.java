package application;

public class GrandSlam {
	private String slamID;
	private String slamName;
	private String playerID;
	private String playerName;
	private String playerWinnings;
	
	public GrandSlam() {};
	
	public GrandSlam(String slamID, String slamName, String playerID, String playerName, String playerWinnings) {
		setSlamID(slamID);
		setSlamName(slamName);
		setPlayerID(playerID);
		setPlayerName(playerName);
		setPlayerWinnings(playerWinnings);
	}

	public String getSlamID() {
		return slamID;
	}

	public void setSlamID(String slamID) {
		this.slamID = slamID;
	}

	public String getSlamName() {
		return slamName;
	}

	public void setSlamName(String slamName) {
		this.slamName = slamName;
	}

	public String getPlayerID() {
		return playerID;
	}

	public void setPlayerID(String playerID) {
		this.playerID = playerID;
	}

	public String getPlayerName() {
		return playerName;
	}

	public void setPlayerName(String playerName) {
		this.playerName = playerName;
	}

	public String getPlayerWinnings() {
		return playerWinnings;
	}

	public void setPlayerWinnings(String playerWinnings) {
		this.playerWinnings = playerWinnings;
	};
	
	
	@Override
	public String toString() {
		String str;
		 str = "Slam ID: " + getSlamID() + "\nSlam Name: " + getSlamName() + "\nPlayer ID: " + getPlayerID() + "\nPlayer Name: " + getPlayerName() + "\nPlayer Winnings: " + getPlayerWinnings();
		return str;
	}
	
}
