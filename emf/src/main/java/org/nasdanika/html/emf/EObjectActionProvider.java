package org.nasdanika.html.emf;

import java.util.function.BiConsumer;
//import java.util.function.Consumer;

import org.eclipse.emf.common.notify.impl.AdapterImpl;
import org.eclipse.emf.ecore.EAttribute;
import org.eclipse.emf.ecore.EObject;
import org.eclipse.emf.ecore.EReference;
import org.eclipse.emf.ecore.ETypedElement;
import org.nasdanika.common.CollectionCompoundConsumer;
import org.nasdanika.common.ProgressMonitor;
import org.nasdanika.common.Util;
import org.nasdanika.emf.EObjectAdaptable;
import org.nasdanika.emf.EmfUtil;
import org.nasdanika.exec.content.ContentFactory;
import org.nasdanika.exec.content.Text;
import org.nasdanika.html.model.app.Action;
import org.nasdanika.html.model.app.AppFactory;
import org.nasdanika.html.model.app.Label;
import org.nasdanika.html.model.app.util.ActionProvider;
import org.nasdanika.html.model.app.util.ActionSupplier;

public class EObjectActionProvider<T extends EObject> extends AdapterImpl implements ActionProvider {
	
	protected Action action;

	public EObjectActionProvider(T target) {
		setTarget(target);
	}
	
	@SuppressWarnings("unchecked")
	@Override
	public T getTarget() {
		return (T) super.getTarget();
	}
	
	protected class EObjectActionResolverAdapter extends AdapterImpl implements EObjectActionResolver {
		
		@Override
		public boolean isAdapterForType(Object type) {
			return type == EObjectActionResolver.class;
		}
		
		private CollectionCompoundConsumer<java.util.function.Function<EObject, Action>> accumulator;
		
		public EObjectActionResolverAdapter() {
			accumulator = new CollectionCompoundConsumer<java.util.function.Function<EObject, Action>>("Resolve consumer accumulator");
			accumulator.add(new org.nasdanika.common.Consumer<java.util.function.Function<EObject, Action>>() {

				@Override
				public double size() {
					return 1;
				}

				@Override
				public String name() {
					return "Calling resolve()";
				}

				@Override
				public void execute(java.util.function.Function<EObject, Action> registry, ProgressMonitor progressMonitor) throws Exception {
					EObjectActionProvider.this.resolve((Action) getTarget(), registry, progressMonitor);					
				}
				
			});
		}
		
		public void add(org.nasdanika.common.Consumer<java.util.function.Function<EObject, Action>> consumer) {
			accumulator.add(consumer);
		}

		/**
		 * Executes and removes itself from the list of adapters.
		 */
		@Override
		public void execute(java.util.function.Function<EObject, Action> registry, ProgressMonitor progressMonitor) throws Exception {
			accumulator.execute(registry, progressMonitor);		
			((Action) getTarget()).eAdapters().remove(this);
		}

		@Override
		public double size() {
			return 1;
		}

		@Override
		public String name() {
			return "Resolving " + EObjectActionProvider.this.name();
		}
		
	}
	
	/**
	 * Calls createAction() to creates an action and contained actions, configures it to the extent possible
	 * with the action hierarchy being built and not all action being available. Configuration
	 * which requires action cross-referencing shall be performed in resolve() and be invoked
	 * by a EObjectActionResolver adapter execute() method. The adapter is created and attached in this method.
	 * Resolve adapter is composite and execute() shall be invoked only for the root action's adapter if it is present.
	 * The resolving adapter removes itself upon resolution.  
	 */
	@Override
	public Action execute(BiConsumer<EObject,Action> registry, ProgressMonitor progressMonitor) throws Exception {
		EObjectActionResolverAdapter resolver = new EObjectActionResolverAdapter();
		Action ret = createAction(registry, resolver::add, progressMonitor);
		registry.accept(getTarget(), ret);
		ret.eAdapters().add(resolver);				
		return ret;
	}
	
	/**
	 * Creates an action and contained actions. Configures the action to the extent possible
	 * with the action hierarchy being built and not all action being available. 
	 * Configuration which requires availability of the fully constructed action hierarchy 
	 * shall be performed in commands added to resolveCommandConsumer.
	 * @param resolveConsumer Consumer of consumers to be executed at the resolve phase.
	 * @param progressMonitor
	 * @return
	 */
	protected Action createAction(
			BiConsumer<EObject,Action> registry, 
			java.util.function.Consumer<org.nasdanika.common.Consumer<java.util.function.Function<EObject, Action>>> resolveConsumer, 
			ProgressMonitor progressMonitor) throws Exception {
		Action ret = AppFactory.eINSTANCE.createAction();
		
		
		return ret;
	}
	
	
	protected void resolve(Action action, java.util.function.Function<EObject, Action> registry, ProgressMonitor progressMonitor) {
		System.out.println("Resolving " + action);
	}	
	
		
	/**
	 * Creates a label for {@link ETypedElement}s such as {@link EAttribute} and {@link EReference} to be used
	 * in (table) headers, tabs, etc. This method creates a label using {@link AppFactory} instance, passes it to configureETypedElementLabel and returns.
	 * @param eTypedElement Typed element for which to create a label.
	 * @param inModal If true then the label is created for use in a modal dialog and shall not contain triggers for other modal dialogs.
	 * @return
	 */
	protected Label createETypedElementLabel(ETypedElement eTypedElement, boolean inModal) {
		Label label = AppFactory.eINSTANCE.createLabel();
		configureETypedElementLabel(eTypedElement, label, inModal);
		return label;
	}

	/**
	 * Configures a typed element label or action.   
	 * @param eTypedElement
	 * @param label
	 * @param inModal
	 */
	protected void configureETypedElementLabel(ETypedElement eTypedElement, Label label, boolean inModal) {
		label.setText(typedElementLabelText(eTypedElement));
		label.setIcon(typedElementIcon(eTypedElement));
		// TODO - tooltip, description
	}
	
	protected String typedElementLabelText(ETypedElement type) {
		return EmfUtil.getNasdanikaAnnotationDetail(type, EmfUtil.LABEL_KEY, Util.nameToLabel(type.getName()));
	}
	
	protected String typedElementIcon(ETypedElement member) {
		return EmfUtil.getNasdanikaAnnotationDetail(member, EmfUtil.ICON_KEY, EmfUtil.getNasdanikaAnnotationDetail(member.getEType(), EmfUtil.ICON_KEY));
	}	
	
	/**
	 * Adapts child eObject to {@link ActionSupplier}.
	 * @param child
	 * @return
	 */
	protected ActionProvider adaptChild(EObject child) {
		return EObjectAdaptable.adaptTo(child, ActionProvider.class);
	}	
	
	/**
	 * Convenience method to add textual content.
	 * @param content
	 */
	protected static void addContent(Action action, String content) {
		Text text = ContentFactory.eINSTANCE.createText();
		text.setContent(content);
		action.getContent().add(text);
	}

	public Action getAction() {
		return action;
	}

	@Override
	public double size() {
		return 1;
	}

	@Override
	public String name() {
		return "Action provider for " + getTarget();
	}
	
	
//	public enum Classifier {
//		
//		/**
//		 * Default action for the literal.
//		 */
//		DEFAULT,
//		
//		/**
//		 * Properties table.
//		 */
//		PROPERTIES,
//		
//		/**
//		 * For {@link TypeElement} literals create an action for each of (return) value elements.
//		 */
//		ELEMENTS,
//		
//		/**
//		 * For {@link TypeElement} literals create an action or a list of actions for each of 
//		 * (return) value elements and then sort results alphabetically by text.
//		 */
//		ELEMENTS_SORTED,
//		
//		/**
//		 * Child actions for the member, e.g. a section with action list
//		 */
//		ACTIONS,
//				
//		/**
//		 * No classifier. Used to suppress inherited classifier via configuration.
//		 */
//		NONE;
//		
//		public final String LITERAL;
//		
//		private Classifier() {
//			this.LITERAL = name().toLowerCase().replace('_', '-');
//		}
//		
//	}	
//		
//	// subClassifier and vararg arguments?
//	/**
//	 * @param literal Part of the target to return a list of actions for. {@link EClass} for self, 
//	 * {@link EStructuralFeature} such as {@link EAttribute} or {@link EReference}, or {@link EOperation}.
//	 * @param classifier
//	 * @return A list of actions parts of the target or the target itself. 
//	 */
//	public List<Action> getActions(
//			EModelElement literal, 
//			Classifier classifier) throws Exception {
//		if (literal instanceof EClass) {
//			return getEClassActions((EClass) literal, classifier);
//		}
//		if (literal instanceof ETypedElement) {
//			return getTypedElementActions((ETypedElement) literal, getTypedElementValue((ETypedElement) literal), classifier);
//		}
//		throw new UnsupportedOperationException("Unsupported literal: " + literal);
//	}
//	
//	protected List<Action> getEClassActions(EClass literal, Classifier classifier) throws Exception  {
//		
//		throw new UnsupportedOperationException();
//	}
//
//	private List<Action> getTypedElementActions(ETypedElement literal, Object typedElementValue, Classifier classifier) throws Exception  {
//		// Further dispatching.
//		throw new UnsupportedOperationException();
//	}
//
//	private Object getTypedElementValue(ETypedElement typedElement) throws Exception  {
//		if (typedElement instanceof EStructuralFeature) {
//			return getTarget().eGet((EStructuralFeature) typedElement);
//		}
//		
//		EOperation eOp = (EOperation) typedElement;
//		
//		// A hack to get to the container.
//		if (eOp == EcorePackage.Literals.EOBJECT___ECONTAINER) {
//			return getTarget().eContainer();
//		}
//		
//		EList<EParameter> params = eOp.getEParameters();
//		if (params.isEmpty()) {
//			return getTarget().eInvoke(eOp, ECollections.emptyEList());
//		}
//		return getTarget().eInvoke(eOp, bind(eOp));
//	}
//	
//	/**
//	 * Returns a list of arguments for the operation.
//	 * @param eOperation
//	 * @return
//	 * @throws Exception
//	 */
//	protected EList<Object> bind(EOperation eOperation) throws Exception {
//		throw new UnsupportedOperationException();
//	}
//
//	private Optional<Action> defaultAction;
//	
//	public Action getAction() throws Exception {
//		if (defaultAction == null) {
//			List<Action> actions = getActions(getTarget().eClass(), Classifier.DEFAULT);
//			
//			if (actions == null || actions.isEmpty()) {
//				defaultAction = Optional.empty();
//			} else if (actions.size() == 1) {
//				defaultAction = Optional.of(actions.get(0));
//			}
//			
//			throw new UnsupportedOperationException("Multiple default actions are not supported");
//		}
//		return defaultAction.orElse(null);
//	}
//		
//	/**
//	 * A list of members - {@link EStructuralFeature}s and {@link EOperation}s 
//	 * @return
//	 */
//	protected List<ETypedElement> getMembers() {
//		
//	}
		
}
