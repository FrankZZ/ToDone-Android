package nl.avans.student.todone;

import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.FragmentActivity;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;

public class MainActivity extends FragmentActivity
{

	@Override
	protected void onCreate(Bundle savedInstanceState)
	{
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);
	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu)
	{
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.main, menu);
		return true;
	}

	@Override
	public boolean onOptionsItemSelected(MenuItem item)
	{
		switch (item.getItemId())
		{

			case android.R.id.home:
			{
				Intent intentHome = new Intent(this, MainActivity.class);

				intentHome.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
				startActivity(intentHome);
				return true;
			}
			case R.id.action_add:
			{
				NewTaskFragment taskFragment = new NewTaskFragment();
				taskFragment.show(getFragmentManager(), null);
				Log.e("test", "aa");
				TaskListFragment fragment = (TaskListFragment)getFragmentManager().findFragmentById(R.id.taskListFragment);
				
				taskFragment.Attach(fragment);
				
				return true;
			}
			default:
				return super.onOptionsItemSelected(item);
		}
	}
}
