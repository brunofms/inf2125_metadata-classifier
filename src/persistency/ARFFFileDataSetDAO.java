package persistency;

import java.io.File;
import java.io.FileInputStream;
import java.util.Properties;

import weka.core.Instances;
import weka.core.converters.ConverterUtils.DataSource;

public class ARFFFileDataSetDAO implements DataSetDAO {
	
	private final static ARFFFileDataSetDAO instance = new ARFFFileDataSetDAO();

	private ARFFFileDataSetDAO() {}

	public final static ARFFFileDataSetDAO getInstance() {
		return instance;
	}

	public Instances getInstances() {
		
		Instances data = null;
		
		try {
			File start_file = new File("/tmp/classifier/BUILD_MODEL.properties");

			Properties train_config = new Properties();
			train_config.load(new FileInputStream(start_file));
			
			DataSource source = new DataSource(train_config.getProperty("ARFF"));
			data = source.getDataSet();
		} catch (Exception e) {
			e.printStackTrace();
		}

		return data;
	}

}
