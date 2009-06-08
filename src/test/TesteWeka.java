import java.util.GregorianCalendar;
import java.util.Random;

import weka.core.converters.ConverterUtils.DataSource;
import weka.core.Instances;
import weka.filters.Filter;
import weka.filters.unsupervised.attribute.StringToWordVector;
import weka.classifiers.Evaluation;
import weka.classifiers.bayes.NaiveBayes;


public class TesteWeka {

	public static void main(String[] args) throws Exception {
		
		System.out.println("TesteWeka.main - Carregando dados - " + 
				new GregorianCalendar().getTime());
		
		DataSource source = new DataSource(
				"/Users/brunofms/Dropbox/ML2/1000_parsed_dataset_ml2.arff");
				//"/Users/brunofms/Dropbox/ML2/10000_parsed_dataset_ml2.arff");
				//"/Users/brunofms/Dropbox/ML2/parsed_dataset_ml2.arff");
		
		Instances data = source.getDataSet();

		System.out.println("TesteWeka.main - Aplicando filtragem no dataset - " + 
				new GregorianCalendar().getTime());
		
		String[] filterOptions = weka.core.Utils.splitOptions(
				"-R first-last -W 1000 -prune-rate -1.0 -N 0 -stemmer weka.core.stemmers.NullStemmer -M 1 -tokenizer \"weka.core.tokenizers.WordTokenizer -delimiters ' \\r\\n\\t.,;:\\\'\\\"()?!'\"");
		StringToWordVector stringToVector = new StringToWordVector();	// new instance of filter
		stringToVector.setOptions(filterOptions);						// set options
		stringToVector.setInputFormat(data);							// inform filter about dataset **AFTER** setting options
		Instances newData = Filter.useFilter(data, stringToVector);		// apply filter
		
		if (newData.classIndex() == -1)
			newData.setClassIndex(0);
		
		System.out.println("TesteWeka.main - Construindo o classificador - " + 
				new GregorianCalendar().getTime());
		
		NaiveBayes nb = new NaiveBayes();	// new instance of nb
		
		System.out.println("TesteWeka.main - Avaliando - " + 
				new GregorianCalendar().getTime());
		
		Evaluation eval = new Evaluation(newData);
		// evaluate classifier and print some statistics
		eval.crossValidateModel(nb, newData, 5, new Random(1));
		
		System.out.println(eval.toSummaryString());
		//System.out.println("\tInstancias corretamente classificadas: "+ eval.correct());
		//System.out.println("\tPercentual de instancias corretamente classificadas: "+ eval.pctCorrect());
		
		System.out.println("TesteWeka.main - Fim da execução - " + 
				new GregorianCalendar().getTime());
	}
}
