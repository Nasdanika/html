package org.nasdanika.html.model.app.graph.drawio;

import java.util.Collection;
import java.util.Map;

import org.eclipse.emf.common.util.URI;
import org.nasdanika.common.ProgressMonitor;
import org.nasdanika.common.Supplier;
import org.nasdanika.drawio.Element;
import org.nasdanika.drawio.Page;
import org.nasdanika.graph.processor.ProcessorElement;
import org.nasdanika.graph.processor.ProcessorInfo;
import org.nasdanika.graph.processor.RegistryEntry;
import org.nasdanika.html.model.app.Action;
import org.nasdanika.html.model.app.AppFactory;
import org.nasdanika.html.model.app.Label;
import org.nasdanika.html.model.app.graph.WidgetFactory;

/**
 * Page processor creates an action for top level pages, adds page diagram and root documentation.
 * For linked pages passes through child labels to be used by the linking element. 
 * Also generates diagram embedding widget to add to the linking element page. 
 */
public class PageProcessor extends LinkTargetProcessor {
	
	/**
	 * Forcing top-level page
	 */
	public boolean isTopLevelPage;

	@ProcessorElement
	public Page page;	
	
	@RegistryEntry("#element.model.root == #this")
	public RootProcessor rootProcessor;
//	public Map<Element, ProcessorInfo<WidgetFactory>> registry;
	
	@Override
	public void resolve(URI base, ProgressMonitor progressMonitor) {
		// TODO Auto-generated method stub
		super.resolve(base, progressMonitor);
	}
	
	@Override
	public Supplier<Collection<Label>> createLabelsSupplier() {
		if (isTopLevelPage || referrers.isEmpty()) {
			return super.createLabelsSupplier();			
		}
		return rootProcessor.createLabelsSupplier();
	}	
	
	@Override
	public Object createLabel(ProgressMonitor progressMonitor) {
		Action action = AppFactory.eINSTANCE.createAction();
		action.setText(page.getName());
		action.setLocation("index.html");
		// TODO - diagram, documentation from root
		return action;
	}	
	
	// Supplier - then with root
	
}
