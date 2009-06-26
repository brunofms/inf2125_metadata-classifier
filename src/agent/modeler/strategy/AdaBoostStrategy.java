package agent.modeler.strategy;

public class AdaBoostStrategy implements ClassifierAlgorithm {
	
	private String dataset_path;
	
	public void setDataSetPath (String dataset_path) {
		this.dataset_path = dataset_path;
	}
	
	// TODO: Implementar l—gica
	public void classify() {
 		System.out.println("Building classification model: AdaBoost");
	}
}
