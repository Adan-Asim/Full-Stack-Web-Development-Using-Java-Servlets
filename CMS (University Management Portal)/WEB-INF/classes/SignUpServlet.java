import java.sql.*;
import javax.servlet.*;
import javax.servlet.http.*;
import java.io.*;

public class SignUpServlet extends HttpServlet
{
	public void doPost (HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException
	{
		PrintWriter out =  response.getWriter();
		DAO object = new DAO();		

		out.println("<html> ");
		out.println("	 <body style = \"background-image:url('bg.png'); background-size:cover; color: white; padding-left:8%;margin-top:12%\">");
		
		if(Integer.parseInt(request.getParameter("user_type")) ==  1)
		{
			Student s = new Student (request.getParameter("email"),request.getParameter("nm"),request.getParameter("dob"),Integer.parseInt(request.getParameter("semester")),request.getParameter("password"),request.getParameter("phone_no"));
			if(object.addStudent(s))
			{
				out.println("		<h1>Account Created Successfully</h1>");
				out.println("		<br><br><a href = \"1-SignIn.html\" style = \"text-decoration:none; font-size:110%; color: white\">>Click here to proceed for  SignIn </a>");
			}
			else	
			{
				out.println("		<h1>Email already exists or an unknown error occured</h1>");
				out.println("		<br><br><a href = \"SignUp.html\" style = \"text-decoration:none; font-size:110%; color: white\">> Click here to try SignUp again </a>");
			}
		}

		else if(Integer.parseInt(request.getParameter("user_type")) == 2)
		{			
			Teacher t = new Teacher(request.getParameter("email"),request.getParameter("nm"),request.getParameter("password"),request.getParameter("phone_no"));
			if(object.addTeacher(t))
			{
				out.println("		<h1>Account Created Successfully</h1>");
				out.println("		<br><br><a href = \"1-SignIn.html\" style = \"text-decoration:none; font-size:110%; color: white\">>Click here to proceed for  SignIn </a>");
			}
			else	
			{
				out.println("		<h1>Email already exists or an unknown error occured</h1>");
				out.println("		<br><br><a href = \"SignUp.html\" style = \"text-decoration:none; font-size:110%; color: white\">> Click here to try SignUp again </a>");
			}
		}
		else 
		{
			out.println("		<h1>An unknown error occured</h1>");
			out.println("		<br><br><a href = \"SignUp.html\" style = \"text-decoration:none; font-size:110%; color: white\">>  Click here to try SignUp again </a>");
		}	
		out.println("	</body>");
		out.println("</html>");
	}
}