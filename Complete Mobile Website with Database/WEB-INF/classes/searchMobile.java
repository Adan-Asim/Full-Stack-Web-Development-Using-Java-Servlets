import java.sql.*;
import java.io.*;
import javax.servlet.http.*;
import javax.servlet.*;

public class searchMobile extends HttpServlet
{
	public void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException
	{
		try
		{	
			PrintWriter out = response.getWriter();
			Class.forName("com.mysql.cj.jdbc.Driver");
			Connection con = DriverManager.getConnection("jdbc:mysql://127.0.0.1/mobileinfo","root", "");
			Statement st = con.createStatement();
			
			String name = request.getParameter("name");
			String model = request.getParameter("model");	
		
			String query = "SELECT * FROM mobileoutlet WHERE companyName = '" + name + "' and model = '" +  model + "'";
			ResultSet rs = st.executeQuery(query);
			
			if(!(rs.next()))	
			{
			out.println("<html> ");
			out.println("	 <body style = \"background-image:url('bg1.jpg'); background-size:cover; color: white; padding-left:8%;margin-top:12%\">");
			out.println("		<h1>No Record found against entry</h1>");
			out.println("		<br><br><a href = \"1-MainPage.html\" style = \"text-decoration:none; font-size:110%; color: white\">>  Go back to main Page </a>");
			out.println("	</body>");
			out.println("</html>");
			return;
			}	
	
			out.println("<html> ");
			out.println("	 <body style = \"background-image:url('bg1.jpg'); background-size:cover; color: white; padding-left:3%;margin-top:2%\">");
			out.println("		<a href = \"1-MainPage.html\" style = \"text-decoration:none; font-size:110%; color:white;margin-left:2.2%\"><button>Go back to main Page</button></a><br><br><br>");
			out.println("		<table style = \"border: 1px solid white\"> ");
			out.println("		<tr style = \"margin-top:2%\">");
			out.println("			<th>Company Name |</th>");
			out.println("			<th>Mode |</th>");
			out.println("			<th>Price </th>");
			out.println("		</tr>");
			
			 do
			{
			out.println("		<tr>");
			out.println("			<th>" + rs.getString("companyName") );
			out.println("			<hr><th>" + rs.getString("model") );
			out.println("			<hr><th>" + rs.getInt("price") );
			out.println("		<hr></tr>");
			}while(rs.next());
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