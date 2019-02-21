package org.nasdanika.cdo.http;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.eclipse.emf.cdo.CDOObject;
import org.eclipse.emf.cdo.transaction.CDOTransaction;

/**
 * {@link CDOObject}s obtained from {@link CDOTransaction} are adapted to this interface to delegate request processing to.
 * @author Pavel
 *
 */
public interface HttpRequestProcessor {

	Result process(HttpServletRequest request, HttpServletResponse response) throws Exception;
	
}
