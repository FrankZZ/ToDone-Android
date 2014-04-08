package nl.avans.student.todone;

import nl.avans.student.todone.models.Task;

public interface OnSubmittedListener
{
	void onSubmitted(Task task);
}