package application;

public class User {
	private String username;
	private String password;
	
	public User() {}

	public String getUsername() {
		return username;
	}

	public void setUsername(String username) {
		this.username = username;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}
	
	@Override
	public String toString() {
		return "USERNAME: " + getUsername() + "\nPASSWORD: " + getPassword();
	}
	
	public void hashPass() {
		this.setPassword(String.valueOf(31 * (this.getUsername().hashCode() * (this.getPassword().hashCode()))));
	}
	
}
