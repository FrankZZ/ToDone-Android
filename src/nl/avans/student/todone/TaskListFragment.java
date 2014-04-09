package nl.avans.student.todone;

import java.util.ArrayList;

import nl.avans.student.todone.models.Task;
import nl.avans.student.todone.models.TaskFactory;
import android.app.FragmentTransaction;
import android.app.ListFragment;
import android.content.Intent;
import android.os.AsyncTask;
import android.os.Bundle;
import android.view.ContextMenu;
import android.view.ContextMenu.ContextMenuInfo;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView.AdapterContextMenuInfo;
import android.widget.ListView;
/**
 * A simple {@link android.support.v4.app.Fragment} subclass. Activities that
 * contain this fragment must implement the
 * {@link TaskListFragment.OnFragmentInteractionListener} interface to handle
 * interaction events.
 * 
 */
public class TaskListFragment extends ListFragment implements OnSubmittedListener
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

		registerForContextMenu(getListView());
		
		this.refreshData();
		
		if (savedInstanceState != null)
		{
			currentId = savedInstanceState.getInt("taskId");
		}
		
		if (dualPane)
		{
			showDetails(currentId);
		}
	}

	public void refreshData()
	{
		
		new TasksLoader().execute();
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
	public void onCreateContextMenu(ContextMenu menu, View v, ContextMenuInfo menuInfo)
	{
		super.onCreateContextMenu(menu, v, menuInfo);
		
		TaskListAdapter adapter = (TaskListAdapter) getListAdapter();
		AdapterContextMenuInfo info = (AdapterContextMenuInfo)menuInfo;
		Task task = adapter.getItem(info.position);
		
		menu.setHeaderTitle("Task opties - " + task.getName());
		
		menu.add(0, 0, 0, "Open item");
		menu.add(0, 0, 0, "Delete item");
		menu.add(0, 0, 0, "Toggle gedaan");
	}
	
	@Override
	public boolean onContextItemSelected(MenuItem item) 
	{
		AdapterContextMenuInfo info = (AdapterContextMenuInfo) item.getMenuInfo();
		
		int rowId = (int) info.id;
		TaskListAdapter adapter = (TaskListAdapter) getListAdapter();
		Task task = adapter.getItem(rowId);
		
		if (item.getTitle() == "Open item")
		{
			showDetails(task.getId());
		}
		else if (item.getTitle() == "Delete item")
		{
			adapter.remove(task);
			
			new AsyncTask<Task, Void, Void>()
			{

				@Override
				protected Void doInBackground(Task... arg0)
				{
					TaskFactory.deleteOne(arg0[0]);
					if (dualPane && currentId == arg0[0].getId())
					{
						FragmentTransaction ft = getFragmentManager().beginTransaction();
						ft.remove(getFragmentManager().findFragmentById(R.id.taskDetailFragment));
						ft.setTransition(FragmentTransaction.TRANSIT_FRAGMENT_FADE);
						ft.commit();
					}
					return null;
				}
			}.execute(task);
		}
		else if (item.getTitle() == "Toggle gedaan")
		{
			task.setDone(!task.getDone());
			adapter.notifyDataSetChanged();
			
			new AsyncTask<Task, Void, Void>()
			{

				@Override
				protected Void doInBackground(Task... arg0)
				{
					TaskFactory.saveOne(arg0[0]);
					
					if (dualPane && currentId == arg0[0].getId())
						TaskListFragment.this.showDetails(arg0[0].getId());
					
					return null;
				}
			}.execute(task);
		}
		
		return super.onContextItemSelected(item);
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

	@Override
	public void onSubmitted(Task task)
	{
		TaskListAdapter adapter = (TaskListAdapter)getListAdapter();
		adapter.add(task);
	}


}
