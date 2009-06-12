package agent.classifier;

import agent.classifier.strategy.NaiveBayesStrategy;

import jade.core.Agent;
import jade.core.behaviours.*;

public class ClassifierBehavior extends SimpleBehaviour {  
	
	private static final long serialVersionUID = 2602197650095065371L;
	
	public ClassifierBehavior(Agent a) { 
		super(a);  
	}

	public void action() 
	{
		// TODO: Adicionar Observer
		// TODO: Filtrar algoritmos
		ClassifierContext context = new ClassifierContext();
		context.setClassifier(new NaiveBayesStrategy());
		context.classifyInstances();
	}

	private boolean finished = false;
	public  boolean done() {  return finished;  }
}