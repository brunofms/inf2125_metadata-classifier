package control.command;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import control.AbstractCommand;
import control.ControlException;

public class ModelingCommand extends AbstractCommand {

	@Override
	protected void doCommand(HttpServletRequest request,
			HttpServletResponse response) throws ControlException {

		// TODO: Check available features

		super.forward("jsp/build_model.jsp", request, response);
	}

	@Override
	protected String getBasicURL() {
		return "build-model.cmd";
	}

}
