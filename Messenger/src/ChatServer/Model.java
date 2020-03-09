package ChatServer;

import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.Statement;

import Lib.UserCon;
import Lib.UserNew;


public class Model {

	public ResultSet  bddConnection(String query) {
		ResultSet rs = null;
		try {
			java.sql.Connection con= DriverManager.getConnection("jdbc:mysql://localhost:3306/testbase","root","root");  
			Statement stmt=con.createStatement();  
			 rs=stmt.executeQuery(query);  
			 
			}catch(Exception e){ System.out.println(e);}
			
		return rs;
		
	}
	
	
	public void AddUser(UserNew user, String secretKey) 
	{
		ResultSet rs;
		String password = AES.encrypt(user.password, secretKey);
		String query = "INSERT INTO table (login, password, email) VALUES ('"+user.login+"', '"+password+"', '"+user.email+"')";
		rs = bddConnection(query);
	}
	
	
	}