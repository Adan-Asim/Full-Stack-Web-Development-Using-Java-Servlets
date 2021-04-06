import java.io.*;

public class Course
{
	String name;
	int id;
	
	Course(String name, int id)
	{
		this.name = name;
		this.id = id;
	}

	public String toString()
	{
		return name + id;
	}
}