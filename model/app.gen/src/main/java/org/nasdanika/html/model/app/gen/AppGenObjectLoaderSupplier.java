package org.nasdanika.html.model.app.gen;

import java.io.File;
import java.util.Collection;
import java.util.Collections;
import java.util.Objects;

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
public class AppGenObjectLoaderSupplier extends AppGenObjectLoaderExecutionParticipant implements Supplier<EObject> {

	private URI uri;
	
	public AppGenObjectLoaderSupplier(URI uri, Context context) {
		super(context);
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
	 * Loads EObject using {@link AppGenObjectLoaderSupplier}
	 * @param resource
	 * @param diagnosticConsumer
	 * @param context
	 * @param progressMonitor
	 * @return
	 */
	@SuppressWarnings("resource")
	public static EObject loadObject(
			String resource, 
			java.util.function.Consumer<org.nasdanika.common.Diagnostic> diagnosticConsumer,
			Context context,
			ProgressMonitor progressMonitor) {
		
		URI resourceURI = URI.createFileURI(new File(resource).getAbsolutePath());

		// Diagnosing loaded resources. 
		try {
			return Objects.requireNonNull(new AppGenObjectLoaderSupplier(resourceURI, context).call(progressMonitor, diagnosticConsumer), "Loaded null from: " + resource);
		} catch (DiagnosticException e) {
			System.err.println("******************************");
			System.err.println("*      Diagnostic failed     *");
			System.err.println("******************************");
			e.getDiagnostic().dump(System.err, 4, Status.FAIL);
			throw e;
		}		
	}	

}
