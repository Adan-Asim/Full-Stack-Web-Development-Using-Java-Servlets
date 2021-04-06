import java.sql.*;
import javax.servlet.*;
import javax.servlet.http.*;
import java.io.*;
import java.util.*;

public class MainServlet extends HttpServlet
{
	public void doPost (HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException
	{
		PrintWriter out =  response.getWriter();
		DAO object = new DAO();		

		out.println("<html> ");
		out.println("	 <body style = \"background-image:url('bg.png'); background-size:cover; color: white; padding-left:8%;margin-top:8%\">");
		
		HttpSession session = request.getSession(false);
		String user_type = (String) session.getAttribute("user_type");
		
		if(user_type.equals("1"))
		{
			Student s = object.getStudentDetails(request.getParameter("email"));
			out.println("		<h1>Students Details</h1>");
			out.println("			<h3>&nbspName: " + s.name);
			out.println("			<br>&nbspEmail: " + s.email);
			out.println("			<br>&nbspDate of Birth: " + s.dob);
			out.println("			<br>&nbspSemester: " + s.semester);
			out.println("			<br>&nbspPhone Number: " + s.phone_no);
			out.println("			</h3><br><br><h2>Courses Enrolled</h3>");
		
			ArrayList <Course> AL = object.getStudentCourse(s);
			if(AL.size() == 0)
			{
				out.println("			<br><h3>*No Course enrolled yet*</h3>");
			}
			else
			{
				for(int i = 0; i< AL.size(); i++)
				{
				out.println("			<br><h3>&nbsp Course name: " + AL.get(i).name);
				out.println("			<br>&nbsp Course Id: " + AL.get(i).id + "</h3><br>");
				}
			}
		}

		else if(user_type.equals("2"))
		{			
			ArrayList <Course> AL = object.getTeacherCourse(request.getParameter("email"));
			out.println("		<h1>Teacher Details</h1>");
			if(AL.size() == 0)
			{
				out.println("			<br><h3>*No Course Assigned Yet*</h3>");
			}
			else
			{
				for(int i = 0; i< AL.size(); i++)
				{
				out.println("			<br><h3>&nbsp Course name: " + AL.get(i).name);
				out.println("			<br>&nbsp Course Id: " + AL.get(i).id + "</h3><br>");
				}
			}
		}
		else 
		{
			out.println("		<h1 style = \"margin-bottom:5%;\">Admin Panel</h1>");
			out.println("		<a href = \"AddCourse\" style = \"text-decoration:none; font-size:110%; color:white;margin-left:2%; \"><button>Add a Course</button></a><br><br><br>");
			out.println("		<a href = \"AssignTeacher\" style = \"text-decoration:none; font-size:110%; color:white;margin-left:2%\"><button>Assign a teacher to course</button></a><br><br><br>");
		}
		
		out.println("		<br><br><a href = \"1-SignIn.html\" style = \"text-decoration:none; font-size:110%; color: white; \">>>  Click here to Log out </a>");
		out.println("	</body>");
		out.println("</html>");
	}
}