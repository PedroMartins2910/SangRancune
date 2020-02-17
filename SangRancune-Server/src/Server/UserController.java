package Server;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Objects;

import Lib.*;

public class UserController {
	
	Model model = new Model();
	 final String secretKey = "secret";
	public UserController() 
	{
		
	}
	
		
	
		//Fonction OK, revoie un objet UserCheck 
		public UserCheck CheckUser(UserCheck user) 
		{
			UserCheck newUser = new UserCheck(); 
			newUser.setLogin("rom");
			newUser.setPassword("Rom123");
			
			String username="";
			
			ResultSet rs= null;
			String password = AES.encrypt(user.getPassword(), secretKey);
			String query = "SELECT login FROM user WHERE login='"+user.getLogin()+"' AND password='"+password+"' ";
			// String query = "SELECT login FROM user WHERE login='rom' AND password='Rom123' ";  //for testing
			rs = model.bddConnection(query);
			try {
				while(rs.next()) {  
					
				username = rs.getString(1);
				System.out.print(username);
				}		} catch (SQLException e) {
				e.printStackTrace();
			} 
		if(Objects.equals(username,"rom")) 
		{
			System.out.print("can connect");
			newUser.setCanConnect(true);
			return newUser;
		}else {
			System.out.print("can not connect");
			newUser.setCanConnect(false);
			return newUser;
		}
		}	
	
		// Verifie que l'user n'existe pas deja 
		public UserNewAccount UserAdd(UserNewAccount user) 
		{
			UserNewAccount newUser = new UserNewAccount();
			newUser.setEmail(user.getEmail());
			newUser.setLogin(user.getLogin());
			newUser.setPassword(user.getPassword());
			String queryLogin = "SELECT login FROM user WHERE login='"+user.getLogin()+"'";
			String queryEmail = "SELECT email FROM user WHERE email='"+user.getPassword()+"' ";
			ResultSet rsLogin= null;
			ResultSet rsEmail= null;
			rsLogin = model.bddConnection(queryLogin);
			rsEmail = model.bddConnection(queryEmail);
			
			
			try {
				if(!rsLogin.next() && !rsEmail.next()) // !rs.next() renvoie true si la requete est vide
				{
					 //Ajoute k'utilisateur s'il n'hexiste pas 
					newUser.setDone("yes");
					model.AddUser(newUser,secretKey);
					return newUser;
				}
			} catch (SQLException e) {
				e.printStackTrace();
			};
			
			
			try {
				if(!rsLogin.next() && rsEmail.next())
				newUser.setDone("email");
				return newUser;
			} catch (SQLException e) {
				e.printStackTrace();
			}	
			
			
			try {
				if(rsLogin.next() && !rsEmail.next())
				newUser.setDone("login");
				return newUser;
			} catch (SQLException e) {
				e.printStackTrace();
			}
			
			return null;
		}
		
		
}
