package agent.modeller.strategy;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.Random;
import java.util.Vector;

import persistency.DAOFactory;

import weka.core.Instances;
import weka.core.RevisionHandler;
import weka.filters.Filter;
import weka.filters.unsupervised.attribute.StringToWordVector;
import weka.classifiers.Evaluation;
import weka.classifiers.bayes.NaiveBayes;
import weka.classifiers.meta.MultiClassClassifier;
import weka.core.SerializationHelper;

public class NaiveBayesStrategy implements ModellerAlgorithm {

	// TODO: Apply logics
	public void classify() {
		
 		System.out.println("Building classification model: Naive Bayes");
 		
 		try {
 			
 			this.lockModelling();
 			
 			Instances data = DAOFactory.getDataSetDAO().getInstances();
			
			String[] filterOptions = weka.core.Utils.splitOptions(
				"-R first-last -W 1000 -prune-rate -1.0 -N 0 -stemmer weka.core.stemmers.NullStemmer -M 1 -tokenizer \"weka.core.tokenizers.WordTokenizer -delimiters ' \\r\\n\\t.,;:\\\'\\\"()?!'\"");
			// new instance of filter
			StringToWordVector stringToVector = new StringToWordVector();
			// set options
			stringToVector.setOptions(filterOptions);
			// inform filter about data set **AFTER** setting options
			stringToVector.setInputFormat(data);
			// apply filter
			Instances newData = Filter.useFilter(data, stringToVector);

			if (newData.classIndex() == -1)
				newData.setClassIndex(0);

			// new instance of classifier
			NaiveBayes nb = new NaiveBayes();

			Evaluation eval = new Evaluation(newData);
			// evaluate classifier and print some statistics
			eval.crossValidateModel(nb, newData, 5, new Random(1));
			
			// keep statistics
			this.generateStatistics(eval);
			
			nb.buildClassifier(newData);

			// serialize model
			// TODO: ModelDAO
			this.serializeModel(nb, newData);

			// keep statistics
			this.generateStatistics(eval);

			this.unlockModelling();

			System.out.println("Finished building model: Naive Bayes");

		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	private void serializeModel(NaiveBayes nb, Instances data) {
		
		try {
			Vector v = new Vector();
		    v.add(nb);
		    v.add(new Instances(data, 0));
			SerializationHelper.write("/tmp/classifier/NaiveBayes.model", v);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	private void generateStatistics(Evaluation eval) {
	
		try {
			
	        BufferedWriter out = new BufferedWriter(
	        		new FileWriter("/tmp/classifier/NaiveBayes.model.stats"));
	        
	        out.write("# Detailed statistics\n");
	        out.write(eval.toSummaryString() + "\n");
	        out.write(eval.toClassDetailsString() + "\n");
	        out.write(eval.toMatrixString() + "\n");
	        
	        out.close();
	        
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	private void lockModelling() {
		
		try {
            File file = new File("/tmp/classifier/NaiveBayes.model.lock");
            file.createNewFile();
        } catch (IOException e) {
            e.printStackTrace();
        }

	}
	
	private void unlockModelling() {
		
		File file = new File("/tmp/classifier/NaiveBayes.model.lock");
		file.delete();
	}
}
