package nl.avans.student.todone;

import android.content.Intent;
import android.content.SharedPreferences;
import android.content.SharedPreferences.OnSharedPreferenceChangeListener;
import android.os.Bundle;
import android.preference.Preference;
import android.preference.PreferenceManager;
import android.support.v4.app.FragmentActivity;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.TextView;

public class MainActivity extends FragmentActivity
{
	private OnSharedPreferenceChangeListener prefsListener;
	
	@Override
	protected void onCreate(Bundle savedInstanceState)
	{
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);

		SharedPreferences sharedPref = PreferenceManager
				.getDefaultSharedPreferences(this);
		String namePref = sharedPref.getString(SettingsActivity.PREF_NAME_KEY,
				"");

		TextView greeting = (TextView) findViewById(R.id.textGreeting);
		greeting.setText("Hallo, " + namePref);
		
		if (prefsListener == null)
		{
			prefsListener = new OnSharedPreferenceChangeListener()
			{
				public void onSharedPreferenceChanged(SharedPreferences sharedPreferences, String key) {
		
			        if (key.equals(SettingsActivity.PREF_NAME_KEY)) 
			        {
			        	MainActivity.this.syncNamePref();
			        }
				}
		    };
		}
	    sharedPref.registerOnSharedPreferenceChangeListener(prefsListener);
	}

	private void syncNamePref()
	{
		SharedPreferences sharedPref = PreferenceManager.getDefaultSharedPreferences(this);
	    
	    String namePref = sharedPref.getString(SettingsActivity.PREF_NAME_KEY, "");
		
		TextView greeting = (TextView) findViewById(R.id.textGreeting);
		greeting.setText("Hallo, " + namePref);
	}
	
	@Override
	public void onResume() 
	{
	    super.onResume();
	    this.syncNamePref();
	    
	    PreferenceManager.getDefaultSharedPreferences(this)
	            .registerOnSharedPreferenceChangeListener(this.prefsListener);
	}

	@Override
	public void onPause() 
	{
	    super.onPause();
	    PreferenceManager.getDefaultSharedPreferences(this)
	            .unregisterOnSharedPreferenceChangeListener(this.prefsListener);
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

				TaskListFragment fragment = (TaskListFragment) getFragmentManager()
						.findFragmentById(R.id.taskListFragment);

				taskFragment.Attach(fragment);

				return true;
			}
			case R.id.action_settings:
			{
				Intent intent = new Intent(this, SettingsActivity.class);
				startActivity(intent);

				return true;
			}
			case R.id.action_refresh:
			{
				TaskListFragment fragment = (TaskListFragment) getFragmentManager()
						.findFragmentById(R.id.taskListFragment);
				fragment.refreshData();
				return true;
			}
			default:
				return super.onOptionsItemSelected(item);
		}
	}
}
