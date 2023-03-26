package application;

public class Admin extends User{
	
	private String adminPassword;
	
	public Admin() {}
	
	public Admin(String username, String password, String adminPassword) {
		setUsername(username);
		setPassword(password);
		setAdminPassword(adminPassword);
	}

	public String getAdminPassword() {
		return adminPassword;
	}

	public void setAdminPassword(String adminPassword) {
		this.adminPassword = adminPassword;
	};
	
	
	@Override
	public String toString() {
		return "USERNAME: " + getUsername() + "\nPASSWORD: " + getPassword() + "\nADMINPW: " + getAdminPassword();
	}

}
