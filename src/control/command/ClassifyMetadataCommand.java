package control.command;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import control.AbstractCommand;
import control.ControlException;

public class ClassifyMetadataCommand extends AbstractCommand {

	@Override
	protected void doCommand(HttpServletRequest request,
			HttpServletResponse response) throws ControlException {
		
		super.forward("jsp/classify_metadata.jsp", request, response);
	}

	@Override
	protected String getBasicURL() {
		return "classify-metadata.cmd";
	}

}