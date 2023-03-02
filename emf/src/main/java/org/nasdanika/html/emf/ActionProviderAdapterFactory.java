package org.nasdanika.html.emf;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

import org.eclipse.emf.common.notify.Adapter;
import org.eclipse.emf.common.notify.Notifier;
import org.eclipse.emf.common.util.Diagnostic;
import org.eclipse.emf.ecore.EStructuralFeature;
import org.nasdanika.common.Context;
import org.nasdanika.emf.ComposedAdapterFactory;
import org.nasdanika.emf.DiagnosticProvider;
import org.nasdanika.emf.DiagnosticProviderAdapter;
import org.nasdanika.emf.FunctionAdapterFactory;
import org.nasdanika.html.model.app.util.ActionProvider;
import org.nasdanika.ncore.Composite;
import org.nasdanika.ncore.NcorePackage;
import org.nasdanika.ncore.Temporal;

/**
 * @author Pavel
 *
 */
public class ActionProviderAdapterFactory extends ComposedAdapterFactory {
	
	public ActionProviderAdapterFactory(Context context) {
		// Registering adapter factories.
		registerAdapterFactory(
			new FunctionAdapterFactory<ActionProvider, Temporal>(
				NcorePackage.Literals.TEMPORAL, 
				ActionProvider.class, 
				this.getClass().getClassLoader(), 
				e -> new TemporalActionBuilder(e, context) {
					
					@Override
					protected String getHtmlExtension() {
						return ActionProviderAdapterFactory.this.getHtmlExtension();
					}
					
				}.asActionProvider()));
		
		registerAdapterFactory(
				new FunctionAdapterFactory<ActionProvider, Composite>(
					NcorePackage.Literals.COMPOSITE, 
					ActionProvider.class, 
					this.getClass().getClassLoader(), 
					e -> new CompositeActionBuilder<Composite>(e, context) {
						
						@Override
						protected String getHtmlExtension() {
							return ActionProviderAdapterFactory.this.getHtmlExtension();
						}
						
					}.asActionProvider()));
	}
	
	protected Diagnostic diagnostic;
	
	public ActionProviderAdapterFactory(Context context, Diagnostic diagnostic) {
		this(context);
		this.diagnostic = diagnostic;
	}
	
	@Override
	public Adapter adaptNew(Notifier target, Object type) {
		if (type == DiagnosticProvider.class) {
			return new DiagnosticProviderAdapter(target, this::getDiagnostic, this::getFeatureDiagnostic);
		}
		return super.adaptNew(target, type);
	}
	
	@Override
	public boolean isFactoryForType(Object type) {
		return super.isFactoryForType(type) || type == DiagnosticProvider.class;
	}
	
	/**
	 * Allows to customize html extension of actions. E.g. set it to aspx to be servable from OneDrive.
	 * @return
	 */
	protected String getHtmlExtension() {
		return "html";
	}
	
	// Diagnostic
	private static void collect(Notifier target, org.eclipse.emf.common.util.Diagnostic source, Collection<org.eclipse.emf.common.util.Diagnostic> accumulator) {
		if (source != null) {
			List<?> data = source.getData();
			if (source.getChildren().isEmpty()
					&& source.getSeverity() > org.eclipse.emf.common.util.Diagnostic.OK 
					&& data != null 
					&& data.size() == 1 
					&& data.get(0) == target) {
				accumulator.add(source);
			}
			for (org.eclipse.emf.common.util.Diagnostic child: source.getChildren()) {
				collect(target, child, accumulator);
			}
		}
	}
	
	protected Collection<org.eclipse.emf.common.util.Diagnostic> getDiagnostic(Notifier target) {
		Collection<org.eclipse.emf.common.util.Diagnostic> ret = new ArrayList<>();
		collect(target, diagnostic, ret);
		return ret;
	}
	
	private static void collect(Notifier target, EStructuralFeature feature, org.eclipse.emf.common.util.Diagnostic source, Collection<org.eclipse.emf.common.util.Diagnostic> accumulator) {
		if (source != null) {
			List<?> data = source.getData();
			if (source.getChildren().isEmpty() 
					&& source.getSeverity() > org.eclipse.emf.common.util.Diagnostic.OK 
					&& data != null 
					&& data.size() > 1 
					&& data.get(0) == target 
					&& data.get(1) == feature) {
				accumulator.add(source);
			}
			for (org.eclipse.emf.common.util.Diagnostic child: source.getChildren()) {
				collect(target, feature, child, accumulator);
			}
		}
	}

	protected Collection<org.eclipse.emf.common.util.Diagnostic> getFeatureDiagnostic(Notifier target, EStructuralFeature feature) {
		Collection<org.eclipse.emf.common.util.Diagnostic> ret = new ArrayList<>();
		collect(target, feature, diagnostic, ret);
		return ret;
	}
	
	
	
}
