package nl.avans.student.todone;

import android.content.res.Configuration;
import android.os.Bundle;
import android.support.v4.app.FragmentActivity;
import android.support.v4.app.NavUtils;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.CheckBox;
import nl.avans.student.todone.R;

public class DetailActivity extends SuperActivity
{
	private String taskName;

	@Override
	protected void onCreate(Bundle savedInstanceState)
	{
		super.onCreate(savedInstanceState);
		
		if (getResources().getConfiguration().orientation == Configuration.ORIENTATION_LANDSCAPE)
		{
			finish();
			return;
		}
		
		setContentView(R.layout.activity_detail);
		
		if (savedInstanceState != null)
			fillFromBundle(savedInstanceState);
		else
		{
			Bundle extras = getIntent().getExtras();
			fillFromBundle(extras);
		}
		
		// Show the Up button in the action bar.
		setupActionBar();
	}

	/**
	 * Set up the {@link android.app.ActionBar}.
	 */
	private void setupActionBar()
	{
		getActionBar().setDisplayHomeAsUpEnabled(true);
	}

	private void fillFromBundle(Bundle bundle)
	{
		if (bundle != null)
		{
			TaskDetailFragment fragment = (TaskDetailFragment)getSupportFragmentManager().findFragmentById(R.id.taskDetail);
			
			taskName = bundle.getString("taskName");
			
			if (fragment != null && fragment.isInLayout())
				fragment.setTaskName(taskName);
			
		}
	}
	
	@Override
	protected void onSaveInstanceState(Bundle outState)
	{
		outState.putString("taskName", taskName);
	}
	
	@Override 
	protected void onRestoreInstanceState(Bundle savedInstanceState)
	{
		fillFromBundle(savedInstanceState);
	}
	
	@Override
	public boolean onCreateOptionsMenu(Menu menu)
	{
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.detail, menu);
		return true;
	}

	@Override
	public boolean onOptionsItemSelected(MenuItem item)
	{
		switch (item.getItemId())
		{
			case android.R.id.home:
				// This ID represents the Home or Up button. In the case of this
				// activity, the Up button is shown. Use NavUtils to allow users
				// to navigate up one level in the application structure. For
				// more details, see the Navigation pattern on Android Design:
				//
				// http://developer.android.com/design/patterns/navigation.html#up-vs-back
				//
				NavUtils.navigateUpFromSameTask(this);
				return true;
		}
		return super.onOptionsItemSelected(item);
	}

}
