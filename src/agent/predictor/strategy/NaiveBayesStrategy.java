package agent.predictor.strategy;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;
import java.util.Properties;
import java.util.Vector;

import weka.core.Attribute;
import weka.core.Instance;
import weka.core.Instances;
import weka.filters.Filter;
import weka.filters.unsupervised.attribute.StringToWordVector;
import weka.classifiers.bayes.NaiveBayes;
import weka.core.SerializationHelper;
import weka.core.converters.ConverterUtils.DataSource;

public class NaiveBayesStrategy implements PredictorAlgorithm {

	// TODO: Apply logics
	public void predict() {
		
 		System.out.println("Predicting: Naive Bayes");
 		
 		try {
 			
 			this.lockPredicting();
 			
 			this.createPredictionARFFFile();

 			//System.out.println("DEBUG: fullfilling source");

 			DataSource source = new DataSource("/tmp/classifier/predict.arff");

 			//System.out.println("DEBUG: fullfilling instances");

 			Instances data = source.getDataSet();

 			//System.out.println("DEBUG: filtering");

 			String[] filterOptions = weka.core.Utils.splitOptions(
 				"-R first-last -W 1000 -prune-rate -1.0 -N 0 -stemmer weka.core.stemmers.NullStemmer -M 1 -tokenizer \"weka.core.tokenizers.WordTokenizer -delimiters ' \\r\\n\\t.,;:\\\'\\\"()?!'\"");
 			StringToWordVector stringToVector = new StringToWordVector();	// new instance of filter
 			stringToVector.setOptions(filterOptions);						// set options
 			stringToVector.setInputFormat(data);							// inform filter about dataset **AFTER** setting options
 			Instances newData = Filter.useFilter(data, stringToVector);		// apply filter

 			if (newData.classIndex() == -1)
 				newData.setClassIndex(0);

 			//System.out.println("DEBUG: read model and header");
 			
 			// read model and header
 			Vector v = (Vector) SerializationHelper.read("/tmp/classifier/NaiveBayes.model");
 			NaiveBayes nb = (NaiveBayes) v.get(0);
 			Instances header = (Instances) v.get(1);

 			// output predictions
 			//System.out.println("actual -> predicted");
 			for (int i = 0; i < newData.numInstances(); i++) {
 				
 				//System.out.println("DEBUG: i = " + i);
 				
 				Instance curr = newData.instance(i);
 				
 				// create an instance for the classifier that fits the training data
 				// Instances object returned here might differ slightly from the one
 				// used during training the classifier, e.g., different order of
 				// nominal values, different number of attributes.
 				Instance inst = new Instance(header.numAttributes());
 				inst.setDataset(header);
 				
 				for (int n = 0; n < header.numAttributes(); n++) {
 					
 					//System.out.println("DEBUG: n = " + n);
 					
 					Attribute att = newData.attribute(header.attribute(n).name());
 					
 					// original attribute is also present in the current dataset
 					if (att != null) {
 						//System.out.println("DEBUG: att != null");
 						
 						if (att.isNominal()) {
 							
 							// is this label also in the original data?
 							// Note:
 							// "numValues() > 0" is only used to avoid problems with nominal 
 							// attributes that have 0 labels, which can easily happen with
 							// data loaded from a database
 							if ((header.attribute(n).numValues() > 0) && (att.numValues() > 0)) {
 								
 								String label = curr.stringValue(att);
 								int index = header.attribute(n).indexOfValue(label);
 								if (index != -1)
 									inst.setValue(n, index);
 							}
 						}
 						else if (att.isNumeric()) {
 							inst.setValue(n, curr.value(att));
 						}
 						else {
 							throw new IllegalStateException("Unhandled attribute type!");
 						}
 					}
 				}
 				
 				// predict class
 				double pred = nb.classifyInstance(inst);
 				this.persistPrediction(header.classAttribute().value((int) pred));
 			}

 			this.unlockPredicting();

 			System.out.println("Predicting finished!");
			
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	private void createPredictionARFFFile() {
		
		try {
			String media_title, media_abstract, media_label;

			File start_file = new File("/tmp/classifier/PREDICTION.enable");

			Properties prediction_config = new Properties();

			prediction_config.load(new FileInputStream(start_file));

			media_title = prediction_config.getProperty("TITLE");
			media_abstract = prediction_config.getProperty("ABSTRACT");
			media_label = prediction_config.getProperty("LABEL");

			BufferedWriter out = new BufferedWriter(
					new FileWriter("/tmp/classifier/predict.arff"));

			out.write("% Dataset metadados - editoria para trabalho final da disciplina Aprendizado de M‡quina 2 %\n\n");
			out.write("@relation metadadosEditoriaGV\n\n");
			out.write("@attribute titulo string\n");
			out.write("@attribute legenda string\n");
			out.write("@attribute descricao string\n");
			out.write("@attribute editoria {\"Not’cias\",\"Esportes\",\"Entretenimento\",\"Adulto\"}\n\n");
			out.write("@data\n");
			out.write("\"" + media_title + "\" \"" + media_abstract + "\" \"" + media_label + "\" ?\n");

			out.close();

		} catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	private void persistPrediction(String prediction) {
		
		try {
			
	        BufferedWriter out = new BufferedWriter(
	        		new FileWriter("/tmp/classifier/NaiveBayes.predict.stats"));
	        
	        out.write("# Predicted Class\n");
	        out.write(prediction + "\n");
	        
	        out.close();
	        
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	private void lockPredicting() {
		
		try {
            File file = new File("/tmp/classifier/NaiveBayes.predict.lock");
            file.createNewFile();
        } catch (IOException e) {
            e.printStackTrace();
        }

	}
	
	private void unlockPredicting() {
		
		File file = new File("/tmp/classifier/NaiveBayes.predict.lock");
		file.delete();
	}
}
