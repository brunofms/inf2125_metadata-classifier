package control;

import java.io.IOException;
import java.util.HashMap;
import java.util.Map;
import java.util.logging.Level;
import java.util.logging.Logger;

import javax.servlet.Servlet;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import control.command.ModelingCommand;
import control.command.ClassifyMetadataCommand;
import control.command.EnableModelingCommand;
import control.command.RetrievePredictionCommand;

public class CommandServlet extends HttpServlet implements Servlet {

	private static final long serialVersionUID = 1L;

	private static Logger logger = Logger.getLogger(CommandServlet.class
			.getName());
	private static Map<String, AbstractCommand> COMMANDS;

	public CommandServlet() {
		super();
	}

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	protected void doGet(HttpServletRequest request,
			HttpServletResponse response) throws ServletException, IOException {
		this.processRequest(request, response);
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	protected void doPost(HttpServletRequest request,
			HttpServletResponse response) throws ServletException, IOException {
		this.processRequest(request, response);
	}

	/**
	 * @see javax.servlet.GenericServlet#init()
	 */
	public void init() throws ServletException {
		try {
			this.loadCommands();
		} catch (Throwable t) {
			logger.log(Level.SEVERE, "Erro ao iniciar o servlet principal.");
		}
	}

	private void loadCommands() {
		COMMANDS = new HashMap<String, AbstractCommand>();

		AbstractCommand abstractCommand;

		abstractCommand = new ModelingCommand();
		COMMANDS.put(abstractCommand.getBasicURL(), abstractCommand);
		
		abstractCommand = new ClassifyMetadataCommand();
		COMMANDS.put(abstractCommand.getBasicURL(), abstractCommand);
		
		abstractCommand = new EnableModelingCommand();
		COMMANDS.put(abstractCommand.getBasicURL(), abstractCommand);
		
		abstractCommand = new RetrievePredictionCommand();
		COMMANDS.put(abstractCommand.getBasicURL(), abstractCommand);
	}

	public void processRequest(HttpServletRequest request,
			HttpServletResponse response) {

		boolean irParaPaginaErro = false;
		AbstractCommand abstractCommand = null;

		try {

			abstractCommand = this.getCommandByURL(ServletUtil
					.getRequestURL(request));

			if (abstractCommand != null) {
				if (abstractCommand.doValidation(request)) {
					abstractCommand.doCommand(request, response);
				} else {
					logger.log(Level.WARNING, "Dados invalidos: "
							+ ServletUtil.getRequestURL(request));
				}
			} else {
				logger.log(Level.SEVERE, "Comando nao encontrado: "
						+ ServletUtil.getRequestURL(request));
				irParaPaginaErro = true;
			}

		} catch (ControlException controleException) {
			logger.log(Level.SEVERE, "Erro no processamento do request.",
					controleException);
			irParaPaginaErro = true;

		} catch (Throwable e) {
			logger.log(Level.SEVERE, "Erro no processamento do request.", e);
			irParaPaginaErro = true;
		}

		if (irParaPaginaErro) {

			try {

				String paginaErro = AbstractCommand.JSP_ERROR;
				if (abstractCommand != null) {
					paginaErro = abstractCommand.getErrorURL();
				}
				ServletUtil.forward(paginaErro, request, response);

			} catch (ControlException e) {
				try {
					response.getWriter().write("Erro generico.");
				} catch (IOException e1) {
					logger.log(Level.SEVERE, "Erro generico.", e);
				}
			}
		}
	}

	private AbstractCommand getCommandByURL(String requestURL) {

		AbstractCommand retorno = null;

		if (COMMANDS != null && !COMMANDS.isEmpty()) {
			retorno = COMMANDS.get(requestURL.substring(1));
		}

		return retorno;
	}
}