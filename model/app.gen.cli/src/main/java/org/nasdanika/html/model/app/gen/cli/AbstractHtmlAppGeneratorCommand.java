package org.nasdanika.html.model.app.gen.cli;

import java.io.File;
import java.util.Collection;

import org.eclipse.emf.common.util.EList;
import org.eclipse.emf.common.util.URI;
import org.eclipse.emf.ecore.EObject;
import org.eclipse.emf.ecore.resource.ResourceSet;
import org.nasdanika.cli.CommandGroup;
import org.nasdanika.cli.ResourceSetMixIn;
import org.nasdanika.common.NasdanikaException;
import org.nasdanika.common.ProgressMonitor;
import org.nasdanika.common.Util;
import org.nasdanika.drawio.Document;
import org.nasdanika.html.model.app.Action;
import org.nasdanika.html.model.app.Label;
import org.nasdanika.html.model.app.util.LabelSupplier;

import picocli.CommandLine.Mixin;
import picocli.CommandLine.Option;
import picocli.CommandLine.ParentCommand;

/**
 * Base class for action supplier commands.
 * Reduces a list of {@link Label}s to an {@link Action} by either using the first and only label if it is an Action,
 * or adding the labels to a root or principal action (https://html-app.models.nasdanika.org/index.html#action-types) 
 * 
 */
public abstract class AbstractHtmlAppGeneratorCommand extends CommandGroup implements LabelSupplier {
	
	@ParentCommand
	private Document.Supplier documentSupplier;
	
	protected abstract Collection<Label> getLabels(ProgressMonitor progressMonitor);
	
	@Mixin
	private ResourceSetMixIn resourceSetMixIn;
	
	@Option(
		names = {"-r", "--root-action" },
		description = {  
			"Root action URL or file path, resolved relative",
			"to the current directory"
		})
	private String rootAction;
	
	@Option(
			names = {"-R", "--add-to-root" },
			description = {  
				"Add labels to the root",
				"even if the principal is present"
			})
	private boolean addToRoot;
		
	@Option(
			names = {"-P", "--position" },
			description = {  
				"Insertion position",
				"Defaults to 0"
			})
	private int insertionIndex;		
		
	@Option(
		names = {"-f", "--file"},
		description = "Root action option is a file path")
	private boolean isFile;

	@Override
	public Label getEObject(ProgressMonitor progressMonitor) {
		Collection<Label> labels = getLabels(progressMonitor);
		return reduce(labels, progressMonitor);
	}
	
	protected Label reduce(
			Collection<Label> labels, 
			ProgressMonitor progressMonitor) {
		if (Util.isBlank(rootAction)) {
			if (labels.size() == 1) {
				return (Action) labels.iterator().next();
			}
			throw new UnsupportedOperationException("Multiple labels, use root action option");
		}
		
		try {
			URI rootURI = isFile ? URI.createFileURI(new File(this.rootAction).getAbsolutePath()) : URI.createURI(this.rootAction).resolve(URI.createFileURI(new File(".").getAbsolutePath()));
			if (!rootURI.hasFragment()) {
				rootURI = rootURI.appendFragment("/");
			}
			ResourceSet rSet = resourceSetMixIn.createResourceSet(progressMonitor);		
			Label root = (Label) rSet.getEObject(rootURI, true);
			EList<EObject> rootChildren = root.getChildren();
			if (rootChildren.isEmpty()) {
				rootChildren.addAll(labels);
			} else if (addToRoot) {
				rootChildren.addAll(insertionIndex, labels);				
			} else {
				EObject principal = rootChildren.get(0);
				if (principal instanceof Label) {
					EList<EObject> principalChildren = ((Label) principal).getChildren();
					if (principalChildren.isEmpty()) {
						principalChildren.addAll(labels);
					} else {
						principalChildren.addAll(insertionIndex, labels);						
					}
				} else {
					rootChildren.addAll(insertionIndex, labels);					
				}
			}
			return root;
		} catch (Exception e) {
			throw new NasdanikaException(e); 
		}
	}

}
