import java.sql.*;
import javax.servlet.*;
import javax.servlet.http.*;
import java.io.*;
import java.util.*;


public class DAO
{
         Statement st;

	
	DAO()
	{
	      try
	      {
		Class.forName("com.mysql.cj.jdbc.Driver");
		Connection con = DriverManager.getConnection("jdbc:mysql://127.0.0.1/lab14", "root", "");
		st = con.createStatement();			
	       }
	      catch(Exception e)
	      {
		System.out.println("An error was triggered, stated as:  " + e.getMessage());
	      }
	}


	public boolean checkEmailExists(String email)
	{
	      try
	      {		
		String Query = ("SELECT * FROM authentication WHERE email = '" + email + "'");
		ResultSet rs = st.executeQuery(Query);	
		if(!rs.next())	
			return false;
		return true;	
	       }			
	      catch(Exception e)
	      {
		System.out.println("An error was triggered, stated as:  " + e.getMessage());
		return false;
	      }	
	}

	public boolean checkCourseExists(Course c)
	{
	      try
	      {		
		String Query = ("SELECT * FROM course WHERE name = '" + c.name + "' and course_id = '" + c.id+"'" );
		ResultSet rs = st.executeQuery(Query);	
		if(!rs.next())	
			return false;
		return true;	
	       }			
	      catch(Exception e)
	      {
		System.out.println("An error was triggered, stated as:  " + e.getMessage());
		return false;
	      }	
	}

	public int loginAuthentication(String email,String password)
	{
	      try
	      {
		String Query = ("SELECT * FROM authentication WHERE email = '" + email + "' and password ='" + password + "'");
		ResultSet rs = st.executeQuery(Query);	
		if(rs.next())	
			return rs.getInt("user_type");
		return 0;
	       }
	      catch(Exception e)
	      {
		System.out.println("An error was triggered, stated as:  " + e.getMessage());
		return 0;
	      }
	}

	public boolean addTeacher(Teacher t)
	{
	      try
	      {
		if(checkEmailExists(t.email))
		{
			return false;
		}
		String Query1 = "INSERT INTO authentication (email,password,user_type) VALUES ('" + t.email + "','" + t.password + "',"+ t.user_type + ");";
		String Query2 = "INSERT INTO profile (email, name, phone_no) VALUES ('" + t.email + "','" + t.name + "','" + t.phone_no + "');";
		int n1 = st.executeUpdate(Query1);
		int n2 = st.executeUpdate(Query2);
	
		if (n1>0 && n2>0)
		{
			return true;
		}
		return false;
	       }
	      catch(Exception e)
	      {
		System.out.println("An error was triggered, stated as:  " + e.getMessage());
		return false;
	      }
	}

	public boolean addStudent(Student s)	
	{	
	      try
	      {
		if(checkEmailExists(s.email))
		{
			return false;
		}
		String Query1 = "INSERT INTO authentication (email,password,user_type) VALUES ('" +s.email+ "','" +s.password+ "'," +s.user_type+ ");";
		String Query2 = "INSERT INTO profile (email, name, dob, semester, phone_no) VALUES ('" +s.email+ "','" +s.name+ "','" +s.dob+ "'," +s.semester+ ",'" +s.phone_no+ "');";
		int n1 = st.executeUpdate(Query1);
		int n2 = st.executeUpdate(Query2);
	
		if (n1>0 && n2>0)
		{
			return true;
		}
		return false;
	       }
	      catch(Exception e)
	      {
		System.out.println("An error was triggered, stated as:  " + e.getMessage());
		return false;
	      }
	}

	public Student getStudentDetails(String email)
	{
	      try
	      {
		String Query = ("SELECT * FROM profile WHERE email = '" + email + "'");
		ResultSet rs = st.executeQuery(Query);	
		if(rs.next())
		{
			Student s = new Student(rs.getString("email"), rs.getString("name"), rs.getString("dob"), rs.getInt("semester"),null, rs.getString("phone_no"));	
			return s;
		}
		return null;	
  	      }
	      catch(Exception e)
	      {
		System.out.println("An error was triggered, stated as:  " + e.getMessage());
		return null;
	      }
	}

	public ArrayList<Course> getStudentCourse(Student s)
	{
		ArrayList<Course> AL = new ArrayList<Course> ();
	      try
	      {
		String Query = ("SELECT * FROM course WHERE semester = '" + s.semester + "'");
		ResultSet rs = st.executeQuery(Query);	
		if(!rs.next())
		{
			return AL;
		}
		do
		{
			Course c = new Course(rs.getString("name"),rs.getInt("course_id"));
			AL.add(c);
		}while(rs.next());
		return AL;
  	      }
	      catch(Exception e)
	      {
		System.out.println("An error was triggered, stated as:  " + e.getMessage());
		return null;
	      }
	}

	public ArrayList<Course> getTeacherCourse(String email)
	{
		ArrayList<Course> AL = new ArrayList<Course> ();
	      try
	      {
		String Query = ("SELECT * FROM course_assignment, course WHERE course_assignment.email = '" + email + "' and course_assignment.course_id =  course.course_id" );
		ResultSet rs = st.executeQuery(Query);	
		if(!rs.next())
		{
			return AL;
		}
		do
		{
			Course c = new Course(rs.getString("name"),rs.getInt("course_id"));
			AL.add(c);
		}while(rs.next());
		return AL;
  	      }
	      catch(Exception e)
	      {
		System.out.println("An error was triggered, stated as:  " + e.getMessage());
		return null;
	      }
	}		

	public boolean addCourse(Course c, String sem)	
	{	
	      try
	      {
		if(checkCourseExists(c))
		{
			return false;
		}
		String Query = "INSERT INTO course (course_id, name, semester) VALUES (" + c.id + ",'"+ c.name  + "','" + sem + "');";
		int n = st.executeUpdate(Query);
	
		if (n>0)
		{
			return true;
		}
		return false;
	       }
	      catch(Exception e)
	      {
		System.out.println("An error was triggered, stated as:  " + e.getMessage());
		return false;
	      }
	}

	public boolean assignTeacherToCourse(int id, String email)	
	{	
	      try
	      {
		String Query1 = ("SELECT * FROM course WHERE course_id = " + id + ";" );
		ResultSet rs = st.executeQuery(Query1);	
		if(!rs.next())
		{
			return false;
		}

		String Query2 = "INSERT INTO course_assignment (course_id, email) VALUES (" + id + ",'" + email+ "');";
		int n = st.executeUpdate(Query2);
	
		if (n>0)
		{
			return true;
		}
		return false;
	       }
	      catch(Exception e)
	      {
		System.out.println("An error was triggered, stated as:  " + e.getMessage());
		return false;
	      }
	}
}

