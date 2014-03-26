package nl.avans.student.todone;

import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.FragmentActivity;
import android.view.Menu;

import nl.avans.student.todone.R;

public class MainActivity extends FragmentActivity implements TaskListFragment.OnItemClickedListener
{

	@Override 
	protected void onCreate(Bundle savedInstanceState)
	{
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);
		
		//TaskListFragment fragment = (TaskListFragment)getSupportFragmentManager().findFragmentById(R.id.movieListFragment);
		
		//fragment.setData(tasks);
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
		/*TaskDetailFragment fragment = (TaskDetailFragment)getSupportFragmentManager().findFragmentById(R.id.movieDetailFragment);
		
		if (fragment != null && fragment.isInLayout())
			fragment.setMovie(task);
		else
		{
			Intent intent = new Intent(getApplicationContext(), DetailActivity.class);
			
			intent.putExtra("movieName", task.getName());
			intent.putExtra("movieDetail", task.getDetail());
			
			startActivity(intent);
		}*/
	}

}
