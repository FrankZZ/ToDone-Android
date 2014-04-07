package nl.avans.student.todone.models;

import org.json.JSONException;
import org.json.JSONObject;

public class Task
{
	private String name;
	private int id;
	private String description;
	private Boolean done;
	
	public Task(String name)
	{
		this.name = name;
	}
	
	public Task(JSONObject taskObject) throws JSONException
	{
		this.setId(taskObject.getInt("ID"));
		this.setName(taskObject.getString("task"));
		this.setDescription(taskObject.getString("description"));
		this.setDone(taskObject.getBoolean("status"));
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
	
	public int getId()
	{
		return this.id;
	}
	
	public void setId(int id)
	{
		this.id = id;
	}
}
