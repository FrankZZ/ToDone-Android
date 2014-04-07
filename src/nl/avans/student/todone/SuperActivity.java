package nl.avans.student.todone;

import android.support.v4.app.FragmentActivity;
import android.view.View;
import android.widget.CheckBox;

public class SuperActivity extends FragmentActivity
{
	public void onCheckboxClicked(View view)
	{
		boolean checked = ((CheckBox) view).isChecked();
		
		if (checked) 
			((CheckBox)view).setText("Deze taak is gedaan.");
		else
			((CheckBox)view).setText("Deze taak is nog niet gedaan.");
	}
	
}
