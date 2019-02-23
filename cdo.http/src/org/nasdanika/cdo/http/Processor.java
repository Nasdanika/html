package org.nasdanika.cdo.http;

import java.util.function.Function;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.eclipse.emf.cdo.CDOObject;
import org.eclipse.emf.cdo.transaction.CDOTransaction;

/**
 * {@link CDOObject}s obtained from {@link CDOTransaction} are adapted to this interface to delegate request processing to.
 * @author Pavel
 *
 */
public interface Processor {

	/**
	 * Processes a request
	 * @param request Request.
	 * @param response Response.
	 * @param pathVariables Path variables from upstream processors.
	 * @return
	 * @throws Exception
	 */
	Result process(HttpServletRequest request, HttpServletResponse response, Function<String,String> pathVariables) throws Exception;
	
}
