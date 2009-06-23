package control.command;

import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import control.AbstractCommand;
import control.ControlException;

public class RetrievePredictionCommand extends AbstractCommand {

	@Override
	protected void doCommand(HttpServletRequest request,
			HttpServletResponse response) throws ControlException {

		// TODO: Apply logics

		super.forward("jsp/retrieve_prediction.jsp", request, response);
	}

	@Override
	protected String getBasicURL() {
		return "retrieve-prediction.cmd";
	}

}
