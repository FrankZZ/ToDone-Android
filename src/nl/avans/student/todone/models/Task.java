package nl.avans.student.todone.models;

public class Task
{
	private String name;
	private String description;
	private Boolean done;
	
	public Task(String name)
	{
		this.name = name;
	}
	
	public void setName(String name)
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
	
	public String getDescription()
	{
		return this.description;
	}
	
	public void setDescription(String description)
	{
		this.description = description;
	}
	
	public Boolean getDone()
	{
		return this.done;
	}
	
	public void setDone(Boolean done)
	{
		this.done = done;
	}
	
}
