package com.Airline;
import java.io.IOException;


import java.math.BigInteger;
import java.security.MessageDigest;
import java.sql.*;

import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;


@WebServlet("/airline")
public class SignUpDataCon extends HttpServlet {

	public void service(HttpServletRequest req, HttpServletResponse res)throws IOException
	{
		String encpass=null;
		String name = req.getParameter("name");
		String phone = req.getParameter("phone");
		String dob = req.getParameter("dob");
		String email = req.getParameter("email");
		String password = req.getParameter("password");
		
		try {
			MessageDigest mdEnc = MessageDigest.getInstance("MD5");
			mdEnc.update(password.getBytes(), 0, password.length());
			encpass = new BigInteger(1, mdEnc.digest()).toString(16);
		}
		catch(Exception ex) {
			ex.printStackTrace();
		}
		
		try
		{
			Connection con=DatabaseConnection.Connecttodatabase();
			PreparedStatement ps=con.prepareStatement("insert into users values(?, ?, ?, ?, ?)");
			
			ps.setString(1, name);
			ps.setString(2, phone);
			ps.setString(3, dob);
			ps.setString(4, email);
			ps.setString(5, encpass);
			
			int i = ps.executeUpdate();
			
			if(i>0)
			{
				res.sendRedirect("UserAdded.html");
			}
		}catch(Exception e) {System.out.println(e);}
	}

}
