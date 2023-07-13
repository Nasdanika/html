package org.nasdanika.html.model.app.util;

import java.io.File;
import java.util.Collection;
import java.util.Collections;
import java.util.Objects;
import java.util.function.Consumer;

import org.eclipse.emf.common.util.URI;
import org.eclipse.emf.ecore.EObject;
import org.nasdanika.common.Context;
import org.nasdanika.common.DiagnosticException;
import org.nasdanika.common.ProgressMonitor;
import org.nasdanika.common.Status;
import org.nasdanika.common.Supplier;

/**
 * Loads root element from a resource URI passed to constructor.
 * @author Pavel
 *
 */
public class AppObjectLoaderSupplier extends AppObjectLoaderExecutionParticipant implements Supplier<EObject> {

	private URI uri;
	
	public AppObjectLoaderSupplier(URI uri, Context context, boolean parallel) {
		super(context, parallel);
		this.uri = uri;
	}

	@Override
	public EObject execute(ProgressMonitor progressMonitor) {
		return resourceSet.getResource(uri, false).getContents().get(0);
	}

	@Override
	protected Collection<URI> getResources() {
		return Collections.singletonList(uri);
	}
	
	/**
	 * Loads object using AppObjectLoaderSupplier
	 * @param resource
	 * @param diagnosticConsumer
	 * @param context
	 * @param progressMonitor
	 * @return
	 * @throws Exception
	 */
	public static EObject loadObject(
			String resource, 
			Consumer<org.nasdanika.common.Diagnostic> diagnosticConsumer,
			Context context,
			boolean parallel,
			ProgressMonitor progressMonitor) {
		
		URI resourceURI = URI.createFileURI(new File(resource).getAbsolutePath());
		return loadObject(resourceURI, diagnosticConsumer, context, parallel, progressMonitor);
	}
		
	public static EObject loadObject(
			URI resourceURI, 
			Consumer<org.nasdanika.common.Diagnostic> diagnosticConsumer,
			Context context,
			boolean parallel,
			ProgressMonitor progressMonitor) {
				
		// Diagnosing loaded resources. 
		try {
			try (AppObjectLoaderSupplier appObjectLoaderSupplier = new AppObjectLoaderSupplier(resourceURI, context, parallel)) {
				return Objects.requireNonNull(appObjectLoaderSupplier.call(progressMonitor, diagnosticConsumer), "Loaded null from " + resourceURI);
			}
		} catch (DiagnosticException e) {
			System.err.println("******************************");
			System.err.println("*      Diagnostic failed     *");
			System.err.println("******************************");
			e.getDiagnostic().dump(System.err, 4, Status.FAIL);
			throw e;
		}		
	}
	

}
