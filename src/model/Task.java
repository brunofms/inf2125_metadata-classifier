package model;

public abstract class Task {
	
	private DataSet dataset;
	private Algorithm algorithm;
	
	public enum TaskType {MODELING, CLASSIFICATION};
	
	public void setDataSet(DataSet dataset) {
		this.dataset = dataset;
	}
	
	public DataSet getDataSet() {
		return this.dataset;
	}
	
	public void setAlgorithm(Algorithm algorithm) {
		this.algorithm = algorithm;
	}
	
	public Algorithm getAlgorithm() {
		return this.algorithm;
	}

	public abstract TaskType getTaskType();
}
