package nl.avans.student.todone;

import android.app.Activity;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemClickListener;
import android.widget.ListView;

import nl.avans.student.todone.R;

/**
 * A simple {@link android.support.v4.app.Fragment} subclass. Activities that
 * contain this fragment must implement the
 * {@link TaskListFragment.OnFragmentInteractionListener} interface to handle
 * interaction events.
 * 
 */
public class TaskListFragment extends Fragment
{

	private OnItemClickedListener mListener;
	private View view;
	
	public TaskListFragment()
	{
		// Required empty public constructor

	}

	@Override
	public View onCreateView(LayoutInflater inflater, ViewGroup container,
			Bundle savedInstanceState)
	{
		super.onCreateView(inflater, container, savedInstanceState);
		view = inflater.inflate(R.layout.fragment_task_list, container, false);
		
		// Inflate the layout for this fragment
		return view;
	}

	@Override
	public void onAttach(Activity activity)
	{
		super.onAttach(activity);
		try
		{
			mListener = (OnItemClickedListener) activity;
		}
		catch (ClassCastException e)
		{
			throw new ClassCastException(activity.toString()
					+ " must implement OnItemSelectedListener");
		}
	}

	@Override
	public void onDetach()
	{
		super.onDetach();
		mListener = null;
	}
	
	public void setData(final Task[] tasks)
	{
		
		ListView listView = (ListView)view.findViewById(R.id.taskList);
		
		TaskListAdapter adapter = new TaskListAdapter(getActivity(), tasks);
		
		listView.setAdapter(adapter);
		
		listView.setOnItemClickListener(new OnItemClickListener()
		{
			@Override
			public void onItemClick(AdapterView<?> parent, View view, int position, long id)
			{
				mListener.onItemClicked(tasks[position]);
			}
		});
	}

	/**
	 * This interface must be implemented by activities that contain this
	 * fragment to allow an interaction in this fragment to be communicated to
	 * the activity and potentially other fragments contained in that activity.
	 * <p>
	 * See the Android Training lesson <a href=
	 * "http://developer.android.com/training/basics/fragments/communicating.html"
	 * >Communicating with Other Fragments</a> for more information.
	 */
	public interface OnItemClickedListener
	{
		public void onItemClicked(Task task);
	}

}
