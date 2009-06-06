package control.command;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import control.AbstractCommand;
import control.ControlException;

public class BuildModelCommand extends AbstractCommand {

	@Override
	protected void doCommand(HttpServletRequest request,
			HttpServletResponse response) throws ControlException {
		
//		Collection<Projeto> projetos = ProjetoFacade.getInstance().obterProjetos();
//        if (projetos != null) {
//            request.setAttribute("projetos", projetos);
//        }
//
//        super.forward("jsp/projeto/lista_projetos.jsp", request, response);
	}

	@Override
	protected String getBasicURL() {
		return "build-model.cmd";
	}

}
