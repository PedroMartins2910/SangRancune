package Lib;

public class UserNewAccount {
	String email;
	String login;
	String password;
	String done; //yes, email, login
	
	public String getDone() {
		return done;
	}
	public void setDone(String done) {
		this.done = done;
	}
	public String getEmail() {
		return email;
	}
	public void setEmail(String email) {
		this.email = email;
	}
	public String getLogin() {
		return login;
	}
	public void setLogin(String login) {
		this.login = login;
	}
	public String getPassword() {
		return password;
	}
	public void setPassword(String password) {
		this.password = password;
	}

}
