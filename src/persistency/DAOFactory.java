package persistency;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.Properties;

public class DAOFactory {

	public static DataSetDAO getDataSetDAO () {
		
		System.out.println("Retrieving data set instances...");
		
		DataSetDAO dao = null;
		
		try {
			
			// Check for customizations
			File product_file = new File("/tmp/classifier/PRODUCT_LINE.properties");
			// Product Line variability
			Properties product_config = new Properties();
			product_config.load(new FileInputStream(product_file));
			
			// TRAIN_DATA_SOURCE=ARFFFile
			String data_source = 
				product_config.getProperty("TRAIN_DATA_SOURCE");
			
			Class t = Class.forName("persistency." + data_source + "DataSetDAO");
			dao = (DataSetDAO) t.newInstance();

		} catch (FileNotFoundException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
		} catch (InstantiationException e) {
			e.printStackTrace();
		} catch (IllegalAccessException e) {
			e.printStackTrace();
		}
		
		return dao;
	}
}
