package agent.classifier;

import jade.core.Agent;

public class ClassifierAgent extends Agent {
	
	private static final long serialVersionUID = 2628364618512203443L;

	protected void setup()
	{
		addBehaviour( new ClassifierBehavior( this ) );
	}
}