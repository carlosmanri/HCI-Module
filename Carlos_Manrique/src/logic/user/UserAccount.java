package logic.user;

import logic.ApplicationException;

public class UserAccount{
	private String username;
	private String password;
	
	public UserAccount(String username, String password) throws ApplicationException {
		if(username == null || password == null || username.length() == 0 || password.length() == 0) {
			throw new ApplicationException("Username and password must not be empty!");
		}

		setUsername(username);
		setPassword(password);
	}
	
	public String getUsername() {
		return username;
	}
	
	private void setUsername(String username) {
		this.username = username;
	}
	
	public String getPassword() {
		return password;
	}
	
	private void setPassword(String password) {
		this.password = password;
	}
	
	public String toString() {
		return "User "+getUsername()+"@"+getPassword();
	}

	public String serialize() {
		return toString();
	}

	@Override
	public boolean equals(Object o) {
		if(o instanceof UserAccount) {
		UserAccount u = (UserAccount)o;
		return ((getUsername().compareTo(u.getUsername()) == 0) &&
				(getPassword().compareTo(u.getPassword()) == 0))?true:false;
		}
		else return false;

	}
	
}
