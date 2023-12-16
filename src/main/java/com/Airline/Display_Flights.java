package com.Airline;

import java.io.IOException;

import java.io.PrintWriter;
import java.sql.*;

import javax.servlet.ServletResponse;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
@WebServlet("/view")
public class Display_Flights extends HttpServlet{
	public void service(HttpServletRequest request, HttpServletResponse res)throws IOException
	{
		try
		{		  
		  	Connection con=DatabaseConnection.Connecttodatabase();
			String quary = "SELECT * FROM flights;";
			Statement st = con.createStatement();
			ResultSet rs = st.executeQuery(quary);

			res.getWriter().println("<html><head><style>button[type=submit]{padding:5px;color: blue; cursor: pointer;font-size:20px;font-weight:bold;width:100px}"
					+ "button[id=btn]{padding: 10px; color: green;cursor: pointer;font-size:20px;font-weight:bold; width: max-content}</style></head><body><br><h1 align='center' >All Flights</h1><br>");
			res.getWriter().println("<table align='center' border='1'><tr><td>Flight_No</td><td>Flight_Name</td>"
					+ "<td>From</td><td>Land</td><td>Flight_Date</td><td>Flight_Time</td><td>Traveling_Charges</td><td>Book Flight</td></tr>");
			while(rs.next())
			{
				res.getWriter().println("<tr><td>"+rs.getString(1)+"</td><td>"+rs.getString(2)+"</td><td>"+rs.getString(3)
				+"</td><td>"+rs.getString(4)+"</td><td>"+rs.getString(5)+"</td><td>"+rs.getString(6)+"</td><td>"
				+rs.getString(7)+"</td><td><button type='submit'><a href='BookTicket.html'>ADD</a></button></td></tr>");
			}
			
			res.getWriter().println("</table><br><br><button id='btn' type='submit' style='margin-left: 45%'><a href='HomePage.html'>Go To Home</a></button></body></html>");
		}
		catch(Exception e){
			e.printStackTrace();
		}
	}
}
