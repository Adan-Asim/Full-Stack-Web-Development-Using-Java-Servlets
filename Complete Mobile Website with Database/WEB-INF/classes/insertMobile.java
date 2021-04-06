import java.sql.*;
import java.io.*;
import javax.servlet.http.*;
import javax.servlet.*;

public class insertMobile extends HttpServlet
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
			String price = request.getParameter("price");
		
			String query = "insert into mobileoutlet(companyName,model,price) values('" + name  + "', '" + model + "', '" + price + "')";
			int n = st.executeUpdate(query);
			if(n>0)
			{
				out.println("<html> ");
				out.println("	 <body>");
				out.println("			<meta http-equiv=\"refresh\" content=\"1; url=1-MainPage.html\" >"); 				
				out.println("		<script> ");
				out.println("		{");
				out.println("			alert(\"Database Successfully Updated\");");
				out.println("		}");
				out.println("		</script>");
				out.println("	</body>");
				out.println("</html>");
			}
			else
			{
				out.println("<html> ");
				out.println("	 <body>");
				out.println("				<meta http-equiv=\"refresh\" content=\"1; url=1-MainPage.html\" >");	
				out.println("		<script> ");
				out.println("		{");
				out.println("				alert(\"Unable to update Database\");");
				out.println("		}");
				out.println("		</script>");
				out.println("	</body>");
				out.println("</html>");
			}
		con.close();
		}
		catch(Exception e)
		{
			System.out.println("An error was triggered, stated as:  " + e.getMessage());
		}		
	}
}