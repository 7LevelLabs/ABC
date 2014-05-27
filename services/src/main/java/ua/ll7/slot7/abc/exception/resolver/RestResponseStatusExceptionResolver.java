package ua.ll7.slot7.abc.exception.resolver;

import org.springframework.stereotype.Component;
import org.springframework.transaction.CannotCreateTransactionException;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.handler.AbstractHandlerExceptionResolver;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.ws.rs.core.HttpHeaders;
import java.io.IOException;

/**
 * @author Alex Velichko
 *         27.05.14 : 14:57
 */
@Component
public class RestResponseStatusExceptionResolver extends AbstractHandlerExceptionResolver {
	@Override
	protected ModelAndView doResolveException(HttpServletRequest request,
							HttpServletResponse response,
							Object handler,
							Exception ex) {

		try {
			if (ex instanceof IllegalArgumentException) {
				return handleIllegalArgument((IllegalArgumentException) ex, response, handler);
			}
			if (ex instanceof CannotCreateTransactionException) {
				return handleCannotCreateTransactionException((CannotCreateTransactionException) ex, response, handler);
			}

		} catch (Exception handlerException) {
			logger.warn("Handling of [" + ex.getClass().getName() + "] resulted in Exception", handlerException);
		}

		return null;
	}

	private ModelAndView handleCannotCreateTransactionException(CannotCreateTransactionException ex,
									    HttpServletResponse response,
									    Object handler) throws IOException {

		response.sendError(HttpServletResponse.SC_INTERNAL_SERVER_ERROR);
		response.setStatus(HttpServletResponse.SC_INTERNAL_SERVER_ERROR);

		String accept = response.getHeader(HttpHeaders.ACCEPT);

		//TODO create response

		return new ModelAndView();
	}

	private ModelAndView handleIllegalArgument(IllegalArgumentException ex,
							 HttpServletResponse response,
							 Object handler) throws IOException {
		response.sendError(HttpServletResponse.SC_CONFLICT);
		response.setStatus(HttpServletResponse.SC_CONFLICT);

		String accept = response.getHeader(HttpHeaders.ACCEPT);

		//TODO create response

		return new ModelAndView();
	}
}
