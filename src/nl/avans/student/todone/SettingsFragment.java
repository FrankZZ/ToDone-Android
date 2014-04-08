package nl.avans.student.todone;

import android.content.SharedPreferences;
import android.content.SharedPreferences.OnSharedPreferenceChangeListener;
import android.os.Bundle;
import android.preference.Preference;
import android.preference.PreferenceFragment;

/**
 * A simple {@link android.support.v4.app.Fragment} subclass.
 * 
 */
public class SettingsFragment extends PreferenceFragment
{
	private OnSharedPreferenceChangeListener listener;
	
	public SettingsFragment()
	{
		// Required empty public constructor
	}

	@Override
	public void onCreate(Bundle savedInstanceState)
	{
		super.onCreate(savedInstanceState);
		
		addPreferencesFromResource(R.xml.preferences);
		
		SharedPreferences sharedPrefs = getPreferenceScreen().getSharedPreferences();
		String name = sharedPrefs.getString(SettingsActivity.PREF_NAME_KEY, "");
		this.setPrefNameSummary(name);
		
		listener = new OnSharedPreferenceChangeListener()
		{
			public void onSharedPreferenceChanged(SharedPreferences sharedPreferences, String key) {
	
		        if (key.equals(SettingsActivity.PREF_NAME_KEY)) 
		        {
		        	String name = sharedPreferences.getString(SettingsActivity.PREF_NAME_KEY, "");

		    		SettingsFragment.this.setPrefNameSummary(name);
		        }
			}
	    };
	    
	    sharedPrefs.registerOnSharedPreferenceChangeListener(listener);
	}
	
	@Override
	public void onResume() {
	    super.onResume();
	    getPreferenceScreen().getSharedPreferences()
	            .registerOnSharedPreferenceChangeListener(this.listener);
	}

	@Override
	public void onPause() {
	    super.onPause();
	    getPreferenceScreen().getSharedPreferences()
	            .unregisterOnSharedPreferenceChangeListener(this.listener);
	}
	
	private void setPrefNameSummary(String name)
	{
		Preference namePref = findPreference(SettingsActivity.PREF_NAME_KEY);
		
		namePref.setSummary(name);
	}

}
