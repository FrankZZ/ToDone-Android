package nl.avans.student.todone;

import java.util.ArrayList;

import nl.avans.student.todone.models.Task;
import nl.avans.student.todone.models.TaskFactory;
import android.content.Intent;
import android.os.AsyncTask;
import android.os.Bundle;
import android.app.FragmentTransaction;
import android.app.ListFragment;
import android.util.Log;
import android.view.View;
import android.widget.ListView;
/**
 * A simple {@link android.support.v4.app.Fragment} subclass. Activities that
 * contain this fragment must implement the
 * {@link TaskListFragment.OnFragmentInteractionListener} interface to handle
 * interaction events.
 * 
 */
public class TaskListFragment extends ListFragment
{
	private boolean dualPane;
	private int currentId = 1;
	
	public TaskListFragment()
	{
		// Required empty public constructor

	}

	public void onActivityCreated(Bundle savedInstanceState)
	{
		super.onActivityCreated(savedInstanceState);
		
		View detailView = getActivity().findViewById(R.id.taskDetailFragment);
		dualPane = detailView != null && detailView.getVisibility() == View.VISIBLE;

		new TasksLoader().execute();
		
		if (savedInstanceState != null)
		{
			currentId = savedInstanceState.getInt("taskId");
		}
		
		if (dualPane)
		{
			showDetails(currentId);
		}
	}

	private void showDetails(int id)
	{
		TaskDetailFragment details = (TaskDetailFragment)getFragmentManager().findFragmentById(R.id.taskDetailFragment);
		
		currentId = id;
		
		if (dualPane)
		{
			if (details == null || details.getId() != id)
				
			{
				details = TaskDetailFragment.newInstance(id);
				
				FragmentTransaction ft = getFragmentManager().beginTransaction();
				ft.replace(R.id.taskDetailFragment, details);
				ft.setTransition(FragmentTransaction.TRANSIT_FRAGMENT_FADE);
				
				ft.commit();
			} 
		}
		else
		{
			Intent intent = new Intent();
			
			intent.setClass(getActivity(), DetailActivity.class);
			intent.putExtra("taskId", id);
			
			startActivity(intent);
		}
	}
	
	public void setData(final ArrayList<Task> tasks)
	{
		TaskListAdapter adapter = new TaskListAdapter(getActivity(), tasks);
		setListAdapter(adapter);
	}
	
	@Override 
	public void onSaveInstanceState(Bundle outState)
	{
		super.onSaveInstanceState(outState);
		outState.putInt("taskId", currentId);
	}
	
	@Override
	public void onListItemClick(ListView l, View v, int position, long id)
	{
		showDetails(v.getId());
	}
	
	private class TasksLoader extends AsyncTask<Void, Void, ArrayList<Task>>
	{

		@Override
		protected ArrayList<Task> doInBackground(Void... arg0)
		{
			return TaskFactory.getAll();
		}
		
		protected void onPostExecute(ArrayList<Task> tasks)
		{
			setData(tasks);
		}
		
	}


}
