package nl.avans.student.todone.models;

import java.util.ArrayList;

import org.json.JSONArray;
import org.json.JSONObject;
import org.json.JSONTokener;

import android.util.Log;

public class TaskFactory
{
	public static ArrayList<Task> all()
	{
		RestClient rest = new RestClient(Configuration.BASE_URL + "tasks");
		ArrayList<Task> taskList = new ArrayList<Task>();
		
		try
		{
			rest.Execute(RequestMethod.GET);
			
			JSONArray taskArray = (JSONArray) new JSONTokener(rest.getResponse()).nextValue();
			
			for (int i = 0; i < taskArray.length(); i++)
			{
				JSONObject taskObject = taskArray.getJSONObject(i);
				Task task = new Task(taskObject.getString("task"));
				
				task.setDescription(taskObject.getString("description"));
				task.setDone(taskObject.getBoolean("status"));
				
				taskList.add(task);
			}
		}
		catch (Exception e)
		{
			Log.e("Ex", e.toString());
		}
		
		return taskList;
	}
}
