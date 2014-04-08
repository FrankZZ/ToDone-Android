package nl.avans.student.todone;

import nl.avans.student.todone.models.Task;
import nl.avans.student.todone.models.TaskFactory;
import android.app.Fragment;
import android.os.AsyncTask;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.CheckBox;
import android.widget.CompoundButton;
import android.widget.CompoundButton.OnCheckedChangeListener;
import android.widget.TextView;

/**
 * A simple {@link android.support.v4.app.Fragment} subclass. Activities that
 * contain this fragment must implement the
 * {@link TaskDetailFragment.OnFragmentInteractionListener} interface to handle
 * interaction events.
 * 
 */
public class TaskDetailFragment extends Fragment
{
	
	public static TaskDetailFragment newInstance(int taskId)
	{
		TaskDetailFragment f = new TaskDetailFragment();
		
		Bundle args = new Bundle();
		args.putInt("taskId", taskId);
		f.setArguments(args);
		
		return f;
	}
	
	public int getTaskId()
	{
		return getArguments().getInt("taskId", 0);
	}
	
	@Override
	public View onCreateView(LayoutInflater inflater, ViewGroup container,
			Bundle savedInstanceState)
	{
		if (container == null)
			return null;
		
		
		// Inflate the layout for this fragment
		return inflater.inflate(R.layout.fragment_task_detail, container, false);
	}
	
	public void onActivityCreated(Bundle savedInstanceState)
	{ 
		super.onActivityCreated(savedInstanceState);
		
		if (getView() != null)
		{
			new TaskLoader().execute(this.getTaskId());
		}
	}
	
	public void setTask(Task task)
	{
		View view = getView();
		if (view != null)
		{
			
			TextView titleTextView = (TextView)view.findViewById(R.id.titleTextView);
			titleTextView.setText(task.getName());
			
			TextView detailTextView = (TextView)view.findViewById(R.id.detailTextView);
			detailTextView.setText(task.getDescription());
			
			TextView doneText = (TextView)view.findViewById(R.id.doneTextView);
			
			String status = task.getDone() ? "Gedaan" : "Nog niet gedaan";
			doneText.setText(status);
			
			//checkBox.setOnCheckedChangeListener(checkBoxChangeListener);
		}
	}
	
	protected class TaskLoader extends AsyncTask<Integer, Void, Task>
	{

		@Override
		protected Task doInBackground(Integer... arg0)
		{
			return TaskFactory.getOne(arg0[0]);
		}
		
		protected void onPostExecute(Task task)
		{
			Log.i("TasksResult", "Loaded task " + task.getId() + " from API.");
			
			setTask(task);
		}
		
	}

	/*public OnCheckedChangeListener checkBoxChangeListener = new OnCheckedChangeListener()
	{

		@Override
		public void onCheckedChanged(CompoundButton buttonView,
				boolean isChecked)
		{
			CheckBox checkBox = (CheckBox)buttonView;
			
			String status = checkBox.isChecked() ? "Gedaan" : "Nog niet gedaan";
			checkBox.setText(status);
		}
		
	};*/
	
}
