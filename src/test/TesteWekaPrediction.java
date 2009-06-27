import java.io.BufferedWriter;
import java.io.FileWriter;
import java.util.GregorianCalendar;
import java.util.Random;
import java.util.Vector;

import weka.core.converters.ConverterUtils.DataSource;
import weka.core.Attribute;
import weka.core.Instances;
import weka.core.Instance;
import weka.core.SerializationHelper;
import weka.filters.Filter;
import weka.filters.unsupervised.attribute.StringToWordVector;
import weka.classifiers.Evaluation;
import weka.classifiers.bayes.NaiveBayes;


public class TesteWekaPrediction {

	public static void main(String[] args) throws Exception {

		BufferedWriter out = new BufferedWriter(
			new FileWriter("/tmp/classifier/predict.arff"));

		out.write("% Dataset metadados - editoria para trabalho final da disciplina Aprendizado de M‡quina 2 %\n\n");
		out.write("@relation metadadosEditoriaGV\n\n");
		out.write("@attribute titulo string\n");
		out.write("@attribute legenda string\n");
		out.write("@attribute descricao string\n");
		out.write("@attribute editoria {\"Not’cias\",\"Esportes\",\"Entretenimento\",\"Adulto\"}\n\n");
		out.write("@data\n");
		out.write("\"Duelo Ramires x Hernanes esquenta confronto entre Cruzeiro e S‹o Paulo\" \"Duelo Ramires x Hernanes esquenta confronto entre Cruzeiro e S‹o Paulo\" \"Revela›es do futebol brasileiro se enfrentam nesta quarta-feira pelas quartas de final da Taa Libertadores.\" ?\n");
		out.write("\"Sexo na ilha - Parte 2\" \"Sexo na ilha\" \"O cen‡rio: uma ilha virgem e paradis’aca. O objetivo: fazer sexo selvagem com mulheres dispostas a tudo. O resultado: uma ilha de onde voc nunca mais vai querer sair.\" ?");

		out.close();

		System.out.println("DEBUG: fullfilling source");

		DataSource source = new DataSource("/tmp/classifier/predict.arff");

		System.out.println("DEBUG: fullfilling instances");

		Instances data = source.getDataSet();

		System.out.println("DEBUG: filtering");

		String[] filterOptions = weka.core.Utils.splitOptions(
			"-R first-last -W 1000 -prune-rate -1.0 -N 0 -stemmer weka.core.stemmers.NullStemmer -M 1 -tokenizer \"weka.core.tokenizers.WordTokenizer -delimiters ' \\r\\n\\t.,;:\\\'\\\"()?!'\"");
		StringToWordVector stringToVector = new StringToWordVector();	// new instance of filter
		stringToVector.setOptions(filterOptions);						// set options
		stringToVector.setInputFormat(data);							// inform filter about dataset **AFTER** setting options
		Instances newData = Filter.useFilter(data, stringToVector);		// apply filter

		if (newData.classIndex() == -1)
			newData.setClassIndex(0);

		System.out.println("DEBUG: read model and header");
		
		// read model and header
		Vector v = (Vector) SerializationHelper.read("/tmp/classifier/NaiveBayes.model");
		NaiveBayes nb = (NaiveBayes) v.get(0);
		Instances header = (Instances) v.get(1);

		// output predictions
		System.out.println("actual -> predicted");
		for (int i = 0; i < newData.numInstances(); i++) {
			
			System.out.println("DEBUG: i = " + i);
			
			Instance curr = newData.instance(i);
			
			// create an instance for the classifier that fits the training data
			// Instances object returned here might differ slightly from the one
			// used during training the classifier, e.g., different order of
			// nominal values, different number of attributes.
			Instance inst = new Instance(header.numAttributes());
			inst.setDataset(header);
			
			for (int n = 0; n < header.numAttributes(); n++) {
				
				System.out.println("DEBUG: n = " + n);
				
				Attribute att = newData.attribute(header.attribute(n).name());
				
				// original attribute is also present in the current dataset
				if (att != null) {
					System.out.println("DEBUG: att != null");
					
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
			System.out.println(inst.classValue() + " -> " + pred);
			System.out.println(pred + " -> " + header.classAttribute().value((int) pred));
		}

		System.out.println("Predicting finished!");
	}
}
