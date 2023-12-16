package com.Airline;
import java.io.IOException;


import java.math.BigInteger;
import java.security.MessageDigest;
import java.sql.*;

import javax.servlet.RequestDispatcher;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
@WebServlet("/checkadmin")

public class Admin extends HttpServlet{
	public void service(HttpServletRequest req, HttpServletResponse res)throws IOException
	{
		String encpass=null;
		String uname = req.getParameter("uname");
		String pass = req.getParameter("pass");
	
		try {
			MessageDigest mdEnc = MessageDigest.getInstance("MD5");
			mdEnc.update(pass.getBytes(), 0, pass.length());
			encpass = new BigInteger(1, mdEnc.digest()).toString(16);
		}
		catch(Exception ex) {
			ex.printStackTrace();
		}
		
		try 
		{
			Connection con=DatabaseConnection.Connecttodatabase();
			String query = "SELECT * FROM admin where User_Name='"+uname+"' and Password='"+encpass+"';";
			Statement st = con.createStatement();
			ResultSet rs = st.executeQuery(query);
			
			int flag=0;
			while(rs.next()) {
				res.sendRedirect("AddFlight.html");
			}
			if(flag!=1) {
				res.sendRedirect("AdminNotExist.html");
			}
		}
		catch(Exception ex) { ex.printStackTrace();}
	}
}
