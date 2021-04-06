import java.sql.*;
import java.io.*;
import javax.servlet.http.*;
import javax.servlet.*;

public class SignUp extends HttpServlet
{
	public void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException
	{
		try
		{
			Class.forName("com.mysql.cj.jdbc.Driver");
			Connection con = DriverManager.getConnection("jdbc:mysql://127.0.0.1/assignment2","root", "");
			Statement st = con.createStatement();

			PrintWriter out = response.getWriter();
			String fn = request.getParameter("fname");
			String ln = request.getParameter("lname");
			String email = request.getParameter("uname1") + "@gmail.com";
			String password = request.getParameter("password");
			
			String query = "insert into signups(Fname,Lname,Email,Password) values('" + fn  + "', '" + ln + "', '" + email + "',  '" + password + "')";
			int e = st.executeUpdate(query);		
			if(e>0)
			{
				out.println("<html>" + "<head>" +"</head>" + "<body>" + "<h1>" + "Account Created Sucessfully" + "</h1>" + "<a href = \"SignIn.html\">" + "Click here to do Sign In" + "</a>" + "</body>");
			}
			else
			{
				out.println("<html>  <head> </head> <body> <h1> Unable to create account </h1> <a href = \"SignUp.html\"> Click here to try again </a></body>");
			}
			con.close();
		}
		catch(Exception e)
		{
			System.out.println("An error was triggered, stated as:  " + e.getMessage());
		}		
	}
}