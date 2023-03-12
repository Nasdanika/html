package org.nasdanika.html.emf;

import org.eclipse.emf.common.util.URI;
import org.eclipse.emf.ecore.EObject;
import org.nasdanika.common.Composeable;
import org.nasdanika.common.ProgressMonitor;
import org.nasdanika.drawio.Document;
import org.nasdanika.html.model.app.Action;

public interface RepresentationProcessor {
	
	default Document processDrawioRepresentation(
			Document document, 
			Action action, 
			java.util.function.Function<URI, EObject> semanticLinkResolver,
			org.nasdanika.html.emf.EObjectActionResolver.Context context, 
			ProgressMonitor progressMonitor) {
		
		return document;
	}

}
