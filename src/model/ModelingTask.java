package model;

public class ModelingTask extends Task {

	public ModelingTask() {
		super();
	}

	@Override
	public TaskType getTaskType() {
		return TaskType.MODELING;
	}
}
