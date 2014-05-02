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
	private OnUpdatedTaskListener listener;
	private Task task;
	
	public static TaskDetailFragment newInstance(int taskId)
	{
		TaskDetailFragment f = new TaskDetailFragment();
		
		Bundle args = new Bundle();
		args.putInt("taskId", taskId);
		f.setArguments(args);
		
		return f;
	}
	
	public void Attach(OnUpdatedTaskListener listener)
	{
		this.listener = listener;
	}
	
	public void Detach()
	{
		this.listener = null;
	}
	
	public int getTaskId()
	{
		return getArguments().getInt("taskId", -1);
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
			int taskId = this.getTaskId();
			
			if (taskId != -1)
				new TaskLoader().execute(taskId);
		}
	}
	
	public void setChooseItemText()
	{
		View view = getView();
		
		if (view != null)
		{
			TextView titleTextView = (TextView)view.findViewById(R.id.titleTextView);
			titleTextView.setText("Kies een item");
		}
		
	}
	
	public void setTask(Task task)
	{
		View view = getView();
		
		this.task = task;
		
		if (view != null)
		{
			
			TextView titleTextView = (TextView)view.findViewById(R.id.titleTextView);
			titleTextView.setText(task.getName());
			
			TextView detailTextView = (TextView)view.findViewById(R.id.detailTextView);
			detailTextView.setText(task.getDescription());
			
			CheckBox checkBox = (CheckBox)view.findViewById(R.id.doneCheckBox);
			
			String status = task.getDone() ? "Gedaan" : "Nog niet gedaan";
			checkBox.setChecked(task.getDone());
			checkBox.setText(status);
			
			checkBox.setOnCheckedChangeListener(checkBoxChangeListener);
		}
	}
	
	protected class TaskLoader extends AsyncTask<Integer, Task, Task>
	{

		@Override
		protected Task doInBackground(Integer... arg0)
		{
			return TaskFactory.getOne(arg0[0]);
		}
		
		protected void onPostExecute(Task task)
		{
			if (task == null)
				return;
			Log.i("TasksResult", "Loaded task " + task.getId() + " from API.");
			
			setTask(task);
		}
		
	}

	public OnCheckedChangeListener checkBoxChangeListener = new OnCheckedChangeListener()
	{

		@Override
		public void onCheckedChanged(CompoundButton buttonView,
				boolean isChecked)
		{
			CheckBox checkBox = (CheckBox)buttonView;
			
			String status = isChecked ? "Gedaan" : "Nog niet gedaan";
			checkBox.setText(status);
			
			task.setDone(isChecked);
			Log.i("TasksResult", "Set task done status to " + isChecked + " for task " + task.getId() + ", saving to API...");
			TaskFactory.saveOne(task);
			
			if (listener != null)
				listener.onUpdatedTask(task);
		}
		
	};
	
}
