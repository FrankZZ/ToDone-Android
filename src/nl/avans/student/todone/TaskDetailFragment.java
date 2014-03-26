package nl.avans.student.todone;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import nl.avans.student.todone.R;

/**
 * A simple {@link android.support.v4.app.Fragment} subclass. Activities that
 * contain this fragment must implement the
 * {@link TaskDetailFragment.OnFragmentInteractionListener} interface to handle
 * interaction events.
 * 
 */
public class TaskDetailFragment extends Fragment
{
		
	public TaskDetailFragment()
	{
		// Required empty public constructor
		
	}

	@Override
	public View onCreateView(LayoutInflater inflater, ViewGroup container,
			Bundle savedInstanceState)
	{
		// Inflate the layout for this fragment
		return inflater.inflate(R.layout.fragment_task_detail, container,
				false);
	}
	
	/*
	public void setMovie(Task task)
	{
		setMovieNameAndDetail(task.getName(), task.getDetail());
	}
	
	public void setMovieNameAndDetail(String name, String detail)
	{
		
		TextView detailTextView = (TextView)getView().findViewById(R.id.detailTextView);
		TextView titleTextView = (TextView)getView().findViewById(R.id.titleTextView);
		
		titleTextView.setText(name);
		detailTextView.setText(detail);
	}
	*/
}