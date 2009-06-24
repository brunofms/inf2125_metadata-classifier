package model;

import model.Task.TaskType;

public class ClassificationTask extends Task {
	
	private Model model;
	
	public ClassificationTask() {
		super();
	}

	@Override
	public TaskType getTaskType() {
		return TaskType.CLASSIFICATION;
	}

}
