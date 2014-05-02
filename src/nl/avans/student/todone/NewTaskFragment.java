package nl.avans.student.todone;

import nl.avans.student.todone.models.Task;
import nl.avans.student.todone.models.TaskFactory;
import android.app.Activity;
import android.app.AlertDialog;
import android.app.Dialog;
import android.app.DialogFragment;
import android.content.DialogInterface;
import android.os.AsyncTask;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.widget.TextView;

/**
 * A simple {@link android.support.v4.app.Fragment} subclass. Activities that
 * contain this fragment must implement the
 * {@link NewTaskFragment.OnFragmentInteractionListener} interface to handle
 * interaction events. Use the {@link NewTaskFragment#newInstance} factory
 * method to create an instance of this fragment.
 * 
 */

public class NewTaskFragment extends DialogFragment
{
	private OnSubmittedListener listener;
	
	public void Attach(OnSubmittedListener listener)
	{
		this.listener = listener;
	}
	
	public void Detach()
	{
		listener = null;
	}
	
	@Override
	public Dialog onCreateDialog(Bundle savedInstanceState)
	{
		AlertDialog.Builder builder = new AlertDialog.Builder(getActivity());


		LayoutInflater inflater = getActivity().getLayoutInflater();

		builder.setView(inflater.inflate(R.layout.fragment_new_task, null))
				.setPositiveButton(android.R.string.ok,
						new DialogInterface.OnClickListener()
						{
							@Override
							public void onClick(DialogInterface dialog, int id)
							{
								
								String taskName = ((TextView)getDialog().findViewById(R.id.taskName)).getText().toString();
								String taskDesc = ((TextView)getDialog().findViewById(R.id.taskDesc)).getText().toString();
								
								Task task = new Task(taskName);

								task.setDescription(taskDesc);
								task.setDone(false);
								
								NewTaskFragment.this.listener.onSubmitted(task);
								
								NewTaskFragment.this.Detach();
								
								new AsyncTask<Task, Void, Task>()
								{

									@Override
									protected Task doInBackground(Task... arg0)
									{
										return TaskFactory.createOne(arg0[0]);
									}
								}.execute(task);

							}
						})
				.setNegativeButton(android.R.string.cancel,
						new DialogInterface.OnClickListener()
						{
							public void onClick(DialogInterface dialog, int id)
							{
								NewTaskFragment.this.Detach();
								NewTaskFragment.this.getDialog().cancel();
							}
						});
		return builder.create();
	}

}
