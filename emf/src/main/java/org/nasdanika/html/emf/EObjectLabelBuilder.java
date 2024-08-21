package org.nasdanika.html.emf;

import java.util.Objects;
import java.util.UUID;
import java.util.function.Consumer;

import org.eclipse.emf.common.notify.impl.AdapterImpl;
import org.eclipse.emf.common.util.TreeIterator;
import org.eclipse.emf.ecore.EObject;
import org.eclipse.emf.ecore.util.EcoreUtil;
import org.nasdanika.common.ProgressMonitor;
import org.nasdanika.emf.EObjectAdaptable;
import org.nasdanika.html.model.app.Action;
import org.nasdanika.html.model.app.ActionReference;
import org.nasdanika.html.model.app.AppFactory;
import org.nasdanika.html.model.app.Label;
import org.nasdanika.html.model.app.util.ActionSupplier;
import org.nasdanika.html.model.app.util.LabelBuilder;
import org.nasdanika.html.model.app.util.LabelProvider;
import org.nasdanika.html.model.app.util.LabelProvider.Resolver;
import org.nasdanika.ncore.ModelElement;

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
	public Label build(Label label, Consumer<Resolver> resolverCollector, ProgressMonitor progressMonitor) {
		if (label == null) {
			label = newLabel(resolverCollector, progressMonitor);
		}
		// TODO Auto-generated method stub
		return label;
	}
	
	/**
	 * Creates a new label if the build() argument label is null.
	 * @return
	 */
	protected Label newLabel(Consumer<Resolver> resolverCollector, ProgressMonitor progressMonitor) {
		T semanticElement = getTarget();
		if (semanticElement instanceof ModelElement) {
			EObject labelPrototype = ((ModelElement) semanticElement).getLabelPrototype();
			if (labelPrototype instanceof ActionReference) {
				labelPrototype = ((ActionReference) labelPrototype).getTarget();
			} 
			
			if (labelPrototype instanceof Action) {
				Action copy = EcoreUtil.copy((Action) labelPrototype);
				copy.setUuid(UUID.randomUUID().toString());
				TreeIterator<EObject> cit = copy.eAllContents();
				while (cit.hasNext()) {
					EObject next = cit.next();
					if (next instanceof ModelElement) {
						((ModelElement) next).setUuid(UUID.randomUUID().toString());
					}
				}
				return copy;
			}
			if (labelPrototype != null) {
				LabelProvider labelProvider = Objects.requireNonNull((LabelProvider) EcoreUtil.getRegisteredAdapter(labelPrototype, LabelProvider.class), "Cannot adapt " + labelPrototype + " to " + LabelProvider.class);
				Label ret =  labelProvider.create(null, resolverCollector, progressMonitor);
				ret.setUuid(UUID.randomUUID().toString());				
				TreeIterator<EObject> cit = ret.eAllContents();
				while (cit.hasNext()) {
					EObject next = cit.next();
					if (next instanceof ModelElement) {
						((ModelElement) next).setUuid(UUID.randomUUID().toString());
					}
				}
				return ret;
			}
		}
		return AppFactory.eINSTANCE.createAction();
	}	
	
	/**
	 * Adapts child eObject to and adds to the list of children to be configured.
	 * @param child
	 * @return
	 */
	protected LabelBuilder adaptChild(EObject child) {
		return EObjectAdaptable.adaptTo(child, LabelBuilder.class);
	}	

}
