import java.sql.*;
import java.io.*;
import javax.servlet.http.*;
import javax.servlet.*;

public class mobileInfo extends HttpServlet
{
	public void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException
	{
		try
		{	
			PrintWriter out = response.getWriter();
			Class.forName("com.mysql.cj.jdbc.Driver");
			Connection con = DriverManager.getConnection("jdbc:mysql://127.0.0.1/mobileinfo","root", "");
			Statement st = con.createStatement();	
		
			String query = "SELECT * FROM mobileoutlet" ;
			ResultSet rs = st.executeQuery(query);
	
			out.println("<html> ");
			out.println("	 <body style = \"background-image:url('bg1.jpg'); background-size:cover; color: white; padding-left:3%;margin-top:2%\">");
			out.println("		<a href = \"1-MainPage.html\" style = \"text-decoration:none; font-size:110%; color:white;margin-left:2%\"><button>Go back to main Page</button></a><br><br><br>");
			out.println("		<table style = \"border: 1px solid white\"> ");
			out.println("		<tr style = \"margin-top:2%\">");
			out.println("			<th>Company Name|</th>");
			out.println("			<th>Mode|</th>");
			out.println("			<th>Price</th>");
			out.println("		</tr>");
			while(rs.next())
			{
			out.println("		<tr>");
			out.println("			<th>" + rs.getString("companyName") );
			out.println("			<hr><th>" + rs.getString("model") );
			out.println("			<hr><th>" + rs.getInt("price") );
			out.println("		<hr></tr>");
			}
			out.println("	</body>");
			out.println("</html>");
			
			con.close();
		}
		catch(Exception e)
		{
			System.out.println("An error was triggered, stated as:  " + e.getMessage());
		}		
	}
}