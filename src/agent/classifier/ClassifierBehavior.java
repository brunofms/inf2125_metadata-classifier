package agent.classifier;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.util.Properties;

//import agent.classifier.strategy.AdaBoostStrategy;
import agent.classifier.strategy.ClassifierAlgorithm;
//import agent.classifier.strategy.NaiveBayesStrategy;
//import agent.classifier.strategy.SVMStrategy;

import jade.core.Agent;
import jade.core.behaviours.*;

public class ClassifierBehavior extends SimpleBehaviour {  
	
	private static final long serialVersionUID = 2602197650095065371L;

	private boolean finished = false;
	
	public ClassifierBehavior(Agent a) { 
		super(a);
	}

	@SuppressWarnings("unchecked")
	public void action() 
	{
		// TODO: Add Observer

		try {
			// Verify if it can start working
			File start_file = new File("/tmp/classifier/BUILD_MODEL.properties");
			
			// Check for customizations
			File product_file = new File("/tmp/classifier/PRODUCT_LINE.properties");
			
			if (start_file.exists()) {
				System.out.println("Start command received.");
				
				Properties train_config = new Properties();
				train_config.load(new FileInputStream(start_file));
			
				String dataset_path = train_config.getProperty("ARFF");
				
				// Product Line variability
				Properties product_config = new Properties();
				String classification_algorithm = 
					product_config.getProperty("ALGORITHM");

				ClassifierContext context = new ClassifierContext();

				Class t = Class.forName("agent.classifier.strategy." + classification_algorithm +"Strategy");
				
				context.setClassifier((ClassifierAlgorithm)t.newInstance());
				context.setDataSetPath(dataset_path);
				context.classifyInstances();
				
				// Job done
				start_file.delete();
			}
			else {
				System.out.println("Waiting for start command...");
			}
		
			Thread.sleep(10000);
			
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