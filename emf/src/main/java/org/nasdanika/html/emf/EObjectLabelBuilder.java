package org.nasdanika.html.emf;

import java.util.function.BiConsumer;
import java.util.function.Consumer;

import org.eclipse.emf.common.notify.impl.AdapterImpl;
import org.eclipse.emf.ecore.EObject;
import org.nasdanika.common.ProgressMonitor;
import org.nasdanika.html.model.app.Label;
import org.nasdanika.html.model.app.util.LabelBuilder;
import org.nasdanika.html.model.app.util.LabelProvider.Resolver;

public class EObjectLabelBuilder<T extends EObject> extends AdapterImpl implements LabelBuilder {

	protected org.nasdanika.common.Context context;

	public EObjectLabelBuilder(T target, org.nasdanika.common.Context context) {
		setTarget(target);
		this.context = context;
	}
	
	@SuppressWarnings("unchecked")
	@Override
	public T getTarget() {
		return (T) super.getTarget();
	}
	
	@Override
	public double size() {
		return 1;
	}

	@Override
	public String name() {
		return "Label builder for " + getTarget();
	}

	@Override
	public Label build(
			Label label, 
			BiConsumer<Object, Label> registry, 
			Consumer<Resolver> resolverCollector,
			ProgressMonitor progressMonitor) {
		// TODO Auto-generated method stub
		return null;
	}

}
