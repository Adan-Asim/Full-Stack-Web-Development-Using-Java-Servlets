import java.sql.*;
import javax.servlet.*;
import javax.servlet.http.*;
import java.io.*;

public class SignInServlet extends HttpServlet
{
	public void doPost (HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException
	{
		PrintWriter out =  response.getWriter();
		DAO object = new DAO();		

		out.println("<html> ");
		out.println("	 <body style = \"background-image:url('bg.png'); background-size:cover; color: white; padding-left:8%;margin-top:12%\">");
		
		int user_type = object.loginAuthentication(request.getParameter("email"), request.getParameter("password"));
		if( user_type != 0)
		{
			HttpSession s = request.getSession();
			s.setAttribute("user_type", String.valueOf(user_type));
	
			RequestDispatcher rd = request.getRequestDispatcher("/MainServlet");				
			rd.forward(request,response);		
		}
		
		else	
		{
			out.println("		<h1>Wrong combination of email and password </h1>");
			out.println("		<br><br><a href = \"1-SignIn.html\" style = \"text-decoration:none; font-size:110%; color: white\">> Click here to try SignIn again </a>");
		}
	
		out.println("	</body>");
		out.println("</html>");
	}
}