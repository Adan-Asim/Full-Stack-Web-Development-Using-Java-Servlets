import java.io.*;

public class Student
{
	String email;
	String name;
	int semester;
	String dob;
	String password;
	String phone_no;
	int user_type;	

	Student(String em, String nm, String d, int sem, String pass, String ph)
	{
		email = em;
		name = nm;
		semester = sem;
		dob = d;
		password = pass;
		phone_no = ph;
		user_type = 1;
	}

	public String toString()
	{
		return email + name + String.valueOf(semester) + dob + password + phone_no + String.valueOf(user_type);
	}
}