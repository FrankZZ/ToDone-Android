package nl.avans.student.todone;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

public class TaskListAdapter extends ArrayAdapter<Task>
{
	private final Context context;
	private final Task[] values;
	
	public TaskListAdapter(Context context, Task[] values)
	{
		super(context, android.R.layout.simple_list_item_2, values);
		
		this.values = values;
		this.context = context;
	}
	
	@Override
	public View getView(int position, View convertView, ViewGroup parent)
	{
		LayoutInflater inflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
		
		View rowView = inflater.inflate(android.R.layout.simple_list_item_2,  parent, false);
		
		/*TextView text1 = (TextView) rowView.findViewById(android.R.id.text1);
		TextView text2 = (TextView) rowView.findViewById(android.R.id.text2);
		
		text1.setText(values[position].getName());
		text2.setText(values[position].getGenre());*/
		
		return rowView;
		
	}

}
