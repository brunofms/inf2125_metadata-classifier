package control;

import java.util.Collection;
import java.util.TreeSet;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public abstract class AbstractCommand {

	protected abstract void doCommand(HttpServletRequest httpServletRequest, HttpServletResponse httpServletResponse) throws ControlException;

    protected abstract String getBasicURL();

    public final static String JSP_ERROR = "jsp/error.jsp";

    protected final static void forward(String path, HttpServletRequest request, HttpServletResponse response) throws ControlException {
        ServletUtil.forward(path, request, response);
    }
    
    protected boolean doValidation(HttpServletRequest request) {
        return true;
    }

    protected String getErrorURL() {
        return JSP_ERROR;
    }
    
    @SuppressWarnings("unchecked")
	public static synchronized void addError(HttpServletRequest request, String error) {

        if (request != null && error != null) {
            Collection<String> errors = null;
            if (request.getAttribute("errors") != null) {
                errors = (Collection<String>)request.getAttribute("errors");
            } else {
                errors = new TreeSet<String>();
                request.setAttribute("errors", errors);
            }
            errors.add(error);
        }


    }

    @SuppressWarnings("unchecked")
	public static synchronized void addMessage(HttpServletRequest request, String message) {

        if (request != null && message != null) {
            Collection<String> messages = null;
            if (request.getAttribute("messages") != null) {
                messages = (Collection<String>)request.getAttribute("messages");
            } else {
                messages = new TreeSet<String>();
                request.setAttribute("messages", messages);
            }
            messages.add(message);
        }


    }
}
