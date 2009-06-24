package model;

public class DataSet {
	
	private String arff_file_path;
	
	public void setPath (String path) {
		this.arff_file_path = path;
	}
	
	public String getPath () {
		return this.arff_file_path;
	}
	
}
