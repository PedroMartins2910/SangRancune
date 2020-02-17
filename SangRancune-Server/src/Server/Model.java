package Server;

import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.Statement;

import Lib.UserNewAccount;

public class Model {

	public ResultSet  bddConnection(String query) {
		ResultSet rs = null;
		try {
			java.sql.Connection con= DriverManager.getConnection("jdbc:mysql://localhost:3308/SangRancune","root","");  
			Statement stmt=con.createStatement();  
			 rs=stmt.executeQuery(query);  
			 
			}catch(Exception e){ System.out.println(e);}
			
		return rs;
		
	}
	
	
	public void AddUser(UserNewAccount user, String secretKey) 
	{
		ResultSet rs;
		String password = AES.encrypt(user.getPassword(), secretKey);
		String query = "INSERT INTO table (login, password, email) VALUES ('"+user.getLogin()+"', '"+password+"', '"+user.getEmail()+"')";
		rs = bddConnection(query);
	}
	
	
	}