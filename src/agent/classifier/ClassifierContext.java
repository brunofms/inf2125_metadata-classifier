package agent.classifier;

import agent.classifier.strategy.ClassifierAlgorithm;

public class ClassifierContext {
	private ClassifierAlgorithm classifier = null;

	public void classifyInstances() {
		classifier.classify();
	}

	public ClassifierAlgorithm getClassifier() {
		return classifier;
	}

	public void setClassifier(ClassifierAlgorithm classifier) {
		this.classifier = classifier;
	}
}
