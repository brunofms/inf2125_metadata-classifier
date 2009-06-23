// javac -cp /opt/app/JADE-all-3.6.1/JADE-bin-3.6.1/jade/lib/jade.jar:/opt/app/weka/weka.jar:. agent/classifier/*.java agent/classifier/strategy/*.java
// java -cp /opt/app/JADE-all-3.6.1/JADE-bin-3.6.1/jade/lib/jade.jar:. jade.Boot Bruno:agent.classifier.ClassifierAgent

package agent.classifier;

import jade.core.Agent;

public class ClassifierAgent extends Agent {
	
	private static final long serialVersionUID = 2628364618512203443L;

	protected void setup()
	{
		addBehaviour( new ClassifierBehavior( this ) );
	}
}