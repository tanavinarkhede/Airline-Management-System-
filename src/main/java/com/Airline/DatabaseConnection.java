package com.Airline;
import java.sql.*;
public class DatabaseConnection {
	public static Connection Connecttodatabase()throws Exception
	{
		String url="jdbc:mysql://localhost:3306/airline?autoReconnect=true&useSSL=false";
		String user="root";
		String password="Tanavi@2003";	
		
		Class.forName("com.mysql.jdbc.Driver");
		
		Connection connect=DriverManager.getConnection(url,user,password);
		return connect;
		
	}

}
