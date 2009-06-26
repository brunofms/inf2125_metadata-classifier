package control.command;

import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import business.TaskFacade;

import model.Algorithm;
import model.ClassificationModel;
import model.PredictionTask;

import control.AbstractCommand;
import control.ControlException;

public class RetrievePredictionCommand extends AbstractCommand {

	@Override
	protected void doCommand(HttpServletRequest request,
			HttpServletResponse response) throws ControlException {

		// Listar dispon’veis
		String model_path = request.getParameter("model_path");
		String algorithm_name = request.getParameter("algorithm_name");
		
		String m_title = request.getParameter("media_title");
		String m_abstract = request.getParameter("media_abstract");
		String m_label = request.getParameter("media_label");
		
		Map<String, String> metadata = new HashMap<String, String>();
		metadata.put("media_title", m_title);
		metadata.put("media_abstract", m_abstract);
		metadata.put("media_label", m_label);
		
		ClassificationModel model = new ClassificationModel();
		model.setPath(model_path);
		
		Algorithm algorithm = new Algorithm();
		algorithm.setName(algorithm_name);
		
		PredictionTask task = new PredictionTask();
		task.setClassificationModel(model);
		task.setAlgorithm(algorithm);
		task.setMetadata(metadata);
		
		try {
			TaskFacade.getInstance().enablePrediction(task);
		} catch (IOException e) {
	    	AbstractCommand.addError(request, e.getMessage());
	        e.printStackTrace();
		}

		super.forward("jsp/retrieve_prediction.jsp", request, response);
	}

	@Override
	protected String getBasicURL() {
		return "retrieve-prediction.cmd";
	}

}
