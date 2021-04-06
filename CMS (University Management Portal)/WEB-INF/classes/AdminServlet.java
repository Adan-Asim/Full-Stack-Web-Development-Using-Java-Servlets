import java.sql.*;
import javax.servlet.*;
import javax.servlet.http.*;
import java.io.*;
import java.util.*;

public class AdminServlet extends HttpServlet
{
	public void doPost (HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException
	{
		PrintWriter out =  response.getWriter();
		DAO object = new DAO();		

		out.println("<html> ");
		out.println("	 <body style = \"background-image:url('bg.png'); background-size:cover; color: white; padding-left:8%;margin-top:8%\">");
		
		if(request.getParameter("function").equals("addcourse"))
		{
			Course c = new Course (request.getParameter("name"),Integer.valueOf(request.getParameter("id")));
			if(object.addCourse(c,request.getParameter("semester")))
			{
				out.println("		<h1>Course added successfully</h1>");
			}
			else
			{
				out.println("		<h1>Unable to add this course</h1>");
			}
		}
		else if(request.getParameter("function").equals("assignteacher"))
		{
			if(object.assignTeacherToCourse(Integer.valueOf(request.getParameter("id")),request.getParameter("email")))
			{
				out.println("		<h1>Course allocated successfully</h1>");
			}
			else
			{
				out.println("		<h1>Unable to allocate this course, make sure that the course is already created</h1>");
			}
		}	
		else
		{
			out.println("		<h1>An unexpected error occured, please try again</h1>");
		}

		out.println("		<br><br><a href = \"1-SignIn.html\" style = \"text-decoration:none; font-size:110%; color: white; \">>>  Click here to Log out </a>");
		out.println("	</body>");
		out.println("</html>");
	}
}