public interface DataSetDAO {   
	public Instances getInstances();    
}

class ARFFFileDataSetDAO implements DataSetDAO {
	
}

public class DAOFactory {
	public static DataSetDAO getDataSetDAO () {
		return new ARFFFileDataSetDAO();
	}
}