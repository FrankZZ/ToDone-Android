package nl.avans.student.todone.models;

import java.util.ArrayList;

import org.json.JSONArray;
import org.json.JSONObject;
import org.json.JSONTokener;

import android.os.AsyncTask;
import android.util.Log;

public class TaskFactory
{
	public static ArrayList<Task> getAll()
	{
		RestClient rest = new RestClient(Configuration.BASE_URL + "tasks");
		ArrayList<Task> taskList = new ArrayList<Task>();
		
		try
		{
			rest.Execute(RequestMethod.GET);
			Log.d("content", rest.getResponse());
			JSONArray taskArray = (JSONArray) new JSONTokener(rest.getResponse()).nextValue();
			
			for (int i = 0; i < taskArray.length(); i++)
			{
				JSONObject taskObject = taskArray.getJSONObject(i);
				Task task = new Task(taskObject);
				
				taskList.add(task);
			}
		}
		catch (Exception e)
		{
			Log.e("Ex", e.toString());
		}
		Log.i("TasksResult", taskList.size() + " tasks loaded from API.");
		return taskList;
	}
	
	public static Task getOne(int id)
	{
		RestClient rest = new RestClient(Configuration.BASE_URL + "tasks/" + id);
		Log.d("id", id + "");
		Task task = null;
		
		try
		{
			rest.Execute(RequestMethod.GET);
			Log.d("content", rest.getResponse());
			JSONObject taskObject = (JSONObject) new JSONTokener(rest.getResponse()).nextValue();
			task = new Task(taskObject);
		}
		catch (Exception e)
		{
			Log.e("Ex", e.toString());
		}
		
		return task;
	}
	
	public static void saveOne(int id, Task task)
	{
		new AsyncTask<Task, Void, Void>()
		{

			@Override
			protected Void doInBackground(Task... arg0)
			{
				Task task = arg0[0];
				
				RestClient rest = new RestClient(Configuration.BASE_URL + "tasks/" + task.getId());
				rest.AddParam("taskName", task.getName());
				rest.AddParam("taskDesc", task.getDescription());
				rest.AddParam("taskStatus", task.getDone().toString());
				
				return null;
			}
		}.execute(task);
	}
	
	public static void createOne(Task task)
	{
		
	}
}
