package Lib;

/*
 * Vérification du mot de passe et du login.
 * Si login faux, alerte
 * Si password faux, alerte + proposition de réinitialisation
 * => prévoir une classe à part pour la réinitialisation password
 */

public class UserCheck {
private String login;
private String password;
private Boolean canConnect;

public UserCheck() 
{
	
}

public String getLogin() 
{
	return login;
}

public void setLogin(String login) 
{
	this.login = login;
}

public String getPassword() {
	return password;
}

public void setPassword(String password) {
	this.password = password;
}

public Boolean getCanConnect() {
	return canConnect;
}

public void setCanConnect(Boolean canConnect) {
	this.canConnect = canConnect;
}


}


