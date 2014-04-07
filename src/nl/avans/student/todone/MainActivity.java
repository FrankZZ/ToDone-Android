package nl.avans.student.todone;

import java.util.ArrayList;

import nl.avans.student.todone.models.Task;
import nl.avans.student.todone.models.TaskFactory;
import android.content.Intent;
import android.os.AsyncTask;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;

public class MainActivity extends SuperActivity implements TaskListFragment.OnItemClickedListener
{

	@Override 
	protected void onCreate(Bundle savedInstanceState)
	{
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);
		
		new TaskLoader().execute();
	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu)
	{
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.main, menu);
		return true;
	}

	@Override
	public void onItemClicked(Task task)
	{
		TaskDetailFragment fragment = (TaskDetailFragment)getSupportFragmentManager().findFragmentById(R.id.taskDetailFragment);
		
		if (fragment != null && fragment.isInLayout())
			fragment.setTask(task);
		else
		{
			Intent intent = new Intent(getApplicationContext(), DetailActivity.class);
			
			intent.putExtra("taskName", task.getName());
			
			startActivity(intent);
		}
	}
	
	private class TaskLoader extends AsyncTask<Void, Void, ArrayList<Task>>
	{

		@Override
		protected ArrayList<Task> doInBackground(Void... arg0)
		{
			return TaskFactory.all();
		}
		
		protected void onPostExecute(ArrayList<Task> tasks)
		{
			Log.i("TasksResult", "" + tasks.size());
			
			TaskListFragment fragment = (TaskListFragment)getSupportFragmentManager().findFragmentById(R.id.taskListFragment);
			
			fragment.setData(tasks);
		}
		
	}

}
