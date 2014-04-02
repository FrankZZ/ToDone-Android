package nl.avans.student.todone;

import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;

import nl.avans.student.todone.R;

public class MainActivity extends SuperActivity implements TaskListFragment.OnItemClickedListener
{

	@Override 
	protected void onCreate(Bundle savedInstanceState)
	{
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);
		
		Task[] tasks = new Task[5];
		
		tasks[0] = new Task("Hond uitlaten");
		tasks[1] = new Task("Auto wassen");
		tasks[2] = new Task("Github repository aanmaken");
		tasks[3] = new Task("Afval buitenzetten");
		tasks[4] = new Task("Krant rondbrengen");
		
		TaskListFragment fragment = (TaskListFragment)getSupportFragmentManager().findFragmentById(R.id.taskListFragment);
		
		fragment.setData(tasks);
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

}
