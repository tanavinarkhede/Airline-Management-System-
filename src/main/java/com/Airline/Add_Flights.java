package com.Airline;
import java.io.IOException;

import java.sql.*;

import javax.servlet.RequestDispatcher;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
@WebServlet("/addf")

public class Add_Flights extends HttpServlet {
	public void service(HttpServletRequest req, HttpServletResponse res)throws IOException
	{
	
		String no = req.getParameter("no");
		String fname = req.getParameter("fname");
		String source = req.getParameter("source");
		String dec = req.getParameter("dec");
		String dob = req.getParameter("dob");
		String time = req.getParameter("time");
		String tra_ch = req.getParameter("tra_ch");
		try
		{
			Connection con=DatabaseConnection.Connecttodatabase();
			PreparedStatement ps=con.prepareStatement("insert into flights values(?, ?, ?, ?, ?, ?, ?)");
		
			ps.setString(1, no);
			ps.setString(2, fname);
			ps.setString(3, source);
			ps.setString(4, dec);
			ps.setString(5, dob);
			ps.setString(6, time);
			ps.setString(7, tra_ch);
		
			int i = ps.executeUpdate();
		
			if(i>0)
			{
				res.sendRedirect("FlightsAdded.html");
			}
		}catch(Exception e) {System.out.println(e);}
	}

}
