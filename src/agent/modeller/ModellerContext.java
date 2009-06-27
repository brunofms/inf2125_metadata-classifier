package agent.modeller;

import agent.modeller.strategy.ModellerAlgorithm;

public class ModellerContext {
	private ModellerAlgorithm classifier = null;

	public void classifyInstances() {
		classifier.classify();
	}

	public ModellerAlgorithm getClassifier() {
		return classifier;
	}

	public void setClassifier(ModellerAlgorithm classifier) {
		this.classifier = classifier;
	}
}
