package control.command;

import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import control.AbstractCommand;
import control.ControlException;

public class EnableModelBuildingCommand extends AbstractCommand {

	@Override
	protected void doCommand(HttpServletRequest request,
			HttpServletResponse response) throws ControlException {

		try {
	        BufferedWriter out = new BufferedWriter(
	        		new FileWriter("/tmp/classifier/BUILD_MODEL.properties"));
	        
	        out.write("# ARFF file path\n");
	        out.write("ARFF=" + request.getParameter("dataset_path") + "\n");
	        
	        out.write("# Classification Algorithm\n");
	        out.write("ALGORITHM=" + request.getParameter("rad_algo") + "\n");
	        
	        out.close();
	    } catch (IOException e) {
	    	AbstractCommand.addError(request, "Error trying to save file.");
	        e.printStackTrace();
	    }

		super.forward("jsp/model_building_enabled.jsp", request, response);
	}

	@Override
	protected String getBasicURL() {
		return "enable-model-building.cmd";
	}

}
