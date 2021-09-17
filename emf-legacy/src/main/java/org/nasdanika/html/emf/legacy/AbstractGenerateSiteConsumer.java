package org.nasdanika.html.emf.legacy;

import static org.nasdanika.html.app.impl.Util.writeAction;

import java.io.File;
import java.util.Collection;

import org.nasdanika.common.BiSupplier;
import org.nasdanika.common.Consumer;
import org.nasdanika.common.Context;
import org.nasdanika.common.ProgressMonitor;
import org.nasdanika.common.SupplierFactory;
import org.nasdanika.common.Util;
import org.nasdanika.common.resources.BinaryEntityContainer;
import org.nasdanika.common.resources.Container;
import org.nasdanika.common.resources.FileSystemContainer;
import org.nasdanika.html.app.Action;
import org.nasdanika.html.app.Application;
import org.nasdanika.html.app.viewparts.AdaptiveNavigationPanelViewPart.Style;

/**
 * Consumes root action and child actions and generates a site.
 * @author Pavel
 *
 */
public abstract class AbstractGenerateSiteConsumer implements Consumer<BiSupplier<Action,Collection<Action>>> {
	
	protected Container<String> output;
	protected SupplierFactory<? extends Application> applicationSupplierFactory;
	private Context context;

	protected AbstractGenerateSiteConsumer(SupplierFactory<? extends Application> applicationSupplierFactory, Container<String> output, Context context) {
		this.applicationSupplierFactory = applicationSupplierFactory;
		this.output = output;
		this.context = context;
	}

	protected AbstractGenerateSiteConsumer(SupplierFactory<? extends Application> applicationSupplierFactory, BinaryEntityContainer output, Context context) {
		this(applicationSupplierFactory, output.stateAdapter().adapt(Util.INPUT_STREAM_TO_STRING_DECODER, Util.OBJECT_TO_INPUT_STREAM_ENCODER), context);
	}
	
	protected AbstractGenerateSiteConsumer(SupplierFactory<? extends Application> applicationSupplierFactory, File output, Context context) {
		this(applicationSupplierFactory, new FileSystemContainer(output), context);
	}	
		
	@Override
	public double size() {
		return 1;
	}

	@Override
	public String name() {
		return "Generating site";
	}

	@Override
	public void execute(BiSupplier<Action,Collection<Action>> inputs, ProgressMonitor progressMonitor) throws Exception {
		Action principal;
		Collection<Action> children = inputs.getSecond();
		Action rootAction = inputs.getFirst();
		if (children != null && children.size() == 1) {
			principal = children.iterator().next();
		} else {
			principal = createPrincipalAction(rootAction, children);
		}
		rootAction.getChildren().add(principal);

		writeAction(
				rootAction, 
				principal, 
				rootAction, 
				context.getString(Context.BASE_URI_PROPERTY), 
				output, 
				context, 
				getNavigationPanelStyle(), 
				applicationSupplierFactory, 
				progressMonitor);
	}
	
	protected Style getNavigationPanelStyle() {
		return Style.AUTO;
	}
	
	// --- Abstract methods to implement ---

	/**
	 * If there is more than one top-level element this method is called to create a grouping principal action.
	 * If there is only one top level element then that element becomes a principal action.
	 * @param rootAction
	 * @param topLevelElements
	 * @return
	 */
	protected abstract Action createPrincipalAction(Action rootAction, Collection<Action> children);
	
}
