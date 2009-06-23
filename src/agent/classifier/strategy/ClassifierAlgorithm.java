package agent.classifier.strategy;

public interface ClassifierAlgorithm {
	//public enum Algorithm {NB, SVM, AB};
	public void classify();

	public void setDataSetPath(String dataset_path);
}
