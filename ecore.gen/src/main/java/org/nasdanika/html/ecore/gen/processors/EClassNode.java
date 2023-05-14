package org.nasdanika.html.ecore.gen.processors;

import java.util.function.Function;

import org.eclipse.emf.ecore.EClass;
import org.eclipse.emf.ecore.EGenericType;
import org.eclipse.emf.ecore.EObject;
import org.eclipse.emf.ecore.EOperation;
import org.eclipse.emf.ecore.EParameter;
import org.nasdanika.graph.emf.EObjectNode;
import org.nasdanika.graph.emf.EOperationConnection;
import org.nasdanika.graph.emf.EReferenceConnection;

public class EClassNode extends EObjectNode {

	public EClassNode(
			EClass target, 
			Function<EObject, ResultRecord> nodeFactory, 
			EReferenceConnection.Factory referenceConnectionFactory,
			EOperationConnection.Factory operationConnectionFactory,
			ReifiedTypeConnection.Factory reifiedTypeConnectionFactory) {
		super(target, nodeFactory, referenceConnectionFactory, operationConnectionFactory);

		if (reifiedTypeConnectionFactory != null) {
			for (EOperation eOperation: target.getEAllOperations()) {
				reifiedTypeConnectionFactory.create(this, eOperation.getEGenericType(), nodeFactory);
				for (EParameter eParameter: eOperation.getEParameters()) {
					reifiedTypeConnectionFactory.create(this, eParameter.getEGenericType(), nodeFactory);					
				}
				for (EGenericType ge: eOperation.getEGenericExceptions()) {
					reifiedTypeConnectionFactory.create(this, ge, nodeFactory);										
				}
			}		
		}		
	}

	@Override
	public EClass getTarget() {
		return (EClass) super.getTarget();
	}	
	
}
