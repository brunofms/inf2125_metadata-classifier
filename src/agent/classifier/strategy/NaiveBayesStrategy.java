package agent.classifier.strategy;

import java.util.Vector;

import persistency.DAOFactory;

import weka.core.Instances;
import weka.core.RevisionHandler;
import weka.filters.Filter;
import weka.filters.unsupervised.attribute.StringToWordVector;
import weka.classifiers.bayes.NaiveBayes;
import weka.core.SerializationHelper;

public class NaiveBayesStrategy implements ClassifierAlgorithm {

	// TODO: Apply logics
	public void classify() {
		
 		System.out.println("Building classification model: Naive Bayes");
 		
 		try {
 			
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
			// train
			nb.buildClassifier(newData);
			
			//Evaluation eval = new Evaluation(newData);
			// evaluate classifier and print some statistics
			//eval.crossValidateModel(nb, newData, 5, new Random(1));
			
			// serialize model
			// TODO: ModelDAO
			Vector v = new Vector();
		    v.add(nb);
		    v.add(new Instances(newData, 0));
			SerializationHelper.write("/tmp/classifier/NB.model", v);
			
			System.out.println("Finished building model: Naive Bayes");
			
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
}
