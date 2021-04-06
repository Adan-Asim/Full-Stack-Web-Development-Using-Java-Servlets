import java.io.*;

public class Teacher
{
	String email;
	String name;
	String password;
	String phone_no;
	int user_type;
	
	Teacher(String em, String nm, String pass, String ph)
	{
		email = em;
		name = nm;
		password = pass;
		phone_no = ph;
		user_type = 2;
	}

	public String toString()
	{
		return email + name + password + phone_no + String.valueOf(user_type);
	}
}