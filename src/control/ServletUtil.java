package control;

import java.io.IOException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class ServletUtil {

	public static void forward(String path, HttpServletRequest request,
			HttpServletResponse response) throws ControlException {

		RequestDispatcher requestDispatcher = request
				.getRequestDispatcher(path);

		try {
			requestDispatcher.forward(request, response);
		} catch (ServletException e) {
			throw new ControlException(e);
		} catch (IOException e) {
			throw new ControlException(e);
		}
	}

	/**
	 * Gets the requestURL attribute of the ServletUtil class
	 * 
	 * @param request
	 *            Description of the Parameter
	 * @return The requestURL value
	 */
	public static String getRequestURL(HttpServletRequest request) {
		String contextPath = request.getContextPath();
		String requestURI = request.getRequestURI();
		String retorno = requestURI.substring(contextPath.length());
		if (retorno.indexOf('?') != -1)
			retorno = retorno.substring(0, retorno.indexOf('?'));
		return retorno;
	}
}
