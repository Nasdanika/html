package org.nasdanika.html.model.app.graph.drawio;

import java.util.Collection;
import java.util.Collections;

import org.eclipse.emf.common.util.URI;
import org.nasdanika.common.ProgressMonitor;
import org.nasdanika.common.Supplier;
import org.nasdanika.drawio.Page;
import org.nasdanika.graph.processor.RegistryEntry;
import org.nasdanika.html.model.app.Action;
import org.nasdanika.html.model.app.AppFactory;
import org.nasdanika.html.model.app.Label;

/**
 * Page processor creates an action for top level pages, adds page diagram and root documentation.
 * For linked pages passes through child labels to be used by the linking element. 
 * Also generates diagram embedding widget to add to the linking element page. 
 */
public class PageProcessor extends LinkTargetProcessor<Page> {
	
	/**
	 * Forcing top-level page
	 */
	public boolean isTopLevelPage;
	
	@RegistryEntry("#element.model.root == #this")
	public RootProcessor rootProcessor;
	
	@Override
	public void resolve(URI base, ProgressMonitor progressMonitor) {
		// TODO Auto-generated method stub
		super.resolve(base, progressMonitor);
	}
	
	@Override
	public Supplier<Collection<Label>> createLabelsSupplier() {
		if (isTopLevelPage || referrers.isEmpty()) {
			return rootProcessor.createLabelsSupplier().then(this::createPageLabels);			
		}
		return rootProcessor.createLabelsSupplier();
	}
	
	protected Collection<Label> createPageLabels(Collection<Label> rootLabels) {
		Action action = AppFactory.eINSTANCE.createAction();		
		action.setText(element.getName());
		action.setLocation("index.html");
		action.getChildren().addAll(rootLabels);
		// TODO - diagram, documentation from root
		return Collections.singleton(action);
	}	
	
}
