package org.nasdanika.html.model.app.gen.cli;

import java.io.File;
import java.util.Collection;
import java.util.Collections;

import org.eclipse.emf.common.util.EList;
import org.eclipse.emf.common.util.URI;
import org.eclipse.emf.ecore.EObject;
import org.eclipse.emf.ecore.resource.ResourceSet;
import org.nasdanika.cli.CommandGroup;
import org.nasdanika.cli.ResourceSetMixIn;
import org.nasdanika.common.ProgressMonitor;
import org.nasdanika.common.Util;
import org.nasdanika.html.model.app.Label;
import org.nasdanika.html.model.app.util.LabelSupplier;

import picocli.CommandLine.Mixin;
import picocli.CommandLine.Option;

/**
 * Base class for label supplier commands.
 * Reduces a list of {@link Label}s to a single label by either using the first and only label,
 * or adding the labels to a root or principal action (https://html-app.models.nasdanika.org/index.html#action-types) 
 * 
 */
public abstract class AbstractHtmlAppGeneratorCommand extends CommandGroup implements LabelSupplier {
		
	protected abstract Collection<Label> getLabels(ProgressMonitor progressMonitor);
	
	@Mixin
	private ResourceSetMixIn resourceSetMixIn;
	
	@Option(
		names = {"-r", "--root-label" },
		description = {  
			"Root label URL or file path, resolved relative",
			"to the current directory"
		})
	private String rootLabel;
	
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
	public Collection<Label> getEObjects(ProgressMonitor progressMonitor) {
		Collection<Label> labels = getLabels(progressMonitor);
		return reduce(labels, progressMonitor);
	}
	
	protected Collection<Label> reduce(
			Collection<Label> labels, 
			ProgressMonitor progressMonitor) {
		if (Util.isBlank(rootLabel)) {
			return labels;
		}
		
		URI rootURI = isFile ? URI.createFileURI(new File(this.rootLabel).getAbsolutePath()) : URI.createURI(this.rootLabel).resolve(URI.createFileURI(new File(".").getAbsolutePath()).appendSegment(""));
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
		return Collections.singleton(root);
	}

}
