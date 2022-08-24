package org.nasdanika.html.model.app.drawio;

import java.util.function.Predicate;

import org.eclipse.emf.ecore.EReference;
import org.nasdanika.graph.Element;
import org.nasdanika.graph.processor.ProcessorInfo;

/**
 * Handler type to invoke node and connection processors to wire elements.
 * @author Pavel
 *
 */
public interface Handler {
	
	/**
	 * Sets default connection role. Called by a node for each outgoing connection and by a connection for the target endpoint.
	 * @param connectionRole
	 * @param traversePredicate Predicate for detecting already traversed node to prevent infinite loops.
	 */
	void setDefaultConnectionRole(EReference connectionRole, Predicate<Element> traversePredicate);
	
	/**
	 * Sets semantic parent. Called by a node for each outgoing connection. If connection has a role then it calls its target.
	 * @param semanticParent
	 */
	void setSemanticParentInfo(ProcessorInfo<ElementProcessor> semanticParentInfo);

}
