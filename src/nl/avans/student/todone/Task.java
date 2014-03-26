package nl.avans.student.todone;

public class Task
{
	private String name;
	
	public Task(String name)
	{
		this.name = name;
	}
	
	public String getName()
	{
		return this.name;
	}
	
	@Override
	public String toString()
	{
		return this.name;
	}
}
