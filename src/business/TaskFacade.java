package business;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileReader;
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

	        this.waitModelling(task.getAlgorithm().getName());
	        
	        this.retrieveModellingResults(task);

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

	        this.waitPredicting(task.getAlgorithm().getName());

	        this.retrievePredictionResults(task);
	        
		} catch (IOException e) {
			throw e;
		}		
	}
	
	private void waitModelling(String algorithm) {
		
		try {
			Thread.sleep(1000);
		
			File wait_file = new File("/tmp/classifier/" + algorithm + ".model.lock");
		
			while (wait_file.exists()) {
			
				Thread.sleep(1000);
			}
		} catch (InterruptedException e) {
				e.printStackTrace();
		}
	}
	
	private void waitPredicting(String algorithm) {
		
		try {
			Thread.sleep(1000);

			File predict_file = new File("/tmp/classifier/" + algorithm + ".predict.lock");

			while (predict_file.exists()) {
			
				Thread.sleep(1000);
			}
		} catch (InterruptedException e) {
				e.printStackTrace();
		}
	}
	
	private void retrieveModellingResults(ModelingTask task) {
		
		String results = "";
		String stat_file = "/tmp/classifier/" + task.getAlgorithm().getName() + ".model.stats";

		try {
	        BufferedReader in = new BufferedReader(
	        		new FileReader(stat_file));
	        
	        String input = "";
	        while ((input = in.readLine()) != null) {
	        	results += input + '\n';
	        }
	        
	        in.close();       
	    } catch (IOException e) {
	    }

		task.setResults(results);
	}
	
	private void retrievePredictionResults(PredictionTask task) {
		
		String results = "";
		String predict_file = "/tmp/classifier/" + task.getAlgorithm().getName() + ".predict.stats";

		try {
	        BufferedReader in = new BufferedReader(
	        		new FileReader(predict_file));
	        
	        String input = "";
	        while ((input = in.readLine()) != null) {
	        	results += input + '\n';
	        }
	        
	        in.close();       
	    } catch (IOException e) {
	    }

		task.setResults(results);
	}
}
