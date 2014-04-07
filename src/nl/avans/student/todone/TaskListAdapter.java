package nl.avans.student.todone;

import java.util.ArrayList;

import nl.avans.student.todone.models.Task;
import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

public class TaskListAdapter extends ArrayAdapter<Task>
{
	private final Context context;
	private final ArrayList<Task> values;
	
	public TaskListAdapter(Context context, ArrayList<Task> tasks)
	{
		super(context, android.R.layout.simple_list_item_2, tasks);
		
		this.values = tasks;
		this.context = context;
	}
	
	@Override
	public View getView(int position, View convertView, ViewGroup parent)
	{
		LayoutInflater inflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
		
		View rowView = inflater.inflate(android.R.layout.simple_list_item_2,  parent, false);
		
		TextView text1 = (TextView) rowView.findViewById(android.R.id.text1);
		TextView text2 = (TextView) rowView.findViewById(android.R.id.text2);

		Task task = values.get(position);
		
		text1.setText(task.getName());
		
		String status = task.getDone() ? "Gedaan" : "Nog niet gedaan";
		text2.setText(status);
		
		rowView.setId(task.getId());
		
		return rowView;
		
	}

}
