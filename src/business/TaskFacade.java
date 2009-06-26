package business;

import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;

import model.ModelingTask;
import model.PredictionTask;

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

	public void enablePrediction(PredictionTask task) throws IOException {

		try {
			
			BufferedWriter out;
			out = new BufferedWriter(
					new FileWriter("/tmp/classifier/PREDICTION.enable"));
	        
	        out.write("# Model file path\n");
	        out.write("MODEL=" + task.getClassificationModel().getPath() + "\n");
	        
	        out.write("# Classification Algorithm\n");
	        out.write("ALGORITHM=" + task.getAlgorithm().getName() + "\n");
	        
	        out.write("# Media Metadata\n");
	        out.write("TITLE=" + task.getMetadata().get("media_title") + "\n");
	        out.write("ABSTRACT=" + task.getMetadata().get("media_abstract") + "\n");
	        out.write("LABEL=" + task.getMetadata().get("media_label") + "\n");
	        
	        out.close();
	        
		} catch (IOException e) {
			throw e;
		}		
	}
	
}
