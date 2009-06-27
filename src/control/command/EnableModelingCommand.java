package control.command;

import java.io.IOException;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import business.TaskFacade;

import model.Algorithm;
import model.DataSet;
import model.ModelingTask;

import control.AbstractCommand;
import control.ControlException;

public class EnableModelingCommand extends AbstractCommand {

	@Override
	protected void doCommand(HttpServletRequest request,
			HttpServletResponse response) throws ControlException {

		String dataset_path = request.getParameter("dataset_path");
		String algorithm_name = request.getParameter("algorithm_name");
		
		DataSet dataset = new DataSet();
		dataset.setPath(dataset_path);
		
		Algorithm algorithm = new Algorithm();
		algorithm.setName(algorithm_name);
		
		ModelingTask task = new ModelingTask();
		task.setDataSet(dataset);
		task.setAlgorithm(algorithm);
		
		try {
			TaskFacade.getInstance().enableModeling(task);
		} catch (IOException e) {
	    	AbstractCommand.addError(request, e.getMessage());
	        e.printStackTrace();
		}
		
		request.setAttribute("stat", task.getResults());

		// TODO: check modeling status
		super.forward("jsp/model_building_enabled.jsp", request, response);
	}

	@Override
	protected String getBasicURL() {
		return "enable-model-building.cmd";
	}

}
