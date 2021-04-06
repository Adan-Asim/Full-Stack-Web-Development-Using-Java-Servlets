import java.sql.*;
import java.io.*;
import javax.servlet.http.*;
import javax.servlet.*;

public class SignIn extends HttpServlet
{
	public void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException
	{
		try
		{
			Class.forName("com.mysql.cj.jdbc.Driver");
			Connection con = DriverManager.getConnection("jdbc:mysql://127.0.0.1/assignment2","root", "");
			Statement st = con.createStatement();

			PrintWriter out = response.getWriter();
			String email = request.getParameter("uname1") + "@gmail.com";
			String password = request.getParameter("password");
			
			String query = "SELECT * FROM signups WHERE Email = '" + email + "'and Password = '" + password + "'";
			ResultSet r = st.executeQuery(query);	
			if(r.next())
			{
				out.println("<html>" + "<head>" +"</head>" + "<body>" + "<h1>" + "SignIn Successfull" + "</h1>" + "<a href = \"SignIn.html\">" + "Click here to do Sign In again" + "</a>" + "</body>");
			}
			else
			{
				out.println("<html>  <head> </head> <body> <h1> Unable to SignIn, Account doesn't exists </h1> <a href = \"SignIn.html\"> Click here to try again </a> </body>");
			}
			con.close();
		}
		catch(Exception e)
		{
			System.out.println("An error was triggered, stated as:  " + e.getMessage());
		}		
	}
}