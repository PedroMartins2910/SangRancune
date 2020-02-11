package com.MarSen.app.SangRancune_Server;

import java.sql.*;

import com.MarSen.app.SangRancune_Lib.UserCheck;

public class Controller {

	
	
	public Controller()
	{
		checkUser();
		
	}
	
	public void checkUser() 
	{
		
		try {
		Class.forName("com.mysql.jdbc.Driver");  
		java.sql.Connection con= DriverManager.getConnection("jdbc:mysql://localhost:3306/inventaire","root","");  
		
		Statement stmt=con.createStatement();  
		ResultSet rs=stmt.executeQuery("select * from list");  
		while(rs.next())  
		System.out.println(rs.getInt(1)+"  "+rs.getString(2)+"  "+rs.getString(3));  
		con.close();  
		}catch(Exception e){ System.out.println(e);}
		
		
	}
	
}
