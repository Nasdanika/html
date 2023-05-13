package org.nasdanika.html.ecore.gen.processors;

import java.util.function.Function;

import org.eclipse.emf.ecore.EObject;
import org.nasdanika.graph.emf.EObjectNode;
import org.nasdanika.graph.emf.EReferenceConnection.Factory;

public class EClassNode extends EObjectNode {

	public EClassNode(
			EObject target, 
			Function<EObject, ResultRecord> nodeFactory, 
			Factory referenceConnectionFactory,
			org.nasdanika.graph.emf.EOperationConnection.Factory operationConnectionFactory) {
		super(target, nodeFactory, referenceConnectionFactory, operationConnectionFactory);
		
		// TODO - Reified connections 
	}

}
