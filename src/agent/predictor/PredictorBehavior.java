package agent.predictor;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.util.Properties;

import agent.predictor.strategy.PredictorAlgorithm;

import jade.core.Agent;
import jade.core.behaviours.*;

public class PredictorBehavior extends SimpleBehaviour {  
	
	private static final long serialVersionUID = 2602197650095065371L;

	private boolean finished = false;
	
	public PredictorBehavior(Agent a) { 
		super(a);
	}

	@SuppressWarnings("unchecked")
	public void action() 
	{
		// TODO: Add Observer

		try {
			// Verify if it can start working
			File start_file = new File("/tmp/classifier/PREDICTION.enable");
			
			if (start_file.exists()) {
				System.out.println("Start command received.");
				
				// Check for customizations
				// TODO: Retirar daqui e colocar no build
				File product_file = new File("/tmp/classifier/PRODUCT_LINE.properties");
				
				// Product Line variability
				Properties product_config = new Properties();
				product_config.load(new FileInputStream(product_file));

				String predictor_algorithm = 
					product_config.getProperty("ALGORITHM");

				PredictorContext context = new PredictorContext();

				Class t = Class.forName("agent.predictor.strategy." + predictor_algorithm +"Strategy");

				context.setClassifier((PredictorAlgorithm)t.newInstance());
				context.classifyInstances();

				// Job done
				start_file.delete();
			}
		
			Thread.sleep(50);
			
		} catch (IOException e) {
			e.printStackTrace();
		} catch (InterruptedException e) {
			e.printStackTrace();
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
		} catch (InstantiationException e) {
			e.printStackTrace();
		} catch (IllegalAccessException e) {
			e.printStackTrace();
		}
	}
	
	public  boolean done() {  return finished;  }
}