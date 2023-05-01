package org.nasdanika.html.ecore.doc;

import java.util.function.Consumer;

import org.eclipse.emf.common.util.URI;
import org.nasdanika.common.Context;
import org.nasdanika.common.ProgressMonitor;
import org.nasdanika.html.model.app.Action;
import org.nasdanika.html.model.app.util.AppObjectLoaderSupplier;
import org.nasdanika.html.model.app.util.DocLoader;

/**
 * Loads documentation actions from classpath resources
 * @author Pavel
 *
 */
public class EcoreDocLoader extends DocLoader {
		
	public EcoreDocLoader(
			Consumer<org.nasdanika.common.Diagnostic> diagnosticConsumer,
			Context context,
			ProgressMonitor progressMonitor) {		

		prototypes.put(URI.createURI(
				"http://www.eclipse.org/emf/2002/Ecore"), 
				(Action) AppObjectLoaderSupplier.loadObject(URI.createURI("classpath://org/nasdanika/html/ecore/doc/package-summary.yml"), diagnosticConsumer, context, progressMonitor));
		
		prototypes.put(URI.createURI(
				"http://www.eclipse.org/emf/2002/Ecore/eClassifiers/EClass"), 
				(Action) AppObjectLoaderSupplier.loadObject(URI.createURI("classpath://org/nasdanika/html/ecore/doc/EClass.yml"), diagnosticConsumer, context, progressMonitor));
		
	}

}
