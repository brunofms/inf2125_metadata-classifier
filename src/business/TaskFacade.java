package business;

import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;

import model.ModelingTask;

public class TaskFacade {

	private final static TaskFacade instance = new TaskFacade();

	private TaskFacade() {}

	public final static TaskFacade getInstance() {
		return instance;
	}

	public void enableModeling(ModelingTask task) throws IOException {
		
		try {
			
	        BufferedWriter out = new BufferedWriter(
	        		new FileWriter("/tmp/classifier/MODELING.enable"));
	        
	        out.write("# ARFF file path\n");
	        out.write("ARFF=" + task.getDataSet().getPath() + "\n");
	        
	        out.write("# Classification Algorithm\n");
	        out.write("ALGORITHM=" + task.getAlgorithm().getName() + "\n");
	        
	        out.close();

	    } catch (IOException e) {
	    	throw e;
	    }

	}
	
}
