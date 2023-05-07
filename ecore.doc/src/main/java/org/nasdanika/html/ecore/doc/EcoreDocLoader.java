package org.nasdanika.html.ecore.doc;

import java.util.function.Consumer;

import org.eclipse.emf.common.util.URI;
import org.eclipse.emf.ecore.EcorePackage;
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

		URI nsURI = URI.createURI(EcorePackage.eNS_URI);
		URI resourceBase = URI.createURI("classpath://" + EcoreDocLoader.class.getName().replace('.', '/'));

		prototypes.put(nsURI, (Action) AppObjectLoaderSupplier.loadObject(URI.createURI("package-summary.yml").resolve(resourceBase), diagnosticConsumer, context, progressMonitor));		
		prototypes.put(URI.createURI("eClassifiers/EClass").resolve(nsURI),	(Action) AppObjectLoaderSupplier.loadObject(URI.createURI("EClass.yml").resolve(resourceBase), diagnosticConsumer, context, progressMonitor));
		
	}

}
