import java.sql.*;
import javax.servlet.*;
import javax.servlet.http.*;
import java.io.*;
import java.util.*;

public class AssignTeacher extends HttpServlet
{
	public void doGet (HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException
	{
		PrintWriter out =  response.getWriter();
		DAO object = new DAO();		

		out.println("<html> ");
		out.println("<head>	<title> Admin Assign Teacher </title><style>body{border: 2px solid white;width: 32%;height: 55%;margin-top: 11%;margin-left: 34%;background-image:url('bg.png');background-size:large;color: white;}</style>");
		out.println("<script>	function check(){if(document.getElementById(\"id\").value == \"\" || document.getElementById(\"email\").value == \"\" ){alert(\"Please fill all fields\");return false;}else{	n1 = document.getElementById('email').value.indexOf('@');n2 = document.getElementById('email').value.indexOf('.');	if((n1>=1) && ((n2-n1)>1) && (document.getElementById('email').value.length - n2 >1));else{alert(\"Email address not correct, must look like this x@x.x\");return false;}} return true; }</script></head> "); 
		out.println("<body>	<h1 style = \"text-align:center; margin-top: 6%; margin-left:5\"> Teacher Association Form</h1><hr>");
		out.println("<form action = \"AdminServlet\" method = \"POST\"><ul ><h2 style = \"text-align:center; margin-left:-10%\">Assign a Teacher to a Course</h2> &nbsp&nbsp&nbsp&nbsp&nbsp&nbsp&nbsp&nbsp");
		out.println("Course ID: <input type = \"number\" name = \"id\" id = \"id\" style = \"margin-left:9%;\"><br>&nbsp&nbsp&nbsp&nbsp&nbsp&nbsp&nbsp&nbsp");
		out.println("Teacher's Email:  <input type = \"text\" name = \"email\" id = \"email\" style = \"margin-top:3%;margin-left:0%\">");
		out.println(" <input type = \"hidden\" name = \"function\" id = \"function\" value =\"assignteacher\"></ul>");	
		out.println("<input type = \"submit\" value = \"Submit\" onClick = \"return check()\" style = \"margin-left:44%; margin-top:3%\"></form>");
		out.println("		<br><br><a href = \"1-SignIn.html\" style = \"text-decoration:none; font-size:110%; color: white; margin-left:29%;\">>>  Click here to Log out </a>");
		out.println("	</body>");
		out.println("</html>");
	}

}