// javac TesteStrategy.java 
// java TesteStrategy

public class TesteStrategy {
	public static void main(String[] args) {
		ClassifierContext context = new ClassifierContext();
		context.setClassifier(new NaiveBayes());
		context.classifyInstances();
	}
}

interface Classifier {
	public void classify();
}

class NaiveBayes implements Classifier {
	public void classify() {
 		System.out.println("Classifying instances: Naive Bayes");
	}
}

class J48 implements Classifier {
	public void classify() {
 		System.out.println("Classifying instances: J48");
	}
}

class ClassifierContext {
	private Classifier classifier = null;

	public void classifyInstances() {
		classifier.classify();
	}

	public Classifier getClassifier() {
		return classifier;
	}

	public void setClassifier(Classifier classifier) {
		this.classifier = classifier;
	}
}