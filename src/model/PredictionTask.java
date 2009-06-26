package model;

import java.util.Map;

public class PredictionTask extends Task {
	
	private ClassificationModel model;
	private Map<String, String> metadata;
	
	public PredictionTask() {
		super();
	}
	
	public void setClassificationModel(ClassificationModel model) {
		this.model = model;
	}
	
	public ClassificationModel getClassificationModel() {
		return this.model;
	}
	
	public void setMetadata(Map metadata) {
		this.metadata = metadata;
	}
	
	public Map getMetadata() {
		return this.metadata;
	}

	@Override
	public TaskType getTaskType() {
		return TaskType.CLASSIFICATION;
	}

}
