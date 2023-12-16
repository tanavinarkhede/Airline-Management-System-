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
@WebServlet("/check")
public class Check_Details extends HttpServlet
{
	public void service(HttpServletRequest req, HttpServletResponse res)throws IOException
	{
			String encpass=null;
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
			try{

			Connection con=DatabaseConnection.Connecttodatabase();
			String quary = "SELECT * FROM users where Email_Id='"+email+"' and Password='"+encpass+"';";
			Statement st = con.createStatement();
			ResultSet rs = st.executeQuery(quary);
			
			int flag=0;
			while(rs.next()) {
				res.sendRedirect("UserExist.html");
			}
			if(flag!=1) {
				res.sendRedirect("UserNotExist.html");
			}
		}
		catch(Exception e) {e.getStackTrace();}
	}
}