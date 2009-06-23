package persistency;

public class DAOFactory {

	private final static DAOFactory instance = new DAOFactory();

	private DAOFactory() {}

	public final static DAOFactory getInstance() {
		return instance;
	}

	public DataSetDAO getDataSetDAO () {
		
		System.out.println("Retrieving data set instances");
		
		return ARFFFileDataSetDAO.getInstance();
	}
}
