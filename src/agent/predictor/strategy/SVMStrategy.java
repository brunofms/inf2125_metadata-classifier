package agent.predictor.strategy;

public class SVMStrategy implements PredictorAlgorithm {
	
	private String dataset_path;
	
	public void setDataSetPath (String dataset_path) {
		this.dataset_path = dataset_path;
	}
	
	// TODO: Implementar l—gica
	public void predict() {
 		System.out.println("Predicting: SVM");
	}
}
