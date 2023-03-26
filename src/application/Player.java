package application;

public class Player extends User {
	
	private String playerID;
	private String fName;
	private String lName;
	
	public Player() {}
	
	public Player(String username, String password, String playerID, String fName, String lName) {
		setUsername(username);
		setPassword(password);
		setPlayerID(playerID);
		setFName(fName);
		setLName(lName);
	}

	public String getPlayerID() {
		return playerID;
	}

	public void setPlayerID(String playerID) {
		this.playerID = playerID;
	}
	
	public String getFName() {
		return fName;
	}

	public void setFName(String fName) {
		this.fName = fName;
	}

	public String getLName() {
		return lName;
	}

	public void setLName(String lName) {
		this.lName = lName;
	}
	
	@Override
	public String toString() {
		return "USERNAME: " + getUsername() + "\nPASSWORD: " + getPassword() + "\nID: " + getPlayerID();
	}

}
