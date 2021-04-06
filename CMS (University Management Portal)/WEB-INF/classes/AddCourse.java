import java.sql.*;
import javax.servlet.*;
import javax.servlet.http.*;
import java.io.*;
import java.util.*;

public class AddCourse extends HttpServlet
{
	public void doGet (HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException
	{
		PrintWriter out =  response.getWriter();
		DAO object = new DAO();		

		out.println("<html> ");
		out.println("<head>	<title> Admin Add Course </title><style>	body{border: 2px solid white;width: 32%;height: 60%;margin-top: 8%;margin-left: 34%;background-image:url('bg.png');background-size:large;color: white;}</style>");
		out.println("<script>	function check(){if(document.getElementById(\"id\").value == \"\" || document.getElementById(\"name\").value == \"\"  || document.getElementById(\"semester\").value == \"\" ){alert(\"Please fill all fields\");return false;} return true; }</script></head> "); 
		out.println("<body>	<h1 style = \"text-align:center; margin-top: 6%; margin-left:5\"> Course Adding Form</h1><hr>");
		out.println("<form action = \"AdminServlet\" method = \"POST\"><ul ><h2 style = \"text-align:center; margin-left:-7%\">Add a course into the System</h2> &nbsp&nbsp&nbsp&nbsp&nbsp&nbsp&nbsp&nbsp");
		out.println("Course ID: <input type = \"number\" name = \"id\" id = \"id\" style = \"margin-left:14%;\"><br>&nbsp&nbsp&nbsp&nbsp&nbsp&nbsp&nbsp&nbsp");
		out.println("Course Name : <input type = \"text\" name = \"name\" id = \"name\" style = \"margin-top:3%;margin-left:8%\"><br> &nbsp&nbsp&nbsp&nbsp&nbsp&nbsp&nbsp&nbsp");
		out.println("Semester offered in:  <input type = \"number\" name = \"semester\" id = \"semester\" style = \"margin-top:3%;margin-left:-0.5%\">");
		out.println(" <input type = \"hidden\" name = \"function\" id = \"function\" value =\"addcourse\"></ul>");	
		out.println("<input type = \"submit\" value = \"Submit\" onClick = \"return check()\" style = \"margin-left:44%; margin-top:3%\"></form>");
		out.println("		<br><br><a href = \"1-SignIn.html\" style = \"text-decoration:none; font-size:110%; color: white; margin-left:30%;\">>>  Click here to Log out </a>");
		out.println("	</body>");
		out.println("</html>");
	}
}