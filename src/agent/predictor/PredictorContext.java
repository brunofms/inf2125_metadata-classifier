package agent.predictor;

import agent.predictor.strategy.PredictorAlgorithm;

public class PredictorContext {
	private PredictorAlgorithm predictor = null;

	public void classifyInstances() {
		predictor.predict();
	}

	public PredictorAlgorithm getClassifier() {
		return predictor;
	}

	public void setClassifier(PredictorAlgorithm predictor) {
		this.predictor = predictor;
	}
}
