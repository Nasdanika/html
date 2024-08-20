package org.nasdanika.html.model.app.gen.maven;

import java.io.File;
import java.util.Collection;
import java.util.function.Consumer;

import org.apache.maven.plugins.annotations.LifecyclePhase;
import org.apache.maven.plugins.annotations.Mojo;
import org.apache.maven.plugins.annotations.Parameter;
import org.eclipse.emf.common.util.URI;
import org.eclipse.emf.ecore.resource.Resource;
import org.eclipse.emf.ecore.resource.ResourceSet;
import org.eclipse.emf.ecore.resource.impl.ResourceSetImpl;
import org.eclipse.emf.ecore.xmi.impl.XMIResourceFactoryImpl;
import org.nasdanika.capability.CapabilityLoader;
import org.nasdanika.capability.CapabilityProvider;
import org.nasdanika.capability.ServiceCapabilityFactory;
import org.nasdanika.capability.ServiceCapabilityFactory.Requirement;
import org.nasdanika.common.Context;
import org.nasdanika.common.Diagnostic;
import org.nasdanika.common.ProgressMonitor;
import org.nasdanika.common.Supplier;
import org.nasdanika.drawio.Document;
import org.nasdanika.html.model.app.Action;
import org.nasdanika.html.model.app.AppFactory;
import org.nasdanika.html.model.app.Label;
import org.nasdanika.html.model.app.graph.drawio.DrawioActionGenerator;
import org.nasdanika.maven.AbstractCommandMojo;

/**

/**
 * Generates an action model from a Drawio diagram
 */
/**
 * Generates action site.
 */
@Mojo(name = "generate-diagram-actions", defaultPhase = LifecyclePhase.PRE_SITE)
public class DrawioActionGeneratorMojo extends AbstractCommandMojo {
		
	/**
	 * URL of the Drawio viewer script for rendering Drawio diagrams.
	 * Set if you are hosting your own Drawio site.
	 */
	@Parameter	
	private String drawioViewer;	    
	
	/**
	 * URI or file path of the diagram to load. 
	 * URI is resolved relative to the project base directory.
	 */
	@Parameter(required = true)	
	protected String diagram;
	
	/**
	 * Output file for the action model.
	 */
	@Parameter(required = true)	
	protected File output;	
	
	/**
	 * If true, diagram parameter is treated as a file path
	 */
	@Parameter(required = false)	
	protected boolean file = false;	
	
	protected ResourceSet createResourceSet(ProgressMonitor progressMonitor) {
		ResourceSet resourceSet = new ResourceSetImpl();
		resourceSet.getResourceFactoryRegistry().getExtensionToFactoryMap().put("*", new XMIResourceFactoryImpl());
		return resourceSet;
	}
	
	@Override
	protected void execute(Context context, ProgressMonitor progressMonitor) {		
		try {
			Document document;
			if (file) {
				document = Document.load(new File(project.getBasedir(), diagram).getCanonicalFile());
			} else {
				URI uri = URI.createURI(diagram);
				URI base = URI.createFileURI(project.getBasedir().getCanonicalPath()).appendSegment("");
				document = Document.load(uri.resolve(base));
			}
			DrawioActionGenerator actionGenerator = new DrawioActionGenerator();
			Supplier<Collection<Label>> labelSupplier = actionGenerator.createLabelsSupplier(document, progressMonitor);
			Consumer<Diagnostic> diagnosticConsumer = d -> d.dump(System.out, 0);
			Collection<Label> labels = labelSupplier.call(progressMonitor, diagnosticConsumer);
			
			Action rootAction = AppFactory.eINSTANCE.createAction();
			rootAction.setText("Nasdanika");		
			rootAction.getChildren().addAll(labels);
			
			ResourceSet resourceSet = createResourceSet(progressMonitor);			
			String outputPath = this.output.getCanonicalPath();
			System.out.println(outputPath);
			Resource output = resourceSet.createResource(URI.createFileURI(outputPath));
			output.getContents().addAll(labels);
			output.save(null);
		} catch (Exception e) {
			getLog().error(e);
		}
	}	

}
