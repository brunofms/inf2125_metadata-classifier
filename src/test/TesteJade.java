// java -cp /opt/app/JADE-all-3.6.1/JADE-bin-3.6.1/jade/lib/jade.jar:. jade.Boot Bruno:TesteJade

import jade.core.Agent;
import jade.core.behaviours.*;

public class TesteJade extends Agent
{
	protected void setup()
	{
		addBehaviour( new ClassifierBehavior( this ) );
	}
}

class ClassifierBehavior extends SimpleBehaviour 
{   
	public ClassifierBehavior(Agent a) { 
		super(a);  
	}

	public void action() 
	{
		// TODO: Adicionar Observer
		// TODO: Filtrar algoritmos
		ClassifierContext context = new ClassifierContext();
		context.setClassifier(new NaiveBayes());
		context.classifyInstances();
	}

	private boolean finished = false;
	public  boolean done() {  return finished;  }

} //End class B1

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